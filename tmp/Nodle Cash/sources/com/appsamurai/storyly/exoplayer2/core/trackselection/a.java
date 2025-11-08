package com.appsamurai.storyly.exoplayer2.core.trackselection;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector;
import java.util.Comparator;
import java.util.List;

public final /* synthetic */ class a implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4582a;

    public /* synthetic */ a(int i3) {
        this.f4582a = i3;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.f4582a) {
            case 0:
                return DefaultTrackSelector.TextTrackInfo.compareSelections((List) obj, (List) obj2);
            case 1:
                return DefaultTrackSelector.AudioTrackInfo.compareSelections((List) obj, (List) obj2);
            case 2:
                return DefaultTrackSelector.VideoTrackInfo.compareSelections((List) obj, (List) obj2);
            case 3:
                return DefaultTrackSelector.VideoTrackInfo.compareNonQualityPreferences((DefaultTrackSelector.VideoTrackInfo) obj, (DefaultTrackSelector.VideoTrackInfo) obj2);
            case 4:
                return DefaultTrackSelector.VideoTrackInfo.compareQualityPreferences((DefaultTrackSelector.VideoTrackInfo) obj, (DefaultTrackSelector.VideoTrackInfo) obj2);
            case 5:
                return BaseTrackSelection.lambda$new$0((Format) obj, (Format) obj2);
            case 6:
                return DefaultTrackSelector.lambda$static$0((Integer) obj, (Integer) obj2);
            default:
                return DefaultTrackSelector.lambda$static$1((Integer) obj, (Integer) obj2);
        }
    }
}
