package DatabaseConnection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import DataBase.Database;

public class ConnectorWithDatabase {
	Database db = new Database();
	private int flag;

	
	public Connection connectToDatabase() throws Exception{
		return db.connectDatabase();
	}

	public void disconnect(){
		db.disConnectDatabase();
	}
	
	

	////////////////// load ans save //////////////////////
	public void saveToFile(File file) throws IOException, SQLException {
		db.saveToFile(file);
	}

	public void LoadFromFile(File file) throws IOException, ClassNotFoundException {
		db.openFromFile(file);
	}
	///////////////////////////////////////////////////////
	
	public int getFlag() {
		System.out.println("1Here in db");
		return flag;
		
	}
	public void setFlag(int flag) {
		System.out.println("2Here in db");
		this.flag = flag;
	}
	
	

}
