package com.appsamurai.storyly.exoplayer2.common.upstream;

import java.io.IOException;

public interface DataReader {
    int read(byte[] bArr, int i3, int i4) throws IOException;
}
