package fr.gilles.gwritter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.CaretListener;
import javax.swing.text.EditorKit;


public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar Menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu terminal = new JMenu("Terminal");
	private JMenu view = new JMenu("Edit");
	private JMenuBar down = new JMenuBar();
	private static JTextPane writtable= new JTextPane();
	private JScrollPane content = new JScrollPane();
	private static final JPopupMenu sous_menu = new JPopupMenu();
	private JMenu ext = new JMenu("");
	private boolean created = true,isRunning = false;
	private File currentfile = null;
	private  JMenu position = new JMenu("LiG:0 COl:0");
	private Process pc;
	private final String[] extensions = {"html","css","js","php","scss","txt","c++","cpp","h","java","py","cs","json","sql","db","c","scss","git","xml","hpp","h++","c","pro","ui","vb","vs"};
	

	
	
	 
	//Constructor of fenetre
	public Fenetre() {
		
		this.setTitle("GWRITTER");
		this.setMinimumSize(new Dimension(800,600));
		this.setSize(900,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponent();
		this.add(Menu,BorderLayout.NORTH);
		this.add(content,BorderLayout.CENTER);
		this.add(down,BorderLayout.SOUTH);
	 
	}
	public void startConsole(String s) throws IOException{
		if(created) {
			pc = Runtime.getRuntime().exec(s);
		}else {
			pc = Runtime.getRuntime().exec(s+" ",null,new File(this.currentfile.getAbsolutePath().substring(0,this.currentfile.getAbsolutePath().lastIndexOf("/"))+"/"));
		
		}
		
		setRunning(true);
		
	}
	
	public void setBgColor(Color s) {
		Fenetre.writtable.setBackground(s);
	}
	public void setFgColor(Color s) {
		Fenetre.writtable.setForeground(s);
		Fenetre.writtable.setCaretColor(s);
	}
	public void setWritableFont(Font s) {
		this.setFont(s);
	}
	public void setExt(String s) {
		this.ext.setText(s);
	}
	private void Sous_menuAddMouse(){
		sous_menu.addMouseListener(new MouseListener() {
			
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
		 
	}
	public final void SetViewsous_menu() {
		JMenuItem inverse = new JMenuItem("Inverse Color of Editor");
		JMenuItem copyall = new JMenuItem("Copy all Text");
		inverse.setName("inverse");
		copyall.setName("copy-all");
		inverse.addMouseListener(new Operation());
		copyall.addMouseListener(new Operation());
		
		sous_menu.add(inverse);
		sous_menu.addSeparator();
		sous_menu.add(copyall);
		
	}
	public final void SetTerminalsous_menu() {
		JMenuItem nt = new JMenuItem("New Terminal");
		nt.setName("new terminal");
		nt.addMouseListener(new Operation());
		sous_menu.add(nt);
	}
	public final void  resetsous_menu() {
		sous_menu.removeMouseListener(null);
		sous_menu.removeAll(); 
		sous_menu.setVisible(false);
	}
	public final void showAlert(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
	public  final void SetFilesous_menu() {
		JMenuItem fopen,fsave,Gquit;
		fopen = new JMenuItem("Open File");
		fopen.setName("open");
		fopen.addMouseListener(new Operation());
		fsave = new JMenuItem("Save File");
		fsave.setName("save");
		fsave.addMouseListener(new Operation());
		Gquit = new JMenuItem("Exit");
		Gquit.setName("exit");
		Gquit.addMouseListener(new Operation());
		
		sous_menu.add(fopen);
		sous_menu.addSeparator();
		sous_menu.add(fsave);
		sous_menu.addSeparator();
		sous_menu.add(Gquit);
		
	}
	
	
	
	public  String getOsName() {
		return System.getProperty("os.name");
	}
	//init component define the first settings of component when started
	public void initComponent() {
		this.setIconImage(new ImageIcon(getClass().getResource("/images/pages.png")).getImage());
		Fenetre.writtable.addKeyListener((KeyListener)(new Trepaint()));
		Fenetre.writtable.addCaretListener((CaretListener)(new Trepaint()));
		this.Menu.add(file);
		this.Menu.add(view); 
		this.Menu.add(terminal);
		/*
		 * 
		 */
		
		this.terminal.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				sous_menu.setInvoker(terminal);
				resetsous_menu();
				SetTerminalsous_menu();
				Sous_menuAddMouse();
				sous_menu.show(e.getComponent(),e.getComponent().getX()-e.getComponent().getWidth(),e.getComponent().getHeight());
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		/*
		 * here we add click event o
		 */
		this.file.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
					
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				sous_menu.setInvoker(file);
				resetsous_menu();
				SetFilesous_menu();
				Sous_menuAddMouse();
				sous_menu.show(e.getComponent(),e.getComponent().getX(),e.getComponent().getHeight());
			
			} 
 
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		/*
		 * 
		 */
		this.view.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				sous_menu.setInvoker(view);
				resetsous_menu();
				SetViewsous_menu();
				Sous_menuAddMouse();
				sous_menu.show(e.getComponent(),e.getComponent().getX()-e.getComponent().getWidth(),e.getComponent().getHeight());
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		/*
		 * 
		 */
		Fenetre.writtable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				resetsous_menu();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.ext.setFont(new Font("sans-serif", 9, 9));
		this.down.add(ext,BorderLayout.LINE_START);
		this.down.setFont(new Font("sans-serif", 9, 9));
		this.down.add(position,BorderLayout.LINE_END);
		Fenetre.writtable.setFont(new Font("Dialog",17,14));
		this.content.setViewportView(getWrittable());
		
		
	}
	public void setCurrentCaretPosition(String s) {
		this.position.setText(s);
	}
	public File getCurrentfile() {
		return currentfile;
	}
	public void paintComponent(Graphics g) {
		
	}
	public void setCurrentfile(File currentfile) {
		this.currentfile = currentfile;
		this.created = false;
	}
	public String[] getExtensions() {
		return extensions;
	}
	public JTextPane getWrittable() {
		return writtable;
	}
	public static void insertinArea(FileReader f) {
		try {
			writtable.read(f, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isCreated() {
		return created;
	}
	public void setCreated(boolean created,String s) {
		this.created = created;
		this.setTitle(s);
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void colorate() {
		new Highlight(Fenetre.writtable);
	}

	
	
	
}

