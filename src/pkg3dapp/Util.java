package pkg3dapp;

/**
 *
 * @author Timur
 */
public class Util {

    static double[] CalculatePosition(double[][] camera, Vertex v) {
        double[] result = new double[3];
//        vector = VectMatrixMult(vector, camera);
//        result[0] = vector[0] / -vector[2];
//        result[1] = vector[1] / -vector[2];
        //

        Vector forward = new Vector(camera[2][0], camera[2][1], camera[2][2]);
        Vector right = new Vector(camera[0][0], camera[0][1], camera[0][2]);
        Vector up = new Vector(camera[1][0], camera[1][1], camera[1][2]);

        Vector viewToPoint = new Vector(v.x - camera[3][0], v.y - camera[3][1], v.z - camera[3][2]);

        double t = (forward.x * 0 + forward.y * 0 + forward.z * 0
                - (forward.x * camera[3][0] + forward.y * camera[3][1] + forward.z * camera[3][2]))
                / (forward.x * viewToPoint.x + forward.y * viewToPoint.y + forward.z * viewToPoint.z);
//        double t = Math.sqrt(viewToPoint.x * viewToPoint.x + viewToPoint.y * viewToPoint.y + viewToPoint.z * viewToPoint.z);
//        double t = Math.sqrt(vector[0]*vector[0]+vector[1]*vector[1]+vector[2]*vector[2]);

        v.x = camera[3][0] + viewToPoint.x * t;
        v.y = camera[3][1] + viewToPoint.y * t;
        v.z = camera[3][2] + viewToPoint.z * t;

        result[0] = up.x * v.x + up.y * v.y + up.z * v.z;
        result[1] = right.x * v.x + right.y * v.y + right.z * v.z;
        //         

        result[0] = (result[0] + 8 / 2) / 8;
        result[1] = (result[1] + 8 / 2) / 8;
        result[0] *= 1600;
        result[1] *= 900;
        
        result[2] = t;

        return result;
    }

    public static double[][] LookAt(double[] from, double[] to) {
        double[][] m = new double[4][4];

        Vector tmp = new Vector(0, 1, 0);

        Vector forward = new Vector(from[0] - to[0], from[1] - to[1], from[2] - to[2]);
        Vector right = tmp.CrossProduct(forward);
        Vector up = forward.CrossProduct(right);

        m[0][0] = right.x;
        m[0][1] = right.y;
        m[0][2] = right.z;
        m[1][0] = up.x;
        m[1][1] = up.y;
        m[1][2] = up.z;
        m[2][0] = forward.x;
        m[2][1] = forward.y;
        m[2][2] = forward.z;

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

    public static double[] VectMatrixMult(double[] vector, double[][] m) {
        double[] result = new double[4];
        for (int j = 0; j < vector.length; j++) {
            double temp = 0;
            for (int k = 0; k < vector.length; k++) {
                temp += m[k][j] * vector[k];
            }
            result[j] = temp;
        }
        return result;
    }

    public static double[][] MatricesMult(double[][] m1, double[][] m2) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double temp = 0;
                for (int k = 0; k < 4; k++) {
                    temp += m1[i][k] * m2[k][j];
                }
                result[i][j] = temp;
            }
        }
        return result;
    }

    public static double[][] Translate(double[] vector, double[][] m) {
        m[3][0] += vector[0];
        m[3][1] += vector[1];
        m[3][2] += vector[2];
        return m;
    }

    public static double[][] RotateX(double angle, double[][] m) {
        double[][] r = new double[4][4];
        for (int i = 0; i < 4; i++) {
            r[i][i] = 1;
        }

        r[0][0] = 1;
        r[0][1] = 0;
        r[0][2] = 0;
        r[0][3] = 0;
        r[1][0] = 0;
        r[1][1] = Math.cos(Math.toRadians(angle));
        r[1][2] = Math.sin(Math.toRadians(angle));
        r[1][3] = 0;
        r[2][0] = 0;
        r[2][1] = -Math.sin(Math.toRadians(angle));
        r[2][2] = Math.cos(Math.toRadians(angle));
        r[2][3] = 0;
        r[3][0] = 0;
        r[3][1] = 0;
        r[3][2] = 0;
        r[3][3] = 1;

        return MatricesMult(m, r);
    }

    public static double[][] RotateY(double angle, double[][] m) {
        double[][] r = new double[4][4];
        for (int i = 0; i < 4; i++) {
            r[i][i] = 1;
        }

        r[0][0] = Math.cos(Math.toRadians(angle));
        r[0][1] = 0;
        r[0][2] = -Math.sin(Math.toRadians(angle));
        r[0][3] = 0;
        r[1][0] = 0;
        r[1][1] = 1;
        r[1][2] = 0;
        r[1][3] = 0;
        r[2][0] = Math.sin(Math.toRadians(angle));
        r[2][1] = 0;
        r[2][2] = Math.cos(Math.toRadians(angle));
        r[2][3] = 0;
        r[3][0] = 0;
        r[3][1] = 0;
        r[3][2] = 0;
        r[3][3] = 1;

        return MatricesMult(m, r);

    }

    public static double[][] RotateZ(double angle, double[][] m) {
        double[][] r = new double[4][4];
        for (int i = 0; i < 4; i++) {
            r[i][i] = 1;
        }

        r[0][0] = Math.cos(Math.toRadians(angle));
        r[0][1] = Math.sin(Math.toRadians(angle));
        r[0][2] = 0;
        r[0][3] = 0;
        r[1][0] = -Math.sin(Math.toRadians(angle));
        r[1][1] = Math.cos(Math.toRadians(angle));
        r[1][2] = 0;
        r[1][3] = 0;
        r[2][0] = 0;
        r[2][1] = 0;
        r[2][2] = 1;
        r[2][3] = 0;
        r[3][0] = 0;
        r[3][1] = 0;
        r[3][2] = 0;
        r[3][3] = 1;

        return MatricesMult(m, r);
    }

    public static double[][] Resize(double[] vector, double[][] m) {

        m[0][0] *= vector[0];
        m[1][1] *= vector[1];
        m[2][2] *= vector[2];

        return m;
    }
}
