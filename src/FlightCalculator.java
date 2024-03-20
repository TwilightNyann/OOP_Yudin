public class FlightCalculator {
    public static double calculateDistance(double v0, double alpha, double g) {
        return v0 * v0 * Math.sin(2 * alpha) / g;
    }
}