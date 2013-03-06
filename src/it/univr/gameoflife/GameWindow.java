package it.univr.gameoflife;

import it.univr.gameoflife.Game.GraphicGrid;

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

		game = new Game(90, 50);
		GraphicGrid label = game.new GraphicGrid("qui sopra disegneremo la griglia");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new BorderLayout());
		pane.add(createToolBar(), BorderLayout.PAGE_START); 
		pane.add(label, BorderLayout.CENTER);
		pane.add(createSlider(), BorderLayout.PAGE_END);
		this.pack();
		
	}
	
	public static void main(String[] args) {
		new GameWindow().setVisible(true);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		//TODO Aggiunta pulsanti e listener
		return toolBar;
	}
	
	private JSlider createSlider() {
		JSlider slider = new JSlider();
		//TODO Implementazione ChangeListener
		return slider;
	}

}