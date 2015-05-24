package kmeans;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by witold on 23/05/15.
 */
public class KernelKMeansTest {

    double x[];
    double y[];

    @Before
    public void before() {
        x = new double[]{1, 3, 4, 5, 10, 11};
        y = new double[]{3, 3, 4, 5, 12, 13};
    }

    @Test
    public void testDiffSqr() throws Exception {

        double[] result = KernelKMeans.diffSqr(x, y);
        System.out.println("bla" + result);
    }

    @Test
    public void testNorm() throws Exception {
        final double norm = KernelKMeans.norm(x, y);
        System.out.println(" " + norm);
    }

    @Test
    public void testRBF() throws Exception {
        double rbf = KernelKMeans.RBF(x, y, 1);

    }

    @Test
    public void testKernel() throws Exception {
    }
}