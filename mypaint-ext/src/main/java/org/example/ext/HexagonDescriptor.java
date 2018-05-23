package org.example.ext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;
import org.example.Figure;
import org.example.FigureDescriptor;

public final class HexagonDescriptor implements FigureDescriptor {

    @Override
    public String getId() {
        return "hexagon";
    }

    @Override
    public String getName() {
        return "Hexagon";
    }

    @Override
    public Figure createFigure(Shell shell) {
        var color = shell.getDisplay().getSystemColor(SWT.COLOR_MAGENTA);
        return (gc, bounds) -> {
            gc.setForeground(color);
            int leftX = bounds.x;
            int leftY = bounds.y + bounds.height/2;
            int topLeftX = bounds.x + bounds.width/3;
            int topLeftY = bounds.y;
            int topRightX = bounds.x + 2*bounds.width/3;
            int topRightY = bounds.y;
            int rightX = bounds.x + bounds.width;
            int rightY = bounds.y + bounds.height/2;
            int bottomRightX = bounds.x + 2*bounds.width/3;
            int bottomRightY = bounds.y + bounds.height;
            int bottomLeftX = bounds.x + bounds.width/3;
            int bottomLeftY = bounds.y + bounds.height;
            int[] points = {
                leftX, leftY,
                topLeftX, topLeftY,
                topRightX, topRightY,
                rightX, rightY,
                bottomRightX, bottomRightY,
                bottomLeftX, bottomLeftY};
            gc.drawPolygon(points);
        };
    }
}
