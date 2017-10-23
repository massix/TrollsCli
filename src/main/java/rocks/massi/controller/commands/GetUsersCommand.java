package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.User;

import java.util.List;

public class GetUsersCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<User> list = connector.getAllUsers();
        list.forEach(user -> System.out.println("bggNick : " + user.getBggNick()));
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
