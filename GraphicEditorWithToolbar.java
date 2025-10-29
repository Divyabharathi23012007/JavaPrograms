import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphicEditorWithToolbar extends JFrame {

    private BufferedImage canvasImage;
    private Graphics2D g2d;
    private int prevX, prevY;
    private boolean drawing = false;
    private Color currentColor = Color.BLACK;
    private int brushSize = 3;

    public GraphicEditorWithToolbar() {
        setTitle("Simple Graphic Editor with JToolBar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton pencilBtn = new JButton("✏️ Pencil");
        JButton eraserBtn = new JButton("🩹 Eraser");
        JButton colorBtn = new JButton("🎨 Color");
        JButton clearBtn = new JButton("🧹 Clear");
        JButton saveBtn = new JButton("💾 Save");

        toolBar.add(pencilBtn);
        toolBar.add(eraserBtn);
        toolBar.add(colorBtn);
        toolBar.add(clearBtn);
        toolBar.add(saveBtn);

        add(toolBar, BorderLayout.NORTH);

        // Create a drawing panel
        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (canvasImage == null) {
                    canvasImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                    g2d = canvasImage.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setColor(currentColor);
                }
                g.drawImage(canvasImage, 0, 0, null);
            }
        };

        // Mouse listeners for drawing
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
                drawing = true;
            }

            public void mouseReleased(MouseEvent e) {
                drawing = false;
            }
        });

        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (drawing && g2d != null) {
                    int x = e.getX();
                    int y = e.getY();
                    g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawLine(prevX, prevY, x, y);
                    prevX = x;
                    prevY = y;
                    drawPanel.repaint();
                }
            }
        });

        add(drawPanel, BorderLayout.CENTER);

        // Button actions
        pencilBtn.addActionListener(e -> {
            currentColor = Color.BLACK;
            brushSize = 3;
            g2d.setColor(currentColor);
        });

        eraserBtn.addActionListener(e -> {
            currentColor = Color.WHITE;
            brushSize = 10;
            g2d.setColor(currentColor);
        });

        colorBtn.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Choose a Color", currentColor);
            if (newColor != null) {
                currentColor = newColor;
                g2d.setColor(currentColor);
            }
        });

        clearBtn.addActionListener(e -> {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
            g2d.setColor(currentColor);
            drawPanel.repaint();
        });

        saveBtn.addActionListener(e -> {
            try {
                File output = new File("drawing.png");
                ImageIO.write(canvasImage, "png", output);
                JOptionPane.showMessageDialog(this, "Image saved as drawing.png");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraphicEditorWithToolbar().setVisible(true);
        });
    }
}
