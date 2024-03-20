import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        FlightParameters parameters = new FlightParameters(100, Math.toRadians(30), 500);
        serialize(parameters);
        FlightParameters restoredParameters = deserialize();
        System.out.println("Restored parameters: " + restoredParameters.v0 + ", " + restoredParameters.alpha + ", " + restoredParameters.distance);
    }

    private static void serialize(FlightParameters parameters) {
        try {
            FileOutputStream fileOut = new FileOutputStream("parameters.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(parameters);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in parameters.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static FlightParameters deserialize() {
        FlightParameters parameters = null;
        try {
            FileInputStream fileIn = new FileInputStream("parameters.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            parameters = (FlightParameters) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return parameters;
    }
}
