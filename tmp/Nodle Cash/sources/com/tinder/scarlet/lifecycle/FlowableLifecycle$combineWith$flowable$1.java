package com.tinder.scarlet.lifecycle;

import com.tinder.scarlet.Lifecycle;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nFlowableLifecycle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowableLifecycle.kt\ncom/tinder/scarlet/lifecycle/FlowableLifecycle$combineWith$flowable$1\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,30:1\n11335#2:31\n11670#2,3:32\n*S KotlinDebug\n*F\n+ 1 FlowableLifecycle.kt\ncom/tinder/scarlet/lifecycle/FlowableLifecycle$combineWith$flowable$1\n*L\n25#1:31\n25#1:32,3\n*E\n"})
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002 \u0004*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u00010\u00012*\u0010\u0005\u001a&\u0012\f\u0012\n \u0004*\u0004\u0018\u00010\u00070\u0007 \u0004*\u0012\u0012\u000e\b\u0001\u0012\n \u0004*\u0004\u0018\u00010\u00070\u00070\u00060\u0006H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "Lio/reactivex/schedulers/Timed;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "it", "", "", "invoke", "([Ljava/lang/Object;)Ljava/util/List;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FlowableLifecycle$combineWith$flowable$1 extends Lambda implements Function1<Object[], List<? extends Timed<Lifecycle.State>>> {
    public static final FlowableLifecycle$combineWith$flowable$1 INSTANCE = new FlowableLifecycle$combineWith$flowable$1();

    public FlowableLifecycle$combineWith$flowable$1() {
        super(1);
    }

    public final List<Timed<Lifecycle.State>> invoke(@NotNull Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Timed timed : objArr) {
            Intrinsics.checkNotNull(timed, "null cannot be cast to non-null type io.reactivex.schedulers.Timed<com.tinder.scarlet.Lifecycle.State>");
            arrayList.add(timed);
        }
        return arrayList;
    }
}
