package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.Writer;

public final class SegmentedStringWriter extends Writer {
    private final TextBuffer _buffer;

    public SegmentedStringWriter(BufferRecycler bufferRecycler) {
        this._buffer = new TextBuffer(bufferRecycler);
    }

    public void close() {
    }

    public void flush() {
    }

    public String getAndClear() {
        String contentsAsString = this._buffer.contentsAsString();
        this._buffer.releaseBuffers();
        return contentsAsString;
    }

    public void write(char[] cArr) {
        this._buffer.append(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i3, int i4) {
        this._buffer.append(cArr, i3, i4);
    }

    public void write(int i3) {
        this._buffer.append((char) i3);
    }

    public Writer append(char c3) {
        write((int) c3);
        return this;
    }

    public void write(String str) {
        this._buffer.append(str, 0, str.length());
    }

    public Writer append(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        this._buffer.append(charSequence2, 0, charSequence2.length());
        return this;
    }

    public void write(String str, int i3, int i4) {
        this._buffer.append(str, i3, i4);
    }

    public Writer append(CharSequence charSequence, int i3, int i4) {
        String charSequence2 = charSequence.subSequence(i3, i4).toString();
        this._buffer.append(charSequence2, 0, charSequence2.length());
        return this;
    }
}
