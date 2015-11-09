/**
 * Description:数据模型
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-8-31
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
package database;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

public abstract class DataModel {
	public final static String idRN="id";//Row Name
	
	protected String id;
	
	public String getId(){
		return id;
	};
	public void setId(String id){
		this.id=id;
	};
	
	public abstract void packDataIntoElement(Element fatherElement);
	protected abstract void getDatafromElement(Element dataModelElement)throws DatatypeConfigurationException;
	public abstract void printData();
}
