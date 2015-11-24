package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Description:销售表数据操作
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-7
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class SalesTable extends TableOperate {
	/**
	 * Description:建立销售表。必须先建立会员表,员工表和商品表，这样才能有外键来进行参照完整性约束
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @exception SQLException
	 */
	public SalesTable(String tn) {
		super(tn);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Description:内部测试
	 */
	/*public static class Tester{
		public static void main(String[] args){
			Database db = new Database("./config/dbProperties.ini");
			db.createDatabase();
			ComboPooledDataSource cpds=db.createcpdsToDb();
			try {
				Connection conn = cpds.getConnection();
				SalesTable st = new SalesTable("SalesTable");
				SalesData sd = new SalesData("20150908003","11",2.3,3.4,
						new Date(20150908),"20150908001","20150908002");
				System.out.println("create Table result:"+st.createTable(conn));
				System.out.println("insert result:"+st.insertIntoTable(conn, sd));
				System.out.println("insert again result:"+st.insertIntoTable(conn, sd));

				SalesData sd1 = (SalesData) st.findDataById(conn, sd.id);
				System.out.println("find ClientID:"+sd1.getClientID());
				sd.setAmount(6.7);
				System.out.println("new sd addr:"+sd.getAmount());
				System.out.println("alter result:"+st.AlterTableDataById(conn, sd));
				sd.setId("123456");
				st.insertIntoTable(conn, sd);
				sd.setId("00000000");
				st.insertIntoTable(conn, sd);
				System.out.println("delete result:" + st.deleteFromTableById(conn, "123456"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	/**
	 * Description:建立SalesTable
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @exception SQLException
	 */
	@Override
	public boolean createTable(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		Statement st = null;
		try {
			st = conn.createStatement();
			if( 0!=st.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "("
					+ DataModel.idRN +" CHAR(20) NOT NULL,"
					+ SalesData.goodsIDRN +" CHAR(20) NOT NULL,"
					+ SalesData.priceOutRN +" DOUBLE NOT NULL,"
					+ SalesData.amountRN +" DOUBLE NOT NULL,"
					+ SalesData.dateOutRN + " DATETIME NOT NULL,"
					+ SalesData.operaterIDRN + " CHAR(20) NOT NULL,"
					+ SalesData.clientIDRN + " CHAR(20) DEFAULT NULL,"
					+ "PRIMARY KEY(" + DataModel.idRN  + "),"
					+ "FOREIGN KEY(" + SalesData.goodsIDRN +")"
					+ "REFERENCES GoodsTable(" + DataModel.idRN  + "),"
					+ "FOREIGN KEY(" + SalesData.operaterIDRN +")"
					+ "REFERENCES StaffTable(" + DataModel.idRN  + "),"
					+ "FOREIGN KEY(" + SalesData.clientIDRN +")"
					+ "REFERENCES VIPTable(" + DataModel.idRN + ")"
					+ ");")) {
						String[] index = new String[1];
						index[0] = DataModel.idRN;
						createIndexforTable(conn, "index_" + tableName, index);
						return true;
					}
			//System.out.println("SalesTable表建立完成！");
			//return true;
		} 
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("SalesTable表建立失败！");
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:插入一个数据到销售表中
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm必须是SalesData sd:封装好的SalesData数据sd
	 * @return int 返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int insertIntoTable(Connection conn, DataModel dm)
			throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof SalesData){
			SalesData sd = (SalesData) dm;
			PreparedStatement pst = null;
			try {
				if(hasData(conn,sd)){
					System.out.println("数据库中已经存在该数据！无法继续添加");
					return 0;
				}
				else{
					pst = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)");
					pst.setString(1, sd.getId());
					pst.setString(2, sd.getGoodsID());
					pst.setDouble(3, sd.getPriceOut());
					pst.setDouble(4, sd.getAmount());
					pst.setDate(5, sd.getDateOut());
					pst.setString(6, sd.getOperaterID());
					pst.setString(7, sd.getClientID());
					if(pst.executeUpdate()>0){
						return 1;
					}
				}	
			} 
			catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				if(pst!=null){
					pst.close();
				}
			}
		}
		return 0;
	}
	/**
	 * Description:依据id更新表数据
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm必须是SalesData sd：封装好的SalesData数据sd
	 * @return 返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int AlterTableDataById(Connection conn, DataModel dm)
			throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof SalesData){
			SalesData sd = (SalesData) dm;
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement("UPDATE " + tableName +" SET " 
						+ SalesData.goodsIDRN +"=?,"
						+ SalesData.priceOutRN +"=?,"
						+ SalesData.amountRN +"=?,"
						+ SalesData.dateOutRN + "=?,"
						+ SalesData.operaterIDRN + "=?,"
						+ SalesData.clientIDRN + "=?"
						+ " WHERE " + DataModel.idRN +"=?");
				pst.setString(1, sd.getGoodsID());
				pst.setDouble(2, sd.getPriceOut());
				pst.setDouble(3, sd.getAmount());
				pst.setDate(4, sd.getDateOut());
				pst.setString(5, sd.getOperaterID());
				pst.setString(6, sd.getClientID());
				pst.setString(7, sd.getId());
				if(pst.executeUpdate()>0){
					return 1;
				}
			} 
			catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				if(pst!=null){
					pst.close();
				}
			}
		}
		return 0;
	}
	/**
	 * Description:依据id查找表数据
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param String id 对应id
	 * @return 返回DataModel-->SalesData，返回null表示没找到数据
	 * @exception SQLException
	 */
	@Override
	public DataModel findDataById(Connection conn, String id)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + tableName + 
					" WHERE " + DataModel.idRN +"=?");
			pst.setString(1, id);
			rst = pst.executeQuery();
			if(rst.first()){
				return new SalesData(rst.getString(1),rst.getString(2),rst.getDouble(3),
						rst.getDouble(4),rst.getDate(5),rst.getString(6),rst.getString(7));
			}
			
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(rst!=null){
				rst.close();
			}
			if(pst!=null){
				pst.close();
			}
		}
		return null;
	}

}
