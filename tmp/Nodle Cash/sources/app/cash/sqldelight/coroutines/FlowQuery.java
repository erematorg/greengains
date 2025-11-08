package app.cash.sqldelight.coroutines;

import app.cash.sqldelight.Query;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a-\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0002H\u0007¢\u0006\u0002\b\u0005\u001a4\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00070\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a.\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a.\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a;\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\r\u001a\u0002H\u00032\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u000e\u001a0\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\b\u001a\u00020\t¨\u0006\u0010"}, d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "Lapp/cash/sqldelight/Query;", "T", "", "toFlow", "mapToList", "", "context", "Lkotlin/coroutines/CoroutineContext;", "mapToOne", "mapToOneNotNull", "mapToOneOrDefault", "defaultValue", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/flow/Flow;", "mapToOneOrNull", "sqldelight-coroutines-extensions"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFlowExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowExtensions.kt\napp/cash/sqldelight/coroutines/FlowQuery\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,95:1\n49#2:96\n51#2:100\n49#2:101\n51#2:105\n49#2:106\n51#2:110\n56#2:111\n59#2:115\n49#2:116\n51#2:120\n46#3:97\n51#3:99\n46#3:102\n51#3:104\n46#3:107\n51#3:109\n46#3:112\n51#3:114\n46#3:117\n51#3:119\n105#4:98\n105#4:103\n105#4:108\n105#4:113\n105#4:118\n*S KotlinDebug\n*F\n+ 1 FlowExtensions.kt\napp/cash/sqldelight/coroutines/FlowQuery\n*L\n57#1:96\n57#1:100\n66#1:101\n66#1:105\n74#1:106\n74#1:110\n82#1:111\n82#1:115\n90#1:116\n90#1:120\n57#1:97\n57#1:99\n66#1:102\n66#1:104\n74#1:107\n74#1:109\n82#1:112\n82#1:114\n90#1:117\n90#1:119\n57#1:98\n66#1:103\n74#1:108\n82#1:113\n90#1:118\n*E\n"})
@JvmName(name = "FlowQuery")
public final class FlowQuery {
    @NotNull
    public static final <T> Flow<List<T>> mapToList(@NotNull Flow<? extends Query<? extends T>> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return new FlowQuery$mapToList$$inlined$map$1(flow, coroutineContext);
    }

    @NotNull
    public static final <T> Flow<T> mapToOne(@NotNull Flow<? extends Query<? extends T>> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return new FlowQuery$mapToOne$$inlined$map$1(flow, coroutineContext);
    }

    @NotNull
    public static final <T> Flow<T> mapToOneNotNull(@NotNull Flow<? extends Query<? extends T>> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return new FlowQuery$mapToOneNotNull$$inlined$mapNotNull$1(flow, coroutineContext);
    }

    @NotNull
    public static final <T> Flow<T> mapToOneOrDefault(@NotNull Flow<? extends Query<? extends T>> flow, @NotNull T t2, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(t2, "defaultValue");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return new FlowQuery$mapToOneOrDefault$$inlined$map$1(flow, coroutineContext, t2);
    }

    @NotNull
    public static final <T> Flow<T> mapToOneOrNull(@NotNull Flow<? extends Query<? extends T>> flow, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return new FlowQuery$mapToOneOrNull$$inlined$map$1(flow, coroutineContext);
    }

    @NotNull
    @JvmName(name = "toFlow")
    public static final <T> Flow<Query<T>> toFlow(@NotNull Query<? extends T> query) {
        Intrinsics.checkNotNullParameter(query, "<this>");
        return FlowKt.flow(new FlowQuery$asFlow$1(query, (Continuation<? super FlowQuery$asFlow$1>) null));
    }
}
