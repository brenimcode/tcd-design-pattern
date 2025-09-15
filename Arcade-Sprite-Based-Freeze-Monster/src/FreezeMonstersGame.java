package freezemonsters;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

import java.awt.*;

public class FreezeMonstersGame extends MainFrame {
    public FreezeMonstersGame() {
        super("Freeze Monsters");
    }

    protected AbstractBoard createBoard() {
        return new FreezeMonstersBoard();
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var ex = new FreezeMonstersGame();
            ex.setVisible(true);
        });
    }
}
