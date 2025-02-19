import javax.swing.*;
import java.awt.*;

public class GridExample extends JPanel {

    // Size of the outer grid
    private static final int GRID_SIZE = 3;
    private static final int CELL_SIZE = 50; // Size of each outer grid cell
    private static final int SUBGRID_SIZE = 3; // Size of the inner subgrid (each 3x3)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast Graphics to Graphics2D for better control
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for grid lines
        g2d.setColor(Color.BLACK);

        // Draw the 3x3 outer grid
        for (int i = 0; i <= GRID_SIZE; i++) {
            // Vertical lines for the outer grid
            g2d.drawLine(i * CELL_SIZE * SUBGRID_SIZE, 0, i * CELL_SIZE * SUBGRID_SIZE, GRID_SIZE * CELL_SIZE * SUBGRID_SIZE);
            // Horizontal lines for the outer grid
            g2d.drawLine(0, i * CELL_SIZE * SUBGRID_SIZE, GRID_SIZE * CELL_SIZE * SUBGRID_SIZE, i * CELL_SIZE * SUBGRID_SIZE);
        }

        // Draw the inner 3x3 grids within each cell
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int xOffset = i * CELL_SIZE * SUBGRID_SIZE;
                int yOffset = j * CELL_SIZE * SUBGRID_SIZE;

                // Draw the subgrid inside each outer grid cell
                for (int x = 0; x <= SUBGRID_SIZE; x++) {
                    // Vertical lines for the subgrid
                    g2d.drawLine(xOffset + x * CELL_SIZE, yOffset, xOffset + x * CELL_SIZE, yOffset + CELL_SIZE * SUBGRID_SIZE);
                }
                for (int y = 0; y <= SUBGRID_SIZE; y++) {
                    // Horizontal lines for the subgrid
                    g2d.drawLine(xOffset, yOffset + y * CELL_SIZE, xOffset + CELL_SIZE * SUBGRID_SIZE, yOffset + y * CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a frame
        JFrame frame = new JFrame("3x3 Grid of 3x3 Cells");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the GridExample panel and add it to the frame
        GridExample gridPanel = new GridExample();
        frame.add(gridPanel);

        // Set the size of the frame to fit the 3x3 outer grid of 3x3 cells
        frame.setSize(GRID_SIZE * CELL_SIZE * SUBGRID_SIZE + 15, GRID_SIZE * CELL_SIZE * SUBGRID_SIZE + 40); // Add padding for window borders

        // Make sure to set the frame visible
        frame.setVisible(true);
    }
}