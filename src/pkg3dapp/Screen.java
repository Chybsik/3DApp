package pkg3dapp;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Timur
 */
public class Screen extends JPanel {

    double[] viewFrom;
    double[] viewTo;

//    int numberOfPolygons = 0;
    Polygon3D[] polygons;

    public Screen() {//double[] pos, double[] direction, Polygon3D[] polygons) {
//        double[] pos = new double[]{10,10,10};
//        double[] rot = new double[]{37.5,0,-135};
//        double[] size = new double[]{1,1,1};
//        CoordinateSystem camera1 = new CoordinateSystem(pos,rot,size);

//        double m[][]=new double[4][4];
//        m[0]=new double[]{0.718762, 0.615033, -0.324214, 0};
//        m[1]=new double[]{-0.393732, 0.744416, 0.539277, 0};
//        m[2]=new double[]{0.573024, -0.259959, 0.777216, 0};
//        m[3]=new double[]{0.526967, 1.254234, -2.53215, 1};
//        
//        camera.m=m;
        viewFrom = new double[]{10, 10, 10};
        viewTo = new double[]{0, 0, 0};

        double[] x = new double[]{0, 2, 2, 0, 0};
        double[] y = new double[]{0, 0, 0, 0, 0};
        double[] z = new double[]{0, 0, 2, 2, 0};
        Polygon3D poly1 = new Polygon3D(x, y, z, Color.BLACK);

        x = new double[]{0, 0, 2, 2, 0};
        y = new double[]{0, 0, 2, 2, 0};
        z = new double[]{0, 2, 2, 0, 0};
        Polygon3D poly2 = new Polygon3D(x, y, z, Color.BLACK);

        x = new double[]{2, 2, 0, 0, 2};
        y = new double[]{2, 2, 2, 2, 2};
        z = new double[]{0, 2, 2, 0, 0};
        Polygon3D poly3 = new Polygon3D(x, y, z, Color.BLACK);

        polygons = new Polygon3D[]{poly1, poly2, poly3};

//        this.pos=pos;
//        this.direction = direction;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (Polygon3D polygon : polygons) {
            polygon.to2DPolygon(viewFrom, viewTo).drawPolygon(g);
        }
    }
}
