package pkg3dapp;

/**
 *
 * @author Timur
 */
public class Util {

    static double drawX = 0, drawY = 0;

    static double CalculatePositionX(double[] camPos, double[] camDirection, double[] vector) {
        double[][] camera = LookAt(camPos,camDirection);
        vector = VectMatrixMult(vector,camera);
        drawX = vector[0]/vector[1];
        
        return drawX ;
    }

    static double CalculatePositionY(double[] camPos, double[] camDirection, double[] vector) {
        double[][] camera = LookAt(camPos,camDirection);
        vector = VectMatrixMult(vector,camera);
        drawY = vector[2]/vector[1];
        return drawY;
    }


    public static double[][] LookAt(double[] from, double[] to) {
        double[][] m = new double[4][4];

        Vector tmp = new Vector(0, 0, 1);

        Vector forward = new Vector(to[0] - from[0], to[1] - from[1], to[2] - from[2]);
        Vector right = forward.CrossProduct(tmp);
        Vector up = right.CrossProduct(forward);

        m[0][0] = right.x;
        m[0][1] = right.y;
        m[0][2] = right.z;
        m[1][0] = forward.x;
        m[1][1] = forward.y;
        m[1][2] = forward.z;
        m[2][0] = up.x;
        m[2][1] = up.y;
        m[2][2] = up.z;

//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                m[i][j]+=10;
//            }
//        }
        
        m[3][0] = from[0];
        m[3][1] = from[1];
        m[3][2] = from[2];

        m[0][3] = 0;
        m[1][3] = 0;
        m[2][3] = 0;
        m[3][3] = 1;

        return m;
    }

    public static double[] VectMatrixMult(double[] vector,double[][] m) {
        double[] res = new double[4];
        for (int j = 0; j < 4; j++) {
            double temp = 0;
            for (int k = 0; k < 4; k++) {
                temp += m[k][j] * vector[k];
            }
            res[j] = temp;
        }
        return res;
    }
}
