package database;
/**
 * Description:进货数据封装
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

public class PurchaseData extends DataModel {
	/*进货单号（CHAR(20) NOT NULL），商品ID（外键商品表CHAR(20) NOT NULL），
	进价(DOUBLE NOT NULL)，进量(DOUBLE NOT NULL)，进货时间(DATETIME(系统自动获取当前时间))，
	经办人ID（外键员工表CHAR(20) NOT NULL）*/
	public final static String goodsIDRN="goodsID";//商品ID
	public final static String priceInRN="priceIn";//进价
	public final static String amountRN="weight";//进货量（单位kg）
	public final static String dateInRN="dateIn";//进货日期
	public final static String operatorRN="operator";//操作员
	
	private String goodsID;//商品ID
	private double priceIn;//进价
	private double amount;//进货量（单位kg）
	private Date dateIn;//进货日期
	private String operator;//操作员
	
	public PurchaseData(String purchaseID,String goodsID,double priceIn,double amount,Date dateIn,String operator){
		this.id = purchaseID;
		this.goodsID = goodsID;
		this.priceIn = priceIn;
		this.amount = amount;
		this.dateIn = dateIn;
		this.operator = operator;
	}
	/**
	 * Description:构造函数，传入xml中关于PurchaseData的Element节点
	 * @param Element dataModelElement:xml中关于PurchaseData的Element节点
	 * @exception DatatypeConfigurationException ：数据格式不匹配
	 */
	public PurchaseData(Element dataModelElement) {
		try {
			getDatafromElement(dataModelElement);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setGoodsID(String goodsID){
		this.goodsID = goodsID;
	}
	public String getGoodsID(){
		return this.goodsID;
	}
	
	public void setPriceIn(double priceIn){
		this.priceIn = priceIn;
	}
	public double getPriceIn(){
		return this.priceIn;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	public double getAmount(){
		return this.amount;
	}
	
	public void setDateIn(Date dateIn){
		this.dateIn = dateIn;
	}
	public Date getDateIn(){
		return this.dateIn;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	public String getOperator(){
		return this.operator;
	}
	/**
	 * Description:将该PurchaseData打包到Element中
	 * @param Element fatherElement:要打包到的Element，通过该Element可生成xml文档
	 */
	@Override
	public void packDataIntoElement(Element fatherElement) {
		// TODO Auto-generated method stub
		Element pd = fatherElement.addElement(this.getClass().getSimpleName());
		pd.addElement(idRN).addText(this.id);
		pd.addElement(goodsIDRN).addText(this.goodsID);
		pd.addElement(priceInRN).addText(this.priceIn+"");
		pd.addElement(amountRN).addText(this.amount+"");
		pd.addElement(dateInRN).addText(this.dateIn+"");
		pd.addElement(operatorRN).addText(this.operator);
	}
	/**
	 * Description:从Element dataModelElement中提取PurchaseData
	 * @param Element dataModelElement:保存有PurchaseData数据的Element
	 * @exception DatatypeConfigurationException ： 格式不匹配
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			this.id = dataModelElement.elementText(idRN);
			this.goodsID = dataModelElement.elementText(goodsIDRN);
			this.priceIn = Double.valueOf(dataModelElement.elementText(priceInRN));
			this.amount = Double.valueOf(dataModelElement.elementText(amountRN));
			this.dateIn = Date.valueOf(dataModelElement.elementText(dateInRN));
			this.operator = dataModelElement.elementText(operatorRN);
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:打印该PurchaseData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id);
		System.out.println(goodsIDRN + ":" + this.goodsID);
		System.out.println(priceInRN + ":" + this.priceIn);
		System.out.println(amountRN + ":" + this.amount);
		System.out.println(dateInRN + ":" + this.dateIn);
		System.out.println(operatorRN + ":" + this.operator);
	}
}
