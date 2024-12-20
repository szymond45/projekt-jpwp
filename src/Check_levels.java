import java.util.ArrayList;
import java.util.List;

public class Check_levels {
    private Drawing_field drawing_field;
    private int connection_counter;

    public Check_levels(Drawing_field drawing_field) {
        this.drawing_field = drawing_field;
    }

    public boolean check_level(int level) {
        boolean error = false;
        List<Integer> atom_count = new ArrayList<>();
        List<String> atom_names = new ArrayList<>();
        for (Drawing_field.Circle circle : drawing_field.circles) {
            if (atom_names.contains(circle.name)){
                atom_count.set(atom_names.indexOf(circle.name), atom_count.get(atom_names.indexOf(circle.name)) + 1);
            }else{
                atom_names.add(circle.name);
                atom_count.add(1);
            }
        }
        for (Drawing_field.Connection connection : drawing_field.connections) {
            
        }
        error = check_atoms(atom_count, atom_names, level);
        return error;
    }

    private boolean check_atoms(List<Integer> atom_count, List<String> atom_names, int level) {
        boolean error = false;
        if (level == 1) {
            for (String atom_name : atom_names) {
                if (!atom_name.equals("Hydrogen") && !atom_name.equals("Oxygen")) {
                    error = true;
                    break;
                }
                if (atom_name.equals("Hydrogen")) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != 2) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals("Oxygen")) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != 1) {
                        error = true;
                        break;
                    }
                }
            }
        }
        return error;
    }

}
