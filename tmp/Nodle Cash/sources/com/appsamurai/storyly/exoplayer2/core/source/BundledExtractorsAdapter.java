package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Mp3Extractor;
import java.io.IOException;

public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    @Nullable
    private Extractor extractor;
    @Nullable
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory2) {
        this.extractorsFactory = extractorsFactory2;
    }

    public void disableSeekingOnMp3Streams() {
        Extractor extractor2 = this.extractor;
        if (extractor2 instanceof Mp3Extractor) {
            ((Mp3Extractor) extractor2).disableSeeking();
        }
    }

    public long getCurrentInputPosition() {
        ExtractorInput extractorInput2 = this.extractorInput;
        if (extractorInput2 != null) {
            return extractorInput2.getPosition();
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r0.getPosition() != r11) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006c, code lost:
        if (r0.getPosition() != r11) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init(com.appsamurai.storyly.exoplayer2.common.upstream.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorInput r6 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorInput
            r0 = r6
            r1 = r8
            r2 = r11
            r4 = r13
            r0.<init>(r1, r2, r4)
            r7.extractorInput = r6
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto L_0x0010
            return
        L_0x0010:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            r13 = 0
            r14 = 1
            if (r10 != r14) goto L_0x0020
            r8 = r8[r13]
            r7.extractor = r8
            goto L_0x0076
        L_0x0020:
            int r10 = r8.length
            r0 = r13
        L_0x0022:
            if (r0 >= r10) goto L_0x0072
            r1 = r8[r0]
            boolean r2 = r1.sniff(r6)     // Catch:{ EOFException -> 0x0062, all -> 0x0035 }
            if (r2 == 0) goto L_0x0037
            r7.extractor = r1     // Catch:{ EOFException -> 0x0062, all -> 0x0035 }
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r14)
            r6.resetPeekPosition()
            goto L_0x0072
        L_0x0035:
            r8 = move-exception
            goto L_0x004e
        L_0x0037:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor r1 = r7.extractor
            if (r1 != 0) goto L_0x0046
            long r1 = r6.getPosition()
            int r1 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r1 != 0) goto L_0x0044
            goto L_0x0046
        L_0x0044:
            r1 = r13
            goto L_0x0047
        L_0x0046:
            r1 = r14
        L_0x0047:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r1)
            r6.resetPeekPosition()
            goto L_0x006f
        L_0x004e:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor r7 = r7.extractor
            if (r7 != 0) goto L_0x005a
            long r9 = r6.getPosition()
            int r7 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x005b
        L_0x005a:
            r13 = r14
        L_0x005b:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r13)
            r6.resetPeekPosition()
            throw r8
        L_0x0062:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor r1 = r7.extractor
            if (r1 != 0) goto L_0x0046
            long r1 = r6.getPosition()
            int r1 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r1 != 0) goto L_0x0044
            goto L_0x0046
        L_0x006f:
            int r0 = r0 + 1
            goto L_0x0022
        L_0x0072:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor r10 = r7.extractor
            if (r10 == 0) goto L_0x007c
        L_0x0076:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor r7 = r7.extractor
            r7.init(r15)
            return
        L_0x007c:
            com.appsamurai.storyly.exoplayer2.core.source.UnrecognizedInputFormatException r7 = new com.appsamurai.storyly.exoplayer2.core.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "None of the available extractors ("
            r10.<init>(r11)
            java.lang.String r8 = com.appsamurai.storyly.exoplayer2.common.util.Util.getCommaDelimitedSimpleClassNames(r8)
            java.lang.String r11 = ") could read the stream."
            java.lang.String r8 = A.a.n(r10, r8, r11)
            java.lang.Object r9 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r9)
            android.net.Uri r9 = (android.net.Uri) r9
            r7.<init>(r8, r9)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.BundledExtractorsAdapter.init(com.appsamurai.storyly.exoplayer2.common.upstream.DataReader, android.net.Uri, java.util.Map, long, long, com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput):void");
    }

    public int read(PositionHolder positionHolder) throws IOException {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }

    public void release() {
        Extractor extractor2 = this.extractor;
        if (extractor2 != null) {
            extractor2.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    public void seek(long j2, long j3) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(j2, j3);
    }
}
