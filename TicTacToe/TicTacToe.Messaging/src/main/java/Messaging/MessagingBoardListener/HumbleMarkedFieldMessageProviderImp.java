package Messaging.MessagingBoardListener;

import Domain.Data.Field.Field;
import Messages.MarkedFieldMessage;

public class HumbleMarkedFieldMessageProviderImp implements MarkedFieldMessageProvider {

    public String getMarkedFieldMessage(Field field) {
        return  MarkedFieldMessage.getMarkedFieldMessage(field);
    }

}
