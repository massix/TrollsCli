package rocks.massi.controller.commands;

import feign.FeignException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.Queue;

public class GetQueueCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        // Parse CLI
        CommandLine commandLine = new DefaultParser().parse(options, args);
        int queueId;
        if (commandLine.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        }
        else {
            queueId = Integer.valueOf(commandLine.getArgList().get(0));
        }

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
