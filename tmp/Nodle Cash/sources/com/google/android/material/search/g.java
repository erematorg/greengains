package com.google.android.material.search;

import android.view.View;

public final /* synthetic */ class g implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6670a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SearchView f6671b;

    public /* synthetic */ g(SearchView searchView, int i3) {
        this.f6670a = i3;
        this.f6671b = searchView;
    }

    public final void onClick(View view) {
        int i3 = this.f6670a;
        SearchView searchView = this.f6671b;
        switch (i3) {
            case 0:
                searchView.lambda$setUpBackButton$1(view);
                return;
            case 1:
                searchView.lambda$setUpClearButton$2(view);
                return;
            default:
                searchView.lambda$setupWithSearchBar$7(view);
                return;
        }
    }
}
