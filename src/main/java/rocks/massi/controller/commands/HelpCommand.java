package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;

public class HelpCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        throw new HelpRequestedException();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String help() {
        return "help\tPrints help.";
    }
}
