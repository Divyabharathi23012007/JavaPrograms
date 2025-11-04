import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotepadApp extends JFrame implements ActionListener {

    JTextArea textArea;
    JFileChooser fileChooser;

    public NotepadApp() {
        setTitle("Simple Notepad");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exit);
        

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // Menu Bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
 
        switch (command) {
            case "New":
                textArea.setText("");
                break;

            case "Open":
                try {
                    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        textArea.read(br, null);
                        br.close();
                        setTitle(file.getName());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file");
                }
                break;

            case "Save":
                try {
                    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                        textArea.write(bw);
                        bw.close();
                        setTitle(file.getName());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file");
                }
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
        }
    }

    public static void main(String[] args) {
        new NotepadApp();
    }
}
