import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame {
    Window(String windowTitle) {
        super(windowTitle);
        setResizable(false);
        setSize(1280,1024);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Init_start_menu();
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

        add(p);
    }
}
class Start_listener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Start");
    }
}

class Level_listener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Level");
    }
}

class exit_listener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
