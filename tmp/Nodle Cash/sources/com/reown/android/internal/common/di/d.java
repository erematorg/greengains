package com.reown.android.internal.common.di;

import com.reown.foundation.di.FoundationCommonModuleKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.lang.reflect.Type;
import java.util.Set;

public final /* synthetic */ class d implements JsonAdapter.Factory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7305a;

    public /* synthetic */ d(int i3) {
        this.f7305a = i3;
    }

    public final JsonAdapter create(Type type, Set set, Moshi moshi) {
        switch (this.f7305a) {
            case 0:
                return CoreCommonModuleKt.coreCommonModule$lambda$5$lambda$2$lambda$1(type, set, moshi);
            default:
                return FoundationCommonModuleKt.foundationCommonModule$lambda$4$lambda$2$lambda$1(type, set, moshi);
        }
    }
}
