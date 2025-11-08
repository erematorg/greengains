package com.google.zxing.client.result;

import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class SMSParsedResult extends ParsedResult {
    private final String body;
    private final String[] numbers;
    private final String subject;
    private final String[] vias;

    public SMSParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.SMS);
        this.numbers = new String[]{str};
        this.vias = new String[]{str2};
        this.subject = str3;
        this.body = str4;
    }

    public String getBody() {
        return this.body;
    }

    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(this.numbers, sb);
        ParsedResult.maybeAppend(this.subject, sb);
        ParsedResult.maybeAppend(this.body, sb);
        return sb.toString();
    }

    public String[] getNumbers() {
        return this.numbers;
    }

    public String getSMSURI() {
        StringBuilder sb = new StringBuilder("sms:");
        boolean z2 = true;
        boolean z3 = true;
        for (int i3 = 0; i3 < this.numbers.length; i3++) {
            if (z3) {
                z3 = false;
            } else {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(this.numbers[i3]);
            String[] strArr = this.vias;
            if (!(strArr == null || strArr[i3] == null)) {
                sb.append(";via=");
                sb.append(this.vias[i3]);
            }
        }
        boolean z4 = this.body != null;
        if (this.subject == null) {
            z2 = false;
        }
        if (z4 || z2) {
            sb.append('?');
            if (z4) {
                sb.append("body=");
                sb.append(this.body);
            }
            if (z2) {
                if (z4) {
                    sb.append(Typography.amp);
                }
                sb.append("subject=");
                sb.append(this.subject);
            }
        }
        return sb.toString();
    }

    public String getSubject() {
        return this.subject;
    }

    public String[] getVias() {
        return this.vias;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.SMS);
        this.numbers = strArr;
        this.vias = strArr2;
        this.subject = str;
        this.body = str2;
    }
}
