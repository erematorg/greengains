package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultHttpDataSource;
import com.google.common.base.Predicate;
import java.util.Map;

public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4617a;

    public /* synthetic */ a(int i3) {
        this.f4617a = i3;
    }

    public final boolean apply(Object obj) {
        switch (this.f4617a) {
            case 0:
                return DefaultHttpDataSource.NullFilteringHeadersMap.lambda$keySet$0((String) obj);
            case 1:
                return DefaultHttpDataSource.NullFilteringHeadersMap.lambda$entrySet$1((Map.Entry) obj);
            default:
                return HttpDataSource.lambda$static$0((String) obj);
        }
    }
}
