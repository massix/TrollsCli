package rocks.massi.controller.commands;

import lombok.ToString;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.User;

@ToString
public class GetUserCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException {
        String nick = parseArgsForString(args);
        User user = connector.getUser(nick);

        if (user != null) {
            String[] games = {""};
            if (user.getGames() != null)
                games = user.getGames().split(" ");

            String[] wants = {""};
            if (user.getWants() != null)
                wants = user.getWants().split(" ");

            System.out.println("bggNick      : " + user.getBggNick());
            System.out.println("forumNick    : " + user.getForumNick());
            System.out.println("owned games  : " + games.length);
            System.out.println("wanted games : " + wants.length);
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
