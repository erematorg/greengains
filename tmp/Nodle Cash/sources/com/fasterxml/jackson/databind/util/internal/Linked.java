package com.fasterxml.jackson.databind.util.internal;

import com.fasterxml.jackson.databind.util.internal.Linked;

interface Linked<T extends Linked<T>> {
    T getNext();

    T getPrevious();

    void setNext(T t2);

    void setPrevious(T t2);
}
