package fr.gilles.gwritter;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;

public class Trepaint implements KeyListener,CaretListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		Main.f.colorate();
		if(!Main.f.isCreated()) {
			new Operation().Save();
		}
		
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		
	}

	
	

}
