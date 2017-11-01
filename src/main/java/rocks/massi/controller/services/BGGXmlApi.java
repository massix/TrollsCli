package rocks.massi.controller.services;

import feign.Param;
import feign.RequestLine;
import rocks.massi.controller.data.bgg.Boardgames;

public interface BGGXmlApi {
    @RequestLine("GET /boardgame/{id}")
    Boardgames getGameForId(@Param("id") final int id);

    @RequestLine("GET /search?search={search}")
    Boardgames search(@Param("search") final String search);
}
