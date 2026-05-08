package diary.ui;

import diary.dao.DiaryDAO;
import diary.dto.DiaryEntry;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class DiaryFrame extends JFrame {
    private JTextField titleField = new JTextField(20);
    private JTextArea contentArea = new JTextArea(5, 20);
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> entryList = new JList<>(listModel);
    private DiaryDAO dao = new DiaryDAO();

    public DiaryFrame() {
        setTitle("My Pretty Diary");
        setSize(700, 500); // Increased frame size
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(250, 0)); // Fixed width for input area

        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        contentArea.setLineWrap(true);
        
        leftPanel.add(new JLabel("Title:"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(titleField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(new JLabel("Content:"));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(new JScrollPane(contentArea));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton saveBtn = new JButton("Save Diary");
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveBtn.setBackground(new Color(135, 206, 235));
        saveBtn.addActionListener(e -> {
            try {
                dao.save(new DiaryEntry(0, titleField.getText(), contentArea.getText(), LocalDate.now()));
                loadEntries();
                titleField.setText("");
                contentArea.setText("");
                JOptionPane.showMessageDialog(this, "Saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        leftPanel.add(saveBtn);

        entryList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane listScroll = new JScrollPane(entryList);
        listScroll.setBorder(BorderFactory.createTitledBorder("Saved Entries"));

        add(leftPanel, BorderLayout.WEST);
        add(listScroll, BorderLayout.CENTER);

        loadEntries();
        setVisible(true);
    }

    private void loadEntries() {
        listModel.clear();
        try {
            List<DiaryEntry> entries = dao.findAll();
            for (DiaryEntry entry : entries) {
                listModel.addElement(entry.getId() +" - " + entry.getDate() + " - " + entry.getTitle()+ " - " + entry.getContent());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DiaryFrame::new);
    }
}
