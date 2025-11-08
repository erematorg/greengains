package com.reown.sign.client;

import com.reown.android.relay.WSSConnectionState;
import com.reown.sign.client.Sign;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "connectionState", "Lcom/reown/android/relay/WSSConnectionState;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$handleConnectionState$1", f = "SignProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$handleConnectionState$1 extends SuspendLambda implements Function2<WSSConnectionState, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Sign.Model.ConnectionState, Unit> $onDelegate;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$handleConnectionState$1(SignProtocol signProtocol, Function1<? super Sign.Model.ConnectionState, Unit> function1, Continuation<? super SignProtocol$handleConnectionState$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$onDelegate = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignProtocol$handleConnectionState$1 signProtocol$handleConnectionState$1 = new SignProtocol$handleConnectionState$1(this.this$0, this.$onDelegate, continuation);
        signProtocol$handleConnectionState$1.L$0 = obj;
        return signProtocol$handleConnectionState$1;
    }

    public final Object invoke(WSSConnectionState wSSConnectionState, Continuation<? super Unit> continuation) {
        return ((SignProtocol$handleConnectionState$1) create(wSSConnectionState, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        WSSConnectionState wSSConnectionState = (WSSConnectionState) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String str = "Connection closed";
            if (this.this$0.atomicBoolean == null) {
                this.this$0.atomicBoolean = new AtomicBoolean();
                if (wSSConnectionState instanceof WSSConnectionState.Disconnected.ConnectionFailed) {
                    this.$onDelegate.invoke(new Sign.Model.ConnectionState(false, new Sign.Model.ConnectionState.Reason.ConnectionFailed(((WSSConnectionState.Disconnected.ConnectionFailed) wSSConnectionState).getThrowable())));
                } else if (wSSConnectionState instanceof WSSConnectionState.Disconnected.ConnectionClosed) {
                    Function1<Sign.Model.ConnectionState, Unit> function1 = this.$onDelegate;
                    String message = ((WSSConnectionState.Disconnected.ConnectionClosed) wSSConnectionState).getMessage();
                    if (message != null) {
                        str = message;
                    }
                    function1.invoke(new Sign.Model.ConnectionState(false, new Sign.Model.ConnectionState.Reason.ConnectionClosed(str)));
                } else {
                    this.$onDelegate.invoke(new Sign.Model.ConnectionState(true, (Sign.Model.ConnectionState.Reason) null, 2, (DefaultConstructorMarker) null));
                }
            } else {
                AtomicBoolean access$getAtomicBoolean$p = this.this$0.atomicBoolean;
                if (access$getAtomicBoolean$p == null || !access$getAtomicBoolean$p.get() || !(wSSConnectionState instanceof WSSConnectionState.Disconnected.ConnectionFailed)) {
                    AtomicBoolean access$getAtomicBoolean$p2 = this.this$0.atomicBoolean;
                    if (access$getAtomicBoolean$p2 == null || !access$getAtomicBoolean$p2.get() || !(wSSConnectionState instanceof WSSConnectionState.Disconnected.ConnectionClosed)) {
                        AtomicBoolean access$getAtomicBoolean$p3 = this.this$0.atomicBoolean;
                        if (access$getAtomicBoolean$p3 != null && !access$getAtomicBoolean$p3.get() && (wSSConnectionState instanceof WSSConnectionState.Connected)) {
                            AtomicBoolean access$getAtomicBoolean$p4 = this.this$0.atomicBoolean;
                            if (access$getAtomicBoolean$p4 != null) {
                                access$getAtomicBoolean$p4.set(true);
                            }
                            this.$onDelegate.invoke(new Sign.Model.ConnectionState(true, (Sign.Model.ConnectionState.Reason) null, 2, (DefaultConstructorMarker) null));
                        }
                    } else {
                        AtomicBoolean access$getAtomicBoolean$p5 = this.this$0.atomicBoolean;
                        if (access$getAtomicBoolean$p5 != null) {
                            access$getAtomicBoolean$p5.set(false);
                        }
                        Function1<Sign.Model.ConnectionState, Unit> function12 = this.$onDelegate;
                        String message2 = ((WSSConnectionState.Disconnected.ConnectionClosed) wSSConnectionState).getMessage();
                        if (message2 != null) {
                            str = message2;
                        }
                        function12.invoke(new Sign.Model.ConnectionState(false, new Sign.Model.ConnectionState.Reason.ConnectionClosed(str)));
                    }
                } else {
                    AtomicBoolean access$getAtomicBoolean$p6 = this.this$0.atomicBoolean;
                    if (access$getAtomicBoolean$p6 != null) {
                        access$getAtomicBoolean$p6.set(false);
                    }
                    this.$onDelegate.invoke(new Sign.Model.ConnectionState(false, new Sign.Model.ConnectionState.Reason.ConnectionFailed(((WSSConnectionState.Disconnected.ConnectionFailed) wSSConnectionState).getThrowable())));
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
