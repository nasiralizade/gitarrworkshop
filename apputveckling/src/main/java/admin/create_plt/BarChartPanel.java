package admin.create_plt;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class BarChartPanel extends JPanel {

    // Constants for the size and margin of the chart
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    private static final int MARGIN = 50;

    // Variables for the data of the chart
    private double[] values;
    private String[] labels;
    private String title;

    // Variables for the scale and gap of the chart
    private double maxValue;
    private double scale;
    private int gap;

    // Constructor that takes the values, labels, and title as parameters
    public BarChartPanel(double[] values, String[] labels, String title) {
        this.values = values;
        this.labels = labels;
        this.title = title;

        // Find the maximum value in the array of values
        maxValue = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
            }
        }

        // Calculate the scale and the gap between the bars
        scale = (HEIGHT - 2 * MARGIN) / maxValue;
        gap = (WIDTH - 2 * MARGIN) / values.length;
    }

    // Override the paintComponent() method to draw the chart
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the background color and the font
        setBackground(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 11));

        // Draw the horizontal and vertical axes
        g.setColor(Color.BLACK);
        g.drawLine(MARGIN, HEIGHT - MARGIN, WIDTH - MARGIN, HEIGHT - MARGIN);
        g.drawLine(MARGIN, MARGIN, MARGIN, HEIGHT - MARGIN);

        // Draw the labels for the axes
        g.drawString("Months", WIDTH / 2 - 20, HEIGHT - 10);
        g.drawString("", 10, HEIGHT / 2 + 10);

        // Draw the title for the chart
        g.drawString(title, WIDTH / 2 - 50, 20);

        // Draw the bars and the values for the chart
        for (int i = 0; i < values.length; i++) {
            // Calculate the coordinates and the size of the bar
            int x = MARGIN + i * gap + gap / 4;
            int y = HEIGHT - MARGIN - (int) (values[i] * scale);
            int width = gap / 2;
            int height = (int) (values[i] * scale);

            // Set the color and fill the bar
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);

            // Set the color and draw the value
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(values[i]), x + width / 2 - 10, y - 10);

            // Set the color and draw the label
            g.setColor(Color.BLACK);
            g.drawString(labels[i], x + width / 2 - 10, HEIGHT - MARGIN + 20);
        }
    }

    // A method that saves the graph to an image file
    public void saveGraph(String filename) {
        // Create a BufferedImage object that contains the graph
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        paintComponent(g);
        // change the path to your own path
        File file = new File("/Users/nasiralizade/IdeaProjects/gitarrworkshop/apputveckling/src/main/webapp/resources/img"+File.separator+filename);

        // Write the BufferedImage object to the File object using the ImageIO.write() method
        try {
            ImageIO.write(image, "png", file);
            System.out.println("The graph is saved as " + filename);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
