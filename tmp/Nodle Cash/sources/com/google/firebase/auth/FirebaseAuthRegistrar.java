package com.google.firebase.auth;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

@KeepForSdk
@Keep
public class FirebaseAuthRegistrar implements ComponentRegistrar {
    public static /* synthetic */ FirebaseAuth lambda$getComponents$0(Qualified qualified, Qualified qualified2, Qualified qualified3, Qualified qualified4, Qualified qualified5, ComponentContainer componentContainer) {
        return new zzaa((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(InteropAppCheckTokenProvider.class), componentContainer.getProvider(HeartBeatController.class), (Executor) componentContainer.get(qualified), (Executor) componentContainer.get(qualified2), (Executor) componentContainer.get(qualified3), (ScheduledExecutorService) componentContainer.get(qualified4), (Executor) componentContainer.get(qualified5));
    }

    @NonNull
    @Keep
    public List<Component<?>> getComponents() {
        Class<Executor> cls = Executor.class;
        Qualified<Executor> qualified = Qualified.qualified(Background.class, cls);
        Qualified<Blocking> qualified2 = Qualified.qualified(Blocking.class, cls);
        Class<Lightweight> cls2 = Lightweight.class;
        Qualified<Lightweight> qualified3 = Qualified.qualified(cls2, cls);
        Qualified<ScheduledExecutorService> qualified4 = Qualified.qualified(cls2, ScheduledExecutorService.class);
        Qualified<UiThread> qualified5 = Qualified.qualified(UiThread.class, cls);
        return Arrays.asList(new Component[]{Component.builder(FirebaseAuth.class, (Class<? super T>[]) new Class[]{InternalAuthProvider.class}).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.requiredProvider((Class<?>) HeartBeatController.class)).add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) qualified2)).add(Dependency.required((Qualified<?>) qualified3)).add(Dependency.required((Qualified<?>) qualified4)).add(Dependency.required((Qualified<?>) qualified5)).add(Dependency.optionalProvider((Class<?>) InteropAppCheckTokenProvider.class)).factory(new zzag(qualified, qualified2, qualified3, qualified4, qualified5)).build(), HeartBeatConsumerComponent.create(), LibraryVersionComponent.create("fire-auth", "23.0.0")});
    }
}
