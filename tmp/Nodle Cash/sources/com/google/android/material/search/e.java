package com.google.android.material.search;

import android.view.View;
import com.google.android.material.appbar.AppBarLayout;

public final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchBarAnimationHelper f6663a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SearchBar f6664b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ View f6665c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f6666d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ boolean f6667e;

    public /* synthetic */ e(SearchBarAnimationHelper searchBarAnimationHelper, SearchBar searchBar, View view, AppBarLayout appBarLayout, boolean z2) {
        this.f6663a = searchBarAnimationHelper;
        this.f6664b = searchBar;
        this.f6665c = view;
        this.f6666d = appBarLayout;
        this.f6667e = z2;
    }

    public final void run() {
        this.f6663a.lambda$startExpandAnimation$0(this.f6664b, this.f6665c, this.f6666d, this.f6667e);
    }
}
