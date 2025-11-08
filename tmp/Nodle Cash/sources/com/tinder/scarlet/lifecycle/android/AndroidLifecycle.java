package com.tinder.scarlet.lifecycle.android;

import android.app.Application;
import androidx.lifecycle.LifecycleOwner;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0007J\"\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0007J\"\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/tinder/scarlet/lifecycle/android/AndroidLifecycle;", "", "()V", "ACTIVITY_THROTTLE_TIMEOUT_MILLIS", "", "APPLICATION_THROTTLE_TIMEOUT_MILLIS", "ofApplicationForeground", "Lcom/tinder/scarlet/Lifecycle;", "application", "Landroid/app/Application;", "throttleTimeoutMillis", "ofLifecycleOwnerForeground", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "ofServiceStarted", "scarlet-lifecycle-android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidLifecycle {
    private static final long ACTIVITY_THROTTLE_TIMEOUT_MILLIS = 500;
    private static final long APPLICATION_THROTTLE_TIMEOUT_MILLIS = 1000;
    @NotNull
    public static final AndroidLifecycle INSTANCE = new AndroidLifecycle();

    private AndroidLifecycle() {
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final Lifecycle ofApplicationForeground(@NotNull Application application) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        return ofApplicationForeground$default(application, 0, 2, (Object) null);
    }

    public static /* synthetic */ Lifecycle ofApplicationForeground$default(Application application, long j2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            j2 = 1000;
        }
        return ofApplicationForeground(application, j2);
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final Lifecycle ofLifecycleOwnerForeground(@NotNull Application application, @NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        return ofLifecycleOwnerForeground$default(application, lifecycleOwner, 0, 4, (Object) null);
    }

    public static /* synthetic */ Lifecycle ofLifecycleOwnerForeground$default(Application application, LifecycleOwner lifecycleOwner, long j2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            j2 = 500;
        }
        return ofLifecycleOwnerForeground(application, lifecycleOwner, j2);
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final Lifecycle ofServiceStarted(@NotNull Application application, @NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        return ofServiceStarted$default(application, lifecycleOwner, 0, 4, (Object) null);
    }

    public static /* synthetic */ Lifecycle ofServiceStarted$default(Application application, LifecycleOwner lifecycleOwner, long j2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            j2 = 500;
        }
        return ofServiceStarted(application, lifecycleOwner, j2);
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final Lifecycle ofApplicationForeground(@NotNull Application application, long j2) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        return new ApplicationResumedLifecycle(application, new LifecycleRegistry(j2)).combineWith(new ConnectivityOnLifecycle(application, (LifecycleRegistry) null, 2, (DefaultConstructorMarker) null));
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final Lifecycle ofLifecycleOwnerForeground(@NotNull Application application, @NotNull LifecycleOwner lifecycleOwner, long j2) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        return new LifecycleOwnerResumedLifecycle(lifecycleOwner, new LifecycleRegistry(j2)).combineWith(new ConnectivityOnLifecycle(application, (LifecycleRegistry) null, 2, (DefaultConstructorMarker) null));
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final Lifecycle ofServiceStarted(@NotNull Application application, @NotNull LifecycleOwner lifecycleOwner, long j2) {
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        return new ServiceStartedLifecycle(lifecycleOwner, new LifecycleRegistry(j2)).combineWith(new ConnectivityOnLifecycle(application, (LifecycleRegistry) null, 2, (DefaultConstructorMarker) null));
    }
}
