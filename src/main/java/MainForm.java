import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class MainForm {
    private JPanel panel1;
    private JButton button1;
    private JLabel statusLabel;
    private DrawingPanel drawingPanel1;
    private JSlider slider1;
    private int drawWidth;
    private int drawHeight;

    public MainForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello, World!");
                Stock stock = null;
                try {
                    stock = YahooFinance.get("0200.KL");
                    stock.print();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                //if (!source.getValueIsAdjusting()) {
                int myValue = source.getValue();
                statusLabel.setText("Slider Value = " + myValue + "%");
                drawingPanel1.setPct(myValue);
                drawingPanel1.repaint();
                //}
            }
        });
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                setWidthHeight();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setTitle("MyChartingApp (c) Megat I Hashim 2021");
        frame.pack();
        frame.setVisible(true);
    }

    private void setWidthHeight() {
        drawWidth = panel1.getWidth() - 1;
        drawHeight = panel1.getHeight() - 1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        drawingPanel1 = new DrawingPanel();
        slider1 = new JSlider();
    }
}
