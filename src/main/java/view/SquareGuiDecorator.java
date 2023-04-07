package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import shared.GUICoord;

public class SquareGuiDecorator extends BorderPane implements ChessSquareGui, ChangeListener {
    private ChessSquareGui chessSquare;
    public SquareGuiDecorator(ChessSquareGui chessSquareGui) {
        GuiConfig.blackSquareColor.addListener(this);
        GuiConfig.whiteSquareColor.addListener(this);
        GuiConfig.paintStyle.addListener(this);
    }

    @Override
    public GUICoord getCoord() {
        return chessSquare.getCoord();
    }
    @Override
    public void resetColor(boolean isLight) {
        chessSquare.resetColor(isLight);
    }

    @Override
    public void paint() {
        chessSquare.paint();
    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        paint();
    }
}
