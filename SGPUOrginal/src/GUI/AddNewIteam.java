package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

import DatabaseConnection.ConnectorWithDatabase;
import javafx.scene.text.Text;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddNewIteam extends JFrame {

	private JPanel contentPane;
	private Connection conn;
	private ConnectorWithDatabase db;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblWeight;
	private JTextField textField_2;
	private JLabel lblSize;
	private JTextField textField_3;
	private JLabel lblYarnCount;
	private JTextField textField_4;
	private JLabel lblGauge;
	private JTextField textField_5;
	private JLabel lblMakingCost;
	private JTextField textField_6;
	private JLabel lblFineWool;
	private JTextField textField_7;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private Object iteamCode;
	private String iteamName;
	private int weight;
	private Object size;
	private Object yarnCount;
	private int gauge;
	private int makingCost;
	private int hundred;
	private int seventy;
	private int fifty;
	private int thirty;
	private int fineWool;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewIteam frame = new AddNewIteam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddNewIteam() {

		setBounds(200, 10, 310, 696);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		db = new ConnectorWithDatabase();

		JLabel lblAddNewIteam = new JLabel("Add New Iteam");
		lblAddNewIteam.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblAddNewIteam.setBounds(86, 11, 181, 60);
		getContentPane().add(lblAddNewIteam);

		JLabel lblNewLabel = new JLabel("Iteam Code :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(14, 90, 91, 29);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(99, 91, 167, 29);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblIteamName = new JLabel("Iteam Name :");
		lblIteamName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIteamName.setBounds(14, 129, 91, 29);
		getContentPane().add(lblIteamName);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 131, 167, 29);
		getContentPane().add(textField_1);

		lblWeight = new JLabel("Weight :");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeight.setBounds(42, 167, 91, 29);
		getContentPane().add(lblWeight);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(99, 168, 167, 29);
		getContentPane().add(textField_2);

		lblSize = new JLabel("Size :");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSize.setBounds(62, 207, 91, 29);
		getContentPane().add(lblSize);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(99, 208, 167, 29);
		getContentPane().add(textField_3);

		lblYarnCount = new JLabel("Yarn Count :");
		lblYarnCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYarnCount.setBounds(16, 247, 91, 29);
		getContentPane().add(lblYarnCount);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(99, 248, 167, 29);
		getContentPane().add(textField_4);

		lblGauge = new JLabel("Gauge :");
		lblGauge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGauge.setBounds(47, 287, 91, 29);
		getContentPane().add(lblGauge);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(99, 288, 167, 29);
		getContentPane().add(textField_5);

		lblMakingCost = new JLabel("Making Cost :");
		lblMakingCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMakingCost.setBounds(14, 330, 91, 29);
		getContentPane().add(lblMakingCost);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(99, 331, 167, 29);
		getContentPane().add(textField_6);

		lblFineWool = new JLabel("Fine Wool :");
		lblFineWool.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFineWool.setBounds(14, 370, 91, 29);
		getContentPane().add(lblFineWool);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(99, 371, 167, 29);
		getContentPane().add(textField_7);

		label = new JLabel("100% :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(50, 409, 91, 29);
		getContentPane().add(label);

		label_1 = new JLabel("70% :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(57, 449, 91, 29);
		getContentPane().add(label_1);

		label_2 = new JLabel("50% :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(55, 492, 91, 29);
		getContentPane().add(label_2);

		label_3 = new JLabel("30% :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(54, 532, 91, 29);
		getContentPane().add(label_3);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(99, 533, 167, 29);
		getContentPane().add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(99, 493, 167, 29);
		getContentPane().add(textField_9);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(99, 450, 167, 29);
		getContentPane().add(textField_10);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(99, 410, 167, 29);
		getContentPane().add(textField_11);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addNewIteam();
					JOptionPane.showMessageDialog(AddNewIteam.this, "New Iteam Added.", "Iteam Added",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(AddNewIteam.this, "Iteam Not Added.", " Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(120, 580, 104, 29);
		getContentPane().add(btnAdd);

		JButton btnAddnew = new JButton("AddNew");
		btnAddnew.setVisible(false);
		btnAddnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
			}
		});
		btnAddnew.setBounds(120, 621, 104, 29);
		getContentPane().add(btnAddnew);

	}

	public void addNewIteam()  {

		try {

			iteamCode = textField.getText();
			iteamName = textField_1.getText();
			weight = Integer.parseInt(textField_2.getText());
			size = textField_3.getText();
			yarnCount = textField_4.getText();
			gauge = Integer.parseInt(textField_5.getText());
			makingCost = Integer.parseInt(textField_6.getText());
			fineWool = Integer.parseInt(textField_7.getText());
			hundred = Integer.parseInt(textField_11.getText());
			seventy = Integer.parseInt(textField_10.getText());
			fifty = Integer.parseInt(textField_9.getText());
			thirty = Integer.parseInt(textField_8.getText());

			System.out.println(yarnCount);
			conn = db.connectToDatabase();
			String updatesql = "insert into iteamdetail (code,iteamName,weight,size,yarnCount,gauge,makingCost,Hundred,Seventy,Fifty,Thirty,fineWool) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(updatesql);

			pst.setObject(1, iteamCode);
			pst.setString(2, iteamName);
			pst.setInt(3, weight);
			pst.setObject(4, size);
			pst.setObject(5, yarnCount);
			pst.setInt(6, gauge);
			pst.setInt(7, makingCost);
			pst.setInt(8, hundred);
			pst.setInt(9, seventy);
			pst.setInt(10, fifty);
			pst.setInt(11, thirty);
			pst.setInt(12, fineWool);

			int insertSucessful = pst.executeUpdate();
			if (insertSucessful >= 1) {
				System.out.println("insertion sucess");

			} else {
				System.out.println("insertion failed");
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e);
		}
		finally {
			try {
				 pst.close();
				
				conn.close();
				System.out.println("connection close for new team insert");
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "Connection Not Closed for new team insert");
			}
		}
		
		
	}
}
