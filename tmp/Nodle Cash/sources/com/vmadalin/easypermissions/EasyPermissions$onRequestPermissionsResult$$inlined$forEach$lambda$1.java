package com.vmadalin.easypermissions;

import com.vmadalin.easypermissions.annotations.AfterPermissionGranted;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/vmadalin/easypermissions/annotations/AfterPermissionGranted;", "invoke", "com/vmadalin/easypermissions/EasyPermissions$onRequestPermissionsResult$1$1"}, k = 3, mv = {1, 4, 2})
public final class EasyPermissions$onRequestPermissionsResult$$inlined$forEach$lambda$1 extends Lambda implements Function1<AfterPermissionGranted, Boolean> {
    final /* synthetic */ List $deniedList$inlined;
    final /* synthetic */ List $grantedList$inlined;
    final /* synthetic */ int $requestCode$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EasyPermissions$onRequestPermissionsResult$$inlined$forEach$lambda$1(List list, int i3, List list2) {
        super(1);
        this.$grantedList$inlined = list;
        this.$requestCode$inlined = i3;
        this.$deniedList$inlined = list2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((AfterPermissionGranted) obj));
    }

    public final boolean invoke(@NotNull AfterPermissionGranted afterPermissionGranted) {
        Intrinsics.checkNotNullParameter(afterPermissionGranted, "it");
        return afterPermissionGranted.value() == this.$requestCode$inlined;
    }
}
