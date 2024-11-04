import java.awt.*;
import javax.swing.*;
public class Window extends JFrame {
    Window(String windowTitle) {
        super(windowTitle);
        setResizable(false);
        setSize(1280,1024);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
