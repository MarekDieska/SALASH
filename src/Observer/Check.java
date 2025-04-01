package Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Check extends JFrame {

    private String bgColor = "#3493C8", textColor = "#131B3A";

    public Check() {
        setTitle("Check");
        setUndecorated(true); // Remove window borders

        Ellipse2D circle = new Ellipse2D.Double(0, 0, 300, 300);

        // Set the shape of the window
        setShape(circle);

        // Create a JPanel to hold the components
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the pizza effect (triangular slices)
                drawPizzaSlices(g2d, getWidth(), getHeight());
            }

            private void drawPizzaSlices(Graphics2D g2d, int width, int height) {
                int slices = 10; // Number of slices
                double angleStep = 2 * Math.PI / slices;
                int radius = Math.min(width, height)+50 / 2;

                for (int i = 0; i < slices; i++) {
                    double angle = i * angleStep;
                    Path2D slice = new Path2D.Double();
                    slice.moveTo(width / 2, height / 2);
                    slice.lineTo(width / 2 + radius * Math.cos(angle), height / 2 + radius * Math.sin(angle));
                    slice.lineTo(width / 2 + radius * Math.cos(angle + angleStep), height / 2 + radius * Math.sin(angle + angleStep));
                    slice.closePath();
                    g2d.setColor(i % 2 == 0 ? Color.decode("#3454D1") : Color.decode("#34D1BF")); // Alternate colors
                    g2d.fill(slice);
                }
            }
        };
        panel.setLayout(null); // Use absolute positioning
        panel.setBackground(Color.decode(bgColor));

        // Create and add the label
        JLabel label = new JLabel("Kúpené", SwingConstants.CENTER);
        label.setForeground(Color.decode(textColor));
        Font labelFont = new Font("Bell MT", Font.BOLD, 20);
        label.setFont(labelFont);
        label.setBounds(50, 20, 200, 30);
        panel.add(label);

        // Load the image and create a JLabel to display it
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("kniha_pix.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(80, 70, 150, 150);
        panel.add(imageLabel);

        JButton closeButton = new JButton("X");
        closeButton.setBounds(125, 240, 50, 20);
        closeButton.setBackground(Color.decode("#3454D1"));
        closeButton.setForeground(Color.decode("#FFF8F0"));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(closeButton);

        add(panel);
        setSize(300, 300);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Check window = new Check();
            window.setVisible(true);
        });
    }
}
