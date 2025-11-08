package com.vmadalin.easypermissions.helpers;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import com.vmadalin.easypermissions.dialogs.RationaleDialog;
import com.vmadalin.easypermissions.helpers.base.PermissionsHelper;
import com.vmadalin.easypermissions.models.PermissionRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J%\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/vmadalin/easypermissions/helpers/ActivityPermissionsHelper;", "Lcom/vmadalin/easypermissions/helpers/base/PermissionsHelper;", "Landroid/app/Activity;", "host", "(Landroid/app/Activity;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "directRequestPermissions", "", "requestCode", "", "perms", "", "", "(I[Ljava/lang/String;)V", "shouldShowRequestPermissionRationale", "", "perm", "showRequestPermissionRationale", "permissionRequest", "Lcom/vmadalin/easypermissions/models/PermissionRequest;", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
public final class ActivityPermissionsHelper extends PermissionsHelper<Activity> {
    @Nullable
    private Context context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ActivityPermissionsHelper(@NotNull Activity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "host");
        this.context = activity;
    }

    public void directRequestPermissions(int i3, @NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "perms");
        ActivityCompat.requestPermissions((Activity) getHost(), strArr, i3);
    }

    @Nullable
    public Context getContext() {
        return this.context;
    }

    public void setContext(@Nullable Context context2) {
        this.context = context2;
    }

    public boolean shouldShowRequestPermissionRationale(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "perm");
        return ActivityCompat.shouldShowRequestPermissionRationale((Activity) getHost(), str);
    }

    public void showRequestPermissionRationale(@NotNull PermissionRequest permissionRequest) {
        Intrinsics.checkNotNullParameter(permissionRequest, "permissionRequest");
        new RationaleDialog((Context) getHost(), permissionRequest).showDialog();
    }
}
