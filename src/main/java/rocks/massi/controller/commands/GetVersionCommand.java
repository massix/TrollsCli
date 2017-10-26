package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;

public class GetVersionCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        System.out.println(connector.getVersion().getVersion());
    }

    @Override
    public String getName() {
        return "get-version";
    }

    @Override
    public String help() {
        return "get-version\tGets the version of the server.";
    }
}
