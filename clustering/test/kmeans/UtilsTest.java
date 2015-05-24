package kmeans;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by witold on 24/05/15.
 */
public class UtilsTest {

    @Test
    public void testSqr() throws Exception {
        assertTrue(Utils.sqr(3.) == 9.);
    }

    @Test
    public void testSquaredDistance() throws Exception {
        double res = Utils.squaredDistance(new double[]{3, 3, 3}, new double[]{4, 4, 4});
    }

    @Test
    public void testDivideArray() throws Exception {
        double[] center = new double[]{3, 3, 3};
        Utils.divideArray(center, 3);
        System.out.println(center);
    }

    @Test
    public void testAddAarrays() throws Exception {
        double res = Utils.squaredDistance(new double[]{3, 3, 3}, new double[]{4, 4, 4});
    }
}