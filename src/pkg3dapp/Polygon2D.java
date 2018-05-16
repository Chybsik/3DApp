package pkg3dapp;

import java.awt.*;

/**
 *
 * @author Timur
 */
public class Polygon2D {
    Polygon p;
//    Color c;
    double dist;
    double[] p1,p2;
    Color c1,c2;
    
    public Polygon2D(double[] x, double[] y, double dist,double[] p1,double[] p2,Color c1, Color c2){
        p = new Polygon();
//        c = color;
        this.dist=dist;
        this.p1 = p1;
        this.p2 = p2;
        
        this.c1=c1;
        this.c2=c2;
//        p.npoints=x.length;
        
        for (int i = 0; i < x.length; i++) {
            p.addPoint((int)x[i], (int)y[i]);
//            p.xpoints[i]=(int)x[i];
//            p.ypoints[i]=(int)y[i];
        }
    }
    
    void drawPolygon(Graphics2D g){
        GradientPaint grad = new GradientPaint((float)p1[0],(float)p1[1],c1,(float)p2[0], (float)p2[1],c2);
        g.setPaint(grad);
//        g.setColor(c);
        g.fillPolygon(p);
//        g.setColor(Color.BLACK);
//        g.drawPolygon(p);
    }
}
