package com.airbnb.lottie.compose;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"awaitOrNull", "Lcom/airbnb/lottie/LottieComposition;", "Lcom/airbnb/lottie/compose/LottieCompositionResult;", "(Lcom/airbnb/lottie/compose/LottieCompositionResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lottie-compose_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class LottieCompositionResultKt {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object awaitOrNull(@org.jetbrains.annotations.NotNull com.airbnb.lottie.compose.LottieCompositionResult r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.airbnb.lottie.LottieComposition> r5) {
        /*
            boolean r0 = r5 instanceof com.airbnb.lottie.compose.LottieCompositionResultKt$awaitOrNull$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.airbnb.lottie.compose.LottieCompositionResultKt$awaitOrNull$1 r0 = (com.airbnb.lottie.compose.LottieCompositionResultKt$awaitOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.airbnb.lottie.compose.LottieCompositionResultKt$awaitOrNull$1 r0 = new com.airbnb.lottie.compose.LottieCompositionResultKt$awaitOrNull$1
            r0.<init>(r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r5)     // Catch:{ all -> 0x0040 }
            goto L_0x003d
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.label = r3     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r4.await(r0)     // Catch:{ all -> 0x0040 }
            if (r5 != r1) goto L_0x003d
            return r1
        L_0x003d:
            com.airbnb.lottie.LottieComposition r5 = (com.airbnb.lottie.LottieComposition) r5     // Catch:{ all -> 0x0040 }
            goto L_0x0041
        L_0x0040:
            r5 = 0
        L_0x0041:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieCompositionResultKt.awaitOrNull(com.airbnb.lottie.compose.LottieCompositionResult, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
