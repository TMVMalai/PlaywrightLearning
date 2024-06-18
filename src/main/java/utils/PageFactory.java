package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

import java.lang.reflect.Field;


public class PageFactory {

	 public static void initElements(Page page, Object pageObject) {
		 Class<?> clazz = pageObject.getClass();
	        Field[] fields = clazz.getDeclaredFields();

	        for (Field field : fields) {
	            if (field.isAnnotationPresent(FindBy.class)) {
	                FindBy findBy = field.getAnnotation(FindBy.class);
	                String xpath = findBy.xpath();
	                Locator locator = page.locator(xpath);
	                field.setAccessible(true); // Ensure the field is accessible

	                try {
	                    field.set(pageObject, locator);
	                } catch (IllegalAccessException e) {
	                    throw new RuntimeException("Failed to set locator for field: " + field.getName(), e);
	                }
	            }
	        }
	    }
}
