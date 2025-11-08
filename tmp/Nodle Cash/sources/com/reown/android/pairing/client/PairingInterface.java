package com.reown.android.pairing.client;

import com.reown.android.Core;
import com.reown.android.internal.common.di.b;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u001dJ\b\u0010\u0002\u001a\u00020\u0003H&J \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007H&J(\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\t\u001a\u00020\nH&J<\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u00072\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007H&J&\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\n2\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007H'J&\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00102\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007H'J\u001c\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H'J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nH&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH&¨\u0006\u001eÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/pairing/client/PairingInterface;", "", "initialize", "", "create", "Lcom/reown/android/Core$Model$Pairing;", "onError", "Lkotlin/Function1;", "Lcom/reown/android/Core$Model$Error;", "methods", "", "pair", "Lcom/reown/android/Core$Params$Pair;", "onSuccess", "disconnect", "topic", "Lcom/reown/android/Core$Params$Disconnect;", "ping", "Lcom/reown/android/Core$Params$Ping;", "pairingPing", "Lcom/reown/android/Core$Listeners$PairingPing;", "getPairings", "", "validatePairingUri", "", "uri", "setDelegate", "delegate", "Lcom/reown/android/pairing/client/PairingInterface$Delegate;", "Delegate", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PairingInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/pairing/client/PairingInterface$Delegate;", "", "onPairingDelete", "", "deletedPairing", "Lcom/reown/android/Core$Model$DeletedPairing;", "onPairingExpired", "expiredPairing", "Lcom/reown/android/Core$Model$ExpiredPairing;", "onPairingState", "pairingState", "Lcom/reown/android/Core$Model$PairingState;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Delegate {

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated(message = "onPairingDelete callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
            @Deprecated
            public static void onPairingDelete(@NotNull Delegate delegate, @NotNull Core.Model.DeletedPairing deletedPairing) {
                Intrinsics.checkNotNullParameter(deletedPairing, "deletedPairing");
                Delegate.super.onPairingDelete(deletedPairing);
            }

            @Deprecated(message = "onPairingExpired callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
            @Deprecated
            public static void onPairingExpired(@NotNull Delegate delegate, @NotNull Core.Model.ExpiredPairing expiredPairing) {
                Intrinsics.checkNotNullParameter(expiredPairing, "expiredPairing");
                Delegate.super.onPairingExpired(expiredPairing);
            }

            @Deprecated
            public static void onPairingState(@NotNull Delegate delegate, @NotNull Core.Model.PairingState pairingState) {
                Intrinsics.checkNotNullParameter(pairingState, "pairingState");
                Delegate.super.onPairingState(pairingState);
            }
        }

        @Deprecated(message = "onPairingDelete callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
        void onPairingDelete(@NotNull Core.Model.DeletedPairing deletedPairing) {
            Intrinsics.checkNotNullParameter(deletedPairing, "deletedPairing");
        }

        @Deprecated(message = "onPairingExpired callback has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
        void onPairingExpired(@NotNull Core.Model.ExpiredPairing expiredPairing) {
            Intrinsics.checkNotNullParameter(expiredPairing, "expiredPairing");
        }

        void onPairingState(@NotNull Core.Model.PairingState pairingState) {
            Intrinsics.checkNotNullParameter(pairingState, "pairingState");
        }
    }

    static /* synthetic */ Core.Model.Pairing create$default(PairingInterface pairingInterface, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                function1 = new b(10);
            }
            return pairingInterface.create(function1);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
    }

    /* access modifiers changed from: private */
    static Unit create$lambda$0(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit create$lambda$1(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void disconnect$default(PairingInterface pairingInterface, String str, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new b(12);
            }
            pairingInterface.disconnect(str, (Function1<? super Core.Model.Error, Unit>) function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: disconnect");
    }

    /* access modifiers changed from: private */
    static Unit disconnect$lambda$4(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit disconnect$lambda$5(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void pair$default(PairingInterface pairingInterface, Core.Params.Pair pair, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new b(8);
            }
            if ((i3 & 4) != 0) {
                function12 = new b(9);
            }
            pairingInterface.pair(pair, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: pair");
    }

    /* access modifiers changed from: private */
    static Unit pair$lambda$2(Core.Params.Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit pair$lambda$3(Core.Model.Error error) {
        Intrinsics.checkNotNullParameter(error, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void ping$default(PairingInterface pairingInterface, Core.Params.Ping ping, Core.Listeners.PairingPing pairingPing, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                pairingPing = null;
            }
            pairingInterface.ping(ping, pairingPing);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ping");
    }

    @Nullable
    Core.Model.Pairing create(@NotNull Function1<? super Core.Model.Error, Unit> function1);

    @Nullable
    Core.Model.Pairing create(@NotNull Function1<? super Core.Model.Error, Unit> function1, @NotNull String str);

    @Deprecated(message = "Disconnect method has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
    void disconnect(@NotNull Core.Params.Disconnect disconnect, @NotNull Function1<? super Core.Model.Error, Unit> function1);

    @Deprecated(message = "Disconnect method has been deprecated. It will be removed soon. Pairing will disconnect automatically internally.")
    void disconnect(@NotNull String str, @NotNull Function1<? super Core.Model.Error, Unit> function1);

    @NotNull
    List<Core.Model.Pairing> getPairings();

    void initialize();

    void pair(@NotNull Core.Params.Pair pair, @NotNull Function1<? super Core.Params.Pair, Unit> function1, @NotNull Function1<? super Core.Model.Error, Unit> function12);

    @Deprecated(message = "Ping method has been deprecated. It will be removed soon. Please use Ping from WalletKit or Sign clients.")
    void ping(@NotNull Core.Params.Ping ping, @Nullable Core.Listeners.PairingPing pairingPing);

    void setDelegate(@NotNull Delegate delegate);

    boolean validatePairingUri(@NotNull String str);

    static /* synthetic */ Core.Model.Pairing create$default(PairingInterface pairingInterface, Function1 function1, String str, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 1) != 0) {
                function1 = new b(11);
            }
            return pairingInterface.create(function1, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
    }

    static /* synthetic */ void disconnect$default(PairingInterface pairingInterface, Core.Params.Disconnect disconnect, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new b(13);
            }
            pairingInterface.disconnect(disconnect, (Function1<? super Core.Model.Error, Unit>) function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: disconnect");
    }
}
