package rocks.massi.controller.commands;

import lombok.ToString;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.User;

@ToString
public class GetUserCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException {
        String nick = parseArgsForString(args);
        User user = connector.getUser(nick);

        if (user != null) {
            System.out.println("bggNick      : " + user.getBggNick());
            System.out.println("forumNick    : " + user.getForumNick());
        }
        else
            System.err.println("User not found" + nick);
    }

    @Override
    public String getName() {
        return "get-user";
    }

    @Override
    public String help() {
        return getName() + " [nick]\tFetches nick in server and prints out the information";
    }
}
