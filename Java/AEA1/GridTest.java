import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class GridExample extends JPanel {

    // Set the size of the grid
    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 50; // Size of each cell

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast Graphics to Graphics2D for more control over the drawing
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for grid lines
        g2d.setColor(Color.BLACK);

        // Draw the grid lines (rows and columns)
        for (int i = 0; i <= GRID_SIZE; i++) {
            // Draw vertical lines
            g2d.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE);

            // Draw horizontal lines
            g2d.drawLine(0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE, i * CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        // Create a frame
        JFrame frame = new JFrame("9x9 Grid Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the GridExample panel and add it to the frame
        GridExample gridPanel = new GridExample();
        frame.add(gridPanel);

        // Set the frame size and make it visible
        frame.setSize(GRID_SIZE * CELL_SIZE + 15, GRID_SIZE * CELL_SIZE + 40); // +15 and +40 for padding
        frame.setVisible(true);
    }
}
