import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleTest {

	private WebDriver driver;
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("file:///C:/Users/user/Desktop/Text/Website/text.html");
	}

	@After
	public void tearDown() 
	{
		driver.quit();
	}

	@Test
	//test if the output1 is the correct answer.
	public void inputMeesage1()
	{
		type(By.id("txt"),"-12413");
		click(By.id("ok1"));
		assertEquals("12413", driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	public void type(By by, String value)
	{
		driver.findElement(by).sendKeys(value);
	}

	public void click(By by)
	{
		driver.findElement(by).click();
	}

	@Test
	//test if the input1 is not number, output shows the wrong info. 
	public void inputOnlyNumbers1()
	{
		type(By.id("txt"),"aiofj.a.sd");
		click(By.id("ok1"));
		assertEquals("please input only numbers!",driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	@Test
	// test if the input1 start with 0 as numbers, output please start with none zero!
	public void inputZeroStart1()
	{
		type(By.id("txt"),"0123");
		click(By.id("ok1"));
		assertEquals("please start with none zero!",driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	@Test
	// test if the input1 is too big
	public void inputBignum1()
	{
		type(By.id("txt"),"999999999999999999999999999999999999999999999999");
		click(By.id("ok1"));
		assertEquals("the value is too big!",driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	@Test
	// test if the input1 is too little
	public void inputLittlenum1()
	{
		type(By.id("txt"),"-999999999999999999999999999999999999999999999999");
		click(By.id("ok1"));
		assertEquals("the value is too little!",driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	@Test
	// test if there is no input1
	 public void inputNone1()
	{
		type(By.id("txt"),"");
		click(By.id("ok1"));
		assertEquals("There is no input!",driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	@Test
	// test if input integers!
	 public void inputInteger1()
	{
		type(By.id("txt"),"1.23");
		click(By.id("ok1"));
		assertEquals("please input integers!",driver.findElement(By.id("txt2")).getAttribute("value"));
	}

	@Test
	//test if the third letter could be upper letter
	public void inputMeesage2()
	{
		type(By.id("word"),"sdada");
		click(By.id("ok2"));
		String output2 = "sdAda";
		assertEquals(output2, driver.findElement(By.id("word2")).getAttribute("value"));
	}

	@Test
	//test if the input is not letters
	public void inputOnlyLetters2()
	{
		type(By.id("word"),"1221,2123dfsf");
		click(By.id("ok2"));
		String output2 = "please input only letters!";
		assertEquals(output2, driver.findElement(By.id("word2")).getAttribute("value"));
	}

	@Test
	//test if the input is less than 3 letters or none.
	public void inputEnoughLetters2()
	{
		type(By.id("word"),"ss");
		click(By.id("ok2"));
		String output2 = "please input enough letters!";
		assertEquals(output2, driver.findElement(By.id("word2")).getAttribute("value"));
	}

	@Test
	//test if there is no input.
	public void inputNoLetters2()
	{
		type(By.id("word"),"");
		click(By.id("ok2"));
		String output2 = "No input letters!";
		assertEquals(output2, driver.findElement(By.id("word2")).getAttribute("value"));
	}

	@Test
	//Test if the function can work
	public void inputMeesage3()
	{
		type(By.id("list"),"22,11,42,2,34");
		click(By.id("ok3"));
		String output3 = "2,11,22,34,42";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if input wrong format, output the wrong info.
	public void inputCorrectFormat3()
	{
		type(By.id("list"),".wqeq,qreq,123");
		click(By.id("ok3"));
		String output3 = "please input right format";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if the input negative numbers and positive numbers together. 
	public void inputNegNumbers3()
	{
		type(By.id("list"),"-12,23,-10,25,-99");
		click(By.id("ok3"));
		String output3 = "-99,-12,-10,23,25";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if the input negative numbers and positive numbers together. 
	public void inputDecimal3()
	{
		type(By.id("list"),"-12.22,-2222.33,222.23,99.999");
		click(By.id("ok3"));
		String output3 = "-2222.33,-12.22,99.999,222.23";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if there is no input. 
	public void inputNoString3()
	{
		type(By.id("list"),"");
		click(By.id("ok3"));
		String output3 = "No input number string!";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if the input number with 0 start.  
	public void inputZerostart3()
	{
		type(By.id("list"),"012,023,099,099999");
		click(By.id("ok3"));
		String output3 = "No zero start numbers!";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if one of the input numbers is too big.  
	public void inputBignum3()
	{
		type(By.id("list"),"1,99999999999999999999999999999999999999999999999999999999999999999999,99,9999");
		click(By.id("ok3"));
		String output3 = "one of numbers is too big!";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

	@Test
	//Test if one of the input numbers is too big.  
	public void inputLittlenum3()
	{
		type(By.id("list"),"1,-99999999999999999999999999999999999999999999999999999999999999999999,99,9999");
		click(By.id("ok3"));
		String output3 = "one of numbers is too little!";
		assertEquals(output3, driver.findElement(By.id("list2")).getAttribute("value"));
	}

}