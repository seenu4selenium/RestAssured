package com.objectrepository;

import org.openqa.selenium.By;



public class Locaters {

	// public final By WEBELEMENTNAME_TYPE = By.xpath("LocatorValue");
	// Login screen
	public final By SigninPage_Username_EditBox = By.xpath("//*[@id='username']");
	public final By SigninPage_Password_EditBox = By.xpath("//*[@id='userpword']");
	public final By SigninPage_Login_Button = By.xpath("//button[text()='LOGIN']");
	
	public final By PM_Next_Button = By.xpath("//*[@value='Next']");
	public final By PM_Prev_Button = By.xpath("//*[@value='Prev']");
	
	public final By tagNames = By.xpath("//input");
	public final By dropDowns = By.xpath("//select");
	public final By hyperLinks = By.xpath("//a");
	public final By buttons = By.xpath("//*[@type='button']");
	public final By editboxs = By.xpath("//*[@type='text']"); 
	public final By tables = By.xpath("//table");
	public final By checkboxs = By.xpath("//*[@type='checkbox']");
	public final By radioButtons = By.xpath("//*[@type='radio']");
	
	//Menu's
	public final By validations_Menu = By.xpath("//a[text()='Validations']");
	public final By possibleMerge_Menu = By.xpath("//a[text()='Possible Merge']");
	public final By unMerge_Menu = By.xpath("//a[text()='UnMerge']");
	public final By Merge_Menu = By.xpath("//a[text()='Merge']");
	
	//Validation Screen 
	public final By validation_OpenTask = By.xpath("//*[text()='Open Tasks']");
	public final By  CustomerNumber = By.xpath("//*[@id=''body]/div[2]/div/table/thead/tr/th[1]");
	public final By  SourceSystem = By.xpath("//*[@id=''body]/div[2]/div/table/thead/tr/th[2]");
	public final By  CustomerType = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[3]");
	public final By  FirstName = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[4]");
	public final By  LastName = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[5]");
	public final By  OrganizationName = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[6");
	public final By  AddressKey = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[7]");
	public final By  AddressLine1 = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[8]");
	public final By  AddressLine2 = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[9]");
	public final By  AddressLine3 = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[10]");
	public final By  City = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[11]");
	public final By  State = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[12]");
	public final By  ZipCode = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[13]");
	public final By  Country = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[14]");
	public final By  PrimaryPhoneNumber = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[15]");
	public final By  Email = By.xpath("//*[@id='body']/div[2]/div/table/thead/tr/th[16]");
	
	
			 

}
