package rocks.massi.controller.data;

import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@ToString
public class User {
    private String bggNick;
    private String forumNick;
    private String games;
    private String wants;

    public List<Integer> buildCollection() {
        List<Integer> ret = new LinkedList<>();
        for(String game : games.split(" ")) {
            ret.add(Integer.valueOf(game));
        }
        return ret;
    }

    public List<Integer> buildWants() {
        List<Integer> ret = new LinkedList<>();
        if (!wants.isEmpty()) {
            for (String game : wants.split(" ")) {
                ret.add(Integer.valueOf(game));
            }
        }
        return ret;
    }
}
