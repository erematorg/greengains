package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.client.mapper.ClientMapperKt;
import com.reown.sign.engine.domain.SignEngine;
import com.reown.sign.engine.model.EngineDO;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nSignProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignProtocol.kt\ncom/reown/sign/client/SignProtocol$getActiveSessionByTopic$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,553:1\n1563#2:554\n1634#2,3:555\n1#3:558\n*S KotlinDebug\n*F\n+ 1 SignProtocol.kt\ncom/reown/sign/client/SignProtocol$getActiveSessionByTopic$1\n*L\n455#1:554\n455#1:555,3\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/reown/sign/client/Sign$Model$Session;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$getActiveSessionByTopic$1", f = "SignProtocol.kt", i = {}, l = {455}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$getActiveSessionByTopic$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Sign.Model.Session>, Object> {
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$getActiveSessionByTopic$1(SignProtocol signProtocol, String str, Continuation<? super SignProtocol$getActiveSessionByTopic$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$topic = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$getActiveSessionByTopic$1(this.this$0, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SignEngine access$getSignEngine$p = this.this$0.signEngine;
            if (access$getSignEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                access$getSignEngine$p = null;
            }
            this.label = 1;
            obj = access$getSignEngine$p.getListOfSettledSessions(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Iterable<EngineDO.Session> iterable = (Iterable) obj;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (EngineDO.Session clientActiveSession : iterable) {
            arrayList.add(ClientMapperKt.toClientActiveSession(clientActiveSession));
        }
        String str = this.$topic;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (Intrinsics.areEqual((Object) ((Sign.Model.Session) next).getTopic(), (Object) str)) {
                return next;
            }
        }
        return null;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Sign.Model.Session> continuation) {
        return ((SignProtocol$getActiveSessionByTopic$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
