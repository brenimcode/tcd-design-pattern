package freezemonsters.sprite;

import spriteframework.sprite.Player;
import spriteframework.sprite.PlayerFactory;

public class PlayerFactoryFreezeMonsters implements PlayerFactory {
    public PlayerFactoryFreezeMonsters(){}
    public Player createPlayer(){

        return new PlayerFreezeMonsters();
    }
}
