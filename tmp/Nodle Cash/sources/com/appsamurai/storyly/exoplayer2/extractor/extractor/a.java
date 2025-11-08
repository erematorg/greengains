package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.amr.AmrExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.flac.FlacExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.FlvExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Mp3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Mp4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg.OggExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.Ac3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.Ac4Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.AdtsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.PsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsExtractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.wav.WavExtractor;

public final /* synthetic */ class a implements ExtractorsFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4618a;

    public /* synthetic */ a(int i3) {
        this.f4618a = i3;
    }

    public final Extractor[] createExtractors() {
        switch (this.f4618a) {
            case 0:
                return ExtractorsFactory.lambda$static$0();
            case 1:
                return AmrExtractor.lambda$static$0();
            case 2:
                return FlacExtractor.lambda$static$0();
            case 3:
                return FlvExtractor.lambda$static$0();
            case 4:
                return MatroskaExtractor.lambda$static$0();
            case 5:
                return Mp3Extractor.lambda$static$0();
            case 6:
                return FragmentedMp4Extractor.lambda$static$0();
            case 7:
                return Mp4Extractor.lambda$static$0();
            case 8:
                return OggExtractor.lambda$static$0();
            case 9:
                return Ac3Extractor.lambda$static$0();
            case 10:
                return Ac4Extractor.lambda$static$0();
            case 11:
                return AdtsExtractor.lambda$static$0();
            case 12:
                return PsExtractor.lambda$static$0();
            case 13:
                return TsExtractor.lambda$static$0();
            default:
                return WavExtractor.lambda$static$0();
        }
    }
}
