package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.DeviceInfo;
import com.appsamurai.storyly.exoplayer2.common.MediaMetadata;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl;
import java.util.List;

public final /* synthetic */ class m implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4510a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4511b;

    public /* synthetic */ m(Object obj, int i3) {
        this.f4510a = i3;
        this.f4511b = obj;
    }

    public final void invoke(Object obj) {
        int i3 = this.f4510a;
        Object obj2 = this.f4511b;
        switch (i3) {
            case 0:
                ((Player.Listener) obj).onMediaMetadataChanged((MediaMetadata) obj2);
                return;
            case 1:
                ((Player.Listener) obj).onAudioAttributesChanged((AudioAttributes) obj2);
                return;
            case 2:
                ((Player.Listener) obj).onTrackSelectionParametersChanged((TrackSelectionParameters) obj2);
                return;
            case 3:
                ((Player.Listener) obj).onVideoSizeChanged((VideoSize) obj2);
                return;
            case 4:
                ((Player.Listener) obj).onCues((CueGroup) obj2);
                return;
            case 5:
                ((Player.Listener) obj).onCues((List<Cue>) (List) obj2);
                return;
            case 6:
                ((Player.Listener) obj).onDeviceInfoChanged((DeviceInfo) obj2);
                return;
            case 7:
                ((ExoPlayerImpl.ComponentListener) obj2).lambda$onMetadata$4((Player.Listener) obj);
                return;
            default:
                ((Player.Listener) obj).onMetadata((Metadata) obj2);
                return;
        }
    }
}
