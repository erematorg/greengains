package com.fasterxml.jackson.core.format;

import A.a;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public interface InputAccessor {
    boolean hasMoreBytes() throws IOException;

    byte nextByte() throws IOException;

    void reset();

    public static class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedEnd;
        protected final int _bufferedStart;
        protected final InputStream _in;
        protected int _ptr;

        public Std(InputStream inputStream, byte[] bArr) {
            this._in = inputStream;
            this._buffer = bArr;
            this._bufferedStart = 0;
            this._ptr = 0;
            this._bufferedEnd = 0;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            InputStream inputStream = this._in;
            byte[] bArr = this._buffer;
            int i3 = this._bufferedStart;
            return new DataFormatMatcher(inputStream, bArr, i3, this._bufferedEnd - i3, jsonFactory, matchStrength);
        }

        public boolean hasMoreBytes() throws IOException {
            int read;
            int i3 = this._ptr;
            if (i3 < this._bufferedEnd) {
                return true;
            }
            InputStream inputStream = this._in;
            if (inputStream == null) {
                return false;
            }
            byte[] bArr = this._buffer;
            int length = bArr.length - i3;
            if (length < 1 || (read = inputStream.read(bArr, i3, length)) <= 0) {
                return false;
            }
            this._bufferedEnd += read;
            return true;
        }

        public byte nextByte() throws IOException {
            if (this._ptr < this._bufferedEnd || hasMoreBytes()) {
                byte[] bArr = this._buffer;
                int i3 = this._ptr;
                this._ptr = i3 + 1;
                return bArr[i3];
            }
            StringBuilder sb = new StringBuilder("Failed auto-detect: could not read more than ");
            sb.append(this._ptr);
            sb.append(" bytes (max buffer size: ");
            throw new EOFException(a.m(sb, ")", this._buffer.length));
        }

        public void reset() {
            this._ptr = this._bufferedStart;
        }

        public Std(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        public Std(byte[] bArr, int i3, int i4) {
            this._in = null;
            this._buffer = bArr;
            this._ptr = i3;
            this._bufferedStart = i3;
            this._bufferedEnd = i3 + i4;
        }
    }
}
