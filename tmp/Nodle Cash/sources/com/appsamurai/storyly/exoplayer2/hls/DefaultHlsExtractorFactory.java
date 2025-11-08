package com.appsamurai.storyly.exoplayer2.hls;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.FileTypes;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Mp3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.Ac3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.Ac4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.AdtsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.DefaultTsPayloadReaderFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import com.google.common.primitives.Ints;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {8, 13, 11, 2, 0, 1, 7};
    private final boolean exposeCea608WhenMissingDeclarations;
    private final int payloadReaderFactoryFlags;

    public DefaultHlsExtractorFactory() {
        this(0, true);
    }

    private static void addFileTypeIfValidAndNotPresent(int i3, List<Integer> list) {
        if (Ints.indexOf(DEFAULT_EXTRACTOR_ORDER, i3) != -1 && !list.contains(Integer.valueOf(i3))) {
            list.add(Integer.valueOf(i3));
        }
    }

    @SuppressLint({"SwitchIntDef"})
    @Nullable
    private Extractor createExtractorByFileType(int i3, Format format, @Nullable List<Format> list, TimestampAdjuster timestampAdjuster) {
        if (i3 == 0) {
            return new Ac3Extractor();
        }
        if (i3 == 1) {
            return new Ac4Extractor();
        }
        if (i3 == 2) {
            return new AdtsExtractor();
        }
        if (i3 == 7) {
            return new Mp3Extractor(0, 0);
        }
        if (i3 == 8) {
            return createFragmentedMp4Extractor(timestampAdjuster, format, list);
        }
        if (i3 == 11) {
            return createTsExtractor(this.payloadReaderFactoryFlags, this.exposeCea608WhenMissingDeclarations, format, list, timestampAdjuster);
        }
        if (i3 != 13) {
            return null;
        }
        return new WebvttExtractor(format.language, timestampAdjuster);
    }

    private static FragmentedMp4Extractor createFragmentedMp4Extractor(TimestampAdjuster timestampAdjuster, Format format, @Nullable List<Format> list) {
        int i3 = isFmp4Variant(format) ? 4 : 0;
        if (list == null) {
            list = Collections.emptyList();
        }
        return new FragmentedMp4Extractor(i3, timestampAdjuster, (Track) null, list);
    }

    private static TsExtractor createTsExtractor(int i3, boolean z2, Format format, @Nullable List<Format> list, TimestampAdjuster timestampAdjuster) {
        int i4 = i3 | 16;
        if (list != null) {
            i4 = i3 | 48;
        } else {
            list = z2 ? Collections.singletonList(new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA608).build()) : Collections.emptyList();
        }
        String str = format.codecs;
        if (!TextUtils.isEmpty(str)) {
            if (!MimeTypes.containsCodecsCorrespondingToMimeType(str, MimeTypes.AUDIO_AAC)) {
                i4 |= 2;
            }
            if (!MimeTypes.containsCodecsCorrespondingToMimeType(str, MimeTypes.VIDEO_H264)) {
                i4 |= 4;
            }
        }
        return new TsExtractor(2, timestampAdjuster, (TsPayloadReader.Factory) new DefaultTsPayloadReaderFactory(i4, list));
    }

    private static boolean isFmp4Variant(Format format) {
        Metadata metadata = format.metadata;
        if (metadata == null) {
            return false;
        }
        for (int i3 = 0; i3 < metadata.length(); i3++) {
            Metadata.Entry entry = metadata.get(i3);
            if (entry instanceof HlsTrackMetadataEntry) {
                return !((HlsTrackMetadataEntry) entry).variantInfos.isEmpty();
            }
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    private static boolean sniffQuietly(Extractor extractor, ExtractorInput extractorInput) throws IOException {
        try {
            boolean sniff = extractor.sniff(extractorInput);
            extractorInput.resetPeekPosition();
            return sniff;
        } catch (EOFException unused) {
            extractorInput.resetPeekPosition();
            return false;
        } catch (Throwable th) {
            extractorInput.resetPeekPosition();
            throw th;
        }
    }

    public DefaultHlsExtractorFactory(int i3, boolean z2) {
        this.payloadReaderFactoryFlags = i3;
        this.exposeCea608WhenMissingDeclarations = z2;
    }

    public BundledHlsMediaChunkExtractor createExtractor(Uri uri, Format format, @Nullable List<Format> list, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput, PlayerId playerId) throws IOException {
        int inferFileTypeFromMimeType = FileTypes.inferFileTypeFromMimeType(format.sampleMimeType);
        int inferFileTypeFromResponseHeaders = FileTypes.inferFileTypeFromResponseHeaders(map);
        int inferFileTypeFromUri = FileTypes.inferFileTypeFromUri(uri);
        int[] iArr = DEFAULT_EXTRACTOR_ORDER;
        ArrayList arrayList = new ArrayList(iArr.length);
        addFileTypeIfValidAndNotPresent(inferFileTypeFromMimeType, arrayList);
        addFileTypeIfValidAndNotPresent(inferFileTypeFromResponseHeaders, arrayList);
        addFileTypeIfValidAndNotPresent(inferFileTypeFromUri, arrayList);
        for (int addFileTypeIfValidAndNotPresent : iArr) {
            addFileTypeIfValidAndNotPresent(addFileTypeIfValidAndNotPresent, arrayList);
        }
        extractorInput.resetPeekPosition();
        Extractor extractor = null;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int intValue = ((Integer) arrayList.get(i3)).intValue();
            Extractor extractor2 = (Extractor) Assertions.checkNotNull(createExtractorByFileType(intValue, format, list, timestampAdjuster));
            if (sniffQuietly(extractor2, extractorInput)) {
                return new BundledHlsMediaChunkExtractor(extractor2, format, timestampAdjuster);
            }
            if (extractor == null && (intValue == inferFileTypeFromMimeType || intValue == inferFileTypeFromResponseHeaders || intValue == inferFileTypeFromUri || intValue == 11)) {
                extractor = extractor2;
            }
        }
        return new BundledHlsMediaChunkExtractor((Extractor) Assertions.checkNotNull(extractor), format, timestampAdjuster);
    }
}
