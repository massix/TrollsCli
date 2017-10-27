package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.ServerInformation;

public class GetVersionCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        ServerInformation serverInformation = connector.getVersion();

        System.out.printf("Version: %s - Artifact: %s - Build: %s\n",
                serverInformation.getVersion(),
                serverInformation.getArtifact(),
                serverInformation.getTimestamp());
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
