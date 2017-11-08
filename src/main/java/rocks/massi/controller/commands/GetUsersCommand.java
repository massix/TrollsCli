package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.User;

import java.util.List;

public class GetUsersCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<User> list = connector.getAllUsers();
        System.out.println(String.format("  %-20s %-20s", "Nick on BGG", "Nick on Forum"));
        System.out.println("----------------------------------------------------------------");
        list.forEach(user -> System.out.println(String.format("  %-20s %-20s",
                user.getBggNick(),
                user.getForumNick())));
    }

    @Override
    public String getName() {
        return "get-users";
    }

    @Override
    public String help() {
        return "get-users\tFetches all the users in the server.";
    }
}
