package com.google.firebase.components;

import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public class ComponentRuntime implements ComponentContainer, ComponentLoader {
    private static final Provider<Set<Object>> EMPTY_PROVIDER = new g(1);
    private final ComponentRegistrarProcessor componentRegistrarProcessor;
    private final Map<Component<?>, Provider<?>> components;
    private final AtomicReference<Boolean> eagerComponentsInitializedWith;
    private final EventBus eventBus;
    private final Map<Qualified<?>, Provider<?>> lazyInstanceMap;
    private final Map<Qualified<?>, LazySet<?>> lazySetMap;
    private Set<String> processedCoroutineDispatcherInterfaces;
    private final List<Provider<ComponentRegistrar>> unprocessedRegistrarProviders;

    public static final class Builder {
        private final List<Component<?>> additionalComponents = new ArrayList();
        private ComponentRegistrarProcessor componentRegistrarProcessor = ComponentRegistrarProcessor.NOOP;
        private final Executor defaultExecutor;
        private final List<Provider<ComponentRegistrar>> lazyRegistrars = new ArrayList();

        public Builder(Executor executor) {
            this.defaultExecutor = executor;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ComponentRegistrar lambda$addComponentRegistrar$0(ComponentRegistrar componentRegistrar) {
            return componentRegistrar;
        }

        @CanIgnoreReturnValue
        public Builder addComponent(Component<?> component) {
            this.additionalComponents.add(component);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addComponentRegistrar(ComponentRegistrar componentRegistrar) {
            this.lazyRegistrars.add(new c(componentRegistrar, 1));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addLazyComponentRegistrars(Collection<Provider<ComponentRegistrar>> collection) {
            this.lazyRegistrars.addAll(collection);
            return this;
        }

        public ComponentRuntime build() {
            return new ComponentRuntime(this.defaultExecutor, this.lazyRegistrars, this.additionalComponents, this.componentRegistrarProcessor);
        }

        @CanIgnoreReturnValue
        public Builder setProcessor(ComponentRegistrarProcessor componentRegistrarProcessor2) {
            this.componentRegistrarProcessor = componentRegistrarProcessor2;
            return this;
        }
    }

    public static Builder builder(Executor executor) {
        return new Builder(executor);
    }

    private void discoverComponents(List<Component<?>> list) {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<Provider<ComponentRegistrar>> it = this.unprocessedRegistrarProviders.iterator();
            while (it.hasNext()) {
                try {
                    ComponentRegistrar componentRegistrar = (ComponentRegistrar) it.next().get();
                    if (componentRegistrar != null) {
                        list.addAll(this.componentRegistrarProcessor.processRegistrar(componentRegistrar));
                        it.remove();
                    }
                } catch (InvalidRegistrarException e3) {
                    it.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e3);
                }
            }
            Iterator<Component<?>> it2 = list.iterator();
            while (it2.hasNext()) {
                Object[] array = it2.next().getProvidedInterfaces().toArray();
                int length = array.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Object obj = array[i3];
                    if (obj.toString().contains("kotlinx.coroutines.CoroutineDispatcher")) {
                        if (this.processedCoroutineDispatcherInterfaces.contains(obj.toString())) {
                            it2.remove();
                            break;
                        }
                        this.processedCoroutineDispatcherInterfaces.add(obj.toString());
                    }
                    i3++;
                }
            }
            if (this.components.isEmpty()) {
                CycleDetector.detect(list);
            } else {
                ArrayList arrayList2 = new ArrayList(this.components.keySet());
                arrayList2.addAll(list);
                CycleDetector.detect(arrayList2);
            }
            for (Component next : list) {
                this.components.put(next, new Lazy(new d(this, next)));
            }
            arrayList.addAll(processInstanceComponents(list));
            arrayList.addAll(processSetComponents());
            processDependencies();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            ((Runnable) it3.next()).run();
        }
        maybeInitializeEagerComponents();
    }

    private void doInitializeEagerComponents(Map<Component<?>, Provider<?>> map, boolean z2) {
        for (Map.Entry next : map.entrySet()) {
            Component component = (Component) next.getKey();
            Provider provider = (Provider) next.getValue();
            if (component.isAlwaysEager() || (component.isEagerInDefaultApp() && z2)) {
                provider.get();
            }
        }
        this.eventBus.enablePublishingAndFlushPending();
    }

    private static <T> List<T> iterableToList(Iterable<T> iterable) {
        ArrayList arrayList = new ArrayList();
        for (T add : iterable) {
            arrayList.add(add);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$discoverComponents$0(Component component) {
        return component.getFactory().create(new RestrictedComponentContainer(component, this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ComponentRegistrar lambda$toProviders$1(ComponentRegistrar componentRegistrar) {
        return componentRegistrar;
    }

    private void maybeInitializeEagerComponents() {
        Boolean bool = this.eagerComponentsInitializedWith.get();
        if (bool != null) {
            doInitializeEagerComponents(this.components, bool.booleanValue());
        }
    }

    private void processDependencies() {
        for (Component next : this.components.keySet()) {
            Iterator<Dependency> it = next.getDependencies().iterator();
            while (true) {
                if (it.hasNext()) {
                    Dependency next2 = it.next();
                    if (next2.isSet() && !this.lazySetMap.containsKey(next2.getInterface())) {
                        this.lazySetMap.put(next2.getInterface(), LazySet.fromCollection(Collections.emptySet()));
                    } else if (this.lazyInstanceMap.containsKey(next2.getInterface())) {
                        continue;
                    } else if (next2.isRequired()) {
                        Qualified<?> qualified = next2.getInterface();
                        throw new MissingDependencyException("Unsatisfied dependency for component " + next + ": " + qualified);
                    } else if (!next2.isSet()) {
                        this.lazyInstanceMap.put(next2.getInterface(), OptionalProvider.empty());
                    }
                }
            }
        }
    }

    private List<Runnable> processInstanceComponents(List<Component<?>> list) {
        ArrayList arrayList = new ArrayList();
        for (Component next : list) {
            if (next.isValue()) {
                Provider provider = this.components.get(next);
                for (Qualified qualified : next.getProvidedInterfaces()) {
                    if (!this.lazyInstanceMap.containsKey(qualified)) {
                        this.lazyInstanceMap.put(qualified, provider);
                    } else {
                        arrayList.add(new e((OptionalProvider) this.lazyInstanceMap.get(qualified), provider, 0));
                    }
                }
            }
        }
        return arrayList;
    }

    private List<Runnable> processSetComponents() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.components.entrySet()) {
            Component component = (Component) next.getKey();
            if (!component.isValue()) {
                Provider provider = (Provider) next.getValue();
                for (Qualified qualified : component.getProvidedInterfaces()) {
                    if (!hashMap.containsKey(qualified)) {
                        hashMap.put(qualified, new HashSet());
                    }
                    ((Set) hashMap.get(qualified)).add(provider);
                }
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (!this.lazySetMap.containsKey(entry.getKey())) {
                this.lazySetMap.put((Qualified) entry.getKey(), LazySet.fromCollection((Collection) entry.getValue()));
            } else {
                LazySet lazySet = this.lazySetMap.get(entry.getKey());
                for (Provider eVar : (Set) entry.getValue()) {
                    arrayList.add(new e(lazySet, eVar, 1));
                }
            }
        }
        return arrayList;
    }

    private static Iterable<Provider<ComponentRegistrar>> toProviders(Iterable<ComponentRegistrar> iterable) {
        ArrayList arrayList = new ArrayList();
        for (ComponentRegistrar cVar : iterable) {
            arrayList.add(new c(cVar, 0));
        }
        return arrayList;
    }

    @VisibleForTesting
    public Collection<Component<?>> getAllComponentsForTest() {
        return this.components.keySet();
    }

    public <T> Deferred<T> getDeferred(Qualified<T> qualified) {
        Provider<T> provider = getProvider(qualified);
        return provider == null ? OptionalProvider.empty() : provider instanceof OptionalProvider ? (OptionalProvider) provider : OptionalProvider.of(provider);
    }

    public synchronized <T> Provider<T> getProvider(Qualified<T> qualified) {
        Preconditions.checkNotNull(qualified, "Null interface requested.");
        return this.lazyInstanceMap.get(qualified);
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.TESTS})
    public void initializeAllComponentsForTests() {
        for (Provider<?> provider : this.components.values()) {
            provider.get();
        }
    }

    public void initializeEagerComponents(boolean z2) {
        HashMap hashMap;
        AtomicReference<Boolean> atomicReference = this.eagerComponentsInitializedWith;
        Boolean valueOf = Boolean.valueOf(z2);
        while (!atomicReference.compareAndSet((Object) null, valueOf)) {
            if (atomicReference.get() != null) {
                return;
            }
        }
        synchronized (this) {
            hashMap = new HashMap(this.components);
        }
        doInitializeEagerComponents(hashMap, z2);
    }

    public synchronized <T> Provider<Set<T>> setOfProvider(Qualified<T> qualified) {
        LazySet lazySet = this.lazySetMap.get(qualified);
        if (lazySet != null) {
            return lazySet;
        }
        return EMPTY_PROVIDER;
    }

    @Deprecated
    public ComponentRuntime(Executor executor, Iterable<ComponentRegistrar> iterable, Component<?>... componentArr) {
        this(executor, toProviders(iterable), Arrays.asList(componentArr), ComponentRegistrarProcessor.NOOP);
    }

    private ComponentRuntime(Executor executor, Iterable<Provider<ComponentRegistrar>> iterable, Collection<Component<?>> collection, ComponentRegistrarProcessor componentRegistrarProcessor2) {
        this.components = new HashMap();
        this.lazyInstanceMap = new HashMap();
        this.lazySetMap = new HashMap();
        this.processedCoroutineDispatcherInterfaces = new HashSet();
        this.eagerComponentsInitializedWith = new AtomicReference<>();
        EventBus eventBus2 = new EventBus(executor);
        this.eventBus = eventBus2;
        this.componentRegistrarProcessor = componentRegistrarProcessor2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Component.of(eventBus2, EventBus.class, (Class<? super T>[]) new Class[]{Subscriber.class, Publisher.class}));
        arrayList.add(Component.of(this, ComponentLoader.class, (Class<? super T>[]) new Class[0]));
        for (Component next : collection) {
            if (next != null) {
                arrayList.add(next);
            }
        }
        this.unprocessedRegistrarProviders = iterableToList(iterable);
        discoverComponents(arrayList);
    }

    public void discoverComponents() {
        synchronized (this) {
            try {
                if (!this.unprocessedRegistrarProviders.isEmpty()) {
                    discoverComponents(new ArrayList());
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
