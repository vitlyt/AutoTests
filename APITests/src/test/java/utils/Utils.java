package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
    private static final String URI_PROTOCOL = "https://";
    private static final String SUBDOMAIN_NAME = "dracula";
    private static final String DOMAIN_NAME;

    static {
        String base64Domain = loadJsonData("paths").get("domain");
        byte[] decodedBytes = Base64.getDecoder().decode(base64Domain);
        DOMAIN_NAME = new String(decodedBytes);
    }

    public static String getDomainName(){
        return DOMAIN_NAME;
    }

    public static Map<String, String> loadJsonData(String path){
        Map<String, String> result = new HashMap<>();
        String filePath = Objects.requireNonNull(Utils.class.getClassLoader().getResource(path.concat(".json"))).getPath();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            final JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            jsonNode.fieldNames().
                    forEachRemaining(e -> result.put(e,  jsonNode.get(e).asText()));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;

    }

    public static Object loadJson(String filePath, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object result = null;
        String path = Objects.requireNonNull(Utils.class.getClassLoader().getResource(filePath.concat(".json"))).getPath();
        try {
            result = objectMapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }



    private static String getURI(String subdomainName, String path)  {
        return String.join(DOMAIN_NAME, URI_PROTOCOL.concat(subdomainName.concat(".")), "/".concat(path));
    }


    public static String getDefaultURI() {
        return String.join(SUBDOMAIN_NAME.concat("."), URI_PROTOCOL, DOMAIN_NAME);
    }


    public static String preparePhotoURL(String photoURL){
        return String.format(photoURL, Utils.getDomainName()).replace("ro", "ra");
    }

    public static String getLoginURI() {
        return getURI("auth-api", "Login");
    }

    public static String getLogoutURI()  {
        return getURI("auth-api", "Logout");
    }


    private static boolean matches(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean matchesDateFormat(String date){
        return matches(date, "\\d{4}\\-\\d{2}\\-\\d{2}");
    }


    public static boolean matchesDateTimeFormat(String dateTime){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        try {
            dateFormat.parse(dateTime);
            return true;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    public static boolean matchesEmailFormat(String email){
        return matches(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean matchesPhoneNumberFormat(String phoneNumber){
        return matches(phoneNumber, "^380\\d{9}");
    }

    public static boolean matchesDigitGT0Format(int id){
        return matches(String.valueOf(id), "^([1-9][0-9]+|[1-9])$");
    }

}
