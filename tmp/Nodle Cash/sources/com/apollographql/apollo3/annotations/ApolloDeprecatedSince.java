package com.apollographql.apollo3.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.TYPEALIAS})
@Retention(AnnotationRetention.SOURCE)
@Documented
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/annotations/ApolloDeprecatedSince;", "", "version", "Lcom/apollographql/apollo3/annotations/ApolloDeprecatedSince$Version;", "()Lcom/apollographql/apollo3/annotations/ApolloDeprecatedSince$Version;", "Version", "apollo-annotations"}, k = 1, mv = {1, 5, 1}, xi = 48)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
public @interface ApolloDeprecatedSince {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/annotations/ApolloDeprecatedSince$Version;", "", "(Ljava/lang/String;I)V", "v3_0_0", "v3_0_1", "v3_1_1", "v3_2_1", "v3_2_2", "v3_2_3", "v3_3_1", "v3_3_2", "v3_3_3", "v3_4_1", "v3_5_1", "v3_6_3", "v3_7_2", "v3_7_5", "v3_8_3", "apollo-annotations"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum Version {
        v3_0_0,
        v3_0_1,
        v3_1_1,
        v3_2_1,
        v3_2_2,
        v3_2_3,
        v3_3_1,
        v3_3_2,
        v3_3_3,
        v3_4_1,
        v3_5_1,
        v3_6_3,
        v3_7_2,
        v3_7_5,
        v3_8_3
    }

    Version version();
}
