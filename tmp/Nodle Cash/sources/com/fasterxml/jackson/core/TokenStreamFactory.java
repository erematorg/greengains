package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.net.URL;

public abstract class TokenStreamFactory implements Versioned, Serializable {
    private static final long serialVersionUID = 2;

    public void _checkRangeBoundsForByteArray(byte[] bArr, int i3, int i4) throws IllegalArgumentException {
        if (bArr == null) {
            _reportRangeError("Invalid `byte[]` argument: `null`");
        }
        int length = bArr.length;
        int i5 = i3 + i4;
        if (((length - i5) | i3 | i4 | i5) < 0) {
            _reportRangeError(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `byte[]` of length %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(length)}));
        }
    }

    public void _checkRangeBoundsForCharArray(char[] cArr, int i3, int i4) throws IOException {
        if (cArr == null) {
            _reportRangeError("Invalid `char[]` argument: `null`");
        }
        int length = cArr.length;
        int i5 = i3 + i4;
        if (((length - i5) | i3 | i4 | i5) < 0) {
            _reportRangeError(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `char[]` of length %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(length)}));
        }
    }

    public OutputStream _createDataOutputWrapper(DataOutput dataOutput) {
        return new DataOutputAsStream(dataOutput);
    }

    public InputStream _fileInputStream(File file) throws IOException {
        return new FileInputStream(file);
    }

    public OutputStream _fileOutputStream(File file) throws IOException {
        return new FileOutputStream(file);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r1 = r2.getHost();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.InputStream _optimizedStreamFromURL(java.net.URL r2) throws java.io.IOException {
        /*
            r1 = this;
            java.lang.String r1 = r2.getProtocol()
            java.lang.String r0 = "file"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x002e
            java.lang.String r1 = r2.getHost()
            if (r1 == 0) goto L_0x0018
            int r1 = r1.length()
            if (r1 != 0) goto L_0x002e
        L_0x0018:
            java.lang.String r1 = r2.getPath()
            r0 = 37
            int r1 = r1.indexOf(r0)
            if (r1 >= 0) goto L_0x002e
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.lang.String r2 = r2.getPath()
            r1.<init>(r2)
            return r1
        L_0x002e:
            java.io.InputStream r1 = r2.openStream()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.TokenStreamFactory._optimizedStreamFromURL(java.net.URL):java.io.InputStream");
    }

    public <T> T _reportRangeError(String str) throws IllegalArgumentException {
        throw new IllegalArgumentException(str);
    }

    public abstract boolean canHandleBinaryNatively();

    public abstract boolean canParseAsync();

    public abstract boolean canUseSchema(FormatSchema formatSchema);

    public abstract JsonGenerator createGenerator(DataOutput dataOutput) throws IOException;

    public abstract JsonGenerator createGenerator(DataOutput dataOutput, JsonEncoding jsonEncoding) throws IOException;

    public abstract JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException;

    public abstract JsonGenerator createGenerator(OutputStream outputStream) throws IOException;

    public abstract JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException;

    public abstract JsonGenerator createGenerator(Writer writer) throws IOException;

    public abstract JsonParser createNonBlockingByteArrayParser() throws IOException;

    public abstract JsonParser createNonBlockingByteBufferParser() throws IOException;

    public abstract JsonParser createParser(DataInput dataInput) throws IOException;

    public abstract JsonParser createParser(File file) throws IOException;

    public abstract JsonParser createParser(InputStream inputStream) throws IOException;

    public abstract JsonParser createParser(Reader reader) throws IOException;

    public abstract JsonParser createParser(String str) throws IOException;

    public abstract JsonParser createParser(URL url) throws IOException;

    public abstract JsonParser createParser(byte[] bArr) throws IOException;

    public abstract JsonParser createParser(byte[] bArr, int i3, int i4) throws IOException;

    public abstract JsonParser createParser(char[] cArr) throws IOException;

    public abstract JsonParser createParser(char[] cArr, int i3, int i4) throws IOException;

    public abstract int getFormatGeneratorFeatures();

    public abstract String getFormatName();

    public abstract int getFormatParserFeatures();

    public abstract Class<? extends FormatFeature> getFormatReadFeatureType();

    public abstract Class<? extends FormatFeature> getFormatWriteFeatureType();

    public abstract int getGeneratorFeatures();

    public abstract int getParserFeatures();

    public abstract boolean isEnabled(JsonGenerator.Feature feature);

    public abstract boolean isEnabled(JsonParser.Feature feature);

    public abstract boolean requiresPropertyOrdering();
}
