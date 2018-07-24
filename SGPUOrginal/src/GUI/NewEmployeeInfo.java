package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import DatabaseConnection.ConnectorWithDatabase;
import net.coobird.thumbnailator.Thumbnails;
import net.proteanit.sql.DbUtils;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NewEmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField middleNameField;
	private JTextField lastNameField;
	private JTextField contactField;
	private JTextField emailField;
	private ButtonGroup buttonGroup;
	private ConnectorWithDatabase db;
	private Connection conn;

	private ImageIcon formate = null;
	private String gender;
	private JRadioButton rdbtnFemale;
	private JTextField addressField;
	private JTextField imagePathField;
	private JTable allUserTable;
	private String filename;
	private ImageIcon viewimage = null;
	byte[] employeeimage = null;
	private JDesktopPane desktopPane;
	private JDateChooser dateField;
	private JRadioButton maleradio;
	private ResultSet rs;
	private PreparedStatement pst;
	private String id;
	private JButton savebtn;
	private JButton updateBTn;
	private JButton deleteBtn;
	private JTextField searchField;
	private JComboBox searchCatrgory;
	private JTextField ageField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEmployeeInfo frame = new NewEmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public NewEmployeeInfo() {

		setBounds(200, 10, 726, 698);
		setMinimumSize(new Dimension(726, 698));
		
		contentPane = new JPanel();
		contentPane.setLocation(-80, -81);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		db = new ConnectorWithDatabase();
		

		JLabel PageTitleLabel = new JLabel("Enter Employee Information");
		PageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PageTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		PageTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		PageTitleLabel.setBounds(10, 11, 690, 51);
		contentPane.add(PageTitleLabel);

		buttonGroup = new ButtonGroup();

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.controlHighlight);
		desktopPane.setBounds(445, 93, 204, 218);
		contentPane.add(desktopPane);
		

		JLabel userProfilePicField = new JLabel("");
		userProfilePicField.setBorder(new LineBorder(Color.WHITE, 2));
		userProfilePicField.setBounds(10, 11, 183, 199);
		desktopPane.add(userProfilePicField);

		JButton btnImageUpload = new JButton("Upload Image");
		btnImageUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				imagePathField.setText(filename);

				try {

					File image = new File(filename);

					BufferedImage thumbanail = Thumbnails.of(image).size(180, 200).asBufferedImage();

					ByteArrayOutputStream os = new ByteArrayOutputStream();

					ImageIO.write(thumbanail, "jpeg", os);

					InputStream is = new ByteArrayInputStream(os.toByteArray());

					ByteArrayOutputStream bos = new ByteArrayOutputStream();

					byte[] buf = new byte[1024]; // 1 MB

					for (int readNum; (readNum = is.read(buf)) != -1;) {

						bos.write(buf, 0, readNum);

					}

					viewimage = new ImageIcon(thumbanail);
					userProfilePicField.setIcon(viewimage);

					employeeimage = bos.toByteArray();

				} catch (Exception ev) {

					JOptionPane.showMessageDialog(null, ev);
				}

			}
		});
		btnImageUpload.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\image upload.png"));
		btnImageUpload.setBounds(470, 333, 156, 23);
		contentPane.add(btnImageUpload);

		imagePathField = new JTextField();
		imagePathField.setBounds(461, 364, 188, 20);
		contentPane.add(imagePathField);
		imagePathField.setColumns(10);

		JLabel lblPath = new JLabel("Path :");
		lblPath.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblPath.setBounds(426, 365, 46, 17);
		contentPane.add(lblPath);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 403, 690, 35);
		contentPane.add(panel_1);

		JButton newBtn = new JButton("New");
		newBtn.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\new-icon.png"));
		newBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				middleNameField.setText("");
				lastNameField.setText("");
				contactField.setText("");
				emailField.setText("");
				addressField.setText("");
				imagePathField.setText("");
				ageField.setText("");
				userProfilePicField.setIcon(null);
				dateField.setDate(null);

				maleradio.setSelected(true);
				savebtn.setEnabled(true);
				updateBTn.setEnabled(false);
				newBtn.setEnabled(true);
				deleteBtn.setEnabled(false);
				newBtn.setEnabled(false);
			}
		});
		panel_1.add(newBtn);

		savebtn = new JButton("Save");
		savebtn.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\Save-icon.png"));
		panel_1.add(savebtn);
		//savebtn.setEnabled(false);

		updateBTn = new JButton("Update");
		updateBTn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_info();
			}

			private void update_info() {
				try {
					String sql = "update employee set first_name=?,middle_name=?,last_name=?,gender=?,contact=?,enroll_date=?,emp_address=?,email=?,emp_image=? ,Age=?where emp_id=?";
					pst = conn.prepareStatement(sql);

					pst.setString(1, nameField.getText());

					if (maleradio.isSelected()) {
						pst.setString(4, "Male");
					} else {
						pst.setString(4, "Female");
					}

					pst.setString(2, middleNameField.getText());
					pst.setString(3, lastNameField.getText());
					pst.setString(5, contactField.getText());

					java.util.Date date = dateField.getDate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String strDateOutput = sdf.format(date);

					pst.setString(6, strDateOutput);

					pst.setString(7, addressField.getText());

					pst.setBytes(9, employeeimage);
					pst.setString(10, ageField.getText());

					pst.setString(8, emailField.getText());
					pst.setString(11, id);
					pst.execute();
					updateTable();
					JOptionPane.showMessageDialog(null, "Update Successful");

				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, e);
				}

			}

		});
		updateBTn.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\update.png"));
		panel_1.add(updateBTn);

		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteInfoFromTable();
			}

		});
		deleteBtn.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\delete.png"));
		panel_1.add(deleteBtn);

		JButton printBtn = new JButton("Print");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Employee List Report Print");

				MessageFormat footer = new MessageFormat("Page {0,number,integer}");

				try {

					allUserTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);

				} catch (Exception eve) {
					System.err.format("Cannot Print %s%n", eve.getMessage());
				}
			}
		});
		printBtn.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\print.png"));
		panel_1.add(printBtn);
		
		JButton refreshTable = new JButton("Refresh Table");
		refreshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				searchField.setText("");
				searchCatrgory.setSelectedIndex(1);
			}
		});
		refreshTable.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\Refresh.png"));
		panel_1.add(refreshTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 449, 690, 161);
		contentPane.add(scrollPane);

		allUserTable = new JTable();
		allUserTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = allUserTable.getSelectedRow();
				String Table_click = (allUserTable.getModel().getValueAt(row, 0).toString());
				
				try {
					String sql = "select emp_id,first_name,middle_name,last_name,Age,gender,enroll_date,emp_address,contact,email,emp_image from employee where emp_id='"
							+ Table_click + "'";
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();

					if (rs.next()) {
						id = rs.getString("emp_id");
						String empname = rs.getString("first_name");
						nameField.setText(empname);

						String strname = rs.getString("middle_name");
						middleNameField.setText(strname);
						
						String age = rs.getString("Age");
						ageField.setText(age);

						String gender = rs.getString("gender");
						if ("Male".equals(gender)) {

							maleradio.setSelected(true);
						}

						if ("Female".equals(gender)) {
							rdbtnFemale.setSelected(true);

						}

						String lastname = rs.getString("last_name");
						lastNameField.setText(lastname);

						String contact = rs.getString("contact");
						contactField.setText(contact);

						String datee = rs.getString("enroll_date");
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datee);
						dateField.setDate(date);

						String straddress = rs.getString("emp_address");
						addressField.setText(straddress);

						String email = rs.getString("email");
						emailField.setText(email);

						byte[] imagedata = rs.getBytes("emp_image");
						formate = new ImageIcon(imagedata);
						userProfilePicField.setIcon(formate);
						employeeimage = imagedata;

					}

				} catch (Exception eve) {

					JOptionPane.showMessageDialog(null, eve);
				}

				/////////////////////////////////////////

				savebtn.setEnabled(false);
				updateBTn.setEnabled(true);
				newBtn.setEnabled(true);
				deleteBtn.setEnabled(true);
				newBtn.setEnabled(true);
			}

		});
		scrollPane.setViewportView(allUserTable);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(74, 67, 280, 325);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 20, 268, 294);
		panel_2.add(panel);
		panel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name :");
		lblFirstName.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFirstName.setBounds(26, 19, 87, 22);
		panel.add(lblFirstName);

		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameField.setBounds(101, 20, 126, 21);
		panel.add(nameField);
		nameField.setColumns(10);
		nameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if ((key >= 65 && key <= 90) || key == KeyEvent.VK_DELETE || key == KeyEvent.VK_SHIFT
						|| key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CAPS_LOCK) {
					nameField.setEditable(true);
					nameField.setBackground(Color.WHITE);

				} else {

					nameField.setEditable(false);
					nameField.setBackground(Color.WHITE);

				}
			}

		});

		JLabel lblMiddleName = new JLabel("Middle Name :");
		lblMiddleName.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMiddleName.setBounds(13, 49, 102, 22);
		panel.add(lblMiddleName);

		middleNameField = new JTextField();
		middleNameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		middleNameField.setColumns(10);
		middleNameField.setBounds(100, 49, 126, 21);
		panel.add(middleNameField);
		middleNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if ((key >= 65 && key <= 90) || key == KeyEvent.VK_DELETE || key == KeyEvent.VK_SHIFT
						|| key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CAPS_LOCK) {
					middleNameField.setEditable(true);
					middleNameField.setBackground(Color.WHITE);

				} else {

					middleNameField.setEditable(false);
					middleNameField.setBackground(Color.WHITE);

				}
			}

		});

		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblLastName.setBounds(26, 78, 87, 22);
		panel.add(lblLastName);

		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lastNameField.setColumns(10);
		lastNameField.setBounds(100, 78, 126, 21);
		panel.add(lastNameField);
		lastNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if ((key >= 65 && key <= 90) || key == KeyEvent.VK_DELETE || key == KeyEvent.VK_SHIFT
						|| key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CAPS_LOCK) {
					lastNameField.setEditable(true);
					lastNameField.setBackground(Color.WHITE);

				} else {

					lastNameField.setEditable(false);
					lastNameField.setBackground(Color.WHITE);

				}
			}

		});

		JLabel lblContact = new JLabel("Contact :");
		lblContact.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblContact.setBounds(43, 132, 71, 22);
		panel.add(lblContact);

		contactField = new JTextField();
		contactField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contactField.setColumns(10);
		contactField.setBounds(99, 133, 126, 21);
		panel.add(contactField);
		
		contactField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblEmail.setBounds(56, 160, 51, 22);
		panel.add(lblEmail);

		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailField.setColumns(10);
		emailField.setBounds(100, 161, 126, 21);
		panel.add(emailField);

		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblGender.setBounds(49, 217, 71, 21);
		panel.add(lblGender);

		JLabel lblEnrollDate = new JLabel("Enroll Date :");
		lblEnrollDate.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblEnrollDate.setBounds(28, 262, 87, 21);
		panel.add(lblEnrollDate);

		maleradio = new JRadioButton("Male");
		maleradio.setBounds(108, 219, 109, 21);
		panel.add(maleradio);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(108, 238, 109, 21);
		panel.add(rdbtnFemale);
		buttonGroup.add(maleradio);
		buttonGroup.add(rdbtnFemale);
		maleradio.setSelected(true);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblAddress.setBounds(42, 191, 71, 21);
		panel.add(lblAddress);

		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addressField.setColumns(10);
		addressField.setBounds(101, 189, 126, 21);
		panel.add(addressField);
		addressField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if ((key >= 65 && key <= 90) || key == KeyEvent.VK_DELETE || key == KeyEvent.VK_SHIFT
						|| key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CAPS_LOCK) {
					addressField.setEditable(true);
					addressField.setBackground(Color.WHITE);

				} else {

					addressField.setEditable(false);
					addressField.setBackground(Color.WHITE);

				}
			}

		});

		dateField = new JDateChooser();
		dateField.setDateFormatString("yyyy-MM-dd");
		dateField.setBounds(102, 261, 126, 21);
		panel.add(dateField);
		
		JLabel ageLabel = new JLabel("Age :");
		ageLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		ageLabel.setBounds(65, 104, 46, 22);
		panel.add(ageLabel);
		
		ageField = new JTextField();
		ageField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ageField.setColumns(10);
		ageField.setBounds(101, 105, 126, 21);
		panel.add(ageField);
		ageField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = db.connectToDatabase();
					String insertsql = "insert into employee (first_name,middle_name,last_name,gender,enroll_date,emp_address,contact,email,emp_image,Age) values (?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement insertStmet = conn.prepareStatement(insertsql);
					String fname = nameField.getText();
					String mname = middleNameField.getText();
					String lname = lastNameField.getText();
					String contact =contactField.getText();
					String email = emailField.getText();
					String address = addressField.getText();
					int age=Integer.parseInt(ageField. getText());
					int col = 1;

					if (maleradio.isSelected()) {
						gender = "Male";
					} else if (rdbtnFemale.isSelected()) {
						gender = "Female";
					}

					insertStmet.setString(col++, fname);
					insertStmet.setString(col++, mname);
					insertStmet.setString(col++, lname);
					insertStmet.setString(col++, gender);
					insertStmet.setString(col++, ((JTextField) dateField.getDateEditor().getUiComponent()).getText());

					insertStmet.setString(col++, address);
					insertStmet.setString(col++, contact);
					insertStmet.setString(col++, email);
					insertStmet.setBytes(col++, employeeimage);
					insertStmet.setInt(col++, age);
					insertStmet.execute();
					JOptionPane.showMessageDialog(NewEmployeeInfo.this, "New Employee Information Sucessifully Added",
							"Employee Added", JOptionPane.INFORMATION_MESSAGE);
					updateTable();
					savebtn.setEnabled(false);
					updateBTn.setEnabled(true);
					newBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					updateTable();
				} catch (Exception e1) {

					JOptionPane.showMessageDialog(NewEmployeeInfo.this, "Information Not Added", "Error ",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			

		});
		
		updateTable();
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnClose.setBounds(582, 622, 89, 23);
		contentPane.add(btnClose);
		
		 searchCatrgory = new JComboBox();
		 searchCatrgory.setBounds(74, 622, 89, 23);
		 contentPane.add(searchCatrgory);
		 searchCatrgory.setModel(new DefaultComboBoxModel(new String[] {"emp_id", "first_name", "last_name", "contact", "emp_address", "Age"}));
		 searchCatrgory.setSelectedIndex(1);
		 
		 searchField = new JTextField();
		 searchField.setBounds(168, 621, 134, 27);
		 contentPane.add(searchField);
		 searchField.setHorizontalAlignment(SwingConstants.CENTER);
		 searchField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 searchField.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		 searchField.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		int key=e.getKeyCode();
		 		if(key==KeyEvent.VK_BACK_SPACE){
		 			updateTable();
		 			
		 		}
		 		searchMethod();
		 	}

		 	
		 });
		 searchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				searchMethod();
			}
		});
		 searchField.setColumns(10);
		 
		 JButton searchBtn = new JButton("Search");
		 searchBtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		searchMethod();
		 	}
		 });
		 searchBtn.setBounds(312, 623, 85, 25);
		 contentPane.add(searchBtn);
		 searchBtn.setIcon(new ImageIcon("E:\\eclipse workspace\\SGPU\\src\\Icons\\search.png"));

	}

	private void deleteInfoFromTable() {

		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "PLEASE SELECT DATA TO DELETE");

		} else {
			int p = JOptionPane.showConfirmDialog(null, "DO YOU REALLY WANT TO DELETE", "Delete",
					JOptionPane.YES_NO_OPTION);

			if (p == 0) {

				try {
					String sql = "delete from employee where emp_id=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, id);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted Successful");

				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, e);
				} finally {
					try {
						rs.close();
						pst.close();
					} catch (Exception e) {
					}

				}

			}

		}

		updateTable();

	}

	private void searchMethod() {
		try {
			String searchName=(String)searchCatrgory.getSelectedItem();
			String keyword= searchField.getText().trim();
			System.out.println(keyword);
			String sql="select emp_id,first_name,middle_name,last_name,Age,gender,enroll_date,emp_address,contact,email,emp_image from employee where "+searchName+" like '"+keyword+"%'";
			pst=conn.prepareStatement(sql);
			//pst.setString(1, searchField.getText());
			rs=pst.executeQuery();
			allUserTable.setModel(DbUtils.resultSetToTableModel(rs));
			//updateTable();
		} catch (Exception eve) {
			JOptionPane.showConfirmDialog(null, eve);
		}
		
		
	}
	private void updateTable() {

		try {
			conn = db.connectToDatabase();
			String sql = "select emp_id,first_name,middle_name,last_name,Age,gender,enroll_date,emp_address,contact,email from employee";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			allUserTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception evv) {
			JOptionPane.showConfirmDialog(null, evv);
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}
