package com.appsamurai.storyly.exoplayer2.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import javax.annotation.meta.When;

@Nonnull(when = When.UNKNOWN)
@TypeQualifierDefault({ElementType.TYPE_USE})
@Retention(RetentionPolicy.CLASS)
public @interface UnknownNull {
}
