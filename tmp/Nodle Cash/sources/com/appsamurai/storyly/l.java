package com.appsamurai.storyly;

import com.appsamurai.storyly.analytics.f;
import com.appsamurai.storyly.data.v;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElement;

public final class l extends Lambda implements Function1<JsonArrayBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyDataSource f4635a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<v> f4636b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(StorylyDataSource storylyDataSource, List<v> list) {
        super(1);
        this.f4635a = storylyDataSource;
        this.f4636b = list;
    }

    public Object invoke(Object obj) {
        JsonArrayBuilder jsonArrayBuilder = (JsonArrayBuilder) obj;
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "$this$putJsonArray");
        Iterator<JsonElement> it = f.a(this.f4635a == StorylyDataSource.MomentsAPI ? StoryGroupType.MomentsDefault : StoryGroupType.Default, this.f4636b).iterator();
        while (it.hasNext()) {
            jsonArrayBuilder.add(it.next());
        }
        return Unit.INSTANCE;
    }
}
