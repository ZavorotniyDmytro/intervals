package dzavorontii.lab.intervals;

public class Interval {
    float left;
    float right;

    public Interval() {
        left = 0;
        right = 0;
    }

    public Interval(float left, float right) {
        this.left = left;
        this.right = right;
    }



    @Override
    public String toString() {
        return "Interval{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public static Interval createAddInterval(Interval A, Interval B){
        return new Interval(A.left - B.left, A.right - B.right);
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
}