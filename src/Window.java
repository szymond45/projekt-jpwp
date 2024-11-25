import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame {
    public JPanel main_menu;
    public CardLayout menu_layout;
    private Game_window game_menu;
    private JButton start;
    private JButton level_select;
    private JButton exit;
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
        add_listeners();
        add(main_menu);
    }

    private void Init_start_menu() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7,3));


        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(new JLabel(""));

        start = new JButton("Start");
        start.setIcon(new ImageIcon("Resources/start.png"));
        start.setOpaque(false);
        start.setRolloverEnabled(false);
        p.add(start);

        p.add(new JLabel(""));

        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(new JLabel(""));

        level_select = new JButton("Level Select");
        level_select.setIcon(new ImageIcon("Resources/level.png"));
        level_select.setOpaque(false);
        level_select.setRolloverEnabled(false);
        p.add(level_select);

        p.add(new JLabel(""));

        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(new JLabel(""));

        exit = new JButton("Exit");
        exit.setIcon(new ImageIcon("Resources/exit.png"));
        exit.setOpaque(false);
        exit.setRolloverEnabled(false);
        p.add(exit);

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

    private void add_listeners(){
        start.addActionListener(e -> {
            System.out.println("Start");
            menu_layout.show(main_menu, "game menu");
            game_menu.timer_begin();
        });

        level_select.addActionListener(e -> {
            System.out.println("Level");
            menu_layout.show(main_menu, "level menu");
        });

        exit.addActionListener(e -> System.exit(0));
    }

}
