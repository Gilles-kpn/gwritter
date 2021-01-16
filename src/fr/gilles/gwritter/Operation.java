package fr.gilles.gwritter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public class Operation implements MouseListener{
	private FileReader reader = null;
	private FileWriter write = null;
	private final JFileChooser choose = new JFileChooser();
	@Override
	public void mouseClicked(MouseEvent e) {
		JMenuItem temp = (JMenuItem) e.getSource();
		
		switch(temp.getName()) {
			case "open":{
				this.open();
				break;
			} 
			case "save":{
				this.Save();
				break;
			}
			case "exit":{
				if(!Main.f.getWrittable().getText().isEmpty()) {
					this.Save();
				}
				Main.f.dispose(); 
				break;
			}
			case "inverse":{
				this.applystyle();
				break;
			}
			case "new terminal":{
				this.openterminal();
				break;
			}
		}
		
		
	}
	private void applystyle() {
		if(Main.f.getWrittable().getBackground() == Color.white) {
			Main.f.setBgColor(Color.BLACK);
			Main.f.setFgColor(Color.white);
		}else {
			Main.f.setBgColor(Color.white); 
			Main.f.setFgColor(Color.black);
		}
	}
	public void Save() {
		Main.f.resetsous_menu();
	
		if(Main.f.isCreated()) {
			
		
		choose.setDialogTitle("Save File AS");
		
		choose.addChoosableFileFilter(null);
		int returnval = choose.showSaveDialog(null);
		if(returnval == JFileChooser.APPROVE_OPTION ) {
			//if on text is specified like name file 
			
			try {
				Main.f.setCurrentfile(choose.getSelectedFile());
				if(choose.getSelectedFile().getPath() != null ) {
					if(!choose.getSelectedFile().getName().isEmpty()) {
						if(choose.getSelectedFile().getPath().substring(choose.getSelectedFile().getPath().lastIndexOf(".")+1)!= null) {
							write = new FileWriter(choose.getSelectedFile());
							write.write(Main.f.getWrittable().getText());
							
							write.close();
							Main.f.setExt(Main.f.getCurrentfile().getName().substring(Main.f.getCurrentfile().getName().lastIndexOf(".")+1));
						}else {
							choose.getSelectedFile().renameTo(new File(choose.getSelectedFile().getAbsolutePath()+".txt"));
							write = new FileWriter(choose.getSelectedFile());
							write.close();
							 
						}
					}else {
						Main.f.showAlert("Put a name to the file");
					}
				}else {
					Main.f.showAlert("Invalid path to file");
				}
			}catch(IOException i) {
				i.printStackTrace();
			} 
			
		}else {
			Main.f.showAlert("Something is wrong");
		}
		}else {
			try {
				write  = new FileWriter(Main.f.getCurrentfile());
				write.write(Main.f.getWrittable().getText());
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	private void open() {
		Main.f.resetsous_menu();
	
		int returnval = choose.showOpenDialog(null);
		if(returnval == JFileChooser.APPROVE_OPTION ) {
			 
			//open the file
			if(choose.getSelectedFile().isFile() && choose.getSelectedFile().canRead()) {
				boolean find = false;
				for(String element:Main.f.getExtensions()) {
					if(!choose.getSelectedFile().getName().substring(choose.getSelectedFile().getName().lastIndexOf(".")+1).equalsIgnoreCase(element)) {
						
					}else {
						find = true;
						try {
							Main.f.setCurrentfile(choose.getSelectedFile()) ;
							reader = new FileReader(Main.f.getCurrentfile());
							Fenetre.insertinArea(reader); 
							Main.f.setCreated(false,Main.f.getCurrentfile().getName() );
							Main.f.setExt(Main.f.getCurrentfile().getName().substring(Main.f.getCurrentfile().getName().lastIndexOf(".")+1));
							
						} catch (IOException i) {
							i.printStackTrace(); 
						} 
						
						break;
					}
				}
				if(!find) {
					Main.f.showAlert("File format not supported");
				}
				
				
			}else {
				Main.f.showAlert("Cannot Read the file");
			}
			
		}else {
			Main.f.showAlert("Something is wrong");
		}
	}
	private void openterminal() {
			try { 
				
				if(Pattern.compile("linux", Pattern.CASE_INSENSITIVE).matcher(Main.f.getOsName()).find()) {
					Main.f.startConsole("/usr/bin/gnome-terminal");
				}else if(Pattern.compile("windows", Pattern.CASE_INSENSITIVE).matcher(Main.f.getOsName()).find()){
					Main.f.startConsole("cmd");
					
				}else if(Pattern.compile("Mac", Pattern.CASE_INSENSITIVE).matcher(Main.f.getOsName()).find()){
					Main.f.startConsole("open -n -F -a /Applications/Utilities/Terminal.app --args ls");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
