package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.core.offline.DownloadHelper;
import com.appsamurai.storyly.exoplayer2.core.offline.DownloadService;
import java.io.IOException;

public final /* synthetic */ class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4528a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4529b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4530c;

    public /* synthetic */ h(Object obj, Object obj2, int i3) {
        this.f4528a = i3;
        this.f4529b = obj;
        this.f4530c = obj2;
    }

    public final void run() {
        switch (this.f4528a) {
            case 0:
                ((DownloadService.DownloadManagerHelper) this.f4529b).lambda$attachService$0((DownloadService) this.f4530c);
                return;
            case 1:
                ((DownloadHelper) this.f4529b).lambda$prepare$3((DownloadHelper.Callback) this.f4530c);
                return;
            default:
                ((DownloadHelper) this.f4529b).lambda$onMediaPreparationFailed$5((IOException) this.f4530c);
                return;
        }
    }
}
