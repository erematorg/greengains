package com.reown.android.keyserver.domain;

import androidx.camera.camera2.internal.C0118y;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.exception.UserRejectedSigning;
import com.reown.android.internal.common.jwt.did.DidJwtRepository;
import com.reown.android.internal.common.jwt.did.EncodeDidJwtPayloadUseCase;
import com.reown.android.internal.common.jwt.did.EncodeIdentityKeyDidJwtPayloadUseCase;
import com.reown.android.internal.common.model.ProjectId;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.common.signing.cacao.CacaoKt;
import com.reown.android.internal.common.signing.cacao.CacaoType;
import com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository;
import com.reown.android.internal.utils.ContextKt;
import com.reown.android.keyserver.domain.use_case.RegisterIdentityUseCase;
import com.reown.android.keyserver.domain.use_case.ResolveIdentityUseCase;
import com.reown.android.keyserver.domain.use_case.UnregisterIdentityUseCase;
import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.util.Logger;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.util.UtilFunctionsKt;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 d2\u00020\u0001:\u0001dB?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\\\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 2\u0006\u0010!\u001a\u00020\u001d2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010$0#H@¢\u0006\u0004\b%\u0010&J.\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020'0\u001b2\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020$H@¢\u0006\u0004\b,\u0010-JB\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 H@¢\u0006\u0004\b/\u00100JT\u00101\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010$0#H@¢\u0006\u0004\b2\u00103J\\\u00104\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 2\u0006\u0010!\u001a\u00020\u001d2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010$0#H@¢\u0006\u0004\b5\u0010&J&\u00106\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001dH@¢\u0006\u0004\b7\u00108J\u001e\u00109\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\u0006\u0010:\u001a\u00020\u001dH@¢\u0006\u0004\b;\u0010<J\u001e\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\u0006\u0010>\u001a\u00020\u001dH@¢\u0006\u0004\b?\u0010<J\u001e\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\u0006\u0010:\u001a\u00020\u001dH@¢\u0006\u0004\bA\u0010<J\u001e\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b2\u0006\u0010:\u001a\u00020\u001dH@¢\u0006\u0004\bC\u0010<J\u0017\u0010D\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\bE\u0010FJ\u001f\u0010G\u001a\u00020'2\u0006\u0010H\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\bI\u0010JJ\u001f\u0010K\u001a\u00020'2\u0006\u0010H\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\bL\u0010JJ\r\u0010M\u001a\u00020\u0014¢\u0006\u0004\bN\u0010OJ\\\u0010P\u001a\b\u0012\u0004\u0012\u00020'0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010$0#H@¢\u0006\u0004\bQ\u0010RJ:\u0010S\u001a\b\u0012\u0004\u0012\u00020'0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001d2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H@¢\u0006\u0004\bU\u0010VJ[\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010$0#H\u0002¢\u0006\u0004\bY\u0010ZJ9\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001d2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0002¢\u0006\u0004\b]\u0010^JE\u0010_\u001a\b\u0012\u0004\u0012\u00020*0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010 ¢\u0006\u0004\b`\u0010aJ\u0018\u0010b\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Lcom/reown/android/keyserver/domain/IdentitiesInteractor;", "", "identitiesRepository", "Lcom/reown/android/internal/common/storage/identity/IdentitiesStorageRepository;", "resolveIdentityUseCase", "Lcom/reown/android/keyserver/domain/use_case/ResolveIdentityUseCase;", "registerIdentityUseCase", "Lcom/reown/android/keyserver/domain/use_case/RegisterIdentityUseCase;", "unregisterIdentityUseCase", "Lcom/reown/android/keyserver/domain/use_case/UnregisterIdentityUseCase;", "projectId", "Lcom/reown/android/internal/common/model/ProjectId;", "keyManagementRepository", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/storage/identity/IdentitiesStorageRepository;Lcom/reown/android/keyserver/domain/use_case/ResolveIdentityUseCase;Lcom/reown/android/keyserver/domain/use_case/RegisterIdentityUseCase;Lcom/reown/android/keyserver/domain/use_case/UnregisterIdentityUseCase;Lcom/reown/android/internal/common/model/ProjectId;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/foundation/util/Logger;)V", "getIdentityKeyPair", "Lkotlin/Pair;", "Lcom/reown/foundation/common/model/PublicKey;", "Lcom/reown/foundation/common/model/PrivateKey;", "accountId", "Lcom/reown/android/internal/common/model/AccountId;", "getIdentityKeyPair-PaLCHi0", "(Ljava/lang/String;)Lkotlin/Pair;", "registerIdentity", "Lkotlin/Result;", "statement", "", "domain", "resources", "", "keyserverUrl", "onSign", "Lkotlin/Function1;", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;", "registerIdentity-xVnHb-c", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "identityPublicKey", "payload", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "signature", "registerIdentity-fEM-vcc", "(Ljava/lang/String;Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlreadyRegisteredValidIdentity", "getAlreadyRegisteredValidIdentity-cBM7WSQ", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateAndStoreNewIdentity", "generateAndStoreNewIdentity-21kkFcg", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleIdentitiesOutdatedStatements", "handleIdentitiesOutdatedStatements-xVnHb-c", "unregisterIdentity", "unregisterIdentity-K-ifgIg", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveIdentity", "identityKey", "resolveIdentity-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveIdentityDidKey", "identityDidKey", "resolveIdentityDidKey-gIAlu-s", "resolveIdentityLocally", "resolveIdentityLocally-gIAlu-s", "resolveAndStoreIdentityRemotely", "resolveAndStoreIdentityRemotely-gIAlu-s", "getIdentityPublicKey", "getIdentityPublicKey-wy9PYZM", "(Ljava/lang/String;)Ljava/lang/String;", "storeIdentityPublicKey", "publicKey", "storeIdentityPublicKey-jnUdMTY", "(Ljava/lang/String;Ljava/lang/String;)V", "removeIdentityKeyPair", "removeIdentityKeyPair-jnUdMTY", "generateAndStoreIdentityKeyPair", "generateAndStoreIdentityKeyPair-uN_RPug", "()Ljava/lang/String;", "registerIdentityKeyInKeyserver", "registerIdentityKeyInKeyserver-uJKfU3c", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterIdentityKeyInKeyserver", "identityKeyPair", "unregisterIdentityKeyInKeyserver-slDN04U", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/Pair;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateAndSignCacao", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "generateAndSignCacao-MDnJniU", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "generateUnregisterIdAuth", "Lcom/reown/android/internal/common/model/DidJwt;", "generateUnregisterIdAuth-K-ifgIg", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/Pair;)Ljava/lang/Object;", "generatePayload", "generatePayload-2YvqGik", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Ljava/lang/Object;", "buildUri", "didKey", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIdentitiesInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IdentitiesInteractor.kt\ncom/reown/android/keyserver/domain/IdentitiesInteractor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,195:1\n1#2:196\n*E\n"})
public final class IdentitiesInteractor {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int NONCE_SIZE = 32;
    @NotNull
    private final IdentitiesStorageRepository identitiesRepository;
    @NotNull
    private final KeyManagementRepository keyManagementRepository;
    @NotNull
    private final Logger logger;
    @NotNull
    private final ProjectId projectId;
    @NotNull
    private final RegisterIdentityUseCase registerIdentityUseCase;
    @NotNull
    private final ResolveIdentityUseCase resolveIdentityUseCase;
    @NotNull
    private final UnregisterIdentityUseCase unregisterIdentityUseCase;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/reown/android/keyserver/domain/IdentitiesInteractor$Companion;", "", "<init>", "()V", "NONCE_SIZE", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public IdentitiesInteractor(@NotNull IdentitiesStorageRepository identitiesStorageRepository, @NotNull ResolveIdentityUseCase resolveIdentityUseCase2, @NotNull RegisterIdentityUseCase registerIdentityUseCase2, @NotNull UnregisterIdentityUseCase unregisterIdentityUseCase2, @NotNull ProjectId projectId2, @NotNull KeyManagementRepository keyManagementRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(identitiesStorageRepository, "identitiesRepository");
        Intrinsics.checkNotNullParameter(resolveIdentityUseCase2, "resolveIdentityUseCase");
        Intrinsics.checkNotNullParameter(registerIdentityUseCase2, "registerIdentityUseCase");
        Intrinsics.checkNotNullParameter(unregisterIdentityUseCase2, "unregisterIdentityUseCase");
        Intrinsics.checkNotNullParameter(projectId2, "projectId");
        Intrinsics.checkNotNullParameter(keyManagementRepository2, "keyManagementRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.identitiesRepository = identitiesStorageRepository;
        this.resolveIdentityUseCase = resolveIdentityUseCase2;
        this.registerIdentityUseCase = registerIdentityUseCase2;
        this.unregisterIdentityUseCase = unregisterIdentityUseCase2;
        this.projectId = projectId2;
        this.keyManagementRepository = keyManagementRepository2;
        this.logger = logger2;
    }

    private final String buildUri(String str, String str2) {
        return C0118y.f("bundleid://", str, "?walletconnect_identity_key=", str2);
    }

    /* renamed from: generateAndSignCacao-MDnJniU  reason: not valid java name */
    private final Object m8816generateAndSignCacaoMDnJniU(String str, String str2, String str3, String str4, List<String> list, Function1<? super String, Cacao.Signature> function1) {
        Object r02 = m8830generatePayload2YvqGik(str, str2, str3, str4, list);
        ResultKt.throwOnFailure(r02);
        Cacao.Payload payload = (Cacao.Payload) r02;
        Cacao.Signature invoke = function1.invoke(CacaoKt.toCAIP222Message$default(payload, (String) null, 1, (Object) null));
        if (invoke != null) {
            Result.Companion companion = Result.Companion;
            return Result.m8979constructorimpl(new Cacao(CacaoType.EIP4361.toHeader(), payload, invoke));
        }
        throw new UserRejectedSigning();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* renamed from: generateAndStoreNewIdentity-21kkFcg  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8817generateAndStoreNewIdentity21kkFcg(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.util.List<java.lang.String> r13, kotlin.jvm.functions.Function1<? super java.lang.String, com.reown.android.internal.common.signing.cacao.Cacao.Signature> r14, kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.foundation.common.model.PublicKey>> r15) {
        /*
            r9 = this;
            boolean r0 = r15 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$generateAndStoreNewIdentity$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            com.reown.android.keyserver.domain.IdentitiesInteractor$generateAndStoreNewIdentity$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$generateAndStoreNewIdentity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.label = r1
        L_0x0012:
            r8 = r0
            goto L_0x001a
        L_0x0014:
            com.reown.android.keyserver.domain.IdentitiesInteractor$generateAndStoreNewIdentity$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$generateAndStoreNewIdentity$1
            r0.<init>(r9, r15)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r15 = r8.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x0051
            if (r1 != r2) goto L_0x0049
            java.lang.Object r10 = r8.L$5
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r8.L$4
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r11 = r8.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r11 = r8.L$2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r8.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r8.L$0
            java.lang.String r11 = (java.lang.String) r11
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.Result r15 = (kotlin.Result) r15
            java.lang.Object r12 = r15.m8988unboximpl()
            goto L_0x0086
        L_0x0049:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.String r15 = r9.m8829generateAndStoreIdentityKeyPairuN_RPug()
            r8.L$0 = r10
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r8.L$1 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r8.L$2 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r8.L$3 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r8.L$4 = r1
            r8.L$5 = r15
            r8.label = r2
            r1 = r9
            r2 = r10
            r3 = r15
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            java.lang.Object r12 = r1.m8822registerIdentityKeyInKeyserveruJKfU3c(r2, r3, r4, r5, r6, r7, r8)
            if (r12 != r0) goto L_0x0084
            return r0
        L_0x0084:
            r11 = r10
            r10 = r15
        L_0x0086:
            boolean r13 = kotlin.Result.m8986isSuccessimpl(r12)
            if (r13 == 0) goto L_0x0092
            kotlin.Unit r12 = (kotlin.Unit) r12
            com.reown.foundation.common.model.PublicKey r12 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r10)
        L_0x0092:
            java.lang.Object r12 = kotlin.Result.m8979constructorimpl(r12)
            boolean r13 = kotlin.Result.m8986isSuccessimpl(r12)
            if (r13 == 0) goto L_0x00a5
            r13 = r12
            com.reown.foundation.common.model.PublicKey r13 = (com.reown.foundation.common.model.PublicKey) r13
            r13.m8862unboximpl()
            r9.m8827storeIdentityPublicKeyjnUdMTY(r10, r11)
        L_0x00a5:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8817generateAndStoreNewIdentity21kkFcg(java.lang.String, java.lang.String, java.lang.String, java.util.List, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: generateUnregisterIdAuth-K-ifgIg  reason: not valid java name */
    private final Object m8818generateUnregisterIdAuthKifgIg(String str, String str2, Pair<PublicKey, PrivateKey> pair) {
        return DidJwtRepository.m8738encodeDidJwt57yAOYI(pair.component2().m8854unboximpl(), new EncodeIdentityKeyDidJwtPayloadUseCase(str, (DefaultConstructorMarker) null), new EncodeDidJwtPayloadUseCase.Params(pair.component1().m8862unboximpl(), str2, 0, (TimeUnit) null, 12, (DefaultConstructorMarker) null));
    }

    /* renamed from: getAlreadyRegisteredValidIdentity-cBM7WSQ$default  reason: not valid java name */
    public static /* synthetic */ Object m8819getAlreadyRegisteredValidIdentitycBM7WSQ$default(IdentitiesInteractor identitiesInteractor, String str, String str2, String str3, List list, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str2 = null;
        }
        return identitiesInteractor.m8831getAlreadyRegisteredValidIdentitycBM7WSQ(str, str2, str3, list, continuation);
    }

    /* renamed from: getIdentityPublicKey-wy9PYZM  reason: not valid java name */
    private final String m8820getIdentityPublicKeywy9PYZM(String str) {
        return this.keyManagementRepository.m8728getPublicKeyp9DwDrs(ContextKt.m8807getIdentityTagPaLCHi0(str));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* renamed from: handleIdentitiesOutdatedStatements-xVnHb-c  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8821handleIdentitiesOutdatedStatementsxVnHbc(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.util.List<java.lang.String> r21, java.lang.String r22, kotlin.jvm.functions.Function1<? super java.lang.String, com.reown.android.internal.common.signing.cacao.Cacao.Signature> r23, kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.foundation.common.model.PublicKey>> r24) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r24
            boolean r3 = r2 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$handleIdentitiesOutdatedStatements$1
            if (r3 == 0) goto L_0x0019
            r3 = r2
            com.reown.android.keyserver.domain.IdentitiesInteractor$handleIdentitiesOutdatedStatements$1 r3 = (com.reown.android.keyserver.domain.IdentitiesInteractor$handleIdentitiesOutdatedStatements$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x0019
            int r4 = r4 - r5
            r3.label = r4
            goto L_0x001e
        L_0x0019:
            com.reown.android.keyserver.domain.IdentitiesInteractor$handleIdentitiesOutdatedStatements$1 r3 = new com.reown.android.keyserver.domain.IdentitiesInteractor$handleIdentitiesOutdatedStatements$1
            r3.<init>(r0, r2)
        L_0x001e:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 2
            r7 = 1
            if (r5 == 0) goto L_0x0099
            if (r5 == r7) goto L_0x0066
            if (r5 != r6) goto L_0x005e
            java.lang.Object r0 = r3.L$9
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r3.L$8
            java.lang.Object r1 = r3.L$7
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r3.L$6
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r3.L$5
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            java.lang.Object r1 = r3.L$4
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r3.L$3
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r3.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r3.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r3.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r2)
            kotlin.Result r2 = (kotlin.Result) r2
            r2.m8988unboximpl()
            goto L_0x0149
        L_0x005e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0066:
            java.lang.Object r1 = r3.L$7
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r5 = r3.L$6
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r7 = r3.L$5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r3.L$4
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r3.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r3.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r3.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r3.L$0
            java.lang.String r12 = (java.lang.String) r12
            kotlin.ResultKt.throwOnFailure(r2)
            kotlin.Result r2 = (kotlin.Result) r2
            java.lang.Object r2 = r2.m8988unboximpl()
            r15 = r11
            r11 = r7
            r7 = r8
            r8 = r15
            r16 = r10
            r10 = r9
            r9 = r16
            goto L_0x00d9
        L_0x0099:
            kotlin.ResultKt.throwOnFailure(r2)
            kotlin.Pair r5 = r17.m8832getIdentityKeyPairPaLCHi0(r18)
            java.lang.Object r2 = r5.component1()
            com.reown.foundation.common.model.PublicKey r2 = (com.reown.foundation.common.model.PublicKey) r2
            java.lang.String r2 = r2.m8862unboximpl()
            r3.L$0 = r1
            r8 = r19
            r3.L$1 = r8
            r9 = r20
            r3.L$2 = r9
            r10 = r21
            r3.L$3 = r10
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)
            r3.L$4 = r11
            r11 = r23
            r3.L$5 = r11
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r3.L$6 = r12
            r3.L$7 = r2
            r3.label = r7
            r7 = r22
            java.lang.Object r12 = r0.m8828unregisterIdentityKeyInKeyserverslDN04U(r1, r7, r5, r3)
            if (r12 != r4) goto L_0x00d5
            return r4
        L_0x00d5:
            r15 = r12
            r12 = r1
            r1 = r2
            r2 = r15
        L_0x00d9:
            boolean r13 = kotlin.Result.m8986isSuccessimpl(r2)
            if (r13 == 0) goto L_0x00e5
            kotlin.Unit r2 = (kotlin.Unit) r2
            com.reown.foundation.common.model.PublicKey r2 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r1)
        L_0x00e5:
            java.lang.Object r2 = kotlin.Result.m8979constructorimpl(r2)
            boolean r13 = kotlin.Result.m8986isSuccessimpl(r2)
            if (r13 == 0) goto L_0x014a
            r13 = r2
            com.reown.foundation.common.model.PublicKey r13 = (com.reown.foundation.common.model.PublicKey) r13
            java.lang.String r13 = r13.m8862unboximpl()
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r3.L$0 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r3.L$1 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r3.L$2 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r3.L$3 = r14
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r3.L$4 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r3.L$5 = r7
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r3.L$6 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r3.L$7 = r5
            r3.L$8 = r2
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r3.L$9 = r5
            r5 = 0
            r3.I$0 = r5
            r3.label = r6
            r18 = r12
            r19 = r1
            r20 = r8
            r21 = r9
            r22 = r10
            r23 = r11
            r24 = r3
            java.lang.Object r0 = r17.m8822registerIdentityKeyInKeyserveruJKfU3c(r18, r19, r20, r21, r22, r23, r24)
            if (r0 != r4) goto L_0x0148
            return r4
        L_0x0148:
            r0 = r2
        L_0x0149:
            r2 = r0
        L_0x014a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8821handleIdentitiesOutdatedStatementsxVnHbc(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: kotlin.jvm.functions.Function1<? super java.lang.String, com.reown.android.internal.common.signing.cacao.Cacao$Signature>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.util.List<java.lang.String>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: registerIdentityKeyInKeyserver-uJKfU3c  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8822registerIdentityKeyInKeyserveruJKfU3c(java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.util.List<java.lang.String> r13, kotlin.jvm.functions.Function1<? super java.lang.String, com.reown.android.internal.common.signing.cacao.Cacao.Signature> r14, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r15) {
        /*
            r8 = this;
            boolean r0 = r15 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentityKeyInKeyserver$1
            if (r0 == 0) goto L_0x0013
            r0 = r15
            com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentityKeyInKeyserver$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentityKeyInKeyserver$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentityKeyInKeyserver$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentityKeyInKeyserver$1
            r0.<init>(r8, r15)
        L_0x0018:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0084
            if (r2 == r4) goto L_0x0057
            if (r2 != r3) goto L_0x004f
            java.lang.Object r8 = r0.L$8
            kotlin.Unit r8 = (kotlin.Unit) r8
            java.lang.Object r8 = r0.L$7
            java.lang.Object r9 = r0.L$6
            com.reown.android.internal.common.signing.cacao.Cacao r9 = (com.reown.android.internal.common.signing.cacao.Cacao) r9
            java.lang.Object r9 = r0.L$5
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            java.lang.Object r9 = r0.L$4
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = r0.L$3
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.L$2
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0110
        L_0x004f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0057:
            java.lang.Object r9 = r0.L$6
            com.reown.android.internal.common.signing.cacao.Cacao r9 = (com.reown.android.internal.common.signing.cacao.Cacao) r9
            java.lang.Object r10 = r0.L$5
            r14 = r10
            kotlin.jvm.functions.Function1 r14 = (kotlin.jvm.functions.Function1) r14
            java.lang.Object r10 = r0.L$4
            r13 = r10
            java.util.List r13 = (java.util.List) r13
            java.lang.Object r10 = r0.L$3
            r12 = r10
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r10 = r0.L$2
            r11 = r10
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r10 = r0.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r2 = r0.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.Result r15 = (kotlin.Result) r15
            java.lang.Object r15 = r15.m8988unboximpl()
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x00be
        L_0x0084:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r8.m8816generateAndSignCacaoMDnJniU(r9, r10, r11, r12, r13, r14)
            kotlin.ResultKt.throwOnFailure(r15)
            com.reown.android.internal.common.signing.cacao.Cacao r15 = (com.reown.android.internal.common.signing.cacao.Cacao) r15
            com.reown.android.keyserver.domain.use_case.RegisterIdentityUseCase r2 = r8.registerIdentityUseCase
            r0.L$0 = r9
            r0.L$1 = r10
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r0.L$2 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$3 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r0.L$4 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r0.L$5 = r5
            r0.L$6 = r15
            r0.label = r4
            java.lang.Object r2 = r2.m8837invokegIAlus(r15, r0)
            if (r2 != r1) goto L_0x00b9
            return r1
        L_0x00b9:
            r7 = r2
            r2 = r9
            r9 = r10
            r10 = r15
            r15 = r7
        L_0x00be:
            boolean r4 = kotlin.Result.m8986isSuccessimpl(r15)
            if (r4 == 0) goto L_0x0111
            r4 = r15
            kotlin.Unit r4 = (kotlin.Unit) r4
            com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository r8 = r8.identitiesRepository
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r5 = r10.getPayload()
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$1 = r6
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r0.L$2 = r11
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$3 = r11
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r0.L$4 = r11
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r0.L$5 = r11
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r0.L$6 = r10
            r0.L$7 = r15
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.L$8 = r10
            r10 = 0
            r0.I$0 = r10
            r0.label = r3
            r12 = 1
            r10 = r2
            r11 = r5
            r13 = r0
            java.lang.Object r8 = r8.m8806insertIdentityRiN_R_Q(r9, r10, r11, r12, r13)
            if (r8 != r1) goto L_0x010f
            return r1
        L_0x010f:
            r8 = r15
        L_0x0110:
            r15 = r8
        L_0x0111:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8822registerIdentityKeyInKeyserveruJKfU3c(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: removeIdentityKeyPair-jnUdMTY  reason: not valid java name */
    private final void m8823removeIdentityKeyPairjnUdMTY(String str, String str2) {
        Object obj;
        Object obj2;
        try {
            Result.Companion companion = Result.Companion;
            this.keyManagementRepository.removeKeys(ContextKt.m8807getIdentityTagPaLCHi0(str2));
            obj = Result.m8979constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r3 = Result.m8982exceptionOrNullimpl(obj);
        if (r3 != null) {
            this.logger.error(r3);
        }
        try {
            this.keyManagementRepository.removeKeys(str);
            obj2 = Result.m8979constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable r2 = Result.m8982exceptionOrNullimpl(obj2);
        if (r2 != null) {
            this.logger.error(r2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a A[SYNTHETIC, Splitter:B:23:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    /* renamed from: resolveAndStoreIdentityRemotely-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8824resolveAndStoreIdentityRemotelygIAlus(java.lang.String r9, kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.android.internal.common.model.AccountId>> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$resolveAndStoreIdentityRemotely$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveAndStoreIdentityRemotely$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$resolveAndStoreIdentityRemotely$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.label = r1
        L_0x0012:
            r6 = r0
            goto L_0x001a
        L_0x0014:
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveAndStoreIdentityRemotely$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$resolveAndStoreIdentityRemotely$1
            r0.<init>(r8, r10)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r10 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0054
            if (r1 == r3) goto L_0x0046
            if (r1 != r2) goto L_0x003e
            java.lang.Object r8 = r6.L$2
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r6.L$1
            com.reown.android.keyserver.model.KeyServerResponse$ResolveIdentity r9 = (com.reown.android.keyserver.model.KeyServerResponse.ResolveIdentity) r9
            java.lang.Object r9 = r6.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x003b }
            goto L_0x00b9
        L_0x003b:
            r8 = move-exception
            goto L_0x00c8
        L_0x003e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0046:
            java.lang.Object r9 = r6.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.Result r10 = (kotlin.Result) r10
            java.lang.Object r10 = r10.m8988unboximpl()
            goto L_0x0064
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r10)
            com.reown.android.keyserver.domain.use_case.ResolveIdentityUseCase r10 = r8.resolveIdentityUseCase
            r6.L$0 = r9
            r6.label = r3
            java.lang.Object r10 = r10.m8839invokegIAlus(r9, r6)
            if (r10 != r0) goto L_0x0064
            return r0
        L_0x0064:
            boolean r1 = kotlin.Result.m8986isSuccessimpl(r10)
            if (r1 == 0) goto L_0x00d3
            com.reown.android.keyserver.model.KeyServerResponse$ResolveIdentity r10 = (com.reown.android.keyserver.model.KeyServerResponse.ResolveIdentity) r10     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.signing.cacao.CacaoVerifier r1 = new com.reown.android.internal.common.signing.cacao.CacaoVerifier     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.model.ProjectId r3 = r8.projectId     // Catch:{ all -> 0x003b }
            r1.<init>(r3)     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.signing.cacao.Cacao r3 = r10.getCacao()     // Catch:{ all -> 0x003b }
            boolean r1 = r1.verify(r3)     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x00c2
            com.reown.android.internal.common.signing.cacao.Cacao r1 = r10.getCacao()     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r1 = r1.getPayload()     // Catch:{ all -> 0x003b }
            java.lang.String r1 = r1.getIss()     // Catch:{ all -> 0x003b }
            java.lang.String r1 = com.reown.foundation.util.jwt.JwtUtilsKt.decodeDidPkh(r1)     // Catch:{ all -> 0x003b }
            java.lang.String r7 = com.reown.android.internal.common.model.AccountId.m8750constructorimpl(r1)     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository r1 = r8.identitiesRepository     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.signing.cacao.Cacao r8 = r10.getCacao()     // Catch:{ all -> 0x003b }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r4 = r8.getPayload()     // Catch:{ all -> 0x003b }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ all -> 0x003b }
            r6.L$0 = r8     // Catch:{ all -> 0x003b }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ all -> 0x003b }
            r6.L$1 = r8     // Catch:{ all -> 0x003b }
            r6.L$2 = r7     // Catch:{ all -> 0x003b }
            r8 = 0
            r6.I$0 = r8     // Catch:{ all -> 0x003b }
            r6.label = r2     // Catch:{ all -> 0x003b }
            r5 = 0
            r2 = r9
            r3 = r7
            java.lang.Object r8 = r1.m8806insertIdentityRiN_R_Q(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x003b }
            if (r8 != r0) goto L_0x00b8
            return r0
        L_0x00b8:
            r8 = r7
        L_0x00b9:
            com.reown.android.internal.common.model.AccountId r8 = com.reown.android.internal.common.model.AccountId.m8749boximpl(r8)     // Catch:{ all -> 0x003b }
            java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)     // Catch:{ all -> 0x003b }
            goto L_0x00d7
        L_0x00c2:
            com.reown.android.internal.common.exception.InvalidIdentityCacao r8 = new com.reown.android.internal.common.exception.InvalidIdentityCacao     // Catch:{ all -> 0x003b }
            r8.<init>()     // Catch:{ all -> 0x003b }
            throw r8     // Catch:{ all -> 0x003b }
        L_0x00c8:
            kotlin.Result$Companion r9 = kotlin.Result.Companion
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r8)
            goto L_0x00d7
        L_0x00d3:
            java.lang.Object r8 = kotlin.Result.m8979constructorimpl(r10)
        L_0x00d7:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8824resolveAndStoreIdentityRemotelygIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: resolveIdentity-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8825resolveIdentitygIAlus(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.android.internal.common.model.AccountId>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentity$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentity$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentity$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentity$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 == r4) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r5 = r0.L$1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Result r7 = (kotlin.Result) r7
            java.lang.Object r5 = r7.m8988unboximpl()
            goto L_0x007d
        L_0x003a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0042:
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Result r7 = (kotlin.Result) r7
            java.lang.Object r7 = r7.m8988unboximpl()
            goto L_0x005e
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r5.m8826resolveIdentityLocallygIAlus(r6, r0)
            if (r7 != r1) goto L_0x005e
            return r1
        L_0x005e:
            java.lang.Throwable r2 = kotlin.Result.m8982exceptionOrNullimpl(r7)
            if (r2 != 0) goto L_0x0065
            goto L_0x008e
        L_0x0065:
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$1 = r7
            r7 = 0
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r5 = r5.m8824resolveAndStoreIdentityRemotelygIAlus(r6, r0)
            if (r5 != r1) goto L_0x007d
            return r1
        L_0x007d:
            kotlin.ResultKt.throwOnFailure(r5)
            com.reown.android.internal.common.model.AccountId r5 = (com.reown.android.internal.common.model.AccountId) r5
            java.lang.String r5 = r5.m8756unboximpl()
            com.reown.android.internal.common.model.AccountId r5 = com.reown.android.internal.common.model.AccountId.m8749boximpl(r5)
            java.lang.Object r7 = kotlin.Result.m8979constructorimpl(r5)
        L_0x008e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8825resolveIdentitygIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* renamed from: resolveIdentityLocally-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8826resolveIdentityLocallygIAlus(java.lang.String r5, kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.android.internal.common.model.AccountId>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityLocally$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityLocally$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityLocally$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityLocally$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityLocally$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r4 = r0.L$1
            com.reown.android.keyserver.domain.IdentitiesInteractor r4 = (com.reown.android.keyserver.domain.IdentitiesInteractor) r4
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0037 }
            com.reown.android.internal.common.model.AccountId r6 = (com.reown.android.internal.common.model.AccountId) r6     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = r6.m8756unboximpl()     // Catch:{ all -> 0x0037 }
            goto L_0x0060
        L_0x0037:
            r4 = move-exception
            goto L_0x006b
        L_0x0039:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0037 }
            com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository r6 = r4.identitiesRepository     // Catch:{ all -> 0x0037 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ all -> 0x0037 }
            r0.L$0 = r2     // Catch:{ all -> 0x0037 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0037 }
            r0.L$1 = r4     // Catch:{ all -> 0x0037 }
            r4 = 0
            r0.I$0 = r4     // Catch:{ all -> 0x0037 }
            r0.label = r3     // Catch:{ all -> 0x0037 }
            java.lang.Object r4 = r6.m8805getAccountIdyrRQGmQ(r5, r0)     // Catch:{ all -> 0x0037 }
            if (r4 != r1) goto L_0x0060
            return r1
        L_0x0060:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0037 }
            com.reown.android.internal.common.model.AccountId r4 = com.reown.android.internal.common.model.AccountId.m8749boximpl(r4)     // Catch:{ all -> 0x0037 }
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)     // Catch:{ all -> 0x0037 }
            goto L_0x0075
        L_0x006b:
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
        L_0x0075:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8826resolveIdentityLocallygIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: storeIdentityPublicKey-jnUdMTY  reason: not valid java name */
    private final void m8827storeIdentityPublicKeyjnUdMTY(String str, String str2) {
        this.keyManagementRepository.setKey(PublicKey.m8855boximpl(str), ContextKt.m8807getIdentityTagPaLCHi0(str2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: kotlin.Pair<com.reown.foundation.common.model.PublicKey, com.reown.foundation.common.model.PrivateKey>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: unregisterIdentityKeyInKeyserver-slDN04U  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8828unregisterIdentityKeyInKeyserverslDN04U(java.lang.String r7, java.lang.String r8, kotlin.Pair<com.reown.foundation.common.model.PublicKey, com.reown.foundation.common.model.PrivateKey> r9, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1
            r0.<init>(r6, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x005f
            if (r2 == r4) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r6 = r0.L$4
            kotlin.Unit r6 = (kotlin.Unit) r6
            java.lang.Object r6 = r0.L$3
            java.lang.Object r7 = r0.L$2
            kotlin.Pair r7 = (kotlin.Pair) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00c4
        L_0x003f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0047:
            java.lang.Object r7 = r0.L$2
            r9 = r7
            kotlin.Pair r9 = (kotlin.Pair) r9
            java.lang.Object r7 = r0.L$1
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.Result r10 = (kotlin.Result) r10
            java.lang.Object r10 = r10.m8988unboximpl()
            goto L_0x0088
        L_0x005f:
            kotlin.ResultKt.throwOnFailure(r10)
            com.reown.android.keyserver.domain.use_case.UnregisterIdentityUseCase r10 = r6.unregisterIdentityUseCase
            java.lang.Object r2 = r6.m8818generateUnregisterIdAuthKifgIg(r7, r8, r9)
            kotlin.ResultKt.throwOnFailure(r2)
            com.reown.android.internal.common.model.DidJwt r2 = (com.reown.android.internal.common.model.DidJwt) r2
            java.lang.String r2 = r2.m8763unboximpl()
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r5
            r0.L$2 = r9
            r0.label = r4
            java.lang.Object r10 = r10.m8841invokegIAlus(r2, r0)
            if (r10 != r1) goto L_0x0088
            return r1
        L_0x0088:
            boolean r2 = kotlin.Result.m8986isSuccessimpl(r10)
            if (r2 == 0) goto L_0x00c5
            r2 = r10
            kotlin.Unit r2 = (kotlin.Unit) r2
            com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository r6 = r6.identitiesRepository
            java.lang.Object r4 = r9.getFirst()
            com.reown.foundation.common.model.PublicKey r4 = (com.reown.foundation.common.model.PublicKey) r4
            java.lang.String r4 = r4.m8862unboximpl()
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$2 = r7
            r0.L$3 = r10
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$4 = r7
            r7 = 0
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r6 = r6.removeIdentity(r4, r0)
            if (r6 != r1) goto L_0x00c3
            return r1
        L_0x00c3:
            r6 = r10
        L_0x00c4:
            r10 = r6
        L_0x00c5:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8828unregisterIdentityKeyInKeyserverslDN04U(java.lang.String, java.lang.String, kotlin.Pair, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    /* renamed from: generateAndStoreIdentityKeyPair-uN_RPug  reason: not valid java name */
    public final String m8829generateAndStoreIdentityKeyPairuN_RPug() {
        return this.keyManagementRepository.m8722generateAndStoreEd25519KeyPairuN_RPug();
    }

    @NotNull
    /* renamed from: generatePayload-2YvqGik  reason: not valid java name */
    public final Object m8830generatePayload2YvqGik(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4, @Nullable List<String> list) {
        String str5 = str4;
        String str6 = str;
        Intrinsics.checkNotNullParameter(str, "accountId");
        String str7 = str2;
        Intrinsics.checkNotNullParameter(str2, "identityKey");
        Intrinsics.checkNotNullParameter(str5, "domain");
        Result.Companion companion = Result.Companion;
        String encodeDidPkh = JwtUtilsKt.encodeDidPkh(str);
        String buildUri = buildUri(str5, JwtUtilsKt.encodeEd25519DidKey(PublicKey.m8859getKeyAsBytesimpl(str2)));
        String bytesToHex = UtilFunctionsKt.bytesToHex(UtilFunctionsKt.randomBytes(32));
        String format = new SimpleDateFormat(Cacao.Payload.ISO_8601_PATTERN, Locale.getDefault()).format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        List<String> list2 = list;
        return Result.m8979constructorimpl(new Cacao.Payload(encodeDidPkh, str5, buildUri, "1", bytesToHex, format, (String) null, (String) null, CacaoKt.getStatement(new Pair(str3, list2)), (String) null, list2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008b A[Catch:{ all -> 0x004c }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba A[Catch:{ all -> 0x004c }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: getAlreadyRegisteredValidIdentity-cBM7WSQ  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8831getAlreadyRegisteredValidIdentitycBM7WSQ(@org.jetbrains.annotations.NotNull java.lang.String r14, @org.jetbrains.annotations.Nullable java.lang.String r15, @org.jetbrains.annotations.NotNull java.lang.String r16, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.foundation.common.model.PublicKey>> r18) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r18
            boolean r3 = r2 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1
            if (r3 == 0) goto L_0x0017
            r3 = r2
            com.reown.android.keyserver.domain.IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1 r3 = (com.reown.android.keyserver.domain.IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x0017
            int r4 = r4 - r5
            r3.label = r4
            goto L_0x001c
        L_0x0017:
            com.reown.android.keyserver.domain.IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1 r3 = new com.reown.android.keyserver.domain.IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1
            r3.<init>(r13, r2)
        L_0x001c:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0057
            if (r5 != r6) goto L_0x004f
            java.lang.Object r0 = r3.L$5
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r3.L$4
            com.reown.android.keyserver.domain.IdentitiesInteractor r1 = (com.reown.android.keyserver.domain.IdentitiesInteractor) r1
            java.lang.Object r4 = r3.L$3
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r3.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r3.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r3 = r3.L$0
            java.lang.String r3 = (java.lang.String) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x004c }
            r10 = r4
            r12 = r2
            r2 = r0
            r0 = r1
            r1 = r3
            r3 = r12
            goto L_0x0087
        L_0x004c:
            r0 = move-exception
            goto L_0x00c0
        L_0x004f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r2)
            boolean r2 = com.reown.android.internal.common.model.AccountId.m8754isValidimpl(r14)
            if (r2 == 0) goto L_0x00cb
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x004c }
            java.lang.String r2 = r13.m8820getIdentityPublicKeywy9PYZM(r14)     // Catch:{ all -> 0x004c }
            com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository r5 = r0.identitiesRepository     // Catch:{ all -> 0x004c }
            r3.L$0 = r1     // Catch:{ all -> 0x004c }
            r8 = r15
            r3.L$1 = r8     // Catch:{ all -> 0x004c }
            r9 = r16
            r3.L$2 = r9     // Catch:{ all -> 0x004c }
            r10 = r17
            r3.L$3 = r10     // Catch:{ all -> 0x004c }
            r3.L$4 = r0     // Catch:{ all -> 0x004c }
            r3.L$5 = r2     // Catch:{ all -> 0x004c }
            r11 = 0
            r3.I$0 = r11     // Catch:{ all -> 0x004c }
            r3.label = r6     // Catch:{ all -> 0x004c }
            java.lang.Object r3 = r5.getCacaoPayloadByIdentity(r2, r3)     // Catch:{ all -> 0x004c }
            if (r3 != r4) goto L_0x0085
            return r4
        L_0x0085:
            r6 = r8
            r5 = r9
        L_0x0087:
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r3 = (com.reown.android.internal.common.signing.cacao.Cacao.Payload) r3     // Catch:{ all -> 0x004c }
            if (r3 == 0) goto L_0x00ba
            r13 = r0
            r14 = r1
            r15 = r2
            r16 = r6
            r17 = r5
            r18 = r10
            java.lang.Object r0 = r13.m8830generatePayload2YvqGik(r14, r15, r16, r17, r18)     // Catch:{ all -> 0x004c }
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x004c }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r0 = (com.reown.android.internal.common.signing.cacao.Cacao.Payload) r0     // Catch:{ all -> 0x004c }
            java.lang.String r3 = r3.getStatement()     // Catch:{ all -> 0x004c }
            java.lang.String r0 = r0.getStatement()     // Catch:{ all -> 0x004c }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x00b4
            com.reown.foundation.common.model.PublicKey r0 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r2)     // Catch:{ all -> 0x004c }
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)     // Catch:{ all -> 0x004c }
            goto L_0x00ca
        L_0x00b4:
            com.reown.android.internal.common.exception.AccountHasDifferentStatementStored r0 = new com.reown.android.internal.common.exception.AccountHasDifferentStatementStored     // Catch:{ all -> 0x004c }
            r0.<init>(r1, r7)     // Catch:{ all -> 0x004c }
            throw r0     // Catch:{ all -> 0x004c }
        L_0x00ba:
            com.reown.android.internal.common.exception.AccountHasNoCacaoPayloadStored r0 = new com.reown.android.internal.common.exception.AccountHasNoCacaoPayloadStored     // Catch:{ all -> 0x004c }
            r0.<init>(r1, r7)     // Catch:{ all -> 0x004c }
            throw r0     // Catch:{ all -> 0x004c }
        L_0x00c0:
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)
        L_0x00ca:
            return r0
        L_0x00cb:
            com.reown.android.internal.common.exception.InvalidAccountIdException r0 = new com.reown.android.internal.common.exception.InvalidAccountIdException
            r0.<init>(r14, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8831getAlreadyRegisteredValidIdentitycBM7WSQ(java.lang.String, java.lang.String, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    /* renamed from: getIdentityKeyPair-PaLCHi0  reason: not valid java name */
    public final Pair<PublicKey, PrivateKey> m8832getIdentityKeyPairPaLCHi0(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "accountId");
        return this.keyManagementRepository.m8727getKeyPairwSlyqho(m8820getIdentityPublicKeywy9PYZM(str));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: com.reown.android.internal.common.signing.cacao.Cacao$Signature} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: com.reown.android.internal.common.signing.cacao.Cacao$Payload} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: registerIdentity-fEM-vcc  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8833registerIdentityfEMvcc(@org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull com.reown.android.internal.common.signing.cacao.Cacao.Payload r10, @org.jetbrains.annotations.NotNull com.reown.android.internal.common.signing.cacao.Cacao.Signature r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$3
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$3 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.label = r1
        L_0x0012:
            r6 = r0
            goto L_0x001a
        L_0x0014:
            com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$3 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$3
            r0.<init>(r8, r12)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0067
            if (r1 == r3) goto L_0x004d
            if (r1 != r2) goto L_0x0045
            java.lang.Object r9 = r6.L$5
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r6.L$4
            kotlin.Unit r10 = (kotlin.Unit) r10
            java.lang.Object r10 = r6.L$3
            java.lang.Object r11 = r6.L$2
            com.reown.android.internal.common.signing.cacao.Cacao$Signature r11 = (com.reown.android.internal.common.signing.cacao.Cacao.Signature) r11
            java.lang.Object r11 = r6.L$1
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r11 = (com.reown.android.internal.common.signing.cacao.Cacao.Payload) r11
            java.lang.Object r11 = r6.L$0
            java.lang.String r11 = (java.lang.String) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00cf
        L_0x0045:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x004d:
            java.lang.Object r9 = r6.L$2
            r11 = r9
            com.reown.android.internal.common.signing.cacao.Cacao$Signature r11 = (com.reown.android.internal.common.signing.cacao.Cacao.Signature) r11
            java.lang.Object r9 = r6.L$1
            r10 = r9
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r10 = (com.reown.android.internal.common.signing.cacao.Cacao.Payload) r10
            java.lang.Object r9 = r6.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r12 = r12.m8988unboximpl()
        L_0x0064:
            r4 = r10
            r10 = r12
            goto L_0x008a
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r12)
            com.reown.android.keyserver.domain.use_case.RegisterIdentityUseCase r12 = r8.registerIdentityUseCase
            com.reown.android.internal.common.signing.cacao.Cacao r1 = new com.reown.android.internal.common.signing.cacao.Cacao
            com.reown.android.internal.common.signing.cacao.CacaoType r4 = com.reown.android.internal.common.signing.cacao.CacaoType.EIP4361
            com.reown.android.internal.common.signing.cacao.Cacao$Header r4 = r4.toHeader()
            r1.<init>(r4, r10, r11)
            r6.L$0 = r9
            r6.L$1 = r10
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r6.L$2 = r4
            r6.label = r3
            java.lang.Object r12 = r12.m8837invokegIAlus(r1, r6)
            if (r12 != r0) goto L_0x0064
            return r0
        L_0x008a:
            boolean r12 = kotlin.Result.m8986isSuccessimpl(r10)
            if (r12 == 0) goto L_0x00d2
            r12 = r10
            kotlin.Unit r12 = (kotlin.Unit) r12
            com.reown.android.internal.common.signing.cacao.Issuer r1 = new com.reown.android.internal.common.signing.cacao.Issuer
            java.lang.String r3 = r4.getIss()
            r1.<init>(r3)
            java.lang.String r1 = r1.getAccountId()
            java.lang.String r7 = com.reown.android.internal.common.model.AccountId.m8750constructorimpl(r1)
            com.reown.android.internal.common.storage.identity.IdentitiesStorageRepository r1 = r8.identitiesRepository
            r6.L$0 = r9
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r6.L$1 = r3
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r6.L$2 = r11
            r6.L$3 = r10
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r6.L$4 = r11
            r6.L$5 = r7
            r11 = 0
            r6.I$0 = r11
            r6.label = r2
            r5 = 1
            r2 = r9
            r3 = r7
            java.lang.Object r11 = r1.m8806insertIdentityRiN_R_Q(r2, r3, r4, r5, r6)
            if (r11 != r0) goto L_0x00cd
            return r0
        L_0x00cd:
            r11 = r9
            r9 = r7
        L_0x00cf:
            r8.m8827storeIdentityPublicKeyjnUdMTY(r11, r9)
        L_0x00d2:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8833registerIdentityfEMvcc(java.lang.String, com.reown.android.internal.common.signing.cacao.Cacao$Payload, com.reown.android.internal.common.signing.cacao.Cacao$Signature, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ec A[SYNTHETIC, Splitter:B:27:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: registerIdentity-xVnHb-c  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8834registerIdentityxVnHbc(@org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull java.lang.String r19, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r20, @org.jetbrains.annotations.NotNull java.lang.String r21, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.String, com.reown.android.internal.common.signing.cacao.Cacao.Signature> r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.foundation.common.model.PublicKey>> r23) {
        /*
            r16 = this;
            r0 = r23
            boolean r1 = r0 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$1 r1 = (com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0017
            int r2 = r2 - r3
            r1.label = r2
            r8 = r16
            goto L_0x001e
        L_0x0017:
            com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$1 r1 = new com.reown.android.keyserver.domain.IdentitiesInteractor$registerIdentity$1
            r8 = r16
            r1.<init>(r8, r0)
        L_0x001e:
            java.lang.Object r0 = r1.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r10 = 3
            r11 = 2
            r3 = 1
            if (r2 == 0) goto L_0x00b2
            if (r2 == r3) goto L_0x008a
            if (r2 == r11) goto L_0x0063
            if (r2 != r10) goto L_0x005b
            java.lang.Object r2 = r1.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r2 = r1.L$5
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r2 = r1.L$4
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$3
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0058 }
            kotlin.Result r0 = (kotlin.Result) r0     // Catch:{ all -> 0x0058 }
            java.lang.Object r0 = r0.m8988unboximpl()     // Catch:{ all -> 0x0058 }
            goto L_0x0189
        L_0x0058:
            r0 = move-exception
            goto L_0x019c
        L_0x005b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0063:
            java.lang.Object r2 = r1.L$6
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r2 = r1.L$5
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r2 = r1.L$4
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$3
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0058 }
            kotlin.Result r0 = (kotlin.Result) r0     // Catch:{ all -> 0x0058 }
            java.lang.Object r0 = r0.m8988unboximpl()     // Catch:{ all -> 0x0058 }
            goto L_0x0132
        L_0x008a:
            java.lang.Object r2 = r1.L$5
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r3 = r1.L$4
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r1.L$3
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r1.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r1.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r1.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.Result r0 = (kotlin.Result) r0
            java.lang.Object r0 = r0.m8988unboximpl()
            r15 = r3
            r14 = r4
            r13 = r5
            r12 = r6
            r3 = r0
            r0 = r7
            goto L_0x00e4
        L_0x00b2:
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r17
            r1.L$0 = r0
            r12 = r18
            r1.L$1 = r12
            r13 = r19
            r1.L$2 = r13
            r14 = r20
            r1.L$3 = r14
            r15 = r21
            r1.L$4 = r15
            r7 = r22
            r1.L$5 = r7
            r1.label = r3
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r1
            java.lang.Object r2 = r2.m8831getAlreadyRegisteredValidIdentitycBM7WSQ(r3, r4, r5, r6, r7)
            if (r2 != r9) goto L_0x00e1
            return r9
        L_0x00e1:
            r3 = r2
            r2 = r22
        L_0x00e4:
            java.lang.Throwable r4 = kotlin.Result.m8982exceptionOrNullimpl(r3)
            if (r4 != 0) goto L_0x00ec
            goto L_0x01a7
        L_0x00ec:
            boolean r3 = r4 instanceof com.reown.android.internal.common.model.MissingKeyException     // Catch:{ all -> 0x0058 }
            r5 = 0
            if (r3 == 0) goto L_0x013c
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ all -> 0x0058 }
            r1.L$0 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ all -> 0x0058 }
            r1.L$1 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ all -> 0x0058 }
            r1.L$2 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ all -> 0x0058 }
            r1.L$3 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)     // Catch:{ all -> 0x0058 }
            r1.L$4 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ all -> 0x0058 }
            r1.L$5 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0058 }
            r1.L$6 = r3     // Catch:{ all -> 0x0058 }
            r1.I$0 = r5     // Catch:{ all -> 0x0058 }
            r1.label = r11     // Catch:{ all -> 0x0058 }
            r17 = r0
            r18 = r12
            r19 = r13
            r20 = r14
            r21 = r2
            r22 = r1
            java.lang.Object r0 = r16.m8817generateAndStoreNewIdentity21kkFcg(r17, r18, r19, r20, r21, r22)     // Catch:{ all -> 0x0058 }
            if (r0 != r9) goto L_0x0132
            return r9
        L_0x0132:
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0058 }
            com.reown.foundation.common.model.PublicKey r0 = (com.reown.foundation.common.model.PublicKey) r0     // Catch:{ all -> 0x0058 }
            java.lang.String r0 = r0.m8862unboximpl()     // Catch:{ all -> 0x0058 }
            goto L_0x0192
        L_0x013c:
            boolean r3 = r4 instanceof com.reown.android.internal.common.exception.AccountHasNoCacaoPayloadStored     // Catch:{ all -> 0x0058 }
            if (r3 != 0) goto L_0x0146
            boolean r3 = r4 instanceof com.reown.android.internal.common.exception.AccountHasDifferentStatementStored     // Catch:{ all -> 0x0058 }
            if (r3 == 0) goto L_0x0145
            goto L_0x0146
        L_0x0145:
            throw r4     // Catch:{ all -> 0x0058 }
        L_0x0146:
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ all -> 0x0058 }
            r1.L$0 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ all -> 0x0058 }
            r1.L$1 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ all -> 0x0058 }
            r1.L$2 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ all -> 0x0058 }
            r1.L$3 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)     // Catch:{ all -> 0x0058 }
            r1.L$4 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ all -> 0x0058 }
            r1.L$5 = r3     // Catch:{ all -> 0x0058 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0058 }
            r1.L$6 = r3     // Catch:{ all -> 0x0058 }
            r1.I$0 = r5     // Catch:{ all -> 0x0058 }
            r1.label = r10     // Catch:{ all -> 0x0058 }
            r17 = r0
            r18 = r12
            r19 = r13
            r20 = r14
            r21 = r15
            r22 = r2
            r23 = r1
            java.lang.Object r0 = r16.m8821handleIdentitiesOutdatedStatementsxVnHbc(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ all -> 0x0058 }
            if (r0 != r9) goto L_0x0189
            return r9
        L_0x0189:
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0058 }
            com.reown.foundation.common.model.PublicKey r0 = (com.reown.foundation.common.model.PublicKey) r0     // Catch:{ all -> 0x0058 }
            java.lang.String r0 = r0.m8862unboximpl()     // Catch:{ all -> 0x0058 }
        L_0x0192:
            com.reown.foundation.common.model.PublicKey r0 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r0)     // Catch:{ all -> 0x0058 }
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)     // Catch:{ all -> 0x0058 }
        L_0x019a:
            r3 = r0
            goto L_0x01a7
        L_0x019c:
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)
            goto L_0x019a
        L_0x01a7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8834registerIdentityxVnHbc(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: resolveIdentityDidKey-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8835resolveIdentityDidKeygIAlus(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.android.internal.common.model.AccountId>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityDidKey$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityDidKey$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityDidKey$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityDidKey$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$resolveIdentityDidKey$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Result r7 = (kotlin.Result) r7
            java.lang.Object r5 = r7.m8988unboximpl()
            goto L_0x005f
        L_0x0033:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = ":"
            java.lang.String[] r7 = new java.lang.String[]{r7}
            r2 = 6
            r4 = 0
            java.util.List r7 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r6, (java.lang.String[]) r7, false, (int) r4, (int) r2, (java.lang.Object) null)
            java.lang.Object r7 = kotlin.collections.CollectionsKt.last(r7)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.m8825resolveIdentitygIAlus(r7, r0)
            if (r5 != r1) goto L_0x005f
            return r1
        L_0x005f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8835resolveIdentityDidKeygIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082 A[Catch:{ MissingKeyException -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0092 A[Catch:{ MissingKeyException -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: unregisterIdentity-K-ifgIg  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8836unregisterIdentityKifgIg(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.foundation.common.model.PublicKey>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentity$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentity$1 r0 = (com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentity$1 r0 = new com.reown.android.keyserver.domain.IdentitiesInteractor$unregisterIdentity$1
            r0.<init>(r6, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 != r4) goto L_0x0042
            java.lang.Object r7 = r0.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$2
            kotlin.Pair r8 = (kotlin.Pair) r8
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$0
            java.lang.String r8 = (java.lang.String) r8
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ MissingKeyException -> 0x0040 }
            kotlin.Result r9 = (kotlin.Result) r9     // Catch:{ MissingKeyException -> 0x0040 }
            java.lang.Object r9 = r9.m8988unboximpl()     // Catch:{ MissingKeyException -> 0x0040 }
            goto L_0x007c
        L_0x0040:
            r7 = r8
            goto L_0x00a2
        L_0x0042:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = com.reown.android.internal.common.model.AccountId.m8754isValidimpl(r7)     // Catch:{ MissingKeyException -> 0x00a2 }
            if (r9 == 0) goto L_0x009c
            kotlin.Pair r9 = r6.m8832getIdentityKeyPairPaLCHi0(r7)     // Catch:{ MissingKeyException -> 0x00a2 }
            java.lang.Object r2 = r9.component1()     // Catch:{ MissingKeyException -> 0x00a2 }
            com.reown.foundation.common.model.PublicKey r2 = (com.reown.foundation.common.model.PublicKey) r2     // Catch:{ MissingKeyException -> 0x00a2 }
            java.lang.String r2 = r2.m8862unboximpl()     // Catch:{ MissingKeyException -> 0x00a2 }
            r0.L$0 = r7     // Catch:{ MissingKeyException -> 0x00a2 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ MissingKeyException -> 0x00a2 }
            r0.L$1 = r5     // Catch:{ MissingKeyException -> 0x00a2 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ MissingKeyException -> 0x00a2 }
            r0.L$2 = r5     // Catch:{ MissingKeyException -> 0x00a2 }
            r0.L$3 = r2     // Catch:{ MissingKeyException -> 0x00a2 }
            r0.label = r4     // Catch:{ MissingKeyException -> 0x00a2 }
            java.lang.Object r9 = r6.m8828unregisterIdentityKeyInKeyserverslDN04U(r7, r8, r9, r0)     // Catch:{ MissingKeyException -> 0x00a2 }
            if (r9 != r1) goto L_0x007a
            return r1
        L_0x007a:
            r8 = r7
            r7 = r2
        L_0x007c:
            boolean r0 = kotlin.Result.m8986isSuccessimpl(r9)     // Catch:{ MissingKeyException -> 0x0040 }
            if (r0 == 0) goto L_0x0088
            kotlin.Unit r9 = (kotlin.Unit) r9     // Catch:{ MissingKeyException -> 0x0040 }
            com.reown.foundation.common.model.PublicKey r9 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r7)     // Catch:{ MissingKeyException -> 0x0040 }
        L_0x0088:
            java.lang.Object r9 = kotlin.Result.m8979constructorimpl(r9)     // Catch:{ MissingKeyException -> 0x0040 }
            boolean r0 = kotlin.Result.m8986isSuccessimpl(r9)     // Catch:{ MissingKeyException -> 0x0040 }
            if (r0 == 0) goto L_0x009b
            r0 = r9
            com.reown.foundation.common.model.PublicKey r0 = (com.reown.foundation.common.model.PublicKey) r0     // Catch:{ MissingKeyException -> 0x0040 }
            r0.m8862unboximpl()     // Catch:{ MissingKeyException -> 0x0040 }
            r6.m8823removeIdentityKeyPairjnUdMTY(r7, r8)     // Catch:{ MissingKeyException -> 0x0040 }
        L_0x009b:
            return r9
        L_0x009c:
            com.reown.android.internal.common.exception.InvalidAccountIdException r6 = new com.reown.android.internal.common.exception.InvalidAccountIdException     // Catch:{ MissingKeyException -> 0x00a2 }
            r6.<init>(r7, r3)     // Catch:{ MissingKeyException -> 0x00a2 }
            throw r6     // Catch:{ MissingKeyException -> 0x00a2 }
        L_0x00a2:
            com.reown.android.internal.common.exception.AccountHasNoIdentityStored r6 = new com.reown.android.internal.common.exception.AccountHasNoIdentityStored
            r6.<init>(r7, r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.keyserver.domain.IdentitiesInteractor.m8836unregisterIdentityKifgIg(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
