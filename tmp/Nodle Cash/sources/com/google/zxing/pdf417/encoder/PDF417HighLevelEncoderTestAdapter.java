package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import java.nio.charset.Charset;

public final class PDF417HighLevelEncoderTestAdapter {
    private PDF417HighLevelEncoderTestAdapter() {
    }

    public static String encodeHighLevel(String str, Compaction compaction, Charset charset, boolean z2) throws WriterException {
        return PDF417HighLevelEncoder.encodeHighLevel(str, compaction, charset, z2);
    }
}
