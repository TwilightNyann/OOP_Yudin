import java.io.Serializable;

public class FlightParameters implements Serializable {
    private static final long serialVersionUID = 1L;
    double v0;
    double alpha;
    double distance;

    public FlightParameters(double v0, double alpha, double distance) {
        this.v0 = v0;
        this.alpha = alpha;
        this.distance = distance;
    }
}
