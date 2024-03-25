package com.app.commands;

import com.app.managers.CollectionManager;
import com.app.managers.CommandManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class ExecuteScriptFileNameCommand implements Command {

    private CommandManager commandManager;
    private ArrayDeque<String> scriptStack;

    public ExecuteScriptFileNameCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        scriptStack = new ArrayDeque<>();
    }

    public String description() {
        return "executes script from file";
    }

    public void execute(String[] arguments) {
        try {
            if (scriptStack.contains(arguments[1])) {
                System.out.println("Recursive call is forbidden");
                return;
            }
            else {
                scriptStack.add(arguments[1]);
            }
            BufferedReader reader = new BufferedReader(new FileReader(arguments[1]));
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("add")) {
                    String arg1 = reader.readLine();
                }
                commandManager.executeCommand(line);
                line = reader.readLine();
            }
            reader.close();
            scriptStack.removeLast();
        } catch (FileNotFoundException exception) {
            System.out.println("File doesn't exist");
        } catch (IOException exception) {
            System.out.println("IOE exception");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for execute_script_file_name command. Please try again");
        }
    }

}
