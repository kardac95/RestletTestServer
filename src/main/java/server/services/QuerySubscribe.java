package server.services;

import com.google.gson.Gson;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import server.messengerserverapi.IMessengerApi;

public class QuerySubscribe extends ServerResource {
    private static IMessengerApi restApi = IMessengerApi.getInstance();

    @Put
    public String subscribe(String param) {
        String params[] = new Gson().fromJson(param, String[].class);
        return new Gson().toJson(restApi.subscribe(params[0], params[1]));
    }
}
