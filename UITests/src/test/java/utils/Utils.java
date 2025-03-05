package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
    ;
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


    public static String getURI(String path)  {
        return String.join(DOMAIN_NAME, URI_PROTOCOL, path);
    }

    public static int parseInt(String numberStr){
        return Integer.parseInt(numberStr.replaceAll("\\s",""));
    }

    public static String normalizeStr(String str){
        return str.replaceAll("\"", "").trim();
    }


}