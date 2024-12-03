import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Atoms extends JPanel {
    private Image selected_image;
    private String selected_name;
    private JCheckBox Coal;
    private JCheckBox Hydrogen;
    private JCheckBox Connections;
    private JCheckBox Nitrogen;
    private JCheckBox Oxygen;
    private JCheckBox Sulphur;

    public Atoms() {
        setLayout(null);
        add_checkboxes();
        add_listeners();
    }

    private void add_checkboxes(){

        // Must make better quality images in the future
        Coal = new JCheckBox("Coal");
        Coal.setBounds(0, 0, 84, 80);
        Coal.setIcon(new ImageIcon("Resources/Coal.png"));
        Coal.setSelectedIcon(new ImageIcon("Resources/Coal_selected.png"));
        Coal.setOpaque(false);
        Coal.setRolloverEnabled(false);
        add(Coal);

        Hydrogen = new JCheckBox("Hydrogen");
        Hydrogen.setBounds(80, 0, 84, 80);
        Hydrogen.setIcon(new ImageIcon("Resources/Hydrogen.png"));
        Hydrogen.setSelectedIcon(new ImageIcon("Resources/Hydrogen_selected.png"));
        Hydrogen.setOpaque(false);
        Hydrogen.setRolloverEnabled(false);
        add(Hydrogen);

        Oxygen = new JCheckBox("Oxygen");
        Oxygen.setBounds(160, 0, 84, 80);
        Oxygen.setIcon(new ImageIcon("Resources/Oxygen.png"));
        Oxygen.setSelectedIcon(new ImageIcon("Resources/Oxygen_selected.png"));
        Oxygen.setOpaque(false);
        Oxygen.setRolloverEnabled(false);
        add(Oxygen);

        Nitrogen = new JCheckBox("Nitrogen");
        Nitrogen.setBounds(240, 0, 84, 80);
        Nitrogen.setIcon(new ImageIcon("Resources/Nitrogen.png"));
        Nitrogen.setSelectedIcon(new ImageIcon("Resources/Nitrogen_selected.png"));
        Nitrogen.setOpaque(false);
        Nitrogen.setRolloverEnabled(false);
        add(Nitrogen);

        Sulphur = new JCheckBox("Sulphur");
        Sulphur.setBounds(320, 0, 84, 80);
        Sulphur.setIcon(new ImageIcon("Resources/Sulphur.png"));
        Sulphur.setSelectedIcon(new ImageIcon("Resources/Sulphur_selected.png"));
        Sulphur.setOpaque(false);
        Sulphur.setRolloverEnabled(false);
        add(Sulphur);

        Connections = new JCheckBox("Connections");
        Connections.setBounds(400, 0, 84, 80);
        Connections.setIcon(new ImageIcon("Resources/Connection.png"));
        Connections.setSelectedIcon(new ImageIcon("Resources/Connection_selected.png"));
        Connections.setOpaque(false);
        Connections.setRolloverEnabled(false);
        add(Connections);
    }

    private void add_listeners(){
        Coal.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            selected_image = ((ImageIcon) Coal.getIcon()).getImage();
            selected_name = "Coal";
            Hydrogen.setSelected(false);
            Connections.setSelected(false);
            Oxygen.setSelected(false);
            Sulphur.setSelected(false);
            Nitrogen.setSelected(false);
            }
        });

        Hydrogen.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selected_image = ((ImageIcon) Hydrogen.getIcon()).getImage();
                selected_name = "Hydrogen";
                Coal.setSelected(false);
                Connections.setSelected(false);
                Oxygen.setSelected(false);
                Sulphur.setSelected(false);
                Nitrogen.setSelected(false);
            }
        });

        Connections.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Coal.setSelected(false);
                Hydrogen.setSelected(false);
                Oxygen.setSelected(false);
                Sulphur.setSelected(false);
                Nitrogen.setSelected(false);
            }
        });

        Oxygen.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selected_image = ((ImageIcon) Oxygen.getIcon()).getImage();
                selected_name = "Oxygen";
                Coal.setSelected(false);
                Hydrogen.setSelected(false);
                Connections.setSelected(false);
                Sulphur.setSelected(false);
                Nitrogen.setSelected(false);
            }
        });

        Nitrogen.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selected_image = ((ImageIcon) Nitrogen.getIcon()).getImage();
                selected_name = "Nitrogen";
                Coal.setSelected(false);
                Hydrogen.setSelected(false);
                Connections.setSelected(false);
                Oxygen.setSelected(false);
                Sulphur.setSelected(false);
            }
        });

        Sulphur.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selected_image = ((ImageIcon) Sulphur.getIcon()).getImage();
                selected_name = "Sulphur";
                Coal.setSelected(false);
                Hydrogen.setSelected(false);
                Connections.setSelected(false);
                Oxygen.setSelected(false);
                Nitrogen.setSelected(false);
            }
        });
    }


    public Image get_image() {
        return selected_image;
    }

    public boolean connection_mode() {
        return Connections.isSelected();
    }

    public String get_name() {
        return selected_name;
    }
}
