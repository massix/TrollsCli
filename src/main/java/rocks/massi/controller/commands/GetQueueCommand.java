package rocks.massi.controller.commands;

import feign.FeignException;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.Queue;

public class GetQueueCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        int queueId = parseArgsForInt(args);

        try {
            Queue queue = connector.getQueue(queueId);
            queue.print();
        }
        catch (FeignException exc) {
            System.err.println("Queue not found on server.");
        }

    }

    @Override
    public String getName() {
        return "get-queue";
    }

    @Override
    public String help() {
        return "get-queue [id]\tRetrieves queue with given ID.";
    }
}
