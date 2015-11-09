package control;

import java.io.IOException;

import socket.NSocketServer;
import database.Database;

public class SupermarketServerControl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*Database db=new Database("./config/dbProperties.ini");
		db.createDatabase();
		try {
			Connection conn = db.createcpdsToDb().getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("create table test1("
					+ "id varchar(6) not null primary key,"
					+ "name varchar(12));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("done test!");*/
		Database db=new Database("./config/dbProperties.ini");
		//test
		System.out.println("服务器开始");
		NSocketServer nss = new NSocketServer();
		nss.init(db.getConnection());
		new Thread(nss).start();
		System.out.println("服务器结束");
		//db.createDatabase();
		
	}

}
