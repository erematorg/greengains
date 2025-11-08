package com.tinder.scarlet.lifecycle.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J9\u0010\r\u001a\u00020\f2.\u0010\u000e\u001a*\u0012\u000e\b\u0000\u0012\n \u0011*\u0004\u0018\u00010\u00100\u0010 \u0011*\u0014\u0012\u000e\b\u0000\u0012\n \u0011*\u0004\u0018\u00010\u00100\u0010\u0018\u00010\u000f0\u000fH\u0001J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/ConnectivityOnLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "applicationContext", "Landroid/content/Context;", "lifecycleRegistry", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "(Landroid/content/Context;Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "emitCurrentConnectivity", "", "subscribe", "p0", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "subscribeToConnectivityChange", "toLifecycleState", "isConnected", "", "Landroid/net/ConnectivityManager;", "ConnectivityChangeBroadcastReceiver", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectivityOnLifecycle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectivityOnLifecycle.kt\ncom/tinder/scarlet/lifecycle/android/ConnectivityOnLifecycle\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,56:1\n1#2:57\n*E\n"})
public final class ConnectivityOnLifecycle implements Lifecycle {
    /* access modifiers changed from: private */
    @NotNull
    public final LifecycleRegistry lifecycleRegistry;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/ConnectivityOnLifecycle$ConnectivityChangeBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/tinder/scarlet/lifecycle/android/ConnectivityOnLifecycle;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class ConnectivityChangeBroadcastReceiver extends BroadcastReceiver {
        public ConnectivityChangeBroadcastReceiver() {
        }

        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                ConnectivityOnLifecycle.this.lifecycleRegistry.onNext(ConnectivityOnLifecycle.this.toLifecycleState(!extras.getBoolean("noConnectivity")));
            }
        }
    }

    public ConnectivityOnLifecycle(@NotNull Context context, @NotNull LifecycleRegistry lifecycleRegistry2) {
        Intrinsics.checkNotNullParameter(context, "applicationContext");
        Intrinsics.checkNotNullParameter(lifecycleRegistry2, "lifecycleRegistry");
        this.lifecycleRegistry = lifecycleRegistry2;
        emitCurrentConnectivity(context);
        subscribeToConnectivityChange(context);
    }

    private final void emitCurrentConnectivity(Context context) {
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.lifecycleRegistry.onNext(toLifecycleState(isConnected((ConnectivityManager) systemService)));
    }

    private final boolean isConnected(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private final void subscribeToConnectivityChange(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new ConnectivityChangeBroadcastReceiver(), intentFilter);
    }

    /* access modifiers changed from: private */
    public final Lifecycle.State toLifecycleState(boolean z2) {
        return z2 ? Lifecycle.State.Started.INSTANCE : Lifecycle.State.Stopped.AndAborted.INSTANCE;
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.lifecycleRegistry.combineWith(lifecycleArr);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.lifecycleRegistry.subscribe(subscriber);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ConnectivityOnLifecycle(Context context, LifecycleRegistry lifecycleRegistry2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? new LifecycleRegistry(0, 1, (DefaultConstructorMarker) null) : lifecycleRegistry2);
    }
}
