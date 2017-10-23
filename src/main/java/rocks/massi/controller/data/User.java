package rocks.massi.controller.data;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String bggNick;
    private String forumNick;
    private String games;
    private String wants;
}
