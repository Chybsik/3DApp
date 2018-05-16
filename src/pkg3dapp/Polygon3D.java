package pkg3dapp;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Timur
 */
public class Polygon3D {

    ArrayList<Vertex> p;
//    double[] x, y, z;
    Color c;
    
    Vector n;

    public Polygon3D(ArrayList<Vertex> p){//double[] x, double[] y, double[] z, Color c) {
//        this.x = x;
//        this.y = y;
//        this.z = z;
        this.p = p;

        this.c = c;
        
        n = new Vector(p.get(1).x-p.get(0).x,p.get(1).y-p.get(0).y,p.get(1).z-p.get(0).z).CrossProduct(new Vector(p.get(2).x-p.get(1).x,p.get(2).y-p.get(1).y,p.get(2).z-p.get(1).z));

    }

    public Polygon2D to2DPolygon(double[][] camera,Vector lightSource) {
        double[] x = new double[this.p.size()];
        double[] y = new double[this.p.size()];
        double dist = 0;

        for (int i = 0; i < this.p.size(); i++) {
            double[] temp = Util.CalculatePosition(camera, p.get(i));
            
            x[i] = temp[0];
            y[i] = temp[1];
            dist+=temp[2];
        }
        
        for (Vertex v : p) {
            v.k=v.n.DotProduct(lightSource)*-1;
            v.k=v.k<0?0:v.k;
        }
        
        Vertex p1 = p.get(0);
        Vertex p2 = p.get(0);
        for (int i = 0; i < p.size(); i++) {
            if (p1.k<p.get(i).k) {
                p1=p.get(i);
            }
            if (p2.k>p.get(i).k) {
                p2=p.get(i);
            }
        }
        
        double[] p1Projection = Util.CalculatePosition(camera, p1);
        double[] p2Projection = Util.CalculatePosition(camera, p2);
        Color c1 = new Color((int)(255*p1.k),(int)(255*p1.k),(int)(255*p1.k));
        Color c2 = new Color((int)(255*p2.k),(int)(255*p2.k),(int)(255*p2.k));
        
//        int color = (int)(255*n.DotProduct(lightSource)*-1);
//        color = color<0?0:color;
//        Color currentColor = new Color(color,color,color);
        
        return new Polygon2D(x, y, dist/this.p.size(),p1Projection,p2Projection,c1,c2);
    }
}
