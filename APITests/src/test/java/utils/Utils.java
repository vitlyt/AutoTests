package utils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
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
        String filePath = Objects.requireNonNull(Utils.class.getClassLoader().getResource("paths.json")).getPath();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }


        String base64Domain = jsonNode.get("domain").asText();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Domain);
        DOMAIN_NAME = new String(decodedBytes);
    }


    private static String getURI(String subdomainName, String path)  {
        return String.join(DOMAIN_NAME, URI_PROTOCOL.concat(subdomainName.concat(".")), "/".concat(path));
    }


    public static String getDefaultURI() {
        return String.join(SUBDOMAIN_NAME.concat("."), URI_PROTOCOL, DOMAIN_NAME);
    }

    public static String getPhotoURL(){
        return getURI("cv-photos-original", "non-photo.png").replace("ro", "ra");
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
