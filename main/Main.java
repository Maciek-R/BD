package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {

			public void run() {	
				MyFrame mainFrame = new MyFrame();
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setVisible(true);
			}
		});
	}
}

class MyFrame extends JFrame{
	
	public MyFrame(){
		setTitle("BD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		Board board = new Board(this);
		add(board);
		pack();
	}
}
