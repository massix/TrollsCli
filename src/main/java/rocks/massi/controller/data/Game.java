package rocks.massi.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Game {
    private int id;
    private String name;
    private int rank;
}
