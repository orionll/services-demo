package org.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Main {

    public static void main(String[] args) {
        var pluginsDir = Path.of("plugins");
        var pluginsFinder = ModuleFinder.of(pluginsDir);
        var plugins = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toSet());
        if (!plugins.isEmpty()) {
            System.out.println("Found plugins in " + pluginsDir.toFile().getAbsolutePath());
            plugins.forEach(plugin -> System.out.println("  " + plugin));
        }

        var pluginsConfiguration = ModuleLayer.boot().configuration().resolve(pluginsFinder, ModuleFinder.of(), plugins);
        var layer = ModuleLayer.boot().defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());

        createShell(layer);
    }

    private static void createShell(ModuleLayer layer) {
        var display = new Display();
        var shell = new Shell(display);
        shell.setSize(600, 400);
        shell.setLocation(300, 200);
        shell.setText("MyPaint");
        shell.setLayout(new FillLayout());

        var descriptors = getDescriptors(layer);
        new DrawArea(shell, descriptors).initialize();

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private static List<FigureDescriptor> getDescriptors(ModuleLayer layer) {
        return FigureDescriptorProvider
                .getProviders(layer)
                .flatMap(FigureDescriptorProvider::getFigureDescriptors)
                .collect(Collectors.toUnmodifiableList());
    }
}

final class DrawArea {

    private final List<FigureDescriptor> descriptors;
    private final Shell shell;
    private final List<Shape> shapes = new ArrayList<>();

    private Figure currentFigure;
    private Shape currentShape;

    DrawArea(Shell shell, List<FigureDescriptor> descriptors) {
        this.shell = shell;
        this.descriptors = descriptors;
    }

    void initialize() {
        var composite = new Composite(this.shell, SWT.NONE);
        var layout = new GridLayout(2, false);
        composite.setLayout(layout);
        var canvas = createCanvas(composite);
        var toolBar = new Composite(composite, SWT.NONE);
        toolBar.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));
        toolBar.setLayout(new GridLayout());
        var font = new Font(this.shell.getDisplay(), new FontData("Arial", 18, SWT.NORMAL));

        for (var descriptor : this.descriptors) {
            var button = new Button(toolBar, SWT.RADIO);
            button.setText(descriptor.getName());
            button.setFont(font);
            button.addSelectionListener(SelectionListener.widgetSelectedAdapter(e ->
                    this.currentFigure = descriptor.createFigure(this.shell)));
        }

        var clearButton = new Button(toolBar, SWT.PUSH);
        clearButton.setText("Clear");
        clearButton.setFont(font);
        clearButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
        clearButton.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
            this.shapes.clear();
            canvas.redraw();
        }));
    }

    private Canvas createCanvas(Composite composite) {
        var canvas = new Canvas(composite, SWT.DOUBLE_BUFFERED | SWT.BORDER);
        canvas.setBackground(this.shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        canvas.addPaintListener(e -> {
            e.gc.setLineWidth(3);
            for (var shape : this.shapes) {
                shape.draw(e.gc);
            }
            if (this.currentShape != null) {
                this.currentShape.draw(e.gc);
            }
        });

        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseUp(MouseEvent e) {
                if (e.button == 1) {
                    var bounds = DrawArea.this.currentShape.getBounds();
                    bounds.width = e.x - bounds.x;
                    bounds.height = e.y - bounds.y;
                    DrawArea.this.shapes.add(DrawArea.this.currentShape);
                    DrawArea.this.currentShape = null;
                    canvas.redraw();
                }
            }

            @Override
            public void mouseDown(MouseEvent e) {
                if (e.button == 1) {
                    var bounds = new Rectangle(e.x, e.y, 0, 0);
                    DrawArea.this.currentShape = new Shape(DrawArea.this.currentFigure, bounds);
                }
            }

            @Override
            public void mouseDoubleClick(MouseEvent e) {
            }
        });

        canvas.addMouseMoveListener(e -> {
            if ((e.stateMask & SWT.BUTTON1) != 0 && this.currentShape != null) {
                var bounds = this.currentShape.getBounds();
                bounds.width = e.x - bounds.x;
                bounds.height = e.y - bounds.y;
                canvas.redraw();
            }
        });

        return canvas;
    }
}

final class Shape {
    private final Figure figure;
    private final Rectangle bounds;

    Shape(Figure figure, Rectangle bounds) {
        this.figure = figure;
        this.bounds = bounds;
    }

    Rectangle getBounds() {
        return this.bounds;
    }

    void draw(GC gc) {
        this.figure.draw(gc, this.bounds);
    }
}