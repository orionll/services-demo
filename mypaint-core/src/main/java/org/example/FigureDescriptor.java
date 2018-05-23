package org.example;

import org.eclipse.swt.widgets.Shell;

public interface FigureDescriptor {

    String getId();

    String getName();

    Figure createFigure(Shell shell);
}
