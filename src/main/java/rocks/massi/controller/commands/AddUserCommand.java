package rocks.massi.controller.commands;

import feign.FeignException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.User;

public class AddUserCommand extends Command {

    public AddUserCommand() {
        super();

        options.addRequiredOption("b", "bggNick", true, "Nick on bgg");
        options.addRequiredOption("f", "forumNick", true, "Nick on the forum");
        options.addRequiredOption("p", "password", true, "Password for the user");
        options.addRequiredOption("e", "email", true, "Email");
    }

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        CommandLine cli = new DefaultParser().parse(options, args);
        String bggNick = cli.getOptionValue("b");
        String forumNick = cli.getOptionValue("f");
        String password = cli.getOptionValue("p");
        String email = cli.getOptionValue("e");
        User toBeAdded = new User();
        toBeAdded.setBggNick(bggNick);
        toBeAdded.setForumNick(forumNick);
        toBeAdded.setPassword(password);
        toBeAdded.setEmail(email);

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
        return "add-user -b bgg -f forum -e email -p password\tCreates or updates an user";
    }
}
