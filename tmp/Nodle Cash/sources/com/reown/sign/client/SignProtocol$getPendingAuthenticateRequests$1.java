package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.common.model.Request;
import com.reown.sign.engine.domain.SignEngine;
import java.util.ArrayList;
import java.util.List;
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

@SourceDebugExtension({"SMAP\nSignProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignProtocol.kt\ncom/reown/sign/client/SignProtocol$getPendingAuthenticateRequests$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,553:1\n1563#2:554\n1634#2,3:555\n*S KotlinDebug\n*F\n+ 1 SignProtocol.kt\ncom/reown/sign/client/SignProtocol$getPendingAuthenticateRequests$1\n*L\n475#1:554\n475#1:555,3\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/reown/sign/client/Sign$Model$SessionAuthenticate;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$getPendingAuthenticateRequests$1", f = "SignProtocol.kt", i = {}, l = {475}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$getPendingAuthenticateRequests$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Sign.Model.SessionAuthenticate>>, Object> {
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$getPendingAuthenticateRequests$1(SignProtocol signProtocol, Continuation<? super SignProtocol$getPendingAuthenticateRequests$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$getPendingAuthenticateRequests$1(this.this$0, continuation);
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
            obj = access$getSignEngine$p.getPendingAuthenticateRequests(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Iterable<Request> iterable = (Iterable) obj;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (Request client : iterable) {
            arrayList.add(Intrinsics.checkNotNullParameter(client, "<this>"));
        }
        return arrayList;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Sign.Model.SessionAuthenticate>> continuation) {
        return ((SignProtocol$getPendingAuthenticateRequests$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
