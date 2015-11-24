package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class TableOperate {
	protected String tableName;
	
	public TableOperate(String tn){
		tableName=tn;	
	}
	
	
	public abstract boolean createTable(Connection conn)throws SQLException;
	public abstract int insertIntoTable(Connection conn,DataModel dm)throws SQLException;//增
	public abstract int AlterTableDataById(Connection conn,DataModel dm)throws SQLException;//改
	public abstract DataModel findDataById(Connection conn,String id)throws SQLException;//查
	
	public boolean createIndexforTable(Connection conn, String indexName,String... colName) throws SQLException {
		/*CREATE UNIQUE INDEX 索引名称
		ON 表名称 (列名称) */
		String index="";
		for (String col : colName) {
			index += col + ",";
		}
		index = index.substring(0, index.length()-1);
		
		Statement st=null;
		try {
			st = conn.createStatement();
			st.executeUpdate("CREATE UNIQUE INDEX " + indexName + " ON " + tableName + "(" + index + ");");
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(tableName + "表索引建立异常！");
		} finally{
			if(st!=null){
				st.close();
			}
		}
		return false;
	}
	/**
	 * Description:依据Id删除表中的数据
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
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
	 * Description:判断数据库表中是否已经有数据
	 * @param Connection conn:一个Connection连接，用于连接对应数据库
	 * @param DataModel dm : 传入具体的数据
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
