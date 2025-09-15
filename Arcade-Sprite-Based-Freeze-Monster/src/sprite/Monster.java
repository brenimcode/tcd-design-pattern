package freezemonsters.sprite;

import spriteframework.Commons;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Monster extends BadnessBoxSprite {

    private Gosma shoot;
    private boolean frozen;
    public String imgCongelado;
    private int Timer = 10;

    public Monster(int x, int y) {
        init(x,y);
        setFrozen(false);
    }

    public void init(int x, int y){
        this.x = x;
        this.y = y;
        shoot = new Gosma(x, y);

        Random r = new Random();
        int num = r.nextInt(9)+1;
        String imgMonster = "/images/monster" + num + ".png";
        this.imgCongelado = "/images/monster" + num + "bg.png";
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imgMonster));
        Image originalImage = ii.getImage();
        Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setImage(scaledImage);
    }

    @Override
    public void act(){

        x += dx;
        y += dy;

        if(x <= 2){
            x = 2;
        }

        if (x >= spriteframework.Commons.BOARD_WIDTH - 2 * 50) {

            x = spriteframework.Commons.BOARD_WIDTH - 2 * 50;
        }

        if (y <= 2) {

            y = 2;
        }

        if (y >= Commons.BOARD_HEIGHT - 2 * 50) {

            y = Commons.BOARD_HEIGHT - 2 * 50;
        }
    }

    @Override
    public void die(){
        setFrozen(true);
        java.net.URL imgURL = getClass().getResource(this.imgCongelado);
        if (imgURL != null) {
            ImageIcon ii = new ImageIcon(imgURL);
            Image originalImage = ii.getImage();
            Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            setImage(scaledImage);
        }
    }

    public void setFrozen(boolean frozen){
        this.frozen = frozen;
    }

    public boolean isFrozen(){
        return frozen;
    }

    public Gosma getShoot(){
        return this.shoot;
    }

    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> shot = new LinkedList<>();
        shot.add(shoot);
        return shot;
    }

    public int getTimer() {
        return Timer;
    }

    public void setTimer(int timer) {
        Timer = timer;
    }

}
