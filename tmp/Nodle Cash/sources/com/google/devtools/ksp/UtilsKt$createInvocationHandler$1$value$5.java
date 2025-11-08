package com.google.devtools.ksp;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Byte;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$createInvocationHandler$1$value$5 extends Lambda implements Function0<Byte> {
    final /* synthetic */ Object $result;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UtilsKt$createInvocationHandler$1$value$5(Object obj) {
        super(0);
        this.$result = obj;
    }

    @NotNull
    public final Byte invoke() {
        Object obj = this.$result;
        Intrinsics.checkNotNullExpressionValue(obj, "$result");
        return Byte.valueOf(UtilsKt.asByte(obj));
    }
}
