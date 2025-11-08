package com.google.android.material.search;

public final /* synthetic */ class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6672a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SearchView f6673b;

    public /* synthetic */ h(SearchView searchView, int i3) {
        this.f6672a = i3;
        this.f6673b = searchView;
    }

    public final void run() {
        int i3 = this.f6672a;
        SearchView searchView = this.f6673b;
        switch (i3) {
            case 0:
                searchView.lambda$clearFocusAndHideKeyboard$9();
                return;
            case 1:
                searchView.lambda$requestFocusAndShowKeyboard$8();
                return;
            case 2:
                searchView.show();
                return;
            default:
                searchView.requestFocusAndShowKeyboardIfNeeded();
                return;
        }
    }
}
