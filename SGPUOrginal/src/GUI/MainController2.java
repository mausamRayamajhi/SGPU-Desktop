package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import DatabaseConnection.ConnectorWithDatabase;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;

public class MainController2 extends JFrame {

	AllMethodsClass allMethod;

	NewEntry nextPannelForDB;
	private JPanel contentPane;
	private ToolBar toolBar;
	public static JTable table;
	public static JTable table_1;
	public static JTabbedPane tabpane;
	private static ConnectorWithDatabase db;
	private static Connection conn;
	private PreparedStatement pst;
	private static ResultSet rs;
	private String sql;
	static Statement st;
	private JTextField nameField;
	private JTextField CustomerAddressField;
	private JTextField contactField;
	public static JTextField priceField;
	private JTextField taxField;
	private JTextField vatField;
	private JTextField advancetField;
	private JTextField delivaryAddresField;
	public static JComboBox iteamCombo;
	private JComboBox countryModel;
	private JCheckBox vatCheckBox;
	private JCheckBox taxCheckBox;
	private JRadioButton maleRadio;
	private JCheckBox unpaidCheckBox;
	private JCheckBox paidCheckBox;
	private JPanel panel;
	private JButton resetAll;
	//// random
	private int countIteamInCombobox;
	private String[] iteamNameToArrayForStoringSQl;
	String ar;
	private ButtonGroup buttonGroup;
	String orderDate;
	String delivaryDate;
	private JDateChooser dateChooserField1;
	private JDateChooser dateChooserField2;
	private SelectIteamPageToBuy selectIteamPageToBuy;
	private boolean taxboolean;
	private boolean vatboolean;
	int countryModelSelectedIndex;
	private String status;

	private JMenuItem open;
	private JMenuItem save;
	////
	private JPopupMenu popmenu;
	private String Table_click_id;
	private JFileChooser filechooser;

	int j;
	public static int SnNo = 0;

	///
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController2 frame = new MainController2();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainController2() {
		try {
			UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
		} catch (Exception e) {
			// TODO: handle exception
		}

		nextPannelForDB = new NewEntry();
		db = new ConnectorWithDatabase();
		allMethod = new AllMethodsClass();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1350, 720);
		tabpane = new JTabbedPane();
		toolBar = new ToolBar();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu FIleMenu = new JMenu("File");
		menuBar.add(FIleMenu);

		// FileMenu Items
		JMenuItem ne = new JMenuItem("New");
		open = new JMenuItem("Open");
		JMenuItem close = new JMenuItem("Close");
		JMenuItem closeAll = new JMenuItem("CloseAll");
		save = new JMenuItem("Save");
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
		FIleMenu.setMnemonic(KeyEvent.VK_F);

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

		FIleMenu.add(ne);
		FIleMenu.addSeparator();
		FIleMenu.add(open);
		FIleMenu.add(close);
		FIleMenu.add(closeAll);
		FIleMenu.addSeparator();
		FIleMenu.add(save);
		FIleMenu.add(saveAs);
		FIleMenu.addSeparator();
		FIleMenu.add(rename);
		FIleMenu.add(refresh);
		FIleMenu.addSeparator();
		FIleMenu.add(print);
		FIleMenu.addSeparator();
		FIleMenu.add(restart);
		FIleMenu.addSeparator();
		FIleMenu.add(exit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		// editMenu items
		JMenuItem undoTyping = new JMenuItem("Undo Typing");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem past = new JMenuItem("Past");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem selectAll = new JMenuItem("Select All");

		undoTyping.setPreferredSize(dim);

		mnEdit.add(undoTyping);
		mnEdit.add(redo);
		mnEdit.addSeparator();
		mnEdit.add(cut);
		mnEdit.add(copy);
		mnEdit.add(past);
		mnEdit.addSeparator();
		mnEdit.add(delete);
		mnEdit.add(selectAll);

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

		JMenu mnInsert = new JMenu("Insert");
		menuBar.add(mnInsert);

		JMenu mnAdd = new JMenu("Add");
		mnInsert.add(mnAdd);

		JMenuItem insertEmployeDailyRecord = new JMenuItem("Daily Record...");
		JMenuItem newEmployeeInfo = new JMenuItem("New Employee...");

		mnAdd.add(insertEmployeDailyRecord);
		mnAdd.addSeparator();
		mnAdd.add(newEmployeeInfo);
		mnInsert.add(mnAdd);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		// View

		JMenuItem calculator = new JMenuItem("Calculator");
		JMenuItem viewDueAmount = new JMenuItem("Due Amount...");
		JMenuItem viewIteamDetail = new JMenuItem("Iteam Detail..");
		JMenuItem summaryOfEmoloyee = new JMenuItem("Employee Details...");
		mnView.add(viewDueAmount);
		mnView.addSeparator();
		mnView.add(viewIteamDetail);
		mnView.addSeparator();
		mnView.add(summaryOfEmoloyee);
		mnView.addSeparator();
		mnView.add(calculator);
		calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK));

		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);

		JMenu mnWindow = new JMenu("Window");

		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);

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

		/// adding itme to help
		mnNewMenu.add(welcome);
		mnNewMenu.addSeparator();
		mnNewMenu.add(tNt);
		mnNewMenu.add(checkForUpdate);
		mnNewMenu.add(installationDetail);
		mnNewMenu.addSeparator();
		mnNewMenu.add(aboutSGPU);

		// calculator
		calculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calculator(MainController2.this);
			}
		});

		filechooser = new JFileChooser();
		filechooser.addChoosableFileFilter(new FileFilter());

		insertEmployeDailyRecord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeDailyRecords nw = new EmployeDailyRecords();
				nw.setVisible(true);
			}
		});

		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						db.LoadFromFile(filechooser.getSelectedFile());

					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filechooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						db.saveToFile(filechooser.getSelectedFile());

					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		viewDueAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewDueAmount ne;
				try {
					ne = new ViewDueAmount();
					ne.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		viewIteamDetail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewIteamDetail iteamDetails;
				try {
					iteamDetails = new ViewIteamDetail();
					iteamDetails.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		newEmployeeInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewEmployeeInfo neEmployee = new NewEmployeeInfo();
				neEmployee.setVisible(true);

			}
		});

		summaryOfEmoloyee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeSummary empSummary = new EmployeeSummary();
				empSummary.setVisible(true);

			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar_1 = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		panel = new JPanel();
		tabpane.addTab("Database", panel);

		tabpane.addTab("New Entry", nextPannelForDB);
		splitPane.setRightComponent(tabpane);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		Object[] column = { "Customer ID", "Name", "Gender", "Contact", "Delivary Address", "Order Date",
				"Delivary Date", "Selecetd Iteam", "Total", "Advance", "Due Amount", "Status" };
		DefaultTableModel modell = new DefaultTableModel();
		modell.setColumnIdentifiers(column);
		table.setModel(modell);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.setPreferredSize(new Dimension(300, 0));
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(scrollPane_1);

		JPanel panel_2 = new JPanel();

		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), "Detail Information",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		JLabel lblFullName = new JLabel("Full Name :");
		lblFullName.setBounds(42, 40, 59, 14);
		panel_2.add(lblFullName);
		nameField = new JTextField();
		nameField.setBounds(97, 38, 155, 20);
		panel_2.add(nameField);
		nameField.setColumns(10);
		JLabel lblSelectedIteams = new JLabel("Selected Iteams :");
		lblSelectedIteams.setBounds(10, 151, 97, 14);
		panel_2.add(lblSelectedIteams);
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(49, 67, 59, 14);
		panel_2.add(lblAddress);
		CustomerAddressField = new JTextField();
		CustomerAddressField.setColumns(10);
		CustomerAddressField.setBounds(97, 65, 155, 20);
		panel_2.add(CustomerAddressField);
		JLabel lblContact = new JLabel("Contact :");
		lblContact.setBounds(51, 94, 59, 14);
		panel_2.add(lblContact);
		contactField = new JTextField();
		contactField.setColumns(10);
		contactField.setBounds(97, 92, 155, 20);
		panel_2.add(contactField);
		JLabel label = new JLabel("Full Name :");
		label.setBounds(42, 121, 59, 14);
		panel_2.add(label);
		JButton selectIteambtn = new JButton("Select Iteam");
		selectIteambtn.setBounds(100, 119, 103, 23);
		panel_2.add(selectIteambtn);
		iteamCombo = new JComboBox();
		iteamCombo.setBounds(97, 148, 155, 20);
		panel_2.add(iteamCombo);
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setBounds(64, 178, 59, 14);
		panel_2.add(lblPrice);
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(97, 176, 155, 20);
		panel_2.add(priceField);
		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setBounds(49, 205, 59, 14);
		panel_2.add(lblCountry);

		JLabel lblTax = new JLabel("Tax :");
		lblTax.setBounds(71, 231, 30, 14);
		panel_2.add(lblTax);
		taxCheckBox = new JCheckBox("");
		taxCheckBox.setBounds(97, 228, 25, 23);
		panel_2.add(taxCheckBox);
		JLabel lblVat = new JLabel("Vat :");
		lblVat.setBounds(73, 259, 25, 14);
		panel_2.add(lblVat);
		vatCheckBox = new JCheckBox("");
		vatCheckBox.setBounds(98, 254, 25, 23);
		panel_2.add(vatCheckBox);
		taxField = new JTextField();
		taxField.setBounds(125, 230, 56, 20);
		panel_2.add(taxField);
		taxField.setColumns(10);
		vatField = new JTextField();
		vatField.setColumns(10);
		vatField.setBounds(127, 257, 54, 20);
		panel_2.add(vatField);
		JLabel lblAdvanced = new JLabel("Advanced :\r\n");
		lblAdvanced.setBounds(41, 289, 59, 14);
		panel_2.add(lblAdvanced);
		advancetField = new JTextField();
		advancetField.setColumns(10);
		advancetField.setBounds(99, 287, 155, 20);
		panel_2.add(advancetField);
		JLabel lblOrderDate = new JLabel("Order Date :");
		lblOrderDate.setBounds(34, 317, 67, 14);
		panel_2.add(lblOrderDate);
		dateChooserField1 = new JDateChooser();
		dateChooserField1.setDateFormatString("yyyy-MM-dd");
		dateChooserField1.setBounds(97, 315, 106, 20);
		panel_2.add(dateChooserField1);
		JLabel lblDelivaryDate = new JLabel("Delivary Date :");
		lblDelivaryDate.setBounds(23, 344, 91, 14);
		panel_2.add(lblDelivaryDate);
		dateChooserField2 = new JDateChooser();
		dateChooserField2.setDateFormatString("yyyy-MM-dd");
		dateChooserField2.setBounds(97, 342, 106, 20);
		panel_2.add(dateChooserField2);
		JLabel lblDelivaryAddress = new JLabel("Delivary Address :");
		lblDelivaryAddress.setBounds(8, 375, 91, 14);
		panel_2.add(lblDelivaryAddress);
		delivaryAddresField = new JTextField();
		delivaryAddresField.setColumns(10);
		delivaryAddresField.setBounds(98, 371, 155, 20);
		panel_2.add(delivaryAddresField);
		paidCheckBox = new JCheckBox("");
		paidCheckBox.setBounds(97, 398, 25, 23);
		panel_2.add(paidCheckBox);
		JLabel lblPaid = new JLabel("Paid :");
		lblPaid.setBounds(69, 402, 33, 14);
		panel_2.add(lblPaid);
		unpaidCheckBox = new JCheckBox("");
		unpaidCheckBox.setBounds(97, 424, 25, 23);
		panel_2.add(unpaidCheckBox);
		JLabel lblUnpaid = new JLabel("Unpaid :");
		lblUnpaid.setBounds(56, 428, 48, 14);
		panel_2.add(lblUnpaid);
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setBounds(54, 452, 59, 14);
		panel_2.add(lblGender);
		maleRadio = new JRadioButton("Male");
		maleRadio.setBounds(96, 448, 109, 23);
		panel_2.add(maleRadio);
		JRadioButton femaleRadio = new JRadioButton("Female");
		femaleRadio.setBounds(97, 468, 109, 23);
		panel_2.add(femaleRadio);
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(84, 504, 89, 23);
		panel_2.add(submitButton);
		resetAll = new JButton("Reset All");
		resetAll.setBounds(85, 532, 89, 23);
		panel_2.add(resetAll);

		lblFullName.setToolTipText("Client Full Name");
		lblSelectedIteams.setToolTipText("List Of Items");
		lblTax.setToolTipText("Enter TAX");
		lblAddress.setToolTipText(" Address");
		lblContact.setToolTipText("Enter Client Contact");
		lblVat.setToolTipText("Enter VAT");
		lblPrice.setToolTipText("Enter Price");
		lblCountry.setToolTipText("Country");

		advancetField.setText("0");
		resetAll.setVisible(false);

		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRadio);
		buttonGroup.add(femaleRadio);
		maleRadio.setSelected(true);

		vatField.setEnabled(false);
		taxField.setEnabled(false);
		vatField.setText("0");
		taxField.setText("0");

		taxCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = taxCheckBox.isSelected();
				if (isTicked == true) {
					taxField.setEnabled(isTicked);
					taxboolean = isTicked;
					vatCheckBox.setEnabled(false);
				} else {
					taxField.setEnabled(isTicked);
					taxboolean = isTicked;
					vatCheckBox.setEnabled(true);
				}

			}
		});
		vatCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = vatCheckBox.isSelected();
				if (isTicked == true) {
					vatField.setEnabled(isTicked);
					vatboolean = isTicked;
					taxCheckBox.setEnabled(false);
				} else {
					vatField.setEnabled(isTicked);
					vatboolean = isTicked;
					taxCheckBox.setEnabled(true);
				}
			}
		});

		unpaidCheckBox.setSelected(true);
		paidCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (paidCheckBox.isSelected()) {
					unpaidCheckBox.setSelected(false);
				}

			}
		});
		unpaidCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (unpaidCheckBox.isSelected()) {
					paidCheckBox.setSelected(false);
				}

			}
		});

		countryModel = new JComboBox();
		countryModel.setBounds(97, 202, 103, 20);
		panel_2.add(countryModel);
		DefaultComboBoxModel ctryModel = new DefaultComboBoxModel();
		ctryModel.addElement("Nepal");
		ctryModel.addElement("India");
		ctryModel.addElement("Other");
		countryModel.setModel(ctryModel);
		countryModel.setSelectedIndex(0);

		countryModel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				countryModelSelectedIndex = countryModel.getSelectedIndex();
				if (countryModelSelectedIndex == 2) {
					countryModel.setEditable(true);
				} else {
					countryModel.setEditable(false);
				}

			}
		});

		popmenu = new JPopupMenu();
		JMenuItem deleterow = new JMenuItem("Delete Row");
		JMenuItem showDetails = new JMenuItem("Show Details");
		popmenu.add(deleterow);
		popmenu.addSeparator();
		popmenu.add(showDetails);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				Table_click_id = (table.getModel().getValueAt(row, 0).toString());

				if (e.getButton() == MouseEvent.BUTTON3) {
					popmenu.show(table, e.getX(), e.getY());
				}

			}

		});

		deleterow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn = db.connectToDatabase();
					sql = "delete from customerdetails  where CustomerID=?";
					pst = conn.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(Table_click_id));
					pst.execute();
					allMethod.loadDataFromDB();
					JOptionPane.showMessageDialog(null, "Selected Data Info Deleted Sucessfully");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		showDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn = db.connectToDatabase();
					sql = "select * from customerdetails inner join orderdetails on customerdetails.CustomerID=orderdetails.CustomerID  where customerdetails.CustomerID=?";
					PreparedStatement psst = conn.prepareStatement(sql);
					psst.setInt(1, Integer.parseInt(Table_click_id));
					ResultSet rss = psst.executeQuery();

					rss.next();

					int coln = 1;
					boolean taxBoolean = false;
					boolean vatBoolean = false;
					String CustomerID = rss.getString(coln++);
					String Name = rss.getString(coln++);
					String Contact = rss.getString(coln++);
					String Address = rss.getString(coln++);
					String Gender = rss.getString(coln++);
					String Country = rss.getString(coln++);
					String OrderID = rss.getString(coln++);
					coln++;
					String SelectedIteams = rss.getString(coln++);
					String AmountBeforeTxVt = rss.getString(coln++);
					String Tax = rss.getString(coln++);
					String VAt = rss.getString(coln++);
					String TaxVatRate = rss.getString(coln++);
					String Total = rss.getString(coln++);
					String Advance = rss.getString(coln++);
					String DueAMount = rss.getString(coln++);
					String OrderDate = rss.getString(coln++);
					String DelivaryDate = rss.getString(coln++);
					String DelivaryAddress = rss.getString(coln++);
					String Status = rss.getString(coln++);
					System.out.println(SelectedIteams);
					CustomerAllDetailsShow cus = new CustomerAllDetailsShow();
					if (Tax.equals("1")) {
						taxBoolean = true;
						vatBoolean = false;
						CustomerAllDetailsShow.vatCheckbox.setEnabled(false);
						CustomerAllDetailsShow.lblVat.setEnabled(false);
					} else if (VAt.equals("1")) {
						vatBoolean = true;
						taxBoolean = false;
						CustomerAllDetailsShow.taxCheckBox.setEnabled(false);
						CustomerAllDetailsShow.lblTax.setEnabled(false);
					} else {
						vatBoolean = false;
						taxBoolean = false;
						CustomerAllDetailsShow.taxCheckBox.setEnabled(false);
						CustomerAllDetailsShow.lblTax.setEnabled(false);
						CustomerAllDetailsShow.vatCheckbox.setEnabled(false);
						CustomerAllDetailsShow.lblVat.setEnabled(false);
					}
					///////////////// setting value to label////////////////////

					j = 1;
					for (int i = 0; i < SelectedIteams.length(); i++) {
						if (SelectedIteams.charAt(i) == ',') {
							CustomerAllDetailsShow.comboBox.addItem(SelectedIteams.substring(j, i));
							j = i + 1;
						}
						if (i + 1 == SelectedIteams.length()) {
							CustomerAllDetailsShow.comboBox.addItem(SelectedIteams.substring(j, i));
						}

					}

					CustomerAllDetailsShow.nameField.setText(Name);
					CustomerAllDetailsShow.IDField.setText(CustomerID);
					CustomerAllDetailsShow.contactField.setText(Contact);
					CustomerAllDetailsShow.countryField.setText(Country);
					CustomerAllDetailsShow.GenderField.setText(Gender);
					CustomerAllDetailsShow.addressField.setText(Address);
					CustomerAllDetailsShow.orderID.setText(OrderID);
					CustomerAllDetailsShow.amountBeforeVatTAx.setText(AmountBeforeTxVt);
					CustomerAllDetailsShow.taxCheckBox.setSelected(taxBoolean);
					CustomerAllDetailsShow.vatCheckbox.setSelected(vatBoolean);
					CustomerAllDetailsShow.taxvatrateField.setText(TaxVatRate);
					CustomerAllDetailsShow.totalField.setText(Total);
					CustomerAllDetailsShow.advancefield.setText(Advance);
					CustomerAllDetailsShow.delivaryAddressField.setText(DelivaryAddress);
					CustomerAllDetailsShow.orderDateField.setText(OrderDate);
					CustomerAllDetailsShow.delivaryDateField.setText(DelivaryDate);
					CustomerAllDetailsShow.dueAmountField.setText(DueAMount);
					CustomerAllDetailsShow.padUnpaidField.setText(Status);
					// JOptionPane.showMessageDialog(null, ""+Total);

					cus.setVisible(true);
					taxBoolean = false;
					vatBoolean = false;

					// JOptionPane.showMessageDialog(null, ""+rss);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		////////////////////////////////////////////////////////////////////////////////////////
		// Form Vladation

		formValidation();

		selectIteambtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SelectIteamPageToBuy ne;
				try {
					ne = new SelectIteamPageToBuy();
					ne.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		submitButton.addActionListener(new ActionListener() {
			BigDecimal total;

			BigDecimal advance;
			BigDecimal sum;
			int taxvatrate;
			BigDecimal duePrice;

			public void actionPerformed(ActionEvent e) {

				db = new ConnectorWithDatabase();
				orderDate = ((JTextField) dateChooserField1.getDateEditor().getUiComponent()).getText();
				delivaryDate = ((JTextField) dateChooserField2.getDateEditor().getUiComponent()).getText();

				if (nameField.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Name Field Empty.", "Form Error", JOptionPane.ERROR_MESSAGE);

				} else if (CustomerAddressField.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Delivary Address Field Empty.", "Form Error",
							JOptionPane.ERROR_MESSAGE);

				}

				else if (priceField.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Price Field Field Empty.", "Form Error",
							JOptionPane.ERROR_MESSAGE);

				} else if (orderDate.equals("") || delivaryDate.equals("")) {
					JOptionPane.showMessageDialog(null, "Either Order Or Delivary Date is Empty");

				}

				else {

					countIteamInCombobox = iteamCombo.getItemCount();

					iteamNameToArrayForStoringSQl = new String[countIteamInCombobox];
					for (int i = 0; i < countIteamInCombobox; i++) {
						iteamNameToArrayForStoringSQl[i] = (String) iteamCombo.getModel().getElementAt(i);
						ar = Arrays.toString(iteamNameToArrayForStoringSQl);
						// .replaceAll("[\\[\\]]", "").replaceAll(", ",
						// "\n");
					}
					System.out.println("iteam name in array form form when submit btn clicked:"
							+ Arrays.toString(iteamNameToArrayForStoringSQl));

					String byerName = nameField.getText();
					String Address = CustomerAddressField.getText();

					String contact = contactField.getText();

					BigDecimal price = new BigDecimal(priceField.getText());

					BigDecimal taxValue = new BigDecimal(taxField.getText());
					BigDecimal vatValue = new BigDecimal(vatField.getText());

					try {
						advance = new BigDecimal(advancetField.getText());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Enter Advance Amount to 0 or Some Amount");
						advancetField.setText("0");

					}

					boolean tax = taxboolean;

					boolean vat = vatboolean;
					String country = (String) countryModel.getSelectedItem();
					total = price;

					if (tax == true) {
						// sum = total + ((taxValue / 100) * total);
						sum = total.add((taxValue.divide(new BigDecimal(100))).multiply(total));

						taxvatrate = Integer.parseInt((taxField.getText()));

					} else if (vat == true) {
						// sum = total + (((vatValue) / (100)) * total);
						sum = total.add((vatValue.divide(new BigDecimal(100))).multiply(total));

						taxvatrate = Integer.parseInt((vatField.getText()));
					} else {
						sum = total;
						taxvatrate = 0;
					}
					if (paidCheckBox.isSelected()) {
						status = "Paid";

					} else if (unpaidCheckBox.isSelected()) {
						status = "Unpaid";
					}

					duePrice = sum.subtract(advance);

					if (duePrice.equals(new BigDecimal(0))) {

						status = "Paid";
						System.out.println("paid");
						paidCheckBox.setSelected(true);
						unpaidCheckBox.setSelected(false);
					} else {
						status = "Unpaid";
						System.out.println("unpaid");
						unpaidCheckBox.setSelected(true);
						paidCheckBox.setSelected(false);
					}

					String gender = buttonGroup.getSelection().getActionCommand();

					allMethod.loadDataFromDB();
					resetAll.setVisible(true);

					SnNo++;
					Object[] rowValue = { SnNo, byerName, Address, contact, ar, total, country, tax, vat, taxvatrate,
							advance, orderDate, delivaryDate, delivaryAddresField.getText(), status, gender };

					nextPannelForDB.getModel1().addRow(rowValue);
					tabpane.setSelectedIndex(1);
				}
			}

		});

		resetAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				resetAll.setVisible(false);

			}
		});

		allMethod.loadDataFromDB();
		// ////////////////////////////////////////////////////////////////////

	}

	public void clear() {
		nameField.setText("");
		CustomerAddressField.setText("");
		contactField.setText("");
		priceField.setText("");
		vatField.setText("0");
		taxField.setText("0");
		vatCheckBox.setSelected(false);
		taxCheckBox.setSelected(false);
		maleRadio.setSelected(true);
		advancetField.setText("0");
		unpaidCheckBox.setSelected(true);
		paidCheckBox.setSelected(false);
		iteamCombo.removeAllItems();
		((JTextField) dateChooserField1.getDateEditor().getUiComponent()).setText("");
		((JTextField) dateChooserField2.getDateEditor().getUiComponent()).setText("");
		delivaryAddresField.setText("");
		countryModel.setSelectedIndex(1);
	}

	public void formValidation() {
		contactField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}

		});

		priceField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}

		});

		advancetField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}

		});

		taxField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}

		});

		vatField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}

		});

		nameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if ((key >= 65 && key <= 90) || key == KeyEvent.VK_SHIFT || key == KeyEvent.VK_BACK_SPACE
						|| key == KeyEvent.VK_SPACE || key == KeyEvent.VK_CAPS_LOCK || key == KeyEvent.VK_DELETE) {
					nameField.setEditable(true);
					nameField.setBackground(Color.WHITE);

				} else {

					nameField.setEditable(false);
					// nameField.setBackground(Color.YELLOW);

				}
			}

		});

		CustomerAddressField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eve) {
				int key = eve.getKeyCode();

				if (((key >= 65 && key <= 90) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_SHIFT
						|| key == KeyEvent.VK_CAPS_LOCK || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_DELETE)) {
					CustomerAddressField.setEditable(true);
					CustomerAddressField.setBackground(Color.WHITE);

				} else {

					CustomerAddressField.setEditable(false);
					// CustomerAddressField.setBackground(Color.YELLOW);

				}
			}

		});

	}

}
