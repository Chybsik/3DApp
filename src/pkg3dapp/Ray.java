package pkg3dapp;

/**
 *
 * @author Timur
 */
public class Ray {
    Vertex orig;
    Vector dir;
    public Ray(double x, double y, int width, int height){
        //NDC Space
        x = (x+0.5)/width;
        y = (y+0.5)/height;
        
        x = 2*x-1;
        y = 2*y-1;
        
//        y *=-1;
//        
//        double imageAspectRatio = width/height;
//        
//        x = (2*x-1)*imageAspectRatio;
//        y = 1-2*y;
        
        dir = new Vector(x,y,-1);
    }
}
