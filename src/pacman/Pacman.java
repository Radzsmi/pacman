package pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pacman extends Thing {
    int direction = 1, moving = 0, lives = 3, killercount = 0;
    Field startingF;
    boolean playing = false, killer, staying = false,dead=false;
    Pacman(Field fp) {
        startingF = fp;
        f = fp;
        y = 12;
        x = 12;
        killer = false;
    }
    public boolean getKiller() {
        return killer;
    }
    public boolean getDead() {
    	return dead;
    }
    public void kill() {
    	dead=true;
    }
    public void setKiller(boolean k) {
        if (k == true) killercount = 400;
        killer = k;
    }
    public int getMove() {
        return moving;
    }
    public void setD(int d) {
        direction = d;
    }
    public int getD() {
        return direction;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int l) {
        lives = l;
    }
    public boolean getStaying() {
        return staying;
    }
    public boolean getGameState() {
        return playing;
    }
    public void setGameState(boolean s) {
        playing = s;
    }
    public void die() {
        lives--;
        f = startingF;
        y = 12;
        x = 12;
    }
    public int move() {
        if (killercount > 0) killercount--;
        if (killercount == 0) killer = false;
        if (moving == 0) {
            if (f.checkMove(direction)) {
                if (direction == 1) y--;
                if (direction == 2) x--;
                if (direction == 3) x++;
                if (direction == 4) y++;
                f = f.getNeig(direction);
                moving++;
                staying = false;
            } else
                staying = true;
        } else {
            moving++;
            if (moving == 24) {
                moving = 0;
                return f.removePellet();
            }
        }
        return 0;
    }

}