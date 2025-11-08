package com.google.firebase.crashlytics;

import android.os.Bundle;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class a implements BreadcrumbSource, AnalyticsEventLogger, Deferred.DeferredHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AnalyticsDeferredProxy f7087a;

    public /* synthetic */ a(AnalyticsDeferredProxy analyticsDeferredProxy) {
        this.f7087a = analyticsDeferredProxy;
    }

    public void handle(Provider provider) {
        this.f7087a.lambda$init$2(provider);
    }

    public void logEvent(String str, Bundle bundle) {
        this.f7087a.lambda$getAnalyticsEventLogger$1(str, bundle);
    }

    public void registerBreadcrumbHandler(BreadcrumbHandler breadcrumbHandler) {
        this.f7087a.lambda$getDeferredBreadcrumbSource$0(breadcrumbHandler);
    }
}
