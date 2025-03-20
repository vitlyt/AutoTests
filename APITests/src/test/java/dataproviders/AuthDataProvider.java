package dataproviders;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;


public class AuthDataProvider {

    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";

    @DataProvider(name = "invalid-auth-data-provider")
    public Object[][] getValidCredentials(){
        Map<String, String> params = new HashMap<>(){{
            put(USERNAME_FIELD, "nonexistent@test.com");
            put(PASSWORD_FIELD, "password123");
        }};
        return new Object[][] {{params}};
    }
    @DataProvider(name = "valid-auth-data-provider")
    public Object[][] getInValidCredentials(){
        Map<String, String> params = new HashMap<>(){{
            put(USERNAME_FIELD, System.getProperty("username"));
            put(PASSWORD_FIELD, System.getProperty("password"));
        }};

        return new Object[][] {{params}};
    }
}
