package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import static utils.Utils.*;

public class MockDataProvider {

    public static final String OPERATION_NAME_PROPERTY = "operationName";
    public static final String QUERY_PROPERTY = "query";

    private static final Logger LOGGER = LoggerFactory.getLogger(MockDataProvider.class);

    private static String getMockData(String fileName) {
        String result = null;
        String filePath = Objects.requireNonNull(Utils.class.getClassLoader().getResource(String.join(fileName,
                "mock_data/", ".json"))).getPath();
        try {
            result = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;

    }

    private static Map<String, String> getMockDataMap(String fileName) {
        return loadJsonData("mock_data/".concat(fileName));
    }

    public static Object getMockDataObject(String fileName, Class clazz) {
        return loadJson("mock_data/".concat(fileName), clazz);

    }


    private static String getMockData(String fileName, String var, String value) {
        return getMockData(fileName).replace(var, value);
    }

    private static String getMockData(String fileName, Map<String, String> vars) {
        String json = getMockData(fileName);
        for (var entry : vars.entrySet()) {
            json = json.replace(entry.getKey(), entry.getValue());
        }

        return json;
    }

    public static String getPublishedVacanciesListMockData()  {
        return getMockData("getPublishedVacanciesList");
    }

    public static String getPublishedVacancyMockData()  {
        return getMockData("getPublishedVacancy");
    }

    public static String getSeekerResumesMockData()  {
        return getMockData("getSeekerResumes");
    }

    public static String getSeekerResumeMockData(int id)  {
        return getMockData("getSeekerResume", "$id", String.valueOf(id));
    }

    public static String getInvalidOperationMockData() {
        return getMockData("invalidOperation");
    }

    public static String getInvalidQueryMockData()  {
        return getMockData("invalidQuery");
    }

    public static Map<String, String>  getDeleteSeekerProfResumeMockData( ) {
        return getMockDataMap("deleteSeekerProfResume");
    }

    public static Map<String, String>  getUpdateSeekerProfResumePortfoliosMockData()  {
        return getMockDataMap("updateSeekerProfResumePortfolios");
    }

    public static Map<String, String> getUpdateSeekerProfResumePositionMockData() {
        return getMockDataMap("updateSeekerProfResumePosition");
    }

    public static Map<String, String> getUpdateSeekerProfResumeScheduleMockData() {
        return getMockDataMap("updateSeekerProfResumeSchedule");
    }

    public static Map<String, String> getUpdateSeekerProfResumeSalaryMockData() {
        return getMockDataMap("updateSeekerProfResumeSalary");
    }

    public static Map<String, String> getCreateProfResumeAsCopyMockData() {
        return getMockDataMap("createProfResumeAsCopy");
    }
}
