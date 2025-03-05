package conf;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthFilter implements Filter {

    private final String accessToken;

    public AuthFilter(String accessToken){
        this.accessToken = accessToken;
    }

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification,
                           FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {
        filterableRequestSpecification.replaceHeader("Authorization", "Bearer " + accessToken);
        return filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
    }
}
