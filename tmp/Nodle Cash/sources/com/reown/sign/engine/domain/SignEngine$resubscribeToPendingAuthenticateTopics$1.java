package com.reown.sign.engine.domain;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableSharedFlow;

@SourceDebugExtension({"SMAP\nSignEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$resubscribeToPendingAuthenticateTopics$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,522:1\n1563#2:523\n1634#2,3:524\n*S KotlinDebug\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$resubscribeToPendingAuthenticateTopics$1\n*L\n350#1:523\n350#1:524,3\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$resubscribeToPendingAuthenticateTopics$1", f = "SignEngine.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
public final class SignEngine$resubscribeToPendingAuthenticateTopics$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SignEngine this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$resubscribeToPendingAuthenticateTopics$1$2", f = "SignEngine.kt", i = {}, l = {353}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.engine.domain.SignEngine$resubscribeToPendingAuthenticateTopics$1$2  reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(signEngine, e3, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                MutableSharedFlow access$get_engineEvent$p = signEngine._engineEvent;
                SDKError sDKError = new SDKError(e3);
                this.label = 1;
                if (access$get_engineEvent$p.emit(sDKError, this) == coroutine_suspended) {
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
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$resubscribeToPendingAuthenticateTopics$1(SignEngine signEngine, Continuation<? super SignEngine$resubscribeToPendingAuthenticateTopics$1> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(SignEngine signEngine, Throwable th) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new SignEngine$resubscribeToPendingAuthenticateTopics$1$1$1(signEngine, th, (Continuation<? super SignEngine$resubscribeToPendingAuthenticateTopics$1$1$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignEngine$resubscribeToPendingAuthenticateTopics$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            AuthenticateResponseTopicRepository access$getAuthenticateResponseTopicRepository$p = this.this$0.authenticateResponseTopicRepository;
            this.label = 1;
            obj = access$getAuthenticateResponseTopicRepository$p.getResponseTopics(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                CoroutineScope scope = WalletConnectScopeKt.getScope();
                final SignEngine signEngine = this.this$0;
                Job unused = BuildersKt__Builders_commonKt.launch$default(scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2((Continuation<? super AnonymousClass2>) null), 3, (Object) null);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Iterable<String> iterable = (Iterable) obj;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (String add : iterable) {
            arrayList.add(add);
        }
        RelayJsonRpcInteractorInterface.batchSubscribe$default(this.this$0.jsonRpcInteractor, arrayList, (Function1) null, new a(this.this$0, 0), 2, (Object) null);
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SignEngine$resubscribeToPendingAuthenticateTopics$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
