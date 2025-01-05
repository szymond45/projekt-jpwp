import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Klasa która przechowuje obrazki dla atomów oraz tworzy guziki do wybierania atomów
 */
public class Atoms extends JPanel {
    private String selected_image;
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

    /**
     * metoda tworząca checkboxy do wyboru atomów albo połączenia
     */
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

    /**
     * metoda dodające listenery do guzików
     */
    private void add_listeners(){
        Coal.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            selected_image = "Resources/Coal2.png";
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
                selected_image = "Resources/Hydrogen2.png";
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
                selected_image = "Resources/Oxygen2.png";
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
                selected_image = "Resources/Nitrogen2.png";
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
                selected_image = "Resources/Sulphur2.png";
                selected_name = "Sulphur";
                Coal.setSelected(false);
                Hydrogen.setSelected(false);
                Connections.setSelected(false);
                Oxygen.setSelected(false);
                Nitrogen.setSelected(false);
            }
        });
    }


    /**
     * metoda któa zwraca obecnie wybraną grafikę atomu
     * @return jeżeli grafika jest wybrana to ją zwraca, w przeciwnym wypadku zwraca null
     */
    public Image get_image() {
        if (selected_image != null) {
            return new ImageIcon(selected_image).getImage();
        }
        return null;
    }

    /**
     * metoda która zwraca obecnie wybrany tryb: czy jest tryb połączeń czy tworzenia aotmów
     * @return jezeli tryb połączeń jesty wybrany to zwraca true
     */
    public boolean connection_mode() {
        return Connections.isSelected();
    }

    /**
     * metoda, która zwraca obecnie wybrany atom
     * @return nazwa wybranego atomu
     */
    public String get_name() {
        return selected_name;
    }
}
