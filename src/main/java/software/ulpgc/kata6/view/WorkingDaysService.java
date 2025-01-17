package software.ulpgc.kata6.view;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.ulpgc.kata6.control.CommandFactory;
import software.ulpgc.kata6.control.commands.WorkingDateCommand;
import software.ulpgc.kata6.control.commands.WorkingDaysCommand;
import software.ulpgc.kata6.view.adapters.WorkingDateAdapter;
import software.ulpgc.kata6.view.adapters.WorkingDaysAdapter;


public class WorkingDaysService {
    private final int port;
    private final CommandFactory commandFactory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory commandFactory) {
        this.port = port;
        this.commandFactory = commandFactory;
        commandFactory.register("working-days", workingDaysBuilder());
        commandFactory.register("working-date", workingDateBuilder());
    }

    public void start() {
        app = Javalin.create()
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .start(port);
    }

    public void execute(String command, HttpServletRequest req, HttpServletResponse res) {
        commandFactory.with(req, res).build(command).execute();
    }

    private static CommandFactory.Builder workingDaysBuilder() {
        return (req, res) -> new WorkingDaysCommand(WorkingDaysAdapter.inputOf(req), WorkingDaysAdapter.outputOf(res));
    }

    private static CommandFactory.Builder workingDateBuilder() {
        return (req, res) -> new WorkingDateCommand(WorkingDateAdapter.inputOf(req), WorkingDateAdapter.outputOf(res));
    }
}
