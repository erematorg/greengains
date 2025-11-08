package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;

public final /* synthetic */ class c implements BreadcrumbHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CrashlyticsCore f7094a;

    public /* synthetic */ c(CrashlyticsCore crashlyticsCore) {
        this.f7094a = crashlyticsCore;
    }

    public final void handleBreadcrumb(String str) {
        this.f7094a.log(str);
    }
}
