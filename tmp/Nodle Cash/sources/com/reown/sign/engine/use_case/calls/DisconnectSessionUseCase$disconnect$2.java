package com.reown.sign.engine.use_case.calls;

import androidx.browser.trusted.c;
import com.reown.android.internal.common.exception.CannotFindSequenceForTopic;
import com.reown.android.internal.common.exception.Reason;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.DisconnectSessionUseCase$disconnect$2", f = "DisconnectSessionUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class DisconnectSessionUseCase$disconnect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ DisconnectSessionUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DisconnectSessionUseCase$disconnect$2(DisconnectSessionUseCase disconnectSessionUseCase, String str, Function1<? super Throwable, Unit> function1, Function0<Unit> function0, Continuation<? super DisconnectSessionUseCase$disconnect$2> continuation) {
        super(2, continuation);
        this.this$0 = disconnectSessionUseCase;
        this.$topic = str;
        this.$onFailure = function1;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(DisconnectSessionUseCase disconnectSessionUseCase, String str, Function0 function0) {
        Logger access$getLogger$p = disconnectSessionUseCase.logger;
        access$getLogger$p.log("Disconnect sent successfully on topic: " + str);
        disconnectSessionUseCase.sessionStorageRepository.deleteSession(new Topic(str));
        RelayJsonRpcInteractorInterface.unsubscribe$default(disconnectSessionUseCase.jsonRpcInteractor, new Topic(str), (Function0) null, (Function1) null, 6, (Object) null);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(DisconnectSessionUseCase disconnectSessionUseCase, String str, Function1 function1, Throwable th) {
        Logger access$getLogger$p = disconnectSessionUseCase.logger;
        access$getLogger$p.error("Sending session disconnect error: " + th + " on topic: " + str);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DisconnectSessionUseCase$disconnect$2(this.this$0, this.$topic, this.$onFailure, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.sessionStorageRepository.isSessionValid(new Topic(this.$topic))) {
                Logger access$getLogger$p = this.this$0.logger;
                String str = this.$topic;
                access$getLogger$p.error("Sending session disconnect error: invalid session " + str);
                this.$onFailure.invoke(new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", this.$topic)));
                return Unit.INSTANCE;
            }
            Reason.UserDisconnected userDisconnected = Reason.UserDisconnected.INSTANCE;
            SignRpc.SessionDelete sessionDelete = new SignRpc.SessionDelete(0, (String) null, (String) null, new SignParams.DeleteParams(userDisconnected.getCode(), userDisconnected.getMessage()), 7, (DefaultConstructorMarker) null);
            IrnParams irnParams = new IrnParams(Tags.SESSION_DELETE, new Ttl(Time.getDayInSeconds()), Boxing.boxLong(sessionDelete.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            Logger access$getLogger$p2 = this.this$0.logger;
            String str2 = this.$topic;
            access$getLogger$p2.log("Sending session disconnect on topic: " + str2);
            RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
            Topic topic = new Topic(this.$topic);
            DisconnectSessionUseCase disconnectSessionUseCase = this.this$0;
            String str3 = this.$topic;
            RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(access$getJsonRpcInteractor$p, topic, irnParams, sessionDelete, (EnvelopeType) null, (Participants) null, new h(disconnectSessionUseCase, str3, this.$onSuccess, 0), new a((Object) disconnectSessionUseCase, 1, (Object) str3, (Object) this.$onFailure), 24, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisconnectSessionUseCase$disconnect$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
