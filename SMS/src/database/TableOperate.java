package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class TableOperate {
	protected String tableName;
	
	public TableOperate(String tn){
		tableName=tn;
		
	}
	
	public abstract boolean createTable(Connection conn)throws SQLException;
	public abstract int insertIntoTable(Connection conn,DataModel dm)throws SQLException;//��
	public abstract int AlterTableDataById(Connection conn,DataModel dm)throws SQLException;//��
	public abstract DataModel findDataById(Connection conn,String id)throws SQLException;//��
	
	/**
	 * Description:����Idɾ�����е�����
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param String id : 
	 * @exception SQLException
	 */
	boolean deleteFromTableById(Connection conn,String id)throws SQLException{
		PreparedStatement pst=null;
		try {
			pst = conn.prepareStatement("DELETE FROM "+ tableName +" WHERE "+ DataModel.idRN + "=?;");
			pst.setString(1, id);
			if(pst.executeUpdate()>0){
				return true;
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			pst.close();
		}
		return false;
	};
	/**
	 * Description:�ж����ݿ�����Ƿ��Ѿ�������
	 * @param Connection conn:һ��Connection���ӣ��������Ӷ�Ӧ���ݿ�
	 * @param DataModel dm : ������������
	 * @exception SQLException
	 */
	boolean hasData(Connection conn,DataModel dm)throws SQLException{
		PreparedStatement pst=null;
		try {
			pst = conn.prepareStatement("SELECT * FROM "+ tableName +" WHERE " + DataModel.idRN + "=?;");
			pst.setString(1, dm.id);
			if(pst.executeQuery().next()){
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			pst.close();
		}
		return false;
	};
	
	String getTableName(){
		return tableName;
	}
}
