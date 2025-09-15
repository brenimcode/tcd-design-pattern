package freezemonsters.sprite;

import spriteframework.Commons;
import spriteframework.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerFreezeMonsters extends Player {

    private int ultimaAcao;

    public PlayerFreezeMonsters(){
        this.ultimaAcao = KeyEvent.VK_DOWN;
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/woody.png"));
        setImage(ii.getImage());
        Image originalImage = ii.getImage();
        Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setImage(scaledImage);
        getImageDimensions();
        resetState(freezemonsters.Commons.INIT_WOODY_X, freezemonsters.Commons.INIT_WOODY_Y);
    }


    public void act(){
        x += dx;
        y += dy;

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * super.getWidth()) {

            x = Commons.BOARD_WIDTH - 2 * super.getWidth();
        }

        if(y <= 2){
            y = 2;
        }

        if(y>= Commons.BOARD_WIDTH - 2 * super.getWidth()){
            y = Commons.BOARD_WIDTH - 2 * super.getWidth();
        }
    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(key != KeyEvent.VK_SPACE){
            ultimaAcao = key;
        }

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public int getUltimaAcao() {
        return ultimaAcao;
    }

    public void setUltimaAcao(int ultimaAcao) {
        this.ultimaAcao = ultimaAcao;
    }
}

