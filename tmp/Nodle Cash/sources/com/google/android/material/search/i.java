package com.google.android.material.search;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

public final /* synthetic */ class i implements OnApplyWindowInsetsListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f6674a;

    public /* synthetic */ i(SearchView searchView) {
        this.f6674a = searchView;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return this.f6674a.lambda$setUpStatusBarSpacerInsetListener$5(view, windowInsetsCompat);
    }
}
