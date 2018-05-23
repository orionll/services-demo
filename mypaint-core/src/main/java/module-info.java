
module mypaint.core {
    requires transitive org.eclipse.swt.win32.win32.x64;

    exports org.example;
    uses org.example.FigureDescriptorProvider;
    provides org.example.FigureDescriptorProvider with org.example.BaseDescriptorProvider;
}