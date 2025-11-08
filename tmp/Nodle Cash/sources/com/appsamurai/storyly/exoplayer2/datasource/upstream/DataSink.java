package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import java.io.IOException;

public interface DataSink {

    public interface Factory {
        DataSink createDataSink();
    }

    void close() throws IOException;

    void open(DataSpec dataSpec) throws IOException;

    void write(byte[] bArr, int i3, int i4) throws IOException;
}
