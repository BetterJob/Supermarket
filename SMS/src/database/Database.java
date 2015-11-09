/**
 * Description:Database�࣬�ռ��࣬�ṩ���ݿ���صľ�̬����
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-4
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
package database;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import desutils.DesUtils;

public final class Database {
	private String driver;
	private String url;
	private String urlsys;
	private String dbname;
	private String user;
	private String password;
	
	private ComboPooledDataSource cpds;
	
	private StaffTable staffTable = null;
	private SupplierTable supplierTable = null;
	private PurchaseTable purchaseTable= null;
	private GoodsTable goodsTable= null;
	private VIPTable vipTable= null;
	private SalesTable salesTable= null;
	/**
	 * Description:�ڲ�����
	 */
	/*public static class Tester{
		public static void main(String[] args){
			Database db = new Database("./config/dbProperties.ini");
			db.createDatabase();
			db.createcpdsToDb();
			// ��Ҫ���ܵ��ַ���
			String email = "ybb901210";
			// ����
			String encrypted = DesUtils.encode(email);
			// ����
			String decrypted = DesUtils.decode(encrypted);
			// ������;
			System.out.println("email: " + email);
			System.out.println("encrypted: " + encrypted);
			System.out.println("decrypted: " + decrypted);
			System.out.println("email.equals(decrypted): " + email.equals(decrypted));
			
			System.out.println(DesUtils.decode(db.password));
		}
	}*/
	/**
	 * Description:���캯�������������ļ�·�����Զ���ʼ�����ݿⲢ������صı�
	 * @param String PropertiesFilePath:�����ļ�·��
	 */
	public Database(String PropertiesFilePath){
		//ʹ��Properties�������������ļ�
		Properties props=new Properties();
		try {
			props.load(new FileInputStream(PropertiesFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�Ҳ������ݿ����������ļ���");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�������ݿ����������ļ�����");
			e.printStackTrace();
		}
		driver = props.getProperty("driver");
		dbname = props.getProperty("dbname");
		urlsys = props.getProperty("urlsys");
		url = props.getProperty("url") + dbname;
		user = props.getProperty("user");
		password = DesUtils.decode(props.getProperty("password"));

		initDb();
	}
	
	/**
	 * Description:�������ݿ�
	 */
	private void createDatabase(){
		//@exception ClassNotFoundException �������ݿ�����
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(urlsys, user, password);

			Statement statement = connection.createStatement();
		    statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbname);
		    statement.close();
		    connection.close();
		    /*connection = DriverManager.getConnection(url, user, password);
		    statement = connection.createStatement();
		    statement.executeUpdate("create table test("
									+ "id varchar(6) not null primary key,"
									+ "name varchar(12));");
		    System.out.print("done test!");*/
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	/**
	 * Description:�������ݿ�
	 * @return ComboPooledDataSource ���ӳɹ����򷵻����ݿ��������Դ�أ����򷵻�null
	 */
	private ComboPooledDataSource createcpdsToDb(){
		try {
			//�������ӳ�ʵ��
			cpds = new ComboPooledDataSource();
			//@exception PropertyVetoException�������ӳ��������ݿ����������
			cpds.setDriverClass(driver); //loads the jdbc driver            
			//�����ѽ����ݿ����������
			cpds.setJdbcUrl(url);
			//�����������ݿ���û���
			cpds.setUser(user);
			//�����������ݿ������
			cpds.setPassword(password); 
			//�������ӳ�������Ӹ���Ϊ100��
			cpds.setMaxPoolSize(100);
			//�������ӳ���С���Ӹ���Ϊ1��
			cpds.setMinPoolSize(1);
			//�������ӳصĳ�ʼ���Ӹ���10
			cpds.setInitialPoolSize(10);
			//�������ӳصĻ���Statement�������
			cpds.setMaxStatements(100);
			//@exception SQLException ������ݿ�����
			return cpds;
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	/**
	 * Description:��ȡComboPooledDataSource��Դ��
	 * @return ComboPooledDataSource �������ݿ��������Դ��
	 */
	public ComboPooledDataSource getCPDS(){
		return cpds;
	}
	/**
	 * Description:�ر�ComboPooledDataSource��Դ��
	 */
	public void closeCPDS(){
		cpds.close();
	}
	
	/**
	 * Description:��ȡComboPooledDataSource��Դ�ص�����
	 * @return Connection �������ݿ��������Դ�ص�����
	 */
	public Connection getConnection(){
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Description:�ر�ComboPooledDataSource��Դ�ص������е�����
	 * @param Connection con����ʾҪ�رյ�����
	 */
	public void closeConnection(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Description:��ʼ���������ݿ��еĸ�����
	 * @param Connection con����ʾҪ�رյ�����
	 * @throws SQLException 
	 */
	public void initDb() {
		createDatabase();
		if(createcpdsToDb()!=null){
			Connection conn = null;
			try {
				conn = cpds.getConnection();
				staffTable = new StaffTable("StaffTable");
				staffTable.createTable(conn);
				supplierTable = new SupplierTable("SupplierTable");
				supplierTable.createTable(conn);
				goodsTable = new GoodsTable("GoodsTable");
				goodsTable.createTable(conn);
				purchaseTable = new PurchaseTable("PurchaseTable");
				purchaseTable.createTable(conn);
				vipTable = new VIPTable("VIPTable");
				vipTable.createTable(conn);
				salesTable = new SalesTable("SalesTable");
				salesTable.createTable(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * Description:���ظ����ݿ��ж�Ӧ��StaffTable
	 * @return StaffTable:�����ݿ��ж�Ӧ��StaffTable
	 */
	public StaffTable getStaffTable(){
		return staffTable;
	}
	/**
	 * Description:���ظ����ݿ��ж�Ӧ��supplierTable
	 * @return supplierTable:�����ݿ��ж�Ӧ��supplierTable
	 */
	public SupplierTable getSupplierTable(){
		return supplierTable;
	}
	/**
	 * Description:���ظ����ݿ��ж�Ӧ��purchaseTable
	 * @return purchaseTable:�����ݿ��ж�Ӧ��purchaseTable
	 */
	public PurchaseTable getPurchaseTable(){
		return purchaseTable;
	}
	/**
	 * Description:���ظ����ݿ��ж�Ӧ��goodsTable
	 * @return goodsTable:�����ݿ��ж�Ӧ��goodsTable
	 */
	public GoodsTable getGoodsTable(){
		return goodsTable;
	}
	/**
	 * Description:���ظ����ݿ��ж�Ӧ��vipTable
	 * @return vipTable:�����ݿ��ж�Ӧ��vipTable
	 */
	public VIPTable getVIPTable(){
		return vipTable;
	}
	/**
	 * Description:���ظ����ݿ��ж�Ӧ��salesTable
	 * @return salesTable:�����ݿ��ж�Ӧ��salesTable
	 */
	public SalesTable getSalesTable(){
		return salesTable;
	}
}
