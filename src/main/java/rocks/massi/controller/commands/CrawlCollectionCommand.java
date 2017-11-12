package rocks.massi.controller.commands;

import feign.Response;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.authorization.JWTToken;

public class CrawlCollectionCommand extends Command {
    private static final int SC_ACCEPTED = 202;

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        String nick = parseArgsForString(args);

        Response response = connector.crawlCollection(JWTToken.getInstance().getHeadersMap(), nick);
        if (response.status() == SC_ACCEPTED) {
            System.out.println("Started (or continued) crawling the collection of user " + nick);
            System.out.println("Queue ID: " + response.headers().get("Location"));
        }
        else {
            System.err.println("User " + nick + " not found on server.");
        }
    }

    @Override
    public String getName() {
        return "crawl-collection";
    }

    @Override
    public String help() {
        return "crawl-collection [nick]\tStarts crawling the collection for the user.";
    }
}
