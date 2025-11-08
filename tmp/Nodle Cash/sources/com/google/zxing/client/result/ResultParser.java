package com.google.zxing.client.result;

import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.zxing.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class ResultParser {
    private static final Pattern AMPERSAND = Pattern.compile("&");
    private static final String BYTE_ORDER_MARK = "﻿";
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    static final String[] EMPTY_STR_ARRAY = new String[0];
    private static final Pattern EQUALS = Pattern.compile(StickyVariantProvider.KEY_VALUE_DELIMITER);
    private static final ResultParser[] PARSERS;

    static {
        BookmarkDoCoMoResultParser bookmarkDoCoMoResultParser = new BookmarkDoCoMoResultParser();
        AddressBookDoCoMoResultParser addressBookDoCoMoResultParser = new AddressBookDoCoMoResultParser();
        EmailDoCoMoResultParser emailDoCoMoResultParser = new EmailDoCoMoResultParser();
        AddressBookAUResultParser addressBookAUResultParser = new AddressBookAUResultParser();
        VCardResultParser vCardResultParser = new VCardResultParser();
        BizcardResultParser bizcardResultParser = new BizcardResultParser();
        VEventResultParser vEventResultParser = new VEventResultParser();
        EmailAddressResultParser emailAddressResultParser = new EmailAddressResultParser();
        SMTPResultParser sMTPResultParser = new SMTPResultParser();
        TelResultParser telResultParser = new TelResultParser();
        SMSMMSResultParser sMSMMSResultParser = new SMSMMSResultParser();
        SMSTOMMSTOResultParser sMSTOMMSTOResultParser = new SMSTOMMSTOResultParser();
        GeoResultParser geoResultParser = new GeoResultParser();
        WifiResultParser wifiResultParser = new WifiResultParser();
        URLTOResultParser uRLTOResultParser = new URLTOResultParser();
        URIResultParser uRIResultParser = new URIResultParser();
        URIResultParser uRIResultParser2 = uRIResultParser;
        PARSERS = new ResultParser[]{bookmarkDoCoMoResultParser, addressBookDoCoMoResultParser, emailDoCoMoResultParser, addressBookAUResultParser, vCardResultParser, bizcardResultParser, vEventResultParser, emailAddressResultParser, sMTPResultParser, telResultParser, sMSMMSResultParser, sMSTOMMSTOResultParser, geoResultParser, wifiResultParser, uRLTOResultParser, uRIResultParser2, new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};
    }

    private static void appendKeyValue(CharSequence charSequence, Map<String, String> map) {
        String[] split = EQUALS.split(charSequence, 2);
        if (split.length == 2) {
            try {
                map.put(split[0], urlDecode(split[1]));
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    private static int countPrecedingBackslashes(CharSequence charSequence, int i3) {
        int i4 = i3 - 1;
        int i5 = 0;
        while (i4 >= 0 && charSequence.charAt(i4) == '\\') {
            i5++;
            i4--;
        }
        return i5;
    }

    public static String getMassagedText(Result result) {
        String text = result.getText();
        return text.startsWith(BYTE_ORDER_MARK) ? text.substring(1) : text;
    }

    public static boolean isStringOfDigits(CharSequence charSequence, int i3) {
        return charSequence != null && i3 > 0 && i3 == charSequence.length() && DIGITS.matcher(charSequence).matches();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r4 = r4 + r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isSubstringOfDigits(java.lang.CharSequence r2, int r3, int r4) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x001e
            if (r4 > 0) goto L_0x0006
            goto L_0x001e
        L_0x0006:
            int r4 = r4 + r3
            int r1 = r2.length()
            if (r1 < r4) goto L_0x001e
            java.util.regex.Pattern r1 = DIGITS
            java.lang.CharSequence r2 = r2.subSequence(r3, r4)
            java.util.regex.Matcher r2 = r1.matcher(r2)
            boolean r2 = r2.matches()
            if (r2 == 0) goto L_0x001e
            r0 = 1
        L_0x001e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ResultParser.isSubstringOfDigits(java.lang.CharSequence, int, int):boolean");
    }

    public static String[] matchPrefixedField(String str, String str2, char c3, boolean z2) {
        int length = str2.length();
        ArrayList arrayList = null;
        int i3 = 0;
        while (i3 < length) {
            int indexOf = str2.indexOf(str, i3);
            if (indexOf < 0) {
                break;
            }
            int length2 = str.length() + indexOf;
            boolean z3 = true;
            ArrayList arrayList2 = arrayList;
            int i4 = length2;
            while (z3) {
                int indexOf2 = str2.indexOf(c3, i4);
                if (indexOf2 < 0) {
                    i4 = str2.length();
                } else if (countPrecedingBackslashes(str2, indexOf2) % 2 != 0) {
                    i4 = indexOf2 + 1;
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(3);
                    }
                    String unescapeBackslash = unescapeBackslash(str2.substring(length2, indexOf2));
                    if (z2) {
                        unescapeBackslash = unescapeBackslash.trim();
                    }
                    if (!unescapeBackslash.isEmpty()) {
                        arrayList2.add(unescapeBackslash);
                    }
                    i4 = indexOf2 + 1;
                }
                z3 = false;
            }
            i3 = i4;
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(EMPTY_STR_ARRAY);
    }

    public static String matchSinglePrefixedField(String str, String str2, char c3, boolean z2) {
        String[] matchPrefixedField = matchPrefixedField(str, str2, c3, z2);
        if (matchPrefixedField == null) {
            return null;
        }
        return matchPrefixedField[0];
    }

    public static void maybeAppend(String str, StringBuilder sb) {
        if (str != null) {
            sb.append(10);
            sb.append(str);
        }
    }

    public static String[] maybeWrap(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    public static int parseHexDigit(char c3) {
        if (c3 >= '0' && c3 <= '9') {
            return c3 - '0';
        }
        if (c3 >= 'a' && c3 <= 'f') {
            return c3 - 'W';
        }
        if (c3 < 'A' || c3 > 'F') {
            return -1;
        }
        return c3 - '7';
    }

    public static Map<String, String> parseNameValuePairs(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        HashMap hashMap = new HashMap(3);
        for (String appendKeyValue : AMPERSAND.split(str.substring(indexOf + 1))) {
            appendKeyValue(appendKeyValue, hashMap);
        }
        return hashMap;
    }

    public static ParsedResult parseResult(Result result) {
        for (ResultParser parse : PARSERS) {
            ParsedResult parse2 = parse.parse(result);
            if (parse2 != null) {
                return parse2;
            }
        }
        return new TextParsedResult(result.getText(), (String) null);
    }

    public static String unescapeBackslash(String str) {
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length - 1);
        sb.append(str.toCharArray(), 0, indexOf);
        boolean z2 = false;
        while (indexOf < length) {
            char charAt = str.charAt(indexOf);
            if (z2 || charAt != '\\') {
                sb.append(charAt);
                z2 = false;
            } else {
                z2 = true;
            }
            indexOf++;
        }
        return sb.toString();
    }

    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public abstract ParsedResult parse(Result result);

    public static void maybeAppend(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String append : strArr) {
                sb.append(10);
                sb.append(append);
            }
        }
    }
}
