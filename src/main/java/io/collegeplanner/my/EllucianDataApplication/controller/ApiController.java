package io.collegeplanner.my.EllucianDataApplication.controller;

import io.collegeplanner.my.EllucianDataApplication.model.CoursesRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SectionsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SubjectsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.TermsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Course;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Section;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Subject;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Term;
import io.collegeplanner.my.EllucianDataApplication.service.EllucianDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Log4j2
@Controller
@RequestMapping(value = "/v1")
public class ApiController {

    @Autowired
    private EllucianDataService dataService;

    @ResponseBody
    @RequestMapping(value = "/terms")
    public Set<Term> getTerms(final TermsRequestModel requestModel) {
        return dataService.getTerms(requestModel);
    }

    @ResponseBody
    @RequestMapping(value = "/subjects")
    public Set<Subject> getSubjects(final SubjectsRequestModel requestModel) {
        return dataService.getSubjects(requestModel);
    }

    @ResponseBody
    @RequestMapping(value = "/courses")
    public Set<Course> getTerms(final CoursesRequestModel requestModel) {
        return dataService.getCourses(requestModel);
    }

    @ResponseBody
    @RequestMapping(value = "/sections")
    public Set<Section> getTerms(final SectionsRequestModel requestModel) {
        return dataService.getSections(requestModel);
    }

}
