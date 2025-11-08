package com.appsamurai.storyly.exoplayer2.extractor.video;

import androidx.annotation.Nullable;
import androidx.work.impl.a;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.reown.foundation.util.jwt.JwtUtilsKt;

public final class DolbyVisionConfig {
    public final String codecs;
    public final int level;
    public final int profile;

    private DolbyVisionConfig(int i3, int i4, String str) {
        this.profile = i3;
        this.level = i4;
        this.codecs = str;
    }

    @Nullable
    public static DolbyVisionConfig parse(ParsableByteArray parsableByteArray) {
        String str;
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i3 = readUnsignedByte >> 1;
        int readUnsignedByte2 = ((parsableByteArray.readUnsignedByte() >> 3) & 31) | ((readUnsignedByte & 1) << 5);
        if (i3 == 4 || i3 == 5 || i3 == 7) {
            str = "dvhe";
        } else if (i3 == 8) {
            str = "hev1";
        } else if (i3 != 9) {
            return null;
        } else {
            str = "avc3";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str2 = ".0";
        sb.append(str2);
        sb.append(i3);
        if (readUnsignedByte2 >= 10) {
            str2 = JwtUtilsKt.JWT_DELIMITER;
        }
        return new DolbyVisionConfig(i3, readUnsignedByte2, a.u(sb, str2, readUnsignedByte2));
    }
}
