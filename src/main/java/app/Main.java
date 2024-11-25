package app;

import java.awt.CardLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        final JFrame application = new JFrame("My Entries");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(500, 650);

        CardLayout cardLayout = new CardLayout();

        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        application.setVisible(true);

    }
}