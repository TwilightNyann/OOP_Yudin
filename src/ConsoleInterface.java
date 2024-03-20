import java.util.Scanner;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;






public class ConsoleInterface {
    private Scanner scanner;
    private FlightCalculator calculator;
    private MacroCommand macroCommand;

    public ConsoleInterface() {
        scanner = new Scanner(System.in);
        calculator = new FlightCalculator();
        macroCommand = new MacroCommand();
    }

    public void start() {


        System.out.println("Welcome to Flight Distance Calculator!");

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Calculate flight distance");
            System.out.println("2. Undo last operation");
            System.out.println("3. Exit");
            System.out.println("4. Show Table");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    calculateFlightDistance();
                    break;
                case 2:
                    undoLastOperation();
                    break;
                case 3:
                    running = false;
                    break;
                case 4:
                    showAllSavedResults();
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
    }

    private void calculateFlightDistance() {
        System.out.println("\nEnter initial velocity (v0): ");
        double v0 = scanner.nextDouble();

        System.out.println("Enter launch angle (in degrees): ");
        double alpha = Math.toRadians(scanner.nextDouble());

        // Підрахунок дальності польоту
        double distance = calculator.calculateDistance(v0, alpha, 9.81);
        System.out.println("Flight distance calculated: " + distance + " meters");

        // Збереження параметрів для подальшого використання
        FlightParameters parameters = new FlightParameters(v0, alpha, distance);
        serializeParameters(parameters); // Серіалізація параметрів польоту
        // Збереження результату обчислення також можливе, якщо це необхідно
    }

    private void serializeParameters(FlightParameters parameters) {
        try {
            FileOutputStream fileOut = new FileOutputStream("parameters.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(parameters);
            out.close();
            fileOut.close();
            System.out.println("Parameters saved to parameters.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }



    public void undoLastOperation() {
        CommandManager commandManager = new CommandManager();
        commandManager.undoLastCommand();
    }


    public static void main(String[] args) {
        ConsoleInterface console = new ConsoleInterface();
        console.start();
    }

    private void showAllSavedResults() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("parameters.ser"))) {
            while (true) {
                Object obj = inputStream.readObject();
                if (obj instanceof FlightParameters) {
                    FlightParameters parameters = (FlightParameters) obj;
                    System.out.println("Initial Velocity (v0): " + parameters.v0);
                    System.out.println("Launch Angle (in degrees): " + Math.toDegrees(parameters.alpha));
                    System.out.println("Flight Distance: " + parameters.distance + " meters");
                    System.out.println("-----------------------");
                }
            }
        } catch (EOFException e) {
            // Кінець файлу, не потрібно нічого робити
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





}
