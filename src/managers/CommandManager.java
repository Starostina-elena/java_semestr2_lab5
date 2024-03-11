package managers;

import commands.*;

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

        commandsManager.put("help", new commands.HelpCommand(this));
        commandsManager.put("add", new commands.AddCommand(this.collectionManager));
        commandsManager.put("info", new commands.InfoCommand(this.collectionManager));
        commandsManager.put("show", new commands.ShowCommand(this.collectionManager));
        commandsManager.put("update", new commands.UpdateCommand(this.collectionManager));
        commandsManager.put("remove_by_id", new commands.RemoveByIdCommand(this.collectionManager));
        commandsManager.put("clear", new commands.ClearCommand(this.collectionManager));
        commandsManager.put("save", new commands.SaveCommand(this.fileManager, this.collectionManager));
        commandsManager.put("exit", new commands.ExitCommand());
        commandsManager.put("remove_head", new commands.RemoveHeadCommand(this.collectionManager));
        commandsManager.put("add_if_max", new commands.AddIfMaxCommand(this.collectionManager));
        commandsManager.put("remove_lower", new commands.RemoveLowerCommand(this.collectionManager));
        commandsManager.put("min_by_id", new commands.MinByIdCommand(this.collectionManager));
        commandsManager.put("count_by_part_number", new commands.CountByPartNumberCommand(this.collectionManager));
        commandsManager.put("print_field_ascending_manufacturer", new commands.PrintFieldAscendingManufacturerCommand(this.collectionManager));
        commandsManager.put("execute_script_file_name", new commands.ExecuteScriptFileNameCommand(this));
    }

    /**Execution from command line*/
    public void executeFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            Command command = commandsManager.get(tokens[0]);
            try {
                command.execute(tokens);
            } catch (NullPointerException e) {
                System.out.println("Incorrect command. Use help to see a list of available commands");
            }
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