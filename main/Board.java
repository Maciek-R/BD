package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tables.car.CarDAO;
import tables.client.ClientDAO;
import tables.hire.HireDAO;

public class Board extends JPanel{
	
	public final static int BOARD_WIDTH_PIX = 1200;
	public final static int BOARD_HEIGHT_PIX = 800;
	
	ArrayList<JButton> buttons;

	public Board(MyFrame frejm) {
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(BOARD_WIDTH_PIX, BOARD_HEIGHT_PIX));
		buttons = new ArrayList<JButton>();

		for(int i = 0 ; i < 4 ; ++i){
			buttons.add(new JButton());
		}
		for(JButton b:buttons)
			this.add(b);
		
		init();
	}
	private void init(){
		this.operateButtons();
		this.setLayout(null);
		
		buttons.get(0).setText("Klienci");
		buttons.get(1).setText("Samochody");
		buttons.get(2).setText("Wypozyczenia");
		buttons.get(3).setText("Raporty");
		
		repaint();
	}
	
	private void operateButtons(){
		buttons.get(0).setBounds(new Rectangle(200, 200, 200, 80));
		buttons.get(1).setBounds(new Rectangle(410, 200, 200, 80));
		buttons.get(2).setBounds(new Rectangle(620, 200, 200, 80));
		buttons.get(3).setBounds(new Rectangle(830, 200, 200, 80));
		
		buttons.get(0).addActionListener(ae -> {
			JAction jaAction = new JClientAction();
		});
		buttons.get(1).addActionListener(ae -> {
			JAction jaAction = new JCarAction();	
		});
		buttons.get(2).addActionListener(ae -> {
			JAction jaAction = new JHireAction();
		});
		buttons.get(3).addActionListener(ae -> {
			JReport jReport = new JReport();
		});
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}
}
