import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game_window extends JPanel {
    private JButton menu_button;
    private JButton hint_button;
    private JCheckBox Coal;
    private int selected_element = 0;
    private JLabel time;
    private int time_spent;
    private Timer timer;

    public Game_window(CardLayout menu_layout, JPanel main_menu) {
        setLayout(null);
        buttons();
        Add_listeners(menu_layout, main_menu);
        time_spent = 0;
    }

    private void buttons(){
        menu_button = new JButton("menu");
        menu_button.setBounds(665, 886, 600, 100);
        hint_button = new JButton("hint");
        hint_button.setBounds(300, 886, 365, 100);
        Coal = new JCheckBox("Coal");
        Coal.setBounds(-4, 0, 80, 80);
        //ikonki do zmiany
        Coal.setIcon(new ImageIcon("start.png"));
        Coal.setSelectedIcon(new ImageIcon("exit.png"));
        //Będzie można dodać obwódkę tekstu
        time = new JLabel("time: 0s");
        time.setBounds(100,886,300,100);
        time.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        add(menu_button);
        add(time);
        add(hint_button);
        add(Coal);
    }

    private void Add_listeners(CardLayout menu_layout, JPanel main_menu) {
        menu_button.addActionListener(e -> {
            timer_end();
            menu_layout.show(main_menu, "start menu");
        });
        Coal.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(Coal.isSelected()){
                    System.out.println("Coal");
                }else{
                    System.out.println("Coal2");
                }
            }
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
}

