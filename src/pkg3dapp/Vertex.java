package pkg3dapp;

/**
 *
 * @author Timur
 */
public class Vertex {

    double x, y, z;
    Vector n;
    double k;
//    final String ID;

    public Vertex(double x, double y, double z){//, String ID) {
        this.x = x;
        this.y = y;
        this.z = z;

//        this.ID = ID;
    }
    
    public Vertex(Vertex v){
        this.x = v.x;
        this.y=v.y;
        this.z=v.z;
        this.n=new Vector(v.n);
        this.k=k;
//        this.ID=v.ID;
    }
}
