package io.collegeplanner.my.EllucianDataApplication.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collection;

import static io.collegeplanner.my.EllucianDataApplication.util.Constants.ELLUCIAN_SS_DATA_DUMMY_NODE;

public class GeneralUtils {
    public static boolean containsAnyFromCollection(final String searchString, final Collection<String> searchCollection) {
        return searchCollection.stream().parallel().anyMatch(searchString::contains);
    }

    public static Element getSingularElement(final Elements elems) {
        for(final Element elem : elems) {
            if(elem.val().equalsIgnoreCase(ELLUCIAN_SS_DATA_DUMMY_NODE)) { continue; }
            return elem;
        }
        return elems.first();
    }
}
