package com.appsamurai.storyly.exoplayer2.core.trackselection;

import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector;

public final /* synthetic */ class c implements Bundleable.Creator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4584a;

    public /* synthetic */ c(int i3) {
        this.f4584a = i3;
    }

    public final Bundleable fromBundle(Bundle bundle) {
        switch (this.f4584a) {
            case 0:
                return new DefaultTrackSelector.Parameters.Builder(bundle).build();
            default:
                return DefaultTrackSelector.SelectionOverride.lambda$static$0(bundle);
        }
    }
}
