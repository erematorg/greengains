package com.google.android.material.textfield;

import android.view.View;

public final /* synthetic */ class a implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6691a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ EndIconDelegate f6692b;

    public /* synthetic */ a(EndIconDelegate endIconDelegate, int i3) {
        this.f6691a = i3;
        this.f6692b = endIconDelegate;
    }

    public final void onClick(View view) {
        int i3 = this.f6691a;
        EndIconDelegate endIconDelegate = this.f6692b;
        switch (i3) {
            case 0:
                ((ClearTextEndIconDelegate) endIconDelegate).lambda$new$0(view);
                return;
            case 1:
                ((DropdownMenuEndIconDelegate) endIconDelegate).lambda$new$0(view);
                return;
            default:
                ((PasswordToggleEndIconDelegate) endIconDelegate).lambda$new$0(view);
                return;
        }
    }
}
