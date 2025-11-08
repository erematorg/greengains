package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/google/devtools/ksp/symbol/KSNode;", "<anonymous parameter 1>", "invoke", "(Lcom/google/devtools/ksp/symbol/KSNode;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$validate$1 extends Lambda implements Function2<KSNode, KSNode, Boolean> {
    public static final UtilsKt$validate$1 INSTANCE = new UtilsKt$validate$1();

    public UtilsKt$validate$1() {
        super(2);
    }

    @NotNull
    public final Boolean invoke(@Nullable KSNode kSNode, @NotNull KSNode kSNode2) {
        Intrinsics.checkNotNullParameter(kSNode2, "<anonymous parameter 1>");
        return Boolean.TRUE;
    }
}
