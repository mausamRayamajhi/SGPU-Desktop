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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.StringUtils;

import DatabaseConnection.ConnectorWithDatabase;
import net.proteanit.sql.DbUtils;

public class ViewDueAmount extends JFrame {

	private JPanel contentPane;

	private JTable table;
	private String statuslvl = "Unpaid";
	String status;
	int id;
	private Connection conn;
	private ConnectorWithDatabase db;
	private JTextField textField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField totalField;
	private JTextField dueAmountField;
	private JTextField payAmountField;
	JComboBox comboBox;
	int n;
	int j;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDueAmount frame = new ViewDueAmount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewDueAmount() throws Exception {

		setBounds(100, 100, 1017, 467);
		contentPane = new JPanel();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		db = new ConnectorWithDatabase();

		setSize(1000, 500);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 92, 677, 279);
		contentPane.add(scrollPane);

		table = new JTable();
		Object[] column = { "id", "Name", "Quantity", "price", "Total", "Due AMount", "Status" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
	
		  try { add(); } catch (SQLException e2) {
			  // TODO Auto-generated catch
		
		 }
	
		JButton refreshAgain = new JButton("Refresh");
		refreshAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				nameField.setText("");

				priceField.setText("");
				totalField.setText("");
				dueAmountField.setText("");
				payAmountField.setText("0");
				comboBox.removeAllItems();
			
				 try { add(); } catch (Exception e1) { // TODO Auto-generated
				 }
		
			}
		});
		refreshAgain.setBounds(820, 402, 109, 29);
		contentPane.add(refreshAgain);

		JLabel DueAmountList = new JLabel("Due Amount List");
		DueAmountList.setFont(new Font("Tahoma", Font.PLAIN, 32));
		DueAmountList.setBounds(427, 19, 322, 57);
		contentPane.add(DueAmountList);

		JLabel lblUserId = new JLabel("User ID : ");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserId.setBounds(452, 400, 63, 29);
		contentPane.add(lblUserId);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(515, 400, 96, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();
				if ((key >= eve.VK_0 && key <= eve.VK_9) || (key >= eve.VK_NUMPAD0 && key <= eve.VK_NUMPAD9)
						|| key == KeyEvent.VK_BACK_SPACE) {
					textField.setEditable(true);
					textField.setBackground(Color.WHITE);

				} else {

					textField.setEditable(false);
					textField.setBackground(Color.WHITE);

				}
				if (key==eve.VK_ENTER) {
					updateFunction();
				}
			}

		});

		JButton updateSelectID = new JButton("Select ID");
		updateSelectID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFunction();

			}
		});
		updateSelectID.setBounds(632, 400, 96, 29);
		contentPane.add(updateSelectID);

		JLabel Name = new JLabel("Name :");
		Name.setFont(new Font("Dialog", Font.PLAIN, 14));
		Name.setBounds(61, 91, 63, 29);
		contentPane.add(Name);

		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(106, 93, 127, 29);
		contentPane.add(nameField);

		JLabel qualityIteams = new JLabel("Iteams :");
		qualityIteams.setFont(new Font("Dialog", Font.PLAIN, 14));
		qualityIteams.setBounds(54, 135, 63, 29);
		contentPane.add(qualityIteams);

		JLabel price = new JLabel("Total Price :");
		price.setFont(new Font("Dialog", Font.PLAIN, 14));
		price.setBounds(26, 181, 89, 29);
		contentPane.add(price);

		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setColumns(10);
		priceField.setBounds(106, 179, 127, 29);
		contentPane.add(priceField);

		JLabel total = new JLabel("Advance :");
		total.setFont(new Font("Dialog", Font.PLAIN, 14));
		total.setBounds(38, 221, 69, 29);
		contentPane.add(total);

		totalField = new JTextField();
		totalField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalField.setColumns(10);
		totalField.setBounds(106, 220, 127, 29);
		contentPane.add(totalField);

		JLabel dueAmount = new JLabel("Due Amount :");
		dueAmount.setFont(new Font("Dialog", Font.PLAIN, 14));
		dueAmount.setBounds(18, 262, 109, 29);
		contentPane.add(dueAmount);

		dueAmountField = new JTextField();
		dueAmountField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dueAmountField.setColumns(10);
		dueAmountField.setBounds(107, 264, 127, 29);
		contentPane.add(dueAmountField);

		JLabel status = new JLabel("Status :");
		status.setFont(new Font("Dialog", Font.PLAIN, 14));
		status.setBounds(58, 342, 63, 29);
		contentPane.add(status);

		JButton updatebtn = new JButton("Update");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					update();
					add();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		updatebtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					update();
					add();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		updatebtn.setBounds(112, 398, 109, 29);
		contentPane.add(updatebtn);

		JLabel paidAmount = new JLabel("Pay Amount :");
		paidAmount.setFont(new Font("Dialog", Font.PLAIN, 14));
		paidAmount.setBounds(20, 300, 109, 29);
		contentPane.add(paidAmount);

		payAmountField = new JTextField();
		payAmountField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		payAmountField.setColumns(10);
		payAmountField.setBounds(109, 302, 127, 29);
		contentPane.add(payAmountField);
		payAmountField.setText("0");

		/// alignment
		nameField.setHorizontalAlignment(JTextField.CENTER);
		priceField.setHorizontalAlignment(JTextField.CENTER);
		totalField.setHorizontalAlignment(JTextField.CENTER);
		dueAmountField.setHorizontalAlignment(JTextField.CENTER);
		payAmountField.setHorizontalAlignment(JTextField.CENTER);

		payAmountField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if ((key >= e.VK_0 && key <= e.VK_9) || (key >= e.VK_NUMPAD0 && key <= e.VK_NUMPAD9)
						|| (key == KeyEvent.VK_BACK_SPACE) || (key == 110)) {
					payAmountField.setEditable(true);
					payAmountField.setBackground(Color.WHITE);

				} else {

					payAmountField.setEditable(false);
					payAmountField.setBackground(Color.WHITE);

				}
			}
		});

		nameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if ((key >= 65 && key <= 90) || key == KeyEvent.VK_SHIFT || key == KeyEvent.VK_BACK_SPACE
						|| key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CAPS_LOCK) {
					nameField.setEditable(true);
					nameField.setBackground(Color.WHITE);

				} else {

					nameField.setEditable(false);
					nameField.setBackground(Color.WHITE);

				}
			}

		});
		JCheckBox chckbxPaid = new JCheckBox("Paid");
		chckbxPaid.setBounds(108, 343, 97, 23);
		contentPane.add(chckbxPaid);

		JCheckBox chckbxUnpaid = new JCheckBox("Unpaid");
		chckbxUnpaid.setBounds(108, 366, 97, 23);
		contentPane.add(chckbxUnpaid);

		chckbxUnpaid.setSelected(true);

		comboBox = new JComboBox();
		comboBox.setBounds(106, 141, 127, 27);
		contentPane.add(comboBox);
		chckbxPaid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxPaid.isSelected()) {
					statuslvl = "Paid";
					chckbxUnpaid.setSelected(false);
				}

			}
		});
		chckbxUnpaid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxUnpaid.isSelected()) {
					statuslvl = "UnPaid";
					chckbxPaid.setSelected(false);
				}

			}
		});
		priceField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				priceField.setEditable(false);
				priceField.setBackground(Color.WHITE);

			}
		});
		totalField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				totalField.setEditable(false);
				totalField.setBackground(Color.WHITE);

			}
		});
		dueAmountField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				dueAmountField.setEditable(false);
				dueAmountField.setBackground(Color.WHITE);

			}
		});
		///////////////

	}

	//////////

	/////
	public void add() throws Exception {
		conn = db.connectToDatabase();
		Statement st = conn.createStatement();
		int count = 0;
		ResultSet rs = st.executeQuery(
				"select CustomerID,Name,Total,Advance,DueAmount,Status from due_amount_view where dueAmount>0");

		table.setModel(DbUtils.resultSetToTableModel(rs));

		// put table column text in center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int j = 0; j < 5; j++) {
			table.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);

		}

	}

	public void update() throws SQLException {

		String name = nameField.getText();

		int price = Integer.parseInt(priceField.getText());
		int total = Integer.parseInt(totalField.getText());
		float dueAmount = Float.parseFloat(dueAmountField.getText());
		float totalDueAmount = dueAmount - Float.parseFloat(payAmountField.getText());
		if (totalDueAmount == 0) {
			statuslvl = "Paid";
		}
		String status = statuslvl;
		String updatesql = "update orderdetails set Total=?,Advance=?,DueAmount=?,Status=? where CustomerID=?";
		PreparedStatement updateStmet = conn.prepareStatement(updatesql);

		updateStmet.setInt(1, price);
		updateStmet.setInt(2, total);
		updateStmet.setFloat(3, totalDueAmount);
		updateStmet.setString(4, status);
		///

		updateStmet.setInt(5, id);

		// error
		updateStmet.executeUpdate();
	}

	public void updateFunction() {

		PreparedStatement stat1;
		String sql;

		try {

			sql = "select Name,Total,Advance,DueAmount,Status ,SelectedIteams from due_amount_view where CustomerID=?";

			stat1 = conn.prepareStatement(sql);
			id = Integer.parseInt(textField.getText());
			stat1.setInt(1, id);

			ResultSet rs = stat1.executeQuery();
			comboBox.removeAllItems();
			while (rs.next()) {

				String iteamName = rs.getString("SelectedIteams");
				j = 1;

				for (int i = 0; i < iteamName.length(); i++) {

					if (iteamName.charAt(i) == ',') {

						comboBox.addItem(iteamName.substring(j, i));
						j = i + 1;

					}

					if (i + 1 == iteamName.length()) {
						comboBox.addItem(iteamName.substring(j, i));
					}

				}

				String name = rs.getString(1);

				String name1 = rs.getString(2);
				status = rs.getString(5);

				nameField.setText(rs.getString(1));

				priceField.setText(rs.getString(2));
				totalField.setText(rs.getString(3));
				dueAmountField.setText(rs.getString(4));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
