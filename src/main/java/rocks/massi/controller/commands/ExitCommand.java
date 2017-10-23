package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;

public class ExitCommand implements Command {
    @Override
    public void run(String[] args) {

    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String help() {
        return "exit\tExits the CLI.";
    }
}
