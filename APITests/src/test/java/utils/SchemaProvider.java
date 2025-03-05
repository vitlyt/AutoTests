package utils;



public class SchemaProvider {

    private static String getSchema(String schemaName){
        return String.join(schemaName, "schemas/", ".json");
    }

    public static String getPublishedVacanciesListSchema(){
        return getSchema("getPublishedVacanciesList");
    }

    public static String getPublishedVacancySchema(){
        return getSchema("getPublishedVacancy");
    }

    public static String getSeekerResumesSchema(){
        return getSchema("getSeekerResumes");
    }

    public static String getCreateProfResumeAsCopySeekerSchema(){
        return getSchema("createProfResumeAsCopy");
    }

    public static String getUpdateSeekerProfResumePositionSchema(){
        return getSchema("updateSeekerProfResumePosition");
    }

    public static String getUpdateSeekerProfResumeScheduleSchema(){
        return getSchema("updateSeekerProfResumeSchedule");
    }

    public static String getUpdateSeekerProfResumeSalarySchema(){
        return getSchema("updateSeekerProfResumeSalary");
    }

    public static String getSeekerResumeSchema(){
        return getSchema("getSeekerResume");
    }

    public static String getDeleteSeekerProfResumeSchema(){
        return getSchema("deleteSeekerProfResume");
    }

    public static String getInvalidUserDataSeekerResumeSchema(){
        return getSchema("invalidUserDataGetSeekerResume");
    }

    public static String getErrorSchema(){
        return getSchema("error");
    }


}
