package database;

import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

import tools.Tools;

/**
 * Description:商品表数据封装
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class GoodsData extends DataModel {
	/*商品ID（CHAR(20) NOT NULL PRIMARY KEY），商品名称（CHAR(100) NOT NULL），
	商品条码（CHAR(50)），规格型号(CHAR(50))，商品供货商ID(CHAR(20)外键)，商品进价(DOUBLE)，商品售价(DOUBLE)，
	商品有效期(DATE)，商品类别(CHAR(100))，商品描述（CHAR(200)）*/
	public final static String nameRN="Name";
	public final static String barCodeRN="BarCode";//商品条码
	public final static String typeRN="Type";//商品规格型号
	public final static String supplierIDRN="SupplierID";//供货商ID_外键
	public final static String priceInRN="PriceIn";//进价
	public final static String priceOutRN="PriceOut";//售价
	public final static String validDateRN="ValidDate";//有效期
	public final static String categoryRN="Category";//类别
	public final static String commentRN="Comment";//描述
	public final static String remainAmountRN="remainAmount";//库存量
	public final static String warnAmountRN="warnAmount";//警戒百分比
	
	private String name;
	private String barCode;
	private String type;
	private String supplierID;
	private double priceIn;
	private double priceOut;
	private Date validDate;
	private String category;
	private String comment;
	private double remainAmount;//库存量
	private double warnAmount;
	
	public GoodsData(String ID,String name,String barCode,String type,String supplierID,
			double priceIn,double priceOut,Date validDate,String category,String comment,
			double remainAmount,double warnAmount){
		this.id=ID;
		this.name=name;
		this.barCode=barCode;
		this.type=type;
		this.supplierID=supplierID;
		this.priceIn=priceIn;
		this.priceOut=priceOut;
		this.validDate=validDate;
		this.category=category;
		this.comment=comment;
		this.remainAmount = remainAmount;
		this.warnAmount = warnAmount;
	}
	/**
	 * Description:构造函数，传入xml中关于GoodsData的Element节点
	 * @param Element dataModelElement:xml中关于GoodsData的Element节点
	 * @exception DatatypeConfigurationException ：数据格式不匹配
	 */
	public GoodsData(Element dataModelElement) {
		try {
			getDatafromElement(dataModelElement);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setBarCode(String barCode){
		this.barCode=barCode;
	}
	public String getBarCode(){
		return this.barCode;
	}
	
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
	
	public void setSupplierID(String supplierID){
		this.supplierID=supplierID;
	}
	public String getSupplierID(){
		return this.supplierID;
	}
	
	public void setPriceIn(double priceIn){
		this.priceIn=priceIn;
	}
	public double getPriceIn(){
		return this.priceIn;
	}
	
	public void setPriceOut(double priceOut){
		this.priceOut=priceOut;
	}
	public double getPriceOut(){
		return this.priceOut;
	}
	
	public void setValidDate(Date validDate){
		this.validDate=validDate;
	}
	public Date getValidDate(){
		return this.validDate;
	}
	
	public void setCategory(String category){
		this.category=category;
	}
	public String getCategory(){
		return this.category;
	}
	
	public void setComment(String comment){
		this.comment=comment;
	}
	public String getComment(){
		return this.comment;
	}
	
	public void setRemainAmount(double remainAmount){
		this.remainAmount = remainAmount;
	}
	public double getRemainAmount(){
		return remainAmount;
	}
	public void setWarnPercent(int warnAmount){
		this.warnAmount = warnAmount;
	}
	public double getWarnPercent(){
		return warnAmount;
	}
	/**
	 * Description:将该GoodsData打包到Element中
	 * @param Element fatherElement:要打包到的Element，通过该Element可生成xml文档
	 */
	@Override
	public void packDataIntoElement(Element fatherElement,String dealType) {
		// TODO Auto-generated method stub
		Element gd = fatherElement.addElement(this.getClass().getSimpleName());
		gd.addAttribute(Tools.dealType, dealType);
		gd.addAttribute(Tools.content, this.id + Tools.contentDelimiter + this.name + Tools.contentDelimiter
				+ this.barCode + Tools.contentDelimiter + this.type + Tools.contentDelimiter + this.supplierID 
				+ Tools.contentDelimiter + this.priceIn + Tools.contentDelimiter + this.priceOut + Tools.contentDelimiter
				+ this.validDate + Tools.contentDelimiter + this.category + Tools.contentDelimiter + this.comment 
				+ Tools.contentDelimiter + this.remainAmount + Tools.contentDelimiter + this.warnAmount);
		
	}
	/**
	 * Description:从Element dataModelElement中提取GoodsData
	 * @param Element dataModelElement:保存有GoodsData数据的Element
	 * @exception DatatypeConfigurationException ： 格式不匹配
	 */
	@Override
	protected void getDatafromElement(Element dataModelElement) throws DatatypeConfigurationException{
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			String content = dataModelElement.attributeValue(Tools.content);
			String[] temp = content.split(Tools.contentDelimiter);
			this.id=temp[0];
			this.name=temp[1];
			this.barCode=temp[2];
			this.type=temp[3];
			this.supplierID=temp[4];
			this.priceIn=temp[5].equals("null")?null:Double.valueOf(temp[5]);
			this.priceOut=temp[6].equals("null")?null:Double.valueOf(temp[6]);
			this.validDate=temp[7].equals("null")?null:Date.valueOf(temp[7]);
			this.category=temp[8];
			this.comment=temp[9];
			this.remainAmount = temp[10].equals("null")?null:Double.valueOf(temp[10]);
			this.warnAmount = temp[11].equals("null")?null:Double.valueOf(temp[11]);
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:打印该GoodsData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id); 
		System.out.println(nameRN + ":" + this.name);
		System.out.println(barCodeRN + ":" + this.barCode);
		System.out.println(typeRN + ":" + this.type);
		System.out.println(supplierIDRN + ":" + this.supplierID);
		System.out.println(priceInRN + ":" + this.priceIn);
		System.out.println(priceOutRN + ":" + this.priceOut);
		System.out.println(validDateRN + ":" + this.validDate);
		System.out.println(categoryRN + ":" + this.category);
		System.out.println(commentRN + ":" + this.comment);
		System.out.println(remainAmountRN + ":" + this.remainAmount);
		System.out.println(warnAmountRN + ":" + this.warnAmount);
	}
}
