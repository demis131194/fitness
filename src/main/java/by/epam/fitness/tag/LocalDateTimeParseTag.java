package by.epam.fitness.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Local date time parse tag.
 */
public class LocalDateTimeParseTag extends TagSupport {
    private LocalDateTime dateTime;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Sets date time.
     *
     * @param dateTime the date time
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }



    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
