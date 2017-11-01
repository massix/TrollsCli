package rocks.massi.controller.commands;

import feign.FeignException;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.User;

public class CrawlUserCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        String nick = parseArgsForString(args);
        try {
            User user = connector.crawlUser(nick);
            if (user != null) {
                System.out.println("Crawled user        : " + user.getBggNick());
                System.out.println("List of owned games : {" + user.getGames() + "}");
                System.out.println("List of wanted games: {" + user.getWants() + "}");
            } else {
                System.err.println("User " + nick + " not found on Database.");
            }
        }
        catch (FeignException ex) {
            System.err.println("Server is too busy right now. Please try again later.");
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
