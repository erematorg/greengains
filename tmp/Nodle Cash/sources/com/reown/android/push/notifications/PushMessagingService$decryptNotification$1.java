package com.reown.android.push.notifications;

import com.google.firebase.messaging.RemoteMessage;
import com.reown.android.Core;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.push.notifications.PushMessagingService$decryptNotification$1", f = "PushMessagingService.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, s = {})
public final class PushMessagingService$decryptNotification$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $encryptedMessage;
    final /* synthetic */ String $tag;
    final /* synthetic */ RemoteMessage $this_decryptNotification;
    int label;
    final /* synthetic */ PushMessagingService this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.push.notifications.PushMessagingService$decryptNotification$1$1", f = "PushMessagingService.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.push.notifications.PushMessagingService$decryptNotification$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0(PushMessagingService pushMessagingService, RemoteMessage remoteMessage, Core.Model.Message message) {
            pushMessagingService.onMessage(message, remoteMessage);
            return Unit.INSTANCE;
        }

        /* access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$1(PushMessagingService pushMessagingService, RemoteMessage remoteMessage, Throwable th) {
            pushMessagingService.onError(th, remoteMessage);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(pushMessagingService, str, remoteMessage, str2, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                Map data = remoteMessage.getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Object value = MapsKt.getValue(data, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                String str = str2;
                PushMessagingService pushMessagingService = pushMessagingService;
                RemoteMessage remoteMessage = remoteMessage;
                a aVar = new a(pushMessagingService, remoteMessage, 0);
                a aVar2 = new a(pushMessagingService, remoteMessage, 1);
                this.label = 1;
                if (((DecryptMessageUseCaseInterface) MapsKt.getValue(pushMessagingService.getDecryptMessageUseCases(), str)).decryptNotification((String) value, str, aVar, aVar2, this) == coroutine_suspended) {
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
    public PushMessagingService$decryptNotification$1(PushMessagingService pushMessagingService, String str, RemoteMessage remoteMessage, String str2, Continuation<? super PushMessagingService$decryptNotification$1> continuation) {
        super(2, continuation);
        this.this$0 = pushMessagingService;
        this.$tag = str;
        this.$this_decryptNotification = remoteMessage;
        this.$encryptedMessage = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PushMessagingService$decryptNotification$1(this.this$0, this.$tag, this.$this_decryptNotification, this.$encryptedMessage, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final PushMessagingService pushMessagingService = this.this$0;
            final String str = this.$tag;
            final RemoteMessage remoteMessage = this.$this_decryptNotification;
            final String str2 = this.$encryptedMessage;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r3, this) == coroutine_suspended) {
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
        return ((PushMessagingService$decryptNotification$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
