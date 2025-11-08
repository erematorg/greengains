package com.google.android.material.timepicker;

public final /* synthetic */ class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6703a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6704b;

    public /* synthetic */ b(Object obj, int i3) {
        this.f6703a = i3;
        this.f6704b = obj;
    }

    public final void run() {
        int i3 = this.f6703a;
        Object obj = this.f6704b;
        switch (i3) {
            case 0:
                ((RadialViewGroup) obj).updateLayoutParams();
                return;
            default:
                ((MaterialTimePicker) obj).lambda$onViewCreated$0();
                return;
        }
    }
}
