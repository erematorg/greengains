package com.reown.android.verify.domain;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.domain.VerifyRepository$resolveV2$1", f = "VerifyRepository.kt", i = {}, l = {39}, m = "invokeSuspend", n = {}, s = {})
public final class VerifyRepository$resolveV2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $attestationId;
    final /* synthetic */ String $attestationJWT;
    final /* synthetic */ String $metadataUrl;
    final /* synthetic */ Function1<Throwable, Unit> $onError;
    final /* synthetic */ Function1<VerifyResult, Unit> $onSuccess;
    int label;
    final /* synthetic */ VerifyRepository this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.verify.domain.VerifyRepository$resolveV2$1$1", f = "VerifyRepository.kt", i = {1, 1}, l = {40, 56}, m = "invokeSuspend", n = {"key", "$i$a$-fold-VerifyRepository$resolveV2$1$1$1"}, s = {"L$5", "I$0"})
    /* renamed from: com.reown.android.verify.domain.VerifyRepository$resolveV2$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(verifyRepository, str, function1, str2, str3, function12, continuation);
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x00d6 A[Catch:{ Exception -> 0x002f }] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x010e A[Catch:{ Exception -> 0x002f }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                java.lang.String r2 = "Error while decoding JWT claims"
                java.lang.Class<com.reown.android.verify.model.VerifyClaims> r3 = com.reown.android.verify.model.VerifyClaims.class
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L_0x0044
                if (r1 == r5) goto L_0x003a
                if (r1 != r4) goto L_0x0032
                java.lang.Object r0 = r12.L$5
                java.lang.String r0 = (java.lang.String) r0
                java.lang.Object r0 = r12.L$4
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                java.lang.Object r1 = r12.L$3
                java.lang.String r1 = (java.lang.String) r1
                java.lang.Object r4 = r12.L$2
                kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
                java.lang.Object r5 = r12.L$1
                java.lang.String r5 = (java.lang.String) r5
                java.lang.Object r12 = r12.L$0
                com.reown.android.verify.domain.VerifyRepository r12 = (com.reown.android.verify.domain.VerifyRepository) r12
                kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x002f }
                goto L_0x00c6
            L_0x002f:
                r12 = move-exception
                goto L_0x011b
            L_0x0032:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r13)
                throw r12
            L_0x003a:
                kotlin.ResultKt.throwOnFailure(r13)
                kotlin.Result r13 = (kotlin.Result) r13
                java.lang.Object r13 = r13.m8988unboximpl()
                goto L_0x0052
            L_0x0044:
                kotlin.ResultKt.throwOnFailure(r13)
                com.reown.android.verify.domain.VerifyRepository r13 = r4
                r12.label = r5
                java.lang.Object r13 = r13.m8846getVerifyPublicKeyIoAF18A(r12)
                if (r13 != r0) goto L_0x0052
                return r0
            L_0x0052:
                com.reown.android.verify.domain.VerifyRepository r5 = r4
                java.lang.String r1 = r5
                kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r11 = r6
                java.lang.String r6 = r7
                java.lang.String r8 = r8
                kotlin.jvm.functions.Function1<com.reown.android.verify.domain.VerifyResult, kotlin.Unit> r9 = r9
                java.lang.Throwable r7 = kotlin.Result.m8982exceptionOrNullimpl(r13)
                if (r7 != 0) goto L_0x011f
                java.lang.String r13 = (java.lang.String) r13
                com.reown.android.verify.domain.JWTRepository r7 = r5.jwtRepository
                byte[] r10 = com.reown.util.UtilFunctionsKt.hexToBytes(r13)
                boolean r7 = r7.verifyJWT(r1, r10)
                if (r7 == 0) goto L_0x00a5
                com.squareup.moshi.Moshi r12 = r5.moshi     // Catch:{ Exception -> 0x0098 }
                com.squareup.moshi.JsonAdapter r12 = r12.adapter(r3)     // Catch:{ Exception -> 0x0098 }
                com.reown.android.verify.domain.JWTRepository r13 = r5.jwtRepository     // Catch:{ Exception -> 0x0098 }
                java.lang.String r13 = r13.decodeClaimsJWT(r1)     // Catch:{ Exception -> 0x0098 }
                java.lang.Object r12 = r12.fromJson((java.lang.String) r13)     // Catch:{ Exception -> 0x0098 }
                r7 = r12
                com.reown.android.verify.model.VerifyClaims r7 = (com.reown.android.verify.model.VerifyClaims) r7     // Catch:{ Exception -> 0x0098 }
                if (r7 != 0) goto L_0x009a
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0098 }
                r12.<init>(r2)     // Catch:{ Exception -> 0x0098 }
                r11.invoke(r12)     // Catch:{ Exception -> 0x0098 }
                kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0098 }
                return r12
            L_0x0098:
                r12 = move-exception
                goto L_0x00a0
            L_0x009a:
                r10 = r11
                r5.checkIds(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0098 }
                goto L_0x0122
            L_0x00a0:
                r11.invoke(r12)
                goto L_0x0122
            L_0x00a5:
                r12.L$0 = r5     // Catch:{ Exception -> 0x0119 }
                r12.L$1 = r1     // Catch:{ Exception -> 0x0119 }
                r12.L$2 = r11     // Catch:{ Exception -> 0x0119 }
                r12.L$3 = r8     // Catch:{ Exception -> 0x0119 }
                r12.L$4 = r9     // Catch:{ Exception -> 0x0119 }
                java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x0119 }
                r12.L$5 = r13     // Catch:{ Exception -> 0x0119 }
                r13 = 0
                r12.I$0 = r13     // Catch:{ Exception -> 0x0119 }
                r12.label = r4     // Catch:{ Exception -> 0x0119 }
                java.lang.Object r13 = r5.fetchAndCacheKey(r12)     // Catch:{ Exception -> 0x0119 }
                if (r13 != r0) goto L_0x00c1
                return r0
            L_0x00c1:
                r12 = r5
                r0 = r9
                r4 = r11
                r5 = r1
                r1 = r8
            L_0x00c6:
                java.lang.String r13 = (java.lang.String) r13     // Catch:{ Exception -> 0x002f }
                com.reown.android.verify.domain.JWTRepository r6 = r12.jwtRepository     // Catch:{ Exception -> 0x002f }
                byte[] r13 = com.reown.util.UtilFunctionsKt.hexToBytes(r13)     // Catch:{ Exception -> 0x002f }
                boolean r13 = r6.verifyJWT(r5, r13)     // Catch:{ Exception -> 0x002f }
                if (r13 == 0) goto L_0x010e
                com.squareup.moshi.Moshi r13 = r12.moshi     // Catch:{ Exception -> 0x002f }
                com.squareup.moshi.JsonAdapter r13 = r13.adapter(r3)     // Catch:{ Exception -> 0x002f }
                com.reown.android.verify.domain.JWTRepository r3 = r12.jwtRepository     // Catch:{ Exception -> 0x002f }
                java.lang.String r3 = r3.decodeClaimsJWT(r5)     // Catch:{ Exception -> 0x002f }
                java.lang.Object r13 = r13.fromJson((java.lang.String) r3)     // Catch:{ Exception -> 0x002f }
                com.reown.android.verify.model.VerifyClaims r13 = (com.reown.android.verify.model.VerifyClaims) r13     // Catch:{ Exception -> 0x002f }
                if (r13 != 0) goto L_0x00f9
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x002f }
                r12.<init>(r2)     // Catch:{ Exception -> 0x002f }
                r4.invoke(r12)     // Catch:{ Exception -> 0x002f }
                kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x002f }
                return r12
            L_0x00f9:
                com.reown.android.verify.domain.VerifyResult r2 = new com.reown.android.verify.domain.VerifyResult     // Catch:{ Exception -> 0x002f }
                com.reown.android.internal.common.model.Validation r12 = r12.getValidation(r13, r1)     // Catch:{ Exception -> 0x002f }
                java.lang.Boolean r1 = r13.isScam()     // Catch:{ Exception -> 0x002f }
                java.lang.String r13 = r13.getOrigin()     // Catch:{ Exception -> 0x002f }
                r2.<init>(r12, r1, r13)     // Catch:{ Exception -> 0x002f }
                r0.invoke(r2)     // Catch:{ Exception -> 0x002f }
                goto L_0x0122
            L_0x010e:
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x002f }
                java.lang.String r13 = "Error while verifying JWT"
                r12.<init>(r13)     // Catch:{ Exception -> 0x002f }
                r4.invoke(r12)     // Catch:{ Exception -> 0x002f }
                goto L_0x0122
            L_0x0119:
                r12 = move-exception
                r4 = r11
            L_0x011b:
                r4.invoke(r12)
                goto L_0x0122
            L_0x011f:
                r11.invoke(r7)
            L_0x0122:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.android.verify.domain.VerifyRepository$resolveV2$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyRepository$resolveV2$1(VerifyRepository verifyRepository, String str, Function1<? super Throwable, Unit> function1, String str2, String str3, Function1<? super VerifyResult, Unit> function12, Continuation<? super VerifyRepository$resolveV2$1> continuation) {
        super(2, continuation);
        this.this$0 = verifyRepository;
        this.$attestationJWT = str;
        this.$onError = function1;
        this.$attestationId = str2;
        this.$metadataUrl = str3;
        this.$onSuccess = function12;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VerifyRepository$resolveV2$1(this.this$0, this.$attestationJWT, this.$onError, this.$attestationId, this.$metadataUrl, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final VerifyRepository verifyRepository = this.this$0;
            final String str = this.$attestationJWT;
            final Function1<Throwable, Unit> function1 = this.$onError;
            final String str2 = this.$attestationId;
            final String str3 = this.$metadataUrl;
            final Function1<VerifyResult, Unit> function12 = this.$onSuccess;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VerifyRepository$resolveV2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
