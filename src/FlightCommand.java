public class FlightCommand implements Command {
    private FlightCalculator calculator;
    private FlightParameters parameters;
    private double result;

    public FlightCommand(FlightCalculator calculator, FlightParameters parameters) {
        this.calculator = calculator;
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        result = calculator.calculateDistance(parameters.v0, parameters.alpha, 9.81);
        parameters.distance = result; // Зберігаємо результат обчислення у параметрах польоту
    }

    @Override
    public void undo() {
        // Undo operation if needed
    }

    public FlightParameters getParameters() {
        return parameters;
    }

    public double getResult() {
        return result;
    }
}
