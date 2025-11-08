package com.appsamurai.storyly.exoplayer2.extractor.audio;

import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class WavUtil {
    public static final int DATA_FOURCC = 1684108385;
    public static final int DS64_FOURCC = 1685272116;
    public static final int FMT_FOURCC = 1718449184;
    public static final int RF64_FOURCC = 1380333108;
    public static final int RIFF_FOURCC = 1380533830;
    public static final int TYPE_ALAW = 6;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_IMA_ADPCM = 17;
    public static final int TYPE_MLAW = 7;
    public static final int TYPE_PCM = 1;
    public static final int TYPE_WAVE_FORMAT_EXTENSIBLE = 65534;
    public static final int WAVE_FOURCC = 1463899717;

    private WavUtil() {
    }

    public static int getPcmEncodingForType(int i3, int i4) {
        if (i3 != 1) {
            if (i3 == 3) {
                return i4 == 32 ? 4 : 0;
            }
            if (i3 != 65534) {
                return 0;
            }
        }
        return Util.getPcmEncoding(i4);
    }

    public static int getTypeForPcmEncoding(int i3) {
        if (i3 == 2 || i3 == 3) {
            return 1;
        }
        if (i3 == 4) {
            return 3;
        }
        if (i3 == 536870912 || i3 == 805306368) {
            return 1;
        }
        throw new IllegalArgumentException();
    }
}
