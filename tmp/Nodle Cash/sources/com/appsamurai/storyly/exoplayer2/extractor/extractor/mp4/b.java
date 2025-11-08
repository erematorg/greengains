package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.net.MediaType;
import java.util.Collection;

public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4621a;

    public /* synthetic */ b(int i3) {
        this.f4621a = i3;
    }

    public final Object apply(Object obj) {
        switch (this.f4621a) {
            case 0:
                return Mp4Extractor.lambda$processMoovAtom$1((Track) obj);
            case 1:
                return MediaType.lambda$computeToString$0((String) obj);
            default:
                return ImmutableMultiset.copyOf((Collection) obj);
        }
    }
}
