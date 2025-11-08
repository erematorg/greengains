package com.appsamurai.storyly.exoplayer2.hls.playlist;

import com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest;
import java.util.Collections;
import java.util.List;

public abstract class HlsPlaylist implements FilterableManifest<HlsPlaylist> {
    public final String baseUri;
    public final boolean hasIndependentSegments;
    public final List<String> tags;

    public HlsPlaylist(String str, List<String> list, boolean z2) {
        this.baseUri = str;
        this.tags = Collections.unmodifiableList(list);
        this.hasIndependentSegments = z2;
    }
}
