import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
        int[] MAX_LIST = { 1000000000, 500, 10 };
        for (int MAX : MAX_LIST) {
            for (int size = 31250; size < 2000001; size *= 2) {
                long qtime = 0;
                long btime = 0;
                // average of 5 sorts.
                for (int trial = 0; trial <= 5; trial++) {
                    int[] data1 = new int[size];
                    int[] data2 = new int[size];
                    for (int i = 0; i < data1.length; i++) {
                        data1[i] = (int) (Math.random() * MAX);
                        data2[i] = data1[i];
                    }
                    long t1, t2;
                    t1 = System.currentTimeMillis();
                    Merge.mergesort(data2);
                    t2 = System.currentTimeMillis();
                    qtime += t2 - t1;
                    t1 = System.currentTimeMillis();
                    Arrays.sort(data1);
                    t2 = System.currentTimeMillis();
                    btime += t2 - t1;
                    if (!Arrays.equals(data1, data2)) {
                        System.out.println("FAIL TO SORT!");
                        System.exit(0);
                    }
                }
                System.out.println(size + "\t\t" + MAX + "\t" + 1.0 * qtime / btime);
            }
            System.out.println();
        }
    }

    public static void mergesort(int[] data) {
        mergeHelper(data);
    }

    private static void mergeHelper(int[] data) {
        if (data.length > 1) {
            int[] left = Arrays.copyOfRange(data, 0, data.length / 2);
            mergeHelper(left);
            int[] right = Arrays.copyOfRange(data, data.length / 2, data.length);
            mergeHelper(right);
            merge(left, right, data);
            // System.out.println(Arrays.toString(left) + " " + Arrays.toString(right));
        }
        return;
    }

    private static void merge(int[] a1, int[] a2, int[] data) {
        int j, k;
        j = k = 0;
        for (int i = 0; i < data.length; i++) {
            if (j < a1.length && k < a2.length) {
                if (a1[j] < +a2[k]) {
                    data[i] = a1[j];
                    j++;
                } else {
                    data[i] = a2[k];
                    k++;
                }
            } else {
                if (j >= a1.length) {
                    data[i] = a2[k];
                    k++;
                } else if (k >= a2.length) {
                    data[i] = a1[j];
                    j++;
                }
            }
        }
    }
}