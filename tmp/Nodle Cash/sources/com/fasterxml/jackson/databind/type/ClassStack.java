package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.ArrayList;
import java.util.Iterator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;

public final class ClassStack {
    protected final Class<?> _current;
    protected final ClassStack _parent;
    private ArrayList<ResolvedRecursiveType> _selfRefs;

    public ClassStack(Class<?> cls) {
        this((ClassStack) null, cls);
    }

    public void addSelfReference(ResolvedRecursiveType resolvedRecursiveType) {
        if (this._selfRefs == null) {
            this._selfRefs = new ArrayList<>();
        }
        this._selfRefs.add(resolvedRecursiveType);
    }

    public ClassStack child(Class<?> cls) {
        return new ClassStack(this, cls);
    }

    public ClassStack find(Class<?> cls) {
        if (this._current == cls) {
            return this;
        }
        do {
            this = this._parent;
            if (this == null) {
                return null;
            }
        } while (this._current != cls);
        return this;
    }

    public void resolveSelfReferences(JavaType javaType) {
        ArrayList<ResolvedRecursiveType> arrayList = this._selfRefs;
        if (arrayList != null) {
            Iterator<ResolvedRecursiveType> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().setReference(javaType);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ClassStack (self-refs: ");
        ArrayList<ResolvedRecursiveType> arrayList = this._selfRefs;
        sb.append(arrayList == null ? SchemaSymbols.ATTVAL_FALSE_0 : String.valueOf(arrayList.size()));
        sb.append(')');
        while (this != null) {
            sb.append(' ');
            sb.append(this._current.getName());
            this = this._parent;
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    private ClassStack(ClassStack classStack, Class<?> cls) {
        this._parent = classStack;
        this._current = cls;
    }
}
