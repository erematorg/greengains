package com.vmadalin.easypermissions;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Size;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted;
import com.vmadalin.easypermissions.helpers.base.PermissionsHelper;
import com.vmadalin.easypermissions.models.PermissionRequest;
import com.vmadalin.easypermissions.utils.AnnotationsUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002'(B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0014\b\u0001\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0002\u0010\nJ-\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bH\u0002¢\u0006\u0002\u0010\u0010JA\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\b\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0007J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\tH\u0007J\u0018\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J;\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0001\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0002\u0010 J\u0018\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J;\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0001\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0002\u0010!J+\u0010\"\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0014\b\u0001\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0002\u0010#J+\u0010\"\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u001b2\u0014\b\u0001\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0&H\u0007J\u001e\u0010%\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u001b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0&H\u0007¨\u0006)"}, d2 = {"Lcom/vmadalin/easypermissions/EasyPermissions;", "", "()V", "hasPermissions", "", "context", "Landroid/content/Context;", "perms", "", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "notifyAlreadyHasPermissions", "", "receiver", "requestCode", "", "(Ljava/lang/Object;I[Ljava/lang/String;)V", "onRequestPermissionsResult", "permissions", "grantResults", "", "receivers", "(I[Ljava/lang/String;[I[Ljava/lang/Object;)V", "permissionPermanentlyDenied", "host", "Landroid/app/Activity;", "deniedPerms", "Landroidx/fragment/app/Fragment;", "requestPermissions", "request", "Lcom/vmadalin/easypermissions/models/PermissionRequest;", "rationale", "(Landroid/app/Activity;Ljava/lang/String;I[Ljava/lang/String;)V", "(Landroidx/fragment/app/Fragment;Ljava/lang/String;I[Ljava/lang/String;)V", "somePermissionDenied", "(Landroid/app/Activity;[Ljava/lang/String;)Z", "(Landroidx/fragment/app/Fragment;[Ljava/lang/String;)Z", "somePermissionPermanentlyDenied", "", "PermissionCallbacks", "RationaleCallbacks", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
public final class EasyPermissions {
    @NotNull
    public static final EasyPermissions INSTANCE = new EasyPermissions();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u001e\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&¨\u0006\n"}, d2 = {"Lcom/vmadalin/easypermissions/EasyPermissions$PermissionCallbacks;", "Landroidx/core/app/ActivityCompat$OnRequestPermissionsResultCallback;", "onPermissionsDenied", "", "requestCode", "", "perms", "", "", "onPermissionsGranted", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsDenied(int i3, @NotNull List<String> list);

        void onPermissionsGranted(int i3, @NotNull List<String> list);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/vmadalin/easypermissions/EasyPermissions$RationaleCallbacks;", "", "onRationaleAccepted", "", "requestCode", "", "onRationaleDenied", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
    public interface RationaleCallbacks {
        void onRationaleAccepted(int i3);

        void onRationaleDenied(int i3);
    }

    private EasyPermissions() {
    }

    @JvmStatic
    public static final boolean hasPermissions(@Nullable Context context, @Size(min = 1) @NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "perms");
        if (context != null) {
            for (String checkSelfPermission : strArr) {
                if (ContextCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalArgumentException("Can't check permissions for null context");
    }

    private final void notifyAlreadyHasPermissions(Object obj, int i3, String[] strArr) {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            iArr[i4] = 0;
        }
        onRequestPermissionsResult(i3, strArr, iArr, obj);
    }

    @JvmStatic
    public static final void onRequestPermissionsResult(int i3, @NotNull String[] strArr, @NotNull int[] iArr, @NotNull Object... objArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        Intrinsics.checkNotNullParameter(objArr, "receivers");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair : ArraysKt.zip(iArr, (R[]) strArr)) {
            Integer valueOf = Integer.valueOf(((Number) pair.getFirst()).intValue());
            Object obj = linkedHashMap.get(valueOf);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(valueOf, obj);
            }
            ((List) obj).add((String) pair.getSecond());
        }
        List list = (List) linkedHashMap.get(0);
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        List list2 = (List) linkedHashMap.get(-1);
        if (list2 == null) {
            list2 = CollectionsKt.emptyList();
        }
        for (PermissionCallbacks permissionCallbacks : objArr) {
            if (permissionCallbacks instanceof PermissionCallbacks) {
                if (!list.isEmpty()) {
                    permissionCallbacks.onPermissionsGranted(i3, list);
                }
                if (!list2.isEmpty()) {
                    permissionCallbacks.onPermissionsDenied(i3, list2);
                }
            }
            if (!list.isEmpty() && list2.isEmpty()) {
                AnnotationsUtils.INSTANCE.notifyAnnotatedMethods$easypermissions_ktx_release(permissionCallbacks, Reflection.getOrCreateKotlinClass(AfterPermissionGranted.class), new EasyPermissions$onRequestPermissionsResult$$inlined$forEach$lambda$1(list, i3, list2));
            }
        }
    }

    @JvmStatic
    public static final boolean permissionPermanentlyDenied(@NotNull Activity activity, @NotNull String str) {
        Intrinsics.checkNotNullParameter(activity, "host");
        Intrinsics.checkNotNullParameter(str, "deniedPerms");
        return PermissionsHelper.Companion.newInstance(activity).permissionPermanentlyDenied(str);
    }

    @JvmStatic
    public static final void requestPermissions(@NotNull Activity activity, @NotNull String str, int i3, @Size(min = 1) @NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(activity, "host");
        Intrinsics.checkNotNullParameter(str, "rationale");
        Intrinsics.checkNotNullParameter(strArr, "perms");
        requestPermissions(activity, new PermissionRequest.Builder(activity).code(i3).perms(strArr).rationale(str).build());
    }

    @JvmStatic
    public static final boolean somePermissionDenied(@NotNull Activity activity, @Size(min = 1) @NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(activity, "host");
        Intrinsics.checkNotNullParameter(strArr, "perms");
        return PermissionsHelper.Companion.newInstance(activity).somePermissionDenied(strArr);
    }

    @JvmStatic
    public static final boolean somePermissionPermanentlyDenied(@NotNull Activity activity, @NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(activity, "host");
        Intrinsics.checkNotNullParameter(list, "deniedPerms");
        return PermissionsHelper.Companion.newInstance(activity).somePermissionPermanentlyDenied(list);
    }

    @JvmStatic
    public static final boolean permissionPermanentlyDenied(@NotNull Fragment fragment, @NotNull String str) {
        Intrinsics.checkNotNullParameter(fragment, "host");
        Intrinsics.checkNotNullParameter(str, "deniedPerms");
        return PermissionsHelper.Companion.newInstance(fragment).permissionPermanentlyDenied(str);
    }

    @JvmStatic
    public static final boolean somePermissionDenied(@NotNull Fragment fragment, @Size(min = 1) @NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(fragment, "host");
        Intrinsics.checkNotNullParameter(strArr, "perms");
        return PermissionsHelper.Companion.newInstance(fragment).somePermissionDenied(strArr);
    }

    @JvmStatic
    public static final boolean somePermissionPermanentlyDenied(@NotNull Fragment fragment, @NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(fragment, "host");
        Intrinsics.checkNotNullParameter(list, "deniedPerms");
        return PermissionsHelper.Companion.newInstance(fragment).somePermissionPermanentlyDenied(list);
    }

    @JvmStatic
    public static final void requestPermissions(@NotNull Fragment fragment, @NotNull String str, int i3, @Size(min = 1) @NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(fragment, "host");
        Intrinsics.checkNotNullParameter(str, "rationale");
        Intrinsics.checkNotNullParameter(strArr, "perms");
        requestPermissions(fragment, new PermissionRequest.Builder(fragment.getContext()).code(i3).perms(strArr).rationale(str).build());
    }

    @JvmStatic
    public static final void requestPermissions(@NotNull Fragment fragment, @NotNull PermissionRequest permissionRequest) {
        Intrinsics.checkNotNullParameter(fragment, "host");
        Intrinsics.checkNotNullParameter(permissionRequest, "request");
        Context context = fragment.getContext();
        String[] perms = permissionRequest.getPerms();
        if (hasPermissions(context, (String[]) Arrays.copyOf(perms, perms.length))) {
            INSTANCE.notifyAlreadyHasPermissions(fragment, permissionRequest.getCode(), permissionRequest.getPerms());
        } else {
            PermissionsHelper.Companion.newInstance(fragment).requestPermissions(permissionRequest);
        }
    }

    @JvmStatic
    public static final void requestPermissions(@NotNull Activity activity, @NotNull PermissionRequest permissionRequest) {
        Intrinsics.checkNotNullParameter(activity, "host");
        Intrinsics.checkNotNullParameter(permissionRequest, "request");
        String[] perms = permissionRequest.getPerms();
        if (hasPermissions(activity, (String[]) Arrays.copyOf(perms, perms.length))) {
            INSTANCE.notifyAlreadyHasPermissions(activity, permissionRequest.getCode(), permissionRequest.getPerms());
        } else {
            PermissionsHelper.Companion.newInstance(activity).requestPermissions(permissionRequest);
        }
    }
}
