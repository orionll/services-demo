package org.example;

import java.util.Comparator;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;

public interface FigureDescriptorProvider {

    Stream<FigureDescriptor> getFigureDescriptors();

    static Stream<FigureDescriptorProvider> getProviders() {
        return ServiceLoader
                .load(FigureDescriptorProvider.class)
                .stream()
                .map(Provider::get)
                .sorted(Comparator.comparingInt(provider -> provider instanceof BaseFigureDescriptorProvider ? 0 : 1));
    }
}

