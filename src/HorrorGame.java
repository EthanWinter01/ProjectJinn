import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HorrorGame{
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Click Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Create a panel as a placeholder for game visuals
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK); // Change to any color
        panel.setBounds(0, 0, 800, 600);

        // Add mouse listener to detect clicks
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("Clicked at: " + x + ", " + y);
                checkInteraction(x, y);
            }
        });

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Dummy method to handle interactions
    public static void checkInteraction(int x, int y) {
        System.out.println("Checking interaction at: " + x + ", " + y);
    }
}
