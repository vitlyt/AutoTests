package data;

import org.testng.annotations.DataProvider;

public class Resume {


    private final String position = "Кухар";
    private final int salary = 15000;

    private final String employmentType = "Неповна зайнятість";


    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public String getEmploymentType() {
        return employmentType;
    }


    @DataProvider(name = "resume-data-provider")
    public Object[][] get(){
        return new Object[][] {{new Resume()}};
    }

}
