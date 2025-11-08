package com.appsamurai.storyly.exoplayer2.hls.playlist;

import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.hls.HlsDataSourceFactory;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsPlaylistTracker;

public final /* synthetic */ class a implements HlsPlaylistTracker.Factory {
    public final HlsPlaylistTracker createTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        return new DefaultHlsPlaylistTracker(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory);
    }
}
