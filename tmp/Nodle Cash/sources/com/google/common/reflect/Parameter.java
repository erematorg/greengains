package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.DoNotCall;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class Parameter implements AnnotatedElement {
    private final Object annotatedType;
    private final ImmutableList<Annotation> annotations;
    private final Invokable<?, ?> declaration;
    private final int position;
    private final TypeToken<?> type;

    public Parameter(Invokable<?, ?> invokable, int i3, TypeToken<?> typeToken, Annotation[] annotationArr, Object obj) {
        this.declaration = invokable;
        this.position = i3;
        this.type = typeToken;
        this.annotations = ImmutableList.copyOf((E[]) annotationArr);
        this.annotatedType = obj;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) obj;
        return this.position == parameter.position && this.declaration.equals(parameter.declaration);
    }

    @IgnoreJRERequirement
    @DoNotCall("fails under Android VMs; do not use from guava-android")
    @Deprecated
    @Beta
    public AnnotatedType getAnnotatedType() {
        AnnotatedType annotatedType2 = (AnnotatedType) this.annotatedType;
        Objects.requireNonNull(annotatedType2);
        AnnotatedType annotatedType3 = annotatedType2;
        return annotatedType2;
    }

    @CheckForNull
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        Preconditions.checkNotNull(cls);
        UnmodifiableIterator<Annotation> it = this.annotations.iterator();
        while (it.hasNext()) {
            Annotation next = it.next();
            if (cls.isInstance(next)) {
                return (Annotation) cls.cast(next);
            }
        }
        return null;
    }

    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }

    public <A extends Annotation> A[] getAnnotationsByType(Class<A> cls) {
        return getDeclaredAnnotationsByType(cls);
    }

    @CheckForNull
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> cls) {
        Preconditions.checkNotNull(cls);
        return (Annotation) FluentIterable.from(this.annotations).filter(cls).first().orNull();
    }

    public Annotation[] getDeclaredAnnotations() {
        return (Annotation[]) this.annotations.toArray(new Annotation[0]);
    }

    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> cls) {
        return (Annotation[]) FluentIterable.from(this.annotations).filter(cls).toArray(cls);
    }

    public Invokable<?, ?> getDeclaringInvokable() {
        return this.declaration;
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    public int hashCode() {
        return this.position;
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return getAnnotation(cls) != null;
    }

    public String toString() {
        return this.type + " arg" + this.position;
    }
}
