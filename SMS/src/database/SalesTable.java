package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Description:���۱����ݲ���
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-7
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class SalesTable extends TableOperate {
	/**
	 * Description:�������۱������Ƚ�����Ա��,Ա�������Ʒ��������������������в���������Լ��
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @exception SQLException
	 */
	public SalesTable(String tn) {
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
	 * Description:����SalesTable
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
			//System.out.println("SalesTable������ɣ�");
			//return true;
		} 
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("SalesTable����ʧ�ܣ�");
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:����һ�����ݵ����۱���
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param DataModel dm������SalesData sd:��װ�õ�SalesData����sd
	 * @return int ���ظı������������0��ʾ����ʧ��
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
					System.out.println("���ݿ����Ѿ����ڸ����ݣ��޷��������");
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
	 * Description:����id���±�����
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param DataModel dm������SalesData sd����װ�õ�SalesData����sd
	 * @return ���ظı������������0��ʾ����ʧ��
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
	 * Description:����id���ұ�����
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param String id ��Ӧid
	 * @return ����DataModel-->SalesData������null��ʾû�ҵ�����
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
