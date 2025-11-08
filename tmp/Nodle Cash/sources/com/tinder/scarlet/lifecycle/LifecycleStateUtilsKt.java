package com.tinder.scarlet.lifecycle;

import com.tinder.scarlet.Lifecycle;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00030\u0002H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\u0002\u001a\f\u0010\b\u001a\u00020\u0005*\u00020\u0001H\u0002¨\u0006\t"}, d2 = {"combine", "Lcom/tinder/scarlet/Lifecycle$State;", "", "Lio/reactivex/schedulers/Timed;", "isEquivalentTo", "", "other", "isStopped", "isStoppedAndAborted", "scarlet"}, k = 2, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLifecycleStateUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LifecycleStateUtils.kt\ncom/tinder/scarlet/lifecycle/LifecycleStateUtilsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,33:1\n1747#2,3:34\n1747#2,3:37\n766#2:40\n857#2,2:41\n1045#2:43\n*S KotlinDebug\n*F\n+ 1 LifecycleStateUtils.kt\ncom/tinder/scarlet/lifecycle/LifecycleStateUtilsKt\n*L\n11#1:34,3\n16#1:37,3\n18#1:40\n18#1:41,2\n19#1:43\n*E\n"})
public final class LifecycleStateUtilsKt {
    @NotNull
    public static final Lifecycle.State combine(@NotNull List<Timed<Lifecycle.State>> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Iterable<Timed> iterable = list;
        boolean z2 = iterable instanceof Collection;
        if (!z2 || !((Collection) iterable).isEmpty()) {
            for (Timed value : iterable) {
                Object value2 = value.value();
                Intrinsics.checkNotNullExpressionValue(value2, "it.value()");
                if (isStoppedAndAborted((Lifecycle.State) value2)) {
                    return Lifecycle.State.Stopped.AndAborted.INSTANCE;
                }
            }
        }
        if (!z2 || !((Collection) iterable).isEmpty()) {
            for (Timed value3 : iterable) {
                Object value4 = value3.value();
                Intrinsics.checkNotNullExpressionValue(value4, "it.value()");
                if (isStopped((Lifecycle.State) value4)) {
                    ArrayList arrayList = new ArrayList();
                    for (Object next : iterable) {
                        Object value5 = ((Timed) next).value();
                        Intrinsics.checkNotNullExpressionValue(value5, "it.value()");
                        if (isStopped((Lifecycle.State) value5)) {
                            arrayList.add(next);
                        }
                    }
                    Object value6 = ((Timed) CollectionsKt.first(CollectionsKt.sortedWith(arrayList, new LifecycleStateUtilsKt$combine$$inlined$sortedBy$1()))).value();
                    Intrinsics.checkNotNullExpressionValue(value6, "filter { it.value().isSt…st()\n            .value()");
                    return (Lifecycle.State) value6;
                }
            }
        }
        return Lifecycle.State.Started.INSTANCE;
    }

    public static final boolean isEquivalentTo(@NotNull Lifecycle.State state, @NotNull Lifecycle.State state2) {
        Intrinsics.checkNotNullParameter(state, "<this>");
        Intrinsics.checkNotNullParameter(state2, "other");
        return Intrinsics.areEqual((Object) state, (Object) state2) || (isStopped(state) && isStopped(state2));
    }

    private static final boolean isStopped(Lifecycle.State state) {
        return state instanceof Lifecycle.State.Stopped;
    }

    private static final boolean isStoppedAndAborted(Lifecycle.State state) {
        return Intrinsics.areEqual((Object) state, (Object) Lifecycle.State.Stopped.AndAborted.INSTANCE);
    }
}
