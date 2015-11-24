package database;
/**
 * Description:���������ݲ���
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


public class PurchaseTable extends TableOperate {

	public PurchaseTable(String tn) {
		super(tn);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Description:�ڲ�����
	 */
	/*public static class Tester{
		public static void main(String[] args){
			Database db = new Database("./config/dbProperties.ini");
			db.createDatabase();
			ComboPooledDataSource cpds=db.createcpdsToDb();
			try {
				Connection conn = cpds.getConnection();
				PurchaseTable st = new PurchaseTable("PurchaseTable");
				PurchaseData sd = new PurchaseData("20150908003","11",3.1,
						4.1,new Date(20150913),"20150908001");
				System.out.println("create Table result:"+st.createTable(conn));
				System.out.println("insert result:"+st.insertIntoTable(conn, sd));
				System.out.println("insert again result:"+st.insertIntoTable(conn, sd));

				PurchaseData sd1 = (PurchaseData) st.findDataById(conn, sd.id);
				System.out.println("find id:"+sd1.getId());
				sd.setOperator("20150908001");
				System.out.println("new sd Operator:"+sd.getOperator());
				System.out.println("alter result:"+st.AlterTableDataById(conn, sd));
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
	 * Description:�����ɹ���֤��(�������ź���ƷID��������)
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
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
				+ PurchaseData.goodsIDRN +" CHAR(20) NOT NULL,"
				+ PurchaseData.priceInRN +" DOUBLE NOT NULL,"
				+ PurchaseData.amountRN +" DOUBLE NOT NULL,"
				+ PurchaseData.dateInRN + " DATE NOT NULL,"
				+ PurchaseData.operatorRN + " CHAR(20) NOT NULL,"
				+ "PRIMARY KEY(" + DataModel.idRN + "," + PurchaseData.goodsIDRN + "),"
				+ "FOREIGN KEY(" + PurchaseData.goodsIDRN +")"
				+ "REFERENCES GoodsTable(" + DataModel.idRN + "),"
				+ "FOREIGN KEY(" + PurchaseData.operatorRN +")"
				+ "REFERENCES StaffTable(" + DataModel.idRN + "));")) {
				String[] index = new String[1];
				index[0] = DataModel.idRN;
				createIndexforTable(conn, "index_" + tableName, index);
				return true;
			}
			//System.out.println("PurchaseTable������ɣ�");
			//return true;
			
		} 
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("PurchaseTable����ʧ�ܣ�");
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:����һ�����ݵ��ɹ�����
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param DataModel dm������PurchaseData pd:��װ�õ�PurchaseData����gd
	 * @return int ���ظı������������0��ʾ����ʧ��
	 * @exception SQLException
	 */
	@Override
	public int insertIntoTable(Connection conn, DataModel dm) throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof PurchaseData){
			PurchaseData pd = (PurchaseData)dm;
			PreparedStatement pst = null;
			try {
				if(hasData(conn,pd)){
					System.out.println("���ݿ����Ѿ����ڸ����ݣ��޷��������");
					return 0;
				}
				else{
					pst = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?)");
					pst.setString(1, pd.getId());
					pst.setString(2, pd.getGoodsID());
					pst.setDouble(3, pd.getPriceIn());
					pst.setDouble(4, pd.getAmount());
					pst.setDate(5, pd.getDateIn());
					pst.setString(6, pd.getOperator());
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
	 * Description:����id���±�����
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param DataModel dm������PurchaseData pd����װ�õ�PurchaseData����pd
	 * @return ���ظı������������0��ʾ����ʧ��
	 * @exception SQLException
	 */
	@Override
	public int AlterTableDataById(Connection conn, DataModel dm) throws SQLException {
		// TODO Auto-generated method stub
		if(dm instanceof PurchaseData){
			PurchaseData pd = (PurchaseData) dm;
			PreparedStatement pst=null;
			try {
				pst = conn.prepareStatement("UPDATE " + tableName + " SET " 
						+ PurchaseData.goodsIDRN +"=?,"
						+ PurchaseData.priceInRN +"=?,"
						+ PurchaseData.amountRN +"=?,"
						+ PurchaseData.dateInRN + "=?,"
						+ PurchaseData.operatorRN + "=? "
						+ "WHERE " + DataModel.idRN +"=?;");	
				pst.setString(1, pd.getGoodsID());
				pst.setDouble(2, pd.getPriceIn());
				pst.setDouble(3, pd.getAmount());
				pst.setDate(4, pd.getDateIn());
				pst.setString(5, pd.getOperator());
				pst.setString(6, pd.getId());
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
	 * Description:����id���ұ�����
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param String id ��Ӧid
	 * @return ����DataModel-->PurchaseData������null��ʾû�ҵ�����
	 * @exception SQLException
	 */
	@Override
	public DataModel findDataById(Connection conn, String id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst=null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + tableName + 
					" WHERE " + DataModel.idRN +"=?");
			pst.setString(1, id);
			rst = pst.executeQuery();
			if(rst.first()){
				return new PurchaseData(rst.getString(1),rst.getString(2),rst.getDouble(3),
						rst.getDouble(4),rst.getDate(5),rst.getString(6));
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(rst != null){
				rst.close();
			}
			if(pst!=null){
				pst.close();
			}
		}
		return null;
	}

}
