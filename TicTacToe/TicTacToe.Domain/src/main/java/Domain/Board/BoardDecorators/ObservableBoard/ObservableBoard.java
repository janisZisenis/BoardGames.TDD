package Domain.Board.BoardDecorators.ObservableBoard;

import Domain.Board.Board;
import Domain.Data.Field.Field;
import Domain.Data.Mark;
import Utilities.Observer.Observer;

import java.util.LinkedList;

public class ObservableBoard implements Board {

    private final Board board;
    private LinkedList<Observer> observers = new LinkedList<>();

    public ObservableBoard(Board board) {
        this.board = board;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void mark(Field f, Mark m) {
        board.mark(f, m);
        notifyObservers();
    }

    private void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update();
    }

    public boolean isEmpty(Field f) {
        return board.isEmpty(f);
    }

    public Mark getMarkAt(Field f) {
        return board.getMarkAt(f);
    }

    public int getMarkedFieldCount() {
        return board.getMarkedFieldCount();
    }

    public void clear() {
        board.clear();
        notifyObservers();
    }

    public boolean isMarked(Field f) {
        return board.isMarked(f);
    }
}
