import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame {
    public JPanel main_menu;
    public CardLayout menu_layout;
    private Game_window game_menu;
    public Window(String windowTitle) {
        super(windowTitle);
        setResizable(false);
        setSize(1280,1024);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menu_layout = new CardLayout();
        main_menu = new JPanel(menu_layout);

        Init_start_menu();
        Init_game_menu();
        Init_level_menu();
        add(main_menu);
    }

    private void Init_start_menu() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7,3));

        ImageIcon start_photo = new ImageIcon("start.png");
        ImageIcon level_photo = new ImageIcon("level.png");
        ImageIcon exit_photo = new ImageIcon("exit.png");

        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(new JLabel(""));
        JButton start = new JButton("Start");
        start.setIcon(start_photo);
        p.add(start);
        start.addActionListener(new Start_listener());
        p.add(new JLabel(""));

        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(new JLabel(""));
        JButton level_select = new JButton("Level Select");
        level_select.setIcon(level_photo);
        p.add(level_select);
        level_select.addActionListener(new Level_listener());
        p.add(new JLabel(""));

        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(new JLabel(""));
        JButton exit = new JButton("Exit");
        exit.setIcon(exit_photo);
        p.add(exit);
        exit.addActionListener(new exit_listener());
        p.add(new JLabel(""));

        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));
        main_menu.add(p, "start menu");
    }

    private void Init_game_menu(){
        game_menu = new Game_window(menu_layout, main_menu);
        main_menu.add(game_menu, "game menu");
    }

    private void Init_level_menu(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7,3));
        p.add(new JButton("Level Select"));
        main_menu.add(p, "level menu");
    }

    class Start_listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Start");
            menu_layout.show(main_menu, "game menu");
            game_menu.timer_begin();
        }
    }

    class Level_listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Level");
            menu_layout.show(main_menu, "level menu");
        }
    }

    static class exit_listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
}

}
