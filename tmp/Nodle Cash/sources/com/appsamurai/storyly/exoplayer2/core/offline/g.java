package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.core.offline.DownloadManager;
import java.util.Comparator;

public final /* synthetic */ class g implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return DownloadManager.InternalHandler.compareStartTimes((Download) obj, (Download) obj2);
    }
}
