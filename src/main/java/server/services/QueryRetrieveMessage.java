package server.services;

import com.google.gson.Gson;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import server.messengerserverapi.IMessengerApi;

public class QueryRetrieveMessage extends ServerResource {
    private static IMessengerApi restApi = IMessengerApi.getInstance();

    @Post
    public String retrieveMessage(String id) {
        return new Gson().toJson(restApi.retrieveMessage(id));
    }
}
