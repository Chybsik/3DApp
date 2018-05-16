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
        Vertex v5 = new Vertex(-1,1,1);
        Vertex v6 = new Vertex(1,-1,1);
        Vertex v7 = new Vertex(-1,-1,1);
        Vertex v8 = new Vertex(-1,-1,-1);
        
        ArrayList<Vertex> vertices = new ArrayList<>(Arrays.asList(v1,v2,v3,v4,v5,v6,v7,v8));
        
        polygons = new ArrayList<>();
        
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v2,v3,v4))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v1,v3,v2))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v1,v4,v3))));
        
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v4,v3,v2,v5))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v2,v3,v1,v8))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v3,v4,v6,v1))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v8,v7,v5,v2))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v6,v4,v5,v7))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v1,v6,v7,v8))));
        
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
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        
        g.clearRect(0, 0, 1600, 900);

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
