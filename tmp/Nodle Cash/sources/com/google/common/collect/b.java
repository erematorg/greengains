package com.google.common.collect;

import java.util.Comparator;
import java.util.Map;

public final /* synthetic */ class b implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6878a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Comparator f6879b;

    public /* synthetic */ b(Comparator comparator, int i3) {
        this.f6878a = i3;
        this.f6879b = comparator;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.f6878a) {
            case 0:
                return this.f6879b.compare(((PeekingIterator) obj).peek(), ((PeekingIterator) obj2).peek());
            default:
                return ImmutableSortedMap.lambda$fromEntries$0(this.f6879b, (Map.Entry) obj, (Map.Entry) obj2);
        }
    }
}
