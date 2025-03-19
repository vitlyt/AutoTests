package mapping;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfResumeRequest {
    protected String operationName;
    protected String query;
    protected Variables variables;

    public ProfResumeRequest(Input input, String operationName, String query){
        Variables variables = new Variables();
        variables.setInput(input);
        setOperationName(operationName);
        setQuery(query);
        setVariables(variables);
    }
}
