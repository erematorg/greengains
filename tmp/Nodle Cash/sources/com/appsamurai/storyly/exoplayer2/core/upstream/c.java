package com.appsamurai.storyly.exoplayer2.core.upstream;

import com.appsamurai.storyly.exoplayer2.core.upstream.SlidingPercentile;
import java.util.Comparator;

public final /* synthetic */ class c implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4593a;

    public /* synthetic */ c(int i3) {
        this.f4593a = i3;
    }

    public final int compare(Object obj, Object obj2) {
        SlidingPercentile.Sample sample = (SlidingPercentile.Sample) obj;
        SlidingPercentile.Sample sample2 = (SlidingPercentile.Sample) obj2;
        switch (this.f4593a) {
            case 0:
                return SlidingPercentile.lambda$static$0(sample, sample2);
            default:
                return Float.compare(sample.value, sample2.value);
        }
    }
}
