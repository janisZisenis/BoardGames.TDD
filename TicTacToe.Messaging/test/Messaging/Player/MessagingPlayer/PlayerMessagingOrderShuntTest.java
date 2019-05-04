package Messaging.Player.MessagingPlayer;

import Api.MarkFieldService;
import Board.Mark;
import Data.Field.Field;
import Core.Input.Input;
import Core.Players.InputGenerator;
import Core.Players.PlayerContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerMessagingOrderShuntTest implements MarkFieldService, PlayerMessenger, InputGenerator {

    private String logString = "";

    @Test
    void IfGetsPlayed_ShouldMarkTheFieldBeforePublishing() {
        MessagingPlayer sut = makeMessagingPlayer();

        sut.playMove();

        String actual = logString;
        String expected = "mark publishMarkedField ";
        assertEquals(expected, actual);
    }

    public void mark(Field f, Mark m) {
        logString += "mark ";
    }

    public void publishMarkedField(Field f) {
        logString += "publishMarkedField ";
    }

    public Input generate() {
        return new Input(0, 1);
    }

    private MessagingPlayer makeMessagingPlayer() {
        PlayerContext context = new PlayerContext(this, this, Mark.John);
        return new MessagingPlayer(context, this);
    }

}
