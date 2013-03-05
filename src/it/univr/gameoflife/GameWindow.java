package it.univr.gameoflife;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.*;

public class GameWindow extends JFrame {

	public GameWindow() {
		super("Game Of Life");
		Container pane = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new BorderLayout());
		pane.add(createToolbar(), BorderLayout.PAGE_START); 
		pane.add(createGridBackground(), BorderLayout.CENTER);
		pane.add(createSlider(), BorderLayout.PAGE_END);
		this.pack();
		paintGrid();
	}

	private JToolBar createToolbar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private JLabel createGridBackground() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private JSlider createSlider() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void paintGrid() {
		// TODO Auto-generated method stub
		
	}

}