package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.signing.cacao.Issuer;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nFormatAuthenticateMessageUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FormatAuthenticateMessageUseCase.kt\ncom/reown/sign/engine/use_case/calls/FormatAuthenticateMessageUseCase$formatMessage$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,25:1\n1869#2,2:26\n*S KotlinDebug\n*F\n+ 1 FormatAuthenticateMessageUseCase.kt\ncom/reown/sign/engine/use_case/calls/FormatAuthenticateMessageUseCase$formatMessage$2\n*L\n14#1:26,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.FormatAuthenticateMessageUseCase$formatMessage$2", f = "FormatAuthenticateMessageUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class FormatAuthenticateMessageUseCase$formatMessage$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ String $iss;
    final /* synthetic */ EngineDO.PayloadParams $payloadParams;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FormatAuthenticateMessageUseCase$formatMessage$2(String str, EngineDO.PayloadParams payloadParams, Continuation<? super FormatAuthenticateMessageUseCase$formatMessage$2> continuation) {
        super(2, continuation);
        this.$iss = str;
        this.$payloadParams = payloadParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormatAuthenticateMessageUseCase$formatMessage$2(this.$iss, this.$payloadParams, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Issuer issuer = new Issuer(this.$iss);
            if (this.$payloadParams.getChains().contains(issuer.getChainId())) {
                for (String isChainIdCAIP2Compliant : this.$payloadParams.getChains()) {
                    if (!CoreValidator.INSTANCE.isChainIdCAIP2Compliant(isChainIdCAIP2Compliant)) {
                        throw new Exception(MessagesKt.NAMESPACE_CHAINS_CAIP_2_MESSAGE);
                    }
                }
                CoreValidator coreValidator = CoreValidator.INSTANCE;
                if (!coreValidator.isChainIdCAIP2Compliant(issuer.getChainId())) {
                    throw new Exception("Issuer chainId is not CAIP-2 compliant");
                } else if (coreValidator.isAccountIdCAIP10Compliant(issuer.getAccountId())) {
                    return EngineMapperKt.toCAIP222Message(this.$payloadParams, issuer, "Ethereum");
                } else {
                    throw new Exception("Issuer address is not CAIP-10 compliant");
                }
            } else {
                throw new Exception("Issuer chainId does not match with PayloadParams");
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((FormatAuthenticateMessageUseCase$formatMessage$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
