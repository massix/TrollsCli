package rocks.massi.controller.data.bgg;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "boardgames")
public class Boardgames {

    @Getter
    @ToString
    public static class Boardgame {

        @XmlAttribute(name = "objectid")
        private int id;

        @XmlElement(name = "yearpublished")
        private int yearPublished;

        @XmlElement
        private String description;

        @XmlElement
        private String thumbnail;

        @XmlElement
        private String image;

        @XmlElement(name = "boardgamehonor")
        private List<String> honors;

        @XmlElement(name = "boardgamedesigner")
        private List<String> designers;

        @XmlElement(name = "name")
        private List<String> alternativeNames;
    }

    @XmlElement
    private List<Boardgame> boardgame;
}
