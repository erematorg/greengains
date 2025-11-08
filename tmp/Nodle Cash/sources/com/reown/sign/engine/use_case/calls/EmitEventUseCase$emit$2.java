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
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.util.UtilFunctionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
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
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nEmitEventUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EmitEventUseCase.kt\ncom/reown/sign/engine/use_case/calls/EmitEventUseCase$emit$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,84:1\n1#2:85\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.EmitEventUseCase$emit$2", f = "EmitEventUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class EmitEventUseCase$emit$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EngineDO.Event $event;
    final /* synthetic */ Long $id;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $topic;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ EmitEventUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EmitEventUseCase$emit$2(EmitEventUseCase emitEventUseCase, String str, EngineDO.Event event, Long l2, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, Continuation<? super EmitEventUseCase$emit$2> continuation) {
        super(2, continuation);
        this.this$0 = emitEventUseCase;
        this.$topic = str;
        this.$event = event;
        this.$id = l2;
        this.$onSuccess = function0;
        this.$onFailure = function1;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3$lambda$1(EmitEventUseCase emitEventUseCase, String str, Function0 function0) {
        Logger access$getLogger$p = emitEventUseCase.logger;
        access$getLogger$p.log("Event sent successfully, on topic: " + str);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3$lambda$2(EmitEventUseCase emitEventUseCase, String str, Function1 function1, Throwable th) {
        Logger access$getLogger$p = emitEventUseCase.logger;
        access$getLogger$p.error("Sending event error: " + th + ", on topic: " + str);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        EmitEventUseCase$emit$2 emitEventUseCase$emit$2 = new EmitEventUseCase$emit$2(this.this$0, this.$topic, this.$event, this.$id, this.$onSuccess, this.$onFailure, continuation);
        emitEventUseCase$emit$2.L$0 = obj;
        return emitEventUseCase$emit$2;
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            EmitEventUseCase emitEventUseCase = this.this$0;
            String str = this.$topic;
            EngineDO.Event event = this.$event;
            try {
                Result.Companion companion = Result.Companion;
                emitEventUseCase.validate(str, event);
                obj2 = Result.m8979constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
            }
            EngineDO.Event event2 = this.$event;
            Long l2 = this.$id;
            EmitEventUseCase emitEventUseCase2 = this.this$0;
            String str2 = this.$topic;
            Function0<Unit> function0 = this.$onSuccess;
            Function1<Throwable, Unit> function1 = this.$onFailure;
            Throwable r7 = Result.m8982exceptionOrNullimpl(obj2);
            if (r7 == null) {
                Unit unit = (Unit) obj2;
                SignRpc.SessionEvent sessionEvent = new SignRpc.SessionEvent(l2 != null ? l2.longValue() : UtilFunctionsKt.generateId(), (String) null, (String) null, new SignParams.EventParams(new SessionEventVO(event2.getName(), event2.getData()), event2.getChainId()), 6, (DefaultConstructorMarker) null);
                IrnParams irnParams = new IrnParams(Tags.SESSION_EVENT, new Ttl(Time.getFiveMinutesInSeconds()), Boxing.boxLong(sessionEvent.getId()), (List) null, (String) null, (List) null, (List) null, true, 120, (DefaultConstructorMarker) null);
                Logger access$getLogger$p = emitEventUseCase2.logger;
                access$getLogger$p.log("Emitting event on topic: " + str2);
                RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(emitEventUseCase2.jsonRpcInteractor, new Topic(str2), irnParams, sessionEvent, (EnvelopeType) null, (Participants) null, new h(emitEventUseCase2, str2, function0, 1), new a((Object) emitEventUseCase2, 2, (Object) str2, (Object) function1), 24, (Object) null);
            } else {
                Logger access$getLogger$p2 = emitEventUseCase2.logger;
                access$getLogger$p2.error("Sending event error: " + r7 + ", on topic: " + str2);
                function1.invoke(r7);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EmitEventUseCase$emit$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
