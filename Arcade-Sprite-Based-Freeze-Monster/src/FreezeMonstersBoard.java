package freezemonsters;

import spriteframework.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import freezemonsters.sprite.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class FreezeMonstersBoard extends AbstractBoard {

    public FreezeMonstersBoard(){
        super(new PlayerFactoryFreezeMonsters(), new Color(32,172,116));
    }

    private Ray ray;
    private String explImg = "/images/explosion.png";
    private int frozen = 0;
    private int UltimaTecla;

    protected void createBadSprites() {

        for (int i = 0; i < Commons.NUMBER_OF_MONSTERS_TO_FREEZE; i++){
            Random random = new Random();
            int x = random.nextInt(399);
            int y = random.nextInt(299);
            Monster monster = new Monster(Commons.MONSTER_INIT_X + x,Commons.MONSTER_INIT_Y + y);
            badSprites.add(monster);
        }
    }


    protected void createOtherSprites() {
        ray = new Ray();
        UltimaTecla = KeyEvent.VK_LEFT;
    }

    private void drawRay(Graphics g) {

        if (ray.isVisible()) {

            g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
        }
    }


    protected void drawOtherSprites(Graphics g) {
        drawRay(g);
        g.setColor(Color.green);
    }

    protected void processOtherSprites(Player player, KeyEvent e) {
        int x = player.getX();
        int y = player.getY();

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            if (inGame) {

                if (!ray.isVisible()) {
                    UltimaTecla = ((PlayerFreezeMonsters)player).getUltimaAcao();
                    ray = new Ray(x, y);
                }
            }
        }
    }

    protected void update() {

        if (frozen == Commons.NUMBER_OF_MONSTERS_TO_FREEZE) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        for (Player player: players)
            player.act();

        // shot
        if (ray.isVisible()) {

            int rayX = ray.getX();
            int rayY = ray.getY();

            for (BadSprite monster : badSprites) {

                int monsterX = monster.getX();
                int monsterY = monster.getY();

                if (monster.isVisible() && ray.isVisible() && !((Monster)monster).isFrozen()) {
                    if (rayX >= (monsterX)
                            && rayX <= (monsterX + Commons.MONSTER_WIDTH)
                            && rayY >= (monsterY)
                            && rayY <= (monsterY + Commons.MONSTER_HEIGHT)) {

                        monster.die();
                        frozen++;
                        ray.die();
                    }
                }
            }
            if(UltimaTecla == KeyEvent.VK_LEFT){
                rayX -= 4;
            }
            if(UltimaTecla == KeyEvent.VK_RIGHT){
                rayX += 4;
            }
            if(UltimaTecla == KeyEvent.VK_UP){
                rayY -= 4;
            }
            if(UltimaTecla == KeyEvent.VK_DOWN){
                rayY += 4;
            }
            if(rayY < 0 || rayX < 0 || rayY > Commons.BOARD_HEIGHT || rayX > Commons.BOARD_WIDTH - Commons.BORDER_RIGHT){
                ray.die();
            }
            else {
                ray.setX(rayX);
                ray.setY(rayY);
            }
        }

        // aliens

        for (BadSprite monster : badSprites) {
            if (!((Monster) monster).isFrozen()) {
                Random rand = new Random();
                if(((Monster) monster).getTimer() == 40) {
                    monster.setDx(rand.nextInt(-1, 2));
                    monster.setDy(rand.nextInt(-1, 2));
                    ((Monster) monster).setTimer(0);
                }
                ((Monster) monster).act();
                ((Monster) monster).setTimer(((Monster) monster).getTimer()+1);
            }
        }


        updateOtherSprites();
    }

    protected void updateOtherSprites() {
        Random generator = new Random();

        for (BadSprite monster : badSprites) {
            //int ray1 = generator.nextInt(15);
            Gosma goo = ((Monster) monster).getShoot();


            if (!((Monster) monster).isFrozen() && goo.isDestroyed()) {

                int directionX = generator.nextInt(-4,5);
                int directionY = generator.nextInt(-4,5);

                while(directionY == 0 && directionX == 0){
                    directionX = generator.nextInt(-4,5);
                    directionY = generator.nextInt(-4,5);
                }

                goo.setDx(directionX);
                goo.setDy(directionY);

                goo.setDestroyed(false);
                goo.setX(monster.getX());
                goo.setY(monster.getY());
            }

            if (players.get(0).isVisible() && !goo.isDestroyed()) {

                if (goo.getX() >= (players.getFirst().getX())
                        && goo.getX() <= (players.getFirst().getX() + Commons.WOODY_WIDTH)
                        && goo.getY() >= (players.getFirst().getY())
                        && goo.getY() <= (players.getFirst().getY() + Commons.WOODY_HEIGHT)) {

                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    goo.setDestroyed(true);
                }
            }

            if (!goo.isDestroyed()) {
                goo.setX(goo.getX() + goo.getDx());
                goo.setY(goo.getY() + goo.getDy());

                if (goo.getX() >= Commons.BOARD_WIDTH || goo.getX() <= 0 || goo.getY() <= 0) {
                    goo.setDestroyed(true);
                }
                if(goo.getX() == ray.getX() && goo.getY() == ray.getY()){
                    goo.setDestroyed(true);
                    ray.die();
                }
            }
        }
    }
}
