package server.messengerserverapi;

import message.IMessenger;
import message.Message;
import server.store.DataStore;

import java.util.List;
import java.util.Map;


public class IMessengerApi implements IMessenger {

    private DataStore store;

    private IMessengerApi() {
        store = new DataStore();
    }

    private static class IMessengerHolder {
        private static IMessengerApi instance = new IMessengerApi();
    }

    public static IMessengerApi getInstance() {
        return IMessengerHolder.instance;
    }



    public void postMessage(Message message) {
        store.addMessage(message);
    }

    public List listMessages(String topic) {
        return store.getMessages(topic);
    }

    public Map listMessagesWithTimestamps(String topic) {
        return store.getMessageWithTimeStamp();
    }

    public Message retrieveMessage(String id) {
        return store.getMessageById(id);
    }

    public List listTopics() {
        return store.getTopics();
    }

    public boolean subscribe(String username, String topic) {
        return store.addSubscriber(topic, username);
    }

    public boolean unsubscribe(String username, String topic) {
        return store.removeSubscriber(topic, username);
    }

    public List listSubscribers(String topic) {
        return store.getSubscribers(topic);
    }

    public List listNodes() {
        return store.getlistNodes();
    }

    public void addToListNodes(String address) { store.addToListNodes(address); }
}
