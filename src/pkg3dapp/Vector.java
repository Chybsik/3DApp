package pkg3dapp;

/**
 *
 * @author Timur
 */
public class Vector {

    double x, y, z = 0;

    public Vector(double x, double y, double z) {
        double length = Math.sqrt(x * x + y * y + z * z);

        if (length > 0) {
            this.x = x / length;
            this.y = y / length;
            this.z = z / length;
        }
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }
    
    public Vertex toVertex(){
        return new Vertex(this.x,this.y,this.z);
    }

    /**
     * Метод CrossProduct возвращает векторное произведение this и v
     *
     * @param v
     * @return
     */
    public Vector CrossProduct(Vector v) {
        return new Vector(z * v.y - y * v.z, x * v.z - z * v.x, y * v.x - x * v.y);
    }
    
    public Vector CrossProductRH(Vector v) {
        return new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    public double DotProduct(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public void Invert() {
        this.x *= -1;
        this.y *= -1;
        this.z *= -1;
    }
    
    public Vector VectorSubstract(Vector v){
        return new Vector(this.x-v.x,this.y-v.y,this.z-v.z);
    }

//    public void Transform(double[] v){
//        x +=v[0];
//        y+=v[1];
//        z+=v[2];
//    }
//    public void Scale(double[] v){
//        x+=v[0];
//        y+=v[1];
//        z+=v[2];
//    }
//    public void Rotate(double angle){
//        
//    }
}
