package kmeans;

public class KMeans {

    public static double computeSSE(double[][] data, double[][] centers, int[] clusterID) {
        double sse = 0;
        for (int i = 0; i < data.length; ++i) {
            int c = clusterID[i];
            sse += Utils.squaredDistance(data[i], centers[c]);
        }
        return sse;
    }

    public static int[] updateClusterID(double[][] data, double[][] centers) {
        int[] clusterID = new int[data.length];

        for (int i = 0; i < data.length; ++i) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < centers.length; ++j) {
                double v = Utils.squaredDistance(data[i], centers[j]);
                if (v < min) {
                    clusterID[i] = j;
                    min = v;
                }
            }
        }
        // TODO assign the closet center to each data point
        // you can use the function Utils.squaredDistance
        return clusterID;
    }

    public static double[][] updateCenters(double[][] data, int[] clusterID, int K) {
        double[][] centers = new double[K][data[0].length];
        int[] ksize = new int[K];

        for (int k = 0; k < K; ++k) {
            for (int i = 0; i < clusterID.length; ++i) {
                if (k == clusterID[i]) {
                    Utils.addAarrays(centers[k], data[i]);
                    ksize[k]++;
                }
            }
            Utils.divideArray(centers[k], ksize[k]);
        }
        // TODO recompute the centers based on current clustering assignment
        // If a cluster doesn't have any data points, in this homework, leave it to ALL 0s.
        return centers;
    }

    /**
     * run kmeans a single time, with specific initialization and number of iterations
     * double[][] data are the points need to be clustered
     * double[][] centers are the initializations
     * int maxIter is the max number of itertions for kmeans
     * double tol is the tolerance for stop criterion
     * return clusterID
     */
    public static int[] kmeans(double[][] data, double[][] centers, int maxIter, double tol) {
        int n = data.length; // the number of data points
        if (n == 0) {
            return new int[0];
        }
        int k = centers.length;
        int[] clusterID = new int[n];
        if (k >= n) {
            for (int i = 0; i < n; ++i) {
                clusterID[i] = i;
            }
            return clusterID;
        }

        double lastDistance = 1e100; // set to infinity

        for (int iter = 0; iter < maxIter; ++iter) {
            clusterID = updateClusterID(data, centers);
            centers = updateCenters(data, clusterID, k);
            double distance = computeSSE(data, centers, clusterID);

            if ((lastDistance - distance) < tol || (lastDistance - distance) / lastDistance < tol) { // stop criterion
                System.out.println("# iterations = " + iter);
                System.out.println("SSE = " + distance);
                return clusterID;
            }
            lastDistance = distance;
        }
        System.out.println("# iterations = " + maxIter);
        System.out.println("SSE = " + lastDistance);
        return clusterID;
    }
}
