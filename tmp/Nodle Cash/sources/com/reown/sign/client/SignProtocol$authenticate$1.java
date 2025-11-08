package com.reown.sign.client;

import com.reown.android.internal.common.model.Expiry;
import com.reown.sign.client.Sign;
import com.reown.sign.client.mapper.ClientMapperKt;
import com.reown.sign.engine.domain.SignEngine;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$authenticate$1", f = "SignProtocol.kt", i = {}, l = {182}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$authenticate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sign.Params.Authenticate $authenticate;
    final /* synthetic */ Function1<Sign.Model.Error, Unit> $onError;
    final /* synthetic */ Function1<String, Unit> $onSuccess;
    final /* synthetic */ String $walletAppLink;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$authenticate$1(SignProtocol signProtocol, Sign.Params.Authenticate authenticate, String str, Function1<? super Sign.Model.Error, Unit> function1, Function1<? super String, Unit> function12, Continuation<? super SignProtocol$authenticate$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$authenticate = authenticate;
        this.$walletAppLink = str;
        this.$onError = function1;
        this.$onSuccess = function12;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(Function1 function1, String str) {
        function1.invoke(str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Function1 function1, Throwable th) {
        function1.invoke(new Sign.Model.Error(th));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$authenticate$1(this.this$0, this.$authenticate, this.$walletAppLink, this.$onError, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SignEngine access$getSignEngine$p = this.this$0.signEngine;
            Expiry expiry = null;
            if (access$getSignEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                access$getSignEngine$p = null;
            }
            EngineDO.Authenticate authenticate = ClientMapperKt.toAuthenticate(this.$authenticate);
            List<String> methods = this.$authenticate.getMethods();
            String pairingTopic = this.$authenticate.getPairingTopic();
            if (this.$authenticate.getExpiry() != null) {
                expiry = new Expiry(this.$authenticate.getExpiry().longValue());
            }
            Expiry expiry2 = expiry;
            String str = this.$walletAppLink;
            d dVar = new d(this.$onSuccess, 2);
            d dVar2 = new d(this.$onError, 3);
            this.label = 1;
            if (access$getSignEngine$p.authenticate(authenticate, methods, pairingTopic, expiry2, str, dVar, dVar2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
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
        return ((SignProtocol$authenticate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
