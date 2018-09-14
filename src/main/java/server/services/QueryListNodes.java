package server.services;

import com.google.gson.Gson;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import server.messengerserverapi.IMessengerApi;

public class QueryListNodes extends ServerResource {
    private static IMessengerApi restApi = IMessengerApi.getInstance();

    @Get
    public String listNodes() {
        return new Gson().toJson(restApi.listNodes());
    }
}
