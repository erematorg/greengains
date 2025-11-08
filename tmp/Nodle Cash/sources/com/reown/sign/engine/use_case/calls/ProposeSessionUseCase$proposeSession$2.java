package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.utils.Expiration;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import com.reown.sign.engine.use_case.utils.NamespaceMerger;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nProposeSessionUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProposeSessionUseCase.kt\ncom/reown/sign/engine/use_case/calls/ProposeSessionUseCase$proposeSession$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,116:1\n1#2:117\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.ProposeSessionUseCase$proposeSession$2", f = "ProposeSessionUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ProposeSessionUseCase$proposeSession$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ Map<String, EngineDO.Namespace.Proposal> $optionalNamespaces;
    final /* synthetic */ Pairing $pairing;
    final /* synthetic */ Map<String, String> $properties;
    final /* synthetic */ Map<String, EngineDO.Namespace.Proposal> $requiredNamespaces;
    final /* synthetic */ Map<String, String> $scopedProperties;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ProposeSessionUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProposeSessionUseCase$proposeSession$2(Pairing pairing, Map<String, EngineDO.Namespace.Proposal> map, Map<String, EngineDO.Namespace.Proposal> map2, ProposeSessionUseCase proposeSessionUseCase, Map<String, String> map3, Map<String, String> map4, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, Continuation<? super ProposeSessionUseCase$proposeSession$2> continuation) {
        super(2, continuation);
        this.$pairing = pairing;
        this.$requiredNamespaces = map;
        this.$optionalNamespaces = map2;
        this.this$0 = proposeSessionUseCase;
        this.$properties = map3;
        this.$scopedProperties = map4;
        this.$onSuccess = function0;
        this.$onFailure = function1;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3$lambda$1(ProposeSessionUseCase proposeSessionUseCase, Pairing pairing, Function0 function0) {
        Logger access$getLogger$p = proposeSessionUseCase.logger;
        Topic topic = pairing.getTopic();
        access$getLogger$p.log("Session proposal sent successfully, topic: " + topic);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3$lambda$2(ProposeSessionUseCase proposeSessionUseCase, Function1 function1, Throwable th) {
        Logger access$getLogger$p = proposeSessionUseCase.logger;
        access$getLogger$p.error("Failed to send a session proposal: " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProposeSessionUseCase$proposeSession$2 proposeSessionUseCase$proposeSession$2 = new ProposeSessionUseCase$proposeSession$2(this.$pairing, this.$requiredNamespaces, this.$optionalNamespaces, this.this$0, this.$properties, this.$scopedProperties, this.$onSuccess, this.$onFailure, continuation);
        proposeSessionUseCase$proposeSession$2.L$0 = obj;
        return proposeSessionUseCase$proposeSession$2;
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            RelayProtocolOptions relayProtocolOptions = new RelayProtocolOptions(this.$pairing.getRelayProtocol(), this.$pairing.getRelayData());
            Map<String, EngineDO.Namespace.Proposal> merge = NamespaceMerger.INSTANCE.merge(this.$requiredNamespaces, this.$optionalNamespaces);
            ProposeSessionUseCase proposeSessionUseCase = this.this$0;
            Map<String, String> map = this.$properties;
            try {
                Result.Companion companion = Result.Companion;
                proposeSessionUseCase.validate((Map<String, EngineDO.Namespace.Proposal>) null, merge, map);
                obj2 = Result.m8979constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
            }
            ProposeSessionUseCase proposeSessionUseCase2 = this.this$0;
            Map<String, String> map2 = this.$properties;
            Map<String, String> map3 = this.$scopedProperties;
            Pairing pairing = this.$pairing;
            Function0<Unit> function0 = this.$onSuccess;
            Function1<Throwable, Unit> function1 = this.$onFailure;
            Throwable r5 = Result.m8982exceptionOrNullimpl(obj2);
            if (r5 == null) {
                Unit unit = (Unit) obj2;
                Expiry expiry = new Expiry(Expiration.getPROPOSAL_EXPIRY());
                String r10 = proposeSessionUseCase2.crypto.m8724generateAndStoreX25519KeyPairuN_RPug();
                List listOf = CollectionsKt.listOf(relayProtocolOptions);
                Map emptyMap = MapsKt.emptyMap();
                if (merge == null) {
                    merge = MapsKt.emptyMap();
                }
                SignParams.SessionProposeParams r02 = EngineMapperKt.m8884toSessionProposeParamsn2MeESo(listOf, emptyMap, merge, map2, map3, r10, proposeSessionUseCase2.selfAppMetaData, expiry);
                SignRpc.SessionPropose sessionPropose = new SignRpc.SessionPropose(0, (String) null, (String) null, r02, 7, (DefaultConstructorMarker) null);
                proposeSessionUseCase2.proposalStorageRepository.insertProposal$sign_release(EngineMapperKt.toVO(r02, pairing.getTopic(), sessionPropose.getId()));
                Logger access$getLogger$p = proposeSessionUseCase2.logger;
                String value = pairing.getTopic().getValue();
                access$getLogger$p.log("Sending proposal on topic: " + value);
                proposeSessionUseCase2.jsonRpcInteractor.proposeSession(pairing.getTopic(), sessionPropose, new h(proposeSessionUseCase2, pairing, function0, 3), new k(proposeSessionUseCase2, function1, 0));
            } else {
                Logger access$getLogger$p2 = proposeSessionUseCase2.logger;
                access$getLogger$p2.error("Failed to validate session proposal: " + r5);
                function1.invoke(r5);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ProposeSessionUseCase$proposeSession$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
