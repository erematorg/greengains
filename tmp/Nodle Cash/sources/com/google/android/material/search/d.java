package com.google.android.material.search;

import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchBarAnimationHelper;

public final /* synthetic */ class d implements SearchBarAnimationHelper.OnLoadAnimationInvocation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6662a;

    public /* synthetic */ d(int i3) {
        this.f6662a = i3;
    }

    public final void invoke(SearchBar.OnLoadAnimationCallback onLoadAnimationCallback) {
        switch (this.f6662a) {
            case 0:
                onLoadAnimationCallback.onAnimationStart();
                return;
            default:
                onLoadAnimationCallback.onAnimationEnd();
                return;
        }
    }
}
