import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game_window extends JPanel {
    private JButton menu_button;
    public Game_window(CardLayout menu_layout, JPanel main_menu) {
        setLayout(new BorderLayout());
        buttons();
        menu_listener(menu_layout, main_menu);
    }

    private void buttons(){
        menu_button = new JButton("menu");
        add(menu_button);
    }

    private void menu_listener(CardLayout menu_layout, JPanel main_menu) {
        menu_button.addActionListener(e -> menu_layout.show(main_menu, "start menu"));
    }
}

