package rocks.massi.controller.commands;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.data.User;
import rocks.massi.controller.services.TrollsServer;

public class RemoveUserCommand implements Command {
    private final TrollsServer trollsServer;
    private final Options options;

    public RemoveUserCommand() {
        trollsServer = Feign.builder().decoder(new JacksonDecoder()).target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());
        options = new Options();
    }

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        CommandLine cli = new DefaultParser().parse(options, args);
        String nick;

        if (cli.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        }
        else {
            nick = String.join(" ", cli.getArgList());
        }

        User ret = trollsServer.removeUser(nick);
        if (ret != null) {
            System.out.println("Removed user " + ret.getBggNick());
        }
        else {
            System.err.println("User " + nick + " not found");
        }
    }

    @Override
    public String getName() {
        return "remove-user";
    }

    @Override
    public String help() {
        return "remove-user [nick]\tRemoves user from DB.";
    }
}
