package com.app.managers;

import com.app.commands.*;

import java.util.*;

/**CommandManager class. Provides operations with commands*/
public class CommandManager {

    private Map<String, Command> commandsManager = new HashMap<>();
    private CollectionManager collectionManager;
    private FileManager fileManager;

    /**Constructor. Loading of available commands*/
    public CommandManager(CollectionManager collectionManager, FileManager fileManager) {

        this.collectionManager = collectionManager;
        this.fileManager = fileManager;

        commandsManager.put("help", new HelpCommand(this));
        commandsManager.put("add", new AddCommand(this.collectionManager));
        commandsManager.put("info", new InfoCommand(this.collectionManager));
        commandsManager.put("show", new ShowCommand(this.collectionManager));
        commandsManager.put("update", new UpdateCommand(this.collectionManager));
        commandsManager.put("remove_by_id", new RemoveByIdCommand(this.collectionManager));
        commandsManager.put("clear", new ClearCommand(this.collectionManager));
        commandsManager.put("save", new SaveCommand(this.fileManager, this.collectionManager));
        commandsManager.put("exit", new ExitCommand());
        commandsManager.put("remove_head", new RemoveHeadCommand(this.collectionManager));
        commandsManager.put("add_if_max", new AddIfMaxCommand(this.collectionManager));
        commandsManager.put("remove_lower", new RemoveLowerCommand(this.collectionManager));
        commandsManager.put("min_by_id", new MinByIdCommand(this.collectionManager));
        commandsManager.put("count_by_part_number", new CountByPartNumberCommand(this.collectionManager));
        commandsManager.put("print_field_ascending_manufacturer", new PrintFieldAscendingManufacturerCommand(this.collectionManager));
        commandsManager.put("execute_script_file_name", new ExecuteScriptFileNameCommand(this));
    }

    /**Execution from command line*/
    public void executeFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        while(sc.hasNext()) {
            String line = sc.nextLine();
            executeCommand(line);
            System.out.print("> ");
        }
    }

    /**Execution of a line (line could be readen from file)*/
    public void executeCommand(String line) {
        String[] tokens = line.split(" ");
        Command command = commandsManager.get(tokens[0]);
        try {
            command.execute(tokens);
        } catch (NullPointerException e) {
            System.out.println("Incorrect command. Use help to see a list of available commands");
        }
    }

    public Map<String, Command> getCommandsList() {
        return commandsManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}