package org.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;

public final class RectangleDescriptor implements FigureDescriptor {

    @Override
    public String getId() {
        return "rectangle";
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public Figure createFigure(Shell shell) {
        var color = shell.getDisplay().getSystemColor(SWT.COLOR_GREEN);
        return (gc, bounds) -> {
            gc.setForeground(color);
            gc.drawRectangle(bounds);
        };
    }
}