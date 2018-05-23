package pkg3dapp;

import java.awt.*;
import java.util.ArrayList;

public class Polygon3D {

    ArrayList<Vertex> p;
    Vector n;

    public Polygon3D(ArrayList<Vertex> p) {
        this.p = p;

        n = new Vector(p.get(1).x - p.get(0).x, p.get(1).y - p.get(0).y, p.get(1).z - p.get(0).z).CrossProduct(new Vector(p.get(2).x - p.get(1).x, p.get(2).y - p.get(1).y, p.get(2).z - p.get(1).z));

    }

    /**
     * @method to2DPolygon - метод непосредственно осуществляющий проецирование полигона на плоскость отрисовки
     */
    public Polygon2D to2DPolygon(double[][] camera, Vector lightSource) {
        double[] x = new double[this.p.size()];
        double[] y = new double[this.p.size()];
        double dist = 0;

        for (int i = 0; i < this.p.size(); i++) {
            double[] temp = Util.CalculatePosition(camera, new Vertex(p.get(i)));

            x[i] = temp[0];
            y[i] = temp[1];
            dist += temp[2];
        }

        for (Vertex v : p) {
            v.k = v.n.DotProduct(lightSource) * -1;
            v.k = v.k < 0 ? 0 : v.k;
        }

        Vertex p1 = p.get(0);
        Vertex p2 = p.get(0);
        for (int i = 0; i < p.size(); i++) {
            if (p1.k < p.get(i).k) {
                p1 = p.get(i);
            }
            if (p2.k > p.get(i).k) {
                p2 = p.get(i);
            }
        }
        double[] originProjection = Util.CalculatePosition(camera, new Vertex(0,0,0));
        double[] lightSourceProjection = Util.CalculatePosition(camera, lightSource.toVertex());
        double[] temp = new double[3];
        temp[0]=lightSourceProjection[0]-originProjection[0];
        temp[1]=lightSourceProjection[1]-originProjection[1];
        
        double[] p1Projection = Util.CalculatePosition(camera, new Vertex(p1));
        double[] p2Projection = Util.CalculatePosition(camera, new Vertex(p2));
        double[] temp2 = new double[3];
        temp2[0] = p2Projection[0]-p1Projection[0];
        temp2[1] = p2Projection[1]-p1Projection[1];
        
        double cos = (temp[0]*temp2[0]+temp[1]*temp2[1])/(Math.sqrt(temp[0]*temp[0]+temp[1]*temp[1])*Math.sqrt(temp2[0]*temp2[0]+temp2[1]*temp2[1]));
        
        p2Projection[0]*=cos;
        p2Projection[1]*=(1-cos);
        Color c1 = new Color((int) (255 * p1.k), (int) (255 * p1.k), (int) (255 * p1.k));
        Color c2 = new Color((int) (255 * p2.k), (int) (255 * p2.k), (int) (255 * p2.k));
        
        return new Polygon2D(x, y, dist / this.p.size(), p1Projection, p2Projection, c1, c2);
    }
}
