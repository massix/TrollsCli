package rocks.massi.controller.commands;

import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.data.User;
import rocks.massi.controller.services.TrollsServer;

public class AddUserCommand implements Command {
    private final TrollsServer trollsServer;
    private Options options;

    public AddUserCommand() {
        trollsServer = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                .target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());
        options = new Options();

        options.addRequiredOption("b", "bggNick", true, "Nick on bgg");
        options.addRequiredOption("f", "forumNick", true, "Nick on the forum");
    }

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        CommandLine cli = new DefaultParser().parse(options, args);
        String bggNick = cli.getOptionValue("b");
        String forumNick = cli.getOptionValue("f");
        User toBeAdded = new User();
        toBeAdded.setBggNick(bggNick);
        toBeAdded.setForumNick(forumNick);

        try {
            User ret = trollsServer.addUser(toBeAdded);
            System.out.println("Added user " + ret.getBggNick());
        }
        catch(FeignException exception) {
            System.err.println("Exception occured: " + exception.getMessage());
        }
    }

    @Override
    public String getName() {
        return "add-user";
    }

    @Override
    public String help() {
        return "add-user [-b nickOnBgg] [-f nickOnForum]\tCreates a new empty user";
    }
}
