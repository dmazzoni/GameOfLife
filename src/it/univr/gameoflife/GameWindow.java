package it.univr.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class GameWindow extends JFrame {
	
	private final Game game;
	private final GraphicGrid graphicGrid;

	public GameWindow() {
		super("Game Of Life");
		Container pane = this.getContentPane();

		game = new Game(90, 50);
		graphicGrid = new GraphicGrid();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new BorderLayout());
		pane.add(createToolBar(), BorderLayout.PAGE_START); 
		pane.add(graphicGrid, BorderLayout.CENTER);
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
	
	private class GraphicGrid extends JLabel {
		
		private GraphicGrid() {
			super();
			this.setBackground(Color.GRAY);
			this.setOpaque(true);
			this.setPreferredSize(new Dimension(720, 400));
		}
	
		private void paintGrid() {
			//TODO
		}
	}

}