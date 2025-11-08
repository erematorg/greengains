package com.apollographql.apollo3.internal;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\t\u0010!\u001a\u00020\u000bH\u0001J\u0013\u0010!\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0001J\u0019\u0010!\u001a\u00020\u000b2\u000e\u0010\n\u001a\n\u0018\u00010\"j\u0004\u0018\u0001`#H\u0001J\u0012\u0010$\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0016J.\u0010%\u001a\u00020\u000b2#\u0010\u0005\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006H\u0001J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'H\u0003J\u0016\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00028\u0000HA¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018HAø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u0010.J\u0010\u00101\u001a\u0004\u0018\u00018\u0000HA¢\u0006\u0002\u0010.J\u0016\u00102\u001a\u00020\u000b2\u0006\u0010)\u001a\u00028\u0000HA¢\u0006\u0002\u00103J+\u00104\u001a\u00020\u000b2#\u0010\u0005\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006J\u001c\u00105\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b6\u0010,J$\u00107\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010)\u001a\u00028\u0000H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00109R-\u0010\u0005\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\r8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\r8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014X\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180\u0014X\u0005¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u001c\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00148VX\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0016R$\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006:"}, d2 = {"Lcom/apollographql/apollo3/internal/ChannelWrapper;", "E", "Lkotlinx/coroutines/channels/Channel;", "wrapped", "(Lkotlinx/coroutines/channels/Channel;)V", "handler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "<set-?>", "", "isClosed", "()Z", "isClosedForReceive", "isClosedForSend", "isEmpty", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "close", "invokeOnClose", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "element", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setInvokeOnClose", "tryReceive", "tryReceive-PtdJZtk", "trySend", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ChannelWrapper<E> implements Channel<E> {
    @Nullable
    private Function1<? super Throwable, Unit> handler;
    private boolean isClosed;
    @NotNull
    private final Channel<E> wrapped;

    public ChannelWrapper(@NotNull Channel<E> channel) {
        Intrinsics.checkNotNullParameter(channel, "wrapped");
        this.wrapped = channel;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        this.wrapped.cancel();
    }

    public boolean close(@Nullable Throwable th) {
        Function1<? super Throwable, Unit> function1;
        this.isClosed = true;
        boolean close = this.wrapped.close(th);
        if (close && (function1 = this.handler) != null) {
            function1.invoke(th);
        }
        this.handler = null;
        return close;
    }

    @NotNull
    public SelectClause1<E> getOnReceive() {
        return this.wrapped.getOnReceive();
    }

    @NotNull
    public SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        return this.wrapped.getOnReceiveCatching();
    }

    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return this.wrapped.getOnReceiveOrNull();
    }

    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this.wrapped.getOnSend();
    }

    @ExperimentalCoroutinesApi
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "handler");
        this.wrapped.invokeOnClose(function1);
    }

    public final boolean isClosed() {
        return this.isClosed;
    }

    public boolean isClosedForReceive() {
        return this.wrapped.isClosedForReceive();
    }

    public boolean isClosedForSend() {
        return this.wrapped.isClosedForSend();
    }

    public boolean isEmpty() {
        return this.wrapped.isEmpty();
    }

    @NotNull
    public ChannelIterator<E> iterator() {
        return this.wrapped.iterator();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e3) {
        return this.wrapped.offer(e3);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    @Nullable
    public E poll() {
        return this.wrapped.poll();
    }

    @Nullable
    public Object receive(@NotNull Continuation<? super E> continuation) {
        return this.wrapped.receive(continuation);
    }

    @Nullable
    /* renamed from: receiveCatching-JP2dKIU  reason: not valid java name */
    public Object m8218receiveCatchingJP2dKIU(@NotNull Continuation<? super ChannelResult<? extends E>> continuation) {
        Object r02 = this.wrapped.m10508receiveCatchingJP2dKIU(continuation);
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return r02;
    }

    @Nullable
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    @LowPriorityInOverloadResolution
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return this.wrapped.receiveOrNull(continuation);
    }

    @Nullable
    public Object send(E e3, @NotNull Continuation<? super Unit> continuation) {
        return this.wrapped.send(e3, continuation);
    }

    public final void setInvokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "handler");
        this.handler = function1;
    }

    @NotNull
    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public Object m8219tryReceivePtdJZtk() {
        return this.wrapped.m10509tryReceivePtdJZtk();
    }

    @NotNull
    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public Object m8220trySendJP2dKIU(E e3) {
        return this.wrapped.m10510trySendJP2dKIU(e3);
    }

    public void cancel(@Nullable CancellationException cancellationException) {
        this.wrapped.cancel(cancellationException);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable th) {
        return this.wrapped.cancel(th);
    }
}
