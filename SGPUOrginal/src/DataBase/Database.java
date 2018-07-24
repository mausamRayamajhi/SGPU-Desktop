package DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import GUI.MainController2;
import GUI.NewEntry;

public class Database {
	private Object tableData[][];
	static Connection con ;
private SwingWorker<Boolean, Integer> worker;
int nRow ;
Object[][] oj;
	// database
	public static Connection connectDatabase() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/sgpu";
			con = (Connection) DriverManager.getConnection(url, "root", "");
			return con;
			// System.out.println(con);
		} catch (ClassNotFoundException e) {

			throw new Exception("Driver not found");
		}

	}

	public void disConnectDatabase() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("cannot close the connection");
			}
		}
	}

	///////// save////////////////////
	public void saveToFile(File file) throws IOException, SQLException {
		worker=new SwingWorker<Boolean, Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				
			
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		DefaultTableModel dtm = (DefaultTableModel) MainController2.table.getModel();
		
		 nRow = dtm.getRowCount();int nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				Thread.sleep(10);
				tableData[i][j] = dtm.getValueAt(i, j);
				System.out.println(tableData[i][j]);
			}
		}
		oos.writeObject(tableData);
		tableData = null;
		oos.close();
		return null;
		
			}

			@Override
			protected void done() {
				JOptionPane.showMessageDialog(null,+nRow+ " Row of Data Successfully Saved To File" );
				
			}};
			
			worker.execute();
	}

	/////////// open/////////////////////////////

	public void openFromFile(File file) throws IOException, ClassNotFoundException {
	worker=new SwingWorker<Boolean, Integer>(){

		@Override
		protected Boolean doInBackground() throws Exception {
			// TODO Auto-generated method stub
			
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		oj = (Object[][]) ois.readObject();
		DefaultTableModel df = (DefaultTableModel) NewEntry.table1.getModel();
		System.out.println("length of oj i :" + oj.length);
		for (int i = 0; i < oj.length; i++) {
			
		Thread.sleep(100);
			df.addRow(oj[i]);
			
		}

		oj = null;
	
		return null;
		
		}

		@Override
		protected void done() {
			JOptionPane.showMessageDialog(null,+oj.length+ " Data Readed From File");
		}};
		worker.execute();
	}
	///////////////////////////////////////////////////////////////////

}
