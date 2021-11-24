package pacman;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new Board());
		f.setSize(26*24+15,26*24+80);
		f.setVisible(true);
	}
}
