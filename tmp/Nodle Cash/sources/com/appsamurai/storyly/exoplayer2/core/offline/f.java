package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.core.scheduler.RequirementsWatcher;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheWriter;

public final /* synthetic */ class f implements RequirementsWatcher.Listener, CacheWriter.ProgressListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f4527a;

    public /* synthetic */ f(Object obj) {
        this.f4527a = obj;
    }

    public void onProgress(long j2, long j3, long j4) {
        ((ProgressiveDownloader) this.f4527a).onProgress(j2, j3, j4);
    }

    public void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int i3) {
        ((DownloadManager) this.f4527a).onRequirementsStateChanged(requirementsWatcher, i3);
    }
}
