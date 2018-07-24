package GUI;

import java.awt.Color;
import java.awt.Dimension;
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
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import DatabaseConnection.ConnectorWithDatabase;
import net.proteanit.sql.DbUtils;

public class SelectIteamPageToBuy extends JFrame {

	private JPanel contentPane;

	private ViewIteamDetail iteamdetail = new ViewIteamDetail();
	private JTable table;
	private JPopupMenu popmenu;
	
	private int id;
	private Object code;
	int btnclicked = 0;
	int catCelected;
	public String[] iteamNameList;
	private int total;
	int rowCount;
	int sum = 0;
	private Connection conn;
	private ConnectorWithDatabase db;

	private String[] totalArray1;
	private String[] totalArray2;
	private String[] totalArray3;
	private String[] totalArray4;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField hundredField;
	private JTextField textField_10;
	private JTextField seventyField;
	private JTextField fiftyField;
	private JTextField thirtyField;
	private JTable table_1;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField quantityField;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JTextField textField_14;

	private PreparedStatement pst;
	private String sql;
	private ResultSet rs;
	private Statement st;

	private int length;
	char key;
	JButton addBtn;
	private ButtonGroup buttonGroup;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectIteamPageToBuy frame = new SelectIteamPageToBuy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectIteamPageToBuy() throws Exception {

		db = new ConnectorWithDatabase();

		setBounds(100, 10, 1200, 710);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		popmenu = new JPopupMenu();
		JMenuItem deleterow = new JMenuItem("Delete Row");
		popmenu.add(deleterow);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(10, 75, 1174, 585);
		contentPane.add(splitPane);

		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBounds(0, 0, 917, 583);
		panel.add(splitPane_1);

		JPanel panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 905, 243);
		panel_2.add(scrollPane_1);

		buttonGroup = new ButtonGroup();
		table_1 = new JTable();
		Object[] column = { "id", "Code", "Name", "Weight", "Size(inch)", "Yarn Count", "Gauge(gg)", "Making Cost(Rs)",
				"100%", "70%", "50%", "30%", "Fine Wool" };
		DefaultTableModel modell = new DefaultTableModel();
		modell.setColumnIdentifiers(column);
		table_1.setModel(modell);
		scrollPane_1.setViewportView(table_1);

		table_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table_1.rowAtPoint(e.getPoint());
				table_1.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3) {
					popmenu.show(table_1, e.getX(), e.getY());
				}

			}

		});
		deleterow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				int row = table_1.getSelectedRow();
			
				modell.removeRow(row);

			}
		});

		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowCount = table_1.getRowCount();
				iteamNameList = new String[rowCount];
				totalArray1 = new String[rowCount];
				totalArray2 = new String[rowCount];
				totalArray3 = new String[rowCount];
				totalArray4 = new String[rowCount];

				for (int i = 0; i < rowCount; i++) {
					if (i < rowCount) {
						iteamNameList[i] = (String) modell.getValueAt(i, 2);
						totalArray1[i] = (String) modell.getValueAt(i, 9);
						totalArray2[i] = (String) modell.getValueAt(i, 8);
						totalArray3[i] = (String) modell.getValueAt(i, 10);
						totalArray4[i] = (String) modell.getValueAt(i, 11);
						sum = sum + Integer.parseInt(totalArray1[i]);
						sum = sum + Integer.parseInt(totalArray2[i]);
						sum = sum + Integer.parseInt(totalArray3[i]);
						sum = sum + Integer.parseInt(totalArray4[i]);
//System.out.println("100% ="+Arrays.toString(totalArray2)+ "70% ="+Arrays.toString(totalArray1)+ "50% ="+Arrays.toString(totalArray3)+ "30% ="+Arrays.toString(totalArray4));
					}

				}
			textField_12.setText("" + sum);
				int logic = JOptionPane.showConfirmDialog(SelectIteamPageToBuy.this,
						"You Really Want To Buy Selected Iteams.", "Conformation", JOptionPane.OK_CANCEL_OPTION);
				if (logic == JOptionPane.OK_OPTION) {
					// new Controllwer
					MainController2.priceField.setText("" + sum);
					for (int i = 0; i < iteamNameList.length; i++) {
						MainController2.iteamCombo.addItem(iteamNameList[i]);
					}
					
				}

				sum = 0;
				
				
			}
		});
		btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuy.setBounds(669, 266, 89, 23);
		panel_2.add(btnBuy);

		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(780, 266, 89, 23);
		panel_2.add(btnCancel);

		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setBounds(469, 266, 55, 23);
		panel_2.add(lblTotal);

		textField_12 = new JTextField();
		textField_12.setBounds(517, 266, 107, 23);
		panel_2.add(textField_12);
		textField_12.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(250, 270));
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 905, 225);
		panel_3.add(scrollPane);

		table = new JTable();
		modell.setColumnIdentifiers(column);
		table.setModel(modell);

		scrollPane.setViewportView(table);
		showAllIteams();

		JButton selectbtn = new JButton("Select");
		selectbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					add();
					checkBox.setEnabled(true);
					checkBox_1.setEnabled(true);
					checkBox_2.setEnabled(true);
					checkBox_3.setEnabled(true);
					checkBox.setSelected(false);
					checkBox_1.setSelected(false);
					checkBox_2.setSelected(false);
					checkBox_3.setSelected(false);
					quantityField.setText("1");
					addBtn.setEnabled(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		
		selectbtn.setBounds(740, 236, 89, 23);
		panel_3.add(selectbtn);

		JLabel lblId = new JLabel("Iteam ID :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(512, 237, 80, 22);
		panel_3.add(lblId);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(578, 235, 121, 29);
		panel_3.add(textField);
		textField.setColumns(10);

		textField.addKeyListener(new KeyAdapter() {
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
			}

		});
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			if (key==10) {
				add();
			}
			
			}
		});
	
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setPreferredSize(new Dimension(250, 400));
		panel_1.setLayout(null);

		JLabel lblIteamDetails = new JLabel("Iteam Details");
		lblIteamDetails.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblIteamDetails.setBounds(64, 11, 127, 42);
		panel_1.add(lblIteamDetails);

		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(42, 67, 60, 27);
		panel_1.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 69, 146, 27);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(96, 105, 144, 27);
		panel_1.add(textField_2);

		JLabel lblWeight = new JLabel("Weight :");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWeight.setBounds(37, 103, 60, 27);
		panel_1.add(lblWeight);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(94, 139, 146, 27);
		panel_1.add(textField_3);

		JLabel lblSize = new JLabel("Size :");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSize.setBounds(58, 137, 60, 27);
		panel_1.add(lblSize);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(94, 213, 146, 27);
		panel_1.add(textField_4);

		JLabel lblGauge = new JLabel("Gauge :");
		lblGauge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGauge.setBounds(40, 211, 60, 27);
		panel_1.add(lblGauge);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(94, 249, 146, 27);
		panel_1.add(textField_5);

		JLabel lblMakingCost = new JLabel("Making Cost :");
		lblMakingCost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMakingCost.setBounds(4, 247, 96, 27);
		panel_1.add(lblMakingCost);

		hundredField = new JTextField();
		hundredField.setColumns(10);
		hundredField.setBounds(96, 287, 144, 27);
		panel_1.add(hundredField);

		JLabel label = new JLabel("100% :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(44, 285, 60, 27);
		panel_1.add(label);

		JLabel lblFineWool = new JLabel("Fine Wool :");
		lblFineWool.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFineWool.setBounds(18, 428, 84, 27);
		panel_1.add(lblFineWool);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(94, 430, 145, 27);
		panel_1.add(textField_10);

		JLabel label_1 = new JLabel("70% :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(53, 318, 60, 27);
		panel_1.add(label_1);

		seventyField = new JTextField();
		seventyField.setColumns(10);
		seventyField.setBounds(96, 320, 144, 27);
		panel_1.add(seventyField);

		JLabel label_2 = new JLabel("50% :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(53, 356, 60, 27);
		panel_1.add(label_2);

		fiftyField = new JTextField();
		fiftyField.setColumns(10);
		fiftyField.setBounds(96, 358, 144, 27);
		panel_1.add(fiftyField);

		JLabel label_3 = new JLabel("30% :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(51, 394, 60, 27);
		panel_1.add(label_3);

		thirtyField = new JTextField();
		thirtyField.setColumns(10);
		thirtyField.setBounds(94, 396, 144, 27);
		panel_1.add(thirtyField);

		addBtn = new JButton("Add");
		addBtn.setEnabled(false);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				reCalculate();
				btnclicked++;
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.addRow(new Object[] { id, code, textField_1.getText(), textField_2.getText(),
						textField_3.getText(), textField_11.getText(), textField_4.getText(), textField_5.getText(),
						hundredField.getText(), seventyField.getText(), fiftyField.getText(), thirtyField.getText(),
						textField_10.getText() });

				hundredField.setText("0");
				seventyField.setText("0");
				fiftyField.setText("0");
				thirtyField.setText("0");

				checkBox.setEnabled(true);
				checkBox_1.setEnabled(true);
				checkBox_2.setEnabled(true);
				checkBox_3.setEnabled(true);
				checkBox.setSelected(false);
				checkBox_1.setSelected(false);
				checkBox_2.setSelected(false);
				checkBox_3.setSelected(false);
				addBtn.setEnabled(false);

			}

			private void reCalculate() {
				if (catCelected == 100) {
					hundredField.setText("" + total);
					seventyField.setText("0");
					fiftyField.setText("0");
					thirtyField.setText("0");
				} else if (catCelected == 70) {
					seventyField.setText("" + total);
					hundredField.setText("0");
					fiftyField.setText("0");
					thirtyField.setText("0");
				} else if (catCelected == 50) {
					fiftyField.setText("" + total);
					hundredField.setText("0");
					seventyField.setText("0");
					thirtyField.setText("0");
				} else if (catCelected == 30) {
					thirtyField.setText("" + total);
					hundredField.setText("0");
					seventyField.setText("0");
					fiftyField.setText("0");
				}
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addBtn.setBounds(94, 553, 89, 23);
		panel_1.add(addBtn);

		JLabel lblYarnCount = new JLabel("Yarn Count :");
		lblYarnCount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYarnCount.setBounds(8, 177, 98, 27);
		panel_1.add(lblYarnCount);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(94, 177, 146, 27);
		panel_1.add(textField_11);

		JLabel lblBuyAt = new JLabel("Buy At :");
		lblBuyAt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuyAt.setBounds(37, 494, 60, 27);
		panel_1.add(lblBuyAt);

		checkBox = new JCheckBox("100%");
		checkBox.setBounds(93, 497, 60, 23);
		panel_1.add(checkBox);

		checkBox_1 = new JCheckBox("50%");
		checkBox_1.setBounds(94, 520, 49, 23);
		panel_1.add(checkBox_1);

		checkBox_2 = new JCheckBox("70%");
		checkBox_2.setBounds(166, 497, 60, 23);
		panel_1.add(checkBox_2);

		checkBox_3 = new JCheckBox("30%");
		checkBox_3.setBounds(167, 520, 49, 23);
		panel_1.add(checkBox_3);

		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(21, 463, 84, 27);
		panel_1.add(lblQuantity);

		quantityField = new JTextField();
		quantityField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				key = e.getKeyChar();
				if (!(Character.isDigit(key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE
						|| key == KeyEvent.VK_TAB || key == KeyEvent.VK_ENTER)) {
					e.consume();

				}

			}
		});

		quantityField.setText("1");
		quantityField.setColumns(10);
		quantityField.setBounds(97, 465, 145, 27);
		panel_1.add(quantityField);

		quantityField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (quantityField.getText().equals("") || Integer.parseInt(quantityField.getText()) == 0) {

					checkBox.setEnabled(false);
					checkBox_1.setEnabled(false);
					checkBox_2.setEnabled(false);
					checkBox_3.setEnabled(false);
					addBtn.setEnabled(false);

				} else {
					checkBox.setEnabled(true);
					checkBox_1.setEnabled(true);
					checkBox_2.setEnabled(true);
					checkBox_3.setEnabled(true);
					addBtn.setEnabled(true);
				}
			}

		});

		checkBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (checkBox.isSelected()) {

					checkBox_1.setEnabled(false);
					checkBox_2.setEnabled(false);
					checkBox_3.setEnabled(false);

					addBtn.setEnabled(true);
					int total1 = Integer.parseInt(quantityField.getText());
					int total2;
					try {
						total2 = Integer.parseInt(hundredField.getText());
						total = total1 * total2;

						catCelected = 100;
						addBtn.setEnabled(true);
					} catch (NumberFormatException e1) {
						addBtn.setEnabled(false);
						JOptionPane.showMessageDialog(null, "100% Field is Empty");

					}

				} else {

					checkBox_1.setEnabled(true);
					checkBox_2.setEnabled(true);
					checkBox_3.setEnabled(true);
					addBtn.setEnabled(false);
				}
			}
		});
		checkBox_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_1.isSelected()) {
					checkBox.setEnabled(false);
					checkBox_2.setEnabled(false);
					checkBox_3.setEnabled(false);
					addBtn.setEnabled(true);

					int total1 = Integer.parseInt(quantityField.getText());
					int total2;
					try {
						total2 = Integer.parseInt(fiftyField.getText());
						total = total1 * total2;
						// total = Integer.parseInt(textField_8.getText());
						catCelected = 50;
						addBtn.setEnabled(true);
					} catch (NumberFormatException e1) {
						addBtn.setEnabled(false);
						JOptionPane.showMessageDialog(null, "50% Field Empty");

					}

				} else {

					checkBox.setEnabled(true);
					checkBox_2.setEnabled(true);
					checkBox_3.setEnabled(true);
					addBtn.setEnabled(false);

				}
			}

		});
		checkBox_2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_2.isSelected()) {
					checkBox.setEnabled(false);
					checkBox_1.setEnabled(false);
					checkBox_3.setEnabled(false);
					addBtn.setEnabled(true);

					int total1 = Integer.parseInt(quantityField.getText());
					int total2;
					try {
						total2 = Integer.parseInt(seventyField.getText());
						total = total1 * total2;

						catCelected = 70;
						addBtn.setEnabled(true);
					} catch (NumberFormatException e1) {
						addBtn.setEnabled(false);
						JOptionPane.showMessageDialog(null, "70% Field is Empty");

					}

				} else {

					checkBox.setEnabled(true);
					checkBox_1.setEnabled(true);
					checkBox_3.setEnabled(true);
					addBtn.setEnabled(false);
				}
			}
		});
		checkBox_3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_3.isSelected()) {
					checkBox.setEnabled(false);
					checkBox_1.setEnabled(false);
					checkBox_2.setEnabled(false);
					addBtn.setEnabled(true);

					int total1 = Integer.parseInt(quantityField.getText());
					int total2;
					try {
						total2 = Integer.parseInt(thirtyField.getText());
						total = total1 * total2;
						// total = Integer.parseInt(textField_9.getText());
						catCelected = 30;
						addBtn.setEnabled(true);
					} catch (NumberFormatException e1) {
						addBtn.setEnabled(false);
						JOptionPane.showMessageDialog(null, "30% Field Empty");

					}

				} else {
					addBtn.setEnabled(false);
					checkBox.setEnabled(true);
					checkBox_1.setEnabled(true);
					checkBox_2.setEnabled(true);
				}
			}
		});

		//////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////
		JLabel DueAmountList = new JLabel("Select Iteams To Buy");
		DueAmountList.setFont(new Font("Tahoma", Font.PLAIN, 32));
		DueAmountList.setBounds(524, 9, 322, 57);
		contentPane.add(DueAmountList);
	}

	public void showAllIteams() {
		try {
			conn = db.connectToDatabase();
			st = conn.createStatement();
			rs = st.executeQuery(
					"select id,code,iteamName,weight,size,yarnCount,gauge,makingCost,Hundred,Seventy,Fifty,Thirty,fineWool from iteamdetail ");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				rs.close();
				conn.close();
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Connection not closed");
			}
		}

	}

	public String[] getIteamNameList() {
		return iteamNameList;
	}

	public void add() {

		try {
			conn = db.connectToDatabase();
			sql = "select code,iteamName,weight,size,yarnCount,gauge,makingCost,hundred,seventy,fifty,thirty,fineWool from iteamdetail where id=?";

			pst = conn.prepareStatement(sql);
			id = Integer.parseInt(textField.getText());

			pst.setInt(1, id);
			rs = pst.executeQuery();

			rs.next();
			Object iteamName = rs.getObject(2);
			Object weight = rs.getObject(3);
			code = rs.getObject(1);
		
			textField_1.setText(iteamName.toString());
			textField_2.setText(weight.toString());
			textField_3.setText(rs.getObject(4).toString());
			textField_4.setText(rs.getObject(6).toString());
			textField_5.setText(rs.getObject(7).toString());
			hundredField.setText(rs.getObject(8).toString());
			seventyField.setText(rs.getObject(9).toString());
			fiftyField.setText(rs.getObject(10).toString());
			thirtyField.setText(rs.getObject(11).toString());
			textField_10.setText(rs.getObject(12).toString());
			textField_11.setText(rs.getObject(5).toString());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Connection not close in add");
			}
		}

	}

}
