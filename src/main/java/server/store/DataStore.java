package server.store;

import message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DataStore {
    private Map<String, List<Message>> messages;
    private Map<String, List<String>> subscribers;
    private List<String> listNodes;

    public DataStore() {
        messages = new ConcurrentHashMap<String, List<Message>>();
        subscribers = new ConcurrentHashMap<String, List<String>>();
        listNodes = new ArrayList<>();
    }

    public void addMessage(Message message) {
        message.setTimestamp(System.currentTimeMillis() / 1000L);
        if(messages.containsKey(message.getTopic())) {
           messages.get(message.getTopic()).add(message);
        } else {
            List tmp = new ArrayList();
            tmp.add(message);
            messages.put(message.getTopic(), tmp);
        }
        if(!subscribers.containsKey(message.getTopic())) {
            addTopicSub(message.getTopic());
        }
    }

    public List getMessages(String topic) {
        return messages.get(topic);
    }

    public void addTopicSub(String topic) {
        subscribers.put(topic, new ArrayList<String>());
    }

    public boolean addSubscriber(String topic, String userName) {
        if(subscribers.containsKey(topic)) {
            subscribers.get(topic).add(userName);
            return true;
        }
        return false;
    }

    public boolean removeSubscriber(String topic, String userName) {
        if(subscribers.containsKey(topic)) {
            subscribers.get(topic).remove(userName);
            return true;
        }
        return false;
    }

    public List getSubscribers(String topic) {
        return subscribers.get(topic);
    }

    public Map getMessageWithTimeStamp() {
        Map<String, List<Message>> messWithTimeStamp = new ConcurrentHashMap<>();
        for (String topic : messages.keySet() ) {
            messWithTimeStamp.put(topic, messages.get(topic).stream().filter(m -> m.getTimestamp() != 0).collect(Collectors.toList()));
        }
        return messWithTimeStamp;
    }

    public Message getMessageById(String id) {
        for (List<Message> mess :messages.values()) {
            List<Message> possibleCandidates = mess.stream().filter(m -> m.getId().equals(id)).collect(Collectors.toList());
            if(possibleCandidates.size() > 0) {
                return possibleCandidates.get(0);
            }
        }
        return null;
    }

    public List getTopics() {
        return new ArrayList<String>(subscribers.keySet());
    }

    public List getlistNodes() {
        return listNodes;
    }

    public void addToListNodes(String address) {
        listNodes.add(address);
    }
}
