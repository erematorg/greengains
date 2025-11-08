package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil;
import java.util.Comparator;

public final /* synthetic */ class g implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaCodecUtil.ScoreProvider f4519a;

    public /* synthetic */ g(MediaCodecUtil.ScoreProvider scoreProvider) {
        this.f4519a = scoreProvider;
    }

    public final int compare(Object obj, Object obj2) {
        return MediaCodecUtil.lambda$sortByScore$3(this.f4519a, obj, obj2);
    }
}
