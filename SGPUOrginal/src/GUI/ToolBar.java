package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import DatabaseConnection.ConnectorWithDatabase;

public class ToolBar extends JToolBar implements ActionListener {
	AllMethodsClass allMethodClass;

	private JButton saveButton;
	private JButton refreshButton;
	private JButton search;
	JButton saveToDatabase;
	private ConnectorWithDatabase db;
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	Statement st;
	mausamtable mausam;
	String status = null;
	int ccustID;
	int totaldatainObject;
	private JProgressBar bar;
	private SwingWorker<Boolean, Object> worker;

	public ToolBar() {
		bar = new JProgressBar();
		allMethodClass = new AllMethodsClass();

		saveButton = new JButton();
		refreshButton = new JButton("refresh");
		JButton file = new JButton("File");
		search = new JButton("Search");
		JButton help = new JButton("Help");
		JButton ne = new JButton("New");
		JButton open = new JButton("Open");
		JButton undoTyping = new JButton("Undo Typing");
		JButton redo = new JButton("Redo");
		JButton cut = new JButton("Cut");
		JButton copy = new JButton("Copy");
		JButton past = new JButton("Past");
		JButton closeAll = new JButton("CloseAll");
		saveToDatabase = new JButton("SaveToDatabase");
		JButton rename = new JButton("Rename");
		JButton print = new JButton("Print");
		JButton exit = new JButton("Exit");

		db = new ConnectorWithDatabase();

		saveButton.setToolTipText("save");
		refreshButton.setToolTipText("Refresh");
		file.setToolTipText("New File");
		search.setToolTipText("Search");
		help.setToolTipText("Help");
		ne.setToolTipText("New File");
		open.setToolTipText("Open File");
		undoTyping.setToolTipText("Undo Typing");
		redo.setToolTipText("Redo");
		cut.setToolTipText("Cut");
		copy.setToolTipText("Copy");
		past.setToolTipText("Past");
		saveToDatabase.setToolTipText("SaveToDatabase");
		print.setToolTipText("Print");
		exit.setToolTipText("Exit");

		ne.setIcon(Extension.createIcon("/Icons/new16.gif"));
		open.setIcon(Extension.createIcon("/Icons/open.gif"));
		saveButton.setIcon(Extension.createIcon("/Icons/save16.gif"));
		saveToDatabase.setIcon(Extension.createIcon("/Icons/database.gif"));
		refreshButton.setIcon(Extension.createIcon("/Icons/refresh.png"));
		print.setIcon(Extension.createIcon("/Icons/print16.gif"));
		undoTyping.setIcon(Extension.createIcon("/Icons/undo16.gif"));
		redo.setIcon(Extension.createIcon("/Icons/redo16.gif"));
		cut.setIcon(Extension.createIcon("/Icons/cut16.gif"));
		copy.setIcon(Extension.createIcon("/Icons/copy16.gif"));
		past.setIcon(Extension.createIcon("/Icons/paste16.gif"));
		search.setIcon(Extension.createIcon("/Icons/search16.gif"));
		help.setIcon(Extension.createIcon("/Icons/help16.gif"));
		exit.setIcon(Extension.createIcon("/Icons/exit.gif"));

		refreshButton.addActionListener(this);
		saveToDatabase.addActionListener(this);
		// saveButton.addActionListener(this);

		//bar.setPreferredSize(new Dimension(200, 20));
		//bar.setStringPainted(true);
		//bar.setVisible(false);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchingFrame searching = new SearchingFrame();

				searching.setVisible(true);

			}

		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int key = JOptionPane.showConfirmDialog(null, "Do you really want to close Appication", "Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (key == JOptionPane.OK_OPTION) {
					System.exit(0);

				}
			}
		});
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Entire Report OF SGPU");

				MessageFormat footer = new MessageFormat("Page {0,number,integer}");

				try {

					MainController2.table.print(JTable.PrintMode.FIT_WIDTH, header, footer);

				} catch (Exception eve) {
					System.err.format("Cannot Print %s%n", eve.getMessage());
				}

			}
		});
		add(ne);
		addSeparator();
		add(saveToDatabase);
		add(refreshButton);
		addSeparator();
		add(saveButton);
		add(open);
		add(rename);
		addSeparator();
		add(print);
		addSeparator();
		add(undoTyping);
		add(redo);
		add(cut);
		add(copy);
		add(past);
		addSeparator();
		add(search);
		add(help);
		addSeparator();

		add(exit);
		addSeparator();
		add(bar);
		setLayout(new FlowLayout(FlowLayout.LEFT));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		db = new ConnectorWithDatabase();

		JButton clicked = (JButton) e.getSource();
		if (clicked == refreshButton) {

			allMethodClass.loadDataFromDB();
			JOptionPane.showMessageDialog(null, "Data Loaded From Database");
		}
		if (clicked == saveToDatabase) {
			worker = new SwingWorker<Boolean, Object>() {

				@Override
				protected Boolean doInBackground() throws Exception {

					DefaultTableModel dtm = (DefaultTableModel) NewEntry.table1.getModel();

					int nCol = dtm.getColumnCount();
					int nRow = dtm.getRowCount();

					System.out.println("Row count :" + nRow);
					System.out.println("column count :" + nCol);
					Object[][] tableData = new Object[nRow][nCol];
					for (int i = 0; i < nRow; i++) {
						for (int j = 0; j < nCol; j++) {
							tableData[i][j] = dtm.getValueAt(i, j);
							// System.out.println("data on object
							// :"+tableData[i][j]);
						}
					}
					try {
						conn = db.connectToDatabase();
						st = conn.createStatement();
						BigDecimal total = null;

						BigDecimal advance;
						BigDecimal sum = null;
						int taxvatrate;
						BigDecimal duePrice;
						BigDecimal taxvatrate1;
						totaldatainObject=tableData.length;
						for (int i = 0; i < totaldatainObject; i++) {

							int col = 1;
							setProgress(i);
							bar.setVisible(true);
							Thread.sleep(1000);
							sql = "insert into customerdetails (Name,Contact,Address,Gender,Country) values (?,?,?,?,?)";

							pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
							pst.setObject(col++, tableData[i][1]);
							pst.setObject(col++, tableData[i][3]);
							pst.setObject(col++, tableData[i][2]);
							pst.setObject(col++, tableData[i][15]);
							pst.setObject(col++, tableData[i][6]);

							// int custID=rs.getInt(1);
							// System.out.println("generated id :"+custID);

							pst.executeUpdate();
							rs = pst.getGeneratedKeys();

							if (rs.next()) {
								ccustID = rs.getInt(1);

							}
							System.out.println("cid :" + ccustID);

							int rate = (int) tableData[i][9];
							BigDecimal priceB = (BigDecimal) tableData[i][5];
							BigDecimal advanceB = (BigDecimal) tableData[i][10];

							taxvatrate1 = new BigDecimal(rate);
							BigDecimal zero = new BigDecimal(0);
							if (taxvatrate1.compareTo(zero) > 0) {
								System.out.println("here");
								sum = priceB.add((taxvatrate1.divide(new BigDecimal(100))).multiply(priceB));
								System.out.println("sum is: " + sum);
							} else {
								sum = (BigDecimal) tableData[i][5];
								taxvatrate = 0;
							}
							duePrice = sum.subtract((BigDecimal) tableData[i][10]);

							if (duePrice.compareTo(zero) == 0) {

								status = "Paid";
								System.out.println("paid");
							} else if (duePrice.compareTo(zero) > 0) {
								status = "Unpaid";
								System.out.println("unpaid");
							} else if (duePrice.compareTo(zero) < 0) {
								status = "Payback";
							}

							sql = "insert into orderdetails (CustomerID,SelectedIteams,BeforeTaxVatAmount,Tax,Vat,TaxVatRate,Total,Advance,DueAmount,OrderDate,DelivaryDate,DelivaryAddress,Status) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
							pst = conn.prepareStatement(sql);
							int coll = 1;
							pst.setObject(coll++, ccustID);
							pst.setObject(coll++, tableData[i][4]);
							pst.setBigDecimal(coll++, (BigDecimal) tableData[i][5]);
							pst.setObject(coll++, tableData[i][7]);
							pst.setObject(coll++, tableData[i][8]);
							pst.setObject(coll++, tableData[i][9]);
							pst.setBigDecimal(coll++, sum);
							pst.setBigDecimal(coll++, (BigDecimal) tableData[i][10]);
							pst.setBigDecimal(coll++, duePrice);
							pst.setObject(coll++, tableData[i][11]);
							pst.setObject(coll++, tableData[i][12]);
							pst.setObject(coll++, tableData[i][13]);
							pst.setObject(coll++, status);
							pst.executeUpdate();

							coll = 0;
							col = 0;
							// System.out.println("row
							// data"+Arrays.toString(tableData[i]));
						}
						for (int i = nRow - 1; i >= 0; i--) {
							dtm.removeRow(i);
						}
						MainController2.SnNo = 0;

						MainController2.tabpane.setSelectedIndex(0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// JOptionPane.showMessageDialog(null, "Data Saved To
					// Database");

					return true;
				}

				@Override
				protected void done() {
					JOptionPane.showMessageDialog(null, "Data Sucessfully Entered into Database");
				}

			};// end of worker
			worker.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					System.out.println(evt);
					System.out.println("total ;"+totaldatainObject);
					switch (evt.getPropertyName()) {
					case "progress":
						
					//	bar.setValue((Integer) evt.getNewValue());
						//bar.setString(((Integer) evt.getNewValue()) + "%" + "Completed");
						
					}
					//bar.setVisible(false);
				}
			});
			worker.execute();
		} // end of if
	
	}

}
