import org.example.BaseFigureDescriptorProvider;
import org.example.FigureDescriptorProvider;

module mypaint.core {
    requires transitive org.eclipse.swt.win32.win32.x64;

    exports org.example;

    uses FigureDescriptorProvider;
    provides FigureDescriptorProvider with BaseFigureDescriptorProvider;
}