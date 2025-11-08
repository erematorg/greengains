package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.CharUtils;
import org.apache.xml.serialize.LineSeparator;

public final class AddressBookAUResultParser extends ResultParser {
    private static String[] matchMultipleValuePrefix(String str, String str2) {
        ArrayList arrayList = null;
        for (int i3 = 1; i3 <= 3; i3++) {
            String matchSinglePrefixedField = ResultParser.matchSinglePrefixedField(str + i3 + AbstractJsonLexerKt.COLON, str2, CharUtils.CR, true);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(3);
            }
            arrayList.add(matchSinglePrefixedField);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(ResultParser.EMPTY_STR_ARRAY);
    }

    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        String[] strArr = null;
        if (!massagedText.contains("MEMORY") || !massagedText.contains(LineSeparator.Windows)) {
            return null;
        }
        String matchSinglePrefixedField = ResultParser.matchSinglePrefixedField("NAME1:", massagedText, CharUtils.CR, true);
        String matchSinglePrefixedField2 = ResultParser.matchSinglePrefixedField("NAME2:", massagedText, CharUtils.CR, true);
        String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", massagedText);
        String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", massagedText);
        String matchSinglePrefixedField3 = ResultParser.matchSinglePrefixedField("MEMORY:", massagedText, CharUtils.CR, false);
        String matchSinglePrefixedField4 = ResultParser.matchSinglePrefixedField("ADD:", massagedText, CharUtils.CR, true);
        if (matchSinglePrefixedField4 != null) {
            strArr = new String[]{matchSinglePrefixedField4};
        }
        return new AddressBookParsedResult(ResultParser.maybeWrap(matchSinglePrefixedField), (String[]) null, matchSinglePrefixedField2, matchMultipleValuePrefix, (String[]) null, matchMultipleValuePrefix2, (String[]) null, (String) null, matchSinglePrefixedField3, strArr, (String[]) null, (String) null, (String) null, (String) null, (String[]) null, (String[]) null);
    }
}
