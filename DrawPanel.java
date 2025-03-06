import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel implements VehicleObserver{
    Model model;
    private final CarController controller;
    private final Map<String, BufferedImage> vehicleImages = new HashMap<>();

    private BufferedImage workshopImage;


    public DrawPanel(int x, int y, CarController controller) {
        this.controller = controller;
        this.model = new Model();
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        loadImages();
        initializeCarPositions();
    }

    private void loadImages() {
        try {
            workshopImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));

            for (VehicleFramework vehicle : controller.model.getVehicles()) {
                String imagePath = getImagePathForVehicle(vehicle);
                BufferedImage image = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(imagePath)));
                vehicleImages.put(vehicle.getClass().getSimpleName(), image);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    private void initializeCarPositions() {
        for (int i = 0; i < controller.model.getVehicles().size(); i++) {
            model.getCarPosition().add(new Point(0, 0)); // Default starting position
        }
    }

    private String getImagePathForVehicle(VehicleFramework vehicle) {
        return "pics/" + vehicle.getClass().getSimpleName() + ".jpg";
    }

    public void moveit(int index, int x, int y) {
        if (index >= 0 && index < model.getCarPosition().size()) {
            model.getCarPosition().set(index, new Point(x, y));
            repaint();
        }
    }
    public void removeLastCarPosition(){
        if (!model.getCarPosition().isEmpty()){
            model.getCarPosition().remove(model.getCarPosition().size() - 1);
            repaint();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(workshopImage, 0, 464, null);

        for (int i = 0; i < controller.model.getVehicles().size(); i++) {
            try {
                VehicleFramework vehicle = controller.model.getVehicles().get(i);
                BufferedImage img = vehicleImages.get(vehicle.getClass().getSimpleName());
                Point pos = model.getCarPosition().get(i);
                if (img != null) {
                    g.drawImage(img, pos.x, pos.y, null);
                }else{
                    g.drawImage(null,0,0,null);
                }
            }
            finally{
                    VehicleFramework vehicle = controller.model.getVehicles().get(i);
                    BufferedImage img = vehicleImages.get(vehicle.getClass().getSimpleName());
                    model.getCarPosition().add(new Point(0, 0));
                    Point pos = model.getCarPosition().get(i);
                    if (img != null) {
                        g.drawImage(img, pos.x, pos.y, null);
                    }else{
                        g.drawImage(null,0,0,null);
                    }
                }

        }
    }
    @Override
    public void update(ArrayList<VehicleFramework> vehicles) {
        repaint();
    }
}
