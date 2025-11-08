package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.zag;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {
    @NonNull
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @NonNull
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    @NonNull
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    @Nullable
    public static Dialog getErrorDialog(int i3, @NonNull Activity activity, int i4) {
        return getErrorDialog(i3, activity, i4, (DialogInterface.OnCancelListener) null);
    }

    @NonNull
    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i3, @NonNull Context context, int i4) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, i3, i4);
    }

    @NonNull
    @Deprecated
    public static String getErrorString(int i3) {
        return GooglePlayServicesUtilLight.getErrorString(i3);
    }

    @NonNull
    public static Context getRemoteContext(@NonNull Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }

    @NonNull
    public static Resources getRemoteResource(@NonNull Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    @ResultIgnorabilityUnspecified
    @Deprecated
    @HideFirstParty
    public static int isGooglePlayServicesAvailable(@NonNull Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i3) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i3);
    }

    @ResultIgnorabilityUnspecified
    @Deprecated
    public static boolean showErrorDialogFragment(int i3, @NonNull Activity activity, int i4) {
        return showErrorDialogFragment(i3, activity, i4, (DialogInterface.OnCancelListener) null);
    }

    @Deprecated
    public static void showErrorNotification(int i3, @NonNull Context context) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i3) || GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, i3)) {
            instance.zaf(context);
        } else {
            instance.showErrorNotification(context, i3);
        }
    }

    @Deprecated
    @Nullable
    public static Dialog getErrorDialog(int i3, @NonNull Activity activity, int i4, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, i3)) {
            i3 = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity, i3, i4, onCancelListener);
    }

    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(@NonNull Context context, int i3) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i3);
    }

    @InlineMe(imports = {"androidx.fragment.app.Fragment", "com.google.android.gms.common.GooglePlayServicesUtil"}, replacement = "GooglePlayServicesUtil.showErrorDialogFragment(errorCode, activity, (Fragment) null, requestCode, cancelListener)")
    @ResultIgnorabilityUnspecified
    @Deprecated
    public static boolean showErrorDialogFragment(int i3, @NonNull Activity activity, int i4, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        return showErrorDialogFragment(i3, activity, (Fragment) null, i4, onCancelListener);
    }

    @ResultIgnorabilityUnspecified
    public static boolean showErrorDialogFragment(int i3, @NonNull Activity activity, @Nullable Fragment fragment, int i4, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, i3)) {
            i3 = 18;
        }
        int i5 = i3;
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (fragment == null) {
            return instance.showErrorDialogFragment(activity, i5, i4, onCancelListener);
        }
        Dialog zaa = instance.zaa(activity, i5, zag.zac(fragment, GoogleApiAvailability.getInstance().getErrorResolutionIntent(activity, i5, "d"), i4), onCancelListener, (DialogInterface.OnClickListener) null);
        if (zaa == null) {
            return false;
        }
        instance.zad(activity, zaa, GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }
}
