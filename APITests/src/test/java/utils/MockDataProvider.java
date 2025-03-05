package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class MockDataProvider {

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

    public static String getDeleteSeekerProfResumeMockData(int id)  {
        return getMockData("deleteSeekerProfResume", "$id", String.valueOf(id));
    }

    public static String getUpdateSeekerProfResumePortfoliosMockData(int id)  {
        return getMockData("updateSeekerProfResumePortfolios", "$id", String.valueOf(id));
    }

    public static String getUpdateSeekerProfResumePositionMockData(int id, String position)  {
        return getMockData("updateSeekerProfResumePosition", Map.of("$id",String.valueOf(id),
                "$position", position));
    }

    public static String getUpdateSeekerProfResumeScheduleMockData(int id, int schedule1, int schedule2)  {

        return getMockData("updateSeekerProfResumeSchedule", Map.of("$id", String.valueOf(id),
                "$scheduleId1", String.valueOf(schedule1), "$scheduleId2", String.valueOf(schedule2)));
    }

    public static String getUpdateSeekerProfResumeSalaryMockData(int id, int salary, String currency) {
        return getMockData("updateSeekerProfResumeSalary", Map.of("$id", String.valueOf(id),
                "$salary", String.valueOf(salary), "$currency", currency));
    }

    public static String getCreateProfResumeAsCopyMockData()  {
        return getMockData("createProfResumeAsCopy");
    }
}
