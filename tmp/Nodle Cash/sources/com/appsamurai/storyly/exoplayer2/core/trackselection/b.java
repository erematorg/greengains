package com.appsamurai.storyly.exoplayer2.core.trackselection;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.google.common.base.Predicate;

public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DefaultTrackSelector f4583a;

    public /* synthetic */ b(DefaultTrackSelector defaultTrackSelector) {
        this.f4583a = defaultTrackSelector;
    }

    public final boolean apply(Object obj) {
        return this.f4583a.isAudioFormatWithinAudioChannelCountConstraints((Format) obj);
    }
}
