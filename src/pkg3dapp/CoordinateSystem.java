package pkg3dapp;

/**
 *
 * @author Timur
 */
public class CoordinateSystem {
    
    double[][] m;
    
    public CoordinateSystem(double[] position, double[] rotation, double[] scale){
        m = new double[4][4];
        for (int i = 0; i < 4; i++) {
            m[i][i]=1;
        }
        
        Resize(scale);
        Rotate(rotation);
        Translate(position);
        
        
    }
    
    public void Translate(double[] vector){
        double[][] r = new double[4][4];
        for (int i = 0; i < 4; i++) {
            r[i][i]=1;
        }
        
        r[3][0]+=vector[0];
        r[3][1]+=vector[1];
        r[3][2]+=vector[2];

        MatricesMult(r);
    }
    
    public void Rotate(double[] angle){
        double[][] r = new double[4][4];
        for (int i = 0; i < 4; i++) {
            r[i][i]=1;
        }
        
        r[0][0]=1; r[0][1]=0;                                   r[0][2]=0;                                  r[0][3]=0;
        r[1][0]=0; r[1][1]= Math.cos(Math.toRadians(angle[0])); r[1][2]=Math.sin(Math.toRadians(angle[0])); r[1][3]=0;
        r[2][0]=0; r[2][1]=-Math.sin(Math.toRadians(angle[0])); r[2][2]=Math.cos(Math.toRadians(angle[0])); r[2][3]=0;
        r[3][0]=0; r[3][1]=0;                                   r[3][2]=0;                                  r[3][3]=1;
        
        MatricesMult(r);
        
        r[0][0]=Math.cos(Math.toRadians(angle[1])); r[0][1]=0; r[0][2]=-Math.sin(Math.toRadians(angle[1])); r[0][3]=0;
        r[1][0]=0;                                  r[1][1]=1; r[1][2]=0;                                   r[1][3]=0;
        r[2][0]=Math.sin(Math.toRadians(angle[1])); r[2][1]=0; r[2][2]=Math.cos(Math.toRadians(angle[1]));  r[2][3]=0;
        r[3][0]=0;                                  r[3][1]=0; r[3][2]=0;                                   r[3][3]=1;
        
        MatricesMult(r);
        
        r[0][0]=Math.cos(Math.toRadians(angle[2]));  r[0][1]=Math.sin(Math.toRadians(angle[2])); r[0][2]=0; r[0][3]=0;
        r[1][0]=-Math.sin(Math.toRadians(angle[2])); r[1][1]=Math.cos(Math.toRadians(angle[2])); r[1][2]=0; r[1][3]=0;
        r[2][0]=0;                                   r[2][1]=0;                                  r[2][2]=1; r[2][3]=0;
        r[3][0]=0;                                   r[3][1]=0;                                  r[3][2]=0; r[3][3]=1;
        
        MatricesMult(r);
    }
    
    public void Resize(double[] vector){
        double[][] r = new double[4][4];
        for (int i = 0; i < 4; i++) {
            r[i][i]=1;
        }
        
        r[0][0]*=vector[0];
        r[1][1]*=vector[1];
        r[2][2]*=vector[2];
        
        MatricesMult(r);
    }
    
    public void MatricesMult(double[][] m) {
        double[][] res = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double temp=0;
                for (int k = 0; k < 4; k++) {
                    temp += this.m[i][k]*m[k][j];
                }
                res[i][j]=temp;
            }
        }
        this.m=res;
    }
    
    public double[] VectMatrixMult(double[] vector) {
        double[] res = new double[4];
        for (int j = 0; j < 4; j++) {
            double temp=0;
            for (int k = 0; k < 4; k++) {
                temp += this.m[k][j]*vector[k];
            }
            res[j]=temp;
        }
        return res;
    }
    
    public double[] Inverse(){
        return new double[1];
    }
}
