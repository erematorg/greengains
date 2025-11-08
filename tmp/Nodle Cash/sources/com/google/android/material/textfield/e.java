package com.google.android.material.textfield;

import android.view.MotionEvent;
import android.view.View;

public final /* synthetic */ class e implements View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DropdownMenuEndIconDelegate f6699a;

    public /* synthetic */ e(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f6699a = dropdownMenuEndIconDelegate;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f6699a.lambda$setUpDropdownShowHideBehavior$4(view, motionEvent);
    }
}
