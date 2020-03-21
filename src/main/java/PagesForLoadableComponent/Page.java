package PagesForLoadableComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import utilsForLoadableComponent.ThreadLocalDriver;

public class Page {

    private ConcurrentMap<WebDriver, ConcurrentMap<String, Object>> pageHashMap = new ConcurrentHashMap<>();

    private synchronized void setPageHashMap(WebDriver driver, String pageName, Object obj) {
        if (!pageHashMap.containsKey(driver)) {
            ConcurrentMap<String, Object> pageHashMapPageNamePageObj = new ConcurrentHashMap<>();
            pageHashMapPageNamePageObj.putIfAbsent(pageName, obj);
            pageHashMap.put(driver, pageHashMapPageNamePageObj);
        } else {
            if (pageHashMap.get(driver).containsKey(pageName)) {
                pageHashMap.get(driver).put(pageName, obj);
            }
        }
    }

    private synchronized Object getPageHashMap(WebDriver driver, String pageName) {
        if(pageHashMap.containsKey(driver)) {
            return pageHashMap.get(driver).get(pageName);
        } else {
            return null;
        }
    }

    //This method creates a new screen with given Screen Name and Screen Class parameters
    private synchronized <TPage extends LoadableComponent<TPage>> TPage createPage(String pageName, Class<TPage> Clazz) {
        TPage tPage = (TPage) getPageHashMap(ThreadLocalDriver.getDriver(), pageName);
        if(tPage != null) {
            return tPage;
        } else {
            tPage = instantiateNewPage(ThreadLocalDriver.getDriver(), Clazz);
            setPageHashMap(ThreadLocalDriver.getDriver(), pageName, tPage.get());
            return (TPage) getPageHashMap(ThreadLocalDriver.getDriver(), pageName);
        }
    }

    //This Method Instantiates a new screen. I got this method from PageFactory Class and modify it.
    private synchronized <TPage extends LoadableComponent<TPage>> TPage instantiateNewPage(WebDriver driver, Class<TPage> Clazz) {
        try {
            try {
                Constructor<TPage> constructor = Clazz.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException var3) {
                return Clazz.newInstance();
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized HomePage homePage() {
        return createPage("homePage", HomePage.class);
    }

    public synchronized LoginPage loginPage() {
        return createPage("loginPage", LoginPage.class);
    }

}
