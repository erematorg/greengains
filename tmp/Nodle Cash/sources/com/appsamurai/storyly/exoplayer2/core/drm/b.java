package com.appsamurai.storyly.exoplayer2.core.drm;

import com.appsamurai.storyly.exoplayer2.common.util.Consumer;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;

public final /* synthetic */ class b implements Consumer, DrmSessionManager.DrmSessionReference {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4484a;

    public /* synthetic */ b(int i3) {
        this.f4484a = i3;
    }

    public static String b(StringBuilder sb, String str, int i3) {
        sb.append(str.substring(i3));
        return sb.toString();
    }

    public static void c(String str, int i3, int i4, StringBuilder sb, String str2) {
        sb.append(str.substring(i3, i4));
        sb.append(str2);
    }

    public void accept(Object obj) {
        DrmSessionEventListener.EventDispatcher eventDispatcher = (DrmSessionEventListener.EventDispatcher) obj;
        switch (this.f4484a) {
            case 0:
                eventDispatcher.drmSessionAcquired(3);
                return;
            case 1:
                eventDispatcher.drmKeysRemoved();
                return;
            case 2:
                eventDispatcher.drmKeysLoaded();
                return;
            default:
                eventDispatcher.drmKeysRestored();
                return;
        }
    }

    public void release() {
        DrmSessionManager.DrmSessionReference.lambda$static$0();
    }
}
