package com.google.android.material.textfield;

public final /* synthetic */ class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6697a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6698b;

    public /* synthetic */ d(Object obj, int i3) {
        this.f6697a = i3;
        this.f6698b = obj;
    }

    public final void run() {
        int i3 = this.f6697a;
        Object obj = this.f6698b;
        switch (i3) {
            case 0:
                ((ClearTextEndIconDelegate) obj).lambda$tearDown$2();
                return;
            case 1:
                ((DropdownMenuEndIconDelegate) obj).lambda$afterEditTextChanged$3();
                return;
            default:
                ((TextInputLayout) obj).lambda$onGlobalLayout$1();
                return;
        }
    }
}
