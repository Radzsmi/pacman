package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TwoPac {
	JFrame fr;
	JTextField f;
	TwoPac(){
		fr=new JFrame();
		fr.setSize(new Dimension(300,200));
		f = new JTextField("Your name goes here");
		JLabel l = new JLabel("Player name:");
		JButton b = new JButton("Submit");
		b.addActionListener(new NameDoneListener());
		l.setFont(new Font("Verdana", Font.PLAIN, 18));
		l.setForeground(Color.white);
		f.setSize(new Dimension(100,30));
		JPanel p1= new JPanel();
		JPanel p2= new JPanel();
		JPanel p3= new JPanel();
		JPanel p4= new JPanel();
		p3.add(b);
		p1.setBackground(Color.black);
		p2.setBackground(Color.black);
		p3.setBackground(Color.black);
		p4.setBackground(Color.black);
		p2.add(f);
		p1.add(l);
		p1.setBounds(0,0,300,50);
		p2.setBounds(0,50,300,50);
		p3.setBounds(0,100,300,50);
		p4.setBounds(0,150,300,50);
		fr.add(p1);
		fr.add(p2);
		fr.add(p3);
		fr.add(p4);
		fr.setVisible(true);
	}
	public class NameDoneListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new PacFrame(f.getText());
			fr.dispose();
		}
		
	}
}
