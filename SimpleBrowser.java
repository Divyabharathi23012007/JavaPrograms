 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.net.URLEncoder;

public class SimpleBrowser extends JFrame {
    private JTextField addressBar;
    private JEditorPane displayPane;
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private JMenu historyMenu;
    private java.util.List<String> visitLog = new ArrayList<>();

    public SimpleBrowser() {
        super("Simple Browser");

        // Address bar
        addressBar = new JTextField();
        addressBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addressBar.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        addressBar.addActionListener(e -> loadPage(addressBar.getText()));

        // Display pane
        displayPane = new JEditorPane();
        displayPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayPane);

        // Buttons
        JButton backButton = createButton("⏪ Back");
        JButton forwardButton = createButton("⏩ Forward");
        JButton refreshButton = createButton("🔄 Refresh");

        backButton.addActionListener(e -> goBack());
        forwardButton.addActionListener(e -> goForward());
        refreshButton.addActionListener(e -> loadPage(addressBar.getText()));

        // Top panel with gradient
        JPanel topPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0x00c6ff), getWidth(), 0, new Color(0x0072ff));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topPanel.setPreferredSize(new Dimension(800, 60));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(refreshButton);

        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(addressBar, BorderLayout.CENTER);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        historyMenu = new JMenu("History");

        JMenuItem exportItem = new JMenuItem("Export History to File");
        exportItem.addActionListener(e -> exportHistoryToFile());

        JMenuItem shareItem = new JMenuItem("Share History via WhatsApp");
        shareItem.addActionListener(e -> shareHistoryOnWhatsApp());

        historyMenu.addSeparator();
        historyMenu.add(exportItem);
        historyMenu.add(shareItem);

        menuBar.add(historyMenu);
        setJMenuBar(menuBar);

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 250, 255));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(0, 122, 204));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 122, 204), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 35));
        return button;
    }

    private void loadPage(String url) {
        try {
            if (!url.startsWith("http")) {
                url = "https://" + url;
            }
            if (!addressBar.getText().equals(url)) {
                backStack.push(addressBar.getText());
                forwardStack.clear();
            }
            addressBar.setText(url);
            displayPane.setPage(url);
            logVisit(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading page: " + e.getMessage());
        }
    }

    private void logVisit(String url) {
        String timestamp = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss").format(new Date());
        String entry = url + " — " + timestamp;
        visitLog.add(entry);
        JMenuItem item = new JMenuItem(entry);
        item.addActionListener(e -> loadPage(url));
        historyMenu.add(item);
    }

    private void exportHistoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("browser_history.txt"))) {
            for (String entry : visitLog) {
                writer.write(entry);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "History exported to browser_history.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
        }
    }

    private void shareHistoryOnWhatsApp() {
        try {
            StringBuilder message = new StringBuilder("Visited Websites:\n");
            for (String entry : visitLog) {
                message.append(entry).append("\n");
            }
            String encodedMessage = URLEncoder.encode(message.toString(), "UTF-8");
            String whatsappURL = "https://wa.me/?text=" + encodedMessage;
            Desktop.getDesktop().browse(new java.net.URI(whatsappURL));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open WhatsApp: " + e.getMessage());
        }
    }

    private void goBack() {
        if (!backStack.isEmpty()) {
            forwardStack.push(addressBar.getText());
            String url = backStack.pop();
            loadPage(url);
        }
    }

    private void goForward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(addressBar.getText());
            String url = forwardStack.pop();
            loadPage(url);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleBrowser::new);
    }
}