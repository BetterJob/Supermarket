package database;
/**
 * Description:商品表操作
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


public class GoodsTable extends TableOperate {

	public GoodsTable(String tn) {
		super(tn);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Description:内部测试
	 */
	/*public static class Tester{
		public static void main(String[] args){
			Database db = new Database("./config/dbProperties.ini");
			//db.createDatabase();
			//ComboPooledDataSource cpds=db.getCPDS();
			try {
				Connection conn = db.getConnection();
				GoodsTable st = new GoodsTable("GoodsTable");
				GoodsData sd = new GoodsData("20150908003","GoodsData","2323223",
						"shucai","123456",3.4,4.7,null,"c","jiayou",4.5,70);
				System.out.println("create Table result:"+st.createTable(conn));
				System.out.println("insert result:"+st.insertIntoTable(conn, sd));
				System.out.println("insert again result:"+st.insertIntoTable(conn, sd));

				GoodsData sd1 = (GoodsData) st.findDataById(conn, sd.id);
				System.out.println("find id:"+sd1.getId());
				sd.setSupplierID("123");
				System.out.println("new sd SupplierID:"+sd.getSupplierID());
				System.out.println("alter result:"+st.AlterTableDataById(conn, sd));
				sd.setSupplierID("123456");
				sd.setId("11");
				st.insertIntoTable(conn, sd);
				sd.setId("22");
				st.insertIntoTable(conn, sd);
				System.out.println("delete result:" + st.deleteFromTableById(conn, "22"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	/**
	 * Description:建立商品表。必须先建立供应商表,这样才能有外键来进行参照完整性约束
	 * 注意外键约束检测！！！
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @exception SQLException
	 */
	@Override
	public boolean createTable(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		Statement st = null;
		try {
			st = conn.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ DataModel.idRN +" CHAR(20) NOT NULL PRIMARY KEY,"
				+ GoodsData.nameRN +" CHAR(50) NOT NULL,"
				+ GoodsData.barCodeRN +" CHAR(50) NOT NULL,"
				+ GoodsData.typeRN +" CHAR(50),"
				+ GoodsData.supplierIDRN + " CHAR(20) NOT NULL,"
				+ GoodsData.priceInRN + " DOUBLE,"
				+ GoodsData.priceOutRN + " DOUBLE,"
				+ GoodsData.validDateRN + " DATE,"
				+ GoodsData.categoryRN + " CHAR(100),"
				+ GoodsData.commentRN + " CHAR(200) DEFAULT NULL,"
				+ GoodsData.remainAmountRN + " DOUBLE NOT NULL,"
				+ GoodsData.warnAmountRN + " DOUBLE DEFAULT 0.0 CHECK(" + GoodsData.warnAmountRN + ">=0 ),"
				+ "FOREIGN KEY(" + GoodsData.supplierIDRN +")"
				+ "REFERENCES SupplierTable(" + DataModel.idRN + "));");
			System.out.println(tableName + "表建立完成！");
			return true;
		} 
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(tableName+"表建立异常！");
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:插入一个数据到商品表中
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm必须是GoodsData gd:封装好的GoodsData数据
	 * @return int 表示返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int insertIntoTable(Connection conn, DataModel dm) throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof GoodsData){
			GoodsData gd = (GoodsData) dm;
			PreparedStatement pst=null;
			try {
				if(hasData(conn,gd)){
					System.out.println("数据库中已经存在该数据！无法继续添加");
					return 0;
				}
				else{
					pst = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, gd.getId());
					pst.setString(2, gd.getName());
					pst.setString(3, gd.getBarCode());
					pst.setString(4, gd.getType());
					pst.setString(5, gd.getSupplierID());
					pst.setDouble(6, gd.getPriceIn());
					pst.setDouble(7, gd.getPriceOut());
					pst.setDate(8, gd.getValidDate());
					pst.setString(9, gd.getCategory());
					pst.setString(10, gd.getComment());
					pst.setDouble(11, gd.getRemainAmount());
					pst.setDouble(12, gd.getWarnPercent());
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
	 * @param DataModel dm必须是GoodsData gd：封装好的GoodsData数据gd
	 * @return 返回改变的行数，返回0表示插入失败
	 * @exception SQLException
	 */
	@Override
	public int AlterTableDataById(Connection conn, DataModel dm)throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof GoodsData){
			GoodsData gd = (GoodsData) dm;
			PreparedStatement pst=null;
			try {
				pst = conn.prepareStatement("UPDATE " + tableName + " SET " 
						+ GoodsData.nameRN +"=?,"
						+ GoodsData.barCodeRN +"=?,"
						+ GoodsData.typeRN +"=?,"
						+ GoodsData.supplierIDRN + "=?,"
						+ GoodsData.priceInRN + "=?,"
						+ GoodsData.priceOutRN + "=?,"
						+ GoodsData.validDateRN + "=?,"
						+ GoodsData.categoryRN + "=?,"
						+ GoodsData.commentRN + "=?,"
						+ GoodsData.remainAmountRN + "=?,"
						+ GoodsData.warnAmountRN + "=? "
						+ "WHERE " + DataModel.idRN +"=?;");	
				pst.setString(1, gd.getName());
				pst.setString(2, gd.getBarCode());
				pst.setString(3, gd.getType());
				pst.setString(4, gd.getSupplierID());
				pst.setDouble(5, gd.getPriceIn());
				pst.setDouble(6, gd.getPriceOut());
				pst.setDate(7, gd.getValidDate());
				pst.setString(8, gd.getCategory());
				pst.setString(9, gd.getComment());
				pst.setDouble(10, gd.getRemainAmount());
				pst.setDouble(11, gd.getWarnPercent());
				pst.setString(12, gd.getId());
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
	 * @return 返回DataModel-->GoodsData，返回null表示没找到数据
	 * @exception SQLException
	 */
	@Override
	public DataModel findDataById(Connection conn, String id) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement pst=null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + tableName + 
					" WHERE " + DataModel.idRN +"=?");
			pst.setString(1, id);
			rst = pst.executeQuery();
			if(rst.first()){
				return new GoodsData(rst.getString(1),rst.getString(2),rst.getString(3),
						rst.getString(4),rst.getString(5),rst.getDouble(6),rst.getDouble(7),
						rst.getDate(8),rst.getString(9),rst.getString(10),rst.getDouble(11),rst.getDouble(12));
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
