package org.example;

import java.util.List;
import java.util.stream.Stream;

public class BaseDescriptorProvider implements FigureDescriptorProvider {

    @Override
    public Stream<FigureDescriptor> getFigureDescriptors() {
        return Stream.of(
            new LineDescriptor(),
            new RectangleDescriptor(),
            new EllipseDescriptor());
    }
}
