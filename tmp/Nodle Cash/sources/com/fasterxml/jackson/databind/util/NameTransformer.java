package com.fasterxml.jackson.databind.util;

import A.a;
import java.io.Serializable;

public abstract class NameTransformer {
    public static final NameTransformer NOP = new NopTransformer();

    public static class Chained extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        public String reverse(String str) {
            String reverse = this._t1.reverse(str);
            return reverse != null ? this._t2.reverse(reverse) : reverse;
        }

        public String toString() {
            return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
        }

        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }
    }

    public static final class NopTransformer extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;

        public String reverse(String str) {
            return str;
        }

        public String transform(String str) {
            return str;
        }
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }

    public static NameTransformer simpleTransformer(final String str, final String str2) {
        boolean z2 = false;
        boolean z3 = str != null && !str.isEmpty();
        if (str2 != null && !str2.isEmpty()) {
            z2 = true;
        }
        return z3 ? z2 ? new NameTransformer() {
            public String reverse(String str) {
                if (!str.startsWith(str)) {
                    return null;
                }
                String substring = str.substring(str.length());
                if (substring.endsWith(str2)) {
                    return substring.substring(0, substring.length() - str2.length());
                }
                return null;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder("[PreAndSuffixTransformer('");
                sb.append(str);
                sb.append("','");
                return a.n(sb, str2, "')]");
            }

            public String transform(String str) {
                return str + str + str2;
            }
        } : new NameTransformer() {
            public String reverse(String str) {
                if (str.startsWith(str)) {
                    return str.substring(str.length());
                }
                return null;
            }

            public String toString() {
                return a.n(new StringBuilder("[PrefixTransformer('"), str, "')]");
            }

            public String transform(String str) {
                return a.n(new StringBuilder(), str, str);
            }
        } : z2 ? new NameTransformer() {
            public String reverse(String str) {
                if (str.endsWith(str2)) {
                    return str.substring(0, str.length() - str2.length());
                }
                return null;
            }

            public String toString() {
                return a.n(new StringBuilder("[SuffixTransformer('"), str2, "')]");
            }

            public String transform(String str) {
                StringBuilder p2 = a.p(str);
                p2.append(str2);
                return p2.toString();
            }
        } : NOP;
    }

    public abstract String reverse(String str);

    public abstract String transform(String str);
}
