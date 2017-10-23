package rocks.massi.controller.data;

import lombok.Data;

@Data
public class Queue {
    private String user;
    private boolean running;
    private int crawled;
    private int failed;
    private int cacheHit;
    private int cacheMiss;
    private int total;
    private String started;
    private String finished;
    private int queue;

    public void print() {
        System.out.println(" --------> Queue #" + getQueue() + " <-------- ");
        System.out.println("User            : " + getUser());
        System.out.println("Running         : " + isRunning());
        System.out.println("Crawled games   : " + getCrawled());
        System.out.println("Failed games    : " + getFailed());
        System.out.println("Cache HITs      : " + getCacheHit());
        System.out.println("Cache MISSes    : " + getCacheMiss());
        System.out.println("Total games     : " + getTotal());
        System.out.println("Started         : " + getStarted());
        System.out.println("Finished        : " + (getFinished() != null ? getFinished() : "nope"));
    }
}
