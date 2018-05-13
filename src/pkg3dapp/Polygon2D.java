package pkg3dapp;

import java.awt.*;

/**
 *
 * @author Timur
 */
public class Polygon2D {
    Polygon p;
    Color c;
    double Dist;
    
    double[] p1;
    double[] p2;
    
    public Polygon2D(double[] x, double[] y, Color color,double Dist,double[] p1, double[] p2){
        p = new Polygon();
        c = color;
        this.Dist=Dist;
        
        p.npoints=x.length;
        
        for (int i = 0; i < x.length; i++) {
            p.xpoints[i]=(int)x[i];
            p.ypoints[i]=(int)y[i];
        }
    }
    
    void drawPolygon(Graphics g){
        g.setColor(c);
        g.fillPolygon(p);
        g.setColor(Color.BLACK);
        g.drawPolygon(p);
    }
}
