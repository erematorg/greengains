package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

@Immutable
public class PrfImpl implements Prf {
    private final StreamingPrf prfStreamer;

    private PrfImpl(StreamingPrf streamingPrf) {
        this.prfStreamer = streamingPrf;
    }

    private static byte[] readBytesFromStream(InputStream inputStream, int i3) throws GeneralSecurityException {
        try {
            byte[] bArr = new byte[i3];
            int i4 = 0;
            while (i4 < i3) {
                int read = inputStream.read(bArr, i4, i3 - i4);
                if (read > 0) {
                    i4 += read;
                } else {
                    throw new GeneralSecurityException("Provided StreamingPrf terminated before providing requested number of bytes.");
                }
            }
            return bArr;
        } catch (IOException e3) {
            throw new GeneralSecurityException(e3);
        }
    }

    public static PrfImpl wrap(StreamingPrf streamingPrf) {
        return new PrfImpl(streamingPrf);
    }

    public byte[] compute(byte[] bArr, int i3) throws GeneralSecurityException {
        if (bArr == null) {
            throw new GeneralSecurityException("Invalid input provided.");
        } else if (i3 > 0) {
            return readBytesFromStream(this.prfStreamer.computePrf(bArr), i3);
        } else {
            throw new GeneralSecurityException("Invalid outputLength specified.");
        }
    }
}
