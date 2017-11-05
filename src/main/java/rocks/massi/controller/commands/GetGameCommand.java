package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.Game;
import rocks.massi.controller.data.trolls.User;

import java.util.LinkedList;
import java.util.List;

public class GetGameCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        int gameId = parseArgsForInt(args);

        Game game = connector.getGame(gameId);
        List<User> users = connector.getAllUsers();
        if (game != null) {
            List<String> owners = new LinkedList<>();
            for (User u : users) {
                if (u.buildCollection().contains(game.getId())) {
                    owners.add(u.getBggNick());
                }
            }

            System.out.println(String.format("%-10s %-60s %-7s %s", "id", "name", "rank", "owners"));
            System.out.println(String.format("%-10d %-60s %-7d %s",
                    game.getId(),
                    game.getName().substring(0, Math.min(58, game.getName().length())),
                    game.getRank(),
                    String.join(", ", owners)));
            System.out.println(game.getDescription());
        }
        else {
            System.err.println("Game with id " + gameId + " doesn't exist.");
        }
    }

    @Override
    public String getName() {
        return "get-game";
    }

    @Override
    public String help() {
        return "get-game [id]\tGets game with the given ID";
    }
}
