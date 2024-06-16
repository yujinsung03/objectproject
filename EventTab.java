import javax.swing.*;
import java.awt.*;

class EventTab extends JFrame {
    public EventTab(String description) {
        setTitle("Event Description");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea(description);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN, 16));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        setVisible(true);
    }
}
