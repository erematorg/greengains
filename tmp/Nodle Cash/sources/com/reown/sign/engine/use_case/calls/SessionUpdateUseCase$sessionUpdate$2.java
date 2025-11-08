package com.reown.sign.engine.use_case.calls;

import androidx.camera.camera2.internal.C0118y;
import com.reown.android.internal.common.exception.GenericException;
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
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import java.util.List;
import java.util.Map;
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

@SourceDebugExtension({"SMAP\nSessionUpdateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionUpdateUseCase.kt\ncom/reown/sign/engine/use_case/calls/SessionUpdateUseCase$sessionUpdate$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n1#2:103\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionUpdateUseCase$sessionUpdate$2", f = "SessionUpdateUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SessionUpdateUseCase$sessionUpdate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, EngineDO.Namespace.Session> $namespaces;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $topic;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SessionUpdateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionUpdateUseCase$sessionUpdate$2(SessionUpdateUseCase sessionUpdateUseCase, String str, Map<String, EngineDO.Namespace.Session> map, Function1<? super Throwable, Unit> function1, Function0<Unit> function0, Continuation<? super SessionUpdateUseCase$sessionUpdate$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionUpdateUseCase;
        this.$topic = str;
        this.$namespaces = map;
        this.$onFailure = function1;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3$lambda$1(SessionUpdateUseCase sessionUpdateUseCase, String str, Function0 function0) {
        Logger access$getLogger$p = sessionUpdateUseCase.logger;
        access$getLogger$p.log("Update sent successfully, topic: " + str);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3$lambda$2(SessionUpdateUseCase sessionUpdateUseCase, String str, Function1 function1, Throwable th) {
        Logger access$getLogger$p = sessionUpdateUseCase.logger;
        access$getLogger$p.error("Sending session update error: " + th + ", topic: " + str);
        sessionUpdateUseCase.sessionStorageRepository.deleteTempNamespacesByTopic(str);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SessionUpdateUseCase$sessionUpdate$2 sessionUpdateUseCase$sessionUpdate$2 = new SessionUpdateUseCase$sessionUpdate$2(this.this$0, this.$topic, this.$namespaces, this.$onFailure, this.$onSuccess, continuation);
        sessionUpdateUseCase$sessionUpdate$2.L$0 = obj;
        return sessionUpdateUseCase$sessionUpdate$2;
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SessionUpdateUseCase sessionUpdateUseCase = this.this$0;
            String str = this.$topic;
            Map<String, EngineDO.Namespace.Session> map = this.$namespaces;
            try {
                Result.Companion companion = Result.Companion;
                sessionUpdateUseCase.validate(str, map);
                obj2 = Result.m8979constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
            }
            Map<String, EngineDO.Namespace.Session> map2 = this.$namespaces;
            SessionUpdateUseCase sessionUpdateUseCase2 = this.this$0;
            String str2 = this.$topic;
            Function1<Throwable, Unit> function1 = this.$onFailure;
            Function0<Unit> function0 = this.$onSuccess;
            Throwable r7 = Result.m8982exceptionOrNullimpl(obj2);
            if (r7 == null) {
                Unit unit = (Unit) obj2;
                SignRpc.SessionUpdate sessionUpdate = new SignRpc.SessionUpdate(0, (String) null, (String) null, new SignParams.UpdateNamespacesParams(EngineMapperKt.toMapOfNamespacesVOSession(map2)), 7, (DefaultConstructorMarker) null);
                IrnParams irnParams = new IrnParams(Tags.SESSION_UPDATE, new Ttl(Time.getDayInSeconds()), Boxing.boxLong(sessionUpdate.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
                try {
                    Logger access$getLogger$p = sessionUpdateUseCase2.logger;
                    access$getLogger$p.log("Sending session update on topic: " + str2);
                    sessionUpdateUseCase2.sessionStorageRepository.insertTempNamespaces(str2, EngineMapperKt.toMapOfNamespacesVOSession(map2), sessionUpdate.getId());
                    RelayJsonRpcInteractorInterface.publishJsonRpcRequest$default(sessionUpdateUseCase2.jsonRpcInteractor, new Topic(str2), irnParams, sessionUpdate, (EnvelopeType) null, (Participants) null, new h(sessionUpdateUseCase2, str2, function0, 4), new a((Object) sessionUpdateUseCase2, 7, (Object) str2, (Object) function1), 24, (Object) null);
                } catch (Exception e3) {
                    Logger access$getLogger$p2 = sessionUpdateUseCase2.logger;
                    access$getLogger$p2.error("Error updating namespaces: " + e3);
                    function1.invoke(new GenericException(C0118y.e("Error updating namespaces: ", e3)));
                }
            } else {
                Logger access$getLogger$p3 = sessionUpdateUseCase2.logger;
                access$getLogger$p3.error("Error updating namespaces: " + r7);
                function1.invoke(r7);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionUpdateUseCase$sessionUpdate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
