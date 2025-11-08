package com.airbnb.lottie.compose;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.airbnb.lottie.compose.RememberLottieCompositionKt$rememberLottieComposition$3", f = "rememberLottieComposition.kt", i = {0, 0, 1, 1}, l = {91, 93}, m = "invokeSuspend", n = {"exception", "failedCount", "exception", "failedCount"}, s = {"L$0", "I$0", "L$0", "I$0"})
public final class RememberLottieCompositionKt$rememberLottieComposition$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $fontAssetsFolder;
    final /* synthetic */ String $fontFileExtension;
    final /* synthetic */ String $imageAssetsFolder;
    final /* synthetic */ Function3<Integer, Throwable, Continuation<? super Boolean>, Object> $onRetry;
    final /* synthetic */ MutableState<LottieCompositionResultImpl> $result$delegate;
    final /* synthetic */ LottieCompositionSpec $spec;
    int I$0;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RememberLottieCompositionKt$rememberLottieComposition$3(Function3<? super Integer, ? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> function3, Context context, LottieCompositionSpec lottieCompositionSpec, String str, String str2, String str3, String str4, MutableState<LottieCompositionResultImpl> mutableState, Continuation<? super RememberLottieCompositionKt$rememberLottieComposition$3> continuation) {
        super(2, continuation);
        this.$onRetry = function3;
        this.$context = context;
        this.$spec = lottieCompositionSpec;
        this.$imageAssetsFolder = str;
        this.$fontAssetsFolder = str2;
        this.$fontFileExtension = str3;
        this.$cacheKey = str4;
        this.$result$delegate = mutableState;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new RememberLottieCompositionKt$rememberLottieComposition$3(this.$onRetry, this.$context, this.$spec, this.$imageAssetsFolder, this.$fontAssetsFolder, this.$fontFileExtension, this.$cacheKey, this.$result$delegate, continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        if (((java.lang.Boolean) r13).booleanValue() == false) goto L_0x0094;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002e
            if (r1 == r3) goto L_0x0024
            if (r1 != r2) goto L_0x001c
            int r1 = r12.I$0
            java.lang.Object r4 = r12.L$0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0018 }
            goto L_0x0086
        L_0x0018:
            r13 = move-exception
            r4 = r13
            goto L_0x0092
        L_0x001c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0024:
            int r1 = r12.I$0
            java.lang.Object r4 = r12.L$0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0058
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = 0
            r1 = 0
            r4 = r13
        L_0x0034:
            androidx.compose.runtime.MutableState<com.airbnb.lottie.compose.LottieCompositionResultImpl> r13 = r12.$result$delegate
            com.airbnb.lottie.compose.LottieCompositionResultImpl r13 = com.airbnb.lottie.compose.RememberLottieCompositionKt.rememberLottieComposition$lambda$1(r13)
            boolean r13 = r13.isSuccess()
            if (r13 != 0) goto L_0x0094
            if (r1 == 0) goto L_0x0060
            kotlin.jvm.functions.Function3<java.lang.Integer, java.lang.Throwable, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r13 = r12.$onRetry
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r12.L$0 = r4
            r12.I$0 = r1
            r12.label = r3
            java.lang.Object r13 = r13.invoke(r5, r4, r12)
            if (r13 != r0) goto L_0x0058
            return r0
        L_0x0058:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x0094
        L_0x0060:
            android.content.Context r5 = r12.$context     // Catch:{ all -> 0x0018 }
            com.airbnb.lottie.compose.LottieCompositionSpec r6 = r12.$spec     // Catch:{ all -> 0x0018 }
            java.lang.String r13 = r12.$imageAssetsFolder     // Catch:{ all -> 0x0018 }
            java.lang.String r7 = com.airbnb.lottie.compose.RememberLottieCompositionKt.ensureTrailingSlash(r13)     // Catch:{ all -> 0x0018 }
            java.lang.String r13 = r12.$fontAssetsFolder     // Catch:{ all -> 0x0018 }
            java.lang.String r8 = com.airbnb.lottie.compose.RememberLottieCompositionKt.ensureTrailingSlash(r13)     // Catch:{ all -> 0x0018 }
            java.lang.String r13 = r12.$fontFileExtension     // Catch:{ all -> 0x0018 }
            java.lang.String r9 = com.airbnb.lottie.compose.RememberLottieCompositionKt.ensureLeadingPeriod(r13)     // Catch:{ all -> 0x0018 }
            java.lang.String r10 = r12.$cacheKey     // Catch:{ all -> 0x0018 }
            r12.L$0 = r4     // Catch:{ all -> 0x0018 }
            r12.I$0 = r1     // Catch:{ all -> 0x0018 }
            r12.label = r2     // Catch:{ all -> 0x0018 }
            r11 = r12
            java.lang.Object r13 = com.airbnb.lottie.compose.RememberLottieCompositionKt.lottieComposition(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0018 }
            if (r13 != r0) goto L_0x0086
            return r0
        L_0x0086:
            com.airbnb.lottie.LottieComposition r13 = (com.airbnb.lottie.LottieComposition) r13     // Catch:{ all -> 0x0018 }
            androidx.compose.runtime.MutableState<com.airbnb.lottie.compose.LottieCompositionResultImpl> r5 = r12.$result$delegate     // Catch:{ all -> 0x0018 }
            com.airbnb.lottie.compose.LottieCompositionResultImpl r5 = com.airbnb.lottie.compose.RememberLottieCompositionKt.rememberLottieComposition$lambda$1(r5)     // Catch:{ all -> 0x0018 }
            r5.complete$lottie_compose_release(r13)     // Catch:{ all -> 0x0018 }
            goto L_0x0034
        L_0x0092:
            int r1 = r1 + r3
            goto L_0x0034
        L_0x0094:
            androidx.compose.runtime.MutableState<com.airbnb.lottie.compose.LottieCompositionResultImpl> r13 = r12.$result$delegate
            com.airbnb.lottie.compose.LottieCompositionResultImpl r13 = com.airbnb.lottie.compose.RememberLottieCompositionKt.rememberLottieComposition$lambda$1(r13)
            boolean r13 = r13.isComplete()
            if (r13 != 0) goto L_0x00ab
            if (r4 == 0) goto L_0x00ab
            androidx.compose.runtime.MutableState<com.airbnb.lottie.compose.LottieCompositionResultImpl> r12 = r12.$result$delegate
            com.airbnb.lottie.compose.LottieCompositionResultImpl r12 = com.airbnb.lottie.compose.RememberLottieCompositionKt.rememberLottieComposition$lambda$1(r12)
            r12.completeExceptionally$lottie_compose_release(r4)
        L_0x00ab:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.RememberLottieCompositionKt$rememberLottieComposition$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((RememberLottieCompositionKt$rememberLottieComposition$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
