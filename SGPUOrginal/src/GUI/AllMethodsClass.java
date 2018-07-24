package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

import DatabaseConnection.ConnectorWithDatabase;
import net.proteanit.sql.DbUtils;

public class AllMethodsClass {
	private static ConnectorWithDatabase db;
	private static Connection conn;
	private PreparedStatement pst;
	private static ResultSet rs;
	private String sql;
	static Statement st;
	

	
	
	public static void loadDataFromDB() {
		db = new ConnectorWithDatabase();
		try {

			conn = db.connectToDatabase();

			st = conn.createStatement();
			rs = st.executeQuery(
					"select customerdetails.CustomerID,Name,Gender,Contact,DelivaryAddress,OrderDate,DelivaryDate,SelectedIteams,Total,Advance,DueAmount,Status from customerdetails inner join orderdetails on customerdetails.CustomerID=orderdetails.CustomerID order by customerdetails.CustomerID desc");
			MainController2.table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Connection Problem With Database");
		} finally {
			try {
				st.close();
				rs.close();
				conn.close();

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "db not closed");
			}
		}

	}
	
	public void searching(){
		try {
			conn=db.connectToDatabase();
			String searchName=(String)SearchingFrame.searchCatrgory.getSelectedItem();
			String keyword=SearchingFrame.searchField.getText().trim();
			System.out.println("search :"+SearchingFrame.searchField.getText());
			System.out.println("categiory :"+searchName);
			sql="select customerdetails.CustomerID,Name,Gender,Contact,Address,Country,DueAmount,Status from customerdetails inner join orderdetails on customerdetails.CustomerID=orderdetails.CustomerId where "+searchName+" like '"+keyword+"%'";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			MainController2.table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	


	/////////////////// END OF CLASS//////////////////////////////////////////////////////
}
