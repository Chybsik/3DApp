package pkg3dapp;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Timur
 */
public class Screen extends JPanel {

    double[] pos ;
    double[] direction;

    int numberOfPolygons = 0;
//    Polygon2D[] drawablePolygons;
    Polygon3D[] polygons;

    public Screen(double[] pos, double[] direction, Polygon3D[] polygons) {
        this.pos=pos;
        this.direction = direction;
        
//        Polygon3D dPoly1 = new Polygon3D(new double[]{0, 0, 0}, new double[]{0, 5, 0}, new double[]{0, 0, 5}, Color.BLACK);
//        polygons = new Polygon3D[]{dPoly1};

    }

    @Override
    public void paintComponent(Graphics g) {
        for (Polygon3D polygon : polygons) {
            polygon.CreatePolygonObject(pos, direction).drawPolygon(g);
        }
    }
}
