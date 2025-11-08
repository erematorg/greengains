package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.core.offline.DownloadService;

public final /* synthetic */ class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4531a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4532b;

    public /* synthetic */ i(Object obj, int i3) {
        this.f4531a = i3;
        this.f4532b = obj;
    }

    public final void run() {
        int i3 = this.f4531a;
        Object obj = this.f4532b;
        switch (i3) {
            case 0:
                ((DownloadService.ForegroundNotificationUpdater) obj).update();
                return;
            default:
                ((DownloadHelper) obj).lambda$onMediaPrepared$4();
                return;
        }
    }
}
