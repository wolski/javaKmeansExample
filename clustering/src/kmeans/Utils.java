package kmeans;

import java.util.Arrays;

public class Utils {
    public static double sqr(double x) {
        return x * x;
    }

    public static double squaredDistance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += sqr(a[i] - b[i]);
        }
        return sum;
    }

    static void divideArray(double[] center, double Ksize) {
        for (int i = 0; i < center.length; ++i) {
            center[i] /= Ksize;
        }
    }

    static void addAarrays(double[] center, double[] data) {
        Arrays.setAll(center, i -> center[i] + data[i]);
    }
}
