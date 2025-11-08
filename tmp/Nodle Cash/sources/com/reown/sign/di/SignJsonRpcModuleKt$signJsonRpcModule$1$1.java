package com.reown.sign.di;

import com.reown.sign.common.adapters.SessionEventVOJsonAdapter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class SignJsonRpcModuleKt$signJsonRpcModule$1$1 extends FunctionReferenceImpl implements Function1<Moshi, SessionEventVOJsonAdapter> {
    public static final SignJsonRpcModuleKt$signJsonRpcModule$1$1 INSTANCE = new SignJsonRpcModuleKt$signJsonRpcModule$1$1();

    public SignJsonRpcModuleKt$signJsonRpcModule$1$1() {
        super(1, SessionEventVOJsonAdapter.class, "<init>", "<init>(Lcom/squareup/moshi/Moshi;)V", 0);
    }

    public final SessionEventVOJsonAdapter invoke(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "p0");
        return new SessionEventVOJsonAdapter(moshi);
    }
}
