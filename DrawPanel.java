import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel {
    private final Map<Class<? extends VehicleFramework>, BufferedImage> carImages = new HashMap<>();
    private final Map<VehicleFramework, Point> carPositions = new HashMap<>();

    // Constructor
    public DrawPanel(int x, int y, List<VehicleFramework> vehicles) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        // Load images
        try {
            carImages.put(Volvo240.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.put(Saab95.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.put(Scania.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize positions
        for (VehicleFramework vehicle : vehicles) {
            carPositions.put(vehicle, new Point(0, 0)); // Default start position
        }
    }

    // Move a specific car
    void moveit(VehicleFramework vehicle, int x, int y) {
        carPositions.put(vehicle, new Point(x, y));
    }

    // Paint all cars
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Map.Entry<VehicleFramework, Point> entry : carPositions.entrySet()) {
            VehicleFramework vehicle = entry.getKey();
            Point pos = entry.getValue();
            BufferedImage img = carImages.get(vehicle.getClass());

            if (img != null) {
                g.drawImage(img, pos.x, pos.y, null);
            }
        }
    }
}
