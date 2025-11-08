package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil;

public final /* synthetic */ class h implements MediaCodecUtil.ScoreProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4520a;

    public /* synthetic */ h(int i3) {
        this.f4520a = i3;
    }

    public static void a(String str, String str2, String str3) {
        Log.w(str3, str + str2);
    }

    public int getScore(Object obj) {
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) obj;
        switch (this.f4520a) {
            case 0:
                return MediaCodecUtil.lambda$applyWorkarounds$1(mediaCodecInfo);
            default:
                return MediaCodecUtil.lambda$applyWorkarounds$2(mediaCodecInfo);
        }
    }
}
