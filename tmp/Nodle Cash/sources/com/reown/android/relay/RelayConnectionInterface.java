package com.reown.android.relay;

import com.reown.android.Core;
import com.reown.android.internal.common.di.b;
import com.reown.foundation.network.RelayInterface;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J2\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u0011H'J\u001c\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0011H&J2\u0010\u0015\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u0011H'J\u001c\u0010\u0015\u001a\u00020\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0011H&J\u001c\u0010\u0016\u001a\u00020\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0011H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006R\u001a\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0017À\u0006\u0003"}, d2 = {"Lcom/reown/android/relay/RelayConnectionInterface;", "Lcom/reown/foundation/network/RelayInterface;", "wssConnectionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/reown/android/relay/WSSConnectionState;", "getWssConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "isNetworkAvailable", "", "onResubscribe", "Lkotlinx/coroutines/flow/Flow;", "", "getOnResubscribe", "()Lkotlinx/coroutines/flow/Flow;", "connect", "", "onErrorModel", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "onError", "", "disconnect", "restart", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RelayConnectionInterface extends RelayInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void connect$default(RelayConnectionInterface relayConnectionInterface, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                function1 = new b(15);
            }
            relayConnectionInterface.connect(function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: connect");
    }

    /* access modifiers changed from: private */
    static Unit connect$lambda$0(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void disconnect$default(RelayConnectionInterface relayConnectionInterface, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                function1 = new b(14);
            }
            relayConnectionInterface.disconnect(function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: disconnect");
    }

    /* access modifiers changed from: private */
    static Unit disconnect$lambda$1(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    void connect(@NotNull Function1<? super Core.Model.Error, Unit> function1);

    @Deprecated(message = "This has become deprecate in favor of the onError returning Core.Model.Error", replaceWith = @ReplaceWith(expression = "this.connect(onErrorModel)", imports = {}))
    void connect(@NotNull Function1<? super Core.Model.Error, Unit> function1, @NotNull Function1<? super String, Unit> function12);

    void disconnect(@NotNull Function1<? super Core.Model.Error, Unit> function1);

    @Deprecated(message = "This has become deprecate in favor of the onError returning Core.Model.Error", replaceWith = @ReplaceWith(expression = "this.disconnect(onErrorModel)", imports = {}))
    void disconnect(@NotNull Function1<? super Core.Model.Error, Unit> function1, @NotNull Function1<? super String, Unit> function12);

    @NotNull
    Flow<Object> getOnResubscribe();

    @NotNull
    StateFlow<WSSConnectionState> getWssConnectionState();

    @NotNull
    StateFlow<Boolean> isNetworkAvailable();

    void restart(@NotNull Function1<? super Core.Model.Error, Unit> function1);
}
