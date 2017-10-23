package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.User;

public class CrawlUserCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        String nick = parseArgsForString(args);
        User user = connector.crawlUser(nick);
        if (user != null) {
            System.out.println("Crawled user        : " + user.getBggNick());
            System.out.println("List of owned games : {" + user.getGames() + "}");
            System.out.println("List of wanted games: {" + user.getWants() + "}");
        }
        else {
            System.err.println("User " + nick + " not found on Database.");
        }
    }

    @Override
    public String getName() {
        return "crawl-user";
    }

    @Override
    public String help() {
        return "crawl-user [nick]\tCrawls a single user";
    }
}
