package com.google.zxing.client.result;

import androidx.core.net.MailTo;
import java.util.List;

public final class VEventResultParser extends ResultParser {
    private static String matchSingleVCardPrefixedField(CharSequence charSequence, String str) {
        List<String> matchSingleVCardPrefixedField = VCardResultParser.matchSingleVCardPrefixedField(charSequence, str, true, false);
        if (matchSingleVCardPrefixedField == null || matchSingleVCardPrefixedField.isEmpty()) {
            return null;
        }
        return matchSingleVCardPrefixedField.get(0);
    }

    private static String[] matchVCardPrefixedField(CharSequence charSequence, String str) {
        List<List<String>> matchVCardPrefixedField = VCardResultParser.matchVCardPrefixedField(charSequence, str, true, false);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        int size = matchVCardPrefixedField.size();
        String[] strArr = new String[size];
        for (int i3 = 0; i3 < size; i3++) {
            strArr[i3] = (String) matchVCardPrefixedField.get(i3).get(0);
        }
        return strArr;
    }

    private static String stripMailto(String str) {
        return str != null ? (str.startsWith(MailTo.MAILTO_SCHEME) || str.startsWith("MAILTO:")) ? str.substring(7) : str : str;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.CalendarParsedResult parse(com.google.zxing.Result r15) {
        /*
            r14 = this;
            java.lang.String r14 = com.google.zxing.client.result.ResultParser.getMassagedText(r15)
            java.lang.String r15 = "BEGIN:VEVENT"
            int r15 = r14.indexOf(r15)
            r0 = 0
            if (r15 >= 0) goto L_0x000e
            return r0
        L_0x000e:
            java.lang.String r15 = "SUMMARY"
            java.lang.String r2 = matchSingleVCardPrefixedField(r15, r14)
            java.lang.String r15 = "DTSTART"
            java.lang.String r3 = matchSingleVCardPrefixedField(r15, r14)
            if (r3 != 0) goto L_0x001d
            return r0
        L_0x001d:
            java.lang.String r15 = "DTEND"
            java.lang.String r4 = matchSingleVCardPrefixedField(r15, r14)
            java.lang.String r15 = "DURATION"
            java.lang.String r5 = matchSingleVCardPrefixedField(r15, r14)
            java.lang.String r15 = "LOCATION"
            java.lang.String r6 = matchSingleVCardPrefixedField(r15, r14)
            java.lang.String r15 = "ORGANIZER"
            java.lang.String r15 = matchSingleVCardPrefixedField(r15, r14)
            java.lang.String r7 = stripMailto(r15)
            java.lang.String r15 = "ATTENDEE"
            java.lang.String[] r8 = matchVCardPrefixedField(r15, r14)
            r15 = 0
            if (r8 == 0) goto L_0x0051
            r1 = r15
        L_0x0043:
            int r9 = r8.length
            if (r1 >= r9) goto L_0x0051
            r9 = r8[r1]
            java.lang.String r9 = stripMailto(r9)
            r8[r1] = r9
            int r1 = r1 + 1
            goto L_0x0043
        L_0x0051:
            java.lang.String r1 = "DESCRIPTION"
            java.lang.String r9 = matchSingleVCardPrefixedField(r1, r14)
            java.lang.String r1 = "GEO"
            java.lang.String r14 = matchSingleVCardPrefixedField(r1, r14)
            if (r14 != 0) goto L_0x0064
            r14 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r10 = r14
            r12 = r10
            goto L_0x0080
        L_0x0064:
            r1 = 59
            int r1 = r14.indexOf(r1)
            if (r1 >= 0) goto L_0x006d
            return r0
        L_0x006d:
            java.lang.String r15 = r14.substring(r15, r1)     // Catch:{ NumberFormatException -> 0x0087 }
            double r10 = java.lang.Double.parseDouble(r15)     // Catch:{ NumberFormatException -> 0x0087 }
            int r1 = r1 + 1
            java.lang.String r14 = r14.substring(r1)     // Catch:{ NumberFormatException -> 0x0087 }
            double r14 = java.lang.Double.parseDouble(r14)     // Catch:{ NumberFormatException -> 0x0087 }
            r12 = r14
        L_0x0080:
            com.google.zxing.client.result.CalendarParsedResult r14 = new com.google.zxing.client.result.CalendarParsedResult     // Catch:{  }
            r1 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r12)     // Catch:{  }
            return r14
        L_0x0087:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VEventResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.CalendarParsedResult");
    }
}
