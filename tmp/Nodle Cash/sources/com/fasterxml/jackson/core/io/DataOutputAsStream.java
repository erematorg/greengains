package com.fasterxml.jackson.core.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputAsStream extends OutputStream {
    protected final DataOutput _output;

    public DataOutputAsStream(DataOutput dataOutput) {
        this._output = dataOutput;
    }

    public void write(int i3) throws IOException {
        this._output.write(i3);
    }

    public void write(byte[] bArr) throws IOException {
        this._output.write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i3, int i4) throws IOException {
        this._output.write(bArr, i3, i4);
    }
}
