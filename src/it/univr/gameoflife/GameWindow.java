package it.univr.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.*;

public class GameWindow extends JFrame {
	
	private final Game game;
	private final GraphicGrid graphicGrid;
	private int cellSize;

	public GameWindow() {
		super("Game Of Life");
		Container pane = this.getContentPane();

		game = new Game(90, 50);
		graphicGrid = new GraphicGrid();
		cellSize = 7;
		
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
			this.setPreferredSize(new Dimension(720, 400));
		}
	
		@Override
		public void paintComponent(Graphics g) {
			Grid gameGrid = game.getGrid();
			Dimension gameSize = game.getSize();
			for (int i = 0; i < gameSize.height; i++)
				for (int j = 0; j < gameSize.width; j++) {
					g.setColor(gameGrid.isAlive(new Point(i,j)) ? Color.YELLOW : Color.GRAY);
					g.fillRect((cellSize + 1) * j, (cellSize + 1) * i, cellSize, cellSize);
				}
		}
	}

}