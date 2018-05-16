package pkg3dapp;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

/**
 *
 * @author Timur
 */
public class Screen extends JPanel implements KeyListener {

    double[] viewFrom;
    double[] viewTo;
    
    double[][] camera;
    Vector lightSource;

    ArrayList<Polygon3D>polygons;


    public Screen() {
        addKeyListener(this);
        setFocusable(true);

        viewFrom = new double[]{10, 10, 10};
        viewTo = new double[]{0, 0, 0};
        
        lightSource = new Vector(-10,-5,-10);
        
        camera = Util.LookAt(viewFrom, viewTo);

        Vertex v1 = new Vertex(1,-1,-1);
        Vertex v2 = new Vertex(-1,1,-1);
        Vertex v3 = new Vertex(1,1,-1);
        Vertex v4 = new Vertex(1,1,1);
        
        ArrayList<Vertex> vertices = new ArrayList<>(Arrays.asList(v1,v2,v3,v4));
        
        polygons = new ArrayList<>();
        
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v2,v3,v4))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v1,v3,v2))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v1,v4,v3))));
        
        for (Vertex vertex : vertices) {
            int temp=0;
            for (Polygon3D polygon : polygons) {
                for (Vertex p : polygon.p) {
                    if (vertex.equals(p)) {
                        vertex.n.x+=polygon.n.x;
                        vertex.n.y+=polygon.n.y;
                        vertex.n.z+=polygon.n.z;
                        temp++;
                    }
                }
            }
            vertex.n.x/=temp;
            vertex.n.y/=temp;
            vertex.n.z/=temp;
        }
//        polygons = new ArrayList<>();
//        polygons.add(new Polygon3D(new double[]{-1, 1, 1, -1}, new double[]{-1, -1, 1, 1}, new double[]{-1, -1, -1, -1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{1, 1, 1, 1}, new double[]{-1, -1, 1, 1}, new double[]{-1, 1, 1, -1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-1, 1, 1, -1}, new double[]{1, 1, 1, 1}, new double[]{-1, -1, 1, 1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{1, -1, -1, 1}, new double[]{-1, -1, 1, 1}, new double[]{1, 1, 1, 1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-1, -1, -1, -1}, new double[]{-1, -1, 1, 1}, new double[]{1, -1, -1, 1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-1, -1, 1, 1}, new double[]{-1, -1, -1, -1}, new double[]{-1, 1, 1, -1}, Color.GRAY));
//        Vertex v1 = new Vertex(1,1,-1);
//        v1.n.x = (polygons.get(0).n.x+polygons.get(1).n.x+polygons.get(2).n.x)/3;
//        v1.n.y = (polygons.get(0).n.y+polygons.get(1).n.y+polygons.get(2).n.y)/3;
//        v1.n.z = (polygons.get(0).n.z+polygons.get(1).n.z+polygons.get(2).n.z)/3;
//        
//        Vertex v2 = new Vertex(-1,1,-1);
//        v2.n.x = (polygons.get(0).n.x+polygons.get(2).n.x)/2;
//        v2.n.y = (polygons.get(0).n.y+polygons.get(2).n.y)/2;
//        v2.n.z = (polygons.get(0).n.z+polygons.get(2).n.z)/2;
//        
//        Vertex v3 = new Vertex(1,1,1);
//        v3.n.x = (polygons.get(1).n.x+polygons.get(2).n.x)/2;
//        v3.n.y = (polygons.get(1).n.y+polygons.get(2).n.y)/2;
//        v3.n.z = (polygons.get(1).n.z+polygons.get(2).n.z)/2;
//        
//        Vertex v4 = new Vertex(1,-1,-1);
//        v4.n.x = (polygons.get(0).n.x+polygons.get(1).n.x)/2;
//        v4.n.y = (polygons.get(0).n.y+polygons.get(1).n.y)/2;
//        v4.n.z = (polygons.get(0).n.z+polygons.get(1).n.z)/2;
//        polygons.add(new Polygon3D(new double[]{0.866025, 0.0, 0.0}, new double[]{0.0, 0, 2}, new double[]{-0.5, 1, 0.0}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{0.0, -0.866025, 0.0}, new double[]{0.0, 0.0, 2}, new double[]{1, -0.5, 0.0}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-0.866025, 0.866025, 0.0}, new double[]{0.0, 0.0, 2.0}, new double[]{-0.5, -0.5, 0.0}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-0.866025, 0.0, 0.866025}, new double[]{0.0, 1, 0}, new double[]{-0.5, 0, -0.5}, Color.GRAY));
    }
    
//    public void tmp(Polygon3D p, Vertex v){
//        Vertex v1 = new Vertex(1,1,-1);
//        v1.n.x = (polygons.get(0).n.x+polygons.get(1).n.x+polygons.get(2).n.x)/3;
//        v1.n.y = (polygons.get(0).n.y+polygons.get(1).n.y+polygons.get(2).n.y)/3;
//        v1.n.z = (polygons.get(0).n.z+polygons.get(1).n.z+polygons.get(2).n.z)/3;
//        
//        Vertex v2 = new Vertex(-1,1,-1);
//        v2.n.x = (polygons.get(0).n.x+polygons.get(2).n.x)/2;
//        v2.n.y = (polygons.get(0).n.y+polygons.get(2).n.y)/2;
//        v2.n.z = (polygons.get(0).n.z+polygons.get(2).n.z)/2;
//        
//        Vertex v3 = new Vertex(1,1,1);
//        v3.n.x = (polygons.get(1).n.x+polygons.get(2).n.x)/2;
//        v3.n.y = (polygons.get(1).n.y+polygons.get(2).n.y)/2;
//        v3.n.z = (polygons.get(1).n.z+polygons.get(2).n.z)/2;
//        
//        double tmp1 = v1.n.DotProduct(lightSource);
//        double tmp2 = v2.n.DotProduct(lightSource);
//        double tmp3 = v3.n.DotProduct(lightSource);
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.clearRect(0, 0, 2000, 2000);

        Polygon2D[] polygons2D = new Polygon2D[polygons.size()];
        for (int i = 0; i < polygons2D.length; i++) {
            polygons2D[i] = polygons.get(i).to2DPolygon(camera,lightSource);
        }

//        Polygon2D[] temp = new Polygon2D[polygons2D.length];
//        
//        for (int i = 0; i < polygons2D.length; i++) {
//            temp[i]=polygons2D[i];
//        }

        int[] order = new int[polygons2D.length];
        for (int i = 0; i < polygons2D.length; i++) {
            order[i]=i;
        }
        
        for (int i = 0; i < polygons2D.length; i++) {
            for (int j = i+1; j < polygons2D.length; j++) {
                if (polygons2D[order[i]].dist>polygons2D[order[j]].dist) {
                        int temp = order[i];
                        order[i]=order[j];
                        order[j]=temp;
                    
                }
            }
        }
        
        for (int i = 0; i < polygons2D.length; i++) {
            polygons2D[order[i]].drawPolygon((Graphics2D)g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                camera = Util.RotateY(5.0, camera);
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                camera = Util.RotateY(-5.0, camera);
                repaint();
                break;
            case KeyEvent.VK_UP:
                camera = Util.RotateX(5.0, camera);
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                camera = Util.RotateX(-5.0, camera);
                repaint();
                break;
        }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
