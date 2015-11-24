package database;
/**
 * Description:供货商表操作
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SupplierTable extends TableOperate {

	public SupplierTable(String tn) {
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
				SupplierTable st = new SupplierTable("SupplierTable");
				SupplierData sd = new SupplierData("20150908002","湘涛","guotonglu",
						"hunan","13110100101","xxxx@abc.com","abc","guotonglu","123456789","jiayou");
				System.out.println("create Table result:"+st.createTable(conn));
				System.out.println("insert result:"+st.insertIntoTable(conn, sd));
				System.out.println("insert again result:"+st.insertIntoTable(conn, sd));

				SupplierData sd1 = (SupplierData) st.findDataById(conn, sd.id);
				System.out.println("find addr:"+sd1.getAddr());
				sd.setAddr("china");
				System.out.println("new sd addr:"+sd.getAddr());
				System.out.println("alter result:"+st.AlterTableDataById(conn, sd));
				sd.setId("123456");
				st.insertIntoTable(conn, sd);
				sd.setId("00000000");
				st.insertIntoTable(conn, sd);
				System.out.println("delete result:" + st.deleteFromTableById(conn, "12356"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	*/
	/**
	 * Description:建立供应商表
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @exception SQLException
	 */
	@Override
	public boolean createTable(Connection conn) throws SQLException{
		// TODO Auto-generated method stub
		Statement st = null;
		try {
			st = conn.createStatement();
			if( 0!=st.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ DataModel.idRN +" CHAR(20) NOT NULL PRIMARY KEY,"
				+ SupplierData.nameRN +" CHAR(50) NOT NULL,"
				+ SupplierData.linkmanRN +" CHAR(50) NOT NULL,"
				+ SupplierData.addrRN +" CHAR(100),"
				+ SupplierData.telRN + " CHAR(50) NOT NULL,"
				+ SupplierData.emailRN + " CHAR(50),"
				+ SupplierData.bankRN + " CHAR(50),"
				+ SupplierData.accountNameRN + " CHAR(50) NOT NULL,"
				+ SupplierData.accountNumRN + " CHAR(50) NOT NULL,"
				+ SupplierData.commentRN + " CHAR(200) DEFAULT NULL);")) {
				String[] index = new String[1];
				index[0] = DataModel.idRN;
				createIndexforTable(conn, "index_" + tableName, index);
				return true;
			}
			//System.out.println(tableName + "表建立完成！");
			//return true;
		} 
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(tableName + "表建立异常！");
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:插入一个数据到供应商表中
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm必须是SupplierData sd：封装好的SupplierData数据sd
	 * @return int 返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int insertIntoTable(Connection conn, DataModel dm) throws SQLException{
		// TODO Auto-generated method stub
		if(dm instanceof SupplierData){
			SupplierData sd = (SupplierData) dm;
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?)");
				if(hasData(conn,sd)){
					System.out.println("数据库中已经存在该数据！无法继续添加");
					return 0;
				}
				else{
					pst.setString(1, sd.getId());
					pst.setString(2, sd.getName());
					pst.setString(3, sd.getLinkman());
					pst.setString(4, sd.getAddr());
					pst.setString(5, sd.getTel());
					pst.setString(6, sd.getEmail());
					pst.setString(7, sd.getBank());
					pst.setString(8, sd.getAccountName());
					pst.setString(9, sd.getAccountNum());
					pst.setString(10, sd.getComment());
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
	 * @param DataModel dm必须是SupplierData sd：封装好的SupplierData数据sd
	 * @return 返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int AlterTableDataById(Connection conn, DataModel dm) throws SQLException{
		// TODO Auto-generated method stub
		if(dm instanceof SupplierData){
			SupplierData sd = (SupplierData) dm;
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement("UPDATE " + tableName +" SET " 
						+ SupplierData.nameRN +"=?,"
						+ SupplierData.linkmanRN +"=?,"
						+ SupplierData.addrRN +"=?,"
						+ SupplierData.telRN + "=?,"
						+ SupplierData.emailRN + "=?,"
						+ SupplierData.bankRN + "=?,"
						+ SupplierData.accountNameRN + "=?,"
						+ SupplierData.accountNumRN + "=?,"
						+ SupplierData.commentRN + "=? "
						+ "WHERE " + DataModel.idRN +"=?");
				pst.setString(1, sd.getName());
				pst.setString(2, sd.getLinkman());
				pst.setString(3, sd.getAddr());
				pst.setString(4, sd.getTel());
				pst.setString(5, sd.getEmail());
				pst.setString(6, sd.getBank());
				pst.setString(7, sd.getAccountName());
				pst.setString(8, sd.getAccountNum());
				pst.setString(9, sd.getComment());
				pst.setString(10, sd.getId());
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
	 * @return 返回DataModel-->SupplierData，返回null表示没找到数据
	 * @exception SQLException
	 */
	@Override
	public DataModel findDataById(Connection conn, String id) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + tableName + 
					" WHERE " + DataModel.idRN +"=?");
			pst.setString(1, id);
			rst = pst.executeQuery();
			if(rst.first()){
				return new SupplierData(rst.getString(1),rst.getString(2),rst.getString(3),
						rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),
						rst.getString(8),rst.getString(9),rst.getString(10));
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
