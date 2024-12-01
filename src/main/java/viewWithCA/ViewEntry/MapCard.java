package viewWithCA.ViewEntry;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;


// TODO add the pin
public class MapCard extends JPanel {

    private JXMapKit mapViewer;

    public MapCard() {
        setLayout(new BorderLayout());
        mapViewer = new JXMapKit();
        mapViewer.setCenterPosition(new GeoPosition(0,0));
        mapViewer.setPreferredSize(new Dimension(800, 600));
        add(mapViewer, BorderLayout.CENTER);
    }

    public void updateCoords(double latitude, double longitude) {
        mapViewer.setCenterPosition(new GeoPosition(latitude, longitude));
    }

}