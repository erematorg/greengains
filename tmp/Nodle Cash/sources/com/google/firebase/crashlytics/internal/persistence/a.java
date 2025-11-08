package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class a implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7104a;

    public /* synthetic */ a(int i3) {
        this.f7104a = i3;
    }

    public final boolean accept(File file, String str) {
        switch (this.f7104a) {
            case 0:
                return str.startsWith("event");
            default:
                return CrashlyticsReportPersistence.isNormalPriorityEventFile(file, str);
        }
    }
}
