package dataproviders;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class AuthDataProvider {
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";
    public static final String IS_VALID_FIELD = "is_valid";

    @DataProvider(name = "auth-data-provider")
    public Object[][] getInValidCredentials(){
        Map<String, String> validCreds = new HashMap<>(){{
            put(USERNAME_FIELD, System.getProperty("username"));
            put(PASSWORD_FIELD, System.getProperty("password"));
            put(IS_VALID_FIELD, String.valueOf(true));
        }};
        Map<String, String> invalidCreds = new HashMap<>(){{
            put(USERNAME_FIELD, "nonexistent@test.com");
            put(PASSWORD_FIELD, "password123");
            put(IS_VALID_FIELD, String.valueOf(false));
        }};

        return new Object[][] {{invalidCreds}, {validCreds}};
    }
}
