package com.google.mlkit.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.internal.model.zzg;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import java.util.List;

@KeepForSdk
public class CommonComponentRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @NonNull
    public final List getComponents() {
        Component<?> component = SharedPrefManager.COMPONENT;
        Class<MlKitContext> cls = MlKitContext.class;
        Component<ModelFileHelper> build = Component.builder(ModelFileHelper.class).add(Dependency.required((Class<?>) cls)).factory(new zza()).build();
        Class<MlKitThreadPool> cls2 = MlKitThreadPool.class;
        Component<MlKitThreadPool> build2 = Component.builder(cls2).factory(new zzb()).build();
        Class<RemoteModelManager.RemoteModelManagerRegistration> cls3 = RemoteModelManager.RemoteModelManagerRegistration.class;
        Component<RemoteModelManager> build3 = Component.builder(RemoteModelManager.class).add(Dependency.setOf((Class<?>) cls3)).factory(new zzc()).build();
        Component<ExecutorSelector> build4 = Component.builder(ExecutorSelector.class).add(Dependency.requiredProvider((Class<?>) cls2)).factory(new zzd()).build();
        Class<Cleaner> cls4 = Cleaner.class;
        Component<Cleaner> build5 = Component.builder(cls4).factory(new zze()).build();
        Component<CloseGuard.Factory> build6 = Component.builder(CloseGuard.Factory.class).add(Dependency.required((Class<?>) cls4)).factory(new zzf()).build();
        Class<zzg> cls5 = zzg.class;
        return zzaf.zzi(component, build, build2, build3, build4, build5, build6, Component.builder(cls5).add(Dependency.required((Class<?>) cls)).factory(new zzg()).build(), Component.intoSetBuilder(cls3).add(Dependency.requiredProvider((Class<?>) cls5)).factory(new zzh()).build());
    }
}
