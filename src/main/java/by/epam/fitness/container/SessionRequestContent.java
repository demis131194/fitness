package by.epam.fitness.container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes = new HashMap<>();
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes = new HashMap<>();
    private boolean invalidateSession;

    public SessionRequestContent(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        Collections.list(attributeNames).forEach(attr -> requestAttributes.put(attr, request.getAttribute(attr)));
        requestParameters = new HashMap<>(request.getParameterMap());
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            Enumeration<String> sessionAttributesNames = session.getAttributeNames();
            Collections.list(sessionAttributesNames).forEach(attr -> sessionAttributes.put(attr, session.getAttribute(attr)));
        }

    }

    public void insertAttributes(HttpServletRequest request) {
        requestAttributes.forEach(request::setAttribute);
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            sessionAttributes.forEach(session::setAttribute);
        }
        if (invalidateSession) {
            request.getSession().invalidate();
        }
    }

    public Object getAttributeByName(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public Object putAttribute(String attributeName, Object object) {
        return requestAttributes.put(attributeName, object);
    }

    public String[] getRequestParametersByName(String parameterName) {
        return requestParameters.get(parameterName);
    }

    public String getParameterByName(String parameterName) {
        String[] parameters = requestParameters.get(parameterName);
        return parameters.length != 0 ? parameters[0] : null;
    }

    public Object getSessionAttributeByName(String sessionAttributeName) {
        return sessionAttributes.get(sessionAttributeName);
    }

    public Object putSessionAttribute(String sessionAttributeName, Object object) {
        return sessionAttributes.put(sessionAttributeName, object);
    }

    public void invalidateSession() {
        invalidateSession = true;
    }
}
