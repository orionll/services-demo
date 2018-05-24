import org.example.FigureDescriptorProvider;
import org.example.ext.ExtFigureDescriptorProvider;

module mypaint.ext {
    requires mypaint.core;

    provides FigureDescriptorProvider with ExtFigureDescriptorProvider;
}