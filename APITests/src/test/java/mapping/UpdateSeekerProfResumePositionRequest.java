package mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSeekerProfResumePositionRequest extends ProfResumeRequest{

    public UpdateSeekerProfResumePositionRequest(PositionInput input, String operationName, String query) {
        super(input, operationName, query);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    public static class PositionInput extends Input{
        protected String position;

        public PositionInput(String resumeId, String position) {
            super(resumeId);
            this.position = position;
        }
    }
}
