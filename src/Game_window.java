import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game_window extends JPanel {
    private JButton menu_button;
    private JButton hint_button;
    private JButton confirm_button;
    private JLabel level_label;
    private JLabel time;
    private JLabel time2;
    private int time_spent;
    public Timer timer;
    public Drawing_field drawing_field;
    private Atoms atoms;
    public Check_levels check_levels;
    private JPanel hint_menu;
    private JLabel hint_label;
    private JLabel answer_label;
    private JPanel intermission_menu;
    private int check_completion = 0;

    public Game_window(CardLayout menu_layout, JPanel main_menu) {
        setLayout(null);
        add_buttons();
        Add_listeners(menu_layout, main_menu);
        add_drawing_field();
        add_hint_menu(menu_layout, main_menu);
        add_intermission_menu(menu_layout, main_menu);
        add_end_menu(menu_layout, main_menu);

        time_spent = 0;
    }

    private void add_buttons(){
        menu_button = new JButton("menu");
        menu_button.setBounds(622, 886, 322, 100);
        menu_button.setIcon(new ImageIcon("Resources/Menu.png"));
        menu_button.setOpaque(false);
        menu_button.setRolloverEnabled(false);

        hint_button = new JButton("hint");
        hint_button.setBounds(300, 886, 322, 100);
        hint_button.setIcon(new ImageIcon("Resources/Hint.png"));
        hint_button.setOpaque(false);
        hint_button.setRolloverEnabled(false);

        confirm_button = new JButton("confirm");
        confirm_button.setBounds(944, 886, 322, 100);
        confirm_button.setIcon(new ImageIcon("Resources/Confirm.png"));
        confirm_button.setOpaque(false);
        confirm_button.setRolloverEnabled(false);

        //Będzie można dodać obwódkę tekstu i by nie było na białym tle
        level_label = new JLabel("Stwórz H2O");
        level_label.setBounds(720, 0, 320, 80);
        level_label.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        //Będzie można dodać obwódkę tekstu i by nie było na białym tle
        time = new JLabel("time: 0s");
        time.setBounds(100,886,300,100);
        time.setFont(new Font("Times New Roman", Font.PLAIN, 30));


        add(confirm_button);
        add(menu_button);
        add(time);
        add(hint_button);
        add(level_label);
    }

    private void Add_listeners(CardLayout menu_layout, JPanel main_menu) {
        menu_button.addActionListener(e -> {
            timer_end();
            menu_layout.show(main_menu, "start menu");
            drawing_field.circles.clear();
            drawing_field.connections.clear();
            drawing_field.just_repaint();
            check_levels.current_level = 1;
            level_updater();
            if (answer_label != null)remove(answer_label);
        });
        confirm_button.addActionListener(e -> {
            if(!check_levels.check_level(check_levels.current_level)) {
                if(check_levels.current_level == 6 && check_completion == 0){
                    level_updater();
                    drawing_field.circles.clear();
                    drawing_field.connections.clear();
                    drawing_field.just_repaint();
                    menu_layout.show(main_menu, "intermission menu");
                }else if(check_levels.current_level == 9){
                    drawing_field.circles.clear();
                    drawing_field.connections.clear();
                    drawing_field.just_repaint();
                    menu_layout.show(main_menu, "end menu");
                } else {
                    level_updater();
                    drawing_field.circles.clear();
                    drawing_field.connections.clear();
                    drawing_field.just_repaint();
                    show_answer_label(1);
                }
            }else{
                show_answer_label(2);
            }
            menu_layout.show(main_menu, "end menu");
            System.out.println(check_levels.level_unlocked);
            System.out.println(check_levels.current_level);
        });
        hint_button.addActionListener(e -> {
            edit_hint_menu();
            menu_layout.show(main_menu, "hint menu");
        });
    }

    public void timer_begin(){
        time_spent = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                time_spent++;
                time.setText("time: " + time_spent + "s");
                time2.setText("czas w którym udało ci się ukończyć: " + time_spent + "s" + " / 300s");
            }
        }, 1000,1000);
    }

    private void timer_end(){
        time_spent = 0;
        timer.cancel();
        time.setText("time: " + time_spent + "s");
        time2.setText("time: " + time_spent + "s");
    }

    private void add_drawing_field(){
        atoms = new Atoms();
        atoms.setBounds(-4, 0, 1024, 80);

        drawing_field = new Drawing_field(atoms);
        drawing_field.setBounds(-4, 80, 1365, 806);

        check_levels = new Check_levels(drawing_field,8);

        add(atoms);
        add(drawing_field);
    }

    private void add_hint_menu(CardLayout menu_layout, JPanel main_menu) {
        hint_menu = new JPanel();
        hint_menu.setLayout(null);

        hint_label = new JLabel();
        hint_label.setBounds(0, 0, 1280, 1024);

        JButton back_button = new JButton("Back");
        back_button.setBounds(944, 886, 322, 100);
        back_button.setIcon(new ImageIcon("Resources/back.png"));
        back_button.setOpaque(false);
        back_button.setRolloverEnabled(false);
        back_button.addActionListener(e -> menu_layout.show(main_menu, "game menu"));
        hint_menu.add(back_button);

        hint_menu.setComponentZOrder(back_button, 0);
        hint_menu.setComponentZOrder(hint_label, 1);

        hint_menu.add(hint_label);
        main_menu.add(hint_menu, "hint menu");
    }

    private void add_intermission_menu(CardLayout menu_layout, JPanel main_menu) {
        intermission_menu = new JPanel();
        intermission_menu.setLayout(null);

        JButton menu_button = new JButton("menu");
        menu_button.setBounds(450, 400, 322, 100);
        menu_button.setIcon(new ImageIcon("Resources/Menu.png"));
        menu_button.setOpaque(false);
        menu_button.setRolloverEnabled(false);
        menu_button.addActionListener(e -> {
            timer_end();
            menu_layout.show(main_menu, "start menu");
            drawing_field.circles.clear();
            drawing_field.connections.clear();
            drawing_field.just_repaint();
            check_levels.current_level = 1;
            check_levels.level_unlocked = 5;
            level_updater();
        });
        intermission_menu.add(menu_button);

        JButton continue_button = new JButton("continue");
        continue_button.setBounds(450, 600, 322, 100);
        continue_button.setIcon(new ImageIcon("Resources/continue.png"));
        continue_button.setOpaque(false);
        continue_button.setRolloverEnabled(false);
        continue_button.addActionListener(e -> {
            if (time_spent < 300) {
                menu_layout.show(main_menu, "game menu");
                check_completion = 1;
            }
        });

        intermission_menu.add(continue_button);
        JLabel level_text = new JLabel("Gratulację ukończyłeś wszystkie poziomy z chemii nieorganicznej");
        level_text.setFont(new Font("Times New Roman", Font.BOLD, 40));
        level_text.setBounds(50, 0, 1280, 100);
        intermission_menu.add(level_text);

        time2 = new JLabel();
        time2.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        time2.setBounds(250, 200, 1280, 100);
        intermission_menu.add(time2);

        JLabel lelel_text2 = new JLabel("Jeżeli udało ci się ukończyć w podanym czasie to odblokowałeś bonusowe poziomy");
        lelel_text2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
        lelel_text2.setBounds(20, 100, 1280, 100);
        intermission_menu.add(lelel_text2);

        main_menu.add(intermission_menu, "intermission menu");
    }

    private void add_end_menu(CardLayout menu_layout, JPanel main_menu) {
        JPanel end_menu = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("Resources/start_bg.png");
                g.drawImage(background.getImage(), 0, 0, 1280, 1024, this);
            }
        };

        end_menu.setLayout(null);

        JButton menu_button = new JButton("menu");
        menu_button.setBounds(450, 400, 322, 100);
        menu_button.setIcon(new ImageIcon("Resources/Menu.png"));
        menu_button.setOpaque(false);
        menu_button.setRolloverEnabled(false);
        menu_button.addActionListener(e -> {
            timer_end();
            menu_layout.show(main_menu, "start menu");
            drawing_field.circles.clear();
            drawing_field.connections.clear();
            drawing_field.just_repaint();
            check_levels.current_level = 1;
            level_updater();
        });
        end_menu.add(menu_button);

        JLabel level_text = new JLabel("Gratulację!!! ukończyłeś wszystkie poziomy możesz wrócić do menu");
        level_text.setFont(new Font("Times New Roman", Font.BOLD, 40));
        level_text.setBounds(50, 0, 1280, 100);
        level_text.setForeground(Color.BLACK);
        end_menu.add(level_text);

        main_menu.add(end_menu, "end menu");
    }

    private void show_answer_label(int answer){
        answer_label = new JLabel();
        if (answer == 1){
            answer_label.setText("Poprawna odpowiedź");
            answer_label.setForeground(Color.GREEN);
        }else{
            answer_label.setText("Niepoprawna odpowiedź");
            answer_label.setForeground(Color.RED);
        }
        answer_label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        answer_label.setBounds(450, 400, 500, 100);
        add(answer_label, 0);
        setComponentZOrder(answer_label, 0);
        repaint();
        revalidate();
        timer.schedule(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    remove(answer_label);
                    repaint();
                    revalidate();
                });
            }
        }, 2000);
    }

    private void edit_hint_menu(){
        switch (check_levels.current_level){
            case 1:
                hint_label.setIcon(new ImageIcon("Resources/hint1.png"));
                break;
            case 2:
                hint_label.setIcon(new ImageIcon("Resources/hint2.png"));
                break;
            case 3:
                hint_label.setIcon(new ImageIcon("Resources/hint3.png"));
                break;
            case 4:
                hint_label.setIcon(new ImageIcon("Resources/hint4.png"));
                break;
            case 5:
                hint_label.setIcon(new ImageIcon("Resources/hint5.png"));
                break;
            case 6:
                hint_label.setIcon(new ImageIcon("Resources/hint6.png"));
                break;
            case 7:
                hint_label.setIcon(new ImageIcon("Resources/hint7.png"));
                break;
            case 8:
                hint_label.setIcon(new ImageIcon("Resources/hint8.png"));
                break;

        }
    }

    public void level_updater(){
        int level_number = check_levels.current_level;
        switch (level_number){
            case 1:
                level_label.setText("Stwórz: H20");
                break;
            case 2:
                level_label.setText("Stwórz: CH4");
                break;
            case 3:
                level_label.setText("Stwórz: CO2");
                break;
            case 4:
                level_label.setText("Stwórz: amoniak");
                break;
            case 5:
                level_label.setText("Stwórz: kwas siarkowy VI");
                break;
            case 6:
                level_label.setText("Stwórz: C2H6O");
                break;
            case 7:
                level_label.setText("Stwórz: CH3COOH");
                break;
            case 8:
                level_label.setText("Stwórz: benzen");
        }
    }
}

