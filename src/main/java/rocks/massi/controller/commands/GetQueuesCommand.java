package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.Queue;

import java.util.List;

public class GetQueuesCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<Queue> queues = connector.getQueues();
        queues.forEach(q -> {
            q.print();
            System.out.println();
        });
    }

    @Override
    public String getName() {
        return "get-queues";
    }

    @Override
    public String help() {
        return "get-queues\tRetrieves all queues";
    }
}
