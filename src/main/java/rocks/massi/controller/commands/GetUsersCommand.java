package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.User;

import java.util.List;

public class GetUsersCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<User> list = connector.getAllUsers();
        System.out.println(String.format("  %-20s %-20s %-10s %-10s", "Nick on BGG", "Nick on Forum", "Owned", "Wanted"));
        System.out.println("----------------------------------------------------------------");
        list.forEach(user -> System.out.println(String.format("  %-20s %-20s %-10d %-10d",
                user.getBggNick(),
                user.getForumNick(),
                user.buildCollection().size(),
                user.buildWants().size())));
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
