package database;
/**
 * Description:VIP表数据操作
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


public class VIPTable extends TableOperate {

	public VIPTable(String tn) {
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
				VIPTable st = new VIPTable("VIPTable");
				VIPData sd = new VIPData("20150908002","湘涛","guotonglu",
						"m","13110100101",null,"abc","guotonglu",23,34,null);
				System.out.println("create Table result:"+st.createTable(conn));
				System.out.println("insert result:"+st.insertIntoTable(conn, sd));
				System.out.println("insert again result:"+st.insertIntoTable(conn, sd));

				VIPData sd1 = (VIPData) st.findDataById(conn, sd.id);
				System.out.println("find addr:"+sd1.getEmail());
				sd.setEmail("dfslfkj");
				System.out.println("new sd addr:"+sd.getEmail());
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
	 * Description:建立会员表。
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
					+ DataModel.idRN +" CHAR(20) NOT NULL PRIMARY KEY,"
					+ VIPData.nameRN +" CHAR(50) NOT NULL,"
					+ VIPData.iDNoRN +" CHAR(50) NOT NULL,"
					+ VIPData.genderRN +" CHAR(1),"
					+ VIPData.telRN + " CHAR(30) NOT NULL,"
					+ VIPData.birthdayRN + " DATE,"
					+ VIPData.passwordRN + " CHAR(50) DEFAULT '888888',"
					+ VIPData.emailRN + " CHAR(50),"
					+ VIPData.pointsRN + " INT DEFAULT 0,"
					+ VIPData.totalPointsRN + " INT DEFAULT 0,"
					+ VIPData.registDateRN + " DATE);")) {
				String[] index = new String[1];
				index[0] = DataModel.idRN;
				createIndexforTable(conn, "index_" + tableName, index);
				return true;
			}
			//System.out.println("VIPTable表建立完成！");
			//return true;
		} 
		catch(SQLException e){
			System.out.println("VIPTable表建立失败！");
			e.printStackTrace();
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:插入一个数据到会员表中
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm必须是VIPData vd：封装好的VIPData数据vd
	 * @return int 返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int insertIntoTable(Connection conn, DataModel dm)
			throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof VIPData){
			PreparedStatement pst = null;
			VIPData vd = (VIPData) dm;
			try {
				pst = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?,?)");
				if(hasData(conn,vd)){
					System.out.println("数据库VIP表中已经存在该数据！无法继续添加");
					return 0;
				}
				else{
					pst.setString(1, vd.getId());
					pst.setString(2, vd.getName());
					pst.setString(3, vd.getIDNo());
					pst.setString(4, vd.getGender());
					pst.setString(5, vd.getTel());
					pst.setDate(6, vd.getBirthday());
					pst.setString(7, vd.getPassword());
					pst.setString(8, vd.getEmail());
					pst.setInt(9, vd.getPoints());
					pst.setInt(10, vd.getTotalPoints());
					pst.setDate(11, vd.getRegistDate());
					if(pst.executeUpdate()==1){
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
	 * Description:更新VIP信息
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm必须是VIPData vd：封装好的VIPData数据 vd
	 * @return boolean 返回是否更新成功
	 * @exception SQLException
	 */
	@Override
	public int AlterTableDataById(Connection conn, DataModel dm)
			throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof VIPData){
			VIPData vd = (VIPData) dm;
			PreparedStatement pst=null;
			try {
				pst = conn.prepareStatement("UPDATE " + tableName + " SET " 
						+ VIPData.nameRN +"=?,"
						+ VIPData.iDNoRN +"=?,"
						+ VIPData.genderRN +"=?,"
						+ VIPData.telRN + "=?,"
						+ VIPData.birthdayRN + "=?,"
						+ VIPData.passwordRN + "=?,"
						+ VIPData.emailRN + "=?,"
						+ VIPData.pointsRN + "=?,"
						+ VIPData.totalPointsRN + "=?,"
						+ VIPData.registDateRN + "=? "
						+ "WHERE " + DataModel.idRN +"=?;");	
				
				pst.setString(1, vd.getName());
				pst.setString(2, vd.getIDNo());
				pst.setString(3, vd.getGender());
				pst.setString(4, vd.getTel());
				pst.setDate(5, vd.getBirthday());
				pst.setString(6, vd.getPassword());
				pst.setString(7, vd.getEmail());
				pst.setInt(8, vd.getPoints());
				pst.setInt(9, vd.getTotalPoints());
				pst.setDate(10, vd.getRegistDate());
				pst.setString(11, vd.getId());
				if(pst.executeUpdate()>0){
					return 1;
				}
			} 
			catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				if(pst != null){
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
	 * @return 返回DataModel-->VIPData，返回null表示没找到数据
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
				return new VIPData(rst.getString(1),rst.getString(2),rst.getString(3),
						rst.getString(4),rst.getString(5),rst.getDate(6),rst.getString(7),
						rst.getString(8),rst.getInt(9),rst.getInt(10),rst.getDate(11));
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
