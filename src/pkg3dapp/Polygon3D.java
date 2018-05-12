package pkg3dapp;

import java.awt.*;

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
    
    public Polygon2D to2DPolygon(double[] viewFrom, double[] viewTo){
        double[] x = new double[this.x.length];
        double[] y = new double[this.x.length];
        
        for (int i = 0; i < this.x.length; i++) {
            double[] temp = new double[]{this.x[i], this.y[i], this.z[i],1};
            x[i] = Util.CalculatePositionX(viewFrom, viewTo, temp);
            y[i] = Util.CalculatePositionY(viewFrom, viewTo, temp);
        }
        return new Polygon2D(x,y,c);
    }
}
