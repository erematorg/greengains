package com.tinder.scarlet.lifecycle;

import com.tinder.scarlet.Lifecycle;
import io.reactivex.schedulers.Timed;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class FlowableLifecycle$combineWith$flowable$2 extends FunctionReferenceImpl implements Function1<List<? extends Timed<Lifecycle.State>>, Lifecycle.State> {
    public static final FlowableLifecycle$combineWith$flowable$2 INSTANCE = new FlowableLifecycle$combineWith$flowable$2();

    public FlowableLifecycle$combineWith$flowable$2() {
        super(1, LifecycleStateUtilsKt.class, "combine", "combine(Ljava/util/List;)Lcom/tinder/scarlet/Lifecycle$State;", 1);
    }

    @NotNull
    public final Lifecycle.State invoke(@NotNull List<Timed<Lifecycle.State>> list) {
        Intrinsics.checkNotNullParameter(list, "p0");
        return LifecycleStateUtilsKt.combine(list);
    }
}
