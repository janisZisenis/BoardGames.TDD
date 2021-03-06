package ConsoleView;

import Domain.Data.BoardBoundaries;
import Domain.Data.Field.Field;
import Domain.Data.Line.Line;
import Domain.Data.Mark;
import Domain.GameEvaluation.EquallyMarkedLineEvaluator.MarkedFieldProvider;
import Messaging.WinnerMessageProviderImp.MarkToStringMapper;
import Rendering.BoardRenderer.RenderingBoardView;

public class ConsoleBoardView implements RenderingBoardView {

    private final String empty = "\u00B7";
    private final String winningColor = "\u001B[32m";
    private final String colorReset = "\u001B[0m";

    private final int rowColumn = BoardBoundaries.rowColumnCount;

    private final MarkToStringMapper mapper;
    private final MarkedFieldProvider fieldProvider;

    public ConsoleBoardView(MarkedFieldProvider fieldProvider, MarkToStringMapper mapper) {
        this.fieldProvider = fieldProvider;
        this.mapper = mapper;
    }

    public void showBoard() {
        StringBuilder board = new StringBuilder();
        board.append("  C 012  \n");
        board.append("R +-----+\n");

        for(int i = 0; i < rowColumn; i++)
            board.append(i + " | ").append(getRow(i)).append(" |\n");

        board.append("  +-----+");

        System.out.println(board);
    }

    private String getRow(int row) {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < rowColumn; i++) {
            Field f = new Field(row, i);
            s.append(getField(f));
        }

        return s.toString();
    }

    private boolean lineContains(Field f, Line line) {
        return f.equals(line.getFirst())
                || f.equals(line.getSecond())
                || f.equals(line.getThird());
    }

    private String getField(Field f) {
        return fieldProvider.isMarked(f) ? mapper.map(fieldProvider.getMarkAt(f)) : empty;
    }

    private String map(Mark m) {
        return mapper.map(m);
    }


    public void showWinningLine(Line line) {
        StringBuilder board = new StringBuilder();
        board.append("  C 012  \n");
        board.append("R +-----+\n");

        for(int i = 0; i < rowColumn; i++)
            board.append(i + " | ").append(getRow(i, line)).append(" |\n");

        board.append("  +-----+");

        System.out.println(board);
    }

    private String getRow(int row, Line line) {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < rowColumn; i++) {
            Field f = new Field(row, i);
            String field = lineContains(f, line) ? getWinningField(f) : getField(f);
            s.append(field);
        }

        return s.toString();
    }

    private String getWinningField(Field f) {
        StringBuilder s = new StringBuilder();
        s.append(winningColor);
        s.append(getField(f));
        s.append(colorReset);

        return s.toString();
    }

}
