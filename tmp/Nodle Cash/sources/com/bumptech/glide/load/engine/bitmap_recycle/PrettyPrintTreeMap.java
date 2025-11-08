package com.bumptech.glide.load.engine.bitmap_recycle;

import A.a;
import java.util.Map;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

class PrettyPrintTreeMap<K, V> extends TreeMap<K, V> {
    public String toString() {
        StringBuilder p2 = a.p("( ");
        for (Map.Entry entry : entrySet()) {
            p2.append(AbstractJsonLexerKt.BEGIN_OBJ);
            p2.append(entry.getKey());
            p2.append(AbstractJsonLexerKt.COLON);
            p2.append(entry.getValue());
            p2.append("}, ");
        }
        if (!isEmpty()) {
            p2.replace(p2.length() - 2, p2.length(), "");
        }
        p2.append(" )");
        return p2.toString();
    }
}
