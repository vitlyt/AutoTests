package mapping;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSeekerProfResumePortfoliosRequest extends ProfResumeRequest{
    protected List<String> portfolios;

}
