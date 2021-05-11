import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class DrawingPanel extends JPanel {
    private int pct;

    public DrawingPanel() {
        pct = 100;
    }

    public void setPct(int pct) {
        this.pct = pct;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double drawRect = new Rectangle2D.Double(0, 0, (this.getWidth() - 1), (this.getHeight() - 1));
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, (int) drawRect.width, (int) drawRect.height);
        for (int i = 0; i <= 255; i++) {
            float myColor = i / 255f;
            if (myColor <= pct / 100f) {
                g2d.setColor(Color.getHSBColor(myColor, 1f, 1f));
                Arc2D.Double myArc = new Arc2D.Double(drawRect,
                        (i * (360.0 / 255.0)),
                        (360.0 / 255.0),
                        Arc2D.PIE);
                g2d.fill(myArc);
            }
        }
    }


}
