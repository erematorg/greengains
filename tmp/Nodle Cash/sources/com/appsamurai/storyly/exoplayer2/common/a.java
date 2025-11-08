package com.appsamurai.storyly.exoplayer2.common;

import android.net.Uri;
import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionOverride;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.FlagSet;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.common.video.ColorInfo;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.analytics.DefaultAnalyticsCollector;
import java.util.HashMap;

public final /* synthetic */ class a implements Bundleable.Creator, ListenerSet.IterationFinishedEvent {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4387a;

    public /* synthetic */ a(int i3) {
        this.f4387a = i3;
    }

    public static int a(int i3, int i4, int i5, int i6) {
        return ((i3 + i4) - i5) / i6;
    }

    public static String b(int i3, String str, String str2) {
        return str + str2 + i3;
    }

    public static void c(int i3, HashMap hashMap, String str, int i4, String str2) {
        hashMap.put(str, Integer.valueOf(i3));
        hashMap.put(str2, Integer.valueOf(i4));
    }

    public static void d(HashMap hashMap, String str, Integer num, int i3, String str2) {
        hashMap.put(str, num);
        hashMap.put(str2, Integer.valueOf(i3));
    }

    public Bundleable fromBundle(Bundle bundle) {
        switch (this.f4387a) {
            case 0:
                return new MediaItem.RequestMetadata.Builder().setMediaUri((Uri) bundle.getParcelable(MediaItem.RequestMetadata.keyForField(0))).setSearchQuery(bundle.getString(MediaItem.RequestMetadata.keyForField(1))).setExtras(bundle.getBundle(MediaItem.RequestMetadata.keyForField(2))).build();
            case 1:
                return MediaMetadata.fromBundle(bundle);
            case 2:
                return PercentageRating.fromBundle(bundle);
            case 3:
                return new PlaybackException(bundle);
            case 4:
                return PlaybackParameters.lambda$static$0(bundle);
            case 5:
                return Player.Commands.fromBundle(bundle);
            case 6:
                return Player.PositionInfo.fromBundle(bundle);
            case 7:
                return Rating.fromBundle(bundle);
            case 8:
                return StarRating.fromBundle(bundle);
            case 9:
                return ThumbRating.fromBundle(bundle);
            case 10:
                return Timeline.fromBundle(bundle);
            case 11:
                return Timeline.Period.fromBundle(bundle);
            case 12:
                return Timeline.Window.fromBundle(bundle);
            case 13:
                return Tracks.lambda$static$0(bundle);
            case 14:
                return TrackGroup.CREATOR.fromBundle((Bundle) Assertions.checkNotNull(bundle.getBundle(Tracks.Group.keyForField(0))));
            case 15:
                return AudioAttributes.lambda$static$0(bundle);
            case 16:
                return TrackGroup.lambda$static$0(bundle);
            case 17:
                return AdPlaybackState.fromBundle(bundle);
            case 18:
                return AdPlaybackState.AdGroup.fromBundle(bundle);
            case 19:
                return Cue.fromBundle(bundle);
            case 20:
                return CueGroup.fromBundle(bundle);
            case 21:
                return TrackSelectionOverride.lambda$static$0(bundle);
            case 22:
                return TrackSelectionParameters.fromBundle(bundle);
            case 27:
                return ColorInfo.lambda$static$0(bundle);
            default:
                return VideoSize.lambda$static$0(bundle);
        }
    }

    public void invoke(Object obj, FlagSet flagSet) {
        DefaultAnalyticsCollector.lambda$new$0((AnalyticsListener) obj, flagSet);
    }
}
