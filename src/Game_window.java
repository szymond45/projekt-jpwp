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
    private int time_spent;
    private Timer timer;
    private Drawing_field drawing_field;
    private Atoms atoms;
    private Check_levels check_levels;

    public Game_window(CardLayout menu_layout, JPanel main_menu) {
        setLayout(null);
        add_buttons();
        Add_listeners(menu_layout, main_menu);
        add_drawing_field();

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
        });
        confirm_button.addActionListener(e -> {
            if(!check_levels.check_level(check_levels.level_unlocked)) {
                level_updater();
                drawing_field.circles.clear();
                drawing_field.connections.clear();
                drawing_field.just_repaint();
            }
            System.out.println(check_levels.level_unlocked);
        });
    }

    public void timer_begin(){
        time_spent = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                time_spent++;
                time.setText("time: " + time_spent + "s");
            }
        }, 1000,1000);
    }

    private void timer_end(){
        time_spent = 0;
        timer.cancel();
        time.setText("time: " + time_spent + "s");
    }

    private void add_drawing_field(){
        atoms = new Atoms();
        atoms.setBounds(-4, 0, 1024, 80);

        drawing_field = new Drawing_field(atoms);
        drawing_field.setBounds(-4, 80, 1365, 806);

        check_levels = new Check_levels(drawing_field,1);

        add(atoms);
        add(drawing_field);
    }

    private void level_updater(){
        int level_number = check_levels.level_unlocked;
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
        }
    }
}

