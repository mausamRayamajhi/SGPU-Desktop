package GUI;

import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import DatabaseConnection.ConnectorWithDatabase;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class mausamtable extends JPanel {
	private JTable table;
	private ConnectorWithDatabase db;
	private Connection conn ;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	Statement st;
	public mausamtable() {
		System.out.println("in tbale");
		db=new ConnectorWithDatabase();
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		Object[] column = { "ID", "Name", "Contact","Deliver Address","Order Date","Delivery Date", "Items", "Price", "Advance",
				"Due Amount", "Status" };
		DefaultTableModel modell = new DefaultTableModel();
		modell.setColumnIdentifiers(column);
		table.setModel(modell);
		
		scrollPane.setViewportView(table);
		loadDataFromDB();
		

	}

	public void loadDataFromDB() {
		
			try {
				System.out.println("in db");
				 conn = db.connectToDatabase();
				//sql = "select Name,Address,Contact,SelectedIteams,Country,Tax,Vat,Total,Advance,DueAmount,Status,Gender from customerdetails inner join orderdetails on customerdetails.CustomerID=orderdetails.CustomerID";
				 st = conn.createStatement();
				rs = st.executeQuery("select customerdetails.CustomerID,Name,Contact,DelivaryAddress,OrderDate,DelivaryDate,SelectedIteams,Total,Advance,DueAmount,Status from customerdetails inner join orderdetails on customerdetails.CustomerID=orderdetails.CustomerID");
				
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					st.close();
					rs.close();
					conn.close();
					//JOptionPane.showMessageDialog(null, "db closed");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "db not closed");
				}
			}
		
		

	}

	public JTable getTable() {
		return table;
	}

}
