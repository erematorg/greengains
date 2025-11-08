package com.apollographql.apollo3.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.RequiresOptIn;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.PROPERTY})
@RequiresOptIn(level = RequiresOptIn.Level.WARNING, message = "This field, input field or enum value is declared `@requiresOptIn`.")
@Retention(AnnotationRetention.RUNTIME)
@Documented
@java.lang.annotation.Target({ElementType.TYPE})
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/apollographql/apollo3/annotations/ApolloRequiresOptIn;", "", "apollo-annotations"}, k = 1, mv = {1, 5, 1}, xi = 48)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface ApolloRequiresOptIn {
}
