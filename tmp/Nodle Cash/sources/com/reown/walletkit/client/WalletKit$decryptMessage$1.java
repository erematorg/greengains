package com.reown.walletkit.client;

import com.reown.sign.client.Sign;
import com.reown.sign.client.SignClient;
import com.reown.walletkit.client.Wallet;
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
@DebugMetadata(c = "com.reown.walletkit.client.WalletKit$decryptMessage$1", f = "WalletKit.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class WalletKit$decryptMessage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Wallet.Model.Error, Unit> $onError;
    final /* synthetic */ Function1<Wallet.Model.Message, Unit> $onSuccess;
    final /* synthetic */ Wallet.Params.DecryptMessage $params;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WalletKit$decryptMessage$1(Wallet.Params.DecryptMessage decryptMessage, Function1<? super Wallet.Model.Message, Unit> function1, Function1<? super Wallet.Model.Error, Unit> function12, Continuation<? super WalletKit$decryptMessage$1> continuation) {
        super(2, continuation);
        this.$params = decryptMessage;
        this.$onSuccess = function1;
        this.$onError = function12;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(Function1 function1, Sign.Model.Message message) {
        if (message instanceof Sign.Model.Message.SessionRequest) {
            function1.invoke(Intrinsics.checkNotNullParameter((Sign.Model.Message.SessionRequest) message, "<this>"));
        } else if (message instanceof Sign.Model.Message.SessionProposal) {
            function1.invoke(Intrinsics.checkNotNullParameter((Sign.Model.Message.SessionProposal) message, "<this>"));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Function1 function1, Sign.Model.Error error) {
        function1.invoke(new Wallet.Model.Error(error.getThrowable()));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WalletKit$decryptMessage$1(this.$params, this.$onSuccess, this.$onError, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SignClient.INSTANCE.decryptMessage(new Sign.Params.DecryptMessage(this.$params.getTopic(), this.$params.getEncryptedMessage()), new a(this.$onSuccess, 0), new a(this.$onError, 1));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WalletKit$decryptMessage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
