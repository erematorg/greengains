package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.view.MenuItem;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.google.android.material.navigation.NavigationBarView;

public final /* synthetic */ class n implements ListenerSet.Event, NavigationBarView.OnItemSelectedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4436a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4437b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f4438c;

    public /* synthetic */ n(Object obj, boolean z2, int i3) {
        this.f4436a = i3;
        this.f4437b = obj;
        this.f4438c = z2;
    }

    public void invoke(Object obj) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        switch (this.f4436a) {
            case 0:
                analyticsListener.onIsPlayingChanged((AnalyticsListener.EventTime) this.f4437b, this.f4438c);
                return;
            case 1:
                DefaultAnalyticsCollector.lambda$onIsLoadingChanged$32((AnalyticsListener.EventTime) this.f4437b, this.f4438c, analyticsListener);
                return;
            case 2:
                analyticsListener.onSkipSilenceEnabledChanged((AnalyticsListener.EventTime) this.f4437b, this.f4438c);
                return;
            default:
                analyticsListener.onShuffleModeChanged((AnalyticsListener.EventTime) this.f4437b, this.f4438c);
                return;
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return NavigationUI.setupWithNavController$lambda$8((NavController) this.f4437b, this.f4438c, menuItem);
    }
}
