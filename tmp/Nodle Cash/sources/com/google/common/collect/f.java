package com.google.common.collect;

import com.google.common.collect.Table;
import java.util.Comparator;

public final /* synthetic */ class f implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Comparator f6883a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Comparator f6884b;

    public /* synthetic */ f(Comparator comparator, Comparator comparator2) {
        this.f6883a = comparator;
        this.f6884b = comparator2;
    }

    public final int compare(Object obj, Object obj2) {
        return RegularImmutableTable.lambda$forCells$0(this.f6883a, this.f6884b, (Table.Cell) obj, (Table.Cell) obj2);
    }
}
