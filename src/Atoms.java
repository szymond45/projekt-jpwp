import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Atoms extends JPanel {
    private Image selectedImage;
    private JCheckBox Coal;
    private JCheckBox Hydrogen;
    private JCheckBox Connections;

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

        Connections = new JCheckBox("Connections");
        Connections.setBounds(160, 0, 84, 80);
        Connections.setIcon(new ImageIcon("Resources/Connection.png"));
        Connections.setSelectedIcon(new ImageIcon("Resources/Connection_selected.png"));
        Connections.setOpaque(false);
        Connections.setRolloverEnabled(false);
        add(Connections);
    }

    private void add_listeners(){
        Coal.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            selectedImage = ((ImageIcon) Coal.getIcon()).getImage();
            Hydrogen.setSelected(false);
            Connections.setSelected(false);
            }
        });

        Hydrogen.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedImage = ((ImageIcon) Hydrogen.getIcon()).getImage();
                Coal.setSelected(false);
                Connections.setSelected(false);
            }
        });

        Connections.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Coal.setSelected(false);
                Hydrogen.setSelected(false);
            }
        });
    }


    public Image get_image() {
        return selectedImage;
    }

    public boolean connection_mode() {
        return Connections.isSelected();
    }
}
