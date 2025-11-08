package com.fasterxml.jackson.databind.introspect;

import A.a;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class MemberKey {
    static final Class<?>[] NO_CLASSES = new Class[0];
    final Class<?>[] _argTypes;
    final String _name;

    public MemberKey(Method method) {
        this(method.getName(), method.getParameterTypes());
    }

    public int argCount() {
        return this._argTypes.length;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != MemberKey.class) {
            return false;
        }
        MemberKey memberKey = (MemberKey) obj;
        if (!this._name.equals(memberKey._name)) {
            return false;
        }
        Class<?>[] clsArr = memberKey._argTypes;
        int length = this._argTypes.length;
        if (clsArr.length != length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (clsArr[i3] != this._argTypes[i3]) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return this._name;
    }

    public int hashCode() {
        return this._name.hashCode() + this._argTypes.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._name);
        sb.append("(");
        return a.m(sb, "-args)", this._argTypes.length);
    }

    public MemberKey(Constructor<?> constructor) {
        this("", constructor.getParameterTypes());
    }

    public MemberKey(String str, Class<?>[] clsArr) {
        this._name = str;
        this._argTypes = clsArr == null ? NO_CLASSES : clsArr;
    }
}
