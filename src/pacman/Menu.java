package pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class Menu implements ActionListener {
    JFrame f;
    Menu() {
        int x = 0;
        f = new MyFrame();
        f.setTitle("Pacman");
        ArrayList < JPanel > panels = new ArrayList < JPanel > ();
        for (int i = 0; i < 7; i++) panels.add(new JPanel());
        for (int i = 0; i < 7; i++) {
            panels.get(i).setBounds(0, x + 100, 700, 60);
            panels.get(i).setBackground(Color.black);
            x = x + 60;
        }
        JButton b = new JButton("Game");
        b.addActionListener(new PlayGameListener());
        JButton b2 = new JButton("LeaderBoard");
        JButton b3 = new JButton("Exit");
        b2.addActionListener(new LeaderListener());
        b.setFont(new Font("Verdana", Font.PLAIN, 18));
        b2.setFont(new Font("Verdana", Font.PLAIN, 18));
        b3.setFont(new Font("Verdana", Font.PLAIN, 18));
        b.setPreferredSize(new Dimension(120, 40));
        b2.setPreferredSize(new Dimension(160, 40));
        b3.setPreferredSize(new Dimension(120, 40));
        b3.addActionListener(new ExitListener());
        panels.get(0).add(b);
        panels.get(1).add(b2);
        panels.get(2).add(b3);
        for (int i = 0; i < 7; i++) f.add(panels.get(i));
        f.setSize(700, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        String comStr = ae.getActionCommand();
        System.out.println(comStr + " Selected");
    }
    public class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
        }

    }
    public class PlayGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            new TwoPac();
        }
    }
    public class LeaderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new LeaderBoard();
        }
    }
    public class MyFrame extends JFrame {
        Image img = Toolkit.getDefaultToolkit().getImage("images/pacmanlogo.jpg");
        public MyFrame() {
            this.setContentPane(new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    this.setBackground(Color.black);
                    g.drawImage(img, 200, 0, null);
                }
            });
            pack();
            setVisible(true);
        }
    }
}