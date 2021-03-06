package Messaging.MessagingBoardListener;

import Domain.Data.Field.Field;
import Messaging.MessagingBoardListener.MarkedFieldMessageProvider;

import java.util.HashMap;

public class MarkedFieldMessageProviderStub implements MarkedFieldMessageProvider {

    private HashMap<Field, String> messages = new HashMap<>();

    public void setMessageForField(String message, Field f) {
        messages.put(f, message);
    }
    public String getMarkedFieldMessage(Field field) {
        return messages.get(field);
    }
}
