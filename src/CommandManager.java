import java.io.File;
import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;





public class CommandManager {
    private Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLastCommand() {
        /*if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();*/
            clearParametersFile(); // Очищення файлу parameters.ser
        //}
    }

    private void clearParametersFile() {
        try (FileWriter fileWriter = new FileWriter("parameters.ser")) {
            // Пишемо порожній рядок у файл, щоб він став порожнім
            fileWriter.write("");
            System.out.println("parameters.ser очищений");
        } catch (IOException e) {
            System.err.println("Failed to clear parameters.ser: " + e.getMessage());
        }
    }



}
