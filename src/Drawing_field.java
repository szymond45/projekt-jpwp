import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * klasa odpowiedzialna za stworzenia pola do rysowania atomów, rysowanie atomów i połączeń
 */
public class Drawing_field extends JPanel {
    /**
     * lista przechowująca obiekty atomów
     */
    public List<Circle> circles;
    /**
     * lista przechowująca obiekty połączeń
     */
    public List<Connection> connections;
    private Circle selected_Circle;
    private Circle first_circle;
    private int offsetX, offsetY;
    private final Atoms atoms;

    public Drawing_field(Atoms atoms) {
        this.atoms = atoms;
        circles = new ArrayList<>();
        connections = new ArrayList<>();
        setBackground(Color.WHITE);

        add_listeners();
    }

    /**
     * metoda odpowiedzialna za dodanie listenerów
     */
    private void add_listeners() {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            /**
             * metoda pozwalająca na przenoszenie atomów, aktualizuje współrzędne, i rysuje je ponownie
             * @param e the event to be processed
             */
            public void mouseDragged(MouseEvent e) {
                if (selected_Circle != null) {
                    selected_Circle.x = e.getX() - offsetX;
                    selected_Circle.y = e.getY() - offsetY;
                    repaint();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {

            /**
             * metoda odpowiedzialna za sprawdznie czy myszka jest obecnie nad jakimś atomem i zmiane parametrów
             * @param e the event to be processed
             */
            public void mousePressed(MouseEvent e) {
                if(!atoms.connection_mode()) {
                    for (Circle circle : circles) {
                        if (circle.contains(e.getX(), e.getY())) {
                            selected_Circle = circle;
                            offsetX = e.getX() - circle.x;
                            offsetY = e.getY() - circle.y;
                            break;
                        }
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                selected_Circle = null;
            }

            /**
             * metoda odpowiedzialna za stworznie atomów lub połączeń między nimi
             * @param e the event to be processed
             */
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //create a circle
                    if (!atoms.connection_mode()) {
                        if (atoms.get_image() != null) {
                            circles.add(new Circle(e.getX(), e.getY(), 50, atoms.get_image(), atoms.get_name()));
                            repaint();
                        }
                    }

                    //Connect 2 cirles
                    if (atoms.connection_mode()) {
                        for (Circle circle : circles) {
                            if (circle.contains(e.getX(), e.getY())) {
                                if (first_circle == null) {
                                    first_circle = circle;
                                } else if (first_circle != circle) {
                                    if (check_connections(circle) == 0){
                                        connections.add(new Connection(first_circle, circle, 0));
                                    }else if(check_connections(circle) == 1){
                                        connections.add(new Connection(first_circle, circle, 10));
                                    }else if(check_connections(circle) == 2){
                                        connections.add(new Connection(first_circle, circle, -10));
                                    }
                                    first_circle = null;
                                    repaint();
                                    break;
                                }else first_circle = null;
                            }
                        }
                    }
                }

                /**
                 * metoda odpowiedzialna za usuwanie atomów i połączeń między nimi
                 */
                if (e.getButton() == MouseEvent.BUTTON3){
                    if (!atoms.connection_mode()){
                        //remove circle
                        for (Circle circle : circles){
                            if (circle.contains(e.getX(), e.getY())){
                                connections.removeIf(connection -> connection.circle_1 == circle || connection.circle_2 == circle);
                                circles.remove(circle);
                                selected_Circle = null;
                                repaint();
                                break;
                            }
                        }
                    }

                    //remove a connection
                    if (atoms.connection_mode()) {
                        for (Circle circle : circles) {
                            if (circle.contains(e.getX(), e.getY())) {
                                if (first_circle == null) {
                                    first_circle = circle;
                                } else {
                                    for (Connection connection : connections) {
                                        if(connection.circle_1 == circle && connection.circle_2 == first_circle){
                                            connections.remove(connection);
                                            break;
                                        }
                                        if(connection.circle_2 == circle && connection.circle_1 == first_circle){
                                            connections.remove(connection);
                                            break;
                                        }
                                    }
                                    first_circle = null;
                                    repaint();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * metoda odpowiedzialna za odświeżenie pola do rysowania, jest użyta w innych klasa gdzie jest konieczność odświeżenia pola do rysowania
     */
    public void just_repaint(){
        repaint();
    }

    /**
     * metoda odpowiedzialna za sprawdznie maksymalnej liczby połaczeń między dwoma atomami
     * @return liczba połączeń
     */
    private int check_connections(Circle circle){
        int counter = 0;
        for (Connection connection : connections) {
            if(connection.circle_1 == circle && connection.circle_2 == first_circle){
                counter++;
            }
            if(connection.circle_2 == circle && connection.circle_1 == first_circle){
                counter++;
            }
        }
        return counter;
    }

    /**
     * metoda odpowiedzialna za rysowanie atomów, połączeń między nimi
     * @param g the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Image paper = new ImageIcon("Resources/paper.png").getImage();
        graphics2D.drawImage(paper, 0, 0, getWidth(), getHeight(), null);

        // draw circle connection
        graphics2D.setColor(Color.BLACK);
        for (Connection connection : connections) {
            graphics2D.drawLine(
                    connection.circle_1.x + connection.offset,
                    connection.circle_1.y + connection.offset,
                    connection.circle_2.x + connection.offset,
                    connection.circle_2.y + connection.offset
            );
        }

        // draw circles
        for (Circle circle : circles) {
            graphics2D.setColor(new Color(0,0,0,0));
            graphics2D.drawOval(
                    circle.x - circle.radius,
                    circle.y - circle.radius,
                    circle.radius * 2,
                    circle.radius * 2
            );

            // put image in circle
            if (circle.image != null) {
                graphics2D.setClip(new java.awt.geom.Ellipse2D.Float(
                        circle.x - circle.radius,
                        circle.y - circle.radius,
                        circle.radius * 2,
                        circle.radius * 2
                ));
                graphics2D.drawImage(circle.image,
                        circle.x - circle.radius,
                        circle.y - circle.radius,
                        circle.radius * 2,
                        circle.radius * 2,
                        null);
                graphics2D.setClip(null);
            }
        }
    }

    /**
     * klasa, która jest odpowiedzialna za tworzenie obiektów atomów, które są następnie rysowane
     */
    public static class Circle {
        int x, y, radius;
        Image image;
        String name;

        /**
         * konstruktor tworzący koło
         * @param x współrzędna x myszki
         * @param y współrzędna y myszki
         * @param radius średnica atomu
         * @param image grafika atomu
         * @param name nazwa atomu
         */
        public Circle(int x, int y, int radius, Image image, String name) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.image = image;
            this.name = name;
        }

        /**
         * metoda sprawdzająca czy myszka znajduje się obecnie nad jakimś atomem
         * @param mouse_x współrzędna x myszki
         * @param mouse_y współrzedna y myszki
         * @return true jeżeli myszka znajduje się nad atomem
         */
        public boolean contains(int mouse_x, int mouse_y) {
            int x_distance = mouse_x - x;
            int y_distance = mouse_y - y;
            return x_distance * x_distance + y_distance * y_distance <= radius * radius;
        }
    }

    /**
     * klasa, która jest odpowiedzialna za tworzenie obiektów połączeń, które są następnie rysowane
     */
    public static class Connection {
        Circle circle_1, circle_2;
        private final int offset;

        /**
         * konstruktor tworzący połączenie
         * @param circle_1 atom 1
         * @param circle_2 atom 2
         * @param offset przesuniecie względem środka
         */
        public Connection(Circle circle_1, Circle circle_2, int offset) {
            this.circle_1 = circle_1;
            this.circle_2 = circle_2;
            this.offset = offset;
        }
    }
}
