package rocks.massi.controller.services;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import rocks.massi.controller.data.User;

import java.util.List;

public interface TrollsServer {
    @RequestLine("GET /v1/users/get/{id}")
    User getUser(@Param("id") final String nick);

    @RequestLine("GET /v1/users/get")
    List<User> getAllUsers();

    @RequestLine("POST /v1/users/add")
    @Headers({"Content-Type: application/json"})
    User addUser(final User user);

    @RequestLine("DELETE /v1/users/remove/{nick}")
    User removeUser(@Param("nick") final String nick);
}
