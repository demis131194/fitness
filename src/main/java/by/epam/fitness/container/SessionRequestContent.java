package by.epam.fitness.container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes = new HashMap<>();
//    private HashMap<String, String[]> requestParameters;                              // FIXME: 22.10.2019 ???????????????????????
    private HashMap<String, String> requestParameters = new HashMap<>();
    private HashMap<String, Object> sessionAttributes = new HashMap<>();

    public SessionRequestContent(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        Collections.list(attributeNames).forEach(attr -> requestAttributes.put(attr, request.getAttribute(attr)));
//        requestParameters = new HashMap<>(request.getParameterMap());
//        requestAttributes.putAll(request.getParameterMap());                            // FIXME: 22.10.2019 putAll
        Enumeration<String> parameterNames = request.getParameterNames();
        Collections.list(parameterNames).forEach(param -> requestParameters.put(param, request.getParameter(param)));

        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            Enumeration<String> sessionAttributesNames = session.getAttributeNames();
            Collections.list(sessionAttributesNames).forEach(attr -> sessionAttributes.put(attr, session.getAttribute(attr)));
        }
    }

//    public void extractValues(HttpServletRequest request) {
//
//    }

    public void insertAttributes(HttpServletRequest request) {                          // FIXME: 22.10.2019 SetParameters ???
        requestAttributes.forEach(request::setAttribute);

        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            sessionAttributes.forEach(session::setAttribute);
        }
    }

    public Object geAttributeByName(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public Object putAttribute(String attributeName, Object object) {
        return requestAttributes.put(attributeName, object);
    }

//    public String[] getRequestParametersByName(String parameterName) {                          // FIXME: 22.10.2019 need setter  ???
//        return requestParameters.get(parameterName);
//    }

    public String getParameterByName(String parameterName) {
        return requestParameters.get(parameterName);
    }

    public String putParameter(String parameterName, String parameterValue) {
        return requestParameters.put(parameterName, parameterValue);
    }

    public Object getSessionAttributeByName(String sessionAttributeName) {
        return sessionAttributes.get(sessionAttributeName);
    }

    public Object putSessionAttribute(String sessionAttributeName, Object object) {
        return sessionAttributes.put(sessionAttributeName, object);
    }
}
