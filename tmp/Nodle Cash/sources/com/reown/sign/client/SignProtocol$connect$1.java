package com.reown.sign.client;

import com.reown.android.internal.common.model.Pairing;
import com.reown.android.pairing.model.mapper.PairingMapperKt;
import com.reown.sign.client.Sign;
import com.reown.sign.client.mapper.ClientMapperKt;
import com.reown.sign.engine.domain.SignEngine;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$connect$1", f = "SignProtocol.kt", i = {0, 0}, l = {130}, m = "invokeSuspend", n = {"$this$invokeSuspend_u24lambda_u242", "$i$a$-with-SignProtocol$connect$1$1"}, s = {"L$0", "I$0"})
public final class SignProtocol$connect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sign.Params.Connect $connect;
    final /* synthetic */ Function1<Sign.Model.Error, Unit> $onError;
    final /* synthetic */ Function1<String, Unit> $onSuccess;
    int I$0;
    Object L$0;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$connect$1(Sign.Params.Connect connect, Function1<? super Sign.Model.Error, Unit> function1, SignProtocol signProtocol, Function1<? super String, Unit> function12, Continuation<? super SignProtocol$connect$1> continuation) {
        super(2, continuation);
        this.$connect = connect;
        this.$onError = function1;
        this.this$0 = signProtocol;
        this.$onSuccess = function12;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2$lambda$0(Function1 function1, Sign.Params.Connect connect) {
        function1.invoke(connect.getPairing().getUri());
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2$lambda$1(Function1 function1, Throwable th) {
        function1.invoke(new Sign.Model.Error(th));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$connect$1(this.$connect, this.$onError, this.this$0, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Sign.Params.Connect connect = this.$connect;
            SignProtocol signProtocol = this.this$0;
            Function1<String, Unit> function1 = this.$onSuccess;
            Function1<Sign.Model.Error, Unit> function12 = this.$onError;
            SignEngine access$getSignEngine$p = signProtocol.signEngine;
            Map map = null;
            if (access$getSignEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                access$getSignEngine$p = null;
            }
            Map<String, Sign.Model.Namespace.Proposal> namespaces = connect.getNamespaces();
            Map mapOfEngineNamespacesRequired = namespaces != null ? ClientMapperKt.toMapOfEngineNamespacesRequired(namespaces) : null;
            Map<String, Sign.Model.Namespace.Proposal> optionalNamespaces = connect.getOptionalNamespaces();
            if (optionalNamespaces != null) {
                map = ClientMapperKt.toMapOfEngineNamespacesOptional(optionalNamespaces);
            }
            Map<String, String> properties = connect.getProperties();
            Map<String, String> scopedProperties = connect.getScopedProperties();
            Pairing pairing = PairingMapperKt.toPairing(connect.getPairing());
            c cVar = new c(function1, connect, 2);
            d dVar = new d(function12, 4);
            this.L$0 = SpillingKt.nullOutSpilledVariable(connect);
            this.I$0 = 0;
            this.label = 1;
            if (access$getSignEngine$p.proposeSession(mapOfEngineNamespacesRequired, map, properties, scopedProperties, pairing, cVar, dVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            Sign.Params.Connect connect2 = (Sign.Params.Connect) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                this.$onError.invoke(new Sign.Model.Error(e3));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SignProtocol$connect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
