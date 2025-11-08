package com.appsamurai.storyly.exoplayer2.core.offline;

import android.os.Handler;
import android.os.Message;
import com.appsamurai.storyly.exoplayer2.core.offline.DownloadHelper;

public final /* synthetic */ class e implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4525a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4526b;

    public /* synthetic */ e(Object obj, int i3) {
        this.f4525a = i3;
        this.f4526b = obj;
    }

    public final boolean handleMessage(Message message) {
        int i3 = this.f4525a;
        Object obj = this.f4526b;
        switch (i3) {
            case 0:
                return ((DownloadHelper.MediaPreparer) obj).handleDownloadHelperCallbackMessage(message);
            default:
                return ((DownloadManager) obj).handleMainMessage(message);
        }
    }
}
