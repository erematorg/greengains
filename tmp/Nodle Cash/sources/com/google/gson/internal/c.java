package com.google.gson.internal;

import android.app.Activity;
import android.net.Uri;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import com.adjust.sdk.OnDeeplinkResponseListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.reown.sign.storage.data.dao.temp.b;
import io.nodle.cash.core.android.permission.PermissionManager;
import io.nodle.cash.ui.NodleApp;
import io.nodle.cash.ui.feature.wallet.backup.BackupWalletInfoSheetFragment;
import java.lang.reflect.Constructor;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.tasks.TasksKt;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.Functions;
import org.slf4j.Logger;

public final /* synthetic */ class c implements ObjectConstructor, OnSuccessListener, OnFailureListener, OnDeeplinkResponseListener, ActivityResultCallback, OnCompleteListener, Functions.FailableSupplier, HttpLoggingInterceptor.Logger {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7186a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7187b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f7186a = i3;
        this.f7187b = obj;
    }

    public Object construct() {
        int i3 = this.f7186a;
        Object obj = this.f7187b;
        switch (i3) {
            case 0:
                return ConstructorConstructor.lambda$newDefaultConstructor$9((Constructor) obj);
            default:
                return ConstructorConstructor.lambda$newUnsafeAllocator$19((Class) obj);
        }
    }

    public Object get() {
        return ((Functions.FailableCallable) this.f7187b).call();
    }

    public boolean launchReceivedDeeplink(Uri uri) {
        return NodleApp.initAdjust$lambda$2$lambda$1((NodleApp) this.f7187b, uri);
    }

    public void log(String str) {
        ((Logger) this.f7187b).debug(str);
    }

    public void onActivityResult(Object obj) {
        BackupWalletInfoSheetFragment.startWalletBackup$lambda$0((BackupWalletInfoSheetFragment) this.f7187b, (ActivityResult) obj);
    }

    public void onComplete(Task task) {
        TasksKt.asDeferredImpl$lambda$1((CompletableDeferred) this.f7187b, task);
    }

    public void onFailure(Exception exc) {
        PermissionManager.enableLocation$lambda$9$lambda$8((Activity) this.f7187b, exc);
    }

    public void onSuccess(Object obj) {
        PermissionManager.enableLocation$lambda$9$lambda$7((b) this.f7187b, obj);
    }
}
