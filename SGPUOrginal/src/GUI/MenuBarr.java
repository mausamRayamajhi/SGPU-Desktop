package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import DatabaseConnection.ConnectorWithDatabase;

public class MenuBarr extends JPanel {
	private TextPannel textPannel;
	
	private ConnectorWithDatabase connection;
	
	private JFileChooser filechooser;
	

	public JMenuBar createMenuBar() {
		textPannel = new TextPannel();
		
		filechooser = new JFileChooser();
		filechooser.addChoosableFileFilter(new FileFilter());
		connection=new ConnectorWithDatabase();
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu insert = new JMenu("Insert");
		JMenu view = new JMenu("View");
		JMenu window = new JMenu("Window");
		JMenu search = new JMenu("Search");
		JMenu help = new JMenu("Help");

		// FileMenu Items
		JMenuItem ne = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem close = new JMenuItem("Close");
		JMenuItem closeAll = new JMenuItem("CloseAll");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("SaveAs");
		JMenuItem rename = new JMenuItem("Rename");
		JMenuItem refresh = new JMenuItem("Refresh");
		JMenuItem print = new JMenuItem("Print");
		JMenuItem restart = new JMenuItem("Restart");
		JMenuItem exit = new JMenuItem("Exit");

		// setPicture
		ne.setIcon(Extension.createIcon("/Icons/new16.gif"));
		open.setIcon(Extension.createIcon("/Icons/open16.gif"));
		save.setIcon(Extension.createIcon("/Icons/saveall16.gif"));
		saveAs.setIcon(Extension.createIcon("/Icons/saveas16.gif"));
		refresh.setIcon(Extension.createIcon("/Icons/refresh16.gif"));
		print.setIcon(Extension.createIcon("/Icons/print16.gif"));

		// Setting of Mnemonic
		file.setMnemonic(KeyEvent.VK_F);

		// Setting of Accelerators
		ne.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.SHIFT_MASK));
		closeAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
		rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		// exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
		// ActionEvent.ALT_MASK));

		Dimension dim = ne.getPreferredSize();
		dim.width = 110;
		ne.setPreferredSize(dim);

		file.add(ne);
		file.addSeparator();
		file.add(open);
		file.add(close);
		file.add(closeAll);
		file.addSeparator();
		file.add(save);
		file.add(saveAs);
		file.addSeparator();
		file.add(rename);
		file.add(refresh);
		file.addSeparator();
		file.add(print);
		file.addSeparator();
		file.add(restart);
		file.addSeparator();
		file.add(exit);

		// editMenu items
		JMenuItem undoTyping = new JMenuItem("Undo Typing");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem past = new JMenuItem("Past");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem selectAll = new JMenuItem("Select All");

		undoTyping.setPreferredSize(dim);

		edit.add(undoTyping);
		edit.add(redo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(past);
		edit.addSeparator();
		edit.add(delete);
		edit.add(selectAll);

		// accelator
		undoTyping.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		past.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.SHIFT_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		KeyStroke delKey = KeyStroke.getKeyStroke("DELETE");
		delete.setAccelerator(delKey);

		// pic to edit menuitems
		undoTyping.setIcon(Extension.createIcon("/Icons/undo16.gif"));
		redo.setIcon(Extension.createIcon("/Icons/redo16.gif"));
		cut.setIcon(Extension.createIcon("/Icons/cut16.gif"));
		copy.setIcon(Extension.createIcon("/Icons/copy16.gif"));
		past.setIcon(Extension.createIcon("/Icons/paste16.gif"));
		delete.setIcon(Extension.createIcon("/Icons/delete16.gif"));

		// window items
		JMenuItem prefs = new JMenuItem("Preferences");

		prefs.setPreferredSize(dim);

		window.add(prefs);

		// help items list
		JMenuItem tNt = new JMenuItem("Tips And Tricks");
		JMenuItem welcome = new JMenuItem("Welcome");
		JMenuItem checkForUpdate = new JMenuItem("Check For Update");
		JMenuItem installationDetail = new JMenuItem("Installation Detail");
		JMenuItem aboutSGPU = new JMenuItem("About SGPU");

		welcome.setPreferredSize(dim);

		// setting icon
		aboutSGPU.setIcon(Extension.createIcon("/Icons/Information16.gif"));
		tNt.setIcon(Extension.createIcon("/Icons/tipoftheday16.gif"));
		checkForUpdate.setIcon(Extension.createIcon("/Icons/do.png"));
		installationDetail.setIcon(Extension.createIcon("/Icons/Import16.gif"));
		aboutSGPU.setIcon(Extension.createIcon("/Icons/l.gif"));
		welcome.setIcon(Extension.createIcon("/Icons/welcome.gif"));
		prefs.setIcon(Extension.createIcon("/Icons/preferences16.gif"));

		/// adding itme to help
		help.add(welcome);
		help.addSeparator();
		help.add(tNt);
		help.add(checkForUpdate);
		help.add(installationDetail);
		help.addSeparator();
		help.add(aboutSGPU);

		// Indert
		JMenu ad = new JMenu("Add");
		JCheckBoxMenuItem addDetailOfProduct = new JCheckBoxMenuItem("Add Order");
		addDetailOfProduct.setSelected(true);
		ad.add(addDetailOfProduct);
		insert.add(ad);
		// ad.setPreferredSize(dim);

		filechooser = new JFileChooser();
		filechooser.addChoosableFileFilter(new FileFilter());
		
		// ActionListners for Menus
		ne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textPannel.appendText("new clicked....!! \n");

			}
		});

		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filechooser.showOpenDialog(MenuBarr.this) == JFileChooser.APPROVE_OPTION) {
					/*try {
						connection.LoadFromFile(filechooser.getSelectedFile());

						/// refresh table
						table.refresh();

					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MenuBarr.this, "could not load file", "file erroe",
								JOptionPane.ERROR_MESSAGE);
					}*/
					 System.out.println(filechooser.getSelectedFile());
				}

			}
		});
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filechooser.showSaveDialog(MenuBarr.this) == JFileChooser.APPROVE_OPTION) {
				/*	try {
						connection.saveToFile(filechooser.getSelectedFile());
						table.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MenuBarr.this, "could not save to file", "file erroe",
								JOptionPane.ERROR_MESSAGE);
					}*/
					 System.out.println(filechooser.getSelectedFile());
				}

			}
		});
		// Adding Menu to MenuBar
		menu.add(file);
		menu.add(edit);
		menu.add(insert);
		menu.add(view);
		menu.add(search);
		menu.add(window);
		menu.add(help);

		return menu;
	}

	public void setTextPannel(TextPannel textPannel) {
		this.textPannel = textPannel;

	}
}
