package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class b implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f7105a;

    public /* synthetic */ b(String str) {
        this.f7105a = str;
    }

    public final boolean accept(File file, String str) {
        return str.startsWith(this.f7105a);
    }
}
