package com.appsamurai.storyly.exoplayer2.core.text;

import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.dvb.DvbDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.pgs.PgsDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.subrip.SubripDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.tx3g.Tx3gDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.webvtt.Mp4WebvttDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.webvtt.WebvttDecoder;

public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() {
        public SubtitleDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            if (str != null) {
                char c3 = 65535;
                switch (str.hashCode()) {
                    case -1351681404:
                        if (str.equals(MimeTypes.APPLICATION_DVBSUBS)) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case -1248334819:
                        if (str.equals(MimeTypes.APPLICATION_PGS)) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case -1026075066:
                        if (str.equals(MimeTypes.APPLICATION_MP4VTT)) {
                            c3 = 2;
                            break;
                        }
                        break;
                    case -1004728940:
                        if (str.equals(MimeTypes.TEXT_VTT)) {
                            c3 = 3;
                            break;
                        }
                        break;
                    case 691401887:
                        if (str.equals(MimeTypes.APPLICATION_TX3G)) {
                            c3 = 4;
                            break;
                        }
                        break;
                    case 822864842:
                        if (str.equals(MimeTypes.TEXT_SSA)) {
                            c3 = 5;
                            break;
                        }
                        break;
                    case 930165504:
                        if (str.equals(MimeTypes.APPLICATION_MP4CEA608)) {
                            c3 = 6;
                            break;
                        }
                        break;
                    case 1201784583:
                        if (str.equals(MimeTypes.TEXT_EXOPLAYER_CUES)) {
                            c3 = 7;
                            break;
                        }
                        break;
                    case 1566015601:
                        if (str.equals(MimeTypes.APPLICATION_CEA608)) {
                            c3 = 8;
                            break;
                        }
                        break;
                    case 1566016562:
                        if (str.equals(MimeTypes.APPLICATION_CEA708)) {
                            c3 = 9;
                            break;
                        }
                        break;
                    case 1668750253:
                        if (str.equals(MimeTypes.APPLICATION_SUBRIP)) {
                            c3 = 10;
                            break;
                        }
                        break;
                    case 1693976202:
                        if (str.equals(MimeTypes.APPLICATION_TTML)) {
                            c3 = 11;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        return new DvbDecoder(format.initializationData);
                    case 1:
                        return new PgsDecoder();
                    case 2:
                        return new Mp4WebvttDecoder();
                    case 3:
                        return new WebvttDecoder();
                    case 4:
                        return new Tx3gDecoder(format.initializationData);
                    case 5:
                        return new SsaDecoder(format.initializationData);
                    case 6:
                    case 8:
                        return new Cea608Decoder(str, format.accessibilityChannel, Cea608Decoder.MIN_DATA_CHANNEL_TIMEOUT_MS);
                    case 7:
                        return new ExoplayerCuesDecoder();
                    case 9:
                        return new Cea708Decoder(format.accessibilityChannel, format.initializationData);
                    case 10:
                        return new SubripDecoder();
                    case 11:
                        return new TtmlDecoder();
                }
            }
            throw new IllegalArgumentException(c.a("Attempted to create decoder for unsupported MIME type: ", str));
        }

        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return MimeTypes.TEXT_VTT.equals(str) || MimeTypes.TEXT_SSA.equals(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_SUBRIP.equals(str) || MimeTypes.APPLICATION_TX3G.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_MP4CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_DVBSUBS.equals(str) || MimeTypes.APPLICATION_PGS.equals(str) || MimeTypes.TEXT_EXOPLAYER_CUES.equals(str);
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
