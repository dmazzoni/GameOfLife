package it.univr.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class GameWindow extends JFrame {
	
	private final Game game;

	public GameWindow() {
		super("Game Of Life");
		Container pane = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new BorderLayout());
		pane.add(createToolBar(), BorderLayout.PAGE_START); 
		pane.add(createGridBackground(), BorderLayout.CENTER);
		pane.add(createSlider(), BorderLayout.PAGE_END);
		this.pack();
		
		game = new Game(90, 50);
		paintGrid();
	}
	
	public static void main(String[] args) {
		new GameWindow().setVisible(true);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		//TODO Aggiunta pulsanti e listener
		return toolBar;
	}
	
	private JLabel createGridBackground() {
		JLabel label = new JLabel("qui sopra disegneremo la griglia");
		label.setBackground(Color.GRAY);
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(720, 400));
		return label;
	}
	
	private JSlider createSlider() {
		JSlider slider = new JSlider();
		//TODO Implementazione ChangeListener
		return slider;
	}
	
	private void paintGrid() {
		// TODO Auto-generated method stub	
	}

}