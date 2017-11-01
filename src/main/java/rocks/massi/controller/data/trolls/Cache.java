package rocks.massi.controller.data.trolls;

import lombok.Data;

import java.util.List;

@Data
public class Cache {

    @Data
    public static class Entry {
        private long key;
        private long timestamp;
        private String humanReadable;
    }

    boolean success;
    String error;
    List<Entry> entries;
}
