package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.User;

public class RemoveUserCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        String nick = parseArgsForString(args);

        User ret = connector.removeUser(nick);
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
