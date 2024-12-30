import java.awt.*;
import javax.swing.*;
import java.util.TimerTask;

public class Window extends JFrame {
    public JPanel main_menu;
    public CardLayout menu_layout;
    private Game_window game_menu;
    private JButton start;
    private JButton level_select;
    private JButton exit;
    private JLabel message;
    private JPanel level_menu;
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
        JPanel p = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("Resources/start_bg.png");
                g.drawImage(background.getImage(), 0, 0, 1280, 1024, this);
            }
        };
        p.setLayout(new GridLayout(7, 3));

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


    private void switch_level(int level){
        if(game_menu.check_levels.level_unlocked >= level){
            if(game_menu.time_spent != 0) game_menu.timer_end();
            game_menu.check_levels.current_level = level;
            game_menu.timer_begin();
            game_menu.level_updater();
            game_menu.drawing_field.circles.clear();
            game_menu.drawing_field.connections.clear();
            game_menu.drawing_field.just_repaint();
            menu_layout.show(main_menu, "game menu");
        }else{
            game_menu.timer_begin();
            show_answer_label();
        }
    }
    private void show_answer_label(){
        message = new JLabel();
        JLabel answer_label1 = new JLabel();
        add(answer_label1);
        message.setText("Nie odblokowałeś jeszcze tego poziomu");
        message.setForeground(Color.RED);
        message.setFont(new Font("Times New Roman", Font.BOLD, 30));
        message.setBounds(450, 600, 500, 100);
        level_menu.add(message, 0);
        level_menu.setComponentZOrder(message, 0);
        level_menu.repaint();
        level_menu.revalidate();
        game_menu.timer.schedule(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    level_menu.remove(message);
                    level_menu.repaint();
                    level_menu.revalidate();
                    game_menu.timer_end();
                });
            }
        }, 500);
    }

    private void add_listeners(){
        start.addActionListener(e -> {
            menu_layout.show(main_menu, "game menu");
            game_menu.timer_begin();
        });

        level_select.addActionListener(e -> menu_layout.show(main_menu, "level menu"));

        exit.addActionListener(e -> System.exit(0));
    }

    private void Init_level_menu(){
        level_menu = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("Resources/start_bg.png");
                g.drawImage(background.getImage(), 0, 0, 1280, 1024, this);
            }
        };

        level_menu.setLayout(null);
        JButton level_1 = new JButton("Level 1");
        level_1.setIcon(new ImageIcon("Resources/level1.png"));
        level_1.setOpaque(false);
        level_1.setRolloverEnabled(false);
        level_1.setBounds(175,200,150,150);
        level_1.addActionListener(e -> switch_level(1));
        level_menu.add(level_1);

        JButton level_2 = new JButton("Level 2");
        level_2.setIcon(new ImageIcon("Resources/level2.png"));
        level_2.setOpaque(false);
        level_2.setRolloverEnabled(false);
        level_2.setBounds(425,200,150,150);
        level_2.addActionListener(e -> switch_level(2));
        level_menu.add(level_2);

        JButton level_3 = new JButton("Level 3");
        level_3.setIcon(new ImageIcon("Resources/level3.png"));
        level_3.setOpaque(false);
        level_3.setRolloverEnabled(false);
        level_3.setBounds(675,200,150,150);
        level_3.addActionListener(e -> switch_level(3));
        level_menu.add(level_3);

        JButton level_4 = new JButton("Level 4");
        level_4.setIcon(new ImageIcon("Resources/level4.png"));
        level_4.setOpaque(false);
        level_4.setRolloverEnabled(false);
        level_4.setBounds(925,200,150,150);
        level_4.addActionListener(e -> switch_level(4));
        level_menu.add(level_4);

        JButton level_5 = new JButton("Level 5");
        level_5.setIcon(new ImageIcon("Resources/level5.png"));
        level_5.setOpaque(false);
        level_5.setRolloverEnabled(false);
        level_5.setBounds(175,400,150,150);
        level_5.addActionListener(e -> switch_level(5));
        level_menu.add(level_5);

        JButton level_6 = new JButton("Level 6");
        level_6.setIcon(new ImageIcon("Resources/level6.png"));
        level_6.setOpaque(false);
        level_6.setRolloverEnabled(false);
        level_6.setBounds(425,400,150,150);
        level_6.addActionListener(e -> switch_level(6));
        level_menu.add(level_6);

        JButton level_7 = new JButton("Level 7");
        level_7.setIcon(new ImageIcon("Resources/level7.png"));
        level_7.setOpaque(false);
        level_7.setRolloverEnabled(false);
        level_7.setBounds(675,400,150,150);
        level_7.addActionListener(e -> switch_level(7));
        level_menu.add(level_7);

        JButton level_8 = new JButton("Level 8");
        level_8.setIcon(new ImageIcon("Resources/level8.png"));
        level_8.setOpaque(false);
        level_8.setRolloverEnabled(false);
        level_8.setBounds(925,400,150,150);
        level_8.addActionListener(e -> switch_level(8));
        level_menu.add(level_8);

        JLabel level_text = new JLabel("Wybierz poziom");
        level_text.setFont(new Font("Times New Roman", Font.BOLD, 100));
        level_text.setBounds(250, 0, 800, 100);
        level_menu.add(level_text);

        JButton back_button = new JButton("Back");
        back_button.setBounds(944, 886, 322, 100);
        back_button.setIcon(new ImageIcon("Resources/back.png"));
        back_button.setOpaque(false);
        back_button.setRolloverEnabled(false);
        back_button.addActionListener(e -> {
            menu_layout.show(main_menu, "start menu");
            if (message != null){
                level_menu.remove(message);
                level_menu.repaint();
                level_menu.revalidate();

            }
            if(game_menu.time_spent != 0) game_menu.timer_end();
        });
        level_menu.add(back_button);


        main_menu.add(level_menu, "level menu");
    }

}
