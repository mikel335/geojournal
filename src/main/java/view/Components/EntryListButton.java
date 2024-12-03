package view.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EntryListButton extends JButton {
    public EntryListButton(String text) {
        setText(text);
        setFont(new Font("sans serif", Font.BOLD, 20));
        setBackground(new Color(0x0C,0x2d,0x48));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setForeground(new Color(255,255,255));
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
