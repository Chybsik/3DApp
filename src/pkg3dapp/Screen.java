package pkg3dapp;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

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

        polygons = new ArrayList<>();
        polygons.add(new Polygon3D(new double[]{-1, 1, 1, -1}, new double[]{-1, -1, 1, 1}, new double[]{-1, -1, -1, -1}, Color.GRAY));
        polygons.add(new Polygon3D(new double[]{1, 1, 1, 1}, new double[]{-1, -1, 1, 1}, new double[]{-1, 1, 1, -1}, Color.GRAY));
        polygons.add(new Polygon3D(new double[]{-1, 1, 1, -1}, new double[]{1, 1, 1, 1}, new double[]{-1, -1, 1, 1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{1, -1, -1, 1}, new double[]{-1, -1, 1, 1}, new double[]{1, 1, 1, 1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-1, -1, -1, -1}, new double[]{-1, -1, 1, 1}, new double[]{1, -1, -1, 1}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-1, -1, 1, 1}, new double[]{-1, -1, -1, -1}, new double[]{-1, 1, 1, -1}, Color.GRAY));
        
        Vertex v1 = new Vertex(1,1,-1);
        v1.n.x = (polygons.get(0).n.x+polygons.get(0).n.x+polygons.get(0).n.x)/3;
        v1.n.y = (polygons.get(0).n.y+polygons.get(0).n.y+polygons.get(0).n.y)/3;
        v1.n.z = (polygons.get(0).n.z+polygons.get(0).n.z+polygons.get(0).n.z)/3;
        
        Vertex v2 = new Vertex(-1,1,-1);
        v1.n.x = (polygons.get(0).n.x+polygons.get(0).n.x+polygons.get(0).n.x)/3;
        v1.n.y = (polygons.get(0).n.y+polygons.get(0).n.y+polygons.get(0).n.y)/3;
        v1.n.z = (polygons.get(0).n.z+polygons.get(0).n.z+polygons.get(0).n.z)/3;
        
        Vertex v3 = new Vertex(1,1,1);
        v1.n.x = (polygons.get(0).n.x+polygons.get(0).n.x+polygons.get(0).n.x)/3;
        v1.n.y = (polygons.get(0).n.y+polygons.get(0).n.y+polygons.get(0).n.y)/3;
        v1.n.z = (polygons.get(0).n.z+polygons.get(0).n.z+polygons.get(0).n.z)/3;
        
        Vertex v4 = new Vertex(1,-1,-1);
        v1.n.x = (polygons.get(0).n.x+polygons.get(0).n.x+polygons.get(0).n.x)/3;
        v1.n.y = (polygons.get(0).n.y+polygons.get(0).n.y+polygons.get(0).n.y)/3;
        v1.n.z = (polygons.get(0).n.z+polygons.get(0).n.z+polygons.get(0).n.z)/3;

//        polygons.add(new Polygon3D(new double[]{0.866025, 0.0, 0.0}, new double[]{0.0, 0, 2}, new double[]{-0.5, 1, 0.0}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{0.0, -0.866025, 0.0}, new double[]{0.0, 0.0, 2}, new double[]{1, -0.5, 0.0}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-0.866025, 0.866025, 0.0}, new double[]{0.0, 0.0, 2.0}, new double[]{-0.5, -0.5, 0.0}, Color.GRAY));
//        polygons.add(new Polygon3D(new double[]{-0.866025, 0.0, 0.866025}, new double[]{0.0, 1, 0}, new double[]{-0.5, 0, -0.5}, Color.GRAY));
    }

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
                if (polygons2D[order[i]].Dist>polygons2D[order[j]].Dist) {
                        int temp = order[i];
                        order[i]=order[j];
                        order[j]=temp;
                    
                }
            }
        }
        
        for (int i = 0; i < polygons2D.length; i++) {
            polygons2D[order[i]].drawPolygon(g);
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
