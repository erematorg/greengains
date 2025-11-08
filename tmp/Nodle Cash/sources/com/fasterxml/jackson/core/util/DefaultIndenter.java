package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;

public class DefaultIndenter extends DefaultPrettyPrinter.NopIndenter {
    private static final int INDENT_LEVELS = 16;
    public static final DefaultIndenter SYSTEM_LINEFEED_INSTANCE;
    public static final String SYS_LF;
    private static final long serialVersionUID = 1;
    private final int charsPerLevel;
    private final String eol;
    private final char[] indents;

    static {
        String str;
        try {
            str = System.getProperty("line.separator");
        } catch (Throwable unused) {
            str = "\n";
        }
        SYS_LF = str;
        SYSTEM_LINEFEED_INSTANCE = new DefaultIndenter("  ", str);
    }

    public DefaultIndenter() {
        this("  ", SYS_LF);
    }

    public String getEol() {
        return this.eol;
    }

    public String getIndent() {
        return new String(this.indents, 0, this.charsPerLevel);
    }

    public boolean isInline() {
        return false;
    }

    public DefaultIndenter withIndent(String str) {
        return str.equals(getIndent()) ? this : new DefaultIndenter(str, this.eol);
    }

    public DefaultIndenter withLinefeed(String str) {
        return str.equals(this.eol) ? this : new DefaultIndenter(getIndent(), str);
    }

    public void writeIndentation(JsonGenerator jsonGenerator, int i3) throws IOException {
        jsonGenerator.writeRaw(this.eol);
        if (i3 > 0) {
            int i4 = i3 * this.charsPerLevel;
            while (true) {
                char[] cArr = this.indents;
                if (i4 > cArr.length) {
                    jsonGenerator.writeRaw(cArr, 0, cArr.length);
                    i4 -= this.indents.length;
                } else {
                    jsonGenerator.writeRaw(cArr, 0, i4);
                    return;
                }
            }
        }
    }

    public DefaultIndenter(String str, String str2) {
        this.charsPerLevel = str.length();
        this.indents = new char[(str.length() * 16)];
        int i3 = 0;
        for (int i4 = 0; i4 < 16; i4++) {
            str.getChars(0, str.length(), this.indents, i3);
            i3 += str.length();
        }
        this.eol = str2;
    }
}
