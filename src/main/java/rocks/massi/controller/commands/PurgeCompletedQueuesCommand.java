package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.Queue;

import java.util.List;

public class PurgeCompletedQueuesCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<Queue> purged = connector.purgeCompletedQueues();
        System.out.println("Purged Queues");
        purged.forEach(q -> {
            q.print();
            System.out.println();
        });
    }

    @Override
    public String getName() {
        return "purge-queues";
    }

    @Override
    public String help() {
        return "purge-queues\tPurges completed queues.";
    }
}
