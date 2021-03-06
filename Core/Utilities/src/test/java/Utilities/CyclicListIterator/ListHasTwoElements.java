package Utilities.CyclicListIterator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListHasTwoElements {

    private Object first = new Object();
    private Object second = new Object();
    private List<Object> list = Arrays.asList(first, second);
    private CyclicListIterator<Object> sut = new CyclicListIterator<>(list);

    @Test
    void MovedToNextOnce_ShouldReturnSecondElementAsCurrent() {
        sut.next();

        Object actual = sut.getCurrent();

        Object expected = second;
        assertEquals(expected, actual);
    }

}
