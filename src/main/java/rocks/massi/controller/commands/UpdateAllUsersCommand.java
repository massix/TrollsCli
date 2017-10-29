package rocks.massi.controller.commands;

import feign.Response;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.Queue;
import rocks.massi.controller.data.User;

import java.util.List;

public class UpdateAllUsersCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        List<User> users = connector.getAllUsers();
        for (User u : users) {
            User ret = null;
            while (ret == null) {
                try {
                    ret = connector.crawlUser(u.getBggNick());
                    Thread.sleep(250);
                } catch (Exception e) {
                    /* Do nothing */
                }
            }

            List<Integer> userCollection = u.buildCollection();

            System.out.printf("%-15s %d\n", u.getBggNick(), userCollection.size());
            Response r = connector.crawlCollection(u.getBggNick());

            if (r.headers().containsKey("location")) {
                String location = (String) r.headers().get("location").toArray()[0];
                String locationSplit[] = location.split("/");
                int queueId = Integer.valueOf(locationSplit[locationSplit.length - 1]);
                System.out.printf("  -> Monitoring Queue %d\n", queueId);
                Queue queue = connector.getQueue(queueId);

                while (queue.isRunning()) {
                    System.out.printf("\033[1K\r");
                    System.out.flush();
                    System.out.printf("  -> (%d/%d) [%d failed] [%d cache hit]",
                            queue.getCrawled() + queue.getCacheHit(), queue.getTotal(), queue.getFailed(), queue.getCacheHit());

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    queue = connector.getQueue(queueId);
                }

                System.out.printf("\033[1K\r");
                System.out.flush();
                System.out.printf("  -> (%d/%d)\n\n", queue.getCrawled() + queue.getCacheHit(), queue.getTotal());
            }

            else {
                System.err.printf("Something wrong might have happened, I don't have a queue id. Stopping here.\n");
                return;
            }
        }
    }

    @Override
    public String getName() {
        return "update-users";
    }

    @Override
    public String help() {
        return "update-users\tRecrawl all users";
    }
}
