package org.example.ext;

import org.example.FigureDescriptor;
import org.example.FigureDescriptorProvider;

import java.util.stream.Stream;

public class ExtFigureDescriptorProvider implements FigureDescriptorProvider {

    @Override
    public Stream<FigureDescriptor> getFigureDescriptors() {
        return Stream.of(new HexagonDescriptor());
    }
}
