import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class RozmisteniVozidel extends JPanel {

    private Graphics2D g;
    private int currentX, currentY, oldX, oldY;
    private int[] xPoly;
    private int[] yPoly;
    int n = xPoly.length;


    public void drawPolygon() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // coordinates X, Y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX=e.getX();
                currentY=e.getY();

                if (g != null) {
                    g.drawPolygon(xPoly, yPoly, n);
                }
            }
        });

    }
    public void drawCircle() { }
    public void drawRectangle() { }
}