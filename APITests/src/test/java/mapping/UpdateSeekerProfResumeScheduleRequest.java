package mapping;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSeekerProfResumeScheduleRequest extends ProfResumeRequest{

    public UpdateSeekerProfResumeScheduleRequest(SchedulesInput input, String operationName, String query) {
        super(input, operationName, query);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SchedulesInput extends Input{
        protected List<Integer> scheduleIds;

        public SchedulesInput(String resumeId, List<Integer> scheduleIds) {
            super(resumeId);
            this.scheduleIds = scheduleIds;
        }
    }

}
