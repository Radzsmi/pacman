package pacman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.Comparator;
public class LeaderBoard {
	JFrame f;
	LeaderBoard(){
			f = new JFrame("LeaderBoard");
		
	      JPanel panel = new JPanel(new BorderLayout());
	      JPanel p = new JPanel();
	      ArrayList<String> lb=new ArrayList<>();
	      lb=getData();
	      System.out.println(lb);
	      JButton b= new JButton("Exit");
	      b.addActionListener(new ExitLeader());
	      JList<String> list = new JList<String>(lb.toArray(new String[lb.size()]));
	      JScrollPane scrollPane = new JScrollPane();
	      scrollPane.setViewportView(list);
	      list.setLayoutOrientation(JList.VERTICAL);
	      panel.add(scrollPane,BorderLayout.CENTER);
	      panel.add(b,BorderLayout.SOUTH);
	      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      f.add(panel);
	      f.setSize(500, 250);
	      f.setLocationRelativeTo(null);
	      f.setVisible(true);
	}
	public ArrayList<String> getData() {
		ArrayList<String> arr = new ArrayList<String>();
		TreeMap<String,Integer> eredmenyek=new TreeMap<>();
		try {
	        File myObj = new File("filename.txt");
	        Scanner myReader = new Scanner(myObj);
	        String all="";
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          all=all+data;
	        }
	        myReader.close();
	        
	        for(int i=0;i<all.split(",").length;i++){
	        	if(eredmenyek.get(all.split(",")[i])==null) {
	        	eredmenyek.put(all.split(",")[i], Integer.parseInt(all.split(",")[i+1]));
	        	}else {
	        		if(eredmenyek.get(all.split(",")[i])<Integer.parseInt(all.split(",")[i+1])) {
	        		eredmenyek.remove(all.split(",")[i]);
	        		eredmenyek.put(all.split(",")[i], Integer.parseInt(all.split(",")[i+1]));
	        		}
	        	}
	        	i=i+1;
	       }
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		for(String s : eredmenyek.keySet()) {
			arr.add(eredmenyek.get(s)+" - Player name: "+s);
		}
	    Comparator<String> cmp = new Comparator<String>() {
	        public int compare(String o1, String o2) {
	          return (Integer.valueOf(o1.split(" - ")[0]).compareTo(Integer.valueOf(o2.split(" - ")[0])))*-1;
	        }
	    };
		Collections.sort(arr,cmp);
		
		return arr;
	}
	public class ExitLeader implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			
		}
		
	}
}
