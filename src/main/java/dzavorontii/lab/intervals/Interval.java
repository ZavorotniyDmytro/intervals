package dzavorontii.lab.intervals;

public class Interval {
    float left;
    float right;

    public Interval() {
        left = 0;
        right = 0;
    }

    public Interval(float left, float right) {
        this.left = Math.min(left, right);
        this.right = Math.max(left, right);
    }



    @Override
    public String toString() {
        return "Interval{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public static Interval createAddInterval(Interval A, Interval B){
        return new Interval(A.left + B.left, A.right + B.right);
    }

    public static Interval createMinusInterval(Interval A, Interval B){
        return new Interval(A.left - B.right, A.right - B.left);
    }

    public static Interval createMultiplyInterval(Interval A, Interval B) {
        return new Interval(
                Math.min(Math.min(A.left * B.left, A.left * B.right), Math.min(A.right * B.left, A.right * B.right)),
                Math.max(Math.max(A.left * B.left, A.left * B.right), Math.max(A.right * B.left, A.right * B.right))
        );
    }

    public static Interval createDivInterval(Interval A, Interval B) {
        return new Interval(A.left / B.right, A.right / B.left);
    }

    public static Interval createDivHypothesisInterval(Interval A, Interval B) {
        return new Interval(
                Math.min(Math.min(A.left / B.left, A.left / B.right), Math.min(A.right / B.left, A.right / B.right)),
                Math.max(Math.max(A.left / B.left, A.left / B.right), Math.max(A.right / B.left, A.right / B.right))
        );
    }

    public static Interval createReflectionInterval(Interval A) {
        return new Interval(A.left * -1, A.right * -1);
    }

    public static Interval createMaximumInterval(Interval A, Interval B) {
        return new Interval(Math.max(A.left, B.left), Math.max(A.right, B.right));
    }

    public static Interval createMinimumInterval(Interval A, Interval B) {
        return new Interval(Math.min(A.left, B.left), Math.min(A.right, B.right));
    }

    public static Interval createInversionInterval(Interval B) {
        return new Interval(1 / B.left, 1 / B.right);
    }
}