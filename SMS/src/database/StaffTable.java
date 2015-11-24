package database;
/**
 * Description:Ա�������ݲ���
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


public class StaffTable extends TableOperate {

	public StaffTable(String tn) {
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
				StaffTable st = new StaffTable("StaffTable");
				StaffData sd = new StaffData("20150908001","guotonglu","901210",
						5,"430224199101160000",24,"F","kfgz","kf","13146094606",
						4000,new Date(20150908),null);
				System.out.println("create Table result:"+st.createTable(conn));
				System.out.println("insert result:"+st.insertIntoTable(conn, sd));
				System.out.println("insert again result:"+st.insertIntoTable(conn, sd));

				StaffData sd1 = (StaffData) st.findDataById(conn, sd.id);
				System.out.println("find name:"+sd1.getStaffName());
				sd.setStaffName("gtl");
				System.out.println("new sd name:"+sd.getStaffName());
				System.out.println("alter result:"+st.AlterTableDataById(conn, sd));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	/**
	 * Description:����StaffTable
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @exception SQLException
	 */
	@Override
	public boolean createTable(Connection conn) throws SQLException{
		// TODO Auto-generated method stub
		Statement st=null;
		try {
			st = conn.createStatement();
			if( 0!=st.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ DataModel.idRN +" CHAR(20) NOT NULL PRIMARY KEY,"
				+ StaffData.staffNameRN +" CHAR(50) NOT NULL,"
				+ StaffData.passwordRN +" CHAR(50) NOT NULL,"
				+ StaffData.authorityRN +" INT DEFAULT 5 CHECK(" + StaffData.authorityRN + ">=0 AND " + StaffData.authorityRN + "<=10),"
				+ StaffData.staffIDNoRN +" CHAR(50) NOT NULL,"
				+ StaffData.staffAgeRN +" INT CHECK(" + StaffData.staffAgeRN + ">18 AND " + StaffData.staffAgeRN + "<70),"
				+ StaffData.staffGenderRN + " CHAR(1) CHECK(" + StaffData.staffGenderRN +" IN('F','M')),"
				+ StaffData.staffPositionRN + " CHAR(50),"
				+ StaffData.staffDepartmentRN + " CHAR(50),"
				+ StaffData.staffTelRN + " CHAR(30),"
				+ StaffData.staffSalaryRN + " DOUBLE,"
				+ StaffData.staffHireDateRN + " DATE,"
				+ StaffData.staffDimissionDateRN + " DATE DEFAULT NULL);")) {
				String[] index = new String[1];
				index[0] = DataModel.idRN;
				createIndexforTable(conn, "index_" + tableName, index);
				return true;
			}
			//System.out.println(tableName + "������ɣ�");
			
		} 
		catch(SQLException e){
			e.printStackTrace();
			System.out.println(tableName + "�����쳣��");
		}
		finally{
			if(st!=null){
				st.close();
			}
		}
		return false;		
	}
	
	/**
	 * Description:����һ�����ݵ�Ա������
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param DataModel dm������StaffData sd����װ�õ�StaffData����sd
	 * @return ���ظı������������0��ʾ����ʧ��
	 * @exception SQLException
	 */
	@Override
	public int insertIntoTable(Connection conn, DataModel dm) throws SQLException{
		// TODO Auto-generated method stub
		if(dm instanceof StaffData){
			StaffData sd = (StaffData)dm;
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				if(hasData(conn,sd)){
					System.out.println("���ݿ����Ѿ����ڸ����ݣ��޷��������");
					return 0;
				}
				else{
					pst.setString(1, sd.getId());
					pst.setString(2, sd.getStaffName());
					pst.setString(3, sd.getpassword());
					pst.setInt(4, sd.getauthority());
					pst.setString(5, sd.getStaffIDNo());
					pst.setInt(6, sd.getStaffAge());
					pst.setString(7, sd.getStaffGender());
					pst.setString(8, sd.getStaffPosition());
					pst.setString(9, sd.getStaffDepartment());
					pst.setString(10, sd.getStaffTel());
					pst.setDouble(11, sd.getStaffSalary());
					pst.setDate(12, sd.getStaffHireDate());
					pst.setDate(13, sd.getStaffDimissionDate());
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
	 * @param DataModel dm������StaffData sd����װ�õ�StaffData����sd
	 * @return ���ظı������������0��ʾ����ʧ��
	 * @exception SQLException
	 */
	@Override
	public int AlterTableDataById(Connection conn, DataModel dm) throws SQLException{
		// TODO Auto-generated method stub
		if(dm instanceof StaffData){
			StaffData sd = (StaffData)dm;
			PreparedStatement pst=null;
			try {
				pst = conn.prepareStatement("UPDATE " + tableName +" SET " 
						+ StaffData.staffNameRN +"=?,"
						+ StaffData.passwordRN +"=?,"
						+ StaffData.authorityRN +"=?,"
						+ StaffData.staffIDNoRN + "=?,"
						+ StaffData.staffAgeRN + "=?,"
						+ StaffData.staffGenderRN + "=?,"
						+ StaffData.staffPositionRN + "=?,"
						+ StaffData.staffDepartmentRN + "=?,"
						+ StaffData.staffTelRN + "=?,"
						+ StaffData.staffSalaryRN + "=?,"
						+ StaffData.staffHireDateRN + "=?,"
						+ StaffData.staffDimissionDateRN + "=? "
						+ "WHERE " + DataModel.idRN +"=?");
				pst.setString(1, sd.getStaffName());
				pst.setString(2, sd.getpassword());
				pst.setInt(3, sd.getauthority());
				pst.setString(4, sd.getStaffIDNo());
				pst.setInt(5, sd.getStaffAge());
				pst.setString(6, sd.getStaffGender());
				pst.setString(7, sd.getStaffPosition());
				pst.setString(8, sd.getStaffDepartment());
				pst.setString(9, sd.getStaffTel());
				pst.setDouble(10, sd.getStaffSalary());
				pst.setDate(11, sd.getStaffHireDate());
				pst.setDate(12, sd.getStaffDimissionDate());
				pst.setString(13, sd.getId());
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
	 * @return ����DataModel-->StaffData������null��ʾû�ҵ�����
	 * @exception SQLException
	 */
	@Override
	public DataModel findDataById(Connection conn, String id) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement pst=null;
		ResultSet rst=null;
		try{
			pst = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " + DataModel.idRN + "=?;");
			pst.setString(1, id);
			rst = pst.executeQuery();
			if(rst.first()){
				return new StaffData(rst.getString(1),rst.getString(2),rst.getString(3),
						rst.getInt(4),rst.getString(5),rst.getInt(6),rst.getString(7),
						rst.getString(8),rst.getString(9),rst.getString(10),rst.getDouble(11),
						rst.getDate(12),rst.getDate(13));
			}
			
		}catch(SQLException e){
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
