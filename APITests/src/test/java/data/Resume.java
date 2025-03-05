package data;

import org.testng.annotations.DataProvider;

public class Resume {

    private final String position = "Кухар";
    private final int salary = 15000;
    private final String currency = "UAH";
    private final int scheduleId1 = 1;
    private final int scheduleId2 = 4;


    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public String getCurrency() {
        return currency;
    }

    public int getScheduleId1() {
        return scheduleId1;
    }

    public int getScheduleId2() {
        return scheduleId2;
    }


    @DataProvider(name = "resume-data-provider")
    public Object[][] get(){
        return new Object[][] {{new Resume()}};
    }

}
