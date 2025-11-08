package com.fasterxml.jackson.databind.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;

@Deprecated
public class ISO8601DateFormat extends DateFormat {
    private static final long serialVersionUID = 1;

    public ISO8601DateFormat() {
        this.numberFormat = new DecimalFormat();
        this.calendar = new GregorianCalendar();
    }

    public Object clone() {
        return this;
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        stringBuffer.append(ISO8601Utils.format(date));
        return stringBuffer;
    }

    public Date parse(String str, ParsePosition parsePosition) {
        try {
            return ISO8601Utils.parse(str, parsePosition);
        } catch (ParseException unused) {
            return null;
        }
    }

    public Date parse(String str) throws ParseException {
        return ISO8601Utils.parse(str, new ParsePosition(0));
    }
}
