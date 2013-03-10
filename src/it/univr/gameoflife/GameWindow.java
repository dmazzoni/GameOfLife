package it.univr.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class GameWindow extends JFrame {
	
	private final Game game;
	private final GraphicGrid graphicGrid;
	private int cellSize = 10;
	private boolean started;
	private final int numOfThreads = 2;
	private int delay = 200;

	public GameWindow() {
		super("Game Of Life");
		Container pane = this.getContentPane();

		game = new Game(75, 45);
		graphicGrid = new GraphicGrid();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new BorderLayout());
		pane.add(createToolBar(), BorderLayout.PAGE_START); 
		pane.add(graphicGrid, BorderLayout.CENTER);
		pane.add(createSlider(), BorderLayout.PAGE_END);
		this.pack();
		this.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				synchronized (game) {
					final int updatedWidth = graphicGrid.getWidth() / (cellSize + 1);
					final int updatedHeight = graphicGrid.getHeight() / (cellSize + 1);
					game.resize(updatedWidth, updatedHeight);
				}
			}
		});
		
		//TEST: CREIAMO UN GLIDER
		game.getGrid().changeState(new Point(3,2));
		game.getGrid().changeState(new Point(4,3));
		game.getGrid().changeState(new Point(5,1));
		game.getGrid().changeState(new Point(5,2));
		game.getGrid().changeState(new Point(5,3));

	}
	
	public static void main(String[] args) {
		new GameWindow().setVisible(true);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		final JButton start = new JButton("Inizio");
		start.addActionListener(new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
				if (started == false) {
					started = true;
					start.setText("Stop");
					new Worker().start();
				} else {
					started = false;
					start.setText("Inizio");
				}
            }                       
		});
		toolBar.add(start);
		
		final JButton step = new JButton("Avanti");
		step.addActionListener(new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
				if (started == false) {
					game.new NextGeneration(numOfThreads);
					graphicGrid.repaint();
				}
            }                       
		});
		toolBar.add(step);
		
		toolBar.addSeparator();

		return toolBar;
	}
	
	private JSlider createSlider() {
		final JSlider slider = new JSlider(0, 475, 300);
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				delay = 500 - slider.getValue();
			}
		});
		return slider;
	}
	
	private class GraphicGrid extends JLabel {
		
		private GraphicGrid() {
			super();
			this.setPreferredSize(new Dimension(game.getSize().width * (cellSize + 1), game.getSize().height * (cellSize + 1)));
		}
	
		@Override
		public void paintComponent(Graphics g) {
			Grid gameGrid = game.getGrid();
			Dimension gameSize = game.getSize();
			for (int i = 0; i < gameSize.height; i++)
				for (int j = 0; j < gameSize.width; j++) {
					g.setColor(gameGrid.isAlive(i, j) ? Color.YELLOW : Color.GRAY);
					g.fillRect((cellSize + 1) * j, (cellSize + 1) * i, cellSize, cellSize);
				}
		}
	}
	
	private class Worker extends Thread {
		
		@Override
		public void run() {
			while (started) {
				synchronized (game) {
					game.new NextGeneration(numOfThreads);
					graphicGrid.repaint();
				}
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) { }
			}
		}
	}

}