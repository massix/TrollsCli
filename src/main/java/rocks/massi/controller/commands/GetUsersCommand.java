package rocks.massi.controller.commands;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.data.User;
import rocks.massi.controller.services.TrollsServer;

import java.util.List;

public class GetUsersCommand implements Command {
    private final TrollsServer trollsServer;

    public GetUsersCommand() {
        trollsServer = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());
    }

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<User> list = trollsServer.getAllUsers();
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
