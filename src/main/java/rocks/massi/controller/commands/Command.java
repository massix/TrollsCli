package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;

public interface Command {
    void run(String[] args) throws ParseException, HelpRequestedException;
    String getName();
    String help();
}
