package org.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;

public final class EllipseDescriptor implements FigureDescriptor {

    @Override
    public String getId() {
        return "ellipse";
    }

    @Override
    public String getName() {
        return "Ellipse";
    }

    @Override
    public Figure createFigure(Shell shell) {
        var color = shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
        return (gc, bounds) -> {
            gc.setForeground(color);
            gc.drawOval(
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
        };
    }
}