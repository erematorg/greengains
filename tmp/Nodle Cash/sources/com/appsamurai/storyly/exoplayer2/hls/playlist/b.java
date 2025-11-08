package com.appsamurai.storyly.exoplayer2.hls.playlist;

import android.net.Uri;
import com.appsamurai.storyly.exoplayer2.hls.playlist.DefaultHlsPlaylistTracker;

public final /* synthetic */ class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DefaultHlsPlaylistTracker.MediaPlaylistBundle f4626a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Uri f4627b;

    public /* synthetic */ b(DefaultHlsPlaylistTracker.MediaPlaylistBundle mediaPlaylistBundle, Uri uri) {
        this.f4626a = mediaPlaylistBundle;
        this.f4627b = uri;
    }

    public final void run() {
        this.f4626a.lambda$loadPlaylistInternal$0(this.f4627b);
    }
}
