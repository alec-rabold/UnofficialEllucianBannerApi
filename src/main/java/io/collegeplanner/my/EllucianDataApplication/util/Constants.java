package io.collegeplanner.my.EllucianDataApplication.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

public class Constants {

    /** View */
    public static final String JSP_VIEW_RESOLVER_PREFIX = "/WEB-INF/classes/templates/";
    public static final String JSP_VIEW_RESOLVER_SUFFIX = ".jsp";


    /** Supported colleges */
    public static final String UNIVERSITY_OF_WASHINGTON = "UW";
    public static final String WASHINGTON_STATE_UNIVERSITY = "WSU";
    public static final String SAN_DIEGO_STATE_UNIVERSITY = "SDSU";
    public static final String UNIVERSITY_OF_CALIFORNIA_BERKELEY = "Berkeley";
    public static final String UNIVERSITY_OF_CALIFORNIA_SANTA_BARBARA = "UCSB";
    // Ellucian
    public static final String GEORGIA_STATE_UNIVERSITY = "GSU";
    //    public static final String OTTERBEIN_UNIVERSITY = "Otterbein";
    public static final String WEBER_STATE_UNIVERSITY = "Weber";
    public static final String DREXEL_UNIVERSITY = "Drexel";
    public static final String PURDUE_UNIVERSITY = "Purdue";
    public static final String PURDUE_UNIVERSITY_NORTHWEST = "PurdueNW";
    public static final String GEORGE_MASON_UNIVERSITY = "GMU";
    public static final String UNIVERSITY_OF_TENNESSEE_KNOXVILLE = "UTK";
    public static final String HARPER_COLLEGE = "Harper";
    public static final String BROWN_UNIVERSITY = "Brown";
    public static final String GEORGIA_TECH = "GeorgiaTech";

    /** Ellucian */
//    public static final String ELLUCIAN_SS_COURSE_DATA_FORM_DATA_DEFAULT = "sel_subj=dummy&sel_day=dummy&sel_schd=dummy&sel_insm=dummy&sel_camp=dummy&sel_levl=dummy&sel_sess=dummy&sel_instr=dummy&sel_ptrm=dummy&sel_attr=dummy&sel_crse=&sel_title=&sel_schd=%25&sel_from_cred=&sel_to_cred=&sel_levl=%25&sel_instr=%25&sel_attr=%25&begin_hh=0&begin_mi=0&begin_ap=a&end_hh=0&end_mi=0&end_ap=a&term_in=";
    public static final String ELLUCIAN_SS_COURSE_DATA_FORM_DATA_DEFAULT = "sel_subj=dummy&sel_day=dummy&sel_schd=dummy&sel_insm=dummy&sel_camp=dummy&sel_levl=dummy&sel_sess=dummy&sel_instr=dummy&sel_ptrm=dummy&sel_attr=dummy&sel_title=&sel_schd=%25&sel_from_cred=&sel_to_cred=&sel_levl=%25&sel_instr=%25&sel_attr=%25&begin_hh=0&begin_mi=0&begin_ap=a&end_hh=0&end_mi=0&end_ap=a&term_in=";
    public static final String ELLUCIAN_REGISTRATION_TERMS_RELATIVE_PATH = "/bwckschd.p_disp_dyn_sched";
    public static final String ELLUCIAN_REGISTRATION_COURSES_RELATIVE_PATH = "/bwckschd.p_get_crse_unsec";
    public static final String ELLUCIAN_REGISTRATION_SUBJECTS_RELATIVE_PATH = "/bwckgens.p_proc_term_date";
    public static final String ELLUCIAN_SS_DATA_NAME_KEY = "name";
    public static final String ELLUCIAN_SS_DATA_NAME_VALUE_TERMS = "p_term";
    public static final String ELLUCIAN_SS_DATA_NAME_VALUE_SUBJECTS = "sel_subj";
    public static final String ELLUCIAN_SS_DATA_CLASS_KEY = "class";
    public static final String ELLUCIAN_SS_DATA_CLASS_VALUE_COURSES = "ddtitle";
    public static final String ELLUCIAN_SS_DATA_CLASS_TABLE_KEY = "SUMMARY";
    public static final String ELLUCIAN_SS_DATA_CLASS_TABLE_VALUE_SECTIONS = "This table lists the scheduled meeting times and assigned instructors for this class..";
    public static final String ELLUCIAN_SS_DATA_CLASS_TABLE_ROW_TAG = "tr";
    public static final String ELLUCIAN_SS_DATA_CLASS_TABLE_DATA_COLUMN_TAG = "td";
    public static final String ELLUCIAN_SS_DATA_FORM_SUBJECT = "&sel_subj=";
    public static final String ELLUCIAN_SS_DATA_FORM_COURSE = "&sel_crse=";
    public static final String ELLUCIAN_SS_DATA_TERM_KEY = "term_in";
    public static final String ELLUCIAN_SS_DATA_SUBJECT_KEY = "sel_subj";
    public static final String ELLUCIAN_SS_DATA_COURSE_KEY = "sel_crse";


    public static final String ELLUCIAN_SS_DATA_DUMMY_NODE = "dummy";

    public static final Map<String, String> ELLUCIAN_SS_DATA_COURSES_MAP_DEFAULT = ImmutableMap.<String, String>builder()
//    term_in: 202002
//            .put("sel_subj", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_day", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_schd", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_insm", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_camp", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_levl", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_sess", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_instr", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_ptrm", ELLUCIAN_SS_DATA_DUMMY_NODE)
            .put("sel_attr", ELLUCIAN_SS_DATA_DUMMY_NODE)
//            .put( "sel_subj: AE
//            .put( "sel_crse", "")
            .put( "sel_title", "")
//            .put( "sel_schd", "%")
            .put( "sel_from_cred", "")
            .put("sel_to_cred", "")
//            .put("sel_camp", "%")
//            .put( "sel_ptrm", "%")
//            .put( "sel_instr", "%")
//            .put("sel_attr", "%")
            .put("begin_hh", "0")
            .put( "begin_mi", "0")
            .put( "begin_ap", "a")
            .put("end_hh", "0")
            .put("end_mi", "0")
            .put("end_ap", "a")
            .build();

    // Self-Service Data Pages
    public static final String ELLUCIAN_SS_DATA_GEORGIA_STATE_UNIVERSITY = "https://www.gosolar.gsu.edu/bprod";
    //    public static final String ELLUCIAN_SS_DATA_OTTERBEIN_UNIVERSITY = "https://ssb.otterbein.edu/prod";
    public static final String ELLUCIAN_SS_DATA_WEBER_STATE_UNIVERSITY = "https://selfservice.weber.edu/pls/proddad";
    public static final String ELLUCIAN_SS_DATA_DREXEL_UNIVERSITY = "https://banner.drexel.edu/pls/duprod";
    public static final String ELLUCIAN_SS_DATA_PURDUE_UNIVERSITY = "https://selfservice.mypurdue.purdue.edu/prod";
    public static final String ELLUCIAN_SS_DATA_PURDUE_UNIVERSITY_NORTHWEST = "https://ssb-prod.pnw.edu/dbServer_prod";
    public static final String ELLUCIAN_SS_DATA_GEORGE_MASON_UNIVERSITY = "https://patriotweb.gmu.edu/pls/prod";
    public static final String ELLUCIAN_SS_DATA_UNIVERSITY_OF_TENNESSEE_KNOWXVILLE = "https://bannerssb.utk.edu/kbanpr";
    public static final String ELLUCIAN_SS_DATA_HARPER_COLLEGE = "https://student-self-service.harpercollege.edu/prod";
    public static final String ELLUCIAN_SS_DATA_BROWN_UNIVERSITY = "https://selfservice.brown.edu/ss";
    public static final String ELLUCIAN_SS_DATA_GEORGIA_TECH = "https://oscar.gatech.edu/pls/bprod";


    public static final Map<String, String> ELLUCIAN_UNIVERSITIES_SS_DATA_PAGES = ImmutableMap.<String, String>builder()
            .put(GEORGIA_STATE_UNIVERSITY, ELLUCIAN_SS_DATA_GEORGIA_STATE_UNIVERSITY)
//            .put(OTTERBEIN_UNIVERSITY, ELLUCIAN_SS_DATA_OTTERBEIN_UNIVERSITY)
            .put(WEBER_STATE_UNIVERSITY, ELLUCIAN_SS_DATA_WEBER_STATE_UNIVERSITY)
            .put(DREXEL_UNIVERSITY, ELLUCIAN_SS_DATA_DREXEL_UNIVERSITY)
            .put(PURDUE_UNIVERSITY, ELLUCIAN_SS_DATA_PURDUE_UNIVERSITY)
            .put(PURDUE_UNIVERSITY_NORTHWEST, ELLUCIAN_SS_DATA_PURDUE_UNIVERSITY_NORTHWEST)
            .put(GEORGE_MASON_UNIVERSITY, ELLUCIAN_SS_DATA_GEORGE_MASON_UNIVERSITY)
            .put(UNIVERSITY_OF_TENNESSEE_KNOXVILLE, ELLUCIAN_SS_DATA_UNIVERSITY_OF_TENNESSEE_KNOWXVILLE)
            .put(HARPER_COLLEGE, ELLUCIAN_SS_DATA_HARPER_COLLEGE)
            .put(BROWN_UNIVERSITY, ELLUCIAN_SS_DATA_BROWN_UNIVERSITY)
            .put(GEORGIA_TECH, ELLUCIAN_SS_DATA_GEORGIA_TECH)
            .build();

    /** General */
    public enum Season {
        Fall, Spring, Summer, Winter

    }

    public static final Set<String> INVALID_TERMS = ImmutableSet.<String>builder()
            .add("(")
            .add(")")
            .add("None")
            .add("Language")
            .add("Continuing")
            .build();
}
