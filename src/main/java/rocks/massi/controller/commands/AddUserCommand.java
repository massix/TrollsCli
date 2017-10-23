package rocks.massi.controller.commands;

import feign.FeignException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.User;

public class AddUserCommand extends Command {

    public AddUserCommand() {
        super();

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
            User ret = connector.addUser(toBeAdded);
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
