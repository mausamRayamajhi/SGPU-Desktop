package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import DatabaseConnection.ConnectorWithDatabase;
import net.proteanit.sql.DbUtils;

public class ViewIteamDetail extends JFrame {

	private JPanel contentPane;

	private JTable table;
	private String statuslvl = "Unpaid";
	String status;
	int id;
	private Connection conn;
	private ConnectorWithDatabase db;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIteamDetail frame = new ViewIteamDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewIteamDetail() throws Exception {
		db = new ConnectorWithDatabase();

		setBounds(100, 100, 1017, 467);
		contentPane = new JPanel();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setSize(1046, 500);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 1010, 316);
		contentPane.add(scrollPane);

		table = new JTable();
		Object[] column = { "id", "Code", "Name", "Weight", "Size(inch)", "Yarn Count", "Gauge(gg)", "Making Cost(Rs)",
				"100%", "70%", "50%", "30%", "Fine Wool" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JLabel DueAmountList = new JLabel("Iteams Details");
		DueAmountList.setFont(new Font("Tahoma", Font.PLAIN, 32));
		DueAmountList.setBounds(414, 11, 322, 57);
		contentPane.add(DueAmountList);

		try {
			add();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton updateSelectID = new JButton("Close");
		updateSelectID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("closed clicked");

			}
		});
		updateSelectID.setBounds(924, 402, 96, 29);
		contentPane.add(updateSelectID);

		JButton btnAddNewIteam = new JButton("Add New Iteam");
		btnAddNewIteam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewIteam additeam = new AddNewIteam();
				additeam.setVisible(true);

			}
		});
		btnAddNewIteam.setBounds(28, 399, 140, 29);
		contentPane.add(btnAddNewIteam);
		///////////////

	}

	//////////

	/////
	public void add() throws Exception {
		conn = db.connectToDatabase();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(
				"select id,code,iteamName,weight,size,yarnCount,gauge,makingCost,hundred,seventy,fifty,thirty,fineWool from iteamdetail ");
		table.setModel(DbUtils.resultSetToTableModel(rs));

	}

}
