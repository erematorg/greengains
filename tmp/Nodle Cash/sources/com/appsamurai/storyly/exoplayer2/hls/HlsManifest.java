package com.appsamurai.storyly.exoplayer2.hls;

import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMasterPlaylist;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMediaPlaylist;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMultivariantPlaylist;

public final class HlsManifest {
    @Deprecated
    public final HlsMasterPlaylist masterPlaylist;
    public final HlsMediaPlaylist mediaPlaylist;
    public final HlsMultivariantPlaylist multivariantPlaylist;

    public HlsManifest(HlsMultivariantPlaylist hlsMultivariantPlaylist, HlsMediaPlaylist hlsMediaPlaylist) {
        this.multivariantPlaylist = hlsMultivariantPlaylist;
        this.mediaPlaylist = hlsMediaPlaylist;
        this.masterPlaylist = new HlsMasterPlaylist(hlsMultivariantPlaylist.baseUri, hlsMultivariantPlaylist.tags, hlsMultivariantPlaylist.variants, hlsMultivariantPlaylist.videos, hlsMultivariantPlaylist.audios, hlsMultivariantPlaylist.subtitles, hlsMultivariantPlaylist.closedCaptions, hlsMultivariantPlaylist.muxedAudioFormat, hlsMultivariantPlaylist.muxedCaptionFormats, hlsMultivariantPlaylist.hasIndependentSegments, hlsMultivariantPlaylist.variableDefinitions, hlsMultivariantPlaylist.sessionKeyDrmInitData);
    }
}
