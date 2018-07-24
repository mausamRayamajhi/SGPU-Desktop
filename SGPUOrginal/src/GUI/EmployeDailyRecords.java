package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

import DatabaseConnection.ConnectorWithDatabase;
import net.proteanit.sql.DbUtils;

public class EmployeDailyRecords extends JFrame {

	private ResultSet rs;
	private PreparedStatement pst;
	private ConnectorWithDatabase db;
	private JPanel contentPane;
	private JTextField inTImeField;
	private JTextField outTimeField;
	private JTextField otTImeField;
	private JTextField satTimeField;
	private JComboBox comboBox;
	private Connection conn;
	private String selectedEmployeeID;
	private String name;
	private String mname;
	private String lname;
	private int idToSEnd;
	private int monthIndex;
	private String month;
	private JMonthChooser monthChooser;
	private String monthFromTableClicked;
	private JTable employeeDailyDetailTable;
	private JDateChooser dateChooserField;

	private JButton insertBtn;
	private JButton updateBtn;
	private String Table_click;
	private int monthIndexFromTable;
	private String date;

	private JLabel profilePic;
	private ImageIcon formate = null;
	byte[] employeeimage = null;

	private String query;
	private JLabel selectedID;
	private JLabel showID;
	private int[] Error;

	int inTime;
	int length;
	int sumEror = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeDailyRecords frame = new EmployeDailyRecords();
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
	public EmployeDailyRecords() {
		db = new ConnectorWithDatabase();
		setBounds(100, 100, 985, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Error = new int[4];
		JLabel lblEmployedailyrecords = new JLabel("Employee Daily Record");
		lblEmployedailyrecords.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.BLUE));
		lblEmployedailyrecords.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployedailyrecords.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmployedailyrecords.setBounds(60, 11, 851, 45);
		contentPane.add(lblEmployedailyrecords);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 318, 354);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(55, 28, 61, 26);
		panel.add(lblName);

		JLabel lblMonth = new JLabel("Month :");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMonth.setBounds(55, 75, 61, 26);
		panel.add(lblMonth);

		JLabel lblIn = new JLabel("In :");
		lblIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIn.setBounds(79, 158, 33, 26);
		panel.add(lblIn);

		JLabel lblOut = new JLabel("Out :");
		lblOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOut.setBounds(70, 200, 41, 26);
		panel.add(lblOut);

		JLabel lblOt = new JLabel("OT :");
		lblOt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOt.setBounds(70, 251, 33, 26);
		panel.add(lblOt);

		JLabel lblSatOt = new JLabel("Sat OT :");
		lblSatOt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSatOt.setBounds(48, 304, 61, 26);
		panel.add(lblSatOt);

		comboBox = new JComboBox();
		comboBox.setBounds(113, 28, 176, 26);
		// name of allemployee from data base

		fileCombo();
		panel.add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertBtn.setEnabled(true);
				updateBtn.setEnabled(false);
				dateChooserField.setDate(null);
				inTImeField.setText("0");
				outTimeField.setText("0");
				otTImeField.setText("0");
				satTimeField.setText("0");
				selectedID.setVisible(false);
				showID.setVisible(false);
				try {
					name = String.valueOf(comboBox.getSelectedItem());
					System.out.println(name);
					conn = db.connectToDatabase();

					query = "SELECT * FROM employees_full_name_with_id where fullname =?";
					pst = conn.prepareStatement(query);
					pst.setString(1, name);
					rs = pst.executeQuery();
					rs.next();
					int n = Integer.parseInt(rs.getString(1));
					idToSEnd = n;
					System.out.println("id :" + n);

					byte[] imagedata = rs.getBytes("emp_image");

					formate = new ImageIcon(imagedata);
					profilePic.setIcon(formate);
					employeeimage = imagedata;

					updateTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {

				} finally {
					try {
						pst.close();
						rs.close();
						conn.close();
						System.out.println("connection close for bombox for selctiong name and id ");
					} catch (Exception e2) {
						JOptionPane.showConfirmDialog(null,
								"Connection Not Closed for combobox for selctiong name and id");
					}
				}

			}

		});

		inTImeField = new JTextField();
		inTImeField.setText("0");
		inTImeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inTImeField.setBounds(113, 158, 143, 26);
		panel.add(inTImeField);
		inTImeField.setColumns(10);
		inTImeField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				int length = inTImeField.getText().length() + 1;
				// System.out.println(length);
				if (length > 0 && length <= 2) {
					System.out.println("except");
					inTImeField.setBackground(Color.WHITE);

				} else {
					System.out.println("reject");
					e.consume();
				}
				;

			}

		});

		// System.out.println("length of time field
		// is:"+inTImeField.getText().length());

		outTimeField = new JTextField();
		outTimeField.setText("0");
		outTimeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		outTimeField.setColumns(10);
		outTimeField.setBounds(113, 205, 143, 26);
		panel.add(outTimeField);
		outTimeField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				int length = outTimeField.getText().length() + 1;
				// System.out.println(length);
				if (length > 0 && length <= 2) {
					System.out.println("except");
					outTimeField.setBackground(Color.WHITE);

				} else {
					System.out.println("reject");
					e.consume();
				}

			}

		});

		otTImeField = new JTextField();

		otTImeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		otTImeField.setColumns(10);
		otTImeField.setBounds(113, 256, 143, 26);
		panel.add(otTImeField);
		otTImeField.setEditable(false);
		otTImeField.setBackground(Color.WHITE);

		satTimeField = new JTextField();
		satTimeField.setText("0");
		satTimeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		satTimeField.setColumns(10);
		satTimeField.setBounds(113, 304, 143, 26);
		panel.add(satTimeField);
		satTimeField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				int length = satTimeField.getText().length() + 1;
				// System.out.println(length);
				if (length > 0 && length <= 2) {
					System.out.println("except");
					satTimeField.setBackground(Color.WHITE);
				} else {
					System.out.println("reject");
					e.consume();
				}
				;

			}

		});

		monthChooser = new JMonthChooser();
		int n = monthChooser.getMonth() + 1;
		monthIndex = n;
		System.out.println("month index is:" + n);
		monthChooser.addPropertyChangeListener("month", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				int n = monthChooser.getMonth() + 1;
				monthIndex = n;
				System.out.println("month index after clicked next is:" + n);
			}
		});
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 14));
		monthChooser.setBounds(113, 75, 98, 26);
		panel.add(monthChooser);

		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(63, 113, 61, 26);
		panel.add(lblDate);

		dateChooserField = new JDateChooser();
		dateChooserField.setDateFormatString("yyyy-MM-dd");
		dateChooserField.setBounds(111, 116, 145, 26);
		panel.add(dateChooserField);

		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textField24Check();

				monthChooser();
				sumEror = 0;
				for (int i = 0; i < Error.length; i++) {
					sumEror = sumEror + Error[i];
					System.out.println("sum is :" + sumEror);
				}
				if (sumEror == 0) {
					try {
						conn = db.connectToDatabase();

						date = ((JTextField) dateChooserField.getDateEditor().getUiComponent()).getText();
						inTime = Integer.parseInt(inTImeField.getText());
						int outTime = Integer.parseInt(outTimeField.getText());

						int stTime = Integer.parseInt(satTimeField.getText());
						int dif = outTime - inTime;
						if (dif < 0) {
							otTImeField.setText("0");
						} else if (dif > 0) {
							otTImeField.setText("" + dif);
						}

						int ot = Integer.parseInt(otTImeField.getText());
						String sql = "insert into schedule (emp_id, date, in_time, out_time, ot_time, sat_ot,month) values ( ?, ? , ?, ?, ?, ?,?)";
						pst = conn.prepareStatement(sql);
						int increasecount = 0;
						pst.setInt(1, idToSEnd);
						pst.setString(2, date);
						pst.setInt(3, inTime);
						pst.setInt(4, outTime);
						pst.setInt(5, ot);
						pst.setInt(6, stTime);
						pst.setString(7, month);
						pst.execute();
						updateTable();
						JOptionPane.showMessageDialog(EmployeDailyRecords.this, "Data Successfully Entered",
								"Data Entered", JOptionPane.INFORMATION_MESSAGE);
						int choose = JOptionPane.showConfirmDialog(EmployeDailyRecords.this,
								"Do You Want To Enter Next Record", " Next Record", JOptionPane.OK_CANCEL_OPTION);

						if (choose == JOptionPane.OK_OPTION) {
							insertBtn.setEnabled(true);
							System.out.println("ok clicked");
							inTImeField.setText("");
							outTimeField.setText("");
							satTimeField.setText("");
							otTImeField.setText("");
							((JTextField) dateChooserField.getDateEditor().getUiComponent()).setText("");
						}

						if (choose == JOptionPane.CANCEL_OPTION) {
							dispose();
						}
						insertBtn.setEnabled(false);

					} catch (Exception e1) {

						insertBtn.setEnabled(true);
						JOptionPane.showMessageDialog(EmployeDailyRecords.this, "Data Not Entered", "Error...!!!",
								JOptionPane.ERROR_MESSAGE);
						insertBtn.setEnabled(true);
					} finally {
						try {
							pst.close();

							conn.close();
							System.out.println("connection close for insert Btn");
						} catch (Exception e2) {
							JOptionPane.showConfirmDialog(null, "Connection Not Closed for insert Btn");
						}
					}

				} else {
					JOptionPane.showConfirmDialog(null, "Solve Error to Enter Info", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			}

		});

		insertBtn.setBounds(120, 446, 124, 23);
		contentPane.add(insertBtn);
		Object[] column = { "Name", "Month", "Date", "InTime", "OutTime", "OTTime", "SatOT" };
		DefaultTableModel modell = new DefaultTableModel();
		modell.setColumnIdentifiers(column);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(371, 315, 588, 120);
		contentPane.add(scrollPane_1);

		employeeDailyDetailTable = new JTable();
		scrollPane_1.setViewportView(employeeDailyDetailTable);
		employeeDailyDetailTable.setModel(modell);

		employeeDailyDetailTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertBtn.setEnabled(false);
				updateBtn.setEnabled(true);
				int row = employeeDailyDetailTable.getSelectedRow();
				Table_click = (employeeDailyDetailTable.getModel().getValueAt(row, 0).toString());

				selectedID.setVisible(true);
				showID.setVisible(true);
				selectedID.setText("Selected ID :");
				showID.setText(Table_click);
				System.out.println("table clicked value is :" + Table_click);
				try {
					conn = db.connectToDatabase();
					String sql = "select emp_id,in_time,out_time,ot_time,sat_ot,date,month from schedule where sch_id='"
							+ Table_click + "'";
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();

					if (rs.next()) {

						String inTime = rs.getString("in_time");
						inTImeField.setText(inTime);

						String outTIme = rs.getString("out_time");
						outTimeField.setText(outTIme);

						String otTime = rs.getString("ot_time");
						otTImeField.setText(otTime);

						String satTime = rs.getString("sat_ot");
						satTimeField.setText(satTime);

						String datee = rs.getString("date");
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datee);
						dateChooserField.setDate(date);

						monthFromTableClicked = rs.getString("month");
						monthIndexChooserFromTable();
						monthChooser.setMonth(monthIndexFromTable);

						System.out.println("month form table is :" + monthFromTableClicked);
					}

				} catch (Exception eve) {

					JOptionPane.showMessageDialog(null, eve);
				} finally {
					try {
						pst.close();
						rs.close();
						conn.close();
						System.out.println("connection close for employeeDailyDetailTable");
					} catch (Exception e2) {
						JOptionPane.showConfirmDialog(null, "Connection Not Closed for employeeDailyDetailTable");
					}
				}

			}

		});

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.CYAN);
		desktopPane.setBounds(567, 81, 206, 223);
		contentPane.add(desktopPane);

		profilePic = new JLabel("");
		profilePic.setBorder(new LineBorder(Color.WHITE, 2));
		profilePic.setBounds(10, 11, 183, 199);
		desktopPane.add(profilePic);

		updateBtn = new JButton("Update");
		updateBtn.setEnabled(false);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField24Check();
				sumEror = 0;
				for (int i = 0; i < Error.length; i++) {
					sumEror = sumEror + Error[i];
					System.out.println("sum is :" + sumEror);
				}
				if (sumEror == 0) {
					try {
						int inttime = Integer.parseInt(inTImeField.getText());
						int outtime = Integer.parseInt(outTimeField.getText());
						int diff = outtime - inttime;
						System.out.println("diff is form update :" + diff);
						if (diff > 0) {
							System.out.println("herr");
							otTImeField.setText("" + diff);
						} else {
							otTImeField.setText("0");
						}

						conn = db.connectToDatabase();
						String sql = "update schedule set in_time=?,out_time=?,ot_time=?,sat_ot=? ,month=? ,date=? where sch_id=?";
						pst = conn.prepareStatement(sql);
						date = ((JTextField) dateChooserField.getDateEditor().getUiComponent()).getText();
						pst.setString(1, inTImeField.getText());
						pst.setString(2, outTimeField.getText());
						pst.setString(3, otTImeField.getText());
						pst.setString(4, satTimeField.getText());
						monthChooser();
						pst.setString(5, month);
						pst.setString(6, date);
						pst.setString(7, Table_click);
						pst.execute();

						selectedID.setText("Updated ID :");
						updateTable();
					} catch (Exception e2) {
						JOptionPane.showInputDialog(null, e2);
					} finally {
						try {
							pst.close();

							conn.close();
							System.out.println("connection close for update BTn");
						} catch (Exception e2) {
							JOptionPane.showConfirmDialog(null, "Connection Not Closed updat Btn");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Solve Error to Update");
				}

			}
		});
		updateBtn.setBounds(552, 446, 124, 23);
		contentPane.add(updateBtn);

		selectedID = new JLabel("");
		selectedID.setHorizontalTextPosition(SwingConstants.RIGHT);
		selectedID.setHorizontalAlignment(SwingConstants.RIGHT);
		selectedID.setFont(new Font("Tahoma", Font.BOLD, 14));
		selectedID.setBounds(328, 281, 137, 24);
		contentPane.add(selectedID);

		showID = new JLabel("");
		showID.setHorizontalAlignment(SwingConstants.CENTER);
		showID.setFont(new Font("Tahoma", Font.BOLD, 14));
		showID.setBounds(443, 282, 71, 23);
		contentPane.add(showID);

		selectedID.setVisible(false);
		showID.setVisible(false);
	}

	public void fileCombo() {

		try {
			conn = db.connectToDatabase();
			String checksql = "SELECT CONCAT(first_name, ' ',middle_name,' ', last_name) AS Fullname FROM employee";
			pst = conn.prepareStatement(checksql);
			rs = pst.executeQuery();
			while (rs.next()) {
				String empname = rs.getString("Fullname");

				comboBox.addItem(empname);

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
				System.out.println("connection closed for fileCombo");
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "Connection Not Closed for fileCombo");
			}
		}

	}

	private void updateTable() {

		try {
			conn = db.connectToDatabase();
			String sql = "select sch_id,fullname,month,date,in_time,out_time,ot_time,sat_ot from schedule INNER JOIN employees_full_name_with_id on schedule.emp_id=employees_full_name_with_id.emp_id where schedule.emp_id=? order by date DESC";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idToSEnd);
			rs = pst.executeQuery();

			employeeDailyDetailTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception evv) {
			JOptionPane.showConfirmDialog(null, evv);
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
				System.out.println("connection close for update table");
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "Connection Not Closed update table");
			}
		}

	}

	public void monthIndexChooserFromTable() {
		switch (monthFromTableClicked) {
		case "January":

			monthIndexFromTable = 0;
			break;
		case "February":
			monthIndexFromTable = 1;
			break;
		case "March":
			monthIndexFromTable = 2;
			break;
		case "April":
			monthIndexFromTable = 3;
			break;
		case "May":
			monthIndexFromTable = 4;
			break;
		case "June":
			monthIndexFromTable = 5;
			break;
		case "July":
			monthIndexFromTable = 6;
			break;
		case "August":
			monthIndexFromTable = 7;
			break;
		case "September":
			monthIndexFromTable = 8;
			break;
		case "October":
			monthIndexFromTable = 9;
			break;
		case "November":
			monthIndexFromTable = 10;
			break;
		case "December":
			monthIndexFromTable = 11;
			break;
		default:
			break;
		}
	}

	public void monthChooser() {
		switch (monthIndex) {
		case 1:
			month = "January";
			break;
		case 2:
			month = "February";
			break;
		case 3:
			month = "March";
			break;
		case 4:
			month = "April";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "June";
			break;
		case 7:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12:
			month = "December";
			break;
		default:
			break;
		}
	}

	public void textField24Check() throws HeadlessException, NumberFormatException {
		length = Integer.parseInt(inTImeField.getText());
		if (!(length >= 0 && length <= 24)) {
			JOptionPane.showConfirmDialog(null, "In Time is Greater Than 24.", "In Time Error",
					JOptionPane.ERROR_MESSAGE);
			inTImeField.setBackground(Color.YELLOW);
			Error[0] = 1;
		} else {
			Error[0] = 0;
		}

		length = Integer.parseInt(outTimeField.getText());
		if (!(length >= 0 && length <= 24)) {
			JOptionPane.showConfirmDialog(null, "Out Time is Greater Than 24.", "In Time Error",
					JOptionPane.ERROR_MESSAGE);
			outTimeField.setBackground(Color.YELLOW);
			Error[1] = 1;
		} else {
			Error[1] = 0;
		}

		length = Integer.parseInt(satTimeField.getText());
		if (!(length >= 0 && length <= 24)) {
			JOptionPane.showConfirmDialog(null, "SatOt Time is Greater Than 24.", "In Time Error",
					JOptionPane.ERROR_MESSAGE);
			satTimeField.setBackground(Color.YELLOW);
			Error[3] = 1;
		} else {
			Error[3] = 0;
		}
	}
}
