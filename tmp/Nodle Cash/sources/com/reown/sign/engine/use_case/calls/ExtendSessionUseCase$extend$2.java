package com.reown.sign.engine.use_case.calls;

import androidx.browser.trusted.c;
import com.reown.android.internal.common.exception.CannotFindSequenceForTopic;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.MessagesKt;
import com.reown.sign.common.exceptions.NotSettledSessionException;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.common.model.vo.sequence.SessionVO;
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
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.ExtendSessionUseCase$extend$2", f = "ExtendSessionUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ExtendSessionUseCase$extend$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ ExtendSessionUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtendSessionUseCase$extend$2(ExtendSessionUseCase extendSessionUseCase, String str, Function1<? super Throwable, Unit> function1, Function0<Unit> function0, Continuation<? super ExtendSessionUseCase$extend$2> continuation) {
        super(2, continuation);
        this.this$0 = extendSessionUseCase;
        this.$topic = str;
        this.$onFailure = function1;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(ExtendSessionUseCase extendSessionUseCase, String str, Function0 function0) {
        Logger access$getLogger$p = extendSessionUseCase.logger;
        access$getLogger$p.log("Session extend sent successfully on topic: " + str);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(ExtendSessionUseCase extendSessionUseCase, String str, Function1 function1, Throwable th) {
        Logger access$getLogger$p = extendSessionUseCase.logger;
        access$getLogger$p.error("Sending session extend error: " + th + " on topic: " + str);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtendSessionUseCase$extend$2(this.this$0, this.$topic, this.$onFailure, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.sessionStorageRepository.isSessionValid(new Topic(this.$topic))) {
                this.$onFailure.invoke(new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", this.$topic)));
                return Unit.INSTANCE;
            }
            SessionVO sessionWithoutMetadataByTopic = this.this$0.sessionStorageRepository.getSessionWithoutMetadataByTopic(new Topic(this.$topic));
            if (!sessionWithoutMetadataByTopic.isAcknowledged()) {
                Logger access$getLogger$p = this.this$0.logger;
                String str = this.$topic;
                access$getLogger$p.error("Sending session extend error: not acknowledged session on topic: " + str);
                this.$onFailure.invoke(new NotSettledSessionException(c.a(MessagesKt.SESSION_IS_NOT_ACKNOWLEDGED_MESSAGE, this.$topic)));
                return Unit.INSTANCE;
            }
            long weekInSeconds = Time.getWeekInSeconds() + sessionWithoutMetadataByTopic.getExpiry().getSeconds();
            this.this$0.sessionStorageRepository.extendSession(new Topic(this.$topic), weekInSeconds);
            SignRpc.SessionExtend sessionExtend = new SignRpc.SessionExtend(0, (String) null, (String) null, new SignParams.ExtendParams(weekInSeconds), 7, (DefaultConstructorMarker) null);
            IrnParams irnParams = new IrnParams(Tags.SESSION_EXTEND, new Ttl(Time.getDayInSeconds()), Boxing.boxLong(sessionExtend.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            Logger access$getLogger$p2 = this.this$0.logger;
            String str2 = this.$topic;
            access$getLogger$p2.log("Sending session extend on topic: " + str2);
            RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
            Topic topic = new Topic(this.$topic);
            ExtendSessionUseCase extendSessionUseCase = this.this$0;
            String str3 = this.$topic;
            RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(access$getJsonRpcInteractor$p, topic, irnParams, sessionExtend, (EnvelopeType) null, (Participants) null, new h(extendSessionUseCase, str3, this.$onSuccess, 2), new a((Object) extendSessionUseCase, 3, (Object) str3, (Object) this.$onFailure), 24, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtendSessionUseCase$extend$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
