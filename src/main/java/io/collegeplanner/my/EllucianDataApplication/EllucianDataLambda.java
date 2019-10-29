package io.collegeplanner.my.EllucianDataApplication;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.collegeplanner.my.EllucianDataApplication.model.CoursesRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SectionsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SubjectsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.TermsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.service.EllucianDataService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static io.collegeplanner.my.EllucianDataApplication.util.Constants.*;

public class EllucianDataLambda {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent event, final Context context) {
        final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final Collection<?> data;
            final Map<String, String> queryParameters = event.getQueryStringParameters();
            switch(event.getPath()) {
                case API_PATH_TERMS:
                    data = EllucianDataService.getTerms(mapper.convertValue(queryParameters, TermsRequestModel.class));
                    break;
                case API_PATH_SUBJECTS:
                    data = EllucianDataService.getSubjects(mapper.convertValue(queryParameters, SubjectsRequestModel.class));
                    break;
                case API_PATH_COURSES:
                    data = EllucianDataService.getCourses(mapper.convertValue(queryParameters, CoursesRequestModel.class));
                    break;
                case API_PATH_SECTIONS:
                    data = EllucianDataService.getSections(mapper.convertValue(queryParameters, SectionsRequestModel.class));
                    break;
                default: data = Collections.emptySet();
            }
            response.setBody(mapper.writeValueAsString(data));
        }
        catch(final Exception e) {
            context.getLogger().log(e.toString());
        }
        return response;
    }
}
