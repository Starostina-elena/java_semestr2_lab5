package commands;

import managers.CollectionManager;

public class ExitCommand implements Command {

    public String description() {
        return "quits from program";
    }

    public void execute(String[] arguments) {
        System.out.println("goodbye");
        System.exit(0);
    }

}
