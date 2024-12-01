package view2.ViewEntry;

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

    private JXMapViewer mapViewer;
    TileFactoryInfo info;
    DefaultTileFactory tileFactory;


    public MapCard(double latitude, double longitude) {

        this.info = new OSMTileFactoryInfo();
        this.tileFactory = new DefaultTileFactory(info);

        this.mapViewer.setTileFactory(tileFactory);

        tileFactory.setThreadPoolSize(8);

        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(new GeoPosition(latitude, longitude));

        MouseInputListener mouseInputListener = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mouseInputListener);
        mapViewer.addMouseMotionListener(mouseInputListener);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        add(mapViewer, BorderLayout.CENTER);

        setLayout(new BorderLayout());
    }

    public void updateCoords(double latitude, double longitude) {
        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(new GeoPosition(latitude, longitude));
    }

}