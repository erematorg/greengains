package com.google.firebase.annotations.concurrent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

@Qualifier
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
public @interface UiThread {
}
