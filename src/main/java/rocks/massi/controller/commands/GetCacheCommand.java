package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.Cache;

public class GetCacheCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        Cache cache = connector.getCache();
        if (cache.isSuccess()) {
            System.out.printf("%-15s %-20s %s\n", "Key", "Timestamp", "Human Readable Format");
            cache.getEntries().forEach(elt -> System.out.printf("%-15d %-20d %s\n",
                    elt.getKey(), elt.getTimestamp(), elt.getHumanReadable()));
        }
    }

    @Override
    public String getName() {
        return "get-cache";
    }

    @Override
    public String help() {
        return "get-cache\tGets the current in-memory cache";
    }
}
