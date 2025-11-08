package com.reown.android.utils;

import android.content.pm.PackageManager;
import android.os.Build;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"isWalletInstalled", "", "Landroid/content/pm/PackageManager;", "appPackage", "", "isPackageInstalled", "packageName", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PackageManagerExtensionsKt {
    public static final boolean isPackageInstalled(@NotNull PackageManager packageManager, @NotNull String str) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of(0));
            } else {
                packageManager.getPackageInfo(str, 0);
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static final boolean isWalletInstalled(@NotNull PackageManager packageManager, @Nullable String str) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        try {
            Intrinsics.checkNotNull(str);
            return isPackageInstalled(packageManager, str);
        } catch (Exception unused) {
            return false;
        }
    }
}
