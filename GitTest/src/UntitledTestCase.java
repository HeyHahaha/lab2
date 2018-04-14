
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;



public class UntitledTestCase {
  private WebDriver driver;
  //private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
	XSSFWorkbook xwb = new XSSFWorkbook("/Users/jiawen/Downloads/input.xlsx");  
    XSSFSheet sheet = xwb.getSheetAt(0);  
	XSSFRow row;
	String username;
	String password;
	String gitUrl;
	String url;
	
	for(int i = 80; i < sheet.getPhysicalNumberOfRows(); i ++) {
		row = sheet.getRow(i);
		int cellnum = row.getFirstCellNum();
		if(row.getCell(cellnum).getCellTypeEnum().equals(CellType.NUMERIC)) {
			username = new BigDecimal(row.getCell(cellnum).getNumericCellValue()).toString();
		}else {
			username = row.getCell(cellnum).getStringCellValue();
		}
		password = username.substring(4);
		gitUrl = row.getCell(cellnum + 1).getStringCellValue();
		if(gitUrl.endsWith("/")) {
			gitUrl = gitUrl.substring(0, gitUrl.length() - 1).trim();
		}
		if(gitUrl.startsWith(" ")) {
			gitUrl = gitUrl.substring(1, gitUrl.length()).trim();
		}
		while(gitUrl.endsWith(" ")) {
			gitUrl = gitUrl.substring(0, gitUrl.length() - 1).trim();
		}
		gitUrl = gitUrl.replaceAll("\\s*|\t|\r|\n", "");
		if(gitUrl != null && gitUrl != ""){
			driver.get("https://psych.liebes.top/st");
			driver.findElement(By.id("username")).click();
		    driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys(username);
		    driver.findElement(By.id("password")).click();
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(password);
		    driver.findElement(By.id("submitButton")).click();
		    url = driver.findElement(By.xpath("//p")).getText();
		    if(url.endsWith("/")){
		    	url = url.substring(0, url.length() - 1).trim();
		    }
		    System.out.println(username + " && " +password + " && " +url);
		    assertEquals(gitUrl, url);
		}else {
			System.out.println("The student doesn't have a gitHuburl");
		}
	}
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
