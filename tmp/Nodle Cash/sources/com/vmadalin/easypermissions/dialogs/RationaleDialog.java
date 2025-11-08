package com.vmadalin.easypermissions.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.vmadalin.easypermissions.EasyPermissions;
import com.vmadalin.easypermissions.helpers.base.PermissionsHelper;
import com.vmadalin.easypermissions.models.PermissionRequest;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0010J\u0006\u0010\u0016\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/vmadalin/easypermissions/dialogs/RationaleDialog;", "Landroid/content/DialogInterface$OnClickListener;", "context", "Landroid/content/Context;", "model", "Lcom/vmadalin/easypermissions/models/PermissionRequest;", "(Landroid/content/Context;Lcom/vmadalin/easypermissions/models/PermissionRequest;)V", "permissionCallbacks", "Lcom/vmadalin/easypermissions/EasyPermissions$PermissionCallbacks;", "getPermissionCallbacks", "()Lcom/vmadalin/easypermissions/EasyPermissions$PermissionCallbacks;", "rationaleCallbacks", "Lcom/vmadalin/easypermissions/EasyPermissions$RationaleCallbacks;", "getRationaleCallbacks", "()Lcom/vmadalin/easypermissions/EasyPermissions$RationaleCallbacks;", "onClick", "", "dialogInterface", "Landroid/content/DialogInterface;", "buttonType", "", "showCompatDialog", "showDialog", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
public final class RationaleDialog implements DialogInterface.OnClickListener {
    private final Context context;
    private final PermissionRequest model;

    public RationaleDialog(@NotNull Context context2, @NotNull PermissionRequest permissionRequest) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(permissionRequest, "model");
        this.context = context2;
        this.model = permissionRequest;
    }

    private final EasyPermissions.PermissionCallbacks getPermissionCallbacks() {
        Context context2 = this.context;
        if (context2 instanceof EasyPermissions.PermissionCallbacks) {
            return (EasyPermissions.PermissionCallbacks) context2;
        }
        return null;
    }

    private final EasyPermissions.RationaleCallbacks getRationaleCallbacks() {
        Context context2 = this.context;
        if (context2 instanceof EasyPermissions.RationaleCallbacks) {
            return (EasyPermissions.RationaleCallbacks) context2;
        }
        return null;
    }

    public void onClick(@Nullable DialogInterface dialogInterface, int i3) {
        if (i3 == -2) {
            EasyPermissions.RationaleCallbacks rationaleCallbacks = getRationaleCallbacks();
            if (rationaleCallbacks != null) {
                rationaleCallbacks.onRationaleDenied(this.model.getCode());
            }
            EasyPermissions.PermissionCallbacks permissionCallbacks = getPermissionCallbacks();
            if (permissionCallbacks != null) {
                permissionCallbacks.onPermissionsDenied(this.model.getCode(), ArraysKt.toList((T[]) this.model.getPerms()));
            }
        } else if (i3 == -1) {
            EasyPermissions.RationaleCallbacks rationaleCallbacks2 = getRationaleCallbacks();
            if (rationaleCallbacks2 != null) {
                rationaleCallbacks2.onRationaleAccepted(this.model.getCode());
            }
            Context context2 = this.context;
            if (context2 instanceof Activity) {
                PermissionsHelper.Companion.newInstance((Activity) context2).directRequestPermissions(this.model.getCode(), this.model.getPerms());
            } else if (context2 instanceof AppCompatActivity) {
                PermissionsHelper.Companion.newInstance((Activity) context2).directRequestPermissions(this.model.getCode(), this.model.getPerms());
            }
        }
    }

    public final void showCompatDialog() {
        new AlertDialog.Builder(this.context, this.model.getTheme()).setCancelable(false).setMessage((CharSequence) this.model.getRationale()).setPositiveButton((CharSequence) this.model.getPositiveButtonText(), (DialogInterface.OnClickListener) this).setNegativeButton((CharSequence) this.model.getNegativeButtonText(), (DialogInterface.OnClickListener) this).show();
    }

    public final void showDialog() {
        new AlertDialog.Builder(this.context, this.model.getTheme()).setCancelable(false).setMessage(this.model.getRationale()).setPositiveButton(this.model.getPositiveButtonText(), this).setNegativeButton(this.model.getNegativeButtonText(), this).show();
    }
}
