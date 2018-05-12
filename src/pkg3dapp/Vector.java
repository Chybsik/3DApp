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

    /**
     * Метод CrossProduct возвращает векторное произведение this и v
     *
     * @param v
     * @return
     */
    public Vector CrossProduct(Vector v) {
        return new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
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
