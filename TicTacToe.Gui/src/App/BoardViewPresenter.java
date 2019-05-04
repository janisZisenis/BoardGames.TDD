package App;

import Board.ListenableBoard.BoardListener;
import Board.Mark;
import Data.Field.Field;
import Data.Line.Line;
import Bussiness.Input.Input;
import View.FXBoardView;

public class BoardViewPresenter implements BoardViewDelegate, BoardListener {

    private final FXBoardView view;
    private final BoardViewInteractor interactor;

    public BoardViewPresenter(FXBoardView view, BoardViewInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onTileClicked(int row, int column) {
        Input input = new Input(row, column);
        interactor.process(input);
    }

    public void updateField(Field field) {
        udpateField(field);

        if(interactor.hasWinningLine()) {
            Line line = interactor.getWinningLine();
            view.showWinningLine(line);
        }
    }

    private void udpateField(Field field) {
        if(interactor.isMarked(field)) {
            Mark m = interactor.getMarkAt(field);
            view.setFieldMark(field, m);
        }
    }

}
