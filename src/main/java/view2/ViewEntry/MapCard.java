package view2.ViewEntry;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
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