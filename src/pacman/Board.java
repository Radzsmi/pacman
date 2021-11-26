package pacman;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class Board extends JPanel implements ActionListener {
    ArrayList < Field > map;
    ArrayList < Integer > mymap;
    ArrayList < Ghost > ghosts;
    PacFrame pacframe;
    Pacman pac;
    Timer t;
    int points = 0;
    Board(PacFrame pacf) {
        pacframe = pacf;
        t = new Timer(1, this);
        t.start();
        ghosts = new ArrayList < > ();
        mymap = new ArrayList < Integer > ();
        loadMap();
        initMap();
    }
    public void loadMap(){
        try {
            File myObj = new File("map.txt");
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                for (String s: data.split(",")) {
                    mymap.add(Integer.parseInt(s));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Hiba a palya betoltesekor.");
        }
    }
    public boolean checkPacDead() {
    	
        for (int i = 0; i < ghosts.size(); i++) {
        if(ghosts.get(i).getField()!=null) {
            if (ghosts.get(i).getField().equals(pac.getField()) && !pac.getKiller())
                return true;
            if (ghosts.get(i).getField().equals(pac.getField()) && ghosts.get(i).getId()==2) {
            	pac.setKiller(false);
            	return true;
            }
            if(ghosts.get(i).getField().getNeig(pac.getD())!=null)
            if (ghosts.get(i).getField().getNeig(pac.getD()).equals(pac.getField()) && ghosts.get(i).getId()==2) {
            	pac.setKiller(false);
            	return true;
            }
            if (ghosts.get(i).getField().equals(pac.getField()) && pac.getKiller()) {
                ghosts.remove(i);
                pac.setKiller(false);
            }
        }
        }
        return false;
    }
    public void game(Graphics2D g2d) {
        points = pac.move() + points;
        drawPacman(g2d);
        for (int i = 0; i < ghosts.size(); i++) ghosts.get(i).move();
        drawGhosts(g2d);
        if (checkPacDead()) {
            if (pac.getLives() == 1) {
            	pac.kill();
            } else pac.die();
        }
        if(checkGameEnd()) {
        	pac.kill();
        }
    }
    public boolean checkGameEnd() {
    	for(Field f : map) {
    		if(f.getPellet()!=null) return false;
    	}
		return true;
    }
    public void initMap() {
        map = new ArrayList < > ();
        for (int i = 0; i < 26 * 26; i++) {
            if ((mymap.get(i).intValue() & 16) != 0) map.add(new Field(mymap.get(i), i, true));
            else map.add(new Field(mymap.get(i), i, false));
        }
        for (int i = 0; i < 26 * 26; i++) {
            if (i - 26 >= 0) {
                map.get(i).init(1, map.get(i - 26));
            }
            if (i + 26 <= 26 * 26 - 1) {
                map.get(i).init(4, map.get(i + 26));
            }
            if (i + 1 <= 26 * 26 - 1) map.get(i).init(3, map.get(i + 1));
            if (i - 1 >= 0) map.get(i).init(2, map.get(i - 1));
        }
        ghosts.add(new Ghost(map.get(0), 0, 0));
        ghosts.add(new Ghost(map.get(25), 25, 0));
        ghosts.add(new Ghost(map.get(26 * 25), 0, 25));
        ghosts.add(new Ghost(map.get(26 * 26 - 1), 25, 25));
        pac = new Pacman(map.get(26 * 12 + 12));
        ghosts.add(new Diablo(null, -10, -10, pac));
        map.get(0).setPellet(new HpPellet(pac));
        map.get(25).setPellet(new KillerPellet(pac));
        map.get(26 * 25).setPellet(new KillerPellet(pac));
        map.get(26 * 26 - 1).setPellet(new HpPellet(pac));
        this.setFocusable(true);
        this.addKeyListener(new KeyListener(pac,pacframe));

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 26 * 24, 26 * 24);
        drawMaze(g2d);
        drawScore(g2d);
        game(g2d);
        if(pac.getDead()) {
        	endofGame(g2d);
        	gameEnd(pacframe);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    private void drawPacman(Graphics2D g2d) {
        int m = pac.getMove();
        int xdir = 0, ydir = 0;
        if (pac.getD() == 1) {
            xdir = 0;
            ydir = -1;
        }
        if (pac.getD() == 2) {
            xdir = -1;
            ydir = 0;
        }
        if (pac.getD() == 3) {
            xdir = 1;
            ydir = 0;
        }
        if (pac.getD() == 4) {
            xdir = 0;
            ydir = 1;
        }
        if (pac.getD() == 0) {
            xdir = 0;
            ydir = 0;
        }
        if (m == 0) {
            xdir = 0;
            ydir = 0;
        }
        if (pac.getD() == 2 && m % 16 <= 8) {
            g2d.drawImage((Image) new ImageIcon("images/left.png").getImage(), pac.getX() * 24 + (24 * (-xdir)) + m * xdir,
                pac.getY() * 24 + (24 * (-ydir)) + m * ydir, this);
        } else if (pac.getD() == 3 && m % 16 <= 8) {
            g2d.drawImage((Image) new ImageIcon("images/right.png").getImage(), pac.getX() * 24 + (24 * (-xdir)) + m * xdir,
                pac.getY() * 24 + (24 * (-ydir)) + m * ydir, this);
        } else if (pac.getD() == 1 && m % 16 <= 8) {
            g2d.drawImage((Image) new ImageIcon("images/up.png").getImage(), pac.getX() * 24 + (24 * (-xdir)) + m * xdir,
                pac.getY() * 24 + (24 * (-ydir)) + m * ydir, this);
        } else if (pac.getD() == 4 && m % 16 <= 8) {
            g2d.drawImage((Image) new ImageIcon("images/down.png").getImage(), pac.getX() * 24 + (24 * (-xdir)) + m * xdir,
                pac.getY() * 24 + (24 * (-ydir)) + m * ydir, this);
        } else {
            g2d.drawImage((Image) new ImageIcon("images/pacman.png").getImage(), pac.getX() * 24 + (24 * (-xdir)) + m * xdir,
                pac.getY() * 24 + (24 * (-ydir)) + m * ydir, this);
        }
    }
    private void drawGhosts(Graphics2D g2d) {
        int xdir = 0, ydir = 0, x, y;
        for (int i = 0; i < ghosts.size(); i++) {
            if (ghosts.get(i).getDirection() == 1) {
                xdir = 0;
                ydir = -1;
            }
            if (ghosts.get(i).getDirection() == 2) {
                xdir = -1;
                ydir = 0;
            }
            if (ghosts.get(i).getDirection() == 3) {
                xdir = 1;
                ydir = 0;
            }
            if (ghosts.get(i).getDirection() == 4) {
                xdir = 0;
                ydir = 1;
            }
            if (ghosts.get(i).getMoving() == 0) {
                xdir = 0;
                ydir = 0;
            }
            x = ghosts.get(i).getX() * 24 + (24 * (-xdir)) + ghosts.get(i).getMoving() * xdir;
            y = ghosts.get(i).getY() * 24 + (24 * (-ydir)) + ghosts.get(i).getMoving() * ydir;
            if (ghosts.get(i).getId() == 1)
                g2d.drawImage((Image) new ImageIcon("images/ghost.png").getImage(), x, y, this);
            if (ghosts.get(i).getId() == 2 && ghosts.get(i).getDirection() != 0)
                g2d.drawImage((Image) new ImageIcon("images/diablo.png").getImage(), x, y, this);
            if (ghosts.get(i).getId() == 2 && ghosts.get(i).getDirection() == 0) {
                x = ghosts.get(i).getX() * 24;
                y = ghosts.get(i).getY() * 24;
                g2d.drawImage((Image) new ImageIcon("images/diablo.png").getImage(), x, y, this);
            }
        }
    }
    private void endofGame(Graphics2D g2d) {
    	Toolkit.getDefaultToolkit().sync();
        g2d.setColor(Color.yellow);
        g2d.drawString("Game over", 26*26/2-70, 300);
        g2d.drawString("Press space to continue", 26*26/2-95, 350);
    }
    private void drawScore(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + points;
        g.drawString(s, 26 * 24 / 2 + 96, 26 * 24 + 16);
        for (int i = 0; i < pac.getLives(); i++) {
            g.drawImage((Image) new ImageIcon("images/heart.png").getImage(), i * 28 + 8, 26 * 24 + 1, this);
        }
    }
    public void drawMaze(Graphics2D g2d) {
        int i = 0;
        int x, y;
        for (y = 0; y < 26 * 24; y += 24) {
            for (x = 0; x < 26 * 24; x += 24) {
                g2d.setColor(new Color(0, 72, 251));
                if (pac.getKiller()) g2d.setColor(Color.GREEN);
                g2d.setStroke(new BasicStroke(5));
                if ((mymap.get(i) == 0)) {
                    g2d.fillRect(x, y, 24, 24);
                }
                if ((mymap.get(i) & 1) != 0) {
                    g2d.drawLine(x, y, x, y + 23);
                }
                if ((mymap.get(i) & 2) != 0) {
                    g2d.drawLine(x, y, x + 23, y);
                }
                if ((mymap.get(i) & 4) != 0) {
                    g2d.drawLine(x + 23, y, x + 23,
                        y + 23);
                }
                if ((mymap.get(i) & 8) != 0) {
                    g2d.drawLine(x, y + 23, x + 23,
                        y + 23);
                }
                if (map.get(i).getPellet() != null) {
                    if (map.get(i).getPellet().getP() != 0) {
                        g2d.setColor(new Color(255, 255, 255));
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                    if (map.get(i).getPellet().getP() == 0) {
                        g2d.setColor(Color.RED);
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                    if (map.get(i).getPellet().getP() == 10) {
                        g2d.setColor(Color.GREEN);
                        g2d.fillOval(x + 10, y + 10, 6, 6);
                    }
                }
                i++;
            }
        }
    }
    public void gameEnd(PacFrame f) {
        f.gameEnd(points);
        t.stop();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}