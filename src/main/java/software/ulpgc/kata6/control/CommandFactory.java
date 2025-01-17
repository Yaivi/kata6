package software.ulpgc.kata6.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.module.ModuleDescriptor;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Builder> builders;

    public CommandFactory() {
        builders = new HashMap<>();
    }

    public void register(String name, Builder builder) {
        builders.put(name, builder);
    }

    public Selector with(HttpServletRequest req, HttpServletResponse resp) {
        return name -> builders.get(name).build(req, resp);

    }

    public interface Builder {
        Command build(HttpServletRequest req, HttpServletResponse resp);
    }

    public interface Selector {
        Command build(String name);
    }

}
