import java.util.ArrayList;
import java.util.List;

public class Check_levels {
    private Drawing_field drawing_field;
    public int level_unlocked;
    Check_atom_names check_atom_names = new Check_atom_names();
    Check_atom_connections check_atom_connections = new Check_atom_connections();

    public Check_levels(Drawing_field drawing_field, int level_unlocked) {
        this.drawing_field = drawing_field;
        this.level_unlocked = level_unlocked;
    }

    public boolean check_level(int level) {
        boolean error = false;
        List<Integer> atom_count = new ArrayList<>();
        List<String> atom_names = new ArrayList<>();
        List<String> connection_names = new ArrayList<>();
        List<Integer> connection_count = new ArrayList<>();
        for (Drawing_field.Circle circle : drawing_field.circles) {
            if (atom_names.contains(circle.name)){
                atom_count.set(atom_names.indexOf(circle.name), atom_count.get(atom_names.indexOf(circle.name)) + 1);
            }else{
                atom_names.add(circle.name);
                atom_count.add(1);
            }
        }
        for (Drawing_field.Connection connection : drawing_field.connections) {
            if(connection_names.contains(connection.circle_1.name + connection.circle_2.name) || connection_names.contains(connection.circle_2.name + connection.circle_1.name)){
                if(connection_names.contains(connection.circle_1.name + connection.circle_2.name)) {
                    connection_count.set(connection_names.indexOf(connection.circle_1.name + connection.circle_2.name), connection_count.get(connection_names.indexOf(connection.circle_1.name + connection.circle_2.name)) + 1);
                }else{
                    connection_count.set(connection_names.indexOf(connection.circle_2.name + connection.circle_1.name), connection_count.get(connection_names.indexOf(connection.circle_2.name + connection.circle_1.name)) + 1);
                }
            }else{
                connection_names.add(connection.circle_1.name + connection.circle_2.name);
                connection_count.add(1);
            }
            
        }
        error = check_atoms(atom_count, atom_names, level, connection_names, connection_count);
        return error;
    }

    private boolean check_atoms(List<Integer> atom_count, List<String> atom_names, int level, List<String> connection_names, List<Integer> connection_count) {
        boolean error = false;
        int connection_sum1 = 0;
        int connection_sum2 = 0;
        int connection_sum3 = 0;
        if (level == 1) {
            error = check_atom_names.check_atom_names(atom_names, atom_count, "Hydrogen", 2, "Oxygen", 1);
            if(!error) error = check_atom_connections.check_atom_connections(connection_names, connection_count, "Hydrogen", "Oxygen", 2);
            if (error) {
                System.out.println("error");
            }else if (level_unlocked == 1){
                level_unlocked++;
            }
            System.out.println(atom_count);
            System.out.println(atom_names);
            System.out.println(connection_names);
            System.out.println(connection_count);
        }
        if (level == 2) {
            error = check_atom_names.check_atom_names(atom_names, atom_count, "Coal", 1, "Hydrogen", 4);
            if(!error) error = check_atom_connections.check_atom_connections(connection_names, connection_count, "Hydrogen", "Coal", 4);
            if (error) {
                System.out.println("error");
            }else if (level_unlocked == 2){
                level_unlocked++;
            }
            System.out.println(atom_count);
            System.out.println(atom_names);
            System.out.println(connection_names);
            System.out.println(connection_count);
        }
        if (level == 3) {
            error = check_atom_names.check_atom_names(atom_names, atom_count, "Coal", 1, "Oxygen", 2);
            if(!error) error = check_atom_connections.check_atom_connections(connection_names, connection_count, "Coal", "Oxygen", 4);
            if (error) {
                System.out.println("error");
            }else if (level_unlocked == 3){
                level_unlocked++;
            }
            System.out.println(atom_count);
            System.out.println(atom_names);
            System.out.println(connection_names);
            System.out.println(connection_count);
        }
        if (level == 4) {
            error = check_atom_names.check_atom_names(atom_names, atom_count, "Nitrogen", 1, "Hydrogen", 3);
            if(!error) error = check_atom_connections.check_atom_connections(connection_names, connection_count, "Nitrogen", "Hydrogen", 3);
            if (error) {
                System.out.println("error");
            }else if (level_unlocked == 4){
                level_unlocked++;
            }
            System.out.println(atom_count);
            System.out.println(atom_names);
            System.out.println(connection_names);
            System.out.println(connection_count);
        }
        if (level == 5) {
            error = check_atom_names.check_atom_names(atom_names, atom_count, "Oxygen", 4, "Hydrogen", 2, "Sulphur", 1);
            if(!error) error = check_atom_connections.check_atom_connections(connection_names, connection_count, "Oxygen", "Sulphur", 6, "Hydrogen", "Oxygen", 2);
            if (error) {
                System.out.println("error");
            }else if (level_unlocked == 5){
                level_unlocked++;
            }
            System.out.println(atom_count);
            System.out.println(atom_names);
            System.out.println(connection_names);
            System.out.println(connection_count);
        }
        return error;
    }

    // below: checking atoms on screen and connections between them, a lot of method overrides
    private class Check_atom_names{
        private boolean check_atom_names(List<String> atom_names, List<Integer> atom_count, String a1, int n1, String a2, int n2){
            boolean error = false;
            for (String atom_name : atom_names) {
                if (!atom_name.equals(a1) && !atom_name.equals(a2)) {
                    error = true;
                    break;
                }
                if (atom_name.equals(a1)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n1) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals(a2)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n2) {
                        error = true;
                        break;
                    }
                }
            }
            return error;
        }

        private boolean check_atom_names(List<String> atom_names, List<Integer> atom_count, String a1, int n1, String a2, int n2, String a3, int n3){
            boolean error = false;
            for (String atom_name : atom_names) {
                if (!atom_name.equals(a1) && !atom_name.equals(a2) && !atom_name.equals(a3)) {
                    error = true;
                    break;
                }
                if (atom_name.equals(a1)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n1) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals(a2)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n2) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals(a3)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n3) {
                        error = true;
                        break;
                    }
                }
            }
            return error;
        }

        private boolean check_atom_names(List<String> atom_names, List<Integer> atom_count, final String a1, int n1, String a2, int n2, String a3, int n3, String a4, int n4){
            boolean error = false;
            for (String atom_name : atom_names) {
                if (!atom_name.equals(a1) && !atom_name.equals(a2) && !atom_name.equals(a3) && !atom_name.equals(a4)) {
                    error = true;
                    break;
                }
                if (atom_name.equals(a1)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n1) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals(a2)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n2) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals(a3)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n3) {
                        error = true;
                        break;
                    }
                }
                if (atom_name.equals(a4)) {
                    if (atom_count.get(atom_names.indexOf(atom_name)) != n4) {
                        error = true;
                        break;
                    }
                }
            }
            return error;
        }
    }

    private class Check_atom_connections{
        private boolean check_atom_connections(List<String> connection_names, List<Integer> connection_count, String a1, String a2, int n1){
            boolean error = false;
            int connection_sum = 0;
            for (String connection_name : connection_names) {
                if (connection_name.equals(a1+a2) || connection_name.equals(a2+a1)) {
                    connection_sum =  connection_sum + connection_count.get(connection_names.indexOf(connection_name));
                }else {
                    error = true;
                    break;
                }
            }
            if ( connection_sum != n1) {
                error = true;
            }
            return error;
        }

        private boolean check_atom_connections(List<String> connection_names, List<Integer> connection_count, String a1, String a2, int n1, String a3, String a4, int n2){
            boolean error = false;
            int connection_sum = 0;
            int connection_sum2 = 0;
            int connection_sum3 = 0;
            for (String connection_name : connection_names) {
                if (connection_name.equals(a1+a2) || connection_name.equals(a2+a1)) {
                    connection_sum =  connection_sum + connection_count.get(connection_names.indexOf(connection_name));
                }else if(connection_name.equals(a3+a4) || connection_name.equals(a4+a3)) {
                    connection_sum2 =  connection_sum2 + connection_count.get(connection_names.indexOf(connection_name));
                }else{
                    error = true;
                    break;
                }
            }
            if ( connection_sum != n1) {
                error = true;
            }else if (connection_sum2 != n2){
                error = true;
            }
            return error;
        }

        private boolean check_atom_connections(List<String> connection_names, List<Integer> connection_count, String a1, String a2, int n1, String a3, String a4, int n2, String a5, String a6, int n3){
            boolean error = false;
            int connection_sum = 0;
            int connection_sum2 = 0;
            int connection_sum3 = 0;
            for (String connection_name : connection_names) {
                if (connection_name.equals(a1+a2) || connection_name.equals(a2+a1)) {
                    connection_sum =  connection_sum + connection_count.get(connection_names.indexOf(connection_name));
                }else if(connection_name.equals(a3+a4) || connection_name.equals(a4+a3)) {
                    connection_sum2 =  connection_sum2 + connection_count.get(connection_names.indexOf(connection_name));
                }else if(connection_name.equals(a5+a6) || connection_name.equals(a6+a5)){
                    connection_sum3 =  connection_sum3 + connection_count.get(connection_names.indexOf(connection_name));
                }else{
                        error = true;
                        break;
                    }
            }
            if ( connection_sum != n1) {
                error = true;
            }else if (connection_sum2 != n2){
                error = true;
            }else if (connection_sum3 != n3){
                error = true;
            }
            return error;
        }
    }

}
