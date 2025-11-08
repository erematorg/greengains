package com.reown.android.pairing.engine.domain;

import com.reown.android.pulse.model.Trace;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$pair$7", f = "PairingEngine.kt", i = {}, l = {183}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$pair$7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Topic $pairingTopic;
    final /* synthetic */ List<String> $trace;
    int label;
    final /* synthetic */ PairingEngine this$0;

    @SourceDebugExtension({"SMAP\nPairingEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingEngine.kt\ncom/reown/android/pairing/engine/domain/PairingEngine$pair$7$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,466:1\n1#2:467\n*E\n"})
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$pair$7$1", f = "PairingEngine.kt", i = {}, l = {185}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.pairing.engine.domain.PairingEngine$pair$7$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(list, pairingEngine, topic, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                list.add(Trace.Pairing.EMIT_STORED_PAIRING);
                PairingEngine pairingEngine = pairingEngine;
                Topic topic = topic;
                Logger access$getLogger$p = pairingEngine.logger;
                access$getLogger$p.log("Emitting stored pairing: " + topic);
                MutableSharedFlow access$get_storedPairingTopicFlow$p = pairingEngine._storedPairingTopicFlow;
                Pair pair = new Pair(topic, list);
                this.label = 1;
                if (access$get_storedPairingTopicFlow$p.emit(pair, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingEngine$pair$7(List<String> list, PairingEngine pairingEngine, Topic topic, Continuation<? super PairingEngine$pair$7> continuation) {
        super(2, continuation);
        this.$trace = list;
        this.this$0 = pairingEngine;
        this.$pairingTopic = topic;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairingEngine$pair$7(this.$trace, this.this$0, this.$pairingTopic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final List<String> list = this.$trace;
            final PairingEngine pairingEngine = this.this$0;
            final Topic topic = this.$pairingTopic;
            AnonymousClass1 r7 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r7, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PairingEngine$pair$7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
