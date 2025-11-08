package com.fasterxml.jackson.databind.util;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.objectweb.asm.signature.SignatureVisitor;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = {DATE_FORMAT_STR_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSS", DATE_FORMAT_STR_RFC1123, "yyyy-MM-dd"};
    protected static final Calendar CALENDAR;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    protected static final Locale DEFAULT_LOCALE;
    protected static final TimeZone DEFAULT_TIMEZONE;
    protected static final Pattern PATTERN_ISO8601 = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d(?:[:]\\d\\d)?(\\.\\d+)?(Z|[+-]\\d\\d(?:[:]?\\d\\d)?)?");
    protected static final Pattern PATTERN_PLAIN = Pattern.compile(PATTERN_PLAIN_STR);
    protected static final String PATTERN_PLAIN_STR = "\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d";
    public static final StdDateFormat instance = new StdDateFormat();
    private transient Calendar _calendar;
    private transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;
    private boolean _tzSerializedWithColon;

    static {
        try {
            TimeZone timeZone = TimeZone.getTimeZone("UTC");
            DEFAULT_TIMEZONE = timeZone;
            Locale locale = Locale.US;
            DEFAULT_LOCALE = locale;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_STR_RFC1123, locale);
            DATE_FORMAT_RFC1123 = simpleDateFormat;
            simpleDateFormat.setTimeZone(timeZone);
            CALENDAR = new GregorianCalendar(timeZone, locale);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public StdDateFormat() {
        this._tzSerializedWithColon = true;
        this._locale = DEFAULT_LOCALE;
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, String str, TimeZone timeZone, Locale locale, Boolean bool) {
        DateFormat dateFormat2;
        if (!locale.equals(DEFAULT_LOCALE)) {
            dateFormat2 = new SimpleDateFormat(str, locale);
            if (timeZone == null) {
                timeZone = DEFAULT_TIMEZONE;
            }
            dateFormat2.setTimeZone(timeZone);
        } else {
            dateFormat2 = (DateFormat) dateFormat.clone();
            if (timeZone != null) {
                dateFormat2.setTimeZone(timeZone);
            }
        }
        if (bool != null) {
            dateFormat2.setLenient(bool.booleanValue());
        }
        return dateFormat2;
    }

    public static <T> boolean _equals(T t2, T t3) {
        if (t2 == t3) {
            return true;
        }
        return t2 != null && t2.equals(t3);
    }

    private static int _parse2D(String str, int i3) {
        return (str.charAt(i3 + 1) - '0') + ((str.charAt(i3) - '0') * 10);
    }

    private static int _parse4D(String str, int i3) {
        int charAt = ((str.charAt(i3 + 1) - '0') * 100) + ((str.charAt(i3) - '0') * 1000);
        return (str.charAt(i3 + 3) - '0') + ((str.charAt(i3 + 2) - '0') * 10) + charAt;
    }

    private Date _parseDateFromLong(String str, ParsePosition parsePosition) throws ParseException {
        try {
            return new Date(NumberInput.parseLong(str));
        } catch (NumberFormatException unused) {
            throw new ParseException(a.l("Timestamp value ", str, " out of 64-bit value range"), parsePosition.getErrorIndex());
        }
    }

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    @Deprecated
    public static DateFormat getISO8601Format(TimeZone timeZone, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601, locale);
        simpleDateFormat.setTimeZone(DEFAULT_TIMEZONE);
        return simpleDateFormat;
    }

    @Deprecated
    public static DateFormat getRFC1123Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, timeZone, locale, (Boolean) null);
    }

    private static void pad2(StringBuffer stringBuffer, int i3) {
        int i4 = i3 / 10;
        if (i4 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i4 + 48));
            i3 -= i4 * 10;
        }
        stringBuffer.append((char) (i3 + 48));
    }

    private static void pad3(StringBuffer stringBuffer, int i3) {
        int i4 = i3 / 100;
        if (i4 == 0) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i4 + 48));
            i3 -= i4 * 100;
        }
        pad2(stringBuffer, i3);
    }

    private static void pad4(StringBuffer stringBuffer, int i3) {
        int i4 = i3 / 100;
        if (i4 == 0) {
            stringBuffer.append('0');
            stringBuffer.append('0');
        } else {
            if (i4 > 99) {
                stringBuffer.append(i4);
            } else {
                pad2(stringBuffer, i4);
            }
            i3 -= i4 * 100;
        }
        pad2(stringBuffer, i3);
    }

    public void _clearFormats() {
        this._formatRFC1123 = null;
    }

    public void _format(TimeZone timeZone, Locale locale, Date date, StringBuffer stringBuffer) {
        Calendar _getCalendar = _getCalendar(timeZone);
        _getCalendar.setTime(date);
        int i3 = _getCalendar.get(1);
        int i4 = _getCalendar.get(0);
        char c3 = SignatureVisitor.EXTENDS;
        if (i4 == 0) {
            _formatBCEYear(stringBuffer, i3);
        } else {
            if (i3 > 9999) {
                stringBuffer.append(SignatureVisitor.EXTENDS);
            }
            pad4(stringBuffer, i3);
        }
        stringBuffer.append(SignatureVisitor.SUPER);
        pad2(stringBuffer, _getCalendar.get(2) + 1);
        stringBuffer.append(SignatureVisitor.SUPER);
        pad2(stringBuffer, _getCalendar.get(5));
        stringBuffer.append('T');
        pad2(stringBuffer, _getCalendar.get(11));
        stringBuffer.append(AbstractJsonLexerKt.COLON);
        pad2(stringBuffer, _getCalendar.get(12));
        stringBuffer.append(AbstractJsonLexerKt.COLON);
        pad2(stringBuffer, _getCalendar.get(13));
        stringBuffer.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        pad3(stringBuffer, _getCalendar.get(14));
        int offset = timeZone.getOffset(_getCalendar.getTimeInMillis());
        if (offset != 0) {
            int i5 = offset / 60000;
            int abs = Math.abs(i5 / 60);
            int abs2 = Math.abs(i5 % 60);
            if (offset < 0) {
                c3 = '-';
            }
            stringBuffer.append(c3);
            pad2(stringBuffer, abs);
            if (this._tzSerializedWithColon) {
                stringBuffer.append(AbstractJsonLexerKt.COLON);
            }
            pad2(stringBuffer, abs2);
        } else if (this._tzSerializedWithColon) {
            stringBuffer.append("+00:00");
        } else {
            stringBuffer.append("+0000");
        }
    }

    public void _formatBCEYear(StringBuffer stringBuffer, int i3) {
        if (i3 == 1) {
            stringBuffer.append("+0000");
            return;
        }
        stringBuffer.append(SignatureVisitor.SUPER);
        pad4(stringBuffer, i3 - 1);
    }

    public Calendar _getCalendar(TimeZone timeZone) {
        Calendar calendar = this._calendar;
        if (calendar == null) {
            calendar = (Calendar) CALENDAR.clone();
            this._calendar = calendar;
        }
        if (!calendar.getTimeZone().equals(timeZone)) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setLenient(isLenient());
        return calendar;
    }

    public Date _parseAsISO8601(String str, ParsePosition parsePosition) throws IllegalArgumentException, ParseException {
        String str2;
        String str3 = str;
        int length = str.length();
        TimeZone timeZone = DEFAULT_TIMEZONE;
        if (!(this._timezone == null || 'Z' == str3.charAt(length - 1))) {
            timeZone = this._timezone;
        }
        Calendar _getCalendar = _getCalendar(timeZone);
        _getCalendar.clear();
        int i3 = 0;
        if (length > 10) {
            Matcher matcher = PATTERN_ISO8601.matcher(str3);
            if (matcher.matches()) {
                int start = matcher.start(2);
                int end = matcher.end(2);
                int i4 = end - start;
                if (i4 > 1) {
                    int _parse2D = _parse2D(str3, start + 1) * 3600;
                    if (i4 >= 5) {
                        _parse2D += _parse2D(str3, end - 2) * 60;
                    }
                    _getCalendar.set(15, str3.charAt(start) == '-' ? _parse2D * -1000 : _parse2D * 1000);
                    _getCalendar.set(16, 0);
                }
                Matcher matcher2 = matcher;
                _getCalendar.set(_parse4D(str3, 0), _parse2D(str3, 5) - 1, _parse2D(str3, 8), _parse2D(str3, 11), _parse2D(str3, 14), (length <= 16 || str3.charAt(16) != ':') ? 0 : _parse2D(str3, 17));
                int start2 = matcher2.start(1);
                int i5 = start2 + 1;
                int end2 = matcher2.end(1);
                if (i5 >= end2) {
                    _getCalendar.set(14, 0);
                } else {
                    int i6 = end2 - i5;
                    if (i6 != 0) {
                        if (i6 != 1) {
                            if (i6 != 2) {
                                if (i6 == 3 || i6 <= 9) {
                                    i3 = str3.charAt(start2 + 3) - '0';
                                } else {
                                    throw new ParseException(C0118y.g("Cannot parse date \"", str3, "\": invalid fractional seconds '", matcher2.group(1).substring(1), "'; can use at most 9 digits"), i5);
                                }
                            }
                            i3 += (str3.charAt(start2 + 2) - '0') * 10;
                        }
                        i3 += (str3.charAt(i5) - '0') * 100;
                    }
                    _getCalendar.set(14, i3);
                }
                return _getCalendar.getTime();
            }
            str2 = DATE_FORMAT_STR_ISO8601;
        } else if (PATTERN_PLAIN.matcher(str3).matches()) {
            _getCalendar.set(_parse4D(str3, 0), _parse2D(str3, 5) - 1, _parse2D(str3, 8), 0, 0, 0);
            _getCalendar.set(14, 0);
            return _getCalendar.getTime();
        } else {
            str2 = "yyyy-MM-dd";
        }
        Boolean bool = this._lenient;
        StringBuilder l2 = C0118y.l("Cannot parse date \"", str3, "\": while it seems to fit format '", str2, "', parsing fails (leniency? ");
        l2.append(bool);
        l2.append(")");
        throw new ParseException(l2.toString(), 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Date _parseDate(java.lang.String r5, java.text.ParsePosition r6) throws java.text.ParseException {
        /*
            r4 = this;
            boolean r0 = r4.looksLikeISO8601(r5)
            if (r0 == 0) goto L_0x000b
            java.util.Date r4 = r4.parseAsISO8601(r5, r6)
            return r4
        L_0x000b:
            int r0 = r5.length()
        L_0x000f:
            int r0 = r0 + -1
            r1 = 45
            if (r0 < 0) goto L_0x0025
            char r2 = r5.charAt(r0)
            r3 = 48
            if (r2 < r3) goto L_0x0021
            r3 = 57
            if (r2 <= r3) goto L_0x000f
        L_0x0021:
            if (r0 > 0) goto L_0x0025
            if (r2 == r1) goto L_0x000f
        L_0x0025:
            if (r0 >= 0) goto L_0x0039
            r0 = 0
            char r2 = r5.charAt(r0)
            if (r2 == r1) goto L_0x0034
            boolean r0 = com.fasterxml.jackson.core.io.NumberInput.inLongRange(r5, r0)
            if (r0 == 0) goto L_0x0039
        L_0x0034:
            java.util.Date r4 = r4._parseDateFromLong(r5, r6)
            return r4
        L_0x0039:
            java.util.Date r4 = r4.parseAsRFC1123(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.StdDateFormat._parseDate(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        TimeZone timeZone = this._timezone;
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        _format(timeZone, this._locale, date, stringBuffer);
        return stringBuffer;
    }

    public TimeZone getTimeZone() {
        return this._timezone;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public boolean isColonIncludedInTimeZone() {
        return this._tzSerializedWithColon;
    }

    public boolean isLenient() {
        Boolean bool = this._lenient;
        return bool == null || bool.booleanValue();
    }

    public boolean looksLikeISO8601(String str) {
        return str.length() >= 7 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-' && Character.isDigit(str.charAt(5));
    }

    public Date parse(String str) throws ParseException {
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date _parseDate = _parseDate(trim, parsePosition);
        if (_parseDate != null) {
            return _parseDate;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(str2);
        }
        sb.append('\"');
        throw new ParseException(C0118y.g("Cannot parse date \"", trim, "\": not compatible with any of standard forms (", sb.toString(), ")"), parsePosition.getErrorIndex());
    }

    public Date parseAsISO8601(String str, ParsePosition parsePosition) throws ParseException {
        try {
            return _parseAsISO8601(str, parsePosition);
        } catch (IllegalArgumentException e3) {
            throw new ParseException(C0118y.f("Cannot parse date \"", str, "\", problem: ", e3.getMessage()), parsePosition.getErrorIndex());
        }
    }

    public Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    public void setLenient(boolean z2) {
        Boolean valueOf = Boolean.valueOf(z2);
        if (!_equals(valueOf, this._lenient)) {
            this._lenient = valueOf;
            _clearFormats();
        }
    }

    public void setTimeZone(TimeZone timeZone) {
        if (!timeZone.equals(this._timezone)) {
            _clearFormats();
            this._timezone = timeZone;
        }
    }

    public String toPattern() {
        return a.n(b.o(100, "[one of: 'yyyy-MM-dd'T'HH:mm:ss.SSSX', 'EEE, dd MMM yyyy HH:mm:ss zzz' ("), Boolean.FALSE.equals(this._lenient) ? SchemaSymbols.ATTVAL_STRICT : "lenient", ")]");
    }

    public String toString() {
        return String.format("DateFormat %s: (timezone: %s, locale: %s, lenient: %s)", new Object[]{getClass().getName(), this._timezone, this._locale, this._lenient});
    }

    public StdDateFormat withColonInTimeZone(boolean z2) {
        return this._tzSerializedWithColon == z2 ? this : new StdDateFormat(this._timezone, this._locale, this._lenient, z2);
    }

    public StdDateFormat withLenient(Boolean bool) {
        return _equals(bool, this._lenient) ? this : new StdDateFormat(this._timezone, this._locale, bool, this._tzSerializedWithColon);
    }

    public StdDateFormat withLocale(Locale locale) {
        return locale.equals(this._locale) ? this : new StdDateFormat(this._timezone, locale, this._lenient, this._tzSerializedWithColon);
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        TimeZone timeZone2 = this._timezone;
        return (timeZone == timeZone2 || timeZone.equals(timeZone2)) ? this : new StdDateFormat(timeZone, this._locale, this._lenient, this._tzSerializedWithColon);
    }

    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient, this._tzSerializedWithColon);
    }

    @Deprecated
    public StdDateFormat(TimeZone timeZone, Locale locale) {
        this._tzSerializedWithColon = true;
        this._timezone = timeZone;
        this._locale = locale;
    }

    public StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool) {
        this(timeZone, locale, bool, false);
    }

    public StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool, boolean z2) {
        this._timezone = timeZone;
        this._locale = locale;
        this._lenient = bool;
        this._tzSerializedWithColon = z2;
    }

    public Date parse(String str, ParsePosition parsePosition) {
        try {
            return _parseDate(str, parsePosition);
        } catch (ParseException unused) {
            return null;
        }
    }
}
