package com.reown.sign.engine.use_case.calls;

import android.util.Base64;
import androidx.browser.trusted.c;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.validator.SignValidator;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import com.reown.sign.storage.link_mode.LinkModeStorageRepository;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJl\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u00172\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010\u00172\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001d0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001d0&H@¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u00020+2\b\u0010$\u001a\u0004\u0018\u00010\u0017H@¢\u0006\u0002\u0010,J \u0010-\u001a\u00020\u00172\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010 2\u0006\u0010\u001c\u001a\u00020\u001eH\u0002J\u0010\u0010.\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001eH\u0002J\u0010\u0010/\u001a\u00020+2\u0006\u0010\u001c\u001a\u00020\u001eH\u0002J6\u00100\u001a\b\u0012\u0004\u0012\u00020\u001d012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020#H@¢\u0006\u0004\b9\u0010:J:\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001d012\u0006\u00102\u001a\u0002032\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020>0=2\u0006\u00106\u001a\u000207H@¢\u0006\u0004\b?\u0010@J\u001a\u0010A\u001a\u00020B2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCase;", "Lcom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "crypto", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "selfAppMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "authenticateResponseTopicRepository", "Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;", "proposeSessionUseCase", "Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCaseInterface;", "getPairingForSessionAuthenticate", "Lcom/reown/sign/engine/use_case/calls/GetPairingForSessionAuthenticateUseCase;", "getNamespacesFromReCaps", "Lcom/reown/sign/engine/use_case/calls/GetNamespacesFromReCaps;", "linkModeJsonRpcInteractor", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "linkModeStorageRepository", "Lcom/reown/sign/storage/link_mode/LinkModeStorageRepository;", "insertEventUseCase", "Lcom/reown/android/pulse/domain/InsertEventUseCase;", "clientId", "", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;Lcom/reown/android/internal/common/model/AppMetaData;Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCaseInterface;Lcom/reown/sign/engine/use_case/calls/GetPairingForSessionAuthenticateUseCase;Lcom/reown/sign/engine/use_case/calls/GetNamespacesFromReCaps;Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;Lcom/reown/sign/storage/link_mode/LinkModeStorageRepository;Lcom/reown/android/pulse/domain/InsertEventUseCase;Ljava/lang/String;Lcom/reown/foundation/util/Logger;)V", "authenticate", "", "Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "methods", "", "pairingTopic", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "walletAppLink", "onSuccess", "Lkotlin/Function1;", "onFailure", "", "(Lcom/reown/sign/engine/model/EngineDO$Authenticate;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLinkModeEnabled", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSignReCapsJson", "getExternalReCapsJson", "areExternalReCapsNotEmpty", "publishSessionAuthenticateDeferred", "Lkotlin/Result;", "pairing", "Lcom/reown/android/Core$Model$Pairing;", "authRequest", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionAuthenticate;", "responseTopic", "Lcom/reown/foundation/common/model/Topic;", "requestExpiry", "publishSessionAuthenticateDeferred-yxL6bBk", "(Lcom/reown/android/Core$Model$Pairing;Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionAuthenticate;Lcom/reown/foundation/common/model/Topic;Lcom/reown/android/internal/common/model/Expiry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "publishSessionProposeDeferred", "optionalNamespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "publishSessionProposeDeferred-BWLJW6A", "(Lcom/reown/android/Core$Model$Pairing;Ljava/util/Map;Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIrnParamsTtl", "Lcom/reown/foundation/common/model/Ttl;", "nowInSeconds", "", "generateUUID", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionAuthenticateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionAuthenticateUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,251:1\n1869#2,2:252\n461#2,6:254\n1761#2,3:260\n1#3:263\n*S KotlinDebug\n*F\n+ 1 SessionAuthenticateUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCase\n*L\n163#1:252,2\n169#1:254,6\n179#1:260,3\n*E\n"})
public final class SessionAuthenticateUseCase implements SessionAuthenticateUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final AuthenticateResponseTopicRepository authenticateResponseTopicRepository;
    @NotNull
    private final String clientId;
    @NotNull
    private final KeyManagementRepository crypto;
    @NotNull
    private final GetNamespacesFromReCaps getNamespacesFromReCaps;
    @NotNull
    private final GetPairingForSessionAuthenticateUseCase getPairingForSessionAuthenticate;
    @NotNull
    private final InsertEventUseCase insertEventUseCase;
    @NotNull
    private final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    @NotNull
    private final LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractor;
    @NotNull
    private final LinkModeStorageRepository linkModeStorageRepository;
    @NotNull
    private final Logger logger;
    @NotNull
    private final ProposeSessionUseCaseInterface proposeSessionUseCase;
    @NotNull
    private final AppMetaData selfAppMetaData;

    public SessionAuthenticateUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull KeyManagementRepository keyManagementRepository, @NotNull AppMetaData appMetaData, @NotNull AuthenticateResponseTopicRepository authenticateResponseTopicRepository2, @NotNull ProposeSessionUseCaseInterface proposeSessionUseCaseInterface, @NotNull GetPairingForSessionAuthenticateUseCase getPairingForSessionAuthenticateUseCase, @NotNull GetNamespacesFromReCaps getNamespacesFromReCaps2, @NotNull LinkModeJsonRpcInteractorInterface linkModeJsonRpcInteractorInterface, @NotNull LinkModeStorageRepository linkModeStorageRepository2, @NotNull InsertEventUseCase insertEventUseCase2, @NotNull String str, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(keyManagementRepository, "crypto");
        Intrinsics.checkNotNullParameter(appMetaData, "selfAppMetaData");
        Intrinsics.checkNotNullParameter(authenticateResponseTopicRepository2, "authenticateResponseTopicRepository");
        Intrinsics.checkNotNullParameter(proposeSessionUseCaseInterface, "proposeSessionUseCase");
        Intrinsics.checkNotNullParameter(getPairingForSessionAuthenticateUseCase, "getPairingForSessionAuthenticate");
        Intrinsics.checkNotNullParameter(getNamespacesFromReCaps2, "getNamespacesFromReCaps");
        Intrinsics.checkNotNullParameter(linkModeJsonRpcInteractorInterface, "linkModeJsonRpcInteractor");
        Intrinsics.checkNotNullParameter(linkModeStorageRepository2, "linkModeStorageRepository");
        Intrinsics.checkNotNullParameter(insertEventUseCase2, "insertEventUseCase");
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.crypto = keyManagementRepository;
        this.selfAppMetaData = appMetaData;
        this.authenticateResponseTopicRepository = authenticateResponseTopicRepository2;
        this.proposeSessionUseCase = proposeSessionUseCaseInterface;
        this.getPairingForSessionAuthenticate = getPairingForSessionAuthenticateUseCase;
        this.getNamespacesFromReCaps = getNamespacesFromReCaps2;
        this.linkModeJsonRpcInteractor = linkModeJsonRpcInteractorInterface;
        this.linkModeStorageRepository = linkModeStorageRepository2;
        this.insertEventUseCase = insertEventUseCase2;
        this.clientId = str;
        this.logger = logger2;
    }

    private final boolean areExternalReCapsNotEmpty(EngineDO.Authenticate authenticate) {
        if (authenticate.getResources() != null) {
            List<String> resources = authenticate.getResources();
            Intrinsics.checkNotNull(resources);
            Iterable<String> iterable = resources;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (String e02 : iterable) {
                    if (StringsKt__StringsJVMKt.startsWith$default(e02, Cacao.Payload.RECAPS_PREFIX, false, 2, (Object) null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final Unit authenticate$lambda$0(SessionAuthenticateUseCase sessionAuthenticateUseCase, Topic topic, Core.Model.Pairing pairing, Topic topic2) {
        Intrinsics.checkNotNullParameter(topic2, "it");
        Logger logger2 = sessionAuthenticateUseCase.logger;
        logger2.log("Session authenticate subscribed on topic: " + topic);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SessionAuthenticateUseCase$authenticate$2$1(sessionAuthenticateUseCase, pairing, topic, (Continuation<? super SessionAuthenticateUseCase$authenticate$2$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit authenticate$lambda$1(SessionAuthenticateUseCase sessionAuthenticateUseCase, Topic topic, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        Logger logger2 = sessionAuthenticateUseCase.logger;
        logger2.error("Session authenticate subscribing on topic error: " + topic + ", " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    private final String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        return uuid;
    }

    private final String getExternalReCapsJson(EngineDO.Authenticate authenticate) {
        try {
            if (!areExternalReCapsNotEmpty(authenticate)) {
                return Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
            }
            List<String> resources = authenticate.getResources();
            Intrinsics.checkNotNull(resources);
            ListIterator<String> listIterator = resources.listIterator(resources.size());
            while (listIterator.hasPrevious()) {
                String previous = listIterator.previous();
                if (StringsKt__StringsJVMKt.startsWith$default(previous, Cacao.Payload.RECAPS_PREFIX, false, 2, (Object) null)) {
                    byte[] decode = Base64.decode(StringsKt__StringsKt.removePrefix(previous, (CharSequence) Cacao.Payload.RECAPS_PREFIX), 2);
                    Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                    return new String(decode, Charsets.UTF_8);
                }
            }
            throw new NoSuchElementException("List contains no element matching the predicate.");
        } catch (Exception unused) {
            return Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
    }

    private final Ttl getIrnParamsTtl(Expiry expiry, long j2) {
        if (expiry == null) {
            return new Ttl(Time.getDayInSeconds());
        }
        long dayInSeconds = Time.getDayInSeconds();
        long seconds = expiry.getSeconds() - j2;
        Long valueOf = Long.valueOf(seconds);
        if (seconds < dayInSeconds) {
            valueOf = null;
        }
        if (valueOf != null) {
            dayInSeconds = valueOf.longValue();
        }
        return new Ttl(dayInSeconds);
    }

    private final String getSignReCapsJson(List<String> list, EngineDO.Authenticate authenticate) {
        Collection collection = list;
        if (collection == null || collection.isEmpty()) {
            return Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        String namespaceKeyFromChainId$sign_release = SignValidator.INSTANCE.getNamespaceKeyFromChainId$sign_release((String) CollectionsKt.first(authenticate.getChains()));
        JSONObject jSONObject = new JSONObject();
        for (String a2 : list) {
            jSONObject.put(c.a("request/", a2), new JSONArray().put(0, new JSONObject()));
        }
        String jSONObject2 = new JSONObject().put(Cacao.Payload.ATT_KEY, new JSONObject().put(namespaceKeyFromChainId$sign_release, jSONObject)).toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return StringsKt__StringsJVMKt.replace$default(jSONObject2, "\\/", "/", false, 4, (Object) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r0 = r2.selfAppMetaData.getRedirect();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object isLinkModeEnabled(java.lang.String r3, kotlin.coroutines.Continuation<? super java.lang.Boolean> r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x001f
            int r0 = r3.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x001f
        L_0x0009:
            com.reown.android.internal.common.model.AppMetaData r0 = r2.selfAppMetaData
            com.reown.android.internal.common.model.Redirect r0 = r0.getRedirect()
            if (r0 == 0) goto L_0x001f
            boolean r0 = r0.getLinkMode()
            r1 = 1
            if (r0 != r1) goto L_0x001f
            com.reown.sign.storage.link_mode.LinkModeStorageRepository r2 = r2.linkModeStorageRepository
            java.lang.Object r2 = r2.isEnabled(r3, r4)
            return r2
        L_0x001f:
            r2 = 0
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase.isLinkModeEnabled(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* renamed from: publishSessionAuthenticateDeferred-yxL6bBk  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8893publishSessionAuthenticateDeferredyxL6bBk(com.reown.android.Core.Model.Pairing r21, com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionAuthenticate r22, com.reown.foundation.common.model.Topic r23, com.reown.android.internal.common.model.Expiry r24, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r25) {
        /*
            r20 = this;
            r0 = r20
            r1 = r25
            boolean r2 = r1 instanceof com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1 r2 = (com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1 r2 = new com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1
            r2.<init>(r0, r1)
        L_0x001c:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x0052
            if (r4 != r5) goto L_0x004a
            java.lang.Object r0 = r2.L$6
            kotlinx.coroutines.CompletableDeferred r0 = (kotlinx.coroutines.CompletableDeferred) r0
            java.lang.Object r0 = r2.L$5
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            java.lang.Object r0 = r2.L$4
            com.reown.foundation.common.model.Ttl r0 = (com.reown.foundation.common.model.Ttl) r0
            java.lang.Object r0 = r2.L$3
            com.reown.android.internal.common.model.Expiry r0 = (com.reown.android.internal.common.model.Expiry) r0
            java.lang.Object r0 = r2.L$2
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r2.L$1
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate r0 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionAuthenticate) r0
            java.lang.Object r0 = r2.L$0
            com.reown.android.Core$Model$Pairing r0 = (com.reown.android.Core.Model.Pairing) r0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00f7
        L_0x004a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r1)
            com.reown.foundation.util.Logger r1 = r0.logger
            java.lang.String r4 = r21.getTopic()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Sending session authenticate on topic: "
            r6.<init>(r7)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r1.log((java.lang.String) r4)
            long r6 = com.reown.android.internal.utils.Time.getCurrentTimeInSeconds()
            r1 = r24
            com.reown.foundation.common.model.Ttl r4 = r0.getIrnParamsTtl(r1, r6)
            com.reown.android.internal.common.model.IrnParams r19 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r9 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE
            long r6 = r22.getId()
            java.lang.Long r11 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
            r15 = 0
            r16 = 1
            r12 = 0
            r13 = 0
            r14 = 0
            r17 = 120(0x78, float:1.68E-43)
            r18 = 0
            r8 = r19
            r10 = r4
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            r6 = 0
            kotlinx.coroutines.CompletableDeferred r15 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r6, r5, r6)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r6 = r0.jsonRpcInteractor
            com.reown.foundation.common.model.Topic r7 = new com.reown.foundation.common.model.Topic
            java.lang.String r8 = r21.getTopic()
            r7.<init>(r8)
            com.reown.sign.engine.use_case.calls.o r12 = new com.reown.sign.engine.use_case.calls.o
            r8 = 1
            r14 = r21
            r12.<init>(r0, r14, r15, r8)
            com.reown.sign.engine.use_case.calls.p r13 = new com.reown.sign.engine.use_case.calls.p
            r11 = r23
            r13.<init>(r0, r11, r15, r8)
            r10 = 0
            r0 = 0
            r16 = 24
            r17 = 0
            r8 = r19
            r9 = r22
            r11 = r0
            r14 = r16
            r0 = r15
            r15 = r17
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)
            r2.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)
            r2.L$1 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)
            r2.L$2 = r6
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24)
            r2.L$3 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r2.L$4 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)
            r2.L$5 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r2.L$6 = r1
            r2.label = r5
            java.lang.Object r1 = r0.await(r2)
            if (r1 != r3) goto L_0x00f7
            return r3
        L_0x00f7:
            kotlin.Result r1 = (kotlin.Result) r1
            java.lang.Object r0 = r1.m8988unboximpl()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase.m8893publishSessionAuthenticateDeferredyxL6bBk(com.reown.android.Core$Model$Pairing, com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate, com.reown.foundation.common.model.Topic, com.reown.android.internal.common.model.Expiry, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Unit publishSessionAuthenticateDeferred_yxL6bBk$lambda$5(SessionAuthenticateUseCase sessionAuthenticateUseCase, Core.Model.Pairing pairing, CompletableDeferred completableDeferred) {
        Logger logger2 = sessionAuthenticateUseCase.logger;
        String topic = pairing.getTopic();
        logger2.log("Session authenticate sent successfully on topic: " + topic);
        Result.Companion companion = Result.Companion;
        Unit unit = Unit.INSTANCE;
        completableDeferred.complete(Result.m8978boximpl(Result.m8979constructorimpl(unit)));
        return unit;
    }

    /* access modifiers changed from: private */
    public static final Unit publishSessionAuthenticateDeferred_yxL6bBk$lambda$6(SessionAuthenticateUseCase sessionAuthenticateUseCase, Topic topic, CompletableDeferred completableDeferred, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        RelayJsonRpcInteractorInterface.unsubscribe$default(sessionAuthenticateUseCase.jsonRpcInteractor, topic, (Function0) null, (Function1) null, 6, (Object) null);
        Logger logger2 = sessionAuthenticateUseCase.logger;
        logger2.error("Failed to send a auth request: " + th);
        Result.Companion companion = Result.Companion;
        completableDeferred.complete(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00dd A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* renamed from: publishSessionProposeDeferred-BWLJW6A  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8894publishSessionProposeDeferredBWLJW6A(com.reown.android.Core.Model.Pairing r17, java.util.Map<java.lang.String, com.reown.sign.engine.model.EngineDO.Namespace.Proposal> r18, com.reown.foundation.common.model.Topic r19, kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r20
            boolean r2 = r1 instanceof com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionProposeDeferred$1
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionProposeDeferred$1 r2 = (com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionProposeDeferred$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionProposeDeferred$1 r2 = new com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$publishSessionProposeDeferred$1
            r2.<init>(r0, r1)
        L_0x001c:
            java.lang.Object r1 = r2.result
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            r13 = 2
            r4 = 1
            if (r3 == 0) goto L_0x005f
            if (r3 == r4) goto L_0x0049
            if (r3 != r13) goto L_0x0041
            java.lang.Object r0 = r2.L$3
            kotlinx.coroutines.CompletableDeferred r0 = (kotlinx.coroutines.CompletableDeferred) r0
            java.lang.Object r0 = r2.L$2
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r2.L$1
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r0 = r2.L$0
            com.reown.android.Core$Model$Pairing r0 = (com.reown.android.Core.Model.Pairing) r0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00de
        L_0x0041:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0049:
            java.lang.Object r0 = r2.L$3
            kotlinx.coroutines.CompletableDeferred r0 = (kotlinx.coroutines.CompletableDeferred) r0
            java.lang.Object r3 = r2.L$2
            com.reown.foundation.common.model.Topic r3 = (com.reown.foundation.common.model.Topic) r3
            java.lang.Object r4 = r2.L$1
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r5 = r2.L$0
            com.reown.android.Core$Model$Pairing r5 = (com.reown.android.Core.Model.Pairing) r5
            kotlin.ResultKt.throwOnFailure(r1)
            r15 = r3
            r14 = r5
            goto L_0x00bd
        L_0x005f:
            kotlin.ResultKt.throwOnFailure(r1)
            com.reown.foundation.util.Logger r1 = r0.logger
            java.lang.String r3 = r17.getTopic()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Sending session proposal as a fallback on topic: "
            r5.<init>(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r1.log((java.lang.String) r3)
            r1 = 0
            kotlinx.coroutines.CompletableDeferred r1 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r1, r4, r1)
            com.reown.sign.engine.use_case.calls.ProposeSessionUseCaseInterface r3 = r0.proposeSessionUseCase
            java.util.Map r5 = kotlin.collections.MapsKt.emptyMap()
            com.reown.android.internal.common.model.Pairing r8 = com.reown.android.pairing.model.mapper.PairingMapperKt.toPairing(r17)
            com.reown.sign.engine.use_case.calls.o r9 = new com.reown.sign.engine.use_case.calls.o
            r6 = 0
            r14 = r17
            r9.<init>(r0, r14, r1, r6)
            com.reown.sign.engine.use_case.calls.p r10 = new com.reown.sign.engine.use_case.calls.p
            r15 = r19
            r10.<init>(r0, r15, r1, r6)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)
            r2.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)
            r2.L$1 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)
            r2.L$2 = r0
            r2.L$3 = r1
            r2.label = r4
            r6 = 0
            r7 = 0
            r4 = r5
            r5 = r18
            r11 = r2
            java.lang.Object r0 = r3.proposeSession(r4, r5, r6, r7, r8, r9, r10, r11)
            if (r0 != r12) goto L_0x00ba
            return r12
        L_0x00ba:
            r4 = r18
            r0 = r1
        L_0x00bd:
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r2.L$0 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r2.L$1 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)
            r2.L$2 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r2.L$3 = r1
            r2.label = r13
            java.lang.Object r1 = r0.await(r2)
            if (r1 != r12) goto L_0x00de
            return r12
        L_0x00de:
            kotlin.Result r1 = (kotlin.Result) r1
            java.lang.Object r0 = r1.m8988unboximpl()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase.m8894publishSessionProposeDeferredBWLJW6A(com.reown.android.Core$Model$Pairing, java.util.Map, com.reown.foundation.common.model.Topic, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Unit publishSessionProposeDeferred_BWLJW6A$lambda$7(SessionAuthenticateUseCase sessionAuthenticateUseCase, Core.Model.Pairing pairing, CompletableDeferred completableDeferred) {
        Logger logger2 = sessionAuthenticateUseCase.logger;
        String topic = pairing.getTopic();
        logger2.log("Session proposal as a fallback sent successfully on topic: " + topic);
        Result.Companion companion = Result.Companion;
        Unit unit = Unit.INSTANCE;
        completableDeferred.complete(Result.m8978boximpl(Result.m8979constructorimpl(unit)));
        return unit;
    }

    /* access modifiers changed from: private */
    public static final Unit publishSessionProposeDeferred_BWLJW6A$lambda$8(SessionAuthenticateUseCase sessionAuthenticateUseCase, Topic topic, CompletableDeferred completableDeferred, Throwable th) {
        Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        RelayJsonRpcInteractorInterface.unsubscribe$default(sessionAuthenticateUseCase.jsonRpcInteractor, topic, (Function0) null, (Function1) null, 6, (Object) null);
        Logger logger2 = sessionAuthenticateUseCase.logger;
        logger2.error("Failed to send a session proposal as a fallback: " + th);
        Result.Companion companion = Result.Companion;
        completableDeferred.complete(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(th))));
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0275 A[SYNTHETIC, Splitter:B:56:0x0275] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x034d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0032  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object authenticate(@org.jetbrains.annotations.NotNull com.reown.sign.engine.model.EngineDO.Authenticate r44, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r45, @org.jetbrains.annotations.Nullable java.lang.String r46, @org.jetbrains.annotations.Nullable com.reown.android.internal.common.model.Expiry r47, @org.jetbrains.annotations.Nullable java.lang.String r48, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r49, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r50, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r51) {
        /*
            r43 = this;
            r0 = r43
            r1 = r44
            r2 = r45
            r3 = r47
            r4 = r48
            r5 = r50
            r6 = r51
            boolean r7 = r6 instanceof com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$1
            if (r7 == 0) goto L_0x0021
            r7 = r6
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$1 r7 = (com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$1) r7
            int r8 = r7.label
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r10 = r8 & r9
            if (r10 == 0) goto L_0x0021
            int r8 = r8 - r9
            r7.label = r8
            goto L_0x0026
        L_0x0021:
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$1 r7 = new com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$1
            r7.<init>(r0, r6)
        L_0x0026:
            java.lang.Object r6 = r7.result
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r9 = r7.label
            r10 = 2
            r11 = 1
            if (r9 == 0) goto L_0x00e9
            if (r9 == r11) goto L_0x0086
            if (r9 != r10) goto L_0x007e
            java.lang.Object r1 = r7.L$15
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate r1 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionAuthenticate) r1
            java.lang.Object r1 = r7.L$14
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r1 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r1
            java.lang.Object r1 = r7.L$13
            com.reown.foundation.common.model.Topic r1 = (com.reown.foundation.common.model.Topic) r1
            java.lang.Object r1 = r7.L$12
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r7.L$11
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r7.L$10
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r7.L$9
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r7.L$8
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r1 = r7.L$7
            com.reown.android.internal.common.model.Expiry r1 = (com.reown.android.internal.common.model.Expiry) r1
            java.lang.Object r1 = r7.L$6
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            java.lang.Object r2 = r7.L$5
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r2 = r7.L$4
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r7.L$3
            com.reown.android.internal.common.model.Expiry r2 = (com.reown.android.internal.common.model.Expiry) r2
            java.lang.Object r2 = r7.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r7.L$1
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r7.L$0
            com.reown.sign.engine.model.EngineDO$Authenticate r2 = (com.reown.sign.engine.model.EngineDO.Authenticate) r2
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Error -> 0x007b }
            goto L_0x033f
        L_0x007b:
            r0 = move-exception
            goto L_0x0349
        L_0x007e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0086:
            java.lang.Object r1 = r7.L$15
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate r1 = (com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionAuthenticate) r1
            java.lang.Object r2 = r7.L$14
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r2 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r2
            java.lang.Object r3 = r7.L$13
            com.reown.foundation.common.model.Topic r3 = (com.reown.foundation.common.model.Topic) r3
            java.lang.Object r4 = r7.L$12
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r7.L$11
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r9 = r7.L$10
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r11 = r7.L$9
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r7.L$8
            java.util.Map r12 = (java.util.Map) r12
            java.lang.Object r13 = r7.L$7
            com.reown.android.internal.common.model.Expiry r13 = (com.reown.android.internal.common.model.Expiry) r13
            java.lang.Object r14 = r7.L$6
            kotlin.jvm.functions.Function1 r14 = (kotlin.jvm.functions.Function1) r14
            java.lang.Object r15 = r7.L$5
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15
            java.lang.Object r10 = r7.L$4
            java.lang.String r10 = (java.lang.String) r10
            r44 = r1
            java.lang.Object r1 = r7.L$3
            com.reown.android.internal.common.model.Expiry r1 = (com.reown.android.internal.common.model.Expiry) r1
            r45 = r1
            java.lang.Object r1 = r7.L$2
            java.lang.String r1 = (java.lang.String) r1
            r46 = r1
            java.lang.Object r1 = r7.L$1
            java.util.List r1 = (java.util.List) r1
            r47 = r1
            java.lang.Object r1 = r7.L$0
            com.reown.sign.engine.model.EngineDO$Authenticate r1 = (com.reown.sign.engine.model.EngineDO.Authenticate) r1
            kotlin.ResultKt.throwOnFailure(r6)
            r26 = r5
            r16 = r8
            r25 = r9
            r17 = r11
            r9 = r12
            r8 = r14
            r12 = r15
            r5 = r44
            r15 = r2
            r11 = r3
            r14 = r4
            r4 = r45
            r3 = r46
            r2 = r47
            goto L_0x026d
        L_0x00e9:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.List r6 = r44.getChains()
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x010a
            com.reown.foundation.util.Logger r0 = r0.logger
            java.lang.String r1 = "Sending session authenticate request error: chains are empty"
            r0.error((java.lang.String) r1)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Chains are empty"
            r0.<init>(r1)
            r5.invoke(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x010a:
            com.reown.android.internal.utils.CoreValidator r6 = com.reown.android.internal.utils.CoreValidator.INSTANCE
            boolean r6 = r6.isExpiryWithinBounds(r3)
            if (r6 != 0) goto L_0x0125
            com.reown.foundation.util.Logger r0 = r0.logger
            java.lang.String r1 = "Sending session authenticate request error: expiry not within bounds"
            r0.error((java.lang.String) r1)
            com.reown.android.internal.common.exception.InvalidExpiryException r0 = new com.reown.android.internal.common.exception.InvalidExpiryException
            r1 = 0
            r0.<init>(r1, r11, r1)
            r5.invoke(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0125:
            if (r3 != 0) goto L_0x0136
            com.reown.android.internal.common.model.Expiry r6 = new com.reown.android.internal.common.model.Expiry
            long r9 = com.reown.android.internal.utils.Time.getCurrentTimeInSeconds()
            long r12 = com.reown.android.internal.utils.Time.getOneHourInSeconds()
            long r12 = r12 + r9
            r6.<init>(r12)
            goto L_0x0137
        L_0x0136:
            r6 = r3
        L_0x0137:
            com.reown.sign.engine.use_case.calls.GetNamespacesFromReCaps r9 = r0.getNamespacesFromReCaps
            java.util.List r10 = r44.getChains()
            r12 = r2
            java.util.Collection r12 = (java.util.Collection) r12
            if (r12 == 0) goto L_0x014b
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x0149
            goto L_0x014b
        L_0x0149:
            r12 = r2
            goto L_0x0151
        L_0x014b:
            java.lang.String r12 = "personal_sign"
            java.util.List r12 = kotlin.collections.CollectionsKt.listOf(r12)
        L_0x0151:
            java.util.Map r9 = r9.invoke(r10, r12)
            java.util.Map r9 = com.reown.sign.engine.model.mapper.EngineMapperKt.toMapOfEngineNamespacesOptional(r9)
            java.lang.String r10 = r43.getExternalReCapsJson(r44)
            java.lang.String r12 = r0.getSignReCapsJson(r2, r1)
            int r13 = r10.length()
            if (r13 <= 0) goto L_0x017c
            int r13 = r12.length()
            if (r13 <= 0) goto L_0x017c
            org.json.JSONObject r13 = new org.json.JSONObject
            r13.<init>(r12)
            org.json.JSONObject r14 = new org.json.JSONObject
            r14.<init>(r10)
            java.lang.String r13 = com.reown.android.internal.common.signing.cacao.UtilsKt.mergeReCaps(r13, r14)
            goto L_0x0185
        L_0x017c:
            int r13 = r12.length()
            if (r13 <= 0) goto L_0x0184
            r13 = r12
            goto L_0x0185
        L_0x0184:
            r13 = r10
        L_0x0185:
            java.lang.String r14 = "\\\\/"
            java.lang.String r15 = "/"
            java.lang.String r13 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r13, (java.lang.String) r14, (java.lang.String) r15, false, 4, (java.lang.Object) null)
            int r14 = r13.length()
            if (r14 <= 0) goto L_0x01c5
            java.nio.charset.Charset r14 = kotlin.text.Charsets.UTF_8
            byte[] r14 = r13.getBytes(r14)
            java.lang.String r15 = "getBytes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r15)
            r15 = 3
            java.lang.String r14 = android.util.Base64.encodeToString(r14, r15)
            java.lang.String r15 = "urn:recap:"
            java.lang.String r14 = androidx.browser.trusted.c.a(r15, r14)
            java.util.List r15 = r44.getResources()
            if (r15 != 0) goto L_0x01b7
            java.util.List r14 = kotlin.collections.CollectionsKt.listOf(r14)
        L_0x01b3:
            r1.setResources(r14)
            goto L_0x01c5
        L_0x01b7:
            java.util.List r15 = r44.getResources()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            java.util.Collection r15 = (java.util.Collection) r15
            java.util.List r14 = kotlin.collections.CollectionsKt.plus(r15, r14)
            goto L_0x01b3
        L_0x01c5:
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r14 = r0.crypto
            java.lang.String r14 = r14.m8724generateAndStoreX25519KeyPairuN_RPug()
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r15 = r0.crypto
            com.reown.foundation.common.model.PublicKey r11 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r14)
            com.reown.foundation.common.model.Topic r11 = r15.getTopicFromKey(r11)
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r15 = new com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams
            com.reown.sign.common.model.vo.clientsync.common.Requester r1 = new com.reown.sign.common.model.vo.clientsync.common.Requester
            com.reown.android.internal.common.model.AppMetaData r2 = r0.selfAppMetaData
            r1.<init>(r14, r2)
            com.reown.sign.common.model.vo.clientsync.common.PayloadParams r2 = com.reown.sign.engine.model.mapper.EngineMapperKt.toCommon(r44)
            r25 = r12
            r26 = r13
            long r12 = r6.getSeconds()
            r15.<init>(r1, r2, r12)
            com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate r1 = new com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate
            r20 = 0
            r21 = 0
            r18 = 0
            r23 = 7
            r24 = 0
            r17 = r1
            r22 = r15
            r17.<init>(r18, r20, r21, r22, r23, r24)
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r2 = r0.crypto
            com.reown.foundation.common.model.PublicKey r12 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r14)
            java.lang.String r13 = com.reown.android.internal.utils.ContextKt.getParticipantTag(r11)
            r2.setKey(r12, r13)
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r44)
            r7.L$0 = r2
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r45)
            r7.L$1 = r2
            r2 = r46
            r7.L$2 = r2
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r47)
            r7.L$3 = r12
            r7.L$4 = r4
            r12 = r49
            r7.L$5 = r12
            r7.L$6 = r5
            r7.L$7 = r6
            r7.L$8 = r9
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r7.L$9 = r13
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r25)
            r7.L$10 = r13
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r26)
            r7.L$11 = r13
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r7.L$12 = r13
            r7.L$13 = r11
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)
            r7.L$14 = r13
            r7.L$15 = r1
            r13 = 1
            r7.label = r13
            java.lang.Object r13 = r0.isLinkModeEnabled(r4, r7)
            if (r13 != r8) goto L_0x025b
            return r8
        L_0x025b:
            r16 = r8
            r17 = r10
            r10 = r4
            r8 = r5
            r5 = r1
            r4 = r3
            r1 = r44
            r3 = r2
            r2 = r45
            r42 = r13
            r13 = r6
            r6 = r42
        L_0x026d:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x034d
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface r6 = r0.linkModeJsonRpcInteractor     // Catch:{ Error -> 0x0347 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch:{ Error -> 0x0347 }
            r44 = r15
            com.reown.foundation.common.model.Topic r15 = new com.reown.foundation.common.model.Topic     // Catch:{ Error -> 0x0347 }
            r45 = r11
            java.lang.String r11 = r43.generateUUID()     // Catch:{ Error -> 0x0347 }
            r15.<init>(r11)     // Catch:{ Error -> 0x0347 }
            com.reown.android.internal.common.model.EnvelopeType r11 = com.reown.android.internal.common.model.EnvelopeType.TWO     // Catch:{ Error -> 0x0347 }
            r6.triggerRequest(r5, r15, r10, r11)     // Catch:{ Error -> 0x0347 }
            com.reown.android.pulse.domain.InsertEventUseCase r6 = r0.insertEventUseCase     // Catch:{ Error -> 0x0347 }
            com.reown.android.pulse.model.properties.Props r11 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Error -> 0x0347 }
            java.lang.String r15 = "SUCCESS"
            com.reown.android.internal.common.model.Tags r18 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_LINK_MODE     // Catch:{ Error -> 0x0347 }
            int r18 = r18.getId()     // Catch:{ Error -> 0x0347 }
            r19 = r6
            java.lang.String r6 = java.lang.String.valueOf(r18)     // Catch:{ Error -> 0x0347 }
            r46 = r14
            com.reown.android.pulse.model.properties.Properties r14 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Error -> 0x0347 }
            long r20 = r5.getId()     // Catch:{ Error -> 0x0347 }
            java.lang.Long r36 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r20)     // Catch:{ Error -> 0x0347 }
            r18 = r5
            java.lang.String r5 = r0.clientId     // Catch:{ Error -> 0x0347 }
            com.reown.android.pulse.model.Direction r20 = com.reown.android.pulse.model.Direction.SENT     // Catch:{ Error -> 0x0347 }
            java.lang.String r38 = r20.getState()     // Catch:{ Error -> 0x0347 }
            r35 = 0
            r39 = 0
            r40 = 2303(0x8ff, float:3.227E-42)
            r41 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r27 = r14
            r37 = r5
            r27.<init>(r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41)     // Catch:{ Error -> 0x0347 }
            r11.<init>(r15, r6, r14)     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)     // Catch:{ Error -> 0x0347 }
            r7.L$0 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Error -> 0x0347 }
            r7.L$1 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Error -> 0x0347 }
            r7.L$2 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Error -> 0x0347 }
            r7.L$3 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Error -> 0x0347 }
            r7.L$4 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Error -> 0x0347 }
            r7.L$5 = r1     // Catch:{ Error -> 0x0347 }
            r7.L$6 = r8     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Error -> 0x0347 }
            r7.L$7 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Error -> 0x0347 }
            r7.L$8 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)     // Catch:{ Error -> 0x0347 }
            r7.L$9 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r25)     // Catch:{ Error -> 0x0347 }
            r7.L$10 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r26)     // Catch:{ Error -> 0x0347 }
            r7.L$11 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r46)     // Catch:{ Error -> 0x0347 }
            r7.L$12 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r45)     // Catch:{ Error -> 0x0347 }
            r7.L$13 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r44)     // Catch:{ Error -> 0x0347 }
            r7.L$14 = r1     // Catch:{ Error -> 0x0347 }
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)     // Catch:{ Error -> 0x0347 }
            r7.L$15 = r1     // Catch:{ Error -> 0x0347 }
            r1 = 2
            r7.label = r1     // Catch:{ Error -> 0x0347 }
            r1 = r19
            java.lang.Object r1 = r1.invoke(r11, r7)     // Catch:{ Error -> 0x0347 }
            r2 = r16
            if (r1 != r2) goto L_0x033e
            return r2
        L_0x033e:
            r1 = r8
        L_0x033f:
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Error -> 0x007b }
            java.lang.String r2 = "Link Mode - Request triggered successfully"
            r0.log((java.lang.String) r2)     // Catch:{ Error -> 0x007b }
            goto L_0x03a4
        L_0x0347:
            r0 = move-exception
            r1 = r8
        L_0x0349:
            r1.invoke(r0)
            goto L_0x03a4
        L_0x034d:
            r18 = r5
            r45 = r11
            com.reown.sign.engine.use_case.calls.GetPairingForSessionAuthenticateUseCase r1 = r0.getPairingForSessionAuthenticate
            com.reown.android.Core$Model$Pairing r3 = r1.invoke(r3)
            com.reown.foundation.util.Logger r1 = r0.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Session authenticate subscribing on topic: "
            r2.<init>(r4)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.log((java.lang.String) r2)
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r1 = r0.jsonRpcInteractor
            com.reown.sign.engine.use_case.calls.a r2 = new com.reown.sign.engine.use_case.calls.a
            r4 = 8
            r2.<init>((com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase) r0, (com.reown.foundation.common.model.Topic) r11, (java.lang.Object) r3, (int) r4)
            com.reown.sign.engine.use_case.calls.a r4 = new com.reown.sign.engine.use_case.calls.a
            r5 = 9
            r4.<init>((com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase) r0, (com.reown.foundation.common.model.Topic) r11, (java.lang.Object) r8, (int) r5)
            r1.subscribe(r11, r2, r4)
            kotlinx.coroutines.CoroutineScope r14 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()
            com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4 r15 = new com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4
            r10 = 0
            r1 = r15
            r2 = r43
            r4 = r18
            r5 = r11
            r6 = r13
            r7 = r9
            r9 = r8
            r8 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r0 = 0
            r1 = 0
            r2 = 3
            r3 = 0
            r43 = r14
            r44 = r0
            r45 = r1
            r46 = r15
            r47 = r2
            r48 = r3
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r43, r44, r45, r46, r47, r48)
        L_0x03a4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase.authenticate(com.reown.sign.engine.model.EngineDO$Authenticate, java.util.List, java.lang.String, com.reown.android.internal.common.model.Expiry, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
