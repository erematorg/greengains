package com.appsamurai.storyly.exoplayer2.extractor.text.ttml;

import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class TtmlSubtitle implements Subtitle {
    private final long[] eventTimesUs;
    private final Map<String, TtmlStyle> globalStyles;
    private final Map<String, String> imageMap;
    private final Map<String, TtmlRegion> regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode ttmlNode, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, Map<String, String> map3) {
        this.root = ttmlNode;
        this.regionMap = map2;
        this.imageMap = map3;
        this.globalStyles = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.eventTimesUs = ttmlNode.getEventTimesUs();
    }

    public List<Cue> getCues(long j2) {
        return this.root.getCues(j2, this.globalStyles, this.regionMap, this.imageMap);
    }

    public long getEventTime(int i3) {
        return this.eventTimesUs[i3];
    }

    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    @VisibleForTesting
    public Map<String, TtmlStyle> getGlobalStyles() {
        return this.globalStyles;
    }

    public int getNextEventTimeIndex(long j2) {
        int binarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j2, false, false);
        if (binarySearchCeil < this.eventTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }

    @VisibleForTesting
    public TtmlNode getRoot() {
        return this.root;
    }
}
