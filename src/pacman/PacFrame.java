package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.io.*;

import javax.swing.JFrame;

public class PacFrame extends JFrame {
	String name;
	PacFrame(String s){
		name=s;
		Board b = new Board(this);
		b.setBackground(Color.black);
		this.add(b);
		this.setBackground(Color.black);
		this.setVisible(true);
		this.setSize(new Dimension(26*24+15,26*24+80));
	}
	public void gameEnd(int points) {
		writeLeaderBoard(points);
	}
	public void writeLeaderBoard(int points)  {
	   
	        File myObj = new File("filename.txt");
	        FileWriter myWriter;
			try {
			myWriter = new FileWriter("filename.txt",true);
			BufferedWriter bw = new BufferedWriter(myWriter);
			bw.write(name+","+points+",");
			bw.close();
	        myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
