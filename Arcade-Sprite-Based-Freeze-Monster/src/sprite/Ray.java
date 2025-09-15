package freezemonsters.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;
import java.awt.*;


public class Ray extends BadSprite{
    public Ray(){

    }
    public Ray(int x, int y){
        init(x,y);
    }

    private void init(int x, int y){
        String rayImage = "/images/ray.png";
        ImageIcon ii = new ImageIcon(this.getClass().getResource(rayImage));
        Image originalImage = ii.getImage();
        Image scaledImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        setImage(scaledImage);

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
