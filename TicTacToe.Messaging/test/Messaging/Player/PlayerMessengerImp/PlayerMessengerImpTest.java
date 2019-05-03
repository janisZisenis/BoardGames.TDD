package Messaging.Player.PlayerMessengerImp;

import Lib.Data.Field.Field;
import Messaging.MessengerMock;
import org.junit.jupiter.api.Test;

public class PlayerMessengerImpTest {

    private MessengerMock messenger = new MessengerMock();
    private MarkedFieldMessageProviderStub provider = new MarkedFieldMessageProviderStub();
    private PlayerMessengerImp sut = new PlayerMessengerImp(messenger, provider);

    private Field field = new Field(0, 1);

    @Test
    void IfMarkedFieldGetsPublished_ShouldPublishTheProvidedMessage() {
        provider.setMessageForField("Message", field);
        messenger.expectPublishMessage("Message");

        sut.publishPlayedMove(field);

        messenger.verifyAll();
    }

}
