package com.google.android.material.datepicker;

public final /* synthetic */ class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DateFormatTextWatcher f6653a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f6654b;

    public /* synthetic */ b(DateFormatTextWatcher dateFormatTextWatcher, long j2) {
        this.f6653a = dateFormatTextWatcher;
        this.f6654b = j2;
    }

    public final void run() {
        this.f6653a.lambda$createRangeErrorCallback$1(this.f6654b);
    }
}
