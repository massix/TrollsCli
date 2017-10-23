package rocks.massi.controller.commands;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.ToString;
import org.apache.commons.cli.*;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.data.User;
import rocks.massi.controller.services.TrollsServer;

@ToString
public class GetUserCommand implements Command {
    private final TrollsServer trollsServer;
    private Options options;

    public GetUserCommand() {
        trollsServer = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());
        options = new Options();
    }

    @Override
    public void run(String[] args) throws ParseException {
        // Parse CLI
        CommandLine commandLine = new DefaultParser().parse(options, args);
        String nick;
        if (commandLine.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        }
        else {
            nick = String.join(" ", commandLine.getArgList());
        }

        User user = trollsServer.getUser(nick);
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
