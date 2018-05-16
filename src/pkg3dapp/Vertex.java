package pkg3dapp;

/**
 *
 * @author Timur
 */
public class Vertex {

    double x, y, z;
    Vector n;
    double k;

    public Vertex(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;

        this.n=new Vector(0,0,0);
    }
    
    public Vertex(Vertex v){
        this.x = v.x;
        this.y=v.y;
        this.z=v.z;
        this.n=new Vector(v.n);
        this.k=k;
    }
    
    public Vector toVector(){
        return new Vector(this.x,this.y,this.z);
    }
}
