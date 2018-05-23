package org.example;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public interface Figure {

    void draw(GC gc, Rectangle bounds);
}
