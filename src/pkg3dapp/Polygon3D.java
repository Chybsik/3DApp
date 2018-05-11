package pkg3dapp;

import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author Timur
 */
public class Polygon3D {

    double[] x,y,z;
    Color c;

    public Polygon3D(double[] x, double[] y, double[] z, Color c) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.c = c;

    }
    
    public Polygon2D CreatePolygonObject(double[] viewFrom, double[] viewTo){
        double[] x = new double[this.x.length];
        double[] y = new double[this.x.length];
        
        for (int i = 0; i < this.x.length; i++) {
            x[i] =500+50* Util.CalculatePositionX(viewFrom, viewTo, this.x[i], this.y[i], this.z[i]);
            y[i] =500+50* Util.CalculatePositionY(viewFrom, viewTo, this.x[i], this.y[i], this.z[i]);
        }
        return new Polygon2D(x,y,c);
    }
}
