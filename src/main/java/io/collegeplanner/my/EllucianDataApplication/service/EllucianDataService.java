package io.collegeplanner.my.EllucianDataApplication.service;

import com.google.common.collect.ImmutableMap;
import io.collegeplanner.my.EllucianDataApplication.model.CoursesRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SectionsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SubjectsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.TermsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.entities.*;
import io.collegeplanner.my.EllucianDataApplication.util.GeneralUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static io.collegeplanner.my.EllucianDataApplication.util.Constants.*;

@Log4j2
@Service
public class EllucianDataService {

    public static Set<Term> getTerms(final TermsRequestModel request) {
        final Set<Term> res = new LinkedHashSet<>();
        final Document dom = getDocumentModel(request.getCollege(), ELLUCIAN_REGISTRATION_TERMS_RELATIVE_PATH, ELLUCIAN_REGISTRATION_TERMS_RELATIVE_PATH);
        final Elements elems = dom.getElementsByAttributeValue(ELLUCIAN_SS_DATA_NAME_KEY, ELLUCIAN_SS_DATA_NAME_VALUE_TERMS);
        final Element parentElem = GeneralUtils.getSingularElement(elems);
        final Elements children = parentElem.children();
        for (final Element child : children) {
            final String term = sanitizeTerm(child.text());
            final Season termSeason = parseNameFromTerm(term);
            final String termYear = parseYearFromTerm(term);
            final String termId = child.val();
            if (GeneralUtils.containsAnyFromCollection(term, INVALID_TERMS)) {
                continue;
            }
            res.add(new Term(termSeason, termYear, termId));
        }
        return res;
    }


    public static Set<Subject> getSubjects(final SubjectsRequestModel request) {
        final Set<Subject> res = new LinkedHashSet<>();
        final Map<String, String> data = ImmutableMap.<String, String>builder()
                .put("p_calling_proc", "bwckschd.p_disp_dyn_sched")
                .put("p_term", request.getTerm())
                .build();
        final Document dom = getDocumentModel(request.getCollege(), ELLUCIAN_REGISTRATION_SUBJECTS_RELATIVE_PATH, ELLUCIAN_REGISTRATION_TERMS_RELATIVE_PATH, data);
        final Elements elems = dom.getElementsByAttributeValue(ELLUCIAN_SS_DATA_NAME_KEY, ELLUCIAN_SS_DATA_NAME_VALUE_SUBJECTS);
        final Element parentElem = GeneralUtils.getSingularElement(elems);
        final Elements children = parentElem.children();
        for (final Element child : children) {
            final String abbreviation = child.val();
            final String completeName = child.text();
            res.add(new Subject(abbreviation, completeName));
        }
        return res;
    }

    public static Set<Course> getCourses(final CoursesRequestModel request) {
        final Set<Course> res = new LinkedHashSet<>();
        final Map<String, String> data = ImmutableMap.<String, String>builder()
                .putAll(ELLUCIAN_SS_DATA_COURSES_MAP_DEFAULT)
                .put(ELLUCIAN_SS_DATA_TERM_KEY, request.getTerm())
                .put(ELLUCIAN_SS_DATA_SUBJECT_KEY, request.getSubject())
                .put(ELLUCIAN_SS_DATA_COURSE_KEY, StringUtils.EMPTY)
                .build();
        final Document dom = getDocumentModel(request.getCollege(), ELLUCIAN_REGISTRATION_COURSES_RELATIVE_PATH, ELLUCIAN_REGISTRATION_SUBJECTS_RELATIVE_PATH, request.getTerm(), request.getSubject(), StringUtils.EMPTY);

        final Elements elems = dom.getElementsByAttributeValue(ELLUCIAN_SS_DATA_CLASS_KEY, ELLUCIAN_SS_DATA_CLASS_VALUE_COURSES);
        for (final Element elem : elems) {
            final String courseInfo = elem.text();
            res.add(parseCourseFromCourseInfo(courseInfo));
        }
        return res;
    }

    public static Set<Section> getSections(final SectionsRequestModel request) {
        final Set<Section> res = new LinkedHashSet<>();
        final Map<String, String> data = ImmutableMap.<String, String>builder()
                .putAll(ELLUCIAN_SS_DATA_COURSES_MAP_DEFAULT)
                .put(ELLUCIAN_SS_DATA_TERM_KEY, request.getTerm())
                .put(ELLUCIAN_SS_DATA_SUBJECT_KEY, request.getSubject())
                .put(ELLUCIAN_SS_DATA_COURSE_KEY, request.getNumber())
                .build();
        final Document dom = getDocumentModel(request.getCollege(), ELLUCIAN_REGISTRATION_COURSES_RELATIVE_PATH, ELLUCIAN_REGISTRATION_SUBJECTS_RELATIVE_PATH, request.getTerm(), request.getSubject(), request.getNumber());

        //Get Courses:
        final List<Course> courses = new LinkedList<>();
        final Elements elemsCourses = dom.getElementsByAttributeValue(ELLUCIAN_SS_DATA_CLASS_KEY, ELLUCIAN_SS_DATA_CLASS_VALUE_COURSES);
        for (final Element elem : elemsCourses) {
            final String courseInfo = elem.text();
            courses.add(parseCourseFromCourseInfo(courseInfo));
        }

        // Get SectionMeetings:
        final Elements elemsMeetings = dom.getElementsByAttributeValue(ELLUCIAN_SS_DATA_CLASS_TABLE_KEY, ELLUCIAN_SS_DATA_CLASS_TABLE_VALUE_SECTIONS);
        // For each section (table)
        final Iterator<Course> iterator = courses.iterator();
        for(final Element elem : elemsMeetings) {
            final List<SectionMeeting> sectionMeetings = new LinkedList<>();
            int tableRowCounter = 0; // this is a hacky fix. I want to skip the first row for all collections of <tr>'s
            final Elements tableRows = elem.getElementsByTag(ELLUCIAN_SS_DATA_CLASS_TABLE_ROW_TAG);
            for(final Element row : tableRows) {
                if(tableRowCounter++ < 1) { continue; }
                // Create new scheduledMeeting
                sectionMeetings.add(parseScheduledMeetingFromTableRow(row));
            }
            final Course currentCourse = iterator.next();
            res.add(new Section(currentCourse.getCourseId(), currentCourse.getCourseSection(), sectionMeetings.toArray(new SectionMeeting[sectionMeetings.size()])));
        }
        return res;
    }

    private static Document getDocumentModel(final String collegeName, final String relativePath, final String referrerPath) {
        return getDocumentModel(collegeName, relativePath, referrerPath, Collections.emptyMap());
    }

    private static Document getDocumentModel(final String collegeName, final String relativePath, final String referrerPath, final Map entries) {
        try {
            final String basePage = ELLUCIAN_UNIVERSITIES_SS_DATA_PAGES.get(collegeName);
            final String dataUrl = basePage + relativePath;
            return Jsoup.connect(dataUrl)
                    .referrer(basePage + referrerPath)
//                    .referrer(basePage)
                    .data(entries)
                    .post();
        } catch (final IOException e) {
            log.error(e);
        }
        return null;
    }

    private static Document getDocumentModel(final String collegeName, final String relativePath, final String referrerPath, final String term, final String subject, final String courseNumber) {
        try {
            final String basePage = ELLUCIAN_UNIVERSITIES_SS_DATA_PAGES.get(collegeName);
            final URL url = new URL(basePage + relativePath);
            final HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setRequestMethod("POST");
            http.setInstanceFollowRedirects(false);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setRequestProperty("Referer", basePage + referrerPath);

            String unencodedData = ELLUCIAN_SS_COURSE_DATA_FORM_DATA_DEFAULT + term + ELLUCIAN_SS_DATA_FORM_SUBJECT + subject;
            if(StringUtils.isNotEmpty(courseNumber)) { unencodedData += (ELLUCIAN_SS_DATA_FORM_COURSE + courseNumber); }
            else { unencodedData += (ELLUCIAN_SS_DATA_FORM_COURSE + StringUtils.EMPTY); }
            final byte[] encodedFormParams = unencodedData.getBytes(StandardCharsets.UTF_8);
            http.setFixedLengthStreamingMode(encodedFormParams.length);
            http.getOutputStream().write(encodedFormParams);

            final String html = IOUtils.toString(http.getInputStream(), StandardCharsets.UTF_8);

            return Jsoup.parse(html);

        } catch(final Exception e) {
            log.error(e);
        }
        return null;
    }


    private static String sanitizeTerm(final String term) {
        final StringBuilder sb = new StringBuilder(term);
        if(term.contains("(View only)")) {
            final int start = sb.indexOf("(View only)");
            sb.delete(start, start + "(View only)".length());
        }
        return sb.toString();
    }

    private static String parseYearFromTerm(final String term) {
        return term.replaceAll("[^0-9\\s]", "").trim();
    }

    private static Season parseNameFromTerm(final String term) {
        if(StringUtils.containsIgnoreCase(term, Season.Fall.name())) { return Season.Fall; }
        if(StringUtils.containsIgnoreCase(term, Season.Winter.name())) { return Season.Winter; }
        if(StringUtils.containsIgnoreCase(term, Season.Spring.name())) { return Season.Spring; }
        if(StringUtils.containsIgnoreCase(term, Season.Summer.name())) { return Season.Summer; }
        return null;
//        return term.replaceAll("[^a-zA-Z\\s]", "");
    }

    private static Course parseCourseFromCourseInfo(final String courseInfo) {
        if(courseInfo.contains(" - ")) {
            final String[] info = courseInfo.split(" - ");
            if(info.length != 4) { log.error("Error formatting: " + courseInfo); return null; }
            final String title = info[0];
            final String id = info[1];
            final String name = info[2];
            final String department = name.split(" ")[0];
            final String number = name.split(" ")[1];
            final String section = info[3];
            return new Course(name, title, department, number, id, section);
        }
        else {
            log.error("Error formatting: " + courseInfo);
            return null;
        }
    }

    private static SectionMeeting parseScheduledMeetingFromTableRow(final Element row) {
        final Elements dataColumns = row.getElementsByTag(ELLUCIAN_SS_DATA_CLASS_TABLE_DATA_COLUMN_TAG);
        final Iterator<Element> iterator = dataColumns.iterator();
        return new SectionMeeting(iterator.next().text(),
                iterator.next().text(),
                iterator.next().text(),
                iterator.next().text(),
                iterator.next().text(),
                iterator.next().text(),
                iterator.next().text()
        );
    }

    private static String formatSubjectParameters(final Set<String> subjects) {
        final StringBuilder paramBuilder = new StringBuilder();
        for(final String subj : subjects) {
            paramBuilder.append(ELLUCIAN_SS_DATA_FORM_SUBJECT + subj);
        }
        return paramBuilder.toString();
    }

    private static String formatCourseParameters(final Set<String> subjects) {
        final StringBuilder paramBuilder = new StringBuilder();
        for(final String subj : subjects) {
            paramBuilder.append(ELLUCIAN_SS_DATA_FORM_SUBJECT + subj);
        }
        return paramBuilder.toString();
    }
}
