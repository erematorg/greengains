package com.google.firebase.crashlytics.internal.common;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class a implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7092a;

    public /* synthetic */ a(int i3) {
        this.f7092a = i3;
    }

    public final boolean accept(File file, String str) {
        switch (this.f7092a) {
            case 0:
                return str.startsWith(CrashlyticsAppQualitySessionsStore.AQS_SESSION_ID_FILENAME_PREFIX);
            default:
                return str.startsWith(CrashlyticsController.APP_EXCEPTION_MARKER_PREFIX);
        }
    }
}
