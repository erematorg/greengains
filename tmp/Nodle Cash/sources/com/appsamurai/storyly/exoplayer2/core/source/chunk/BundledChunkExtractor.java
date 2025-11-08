package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyTrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.IOException;

public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {
    public static final ChunkExtractor.Factory FACTORY = new a(0);
    private static final PositionHolder POSITION_HOLDER = new PositionHolder();
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs = new SparseArray<>();
    private long endTimeUs;
    private final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    @Nullable
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public static final class BindingTrackOutput implements TrackOutput {
        private long endTimeUs;
        private final DummyTrackOutput fakeTrackOutput = new DummyTrackOutput();
        private final int id;
        @Nullable
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int i3, int i4, @Nullable Format format) {
            this.id = i3;
            this.type = i4;
            this.manifestFormat = format;
        }

        public void bind(@Nullable ChunkExtractor.TrackOutputProvider trackOutputProvider, long j2) {
            if (trackOutputProvider == null) {
                this.trackOutput = this.fakeTrackOutput;
                return;
            }
            this.endTimeUs = j2;
            TrackOutput track = trackOutputProvider.track(this.id, this.type);
            this.trackOutput = track;
            Format format = this.sampleFormat;
            if (format != null) {
                track.format(format);
            }
        }

        public void format(Format format) {
            Format format2 = this.manifestFormat;
            if (format2 != null) {
                format = format.withManifestFormatInfo(format2);
            }
            this.sampleFormat = format;
            ((TrackOutput) Util.castNonNull(this.trackOutput)).format(this.sampleFormat);
        }

        public int sampleData(DataReader dataReader, int i3, boolean z2, int i4) throws IOException {
            return ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(dataReader, i3, z2);
        }

        public void sampleMetadata(long j2, int i3, int i4, int i5, @Nullable TrackOutput.CryptoData cryptoData) {
            long j3 = this.endTimeUs;
            if (j3 != C.TIME_UNSET && j2 >= j3) {
                this.trackOutput = this.fakeTrackOutput;
            }
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata(j2, i3, i4, i5, cryptoData);
        }

        public void sampleData(ParsableByteArray parsableByteArray, int i3, int i4) {
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(parsableByteArray, i3);
        }
    }

    public BundledChunkExtractor(Extractor extractor2, int i3, Format format) {
        this.extractor = extractor2;
        this.primaryTrackType = i3;
        this.primaryTrackManifestFormat = format;
    }

    /* JADX WARNING: type inference failed for: r8v6, types: [com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor lambda$static$0(int r6, com.appsamurai.storyly.exoplayer2.common.Format r7, boolean r8, java.util.List r9, com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput r10, com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId r11) {
        /*
            java.lang.String r11 = r7.containerMimeType
            boolean r0 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.isText(r11)
            if (r0 == 0) goto L_0x000a
            r6 = 0
            return r6
        L_0x000a:
            boolean r11 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.isMatroska(r11)
            if (r11 == 0) goto L_0x0017
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor r8 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor
            r9 = 1
            r8.<init>(r9)
            goto L_0x0028
        L_0x0017:
            if (r8 == 0) goto L_0x001c
            r8 = 4
        L_0x001a:
            r1 = r8
            goto L_0x001e
        L_0x001c:
            r8 = 0
            goto L_0x001a
        L_0x001e:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor r8 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor
            r2 = 0
            r3 = 0
            r0 = r8
            r4 = r9
            r5 = r10
            r0.<init>(r1, r2, r3, r4, r5)
        L_0x0028:
            com.appsamurai.storyly.exoplayer2.core.source.chunk.BundledChunkExtractor r9 = new com.appsamurai.storyly.exoplayer2.core.source.chunk.BundledChunkExtractor
            r9.<init>(r8, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.chunk.BundledChunkExtractor.lambda$static$0(int, com.appsamurai.storyly.exoplayer2.common.Format, boolean, java.util.List, com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput, com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId):com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor");
    }

    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i3 = 0; i3 < this.bindingTrackOutputs.size(); i3++) {
            formatArr[i3] = (Format) Assertions.checkStateNotNull(this.bindingTrackOutputs.valueAt(i3).sampleFormat);
        }
        this.sampleFormats = formatArr;
    }

    @Nullable
    public ChunkIndex getChunkIndex() {
        SeekMap seekMap2 = this.seekMap;
        if (seekMap2 instanceof ChunkIndex) {
            return (ChunkIndex) seekMap2;
        }
        return null;
    }

    @Nullable
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    public void init(@Nullable ChunkExtractor.TrackOutputProvider trackOutputProvider2, long j2, long j3) {
        this.trackOutputProvider = trackOutputProvider2;
        this.endTimeUs = j3;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            if (j2 != C.TIME_UNSET) {
                this.extractor.seek(0, j2);
            }
            this.extractorInitialized = true;
            return;
        }
        Extractor extractor2 = this.extractor;
        if (j2 == C.TIME_UNSET) {
            j2 = 0;
        }
        extractor2.seek(0, j2);
        for (int i3 = 0; i3 < this.bindingTrackOutputs.size(); i3++) {
            this.bindingTrackOutputs.valueAt(i3).bind(trackOutputProvider2, j3);
        }
    }

    public boolean read(ExtractorInput extractorInput) throws IOException {
        int read = this.extractor.read(extractorInput, POSITION_HOLDER);
        Assertions.checkState(read != 1);
        return read == 0;
    }

    public void release() {
        this.extractor.release();
    }

    public void seekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
    }

    public TrackOutput track(int i3, int i4) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(i3);
        if (bindingTrackOutput == null) {
            Assertions.checkState(this.sampleFormats == null);
            bindingTrackOutput = new BindingTrackOutput(i3, i4, i4 == this.primaryTrackType ? this.primaryTrackManifestFormat : null);
            bindingTrackOutput.bind(this.trackOutputProvider, this.endTimeUs);
            this.bindingTrackOutputs.put(i3, bindingTrackOutput);
        }
        return bindingTrackOutput;
    }
}
