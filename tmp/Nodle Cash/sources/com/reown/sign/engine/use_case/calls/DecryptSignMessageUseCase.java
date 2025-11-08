package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.android.internal.common.crypto.codec.Codec;
import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.push.notifications.DecryptMessageUseCaseInterface;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.utils.ExtensionsKt;
import com.reown.sign.common.model.vo.clientsync.common.PayloadParams;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJF\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r0\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u0012H@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/DecryptSignMessageUseCase;", "Lcom/reown/android/push/notifications/DecryptMessageUseCaseInterface;", "codec", "Lcom/reown/android/internal/common/crypto/codec/Codec;", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "metadataRepository", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "pushMessageStorage", "Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "<init>", "(Lcom/reown/android/internal/common/crypto/codec/Codec;Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;)V", "decryptNotification", "", "topic", "", "message", "onSuccess", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Message;", "onFailure", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDecryptSignMessageUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DecryptSignMessageUseCase.kt\ncom/reown/sign/engine/use_case/calls/DecryptSignMessageUseCase\n+ 2 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,92:1\n56#2:93\n1#3:94\n*S KotlinDebug\n*F\n+ 1 DecryptSignMessageUseCase.kt\ncom/reown/sign/engine/use_case/calls/DecryptSignMessageUseCase\n*L\n32#1:93\n32#1:94\n*E\n"})
public final class DecryptSignMessageUseCase implements DecryptMessageUseCaseInterface {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final Codec codec;
    @NotNull
    private final MetadataStorageRepositoryInterface metadataRepository;
    @NotNull
    private final PushMessagesRepository pushMessageStorage;
    @NotNull
    private final JsonRpcSerializer serializer;

    @SourceDebugExtension({"SMAP\nDecryptSignMessageUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DecryptSignMessageUseCase.kt\ncom/reown/sign/engine/use_case/calls/DecryptSignMessageUseCase$Companion\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,92:1\n463#2:93\n413#2:94\n1252#3,4:95\n*S KotlinDebug\n*F\n+ 1 DecryptSignMessageUseCase.kt\ncom/reown/sign/engine/use_case/calls/DecryptSignMessageUseCase$Companion\n*L\n90#1:93\n90#1:94\n90#1:95,4\n*E\n"})
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\"\u0010\u0004\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\"\u0010\u0004\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013J\"\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00150\u0014*\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00160\u0014¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/DecryptSignMessageUseCase$Companion;", "", "<init>", "()V", "toCore", "Lcom/reown/android/Core$Model$Message$SessionProposal;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "id", "", "topic", "", "Lcom/reown/android/Core$Model$Message$SessionRequest;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "metaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "toClient", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;", "Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "", "Lcom/reown/android/Core$Model$Namespace$Proposal;", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Core.Model.Message.SessionAuthenticate.PayloadParams toClient(@NotNull PayloadParams payloadParams) {
            Intrinsics.checkNotNullParameter(payloadParams, "<this>");
            return new Core.Model.Message.SessionAuthenticate.PayloadParams(payloadParams.getChains(), payloadParams.getDomain(), payloadParams.getNonce(), payloadParams.getAud(), payloadParams.getType(), payloadParams.getNbf(), payloadParams.getExp(), payloadParams.getStatement(), payloadParams.getRequestId(), payloadParams.getResources(), payloadParams.getIat());
        }

        @NotNull
        public final Core.Model.Message.SessionProposal toCore(@NotNull SignParams.SessionProposeParams sessionProposeParams, long j2, @NotNull String str) {
            String str2;
            Intrinsics.checkNotNullParameter(sessionProposeParams, "<this>");
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            String name = sessionProposeParams.getProposer().getMetadata().getName();
            String description = sessionProposeParams.getProposer().getMetadata().getDescription();
            String url = sessionProposeParams.getProposer().getMetadata().getUrl();
            List<String> icons = sessionProposeParams.getProposer().getMetadata().getIcons();
            Redirect redirect = sessionProposeParams.getProposer().getMetadata().getRedirect();
            if (redirect == null || (str2 = redirect.getNative()) == null) {
                str2 = "";
            }
            String str3 = str2;
            Map<String, Core.Model.Namespace.Proposal> core = toCore(sessionProposeParams.getRequiredNamespaces());
            Map<String, Namespace.Proposal> optionalNamespaces = sessionProposeParams.getOptionalNamespaces();
            if (optionalNamespaces == null) {
                optionalNamespaces = MapsKt.emptyMap();
            }
            return new Core.Model.Message.SessionProposal(j2, str, name, description, url, icons, str3, core, toCore(optionalNamespaces), sessionProposeParams.getProperties(), sessionProposeParams.getProposer().getPublicKey(), ((RelayProtocolOptions) CollectionsKt.first(sessionProposeParams.getRelays())).getProtocol(), ((RelayProtocolOptions) CollectionsKt.first(sessionProposeParams.getRelays())).getData());
        }

        private Companion() {
        }

        @NotNull
        public final Core.Model.Message.SessionRequest toCore(@NotNull SignParams.SessionRequestParams sessionRequestParams, long j2, @NotNull String str, @NotNull AppMetaData appMetaData) {
            Intrinsics.checkNotNullParameter(sessionRequestParams, "<this>");
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(appMetaData, "metaData");
            return new Core.Model.Message.SessionRequest(str, sessionRequestParams.getChainId(), ExtensionsKt.toClient(appMetaData), new Core.Model.Message.SessionRequest.JSONRPCRequest(j2, sessionRequestParams.getRequest().getMethod(), sessionRequestParams.getRequest().getParams()));
        }

        @NotNull
        public final Core.Model.Message.SessionAuthenticate toCore(@NotNull SignParams.SessionAuthenticateParams sessionAuthenticateParams, long j2, @NotNull String str, @NotNull AppMetaData appMetaData) {
            Intrinsics.checkNotNullParameter(sessionAuthenticateParams, "<this>");
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(appMetaData, "metaData");
            return new Core.Model.Message.SessionAuthenticate(j2, str, ExtensionsKt.toClient(appMetaData), toClient(sessionAuthenticateParams.getAuthPayload()), sessionAuthenticateParams.getExpiryTimestamp());
        }

        @NotNull
        public final Map<String, Core.Model.Namespace.Proposal> toCore(@NotNull Map<String, Namespace.Proposal> map) {
            Intrinsics.checkNotNullParameter(map, "<this>");
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Namespace.Proposal proposal = (Namespace.Proposal) entry.getValue();
                linkedHashMap.put(key, new Core.Model.Namespace.Proposal(proposal.getChains(), proposal.getMethods(), proposal.getEvents()));
            }
            return linkedHashMap;
        }
    }

    public DecryptSignMessageUseCase(@NotNull Codec codec2, @NotNull JsonRpcSerializer jsonRpcSerializer, @NotNull MetadataStorageRepositoryInterface metadataStorageRepositoryInterface, @NotNull PushMessagesRepository pushMessagesRepository) {
        Intrinsics.checkNotNullParameter(codec2, "codec");
        Intrinsics.checkNotNullParameter(jsonRpcSerializer, "serializer");
        Intrinsics.checkNotNullParameter(metadataStorageRepositoryInterface, "metadataRepository");
        Intrinsics.checkNotNullParameter(pushMessagesRepository, "pushMessageStorage");
        this.codec = codec2;
        this.serializer = jsonRpcSerializer;
        this.metadataRepository = metadataStorageRepositoryInterface;
        this.pushMessageStorage = pushMessagesRepository;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: kotlin.jvm.functions.Function1<? super com.reown.android.Core$Model$Message, kotlin.Unit>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0075 A[Catch:{ Exception -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object decryptNotification(@org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull java.lang.String r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.reown.android.Core.Model.Message, kotlin.Unit> r11, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r8 = this;
            boolean r0 = r13 instanceof com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$decryptNotification$1
            if (r0 == 0) goto L_0x0013
            r0 = r13
            com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$decryptNotification$1 r0 = (com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$decryptNotification$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$decryptNotification$1 r0 = new com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$decryptNotification$1
            r0.<init>(r8, r13)
        L_0x0018:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r9 = r0.L$3
            r12 = r9
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r9 = r0.L$2
            r11 = r9
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r9 = r0.L$1
            r10 = r9
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Exception -> 0x003d }
        L_0x003b:
            r6 = r9
            goto L_0x006d
        L_0x003d:
            r8 = move-exception
            goto L_0x0139
        L_0x0040:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r13)
            com.reown.android.internal.common.storage.push_messages.PushMessagesRepository r13 = r8.pushMessageStorage     // Catch:{ Exception -> 0x003d }
            java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8     // Catch:{ Exception -> 0x003d }
            byte[] r2 = r10.getBytes(r2)     // Catch:{ Exception -> 0x003d }
            java.lang.String r4 = "getBytes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ Exception -> 0x003d }
            java.lang.String r2 = com.reown.android.internal.common.crypto.UtilsKt.sha256(r2)     // Catch:{ Exception -> 0x003d }
            r0.L$0 = r9     // Catch:{ Exception -> 0x003d }
            r0.L$1 = r10     // Catch:{ Exception -> 0x003d }
            r0.L$2 = r11     // Catch:{ Exception -> 0x003d }
            r0.L$3 = r12     // Catch:{ Exception -> 0x003d }
            r0.label = r3     // Catch:{ Exception -> 0x003d }
            java.lang.Object r13 = r13.doesPushMessageExist(r2, r0)     // Catch:{ Exception -> 0x003d }
            if (r13 != r1) goto L_0x003b
            return r1
        L_0x006d:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Exception -> 0x003d }
            boolean r9 = r13.booleanValue()     // Catch:{ Exception -> 0x003d }
            if (r9 != 0) goto L_0x013c
            com.reown.android.internal.common.crypto.codec.Codec r9 = r8.codec     // Catch:{ Exception -> 0x003d }
            com.reown.foundation.common.model.Topic r13 = new com.reown.foundation.common.model.Topic     // Catch:{ Exception -> 0x003d }
            r13.<init>(r6)     // Catch:{ Exception -> 0x003d }
            byte[] r10 = org.bouncycastle.util.encoders.Base64.decode((java.lang.String) r10)     // Catch:{ Exception -> 0x003d }
            java.lang.String r0 = "decode(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)     // Catch:{ Exception -> 0x003d }
            java.lang.String r9 = r9.decrypt(r13, r10)     // Catch:{ Exception -> 0x003d }
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r10 = r8.serializer     // Catch:{ Exception -> 0x003d }
            kotlin.Result$Companion r13 = kotlin.Result.Companion     // Catch:{ all -> 0x00a0 }
            com.squareup.moshi.Moshi r10 = r10.getMoshi()     // Catch:{ all -> 0x00a0 }
            java.lang.Class<com.reown.android.internal.common.model.sync.ClientJsonRpc> r13 = com.reown.android.internal.common.model.sync.ClientJsonRpc.class
            com.squareup.moshi.JsonAdapter r10 = r10.adapter(r13)     // Catch:{ all -> 0x00a0 }
            java.lang.Object r10 = r10.fromJson((java.lang.String) r9)     // Catch:{ all -> 0x00a0 }
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)     // Catch:{ all -> 0x00a0 }
            goto L_0x00ab
        L_0x00a0:
            r10 = move-exception
            kotlin.Result$Companion r13 = kotlin.Result.Companion     // Catch:{ Exception -> 0x003d }
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)     // Catch:{ Exception -> 0x003d }
            java.lang.Object r10 = kotlin.Result.m8979constructorimpl(r10)     // Catch:{ Exception -> 0x003d }
        L_0x00ab:
            boolean r13 = kotlin.Result.m8985isFailureimpl(r10)     // Catch:{ Exception -> 0x003d }
            if (r13 == 0) goto L_0x00b2
            r10 = 0
        L_0x00b2:
            com.reown.android.internal.common.model.sync.ClientJsonRpc r10 = (com.reown.android.internal.common.model.sync.ClientJsonRpc) r10     // Catch:{ Exception -> 0x003d }
            if (r10 != 0) goto L_0x00c1
            com.reown.sign.common.exceptions.InvalidSignParamsType r8 = new com.reown.sign.common.exceptions.InvalidSignParamsType     // Catch:{ Exception -> 0x003d }
            r8.<init>()     // Catch:{ Exception -> 0x003d }
            r12.invoke(r8)     // Catch:{ Exception -> 0x003d }
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003d }
            return r8
        L_0x00c1:
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r13 = r8.serializer     // Catch:{ Exception -> 0x003d }
            java.lang.String r0 = r10.getMethod()     // Catch:{ Exception -> 0x003d }
            com.reown.android.internal.common.model.type.ClientParams r9 = r13.deserialize(r0, r9)     // Catch:{ Exception -> 0x003d }
            if (r9 != 0) goto L_0x00d8
            com.reown.sign.common.exceptions.InvalidSignParamsType r8 = new com.reown.sign.common.exceptions.InvalidSignParamsType     // Catch:{ Exception -> 0x003d }
            r8.<init>()     // Catch:{ Exception -> 0x003d }
            r12.invoke(r8)     // Catch:{ Exception -> 0x003d }
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003d }
            return r8
        L_0x00d8:
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r8 = r8.metadataRepository     // Catch:{ Exception -> 0x003d }
            com.reown.foundation.common.model.Topic r13 = new com.reown.foundation.common.model.Topic     // Catch:{ Exception -> 0x003d }
            r13.<init>(r6)     // Catch:{ Exception -> 0x003d }
            com.reown.android.internal.common.model.AppMetaDataType r0 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x003d }
            com.reown.android.internal.common.model.AppMetaData r7 = r8.getByTopicAndType(r13, r0)     // Catch:{ Exception -> 0x003d }
            if (r7 != 0) goto L_0x00f2
            com.reown.sign.common.exceptions.InvalidSignParamsType r8 = new com.reown.sign.common.exceptions.InvalidSignParamsType     // Catch:{ Exception -> 0x003d }
            r8.<init>()     // Catch:{ Exception -> 0x003d }
            r12.invoke(r8)     // Catch:{ Exception -> 0x003d }
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x003d }
            return r8
        L_0x00f2:
            boolean r8 = r9 instanceof com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionProposeParams     // Catch:{ Exception -> 0x003d }
            if (r8 == 0) goto L_0x0106
            com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$Companion r8 = Companion     // Catch:{ Exception -> 0x003d }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r9 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionProposeParams) r9     // Catch:{ Exception -> 0x003d }
            long r0 = r10.getId()     // Catch:{ Exception -> 0x003d }
            com.reown.android.Core$Model$Message$SessionProposal r8 = r8.toCore(r9, r0, r6)     // Catch:{ Exception -> 0x003d }
            r11.invoke(r8)     // Catch:{ Exception -> 0x003d }
            goto L_0x013c
        L_0x0106:
            boolean r8 = r9 instanceof com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams     // Catch:{ Exception -> 0x003d }
            if (r8 == 0) goto L_0x011b
            com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$Companion r2 = Companion     // Catch:{ Exception -> 0x003d }
            r3 = r9
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionRequestParams r3 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams) r3     // Catch:{ Exception -> 0x003d }
            long r4 = r10.getId()     // Catch:{ Exception -> 0x003d }
            com.reown.android.Core$Model$Message$SessionRequest r8 = r2.toCore((com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionRequestParams) r3, (long) r4, (java.lang.String) r6, (com.reown.android.internal.common.model.AppMetaData) r7)     // Catch:{ Exception -> 0x003d }
            r11.invoke(r8)     // Catch:{ Exception -> 0x003d }
            goto L_0x013c
        L_0x011b:
            boolean r8 = r9 instanceof com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams     // Catch:{ Exception -> 0x003d }
            if (r8 == 0) goto L_0x0130
            com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase$Companion r2 = Companion     // Catch:{ Exception -> 0x003d }
            r3 = r9
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r3 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r3     // Catch:{ Exception -> 0x003d }
            long r4 = r10.getId()     // Catch:{ Exception -> 0x003d }
            com.reown.android.Core$Model$Message$SessionAuthenticate r8 = r2.toCore((com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r3, (long) r4, (java.lang.String) r6, (com.reown.android.internal.common.model.AppMetaData) r7)     // Catch:{ Exception -> 0x003d }
            r11.invoke(r8)     // Catch:{ Exception -> 0x003d }
            goto L_0x013c
        L_0x0130:
            com.reown.sign.common.exceptions.InvalidSignParamsType r8 = new com.reown.sign.common.exceptions.InvalidSignParamsType     // Catch:{ Exception -> 0x003d }
            r8.<init>()     // Catch:{ Exception -> 0x003d }
            r12.invoke(r8)     // Catch:{ Exception -> 0x003d }
            goto L_0x013c
        L_0x0139:
            r12.invoke(r8)
        L_0x013c:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase.decryptNotification(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
