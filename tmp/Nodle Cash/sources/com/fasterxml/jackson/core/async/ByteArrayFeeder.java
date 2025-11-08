package com.fasterxml.jackson.core.async;

import java.io.IOException;

public interface ByteArrayFeeder extends NonBlockingInputFeeder {
    void feedInput(byte[] bArr, int i3, int i4) throws IOException;
}
