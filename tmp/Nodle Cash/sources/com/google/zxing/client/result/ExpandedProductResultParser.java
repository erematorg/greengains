package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import org.apache.commons.lang3.CharUtils;

public final class ExpandedProductResultParser extends ResultParser {
    private static String findAIvalue(int i3, String str) {
        if (str.charAt(i3) != '(') {
            return null;
        }
        String substring = str.substring(i3 + 1);
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < substring.length(); i4++) {
            char charAt = substring.charAt(i4);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i3, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i3);
        for (int i4 = 0; i4 < substring.length(); i4++) {
            char charAt = substring.charAt(i4);
            if (charAt != '(') {
                sb.append(charAt);
            } else if (findAIvalue(i4, substring) != null) {
                break;
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }

    public ExpandedProductParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = ResultParser.getMassagedText(result);
        HashMap hashMap = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        int i3 = 0;
        while (i3 < massagedText.length()) {
            String findAIvalue = findAIvalue(i3, massagedText);
            if (findAIvalue == null) {
                return null;
            }
            int length = findAIvalue.length() + 2 + i3;
            String findValue = findValue(length, massagedText);
            i3 = findValue.length() + length;
            char c3 = 65535;
            switch (findAIvalue.hashCode()) {
                case 1536:
                    if (findAIvalue.equals("00")) {
                        c3 = 0;
                        break;
                    }
                    break;
                case 1537:
                    if (findAIvalue.equals("01")) {
                        c3 = 1;
                        break;
                    }
                    break;
                case 1567:
                    if (findAIvalue.equals("10")) {
                        c3 = 2;
                        break;
                    }
                    break;
                case 1568:
                    if (findAIvalue.equals("11")) {
                        c3 = 3;
                        break;
                    }
                    break;
                case 1570:
                    if (findAIvalue.equals("13")) {
                        c3 = 4;
                        break;
                    }
                    break;
                case 1572:
                    if (findAIvalue.equals("15")) {
                        c3 = 5;
                        break;
                    }
                    break;
                case 1574:
                    if (findAIvalue.equals("17")) {
                        c3 = 6;
                        break;
                    }
                    break;
                case 1567966:
                    if (findAIvalue.equals("3100")) {
                        c3 = 7;
                        break;
                    }
                    break;
                case 1567967:
                    if (findAIvalue.equals("3101")) {
                        c3 = 8;
                        break;
                    }
                    break;
                case 1567968:
                    if (findAIvalue.equals("3102")) {
                        c3 = 9;
                        break;
                    }
                    break;
                case 1567969:
                    if (findAIvalue.equals("3103")) {
                        c3 = 10;
                        break;
                    }
                    break;
                case 1567970:
                    if (findAIvalue.equals("3104")) {
                        c3 = 11;
                        break;
                    }
                    break;
                case 1567971:
                    if (findAIvalue.equals("3105")) {
                        c3 = 12;
                        break;
                    }
                    break;
                case 1567972:
                    if (findAIvalue.equals("3106")) {
                        c3 = CharUtils.CR;
                        break;
                    }
                    break;
                case 1567973:
                    if (findAIvalue.equals("3107")) {
                        c3 = 14;
                        break;
                    }
                    break;
                case 1567974:
                    if (findAIvalue.equals("3108")) {
                        c3 = 15;
                        break;
                    }
                    break;
                case 1567975:
                    if (findAIvalue.equals("3109")) {
                        c3 = 16;
                        break;
                    }
                    break;
                case 1568927:
                    if (findAIvalue.equals("3200")) {
                        c3 = 17;
                        break;
                    }
                    break;
                case 1568928:
                    if (findAIvalue.equals("3201")) {
                        c3 = 18;
                        break;
                    }
                    break;
                case 1568929:
                    if (findAIvalue.equals("3202")) {
                        c3 = 19;
                        break;
                    }
                    break;
                case 1568930:
                    if (findAIvalue.equals("3203")) {
                        c3 = 20;
                        break;
                    }
                    break;
                case 1568931:
                    if (findAIvalue.equals("3204")) {
                        c3 = 21;
                        break;
                    }
                    break;
                case 1568932:
                    if (findAIvalue.equals("3205")) {
                        c3 = 22;
                        break;
                    }
                    break;
                case 1568933:
                    if (findAIvalue.equals("3206")) {
                        c3 = 23;
                        break;
                    }
                    break;
                case 1568934:
                    if (findAIvalue.equals("3207")) {
                        c3 = 24;
                        break;
                    }
                    break;
                case 1568935:
                    if (findAIvalue.equals("3208")) {
                        c3 = 25;
                        break;
                    }
                    break;
                case 1568936:
                    if (findAIvalue.equals("3209")) {
                        c3 = 26;
                        break;
                    }
                    break;
                case 1575716:
                    if (findAIvalue.equals("3920")) {
                        c3 = 27;
                        break;
                    }
                    break;
                case 1575717:
                    if (findAIvalue.equals("3921")) {
                        c3 = 28;
                        break;
                    }
                    break;
                case 1575718:
                    if (findAIvalue.equals("3922")) {
                        c3 = 29;
                        break;
                    }
                    break;
                case 1575719:
                    if (findAIvalue.equals("3923")) {
                        c3 = 30;
                        break;
                    }
                    break;
                case 1575747:
                    if (findAIvalue.equals("3930")) {
                        c3 = 31;
                        break;
                    }
                    break;
                case 1575748:
                    if (findAIvalue.equals("3931")) {
                        c3 = ' ';
                        break;
                    }
                    break;
                case 1575749:
                    if (findAIvalue.equals("3932")) {
                        c3 = '!';
                        break;
                    }
                    break;
                case 1575750:
                    if (findAIvalue.equals("3933")) {
                        c3 = '\"';
                        break;
                    }
                    break;
            }
            switch (c3) {
                case 0:
                    str2 = findValue;
                    continue;
                case 1:
                    str = findValue;
                    continue;
                case 2:
                    str3 = findValue;
                    continue;
                case 3:
                    str4 = findValue;
                    continue;
                case 4:
                    str5 = findValue;
                    continue;
                case 5:
                    str6 = findValue;
                    continue;
                case 6:
                    str7 = findValue;
                    continue;
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    str12 = findAIvalue.substring(3);
                    str11 = findValue;
                    continue;
                case 31:
                case ' ':
                case '!':
                case '\"':
                    if (findValue.length() < 4) {
                        return null;
                    }
                    str11 = findValue.substring(3);
                    str13 = findValue.substring(0, 3);
                    str12 = findAIvalue.substring(3);
                    continue;
                default:
                    hashMap.put(findAIvalue, findValue);
                    continue;
            }
            str8 = findValue;
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, hashMap);
    }
}
