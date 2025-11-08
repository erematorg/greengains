package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.PingUseCase$ping$2", f = "PingUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class PingUseCase$ping$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function1<String, Unit> $onSuccess;
    final /* synthetic */ long $timeout;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ PingUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PingUseCase$ping$2(PingUseCase pingUseCase, String str, Function1<? super Throwable, Unit> function1, long j2, Function1<? super String, Unit> function12, Continuation<? super PingUseCase$ping$2> continuation) {
        super(2, continuation);
        this.this$0 = pingUseCase;
        this.$topic = str;
        this.$onFailure = function1;
        this.$timeout = j2;
        this.$onSuccess = function12;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(PingUseCase pingUseCase, String str, long j2, SignRpc.SessionPing sessionPing, Function1 function1, Function1 function12) {
        Logger access$getLogger$p = pingUseCase.logger;
        access$getLogger$p.log("Ping sent successfully, topic: " + str);
        pingUseCase.m8886onPingSuccessgRj5Bb8(j2, sessionPing, function1, str, function12);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(PingUseCase pingUseCase, String str, Function1 function1, Throwable th) {
        Logger access$getLogger$p = pingUseCase.logger;
        access$getLogger$p.error("Ping sent error: " + th + ", topic: " + str);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PingUseCase$ping$2(this.this$0, this.$topic, this.$onFailure, this.$timeout, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.sessionStorageRepository.isSessionValid(new Topic(this.$topic))) {
                SignRpc.SessionPing sessionPing = new SignRpc.SessionPing(0, (String) null, (String) null, new SignParams.PingParams(), 7, (DefaultConstructorMarker) null);
                IrnParams irnParams = new IrnParams(Tags.SESSION_PING, new Ttl(Time.getThirtySeconds()), Boxing.boxLong(sessionPing.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
                Logger access$getLogger$p = this.this$0.logger;
                String str = this.$topic;
                access$getLogger$p.log("Sending ping... topic: " + str);
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
                Topic topic = new Topic(this.$topic);
                PingUseCase pingUseCase = this.this$0;
                String str2 = this.$topic;
                long j2 = this.$timeout;
                Function1<String, Unit> function1 = this.$onSuccess;
                Function1<Throwable, Unit> function12 = this.$onFailure;
                j jVar = new j(pingUseCase, str2, j2, sessionPing, function1, function12);
                RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(access$getJsonRpcInteractor$p, topic, irnParams, sessionPing, (EnvelopeType) null, (Participants) null, jVar, new a((Object) pingUseCase, 4, (Object) str2, (Object) function12), 24, (Object) null);
            } else {
                this.$onFailure.invoke(new Throwable("Session topic is not valid"));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PingUseCase$ping$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
