package Base;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by gveenam on 2/15/2021.
 */
public class TestBase {
    public Properties prop;
    public TestBase(){
        try{
            prop=new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
        }
        catch (Exception e){

        }
    }
}
