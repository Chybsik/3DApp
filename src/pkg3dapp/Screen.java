package pkg3dapp;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

/**
 *
 * @param viewFrom - координаты камеры
 * @param viewTo - точка в которою направлена камера
 * @param camera - камера записаная как матрица 4x4
 * @param lightSource - вектор источника света
 * @param polygons - набор полигонов
 * @author Timur
 */
public class Screen extends JPanel  implements KeyListener {

    double[] viewFrom;
    double[] viewTo;
    double[][] camera;
    Vector lightSource;
    ArrayList<Polygon3D> polygons;

    public Screen() {
        addKeyListener(this);
        setFocusable(true);

        viewFrom = new double[]{14.1421, 10, 10};
        viewTo = new double[]{0, 0, 0};

        lightSource = new Vector(-10, -5, -7.5);

        camera = Util.LookAt(viewFrom, viewTo);
        Vertex v1 = new Vertex(1, -1, -1);
        Vertex v2 = new Vertex(-1, 1, -1);
        Vertex v3 = new Vertex(1, 1, -1);
        Vertex v4 = new Vertex(1, 1, 1);
        Vertex v5 = new Vertex(-1, 1, 1);
        Vertex v6 = new Vertex(1, -1, 1);
        Vertex v7 = new Vertex(-1, -1, 1);
        Vertex v8 = new Vertex(-1, -1, -1);
//        Vertex v1 = new Vertex(1.414214, -0.899075, -17.8939);
//        Vertex v3 = new Vertex(1.414214, 0.739229, -16.746748);
//        Vertex v4 = new Vertex(0, -0.071931, -15.588291);
//        Vertex v6 = new Vertex(0, -1.710235, -16.735445);
//        Vertex v5 = new Vertex(-1.414214, 0.739229, -16.746748);
//        Vertex v7 = new Vertex(-1.414214, -0.899075, -17.8939);
//        Vertex v2 = new Vertex(0, 1.550389, -17.905203);
//        Vertex v8 = new Vertex(0, -0.087915, -19.052357);
//        Vertex v1 = new Vertex(0, 0, -2);
//        Vertex v2 = new Vertex(0.19509, 0, -2.019215);
//        Vertex v3 = new Vertex(0.191342, 0.19509, -2.03806);
//        Vertex v4 = new Vertex(0, 0.19509, -2.019215);
//        Vertex v5 = new Vertex(0.382684, 0, -2.076121);
//        Vertex v6 = new Vertex(0.37533, 0.19509, -2.093873);
//        Vertex v7 = new Vertex(0.55557, 0, -2.16853);
//        Vertex v8 = new Vertex(0.544895, 0.19509, -2.184507);

        ArrayList<Vertex> vertices = new ArrayList<>(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8));

        polygons = new ArrayList<>();

        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v4, v3, v2, v5))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v2, v3, v1, v8))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v3, v4, v6, v1))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v8, v7, v5, v2))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v6, v4, v5, v7))));
        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v1, v6, v7, v8))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v4, v2, v1))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v4, v3, v2))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v3, v5, v2))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v3, v6, v5))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v6, v7, v5))));
//        polygons.add(new Polygon3D(new ArrayList(Arrays.asList(v6, v8, v7))));

        for (Vertex vertex : vertices) {
            int temp = 0;
            for (Polygon3D polygon : polygons) {
                for (Vertex p : polygon.p) {
                    if (vertex.equals(p)) {
                        vertex.n.x += polygon.n.x;
                        vertex.n.y += polygon.n.y;
                        vertex.n.z += polygon.n.z;
                        temp++;
                    }
                }
            }
            vertex.n.x /= temp;
            vertex.n.y /= temp;
            vertex.n.z /= temp;
        }
    }

    /**
     * paintComponent - метод проецирующий все полигоны на плоскость отрисовки
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, this.getWidth(), this.getHeight());

//        Random random = new Random();
//        BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
//
//        Color black = new Color(0, 0, 0);
//
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                Ray r = new Ray(x, y, width, height);
//                for (int i = 0; i < polygons.size(); i++) {
//                    if (Util.rayQuadIntersect(new Vertex(0, 0, 0), r.dir, polygons.get(i))) {
//                        image.setRGB(x, y, black.getRGB());
//                    }
//                }
//
////                image.setRGB(x, y, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)).getRGB());
//            }
//        }
//        g.drawImage(getScaledImage(image, this.getWidth(), this.getHeight()), 0, 0, null);

        Polygon2D[] polygons2D = new Polygon2D[polygons.size()];
        for (int i = 0; i < polygons2D.length; i++) {
            polygons2D[i] = polygons.get(i).to2DPolygon(camera, lightSource);
        }

        int[] order = new int[polygons2D.length];
        for (int i = 0; i < polygons2D.length; i++) {
            order[i] = i;
        }

        for (int i = 0; i < polygons2D.length; i++) {
            for (int j = i + 1; j < polygons2D.length; j++) {
                if (polygons2D[order[i]].dist > polygons2D[order[j]].dist) {
                    int temp = order[i];
                    order[i] = order[j];
                    order[j] = temp;

                }
            }
        }

        for (int i = 0; i < polygons2D.length; i++) {
            polygons2D[order[i]].drawPolygon((Graphics2D) g);
        }
    }

    private BufferedImage getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * @method keyPressed - вызываемый клавишами стрелок метод осуществляющий
     * поворот камеры вокруг центра сцены
     * @param e
     */
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
