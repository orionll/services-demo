
module mypaint.ext {
    requires mypaint.core;

    provides org.example.FigureDescriptorProvider with org.example.ext.ExtFigureDescriptorProvider;
}