import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Drawing_field extends JPanel {
    public List<Circle> circles;
    public List<Connection> connections;
    private Circle selected_Circle;
    private Circle first_circle;
    private int offsetX, offsetY;
    private Atoms atoms;

    public Drawing_field(Atoms atoms) {
        this.atoms = atoms;
        circles = new ArrayList<>();
        connections = new ArrayList<>();
        setBackground(Color.WHITE);

        add_listeners();
    }

    private void add_listeners() {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selected_Circle != null) {
                    selected_Circle.x = e.getX() - offsetX;
                    selected_Circle.y = e.getY() - offsetY;
                    repaint();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                //move a circle
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

            // Create a circle
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
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
                                } else {
                                    connections.add(new Connection(first_circle, circle));
                                    first_circle = null;
                                    repaint();
                                    break;
                                }
                            }
                        }
                    }
                }

                //remove circle
                if (e.getButton() == MouseEvent.BUTTON3){
                    if (!atoms.connection_mode()){
                        for (Circle circle : circles){
                            if (circle.contains(e.getX(), e.getY())){
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


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        Image paper = new ImageIcon("Resources/paper.png").getImage();
        graphics2D.drawImage(paper, 0, 0, getWidth(), getHeight(), null);

        // draw circle connection
        graphics2D.setColor(Color.BLACK);
        for (Connection connection : connections) {
            graphics2D.drawLine(
                    connection.circle_1.x,
                    connection.circle_1.y,
                    connection.circle_2.x,
                    connection.circle_2.y
            );
        }

        // draw circles
        for (Circle circle : circles) {
            graphics2D.setColor(Color.BLACK);
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


    public static class Circle {
        int x, y, radius;
        Image image;
        String name;

        public Circle(int x, int y, int radius, Image image, String name) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.image = image;
            this.name = name;
        }

        public boolean contains(int mouse_x, int mouse_y) {
            int x_distance = mouse_x - x;
            int y_distance = mouse_y - y;
            return x_distance * x_distance + y_distance * y_distance <= radius * radius;
        }
    }

    public static class Connection {
        Circle circle_1, circle_2;

        public Connection(Circle circle_1, Circle circle_2) {
            this.circle_1 = circle_1;
            this.circle_2 = circle_2;
        }
    }
}
