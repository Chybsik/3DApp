package pkg3dapp;

import java.awt.*;

/**
 *
 * @author Timur
 */
public class Polygon2D {
    Polygon p;
    Color c;
    public Polygon2D(double[] x, double[] y, Color color){
        p = new Polygon();
        c = color;
        
        p.npoints=x.length;
        
        for (int i = 0; i < x.length; i++) {
            p.addPoint((int)x[i], (int)y[i]);
        }
    }
    
    void drawPolygon(Graphics g){
        g.setColor(c);
        g.fillPolygon(p);
    }
}
