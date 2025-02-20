import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel {
    BufferedImage volvoImage;
    BufferedImage scaniaImage;
    BufferedImage saabImage;
    BufferedImage volvoWorkshopImage;
    private final List<BufferedImage> carImages = new ArrayList<>();
    private final List<Point> carPositions = new ArrayList<>();
    private final List<VehicleFramework> vehicles;

    public DrawPanel(int x, int y, List<VehicleFramework> vehicles) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.vehicles = vehicles;

        // Load images and initialize positions
        /*for (VehicleFramework vehicle : vehicles) {*/
            
            try {
                volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
                scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
                saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
                volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
                carImages.add(volvoImage);
                carImages.add(scaniaImage);
                carImages.add(saabImage);
                carImages.add(volvoWorkshopImage);
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            carPositions.add(new Point(0, 0));
            carPositions.add(new Point(0, 0));
            carPositions.add(new Point(0, 0));
            carPositions.add(new Point(0, 0)); 
    }

    public void moveit(int index, int x, int y) {
        if (index >= 0 && index < carPositions.size()) {
            carPositions.set(index, new Point(x, y));
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoWorkshopImage,0,460,null);
        for (int i = 0; i < vehicles.size(); i++) {
            BufferedImage img = carImages.get(i);
            Point pos = carPositions.get(i);
            if (img != null) {
                g.drawImage(img, pos.x, pos.y, null);
            }
        }
    }
}
