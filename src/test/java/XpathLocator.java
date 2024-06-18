import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class XpathLocator {
	public static Playwright playwright = Playwright.create();
	public static Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	public static Page page = browser.newPage();
	
	@SuppressWarnings("static-access")
	public XpathLocator(Page page) {
		this.page =page;
		PageFactory.initElements(page, this);
	}
	
	@FindBy(xpath="//a[text()='Joe.Root']//parent::td/..//following-sibling::td//input[@type='checkbox']")
	private static Locator checkBox;
	
	 public static List<Locator> getLocators(Locator element){
		List<Locator> elements = new ArrayList<>();
		int count = element.count();
		for(int i=0;i<count;i++) {
			elements.add(element.nth(i));
		}
		return elements;
	}
	
	public static void clickCheckBoxes(String username) {
		Locator element =page.locator("//a[text()='"+username+"']//parent::td/..//following-sibling::td//input[@type='checkbox']");
		element.click();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		page.navigate("https://selectorshub.com/xpath-practice-page/");
		checkBox.click();
		List<Locator> elements = getLocators(page.locator("//a[text()='Username']//ancestor::thead/following-sibling::tbody//tr//td[2]"));
		List<String> texts = new ArrayList<>();
		for(Locator ele : elements) {
			texts.add(ele.innerText());
		}
		for(String text:texts) {
			clickCheckBoxes(text);
		}
		System.out.println(texts);
		
	}

}
