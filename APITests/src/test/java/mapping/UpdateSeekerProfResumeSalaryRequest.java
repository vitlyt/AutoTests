package mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSeekerProfResumeSalaryRequest extends ProfResumeRequest{

    public UpdateSeekerProfResumeSalaryRequest(SalaryInput input, String operationName, String query) {
        super(input, operationName, query);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SalaryInput extends Input{
        private Salary salary;

        public SalaryInput(String resumeId, Salary salary) {
            super(resumeId);
            this.salary = salary;
        }

        @Data
        @AllArgsConstructor
        public static class Salary {
            private int amount;
            private String currency;
        }
    }
}
