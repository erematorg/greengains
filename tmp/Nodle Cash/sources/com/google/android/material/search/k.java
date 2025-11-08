package com.google.android.material.search;

import android.view.MotionEvent;
import android.view.View;

public final /* synthetic */ class k implements View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f6675a;

    public /* synthetic */ k(SearchView searchView) {
        this.f6675a = searchView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f6675a.lambda$setUpContentOnTouchListener$3(view, motionEvent);
    }
}
