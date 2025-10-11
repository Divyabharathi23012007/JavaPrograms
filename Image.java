import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Image extends JFrame implements ActionListener {
    JLabel imageLabel;
    BufferedImage originalImage;
    BufferedImage processedImage;

    JButton uploadBtn, bwBtn, saveBtn;

    public Image() {
        setTitle("Image Form");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel: image display
        imageLabel = new JLabel("No image uploaded", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        imageLabel.setPreferredSize(new Dimension(500, 500));
        add(imageLabel, BorderLayout.CENTER);

        // Right panel: buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(200, 500));

        uploadBtn = new JButton("Upload");
        bwBtn = new JButton("Convert to B&W");
        saveBtn = new JButton("Save");

        uploadBtn.addActionListener(this);
        bwBtn.addActionListener(this);
        saveBtn.addActionListener(this);

        buttonPanel.add(uploadBtn);
        buttonPanel.add(bwBtn);
        buttonPanel.add(saveBtn);

        add(buttonPanel, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadBtn) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    originalImage = ImageIO.read(file);
                    processedImage = originalImage;
                    ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(
                            imageLabel.getWidth(), imageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH));
                    imageLabel.setIcon(icon);
                    imageLabel.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Failed to load image.");
                }
            }
        } else if (e.getSource() == bwBtn) {
            if (originalImage == null) {
                JOptionPane.showMessageDialog(this, "No image uploaded.");
                return;
            }

            if (isGrayscale(originalImage)) {
                JOptionPane.showMessageDialog(this, "Image is already grayscale.");
                return;
            }

            processedImage = convertToGrayscale(originalImage);
            ImageIcon icon = new ImageIcon(processedImage.getScaledInstance(
                    imageLabel.getWidth(), imageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH));
            imageLabel.setIcon(icon);
        } else if (e.getSource() == saveBtn) {
            if (processedImage == null) {
                JOptionPane.showMessageDialog(this, "No image to save.");
                return;
            }

            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    ImageIO.write(processedImage, "png", file);
                    JOptionPane.showMessageDialog(this, "Image saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Failed to save image.");
                }
            }
        }
    }

    private boolean isGrayscale(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                if (r != g || g != b) return false;
            }
        }
        return true;
    }

    private BufferedImage convertToGrayscale(BufferedImage img) {
        BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = gray.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return gray;
    }

    public static void main(String[] args) {
        new Image();
    }
}