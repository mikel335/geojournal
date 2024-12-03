package view.Components;

import javax.swing.*;
import java.awt.*;

public class StyledButton extends JButton {
    public StyledButton(String text) {
        setText(text);
        setFont(new Font("sans serif", Font.BOLD, 20));
        setBackground(Colors.darkBlue);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setForeground(new Color(255,255,255));
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
