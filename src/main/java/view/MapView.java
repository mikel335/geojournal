package view;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;


public class MapView extends JPanel {

    public MapView(double latitude, double longitude) {
        JXMapViewer mapViewer = new JXMapViewer();

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        tileFactory.setThreadPoolSize(8);

        // Set the focus
        GeoPosition frankfurt = new GeoPosition(latitude, longitude);

        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(frankfurt);

        MouseInputListener mouseInputListener = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mouseInputListener);
        mapViewer.addMouseMotionListener(mouseInputListener);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        setLayout(new BorderLayout());
        add(mapViewer, BorderLayout.CENTER);
    }

}