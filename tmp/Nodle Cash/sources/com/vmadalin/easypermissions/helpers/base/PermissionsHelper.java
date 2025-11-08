package com.vmadalin.easypermissions.helpers.base;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.vmadalin.easypermissions.helpers.ActivityPermissionsHelper;
import com.vmadalin.easypermissions.helpers.AppCompatActivityPermissionsHelper;
import com.vmadalin.easypermissions.helpers.FragmentPermissionsHelper;
import com.vmadalin.easypermissions.models.PermissionRequest;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\b&\u0018\u0000 #*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001#B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013H&¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u001d\u0010\u001c\u001a\u00020\u00172\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013H\u0002¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H&J\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH&J\u001b\u0010 \u001a\u00020\u00172\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013¢\u0006\u0002\u0010\u001dJ\u0014\u0010!\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\"R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006$"}, d2 = {"Lcom/vmadalin/easypermissions/helpers/base/PermissionsHelper;", "T", "", "host", "(Ljava/lang/Object;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getHost", "()Ljava/lang/Object;", "Ljava/lang/Object;", "directRequestPermissions", "", "requestCode", "", "perms", "", "", "(I[Ljava/lang/String;)V", "permissionPermanentlyDenied", "", "perm", "requestPermissions", "permissionRequest", "Lcom/vmadalin/easypermissions/models/PermissionRequest;", "shouldShowRationale", "([Ljava/lang/String;)Z", "shouldShowRequestPermissionRationale", "showRequestPermissionRationale", "somePermissionDenied", "somePermissionPermanentlyDenied", "", "Companion", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
public abstract class PermissionsHelper<T> {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final T host;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/vmadalin/easypermissions/helpers/base/PermissionsHelper$Companion;", "", "()V", "newInstance", "Lcom/vmadalin/easypermissions/helpers/base/PermissionsHelper;", "Landroid/app/Activity;", "host", "Landroidx/fragment/app/Fragment;", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final PermissionsHelper<? extends Activity> newInstance(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "host");
            AppCompatActivity appCompatActivity = (AppCompatActivity) (!(activity instanceof AppCompatActivity) ? null : activity);
            if (appCompatActivity != null) {
                return new AppCompatActivityPermissionsHelper(appCompatActivity);
            }
            return new ActivityPermissionsHelper(activity);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PermissionsHelper<Fragment> newInstance(@NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "host");
            return new FragmentPermissionsHelper(fragment);
        }
    }

    public PermissionsHelper(T t2) {
        this.host = t2;
    }

    private final boolean shouldShowRationale(String[] strArr) {
        for (String shouldShowRequestPermissionRationale : strArr) {
            if (shouldShowRequestPermissionRationale(shouldShowRequestPermissionRationale)) {
                return true;
            }
        }
        return false;
    }

    public abstract void directRequestPermissions(int i3, @NotNull String[] strArr);

    @Nullable
    public abstract Context getContext();

    public final T getHost() {
        return this.host;
    }

    public final boolean permissionPermanentlyDenied(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "perm");
        return !shouldShowRequestPermissionRationale(str);
    }

    public final void requestPermissions(@NotNull PermissionRequest permissionRequest) {
        Intrinsics.checkNotNullParameter(permissionRequest, "permissionRequest");
        if (shouldShowRationale(permissionRequest.getPerms())) {
            showRequestPermissionRationale(permissionRequest);
        } else {
            directRequestPermissions(permissionRequest.getCode(), permissionRequest.getPerms());
        }
    }

    public abstract void setContext(@Nullable Context context);

    public abstract boolean shouldShowRequestPermissionRationale(@NotNull String str);

    public abstract void showRequestPermissionRationale(@NotNull PermissionRequest permissionRequest);

    public final boolean somePermissionDenied(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "perms");
        return shouldShowRationale(strArr);
    }

    public final boolean somePermissionPermanentlyDenied(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "perms");
        Iterable<String> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (String permissionPermanentlyDenied : iterable) {
            if (permissionPermanentlyDenied(permissionPermanentlyDenied)) {
                return true;
            }
        }
        return false;
    }
}
