package GUI;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchingFrame extends JFrame {

	private JPanel contentPane;
	public static JTextField searchField;
	private JButton Search;
	public static JComboBox searchCatrgory;
private AllMethodsClass allMethods;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchingFrame frame = new SearchingFrame();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchingFrame() {
		super("Search");
		allMethods=new AllMethodsClass();
		setBounds(450, 200, 448, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		 searchCatrgory = new JComboBox();
		 searchCatrgory.setModel(new DefaultComboBoxModel(new String[] {"Name", "Gender", "Contact", "Address", "Country", "DueAmount", "Status"}));
		searchCatrgory.setBounds(10, 23, 106, 20);
		panel.add(searchCatrgory);
		
		searchField = new JTextField();
		searchField.setBounds(123, 23, 189, 20);
		panel.add(searchField);
		searchField.setColumns(10);
		searchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				AllMethodsClass methods=new AllMethodsClass();
		 		methods.searching();
			}
		});
		 Search = new JButton("Search");
		 Search.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		AllMethodsClass methods=new AllMethodsClass();
		 		methods.searching();
		 	}
		 });
		Search.setBounds(325, 22, 89, 23);
		panel.add(Search);
		
	}

}
