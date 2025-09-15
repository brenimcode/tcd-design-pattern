package freezemonsters.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;
import java.awt.*;

public class Gosma extends BadSprite {

    private boolean destroyed;

    public Gosma(int x, int y) {
        init(x,y);
    }

    private void init(int x, int y) {
        setDestroyed(true);

        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/gosma.png"));
        setImage(ii.getImage());
        Image originalImage = ii.getImage();
        Image scaledImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(scaledImage);

    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
