package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataFormatMatcher {
    protected final byte[] _bufferedData;
    protected final int _bufferedLength;
    protected final int _bufferedStart;
    protected final JsonFactory _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;

    public DataFormatMatcher(InputStream inputStream, byte[] bArr, int i3, int i4, JsonFactory jsonFactory, MatchStrength matchStrength) {
        this._originalStream = inputStream;
        this._bufferedData = bArr;
        this._bufferedStart = i3;
        this._bufferedLength = i4;
        this._match = jsonFactory;
        this._matchStrength = matchStrength;
        if ((i3 | i4) < 0 || i3 + i4 > bArr.length) {
            throw new IllegalArgumentException(String.format("Illegal start/length (%d/%d) wrt input array of %d bytes", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(bArr.length)}));
        }
    }

    public JsonParser createParserWithMatch() throws IOException {
        JsonFactory jsonFactory = this._match;
        if (jsonFactory == null) {
            return null;
        }
        return this._originalStream == null ? jsonFactory.createParser(this._bufferedData, this._bufferedStart, this._bufferedLength) : jsonFactory.createParser(getDataStream());
    }

    public InputStream getDataStream() {
        return this._originalStream == null ? new ByteArrayInputStream(this._bufferedData, this._bufferedStart, this._bufferedLength) : new MergedStream((IOContext) null, this._originalStream, this._bufferedData, this._bufferedStart, this._bufferedLength);
    }

    public JsonFactory getMatch() {
        return this._match;
    }

    public MatchStrength getMatchStrength() {
        MatchStrength matchStrength = this._matchStrength;
        return matchStrength == null ? MatchStrength.INCONCLUSIVE : matchStrength;
    }

    public String getMatchedFormatName() {
        if (hasMatch()) {
            return getMatch().getFormatName();
        }
        return null;
    }

    public boolean hasMatch() {
        return this._match != null;
    }
}
