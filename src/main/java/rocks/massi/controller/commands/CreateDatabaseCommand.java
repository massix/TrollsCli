package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;

public class CreateDatabaseCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        connector.createSchema();
    }

    @Override
    public String getName() {
        return "create-database";
    }

    @Override
    public String help() {
        return "create-database\tCreates the schema for the DB.";
    }
}
