package database;
/**
 * Description:Ա�������ݷ�װ
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

public class StaffData extends DataModel {
	/*Ա��ID������+���CHAR(20) NOT NULL PRIMARY KEY����Ա����(CHAR(50))��
	���֤��(CHAR(50))��Ա������(INT)��Ա���Ա�(CHAR(1))��Ա��ְλ(CHAR(50))��
	�������ţ�CHAR(50)����Ա����ϵ��ʽ(CHAR(30))��Ա��н��(INT)����ְʱ��(DATE)��
	��ְʱ�䣨DATE DEFAULT NULL��*/
	public final static String staffNameRN="staffName";
	public final static String passwordRN="password";
	public final static String authorityRN="authority";
	public final static String staffIDNoRN="staffIDNo";//���֤����
	public final static String staffAgeRN="staffAge";//���Ʊ������18 С��70
	public final static String staffGenderRN="staffGender";//���Ʊ���ʹ��F����Ů�ԣ�M��������
	public final static String staffPositionRN="staffPosition";//Ա��ְλ
	public final static String staffDepartmentRN="staffDepartment";
	public final static String staffTelRN="staffTel";
	public final static String staffSalaryRN="staffSalary";
	public final static String staffHireDateRN="staffHireDate";
	public final static String staffDimissionDateRN="staffDimissionDate";//��ְʱ��

	private String staffName;
	private String password;
	private int authority;
	private String staffIDNo;//���֤����
	private int staffAge;
	private String staffGender;
	private String staffPosition;//Ա��ְλ
	private String staffDepartment;
	private String staffTel;
	private double staffSalary;
	private Date staffHireDate;
	private Date staffDimissionDate;//��ְʱ��
	
	public StaffData(String staffID,String staffName,String password,int authority,String staffIDNo,int staffAge,String staffGender,String staffPosition,
			String staffDepartment,String staffTel,double staffSalary,Date staffHireDate,Date staffDimissionDate){
		this.id=staffID;//char
		this.staffName=staffName;
		this.password=password;
		this.authority=authority;
		this.staffIDNo=staffIDNo;//���֤����
		this.staffAge=staffAge;
		this.staffGender=staffGender;
		this.staffPosition=staffPosition;//Ա��ְλ
		this.staffDepartment=staffDepartment;
		this.staffTel=staffTel;
		this.staffSalary=staffSalary;
		this.staffHireDate=staffHireDate;
		this.staffDimissionDate=staffDimissionDate;//��ְʱ��
	}
	/*public StaffData(StaffData sd){
		this.id=sd.id;//char
		this.staffName=sd.staffName;
		this.password=sd.password;
		this.authority=sd.authority;
		this.staffIDNo=sd.staffIDNo;//���֤����
		this.staffAge=sd.staffAge;
		this.staffGender=sd.staffGender;
		this.staffPosition=sd.staffPosition;//Ա��ְλ
		this.staffDepartment=sd.staffDepartment;
		this.staffTel=sd.staffTel;
		this.staffSalary=sd.staffSalary;
		this.staffHireDate=sd.staffHireDate;
		this.staffDimissionDate=sd.staffDimissionDate;//��ְʱ��
	}*/
	/**
	 * Description:���캯��������xml�й���StaffData��Element�ڵ�
	 * @param Element dataModelElement:xml�й���StaffData��Element�ڵ�
	 * @exception DatatypeConfigurationException �����ݸ�ʽ��ƥ��
	 */
	public StaffData(Element dataModelElement) {
		try {
			getDatafromElement(dataModelElement);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setStaffName(String staffName){
		this.staffName=staffName;
	}
	public String getStaffName(){
		return 	this.staffName;
	}
	
	public void setpassword(String password){
		this.password=password;
	}
	public String getpassword(){
		return this.password;
	}
	
	public void setauthority(int authority){
		this.authority=authority;
	}
	public int getauthority(){
		return this.authority;
	}
	
	public void setStaffIDNo(String staffIDNo){
		this.staffIDNo=staffIDNo;
	}
	public String getStaffIDNo(){
		return 	this.staffIDNo;
	}
	
	public void setStaffAge(int staffAge){
		this.staffAge=staffAge;
	}
	public int getStaffAge(){
		return 	this.staffAge;
	}
	
	public void setStaffGender(String staffGender){
		this.staffGender=staffGender;
	}
	public String getStaffGender(){
		return 	this.staffGender;
	}
	
	public void setStaffPosition(String staffPosition){
		this.staffPosition=staffPosition;
	}
	public String getStaffPosition(){
		return 	this.staffPosition;
	}
	
	public void setStaffDepartment(String staffDepartment){
		this.staffDepartment=staffDepartment;
	}
	public String getStaffDepartment(){
		return 	this.staffDepartment;
	}
	
	public void setStaffTel(String staffTel){
		this.staffTel=staffTel;
	}
	public String getStaffTel(){
		return 	this.staffTel;
	}
	
	public void setStaffSalary(double staffSalary){
		this.staffSalary=staffSalary;
	}
	public double getStaffSalary(){
		return 	this.staffSalary;
	}
	
	public void setStaffHireDate(Date staffHireDate){
		this.staffHireDate=staffHireDate;
	}
	public Date getStaffHireDate(){
		return 	this.staffHireDate;
	}
	
	public void setStaffDimissionDate(Date staffDimissionDate){
		this.staffDimissionDate=staffDimissionDate;
	}
	public Date getStaffDimissionDate(){
		return 	this.staffDimissionDate;
	}
	
	/**
	 * Description:��дequals�������������StaffData��staffID��ͬ����Ϊ����StaffData�������
	 * @param Object obj��CheckUserDataʵ��
	 * @return ����CheckUserData�Ƿ����
	 */
	@Override
	public boolean equals(Object obj){
		StaffData sd=null;
		if(obj instanceof StaffData){
			sd = (StaffData)obj;
			if(this.id==sd.id){
				return true;
			}
		}
		return false;
	}
	/**
	 * Description:����StaffData�����Element��
	 * @param Element fatherElement:Ҫ�������Element��ͨ����Element������xml�ĵ�
	 */
	@Override
	public void packDataIntoElement(Element fatherElement){
		//Element sd = new Element;
		Element sd = fatherElement.addElement(this.getClass().getSimpleName());
		sd.addElement(idRN).addText(this.id);
		sd.addElement(staffNameRN).addText(this.staffName);
		sd.addElement(passwordRN).addText(this.password);
		sd.addElement(authorityRN).addText(this.authority+"");
		sd.addElement(staffIDNoRN).addText(this.staffIDNo);
		sd.addElement(staffAgeRN).addText(this.staffAge+"");
		sd.addElement(staffGenderRN).addText(this.staffGender);
		sd.addElement(staffPositionRN).addText(this.staffPosition);
		sd.addElement(staffDepartmentRN).addText(this.staffDepartment);
		sd.addElement(staffTelRN).addText(this.staffTel);
		sd.addElement(staffSalaryRN).addText(this.staffSalary+"");
		sd.addElement(staffHireDateRN).addText(this.staffHireDate+"");
		sd.addElement(staffDimissionDateRN).addText(this.staffDimissionDate+"");
	}
	/**
	 * Description:��Element dataModelElement����ȡStaffData
	 * @param Element dataModelElement:������StaffData���ݵ�Element
	 * @exception DatatypeConfigurationException �� ��ʽ��ƥ��
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			this.id=dataModelElement.elementText(idRN);
			this.staffName=dataModelElement.elementText(staffNameRN);
			this.password=dataModelElement.elementText(passwordRN);
			this.authority=Integer.valueOf(dataModelElement.elementText(authorityRN));
			this.staffIDNo=dataModelElement.elementText(staffIDNoRN);
			this.staffAge=Integer.valueOf(dataModelElement.elementText(staffAgeRN));
			this.staffGender=dataModelElement.elementText(staffGenderRN);
			this.staffPosition=dataModelElement.elementText(staffPositionRN);
			this.staffDepartment=dataModelElement.elementText(staffDepartmentRN);
			this.staffTel=dataModelElement.elementText(staffTelRN);
			this.staffSalary = Double.valueOf(dataModelElement.elementText(staffSalaryRN));
			this.staffHireDate = Date.valueOf(dataModelElement.elementText(staffHireDateRN));
			this.staffDimissionDate = Date.valueOf(dataModelElement.elementText(staffDimissionDateRN));
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:��ӡ��StaffData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id); 
		System.out.println(staffNameRN + ":" + this.staffName);
		System.out.println(passwordRN + ":" + this.password);
		System.out.println(authorityRN + ":" + this.authority);
		System.out.println(staffIDNoRN + ":" + this.staffIDNo);
		System.out.println(staffAgeRN + ":" + this.staffAge);
		System.out.println(staffGenderRN + ":" + this.staffGender);
		System.out.println(staffPositionRN + ":" + this.staffPosition);
		System.out.println(staffDepartmentRN + ":" + this.staffDepartment);
		System.out.println(staffTelRN + ":" + this.staffTel);
		System.out.println(staffSalaryRN + ":" + this.staffSalary);
		System.out.println(staffHireDateRN + ":" + this.staffHireDate);
		System.out.println(staffDimissionDateRN + ":" + this.staffDimissionDate);
	}
}
