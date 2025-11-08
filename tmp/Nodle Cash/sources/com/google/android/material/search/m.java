package com.google.android.material.search;

public final /* synthetic */ class m implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6680a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6681b;

    public /* synthetic */ m(Object obj, int i3) {
        this.f6680a = i3;
        this.f6681b = obj;
    }

    public final void run() {
        int i3 = this.f6680a;
        Object obj = this.f6681b;
        switch (i3) {
            case 0:
                ((SearchViewAnimationHelper) obj).lambda$startShowAnimationExpand$0();
                return;
            case 1:
                ((SearchViewAnimationHelper) obj).lambda$startShowAnimationTranslate$1();
                return;
            default:
                ((SearchBar) obj).lambda$startOnLoadAnimation$1();
                return;
        }
    }
}
