package kmeans;

import java.util.Arrays;

public class KernelKMeans {
    public static double[] diffSqr(double[] x, double[] y) {
        double[] res = new double[x.length];
        Arrays.setAll(res, i -> Math.pow(x[i] - y[i], 2));
        return res;
    }

    public static double norm(double[] x, double[] y) {
        double norm = 0.;
        double[] diff = diffSqr(x, y);
        double res = Arrays.stream(diff).reduce(0, (a, b) -> a + b);
        res = Math.sqrt(res);
        return res;
    }

    public static double RBF(double[] x, double[] y, double sigma) {
        double norm = norm(x, y);
        return Math.exp(-(norm * norm) / (2 * sigma * sigma));
    }

    public static double[][] kernel(double[][] data, double sigma) {

        double[][] RBFKernel = new double[data.length][data.length];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data.length; ++j) {
                RBFKernel[i][j] = RBF(data[i], data[j], sigma);
            }
        }
        return RBFKernel;
    }
}
