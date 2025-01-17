package software.ulpgc.kata6;

import software.ulpgc.kata6.control.CommandFactory;
import software.ulpgc.kata6.view.WorkingDaysService;

public class Main {
    public static void main(String[] args) {
        CommandFactory commandFactory = new CommandFactory();
        new WorkingDaysService(8080, commandFactory).start();
    }
}
