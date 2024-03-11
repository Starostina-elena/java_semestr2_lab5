package commands;

import managers.CollectionManager;
import managers.CommandManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScriptFileNameCommand implements Command {

    private CommandManager commandManager;

    public ExecuteScriptFileNameCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String description() {
        return "executes script from file";
    }

    public void execute(String[] arguments) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arguments[1]));
            String line = reader.readLine();
            while (line != null) {
                commandManager.executeCommand(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File doesn't exist");
        } catch (IOException exception) {
            System.out.println("IOE exception");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for execute_script_file_name command. Please try again");
        }
    }

}
