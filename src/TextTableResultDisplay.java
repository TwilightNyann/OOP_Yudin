public class TextTableResultDisplay implements FlightResultDisplay {
    @Override
    public void displayResult(double result) {
        System.out.println("Result:");
        System.out.println("-----------------");
        System.out.println("Distance: " + result + " meters");
        System.out.println("-----------------");
    }
}
