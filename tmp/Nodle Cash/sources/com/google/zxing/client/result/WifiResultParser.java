package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class WifiResultParser extends ResultParser {
    public WifiParsedResult parse(Result result) {
        String substring;
        String matchSinglePrefixedField;
        String str;
        boolean z2;
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith("WIFI:") || (matchSinglePrefixedField = ResultParser.matchSinglePrefixedField("S:", substring, ';', false)) == null || matchSinglePrefixedField.isEmpty()) {
            return null;
        }
        String matchSinglePrefixedField2 = ResultParser.matchSinglePrefixedField("P:", (substring = massagedText.substring(5)), ';', false);
        String matchSinglePrefixedField3 = ResultParser.matchSinglePrefixedField("T:", substring, ';', false);
        if (matchSinglePrefixedField3 == null) {
            matchSinglePrefixedField3 = "nopass";
        }
        String str2 = matchSinglePrefixedField3;
        String matchSinglePrefixedField4 = ResultParser.matchSinglePrefixedField("PH2:", substring, ';', false);
        String matchSinglePrefixedField5 = ResultParser.matchSinglePrefixedField("H:", substring, ';', false);
        if (matchSinglePrefixedField5 == null) {
            str = matchSinglePrefixedField4;
        } else if (matchSinglePrefixedField4 != null || "true".equalsIgnoreCase(matchSinglePrefixedField5) || "false".equalsIgnoreCase(matchSinglePrefixedField5)) {
            str = matchSinglePrefixedField4;
            z2 = Boolean.parseBoolean(matchSinglePrefixedField5);
            return new WifiParsedResult(str2, matchSinglePrefixedField, matchSinglePrefixedField2, z2, ResultParser.matchSinglePrefixedField("I:", substring, ';', false), ResultParser.matchSinglePrefixedField("A:", substring, ';', false), ResultParser.matchSinglePrefixedField("E:", substring, ';', false), str);
        } else {
            str = matchSinglePrefixedField5;
        }
        z2 = false;
        return new WifiParsedResult(str2, matchSinglePrefixedField, matchSinglePrefixedField2, z2, ResultParser.matchSinglePrefixedField("I:", substring, ';', false), ResultParser.matchSinglePrefixedField("A:", substring, ';', false), ResultParser.matchSinglePrefixedField("E:", substring, ';', false), str);
    }
}
