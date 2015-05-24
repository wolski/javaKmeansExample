package kmeans;

import java.util.Arrays;

public class SupervisedEvaluation {
    public static double purity(int[] groundtruthAssignment, int[] algorithmAssignment, int k) {

        int[][] table = new int[k][k];
        for (int i = 0; i < groundtruthAssignment.length; ++i) {
            table[groundtruthAssignment[i]][algorithmAssignment[i]] += 1;
        }

        int[] col = new int[k];
        // find column maxima...
        for (int row = 0; row < k; ++row) {
            for (int b : table[row]) {
                if (b > col[row]) {
                    col[row] = b;
                }
            }
        }

        double res = Arrays.stream(col).reduce(0, (a, b) -> a + b);
        return res / groundtruthAssignment.length;
    }

    public static double NMI(int[] groundtruthAssignment, int[] algorithmAssignment, int k) {
        int N = groundtruthAssignment.length;
        double[][] table = new double[k][k];
        for (int i = 0; i < N; ++i) {
            table[groundtruthAssignment[i]][algorithmAssignment[i]] += 1;
        }

        double tableT[] = new double[k];
        double tableC[] = new double[k];

        for (int i = 0; i < N; ++i) {
            tableT[groundtruthAssignment[i]] += 1;
            tableC[algorithmAssignment[i]] += 1;
        }

        for (int i = 0; i < k; ++i) {
            tableT[i] /= (double) N;
            tableC[i] /= (double) N;
            for (int j = 0; j < k; ++j) {
                table[i][j] /= N;
            }
        }

        double ICT = 0.;
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < k; ++j) {
                ICT += table[i][j] * Math.log(table[i][j] / (tableC[i] * tableT[j]));
            }
        }

        double HC = 0.;
        double HT = 0.;
        for (int i = 0; i < k; ++i) {
            HC += tableC[i] * Math.log(tableC[i]);
            HT += tableT[i] * Math.log(tableT[i]);
        }
        return ICT / Math.sqrt(HC * HT);
    }
}