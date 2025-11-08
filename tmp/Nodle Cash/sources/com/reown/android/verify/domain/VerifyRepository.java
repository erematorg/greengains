package com.reown.android.verify.domain;

import com.reown.android.internal.common.model.Validation;
import com.reown.android.internal.utils.Time;
import com.reown.android.verify.data.VerifyService;
import com.reown.android.verify.model.VerifyClaims;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H@¢\u0006\u0004\b\u0013\u0010\u0014JF\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00160\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00160\u001bJH\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00160\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00160\u001bH\u0002J\u0018\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0012H\u0002J\u000e\u0010$\u001a\u00020\u0012H@¢\u0006\u0002\u0010\u0014J>\u0010%\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00160\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00160\u001bJ!\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u00122\b\u0010)\u001a\u0004\u0018\u00010*H\u0002¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/reown/android/verify/domain/VerifyRepository;", "", "verifyService", "Lcom/reown/android/verify/data/VerifyService;", "jwtRepository", "Lcom/reown/android/verify/domain/JWTRepository;", "moshi", "Lcom/squareup/moshi/Moshi;", "verifyPublicKeyStorageRepository", "Lcom/reown/android/verify/domain/VerifyPublicKeyStorageRepository;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lcom/reown/android/verify/data/VerifyService;Lcom/reown/android/verify/domain/JWTRepository;Lcom/squareup/moshi/Moshi;Lcom/reown/android/verify/domain/VerifyPublicKeyStorageRepository;Lkotlinx/coroutines/CoroutineScope;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "getVerifyPublicKey", "Lkotlin/Result;", "", "getVerifyPublicKey-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveV2", "", "attestationId", "attestationJWT", "metadataUrl", "onSuccess", "Lkotlin/Function1;", "Lcom/reown/android/verify/domain/VerifyResult;", "onError", "", "checkIds", "claims", "Lcom/reown/android/verify/model/VerifyClaims;", "getValidation", "Lcom/reown/android/internal/common/model/Validation;", "fetchAndCacheKey", "resolve", "isLocalKeyValid", "", "localPublicKey", "expiresAt", "", "(Ljava/lang/String;Ljava/lang/Long;)Z", "isKeyExpired", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVerifyRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VerifyRepository.kt\ncom/reown/android/verify/domain/VerifyRepository\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,134:1\n116#2,11:135\n*S KotlinDebug\n*F\n+ 1 VerifyRepository.kt\ncom/reown/android/verify/domain/VerifyRepository\n*L\n25#1:135,11\n*E\n"})
public final class VerifyRepository {
    /* access modifiers changed from: private */
    @NotNull
    public final JWTRepository jwtRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final Moshi moshi;
    @NotNull
    private final Mutex mutex;
    @NotNull
    private final CoroutineScope scope;
    @NotNull
    private final VerifyPublicKeyStorageRepository verifyPublicKeyStorageRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyService verifyService;

    public VerifyRepository(@NotNull VerifyService verifyService2, @NotNull JWTRepository jWTRepository, @NotNull Moshi moshi2, @NotNull VerifyPublicKeyStorageRepository verifyPublicKeyStorageRepository2, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(verifyService2, "verifyService");
        Intrinsics.checkNotNullParameter(jWTRepository, "jwtRepository");
        Intrinsics.checkNotNullParameter(moshi2, "moshi");
        Intrinsics.checkNotNullParameter(verifyPublicKeyStorageRepository2, "verifyPublicKeyStorageRepository");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        this.verifyService = verifyService2;
        this.jwtRepository = jWTRepository;
        this.moshi = moshi2;
        this.verifyPublicKeyStorageRepository = verifyPublicKeyStorageRepository2;
        this.scope = coroutineScope;
        this.mutex = MutexKt.Mutex$default(false, 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void checkIds(String str, VerifyClaims verifyClaims, String str2, Function1<? super VerifyResult, Unit> function1, Function1<? super Throwable, Unit> function12) {
        if (!Intrinsics.areEqual((Object) str, (Object) verifyClaims.getId())) {
            resolve(str, str2, function1, function12);
        } else {
            function1.invoke(new VerifyResult(getValidation(verifyClaims, str2), verifyClaims.isScam(), verifyClaims.getOrigin()));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object fetchAndCacheKey(kotlin.coroutines.Continuation<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.reown.android.verify.domain.VerifyRepository$fetchAndCacheKey$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.reown.android.verify.domain.VerifyRepository$fetchAndCacheKey$1 r0 = (com.reown.android.verify.domain.VerifyRepository$fetchAndCacheKey$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.verify.domain.VerifyRepository$fetchAndCacheKey$1 r0 = new com.reown.android.verify.domain.VerifyRepository$fetchAndCacheKey$1
            r0.<init>(r4, r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x003f
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r5)
            com.reown.android.verify.data.VerifyService r5 = r4.verifyService
            r0.label = r3
            java.lang.Object r5 = r5.getPublicKey(r0)
            if (r5 != r1) goto L_0x003f
            return r1
        L_0x003f:
            retrofit2.Response r5 = (retrofit2.Response) r5
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto L_0x0073
            java.lang.Object r0 = r5.body()
            if (r0 == 0) goto L_0x0073
            com.reown.android.verify.domain.JWTRepository r0 = r4.jwtRepository
            java.lang.Object r1 = r5.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            com.reown.android.verify.model.VerifyServerPublicKey r1 = (com.reown.android.verify.model.VerifyServerPublicKey) r1
            com.reown.android.verify.model.JWK r1 = r1.getJwk()
            java.lang.String r0 = r0.generateP256PublicKeyFromJWK(r1)
            com.reown.android.verify.domain.VerifyPublicKeyStorageRepository r4 = r4.verifyPublicKeyStorageRepository
            java.lang.Object r5 = r5.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            com.reown.android.verify.model.VerifyServerPublicKey r5 = (com.reown.android.verify.model.VerifyServerPublicKey) r5
            long r1 = r5.getExpiresAt()
            r4.upsertPublicKey(r0, r1)
            return r0
        L_0x0073:
            java.lang.Exception r4 = new java.lang.Exception
            okhttp3.ResponseBody r5 = r5.errorBody()
            if (r5 == 0) goto L_0x0080
            java.lang.String r5 = r5.string()
            goto L_0x0081
        L_0x0080:
            r5 = 0
        L_0x0081:
            java.lang.String r0 = "Error while fetching a Verify PublicKey: "
            java.lang.String r5 = androidx.browser.trusted.c.a(r0, r5)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.verify.domain.VerifyRepository.fetchAndCacheKey(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Validation getValidation(VerifyClaims verifyClaims, String str) {
        return (!verifyClaims.isVerified() || Time.getCurrentTimeInSeconds() >= verifyClaims.getExpiration()) ? Validation.UNKNOWN : VerifyUtilsKt.getValidation(str, verifyClaims.getOrigin());
    }

    private final boolean isKeyExpired(long j2) {
        return Time.getCurrentTimeInSeconds() >= j2;
    }

    private final boolean isLocalKeyValid(String str, Long l2) {
        return (str == null || l2 == null || isKeyExpired(l2.longValue())) ? false : true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0081 A[Catch:{ all -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b0 A[SYNTHETIC, Splitter:B:33:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: getVerifyPublicKey-IoAF18A  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8846getVerifyPublicKeyIoAF18A(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.reown.android.verify.domain.VerifyRepository$getVerifyPublicKey$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.reown.android.verify.domain.VerifyRepository$getVerifyPublicKey$1 r0 = (com.reown.android.verify.domain.VerifyRepository$getVerifyPublicKey$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.verify.domain.VerifyRepository$getVerifyPublicKey$1 r0 = new com.reown.android.verify.domain.VerifyRepository$getVerifyPublicKey$1
            r0.<init>(r10, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            r6 = 0
            if (r2 == 0) goto L_0x0054
            if (r2 == r4) goto L_0x0049
            if (r2 != r3) goto L_0x0041
            java.lang.Object r10 = r0.L$3
            java.lang.Long r10 = (java.lang.Long) r10
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            com.reown.android.verify.domain.VerifyRepository r10 = (com.reown.android.verify.domain.VerifyRepository) r10
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.sync.Mutex r10 = (kotlinx.coroutines.sync.Mutex) r10
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x003e }
            goto L_0x00a7
        L_0x003e:
            r11 = move-exception
            goto L_0x00b9
        L_0x0041:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0049:
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r4
            goto L_0x0067
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.sync.Mutex r11 = r10.mutex
            r0.L$0 = r11
            r0.I$0 = r5
            r0.label = r4
            java.lang.Object r2 = r11.lock(r6, r0)
            if (r2 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r2 = r5
        L_0x0067:
            kotlin.Result$Companion r4 = kotlin.Result.Companion     // Catch:{ all -> 0x00ab }
            com.reown.android.verify.domain.VerifyPublicKeyStorageRepository r4 = r10.verifyPublicKeyStorageRepository     // Catch:{ all -> 0x00ab }
            kotlin.Pair r4 = r4.getPublicKey()     // Catch:{ all -> 0x00ab }
            java.lang.Object r7 = r4.component1()     // Catch:{ all -> 0x00ab }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x00ab }
            java.lang.Object r4 = r4.component2()     // Catch:{ all -> 0x00ab }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x00ab }
            boolean r8 = r10.isLocalKeyValid(r7, r4)     // Catch:{ all -> 0x00ab }
            if (r8 != 0) goto L_0x00b0
            r0.L$0 = r11     // Catch:{ all -> 0x00ab }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ all -> 0x00ab }
            r0.L$1 = r8     // Catch:{ all -> 0x00ab }
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ all -> 0x00ab }
            r0.L$2 = r7     // Catch:{ all -> 0x00ab }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x00ab }
            r0.L$3 = r4     // Catch:{ all -> 0x00ab }
            r0.I$0 = r2     // Catch:{ all -> 0x00ab }
            r0.I$1 = r5     // Catch:{ all -> 0x00ab }
            r0.I$2 = r5     // Catch:{ all -> 0x00ab }
            r0.label = r3     // Catch:{ all -> 0x00ab }
            java.lang.Object r10 = r10.fetchAndCacheKey(r0)     // Catch:{ all -> 0x00ab }
            if (r10 != r1) goto L_0x00a4
            return r1
        L_0x00a4:
            r9 = r11
            r11 = r10
            r10 = r9
        L_0x00a7:
            r7 = r11
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x003e }
            goto L_0x00b4
        L_0x00ab:
            r10 = move-exception
            r9 = r11
            r11 = r10
            r10 = r9
            goto L_0x00b9
        L_0x00b0:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ all -> 0x00ab }
            r10 = r11
        L_0x00b4:
            java.lang.Object r11 = kotlin.Result.m8979constructorimpl(r7)     // Catch:{ all -> 0x003e }
            goto L_0x00c3
        L_0x00b9:
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x00c7 }
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)     // Catch:{ all -> 0x00c7 }
            java.lang.Object r11 = kotlin.Result.m8979constructorimpl(r11)     // Catch:{ all -> 0x00c7 }
        L_0x00c3:
            r10.unlock(r6)
            return r11
        L_0x00c7:
            r11 = move-exception
            r10.unlock(r6)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.verify.domain.VerifyRepository.m8846getVerifyPublicKeyIoAF18A(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void resolve(@NotNull String str, @NotNull String str2, @NotNull Function1<? super VerifyResult, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(str2, "metadataUrl");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new VerifyRepository$resolve$1(this, str, function1, str2, function12, (Continuation<? super VerifyRepository$resolve$1>) null), 3, (Object) null);
    }

    public final void resolveV2(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Function1<? super VerifyResult, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(str2, "attestationJWT");
        Intrinsics.checkNotNullParameter(str3, "metadataUrl");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new VerifyRepository$resolveV2$1(this, str2, function12, str, str3, function1, (Continuation<? super VerifyRepository$resolveV2$1>) null), 3, (Object) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VerifyRepository(VerifyService verifyService2, JWTRepository jWTRepository, Moshi moshi2, VerifyPublicKeyStorageRepository verifyPublicKeyStorageRepository2, CoroutineScope coroutineScope, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(verifyService2, jWTRepository, moshi2, verifyPublicKeyStorageRepository2, (i3 & 16) != 0 ? CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getDefault())) : coroutineScope);
    }
}
