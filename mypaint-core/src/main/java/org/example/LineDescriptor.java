package org.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;

public final class LineDescriptor implements FigureDescriptor {

    @Override
    public String getId() {
        return "line";
    }

    @Override
    public String getName() {
        return "Line";
    }

    @Override
    public Figure createFigure(Shell shell) {
        var color = shell.getDisplay().getSystemColor(SWT.COLOR_RED);
        return (gc, bounds) -> {
            gc.setForeground(color);
            gc.drawLine(
                bounds.x,
                bounds.y,
                bounds.x + bounds.width,
                bounds.y + bounds.height);
        };
    }
}
