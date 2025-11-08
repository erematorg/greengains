package com.google.common.collect;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import java.util.Collection;

public final /* synthetic */ class d implements Maps.EntryTransformer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Multimaps.TransformedEntriesMultimap f6881a;

    public /* synthetic */ d(Multimaps.TransformedEntriesMultimap transformedEntriesMultimap) {
        this.f6881a = transformedEntriesMultimap;
    }

    public final Object transformEntry(Object obj, Object obj2) {
        return this.f6881a.lambda$createAsMap$0(obj, (Collection) obj2);
    }
}
