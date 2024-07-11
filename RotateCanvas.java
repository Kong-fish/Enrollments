import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class RotateCanvas {

    private ComposeArea composeArea;
    private DrawingArea drawingArea;
    private double rotationAngle = 0.0;

    public RotateCanvas(ComposeArea composeArea, DrawingArea drawingArea) {
        this.composeArea = composeArea;
        this.drawingArea = drawingArea;
    }

    public void rotateLeftButton() {
        rotationAngle += Math.toRadians(90); // Rotate right (clockwise)
        rotateImage(composeArea);
    }

    public void rotateRightButton(){
        rotationAngle += Math.toRadians(90); // Rotate right (clockwise)
        rotateImage(drawingArea);
    }

    public void composeButton() {
        // Transfer image from ComposeArea to DrawingArea
        Image image = composeArea.getCurrentImage();
        if (image != null) {
            Graphics2D g2d = (Graphics2D) drawingArea.getGraphics();
            g2d.drawImage(image, 0, 0, null);
        }
        
    }

    private void rotateImage(JPanel component) {
        // Retrieve the current image from the component
        Image image = component.createImage(component.getWidth(), component.getHeight());
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        // Apply rotation transformation
        AffineTransform tx = new AffineTransform();
        tx.rotate(rotationAngle, image.getWidth(null) / 2, image.getHeight(null) / 2); // Rotate around the center
        g2d.setTransform(tx);
        // Draw the component onto the image with the applied transformation
        component.paint(g2d);
        // Dispose the graphics context to release resources
        g2d.dispose();
        // Update the component with the rotated image
        component.getGraphics().drawImage(image, 0, 0, null);
    }
}
    
