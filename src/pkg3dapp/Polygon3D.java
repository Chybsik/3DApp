package pkg3dapp;

import java.awt.*;

/**
 *
 * @author Timur
 */
public class Polygon3D {

    double[] x, y, z;
    Color c;
    
    Vector n;

    public Polygon3D(double[] x, double[] y, double[] z, Color c) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.c = c;
        
        n = new Vector(x[1]-x[0],y[1]-y[0],z[1]-z[0]).CrossProduct(new Vector(x[2]-x[1],y[2]-y[1],z[2]-z[1]));

    }

    public Polygon2D to2DPolygon(double[][] camera,Vector lightSource) {
        double[] x = new double[this.x.length];
        double[] y = new double[this.x.length];
        double Dist = 0;

        for (int i = 0; i < this.x.length; i++) {
            double[] temp = Util.CalculatePosition(camera, new double[]{this.x[i], this.y[i], this.z[i]});
            x[i] = temp[0];
            y[i] = temp[1];
            Dist+=temp[2];
        }
        
        int color = (int)(255*n.DotProduct(lightSource)*-1);
        color = color<0?0:color;
        Color currentColor = new Color(color,color,color);
        
        return new Polygon2D(x, y, currentColor,Dist/x.length);
    }

    void updatePolygon() {

    }
}
