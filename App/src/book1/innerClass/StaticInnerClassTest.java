package src.book1.innerClass;

public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++) {
            double kd = 100 * Math.random();
            d[i] = kd;
        }
        ArrayAlg.Pair p = ArrayAlg.minmax(d);

        System.out.println("min = " + p.getMin() + " max = " + p.getMax());
    }
}

/**
 * ArrayAlg
 */
class ArrayAlg {
    /**
     * Pair
     */
    public static class Pair {
        private double min;
        private double max;

        public Pair(double amin, double amax) {
            this.min = amin;
            this.max = amax;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }
    }

    public static Pair minmax(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double d : values) {
            if (min > d) {
                min = d;
            }
            if (max < d) {
                max = d;
            }
        }
        return new Pair(min, max);
    }
}