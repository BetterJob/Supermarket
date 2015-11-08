/**
 * Description:Database类，终极类，提供数据库相关的静态方法
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
	 * Description:内部测试
	 */
	/*public static class Tester{
		public static void main(String[] args){
			Database db = new Database("./config/dbProperties.ini");
			db.createDatabase();
			db.createcpdsToDb();
			// 需要加密的字符串
			String email = "ybb901210";
			// 加密
			String encrypted = DesUtils.encode(email);
			// 解密
			String decrypted = DesUtils.decode(encrypted);
			// 输出结果;
			System.out.println("email: " + email);
			System.out.println("encrypted: " + encrypted);
			System.out.println("decrypted: " + decrypted);
			System.out.println("email.equals(decrypted): " + email.equals(decrypted));
			
			System.out.println(DesUtils.decode(db.password));
		}
	}*/
	/**
	 * Description:构造函数，传入配置文件路径，自动初始化数据库并建立相关的表
	 * @param String PropertiesFilePath:配置文件路径
	 */
	public Database(String PropertiesFilePath){
		//使用Properties类来加载属性文件
		Properties props=new Properties();
		try {
			props.load(new FileInputStream(PropertiesFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到数据库连接配置文件！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("加载数据库连接配置文件出错！");
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
	 * Description:创建数据库
	 */
	private void createDatabase(){
		//@exception ClassNotFoundException 加载数据库驱动
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
	 * Description:连接数据库
	 * @return ComboPooledDataSource 连接成功，则返回数据库的连接资源池；否则返回null
	 */
	private ComboPooledDataSource createcpdsToDb(){
		try {
			//创建连接池实例
			cpds = new ComboPooledDataSource();
			//@exception PropertyVetoException设置连接池连接数据库所需的驱动
			cpds.setDriverClass(driver); //loads the jdbc driver            
			//设置裂解数据库所需的驱动
			cpds.setJdbcUrl(url);
			//设置连接数据库的用户名
			cpds.setUser(user);
			//设置连接数据库的密码
			cpds.setPassword(password); 
			//设置连接池最大连接个数为100个
			cpds.setMaxPoolSize(100);
			//设置连接池最小连接个数为1个
			cpds.setMinPoolSize(1);
			//设置连接池的初始连接个数10
			cpds.setInitialPoolSize(10);
			//设置连接池的缓存Statement的最大数
			cpds.setMaxStatements(100);
			//@exception SQLException 获得数据库连接
			return cpds;
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	/**
	 * Description:获取ComboPooledDataSource资源池
	 * @return ComboPooledDataSource 返回数据库的连接资源池
	 */
	public ComboPooledDataSource getCPDS(){
		return cpds;
	}
	/**
	 * Description:关闭ComboPooledDataSource资源池
	 */
	public void closeCPDS(){
		cpds.close();
	}
	
	/**
	 * Description:获取ComboPooledDataSource资源池的连接
	 * @return Connection 返回数据库的连接资源池的连接
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
	 * Description:关闭ComboPooledDataSource资源池的连接中的连接
	 * @param Connection con：表示要关闭的连接
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
	 * Description:初始化建立数据库中的各个表
	 * @param Connection con：表示要关闭的连接
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
	 * Description:返回该数据库中对应的StaffTable
	 * @return StaffTable:该数据库中对应的StaffTable
	 */
	public StaffTable getStaffTable(){
		return staffTable;
	}
	/**
	 * Description:返回该数据库中对应的supplierTable
	 * @return supplierTable:该数据库中对应的supplierTable
	 */
	public SupplierTable getSupplierTable(){
		return supplierTable;
	}
	/**
	 * Description:返回该数据库中对应的purchaseTable
	 * @return purchaseTable:该数据库中对应的purchaseTable
	 */
	public PurchaseTable getPurchaseTable(){
		return purchaseTable;
	}
	/**
	 * Description:返回该数据库中对应的goodsTable
	 * @return goodsTable:该数据库中对应的goodsTable
	 */
	public GoodsTable getGoodsTable(){
		return goodsTable;
	}
	/**
	 * Description:返回该数据库中对应的vipTable
	 * @return vipTable:该数据库中对应的vipTable
	 */
	public VIPTable getVIPTable(){
		return vipTable;
	}
	/**
	 * Description:返回该数据库中对应的salesTable
	 * @return salesTable:该数据库中对应的salesTable
	 */
	public SalesTable getSalesTable(){
		return salesTable;
	}
}
