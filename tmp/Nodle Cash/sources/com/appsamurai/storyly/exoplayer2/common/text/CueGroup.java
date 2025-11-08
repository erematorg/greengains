package com.appsamurai.storyly.exoplayer2.common.text;

import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.util.BundleableUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public final class CueGroup implements Bundleable {
    public static final Bundleable.Creator<CueGroup> CREATOR = new a(20);
    public static final CueGroup EMPTY = new CueGroup(ImmutableList.of());
    private static final int FIELD_CUES = 0;
    public final ImmutableList<Cue> cues;

    public CueGroup(List<Cue> list) {
        this.cues = ImmutableList.copyOf(list);
    }

    private static ImmutableList<Cue> filterOutBitmapCues(List<Cue> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (list.get(i3).bitmap == null) {
                builder.add((Object) list.get(i3));
            }
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static final CueGroup fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(0));
        return new CueGroup(parcelableArrayList == null ? ImmutableList.of() : BundleableUtil.fromBundleList(Cue.CREATOR, parcelableArrayList));
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(keyForField(0), BundleableUtil.toBundleArrayList(filterOutBitmapCues(this.cues)));
        return bundle;
    }
}
