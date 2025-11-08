package com.google.firebase.components;

import androidx.constraintlayout.core.state.b;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class RestrictedComponentContainer implements ComponentContainer {
    private final Set<Qualified<?>> allowedDeferredInterfaces;
    private final Set<Qualified<?>> allowedDirectInterfaces;
    private final Set<Qualified<?>> allowedProviderInterfaces;
    private final Set<Class<?>> allowedPublishedEvents;
    private final Set<Qualified<?>> allowedSetDirectInterfaces;
    private final Set<Qualified<?>> allowedSetProviderInterfaces;
    private final ComponentContainer delegateContainer;

    public static class RestrictedPublisher implements Publisher {
        private final Set<Class<?>> allowedPublishedEvents;
        private final Publisher delegate;

        public RestrictedPublisher(Set<Class<?>> set, Publisher publisher) {
            this.allowedPublishedEvents = set;
            this.delegate = publisher;
        }

        public void publish(Event<?> event) {
            if (this.allowedPublishedEvents.contains(event.getType())) {
                this.delegate.publish(event);
                return;
            }
            throw new DependencyException(String.format("Attempting to publish an undeclared event %s.", new Object[]{event}));
        }
    }

    public RestrictedComponentContainer(Component<?> component, ComponentContainer componentContainer) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        for (Dependency next : component.getDependencies()) {
            if (next.isDirectInjection()) {
                if (next.isSet()) {
                    hashSet4.add(next.getInterface());
                } else {
                    hashSet.add(next.getInterface());
                }
            } else if (next.isDeferred()) {
                hashSet3.add(next.getInterface());
            } else if (next.isSet()) {
                hashSet5.add(next.getInterface());
            } else {
                hashSet2.add(next.getInterface());
            }
        }
        if (!component.getPublishedEvents().isEmpty()) {
            hashSet.add(Qualified.unqualified(Publisher.class));
        }
        this.allowedDirectInterfaces = Collections.unmodifiableSet(hashSet);
        this.allowedProviderInterfaces = Collections.unmodifiableSet(hashSet2);
        this.allowedDeferredInterfaces = Collections.unmodifiableSet(hashSet3);
        this.allowedSetDirectInterfaces = Collections.unmodifiableSet(hashSet4);
        this.allowedSetProviderInterfaces = Collections.unmodifiableSet(hashSet5);
        this.allowedPublishedEvents = component.getPublishedEvents();
        this.delegateContainer = componentContainer;
    }

    public <T> T get(Class<T> cls) {
        if (this.allowedDirectInterfaces.contains(Qualified.unqualified(cls))) {
            T t2 = this.delegateContainer.get(cls);
            if (!cls.equals(Publisher.class)) {
                return t2;
            }
            return new RestrictedPublisher(this.allowedPublishedEvents, (Publisher) t2);
        }
        throw new DependencyException(b.l("Attempting to request an undeclared dependency ", cls, JwtUtilsKt.JWT_DELIMITER));
    }

    public <T> Deferred<T> getDeferred(Class<T> cls) {
        return getDeferred(Qualified.unqualified(cls));
    }

    public <T> Provider<T> getProvider(Class<T> cls) {
        return getProvider(Qualified.unqualified(cls));
    }

    public <T> Set<T> setOf(Qualified<T> qualified) {
        if (this.allowedSetDirectInterfaces.contains(qualified)) {
            return this.delegateContainer.setOf(qualified);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Set<" + qualified + ">.");
    }

    public <T> Provider<Set<T>> setOfProvider(Class<T> cls) {
        return setOfProvider(Qualified.unqualified(cls));
    }

    public <T> Deferred<T> getDeferred(Qualified<T> qualified) {
        if (this.allowedDeferredInterfaces.contains(qualified)) {
            return this.delegateContainer.getDeferred(qualified);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Deferred<" + qualified + ">.");
    }

    public <T> Provider<T> getProvider(Qualified<T> qualified) {
        if (this.allowedProviderInterfaces.contains(qualified)) {
            return this.delegateContainer.getProvider(qualified);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Provider<" + qualified + ">.");
    }

    public <T> Provider<Set<T>> setOfProvider(Qualified<T> qualified) {
        if (this.allowedSetProviderInterfaces.contains(qualified)) {
            return this.delegateContainer.setOfProvider(qualified);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Provider<Set<" + qualified + ">>.");
    }

    public <T> T get(Qualified<T> qualified) {
        if (this.allowedDirectInterfaces.contains(qualified)) {
            return this.delegateContainer.get(qualified);
        }
        throw new DependencyException("Attempting to request an undeclared dependency " + qualified + JwtUtilsKt.JWT_DELIMITER);
    }
}
