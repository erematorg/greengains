package com.google.firebase.sessions;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a0\u0012,\u0012*\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0014\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u00050\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar;", "Lcom/google/firebase/components/ComponentRegistrar;", "()V", "getComponents", "", "Lcom/google/firebase/components/Component;", "", "kotlin.jvm.PlatformType", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Keep
public final class FirebaseSessionsRegistrar implements ComponentRegistrar {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String LIBRARY_NAME = "fire-sessions";
    @NotNull
    private static final Qualified<CoroutineDispatcher> backgroundDispatcher;
    @NotNull
    private static final Qualified<CoroutineDispatcher> blockingDispatcher;
    @NotNull
    private static final Qualified<FirebaseApp> firebaseApp;
    @NotNull
    private static final Qualified<FirebaseInstallationsApi> firebaseInstallationsApi;
    @NotNull
    private static final Qualified<SessionLifecycleServiceBinder> sessionLifecycleServiceBinder;
    @NotNull
    private static final Qualified<SessionsSettings> sessionsSettings;
    @NotNull
    private static final Qualified<TransportFactory> transportFactory;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000b0\u000b0\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\r0\r0\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u000f0\u000f0\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00110\u00110\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00130\u00130\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar$Companion;", "", "()V", "LIBRARY_NAME", "", "backgroundDispatcher", "Lcom/google/firebase/components/Qualified;", "Lkotlinx/coroutines/CoroutineDispatcher;", "kotlin.jvm.PlatformType", "blockingDispatcher", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "firebaseInstallationsApi", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "sessionLifecycleServiceBinder", "Lcom/google/firebase/sessions/SessionLifecycleServiceBinder;", "sessionsSettings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "transportFactory", "Lcom/google/android/datatransport/TransportFactory;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Qualified<FirebaseApp> unqualified = Qualified.unqualified(FirebaseApp.class);
        Intrinsics.checkNotNullExpressionValue(unqualified, "unqualified(FirebaseApp::class.java)");
        firebaseApp = unqualified;
        Qualified<FirebaseInstallationsApi> unqualified2 = Qualified.unqualified(FirebaseInstallationsApi.class);
        Intrinsics.checkNotNullExpressionValue(unqualified2, "unqualified(FirebaseInstallationsApi::class.java)");
        firebaseInstallationsApi = unqualified2;
        Class<CoroutineDispatcher> cls = CoroutineDispatcher.class;
        Qualified<CoroutineDispatcher> qualified = Qualified.qualified(Background.class, cls);
        Intrinsics.checkNotNullExpressionValue(qualified, "qualified(Background::cl…neDispatcher::class.java)");
        backgroundDispatcher = qualified;
        Qualified<CoroutineDispatcher> qualified2 = Qualified.qualified(Blocking.class, cls);
        Intrinsics.checkNotNullExpressionValue(qualified2, "qualified(Blocking::clas…neDispatcher::class.java)");
        blockingDispatcher = qualified2;
        Qualified<TransportFactory> unqualified3 = Qualified.unqualified(TransportFactory.class);
        Intrinsics.checkNotNullExpressionValue(unqualified3, "unqualified(TransportFactory::class.java)");
        transportFactory = unqualified3;
        Qualified<SessionsSettings> unqualified4 = Qualified.unqualified(SessionsSettings.class);
        Intrinsics.checkNotNullExpressionValue(unqualified4, "unqualified(SessionsSettings::class.java)");
        sessionsSettings = unqualified4;
        Qualified<SessionLifecycleServiceBinder> unqualified5 = Qualified.unqualified(SessionLifecycleServiceBinder.class);
        Intrinsics.checkNotNullExpressionValue(unqualified5, "unqualified(SessionLifec…erviceBinder::class.java)");
        sessionLifecycleServiceBinder = unqualified5;
    }

    /* access modifiers changed from: private */
    public static final FirebaseSessions getComponents$lambda$0(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        Object obj2 = componentContainer.get(sessionsSettings);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[sessionsSettings]");
        Object obj3 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[backgroundDispatcher]");
        Object obj4 = componentContainer.get(sessionLifecycleServiceBinder);
        Intrinsics.checkNotNullExpressionValue(obj4, "container[sessionLifecycleServiceBinder]");
        return new FirebaseSessions((FirebaseApp) obj, (SessionsSettings) obj2, (CoroutineContext) obj3, (SessionLifecycleServiceBinder) obj4);
    }

    /* access modifiers changed from: private */
    public static final SessionGenerator getComponents$lambda$1(ComponentContainer componentContainer) {
        return new SessionGenerator(WallClock.INSTANCE, (Function0) null, 2, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final SessionFirelogPublisher getComponents$lambda$2(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        Object obj2 = componentContainer.get(firebaseInstallationsApi);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[firebaseInstallationsApi]");
        Object obj3 = componentContainer.get(sessionsSettings);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[sessionsSettings]");
        Provider<TransportFactory> provider = componentContainer.getProvider(transportFactory);
        Intrinsics.checkNotNullExpressionValue(provider, "container.getProvider(transportFactory)");
        EventGDTLogger eventGDTLogger = new EventGDTLogger(provider);
        Object obj4 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj4, "container[backgroundDispatcher]");
        return new SessionFirelogPublisherImpl((FirebaseApp) obj, (FirebaseInstallationsApi) obj2, (SessionsSettings) obj3, eventGDTLogger, (CoroutineContext) obj4);
    }

    /* access modifiers changed from: private */
    public static final SessionsSettings getComponents$lambda$3(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        Object obj2 = componentContainer.get(blockingDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[blockingDispatcher]");
        Object obj3 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[backgroundDispatcher]");
        Object obj4 = componentContainer.get(firebaseInstallationsApi);
        Intrinsics.checkNotNullExpressionValue(obj4, "container[firebaseInstallationsApi]");
        return new SessionsSettings((FirebaseApp) obj, (CoroutineContext) obj2, (CoroutineContext) obj3, (FirebaseInstallationsApi) obj4);
    }

    /* access modifiers changed from: private */
    public static final SessionDatastore getComponents$lambda$4(ComponentContainer componentContainer) {
        Context applicationContext = ((FirebaseApp) componentContainer.get(firebaseApp)).getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "container[firebaseApp].applicationContext");
        Object obj = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj, "container[backgroundDispatcher]");
        return new SessionDatastoreImpl(applicationContext, (CoroutineContext) obj);
    }

    /* access modifiers changed from: private */
    public static final SessionLifecycleServiceBinder getComponents$lambda$5(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj, "container[firebaseApp]");
        return new SessionLifecycleServiceBinderImpl((FirebaseApp) obj);
    }

    @NotNull
    public List<Component<? extends Object>> getComponents() {
        Component.Builder<FirebaseSessions> name = Component.builder(FirebaseSessions.class).name(LIBRARY_NAME);
        Qualified<FirebaseApp> qualified = firebaseApp;
        Component.Builder<FirebaseSessions> add = name.add(Dependency.required((Qualified<?>) qualified));
        Qualified<SessionsSettings> qualified2 = sessionsSettings;
        Component.Builder<FirebaseSessions> add2 = add.add(Dependency.required((Qualified<?>) qualified2));
        Qualified<CoroutineDispatcher> qualified3 = backgroundDispatcher;
        Component<FirebaseSessions> build = add2.add(Dependency.required((Qualified<?>) qualified3)).add(Dependency.required((Qualified<?>) sessionLifecycleServiceBinder)).factory(new b(0)).eagerInDefaultApp().build();
        Component<SessionGenerator> build2 = Component.builder(SessionGenerator.class).name("session-generator").factory(new b(1)).build();
        Component.Builder<SessionFirelogPublisher> add3 = Component.builder(SessionFirelogPublisher.class).name("session-publisher").add(Dependency.required((Qualified<?>) qualified));
        Qualified<FirebaseInstallationsApi> qualified4 = firebaseInstallationsApi;
        return CollectionsKt.listOf(build, build2, add3.add(Dependency.required((Qualified<?>) qualified4)).add(Dependency.required((Qualified<?>) qualified2)).add(Dependency.requiredProvider((Qualified<?>) transportFactory)).add(Dependency.required((Qualified<?>) qualified3)).factory(new b(2)).build(), Component.builder(SessionsSettings.class).name("sessions-settings").add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) blockingDispatcher)).add(Dependency.required((Qualified<?>) qualified3)).add(Dependency.required((Qualified<?>) qualified4)).factory(new b(3)).build(), Component.builder(SessionDatastore.class).name("sessions-datastore").add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) qualified3)).factory(new b(4)).build(), Component.builder(SessionLifecycleServiceBinder.class).name("sessions-service-binder").add(Dependency.required((Qualified<?>) qualified)).factory(new b(5)).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }
}
