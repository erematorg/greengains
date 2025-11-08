package com.google.android.material.textfield;

import android.view.View;

public final /* synthetic */ class b implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6693a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ EndIconDelegate f6694b;

    public /* synthetic */ b(EndIconDelegate endIconDelegate, int i3) {
        this.f6693a = i3;
        this.f6694b = endIconDelegate;
    }

    public final void onFocusChange(View view, boolean z2) {
        int i3 = this.f6693a;
        EndIconDelegate endIconDelegate = this.f6694b;
        switch (i3) {
            case 0:
                ((ClearTextEndIconDelegate) endIconDelegate).lambda$new$1(view, z2);
                return;
            default:
                ((DropdownMenuEndIconDelegate) endIconDelegate).lambda$new$1(view, z2);
                return;
        }
    }
}
