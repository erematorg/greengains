package com.fasterxml.jackson.databind.util;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.ui.autofill.a;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.time.TimeZones;
import org.objectweb.asm.signature.SignatureVisitor;

@Deprecated
public class ISO8601Utils {
    protected static final int DEF_8601_LEN = 29;
    private static final TimeZone TIMEZONE_Z = TimeZone.getTimeZone("UTC");

    private static boolean checkOffset(String str, int i3, char c3) {
        return i3 < str.length() && str.charAt(i3) == c3;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_Z);
    }

    private static int indexOfNonDigit(String str, int i3) {
        while (i3 < str.length()) {
            char charAt = str.charAt(i3);
            if (charAt < '0' || charAt > '9') {
                return i3;
            }
            i3++;
        }
        return str.length();
    }

    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        TimeZone timeZone;
        char charAt;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Objects.requireNonNull(str);
        try {
            int index = parsePosition.getIndex();
            int i8 = index + 4;
            int parseInt = parseInt(str2, index, i8);
            if (checkOffset(str2, i8, SignatureVisitor.SUPER)) {
                i8 = index + 5;
            }
            int i9 = i8 + 2;
            int parseInt2 = parseInt(str2, i8, i9);
            if (checkOffset(str2, i9, SignatureVisitor.SUPER)) {
                i9 = i8 + 3;
            }
            int i10 = i9 + 2;
            int parseInt3 = parseInt(str2, i9, i10);
            boolean checkOffset = checkOffset(str2, i10, 'T');
            if (checkOffset || str.length() > i10) {
                if (checkOffset) {
                    int i11 = i9 + 5;
                    int parseInt4 = parseInt(str2, i9 + 3, i11);
                    if (checkOffset(str2, i11, AbstractJsonLexerKt.COLON)) {
                        i11 = i9 + 6;
                    }
                    int i12 = i11 + 2;
                    int parseInt5 = parseInt(str2, i11, i12);
                    if (checkOffset(str2, i12, AbstractJsonLexerKt.COLON)) {
                        i12 = i11 + 3;
                    }
                    if (str.length() <= i12 || (charAt = str2.charAt(i12)) == 'Z' || charAt == '+' || charAt == '-') {
                        i5 = parseInt5;
                        i4 = 0;
                        i3 = 0;
                        int i13 = parseInt4;
                        i10 = i12;
                        i6 = i13;
                    } else {
                        int i14 = i12 + 2;
                        i3 = parseInt(str2, i12, i14);
                        if (i3 > 59 && i3 < 63) {
                            i3 = 59;
                        }
                        if (checkOffset(str2, i14, ClassUtils.PACKAGE_SEPARATOR_CHAR)) {
                            int i15 = i12 + 3;
                            int indexOfNonDigit = indexOfNonDigit(str2, i12 + 4);
                            int min = Math.min(indexOfNonDigit, i12 + 6);
                            int parseInt6 = parseInt(str2, i15, min);
                            int i16 = min - i15;
                            if (i16 == 1) {
                                parseInt6 *= 100;
                            } else if (i16 == 2) {
                                parseInt6 *= 10;
                            }
                            i6 = parseInt4;
                            i10 = indexOfNonDigit;
                            i5 = parseInt5;
                            i4 = parseInt6;
                        } else {
                            i6 = parseInt4;
                            i10 = i14;
                            i5 = parseInt5;
                            i4 = 0;
                        }
                    }
                } else {
                    i6 = 0;
                    i5 = 0;
                    i4 = 0;
                    i3 = 0;
                }
                if (str.length() > i10) {
                    char charAt2 = str2.charAt(i10);
                    if (charAt2 == 'Z') {
                        timeZone = TIMEZONE_Z;
                        i7 = i10 + 1;
                    } else {
                        if (charAt2 != '+') {
                            if (charAt2 != '-') {
                                throw new IndexOutOfBoundsException("Invalid time zone indicator '" + charAt2 + "'");
                            }
                        }
                        String substring = str2.substring(i10);
                        i7 = i10 + substring.length();
                        if (!"+0000".equals(substring)) {
                            if (!"+00:00".equals(substring)) {
                                String concat = TimeZones.GMT_ID.concat(substring);
                                TimeZone timeZone2 = TimeZone.getTimeZone(concat);
                                String id = timeZone2.getID();
                                if (!id.equals(concat)) {
                                    if (!id.replace(":", "").equals(concat)) {
                                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + concat + " given, resolves to " + timeZone2.getID());
                                    }
                                }
                                timeZone = timeZone2;
                            }
                        }
                        timeZone = TIMEZONE_Z;
                    }
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                    gregorianCalendar.setLenient(false);
                    gregorianCalendar.set(1, parseInt);
                    gregorianCalendar.set(2, parseInt2 - 1);
                    gregorianCalendar.set(5, parseInt3);
                    gregorianCalendar.set(11, i6);
                    gregorianCalendar.set(12, i5);
                    gregorianCalendar.set(13, i3);
                    gregorianCalendar.set(14, i4);
                    parsePosition2.setIndex(i7);
                    return gregorianCalendar.getTime();
                }
                throw new IllegalArgumentException("No time zone indicator");
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(parseInt, parseInt2 - 1, parseInt3);
            parsePosition2.setIndex(i10);
            return gregorianCalendar2.getTime();
        } catch (Exception e3) {
            String i17 = a.i('\"', "\"", str2);
            String message = e3.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e3.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException(C0118y.f("Failed to parse date ", i17, ": ", message), parsePosition.getIndex());
            parseException.initCause(e3);
            throw parseException;
        }
    }

    private static int parseInt(String str, int i3, int i4) throws NumberFormatException {
        int i5;
        int i6;
        if (i3 < 0 || i4 > str.length() || i3 > i4) {
            throw new NumberFormatException(str);
        }
        if (i3 < i4) {
            i6 = i3 + 1;
            int digit = Character.digit(str.charAt(i3), 10);
            if (digit >= 0) {
                i5 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i3, i4));
            }
        } else {
            i5 = 0;
            i6 = i3;
        }
        while (i6 < i4) {
            int i7 = i6 + 1;
            int digit2 = Character.digit(str.charAt(i6), 10);
            if (digit2 >= 0) {
                i5 = (i5 * 10) - digit2;
                i6 = i7;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i3, i4));
            }
        }
        return -i5;
    }

    public static String format(Date date, boolean z2) {
        return format(date, z2, TIMEZONE_Z);
    }

    @Deprecated
    public static String format(Date date, boolean z2, TimeZone timeZone) {
        return format(date, z2, timeZone, Locale.US);
    }

    public static String format(Date date, boolean z2, TimeZone timeZone, Locale locale) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, locale);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(30);
        sb.append(String.format("%04d-%02d-%02dT%02d:%02d:%02d", new Object[]{Integer.valueOf(gregorianCalendar.get(1)), Integer.valueOf(gregorianCalendar.get(2) + 1), Integer.valueOf(gregorianCalendar.get(5)), Integer.valueOf(gregorianCalendar.get(11)), Integer.valueOf(gregorianCalendar.get(12)), Integer.valueOf(gregorianCalendar.get(13))}));
        if (z2) {
            sb.append(String.format(".%03d", new Object[]{Integer.valueOf(gregorianCalendar.get(14))}));
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i3 = offset / 60000;
            sb.append(String.format("%c%02d:%02d", new Object[]{Character.valueOf(offset < 0 ? SignatureVisitor.SUPER : SignatureVisitor.EXTENDS), Integer.valueOf(Math.abs(i3 / 60)), Integer.valueOf(Math.abs(i3 % 60))}));
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }
}
