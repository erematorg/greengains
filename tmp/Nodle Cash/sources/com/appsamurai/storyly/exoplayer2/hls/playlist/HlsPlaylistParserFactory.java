package com.appsamurai.storyly.exoplayer2.hls.playlist;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.core.upstream.ParsingLoadable;

public interface HlsPlaylistParserFactory {
    ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser();

    ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(HlsMultivariantPlaylist hlsMultivariantPlaylist, @Nullable HlsMediaPlaylist hlsMediaPlaylist);
}
