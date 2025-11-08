package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.media.MediaCodec;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderException;

public class MediaCodecDecoderException extends DecoderException {
    @Nullable
    public final MediaCodecInfo codecInfo;
    @Nullable
    public final String diagnosticInfo;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MediaCodecDecoderException(java.lang.Throwable r4, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Decoder failed: "
            r0.<init>(r1)
            r1 = 0
            if (r5 != 0) goto L_0x000c
            r2 = r1
            goto L_0x000e
        L_0x000c:
            java.lang.String r2 = r5.name
        L_0x000e:
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0, r4)
            r3.codecInfo = r5
            int r5 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT
            r0 = 21
            if (r5 < r0) goto L_0x0024
            java.lang.String r1 = getDiagnosticInfoV21(r4)
        L_0x0024:
            r3.diagnosticInfo = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecDecoderException.<init>(java.lang.Throwable, com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo):void");
    }

    @RequiresApi(21)
    @Nullable
    private static String getDiagnosticInfoV21(Throwable th) {
        if (th instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) th).getDiagnosticInfo();
        }
        return null;
    }
}
