package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;

import DataBase.Database;
import DatabaseConnection.ConnectorWithDatabase;
import GUI.MainController2;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;

public class UserLogin implements ActionListener {
	private int i = 0;
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private Database database;
	private com.mysql.jdbc.Connection con;
	private ConnectorWithDatabase connec;
	Timer t = new Timer(20, this);
	JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					UserLogin window = new UserLogin();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLogin() {
		
		connec = new ConnectorWithDatabase();

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
		} catch (Exception e) {
			System.out.println("Cannot load Look And Feel");
		}
		frame = new JFrame();
		frame.setForeground(Color.WHITE);
		frame.setBounds(500, 200, 444, 285);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		database = new Database();

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPUOriginal\\bin\\Icons\\log.png"));
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					adminLogin();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});
		btnNewButton.setBounds(203, 179, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPUOriginal\\bin\\Icons\\can.png"));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		btnNewButton_1.setBounds(307, 180, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblShreeGaneshPasmina = new JLabel("Shree Ganesh Pasmina Yudhog");
		lblShreeGaneshPasmina.setForeground(Color.WHITE);
		lblShreeGaneshPasmina.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblShreeGaneshPasmina.setBounds(30, 11, 381, 39);
		frame.getContentPane().add(lblShreeGaneshPasmina);

		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setForeground(Color.WHITE);
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserLogin.setBounds(169, 50, 97, 31);
		frame.getContentPane().add(lblUserLogin);

		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(169, 92, 97, 31);
		frame.getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(177, 134, 89, 19);
		frame.getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(264, 99, 130, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String name=textField.getText();
				
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Type UserName for Login");
				}
				else if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						adminLogin();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBounds(265, 134, 127, 23);
		frame.getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("F:\\eclipse workspace\\SGPUOrginal\\src\\Icons\\l_opt.png"));
		lblNewLabel.setBounds(10, 61, 151, 141);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1
				.setIcon(new ImageIcon("F:\\eclipse workspace\\SGPUOrginal\\src\\Icons\\444.jpg"));
		lblNewLabel_1.setBounds(0, 0, 428, 216);
		frame.getContentPane().add(lblNewLabel_1);

		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		progressBar.setBounds(0, 217, 428, 18);
		frame.getContentPane().add(progressBar);
	}

	public void adminLogin() throws Exception {
		con= connec.connectToDatabase();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from adminuser");
		// System.out.println(rs);
		while (rs.next()) {
			if (rs.getString(2).equals(textField.getText())
					&& rs.getString(3).equals(String.valueOf(passwordField.getPassword()))) {
				JOptionPane.showMessageDialog(null, "UserName & Password is Correct");
				connec.setFlag(1);
				
				progressBar.setVisible(true);
				t.start();
			
				break;
			} else {
				connec.setFlag(0);
				JOptionPane.showMessageDialog(null, "UserName & Password is Incorrect");
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (i == 100) {
			if (connec.getFlag() == 1) {
		
		
					MainController2 main2=new MainController2();
					main2.setVisible(true);
					JOptionPane.showMessageDialog(null, "Sucessful login");
					t.stop();
					frame.dispose();
				
			
				
			} else {
				
			}

		}

		progressBar.setString(String.format("%d%% loading complete", i * 100 / progressBar.getMaximum()));
		i++;
		progressBar.setValue(i);

	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
