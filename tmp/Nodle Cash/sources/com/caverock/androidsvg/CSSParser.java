package com.caverock.androidsvg;

import A.a;
import android.util.Log;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.objectweb.asm.signature.SignatureVisitor;
import org.slf4j.Marker;

class CSSParser {
    private static final String CLASS = "class";
    static final String CSS_MIME_TYPE = "text/css";
    private static final String ID = "id";
    private static final int SPECIFICITY_ATTRIBUTE_OR_PSEUDOCLASS = 1000;
    private static final int SPECIFICITY_ELEMENT_OR_PSEUDOELEMENT = 1;
    private static final int SPECIFICITY_ID_ATTRIBUTE = 1000000;
    private static final String TAG = "CSSParser";
    private MediaType deviceMediaType;
    private boolean inMediaRule;
    private Source source;

    /* renamed from: com.caverock.androidsvg.CSSParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents;

        /* JADX WARNING: Can't wrap try/catch for region: R(54:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|(3:59|60|62)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(56:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|62) */
        /* JADX WARNING: Can't wrap try/catch for region: R(57:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|55|56|57|58|59|60|62) */
        /* JADX WARNING: Can't wrap try/catch for region: R(58:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|55|56|57|58|59|60|62) */
        /* JADX WARNING: Can't wrap try/catch for region: R(59:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|55|56|57|58|59|60|62) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00e4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00f0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00fc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0108 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0114 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0131 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x013b */
        static {
            /*
                com.caverock.androidsvg.CSSParser$PseudoClassIdents[] r0 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents = r0
                r1 = 1
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r2 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.first_child     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x001d }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r3 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.last_child     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.only_child     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.first_of_type     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x003e }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.last_of_type     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.only_of_type     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.root     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.empty     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x006c }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_child     // Catch:{ NoSuchFieldError -> 0x006c }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r5 = 9
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_last_child     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r5 = 10
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_of_type     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r5 = 11
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_last_of_type     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r5 = 12
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x009c }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.not     // Catch:{ NoSuchFieldError -> 0x009c }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r5 = 13
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.target     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r5 = 14
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.lang     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r5 = 15
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.link     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r5 = 16
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.visited     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r5 = 17
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.hover     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r5 = 18
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00e4 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.active     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r5 = 19
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00f0 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.focus     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r5 = 20
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x00fc }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.enabled     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r5 = 21
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0108 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.disabled     // Catch:{ NoSuchFieldError -> 0x0108 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0108 }
                r5 = 22
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0108 }
            L_0x0108:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0114 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.checked     // Catch:{ NoSuchFieldError -> 0x0114 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0114 }
                r5 = 23
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0114 }
            L_0x0114:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents     // Catch:{ NoSuchFieldError -> 0x0120 }
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r4 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.indeterminate     // Catch:{ NoSuchFieldError -> 0x0120 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0120 }
                r5 = 24
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0120 }
            L_0x0120:
                com.caverock.androidsvg.CSSParser$AttribOp[] r3 = com.caverock.androidsvg.CSSParser.AttribOp.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp = r3
                com.caverock.androidsvg.CSSParser$AttribOp r4 = com.caverock.androidsvg.CSSParser.AttribOp.EQUALS     // Catch:{ NoSuchFieldError -> 0x0131 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0131 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0131 }
            L_0x0131:
                int[] r1 = $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp     // Catch:{ NoSuchFieldError -> 0x013b }
                com.caverock.androidsvg.CSSParser$AttribOp r3 = com.caverock.androidsvg.CSSParser.AttribOp.INCLUDES     // Catch:{ NoSuchFieldError -> 0x013b }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x013b }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x013b }
            L_0x013b:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp     // Catch:{ NoSuchFieldError -> 0x0145 }
                com.caverock.androidsvg.CSSParser$AttribOp r1 = com.caverock.androidsvg.CSSParser.AttribOp.DASHMATCH     // Catch:{ NoSuchFieldError -> 0x0145 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0145 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0145 }
            L_0x0145:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.CSSParser.AnonymousClass1.<clinit>():void");
        }
    }

    public static class Attrib {
        public final String name;
        final AttribOp operation;
        public final String value;

        public Attrib(String str, AttribOp attribOp, String str2) {
            this.name = str;
            this.operation = attribOp;
            this.value = str2;
        }
    }

    public enum AttribOp {
        EXISTS,
        EQUALS,
        INCLUDES,
        DASHMATCH
    }

    public static class CSSTextScanner extends SVGParser.TextScanner {

        public static class AnPlusB {

            /* renamed from: a  reason: collision with root package name */
            public int f6516a;

            /* renamed from: b  reason: collision with root package name */
            public int f6517b;

            public AnPlusB(int i3, int i4) {
                this.f6516a = i3;
                this.f6517b = i4;
            }
        }

        public CSSTextScanner(String str) {
            super(str.replaceAll("(?s)/\\*.*?\\*/", ""));
        }

        private int hexChar(int i3) {
            if (i3 >= 48 && i3 <= 57) {
                return i3 - 48;
            }
            if (i3 >= 65 && i3 <= 70) {
                return i3 - 55;
            }
            if (i3 < 97 || i3 > 102) {
                return -1;
            }
            return i3 - 87;
        }

        private AnPlusB nextAnPlusB() throws CSSParseException {
            AnPlusB anPlusB;
            IntegerParser integerParser;
            if (empty()) {
                return null;
            }
            int i3 = this.position;
            if (!consume('(')) {
                return null;
            }
            skipWhitespace();
            int i4 = 1;
            if (consume("odd")) {
                anPlusB = new AnPlusB(2, 1);
            } else {
                int i5 = 0;
                if (consume("even")) {
                    anPlusB = new AnPlusB(2, 0);
                } else {
                    int i6 = (!consume((char) SignatureVisitor.EXTENDS) && consume((char) SignatureVisitor.SUPER)) ? -1 : 1;
                    IntegerParser parseInt = IntegerParser.parseInt(this.input, this.position, this.inputLength, false);
                    if (parseInt != null) {
                        this.position = parseInt.getEndPos();
                    }
                    if (consume('n') || consume('N')) {
                        if (parseInt == null) {
                            parseInt = new IntegerParser(1, this.position);
                        }
                        skipWhitespace();
                        boolean consume = consume((char) SignatureVisitor.EXTENDS);
                        if (!consume && (consume = consume((char) SignatureVisitor.SUPER))) {
                            i4 = -1;
                        }
                        if (consume) {
                            skipWhitespace();
                            integerParser = IntegerParser.parseInt(this.input, this.position, this.inputLength, false);
                            if (integerParser != null) {
                                this.position = integerParser.getEndPos();
                            } else {
                                this.position = i3;
                                return null;
                            }
                        } else {
                            integerParser = null;
                        }
                        int i7 = i4;
                        i4 = i6;
                        i6 = i7;
                    } else {
                        integerParser = parseInt;
                        parseInt = null;
                    }
                    int value = parseInt == null ? 0 : i4 * parseInt.value();
                    if (integerParser != null) {
                        i5 = i6 * integerParser.value();
                    }
                    anPlusB = new AnPlusB(value, i5);
                }
            }
            skipWhitespace();
            if (consume(')')) {
                return anPlusB;
            }
            this.position = i3;
            return null;
        }

        private String nextAttribValue() {
            if (empty()) {
                return null;
            }
            String nextQuotedString = nextQuotedString();
            return nextQuotedString != null ? nextQuotedString : nextIdentifier();
        }

        private List<String> nextIdentListParam() throws CSSParseException {
            if (empty()) {
                return null;
            }
            int i3 = this.position;
            if (!consume('(')) {
                return null;
            }
            skipWhitespace();
            ArrayList arrayList = null;
            do {
                String nextIdentifier = nextIdentifier();
                if (nextIdentifier == null) {
                    this.position = i3;
                    return null;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(nextIdentifier);
                skipWhitespace();
            } while (skipCommaWhitespace());
            if (consume(')')) {
                return arrayList;
            }
            this.position = i3;
            return null;
        }

        private List<Selector> nextPseudoNotParam() throws CSSParseException {
            List<SimpleSelector> list;
            List<PseudoClass> list2;
            if (empty()) {
                return null;
            }
            int i3 = this.position;
            if (!consume('(')) {
                return null;
            }
            skipWhitespace();
            List<Selector> nextSelectorGroup = nextSelectorGroup();
            if (nextSelectorGroup == null) {
                this.position = i3;
                return null;
            } else if (!consume(')')) {
                this.position = i3;
                return null;
            } else {
                Iterator<Selector> it = nextSelectorGroup.iterator();
                while (it.hasNext() && (list = it.next().simpleSelectors) != null) {
                    Iterator<SimpleSelector> it2 = list.iterator();
                    while (it2.hasNext() && (list2 = it2.next().pseudos) != null) {
                        Iterator<PseudoClass> it3 = list2.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                if (it3.next() instanceof PseudoClassNot) {
                                    return null;
                                }
                            }
                        }
                    }
                }
                return nextSelectorGroup;
            }
        }

        /* access modifiers changed from: private */
        public List<Selector> nextSelectorGroup() throws CSSParseException {
            if (empty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList(1);
            Selector selector = new Selector((AnonymousClass1) null);
            while (!empty() && nextSimpleSelector(selector)) {
                if (skipCommaWhitespace()) {
                    arrayList.add(selector);
                    selector = new Selector((AnonymousClass1) null);
                }
            }
            if (!selector.isEmpty()) {
                arrayList.add(selector);
            }
            return arrayList;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.caverock.androidsvg.CSSParser$PseudoClassNot} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB} */
        /* JADX WARNING: type inference failed for: r13v5, types: [com.caverock.androidsvg.CSSParser$PseudoClassOnlyChild] */
        /* JADX WARNING: type inference failed for: r13v8, types: [com.caverock.androidsvg.CSSParser$PseudoClassOnlyChild] */
        /* JADX WARNING: type inference failed for: r13v9, types: [com.caverock.androidsvg.CSSParser$PseudoClassRoot] */
        /* JADX WARNING: type inference failed for: r13v10, types: [com.caverock.androidsvg.CSSParser$PseudoClassEmpty] */
        /* JADX WARNING: type inference failed for: r13v17, types: [com.caverock.androidsvg.CSSParser$PseudoClassTarget] */
        /* JADX WARNING: type inference failed for: r13v18, types: [com.caverock.androidsvg.CSSParser$PseudoClassNotSupported] */
        /* JADX WARNING: type inference failed for: r13v19, types: [com.caverock.androidsvg.CSSParser$PseudoClassNotSupported] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 7 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void parsePseudoClass(com.caverock.androidsvg.CSSParser.Selector r14, com.caverock.androidsvg.CSSParser.SimpleSelector r15) throws com.caverock.androidsvg.CSSParseException {
            /*
                r13 = this;
                java.lang.String r0 = r13.nextIdentifier()
                if (r0 == 0) goto L_0x0104
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r1 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.fromString(r0)
                int[] r2 = com.caverock.androidsvg.CSSParser.AnonymousClass1.$SwitchMap$com$caverock$androidsvg$CSSParser$PseudoClassIdents
                int r3 = r1.ordinal()
                r2 = r2[r3]
                java.lang.String r3 = "Invalid or missing parameter section for pseudo class: "
                r4 = 1
                r5 = 0
                r6 = 0
                switch(r2) {
                    case 1: goto L_0x00f2;
                    case 2: goto L_0x00e3;
                    case 3: goto L_0x00da;
                    case 4: goto L_0x00ca;
                    case 5: goto L_0x00ba;
                    case 6: goto L_0x00af;
                    case 7: goto L_0x00a6;
                    case 8: goto L_0x009d;
                    case 9: goto L_0x0065;
                    case 10: goto L_0x0065;
                    case 11: goto L_0x0065;
                    case 12: goto L_0x0065;
                    case 13: goto L_0x0047;
                    case 14: goto L_0x003d;
                    case 15: goto L_0x0030;
                    case 16: goto L_0x0026;
                    case 17: goto L_0x0026;
                    case 18: goto L_0x0026;
                    case 19: goto L_0x0026;
                    case 20: goto L_0x0026;
                    case 21: goto L_0x0026;
                    case 22: goto L_0x0026;
                    case 23: goto L_0x0026;
                    case 24: goto L_0x0026;
                    default: goto L_0x001a;
                }
            L_0x001a:
                com.caverock.androidsvg.CSSParseException r13 = new com.caverock.androidsvg.CSSParseException
                java.lang.String r14 = "Unsupported pseudo class: "
                java.lang.String r14 = r14.concat(r0)
                r13.<init>(r14)
                throw r13
            L_0x0026:
                com.caverock.androidsvg.CSSParser$PseudoClassNotSupported r13 = new com.caverock.androidsvg.CSSParser$PseudoClassNotSupported
                r13.<init>(r0)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x0030:
                r13.nextIdentListParam()
                com.caverock.androidsvg.CSSParser$PseudoClassNotSupported r13 = new com.caverock.androidsvg.CSSParser$PseudoClassNotSupported
                r13.<init>(r0)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x003d:
                com.caverock.androidsvg.CSSParser$PseudoClassTarget r13 = new com.caverock.androidsvg.CSSParser$PseudoClassTarget
                r13.<init>(r6)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x0047:
                java.util.List r13 = r13.nextPseudoNotParam()
                if (r13 == 0) goto L_0x005b
                com.caverock.androidsvg.CSSParser$PseudoClassNot r0 = new com.caverock.androidsvg.CSSParser$PseudoClassNot
                r0.<init>(r13)
                int r13 = r0.getSpecificity()
                r14.specificity = r13
            L_0x0058:
                r13 = r0
                goto L_0x0100
            L_0x005b:
                com.caverock.androidsvg.CSSParseException r13 = new com.caverock.androidsvg.CSSParseException
                java.lang.String r14 = r3.concat(r0)
                r13.<init>(r14)
                throw r13
            L_0x0065:
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r2 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_child
                if (r1 == r2) goto L_0x0070
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r2 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_of_type
                if (r1 != r2) goto L_0x006e
                goto L_0x0070
            L_0x006e:
                r9 = r5
                goto L_0x0071
            L_0x0070:
                r9 = r4
            L_0x0071:
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r2 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_of_type
                if (r1 == r2) goto L_0x007c
                com.caverock.androidsvg.CSSParser$PseudoClassIdents r2 = com.caverock.androidsvg.CSSParser.PseudoClassIdents.nth_last_of_type
                if (r1 != r2) goto L_0x007a
                goto L_0x007c
            L_0x007a:
                r10 = r5
                goto L_0x007d
            L_0x007c:
                r10 = r4
            L_0x007d:
                com.caverock.androidsvg.CSSParser$CSSTextScanner$AnPlusB r13 = r13.nextAnPlusB()
                if (r13 == 0) goto L_0x0093
                com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB r0 = new com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB
                int r7 = r13.f6516a
                int r8 = r13.f6517b
                java.lang.String r11 = r15.tag
                r6 = r0
                r6.<init>(r7, r8, r9, r10, r11)
                r14.addedAttributeOrPseudo()
                goto L_0x0058
            L_0x0093:
                com.caverock.androidsvg.CSSParseException r13 = new com.caverock.androidsvg.CSSParseException
                java.lang.String r14 = r3.concat(r0)
                r13.<init>(r14)
                throw r13
            L_0x009d:
                com.caverock.androidsvg.CSSParser$PseudoClassEmpty r13 = new com.caverock.androidsvg.CSSParser$PseudoClassEmpty
                r13.<init>(r6)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00a6:
                com.caverock.androidsvg.CSSParser$PseudoClassRoot r13 = new com.caverock.androidsvg.CSSParser$PseudoClassRoot
                r13.<init>(r6)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00af:
                com.caverock.androidsvg.CSSParser$PseudoClassOnlyChild r13 = new com.caverock.androidsvg.CSSParser$PseudoClassOnlyChild
                java.lang.String r0 = r15.tag
                r13.<init>(r4, r0)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00ba:
                com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB r13 = new com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB
                r9 = 1
                java.lang.String r10 = r15.tag
                r6 = 0
                r7 = 1
                r8 = 0
                r5 = r13
                r5.<init>(r6, r7, r8, r9, r10)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00ca:
                com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB r13 = new com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB
                r4 = 1
                java.lang.String r5 = r15.tag
                r1 = 0
                r2 = 1
                r3 = 1
                r0 = r13
                r0.<init>(r1, r2, r3, r4, r5)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00da:
                com.caverock.androidsvg.CSSParser$PseudoClassOnlyChild r13 = new com.caverock.androidsvg.CSSParser$PseudoClassOnlyChild
                r13.<init>(r5, r6)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00e3:
                com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB r13 = new com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB
                r11 = 0
                r12 = 0
                r8 = 0
                r9 = 1
                r10 = 0
                r7 = r13
                r7.<init>(r8, r9, r10, r11, r12)
                r14.addedAttributeOrPseudo()
                goto L_0x0100
            L_0x00f2:
                com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB r13 = new com.caverock.androidsvg.CSSParser$PseudoClassAnPlusB
                r4 = 0
                r5 = 0
                r1 = 0
                r2 = 1
                r3 = 1
                r0 = r13
                r0.<init>(r1, r2, r3, r4, r5)
                r14.addedAttributeOrPseudo()
            L_0x0100:
                r15.addPseudo(r13)
                return
            L_0x0104:
                com.caverock.androidsvg.CSSParseException r13 = new com.caverock.androidsvg.CSSParseException
                java.lang.String r14 = "Invalid pseudo class"
                r13.<init>(r14)
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.CSSParser.CSSTextScanner.parsePseudoClass(com.caverock.androidsvg.CSSParser$Selector, com.caverock.androidsvg.CSSParser$SimpleSelector):void");
        }

        private int scanForIdentifier() {
            int i3;
            if (empty()) {
                return this.position;
            }
            int i4 = this.position;
            int charAt = this.input.charAt(i4);
            if (charAt == 45) {
                charAt = advanceChar();
            }
            if ((charAt < 65 || charAt > 90) && ((charAt < 97 || charAt > 122) && charAt != 95)) {
                i3 = i4;
            } else {
                int advanceChar = advanceChar();
                while (true) {
                    if ((advanceChar < 65 || advanceChar > 90) && ((advanceChar < 97 || advanceChar > 122) && !((advanceChar >= 48 && advanceChar <= 57) || advanceChar == 45 || advanceChar == 95))) {
                        break;
                    }
                    advanceChar = advanceChar();
                }
                i3 = this.position;
            }
            this.position = i4;
            return i3;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0057, code lost:
            r2 = nextChar().intValue();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String nextCSSString() {
            /*
                r8 = this;
                boolean r0 = r8.empty()
                r1 = 0
                if (r0 == 0) goto L_0x0008
                return r1
            L_0x0008:
                java.lang.String r0 = r8.input
                int r2 = r8.position
                char r0 = r0.charAt(r2)
                r2 = 39
                if (r0 == r2) goto L_0x0019
                r2 = 34
                if (r0 == r2) goto L_0x0019
                return r1
            L_0x0019:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                int r2 = r8.position
                r3 = 1
                int r2 = r2 + r3
                r8.position = r2
                java.lang.Integer r2 = r8.nextChar()
                int r2 = r2.intValue()
            L_0x002c:
                r4 = -1
                if (r2 == r4) goto L_0x0087
                if (r2 == r0) goto L_0x0087
                r5 = 92
                if (r2 != r5) goto L_0x007a
                java.lang.Integer r2 = r8.nextChar()
                int r2 = r2.intValue()
                if (r2 != r4) goto L_0x0040
                goto L_0x002c
            L_0x0040:
                r5 = 10
                if (r2 == r5) goto L_0x0071
                r5 = 13
                if (r2 == r5) goto L_0x0071
                r5 = 12
                if (r2 != r5) goto L_0x004d
                goto L_0x0071
            L_0x004d:
                int r5 = r8.hexChar(r2)
                if (r5 == r4) goto L_0x007a
                r6 = r3
            L_0x0054:
                r7 = 5
                if (r6 > r7) goto L_0x006c
                java.lang.Integer r2 = r8.nextChar()
                int r2 = r2.intValue()
                int r7 = r8.hexChar(r2)
                if (r7 != r4) goto L_0x0066
                goto L_0x006c
            L_0x0066:
                int r5 = r5 * 16
                int r5 = r5 + r7
                int r6 = r6 + 1
                goto L_0x0054
            L_0x006c:
                char r4 = (char) r5
                r1.append(r4)
                goto L_0x002c
            L_0x0071:
                java.lang.Integer r2 = r8.nextChar()
                int r2 = r2.intValue()
                goto L_0x002c
            L_0x007a:
                char r2 = (char) r2
                r1.append(r2)
                java.lang.Integer r2 = r8.nextChar()
                int r2 = r2.intValue()
                goto L_0x002c
            L_0x0087:
                java.lang.String r8 = r1.toString()
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.CSSParser.CSSTextScanner.nextCSSString():java.lang.String");
        }

        public String nextIdentifier() {
            int scanForIdentifier = scanForIdentifier();
            int i3 = this.position;
            if (scanForIdentifier == i3) {
                return null;
            }
            String substring = this.input.substring(i3, scanForIdentifier);
            this.position = scanForIdentifier;
            return substring;
        }

        public String nextLegacyURL() {
            char charAt;
            int hexChar;
            StringBuilder sb = new StringBuilder();
            while (!empty() && (charAt = this.input.charAt(this.position)) != '\'' && charAt != '\"' && charAt != '(' && charAt != ')' && !isWhitespace(charAt) && !Character.isISOControl(charAt)) {
                this.position++;
                if (charAt == '\\') {
                    if (!empty()) {
                        String str = this.input;
                        int i3 = this.position;
                        this.position = i3 + 1;
                        charAt = str.charAt(i3);
                        if (!(charAt == 10 || charAt == 13 || charAt == 12)) {
                            int hexChar2 = hexChar(charAt);
                            if (hexChar2 != -1) {
                                for (int i4 = 1; i4 <= 5 && !empty() && (hexChar = hexChar(this.input.charAt(this.position))) != -1; i4++) {
                                    this.position++;
                                    hexChar2 = (hexChar2 * 16) + hexChar;
                                }
                                sb.append((char) hexChar2);
                            }
                        }
                    }
                }
                sb.append((char) charAt);
            }
            if (sb.length() == 0) {
                return null;
            }
            return sb.toString();
        }

        public String nextPropertyValue() {
            if (empty()) {
                return null;
            }
            int i3 = this.position;
            int charAt = this.input.charAt(i3);
            int i4 = i3;
            while (charAt != -1 && charAt != 59 && charAt != 125 && charAt != 33 && !isEOL(charAt)) {
                if (!isWhitespace(charAt)) {
                    i4 = this.position + 1;
                }
                charAt = advanceChar();
            }
            if (this.position > i3) {
                return this.input.substring(i3, i4);
            }
            this.position = i3;
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0053  */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x0130  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x0135  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean nextSimpleSelector(com.caverock.androidsvg.CSSParser.Selector r11) throws com.caverock.androidsvg.CSSParseException {
            /*
                r10 = this;
                boolean r0 = r10.empty()
                r1 = 0
                if (r0 == 0) goto L_0x0008
                return r1
            L_0x0008:
                int r0 = r10.position
                boolean r2 = r11.isEmpty()
                r3 = 0
                if (r2 != 0) goto L_0x002d
                r2 = 62
                boolean r2 = r10.consume((char) r2)
                if (r2 == 0) goto L_0x001f
                com.caverock.androidsvg.CSSParser$Combinator r2 = com.caverock.androidsvg.CSSParser.Combinator.CHILD
                r10.skipWhitespace()
                goto L_0x002e
            L_0x001f:
                r2 = 43
                boolean r2 = r10.consume((char) r2)
                if (r2 == 0) goto L_0x002d
                com.caverock.androidsvg.CSSParser$Combinator r2 = com.caverock.androidsvg.CSSParser.Combinator.FOLLOWS
                r10.skipWhitespace()
                goto L_0x002e
            L_0x002d:
                r2 = r3
            L_0x002e:
                r4 = 42
                boolean r4 = r10.consume((char) r4)
                if (r4 == 0) goto L_0x003c
                com.caverock.androidsvg.CSSParser$SimpleSelector r4 = new com.caverock.androidsvg.CSSParser$SimpleSelector
                r4.<init>(r2, r3)
                goto L_0x004d
            L_0x003c:
                java.lang.String r4 = r10.nextIdentifier()
                if (r4 == 0) goto L_0x004c
                com.caverock.androidsvg.CSSParser$SimpleSelector r5 = new com.caverock.androidsvg.CSSParser$SimpleSelector
                r5.<init>(r2, r4)
                r11.addedElement()
                r4 = r5
                goto L_0x004d
            L_0x004c:
                r4 = r3
            L_0x004d:
                boolean r5 = r10.empty()
                if (r5 != 0) goto L_0x012e
                r5 = 46
                boolean r5 = r10.consume((char) r5)
                if (r5 == 0) goto L_0x007b
                if (r4 != 0) goto L_0x0062
                com.caverock.androidsvg.CSSParser$SimpleSelector r4 = new com.caverock.androidsvg.CSSParser$SimpleSelector
                r4.<init>(r2, r3)
            L_0x0062:
                java.lang.String r5 = r10.nextIdentifier()
                if (r5 == 0) goto L_0x0073
                java.lang.String r6 = "class"
                com.caverock.androidsvg.CSSParser$AttribOp r7 = com.caverock.androidsvg.CSSParser.AttribOp.EQUALS
                r4.addAttrib(r6, r7, r5)
                r11.addedAttributeOrPseudo()
                goto L_0x004d
            L_0x0073:
                com.caverock.androidsvg.CSSParseException r10 = new com.caverock.androidsvg.CSSParseException
                java.lang.String r11 = "Invalid \".class\" simpleSelectors"
                r10.<init>(r11)
                throw r10
            L_0x007b:
                r5 = 35
                boolean r5 = r10.consume((char) r5)
                if (r5 == 0) goto L_0x00a3
                if (r4 != 0) goto L_0x008a
                com.caverock.androidsvg.CSSParser$SimpleSelector r4 = new com.caverock.androidsvg.CSSParser$SimpleSelector
                r4.<init>(r2, r3)
            L_0x008a:
                java.lang.String r5 = r10.nextIdentifier()
                if (r5 == 0) goto L_0x009b
                java.lang.String r6 = "id"
                com.caverock.androidsvg.CSSParser$AttribOp r7 = com.caverock.androidsvg.CSSParser.AttribOp.EQUALS
                r4.addAttrib(r6, r7, r5)
                r11.addedIdAttribute()
                goto L_0x004d
            L_0x009b:
                com.caverock.androidsvg.CSSParseException r10 = new com.caverock.androidsvg.CSSParseException
                java.lang.String r11 = "Invalid \"#id\" simpleSelectors"
                r10.<init>(r11)
                throw r10
            L_0x00a3:
                r5 = 91
                boolean r5 = r10.consume((char) r5)
                if (r5 == 0) goto L_0x011a
                if (r4 != 0) goto L_0x00b2
                com.caverock.androidsvg.CSSParser$SimpleSelector r4 = new com.caverock.androidsvg.CSSParser$SimpleSelector
                r4.<init>(r2, r3)
            L_0x00b2:
                r10.skipWhitespace()
                java.lang.String r5 = r10.nextIdentifier()
                java.lang.String r6 = "Invalid attribute simpleSelectors"
                if (r5 == 0) goto L_0x0114
                r10.skipWhitespace()
                r7 = 61
                boolean r7 = r10.consume((char) r7)
                if (r7 == 0) goto L_0x00cb
                com.caverock.androidsvg.CSSParser$AttribOp r7 = com.caverock.androidsvg.CSSParser.AttribOp.EQUALS
                goto L_0x00e4
            L_0x00cb:
                java.lang.String r7 = "~="
                boolean r7 = r10.consume((java.lang.String) r7)
                if (r7 == 0) goto L_0x00d7
                com.caverock.androidsvg.CSSParser$AttribOp r7 = com.caverock.androidsvg.CSSParser.AttribOp.INCLUDES
                goto L_0x00e4
            L_0x00d7:
                java.lang.String r7 = "|="
                boolean r7 = r10.consume((java.lang.String) r7)
                if (r7 == 0) goto L_0x00e3
                com.caverock.androidsvg.CSSParser$AttribOp r7 = com.caverock.androidsvg.CSSParser.AttribOp.DASHMATCH
                goto L_0x00e4
            L_0x00e3:
                r7 = r3
            L_0x00e4:
                if (r7 == 0) goto L_0x00f9
                r10.skipWhitespace()
                java.lang.String r8 = r10.nextAttribValue()
                if (r8 == 0) goto L_0x00f3
                r10.skipWhitespace()
                goto L_0x00fa
            L_0x00f3:
                com.caverock.androidsvg.CSSParseException r10 = new com.caverock.androidsvg.CSSParseException
                r10.<init>(r6)
                throw r10
            L_0x00f9:
                r8 = r3
            L_0x00fa:
                r9 = 93
                boolean r9 = r10.consume((char) r9)
                if (r9 == 0) goto L_0x010e
                if (r7 != 0) goto L_0x0106
                com.caverock.androidsvg.CSSParser$AttribOp r7 = com.caverock.androidsvg.CSSParser.AttribOp.EXISTS
            L_0x0106:
                r4.addAttrib(r5, r7, r8)
                r11.addedAttributeOrPseudo()
                goto L_0x004d
            L_0x010e:
                com.caverock.androidsvg.CSSParseException r10 = new com.caverock.androidsvg.CSSParseException
                r10.<init>(r6)
                throw r10
            L_0x0114:
                com.caverock.androidsvg.CSSParseException r10 = new com.caverock.androidsvg.CSSParseException
                r10.<init>(r6)
                throw r10
            L_0x011a:
                r5 = 58
                boolean r5 = r10.consume((char) r5)
                if (r5 == 0) goto L_0x012e
                if (r4 != 0) goto L_0x0129
                com.caverock.androidsvg.CSSParser$SimpleSelector r4 = new com.caverock.androidsvg.CSSParser$SimpleSelector
                r4.<init>(r2, r3)
            L_0x0129:
                r10.parsePseudoClass(r11, r4)
                goto L_0x004d
            L_0x012e:
                if (r4 == 0) goto L_0x0135
                r11.add(r4)
                r10 = 1
                return r10
            L_0x0135:
                r10.position = r0
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.CSSParser.CSSTextScanner.nextSimpleSelector(com.caverock.androidsvg.CSSParser$Selector):boolean");
        }

        public String nextURL() {
            if (empty()) {
                return null;
            }
            int i3 = this.position;
            if (!consume("url(")) {
                return null;
            }
            skipWhitespace();
            String nextCSSString = nextCSSString();
            if (nextCSSString == null) {
                nextCSSString = nextLegacyURL();
            }
            if (nextCSSString == null) {
                this.position = i3;
                return null;
            }
            skipWhitespace();
            if (empty() || consume(")")) {
                return nextCSSString;
            }
            this.position = i3;
            return null;
        }
    }

    public enum Combinator {
        DESCENDANT,
        CHILD,
        FOLLOWS
    }

    public enum MediaType {
        all,
        aural,
        braille,
        embossed,
        handheld,
        print,
        projection,
        screen,
        speech,
        tty,
        tv
    }

    public interface PseudoClass {
        boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase);
    }

    public static class PseudoClassAnPlusB implements PseudoClass {

        /* renamed from: a  reason: collision with root package name */
        private int f6518a;

        /* renamed from: b  reason: collision with root package name */
        private int f6519b;
        private boolean isFromStart;
        private boolean isOfType;
        private String nodeName;

        public PseudoClassAnPlusB(int i3, int i4, boolean z2, boolean z3, String str) {
            this.f6518a = i3;
            this.f6519b = i4;
            this.isFromStart = z2;
            this.isOfType = z3;
            this.nodeName = str;
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            int i3;
            int i4;
            String nodeName2 = (!this.isOfType || this.nodeName != null) ? this.nodeName : svgElementBase.getNodeName();
            SVG.SvgContainer svgContainer = svgElementBase.parent;
            if (svgContainer != null) {
                Iterator<SVG.SvgObject> it = svgContainer.getChildren().iterator();
                i4 = 0;
                i3 = 0;
                while (it.hasNext()) {
                    SVG.SvgElementBase svgElementBase2 = (SVG.SvgElementBase) it.next();
                    if (svgElementBase2 == svgElementBase) {
                        i4 = i3;
                    }
                    if (nodeName2 == null || svgElementBase2.getNodeName().equals(nodeName2)) {
                        i3++;
                    }
                }
            } else {
                i4 = 0;
                i3 = 1;
            }
            int i5 = this.isFromStart ? i4 + 1 : i3 - i4;
            int i6 = this.f6518a;
            if (i6 == 0) {
                return i5 == this.f6519b;
            }
            int i7 = this.f6519b;
            if ((i5 - i7) % i6 == 0) {
                return Integer.signum(i5 - i7) == 0 || Integer.signum(i5 - this.f6519b) == Integer.signum(this.f6518a);
            }
            return false;
        }

        public String toString() {
            String str = this.isFromStart ? "" : "last-";
            return this.isOfType ? String.format("nth-%schild(%dn%+d of type <%s>)", new Object[]{str, Integer.valueOf(this.f6518a), Integer.valueOf(this.f6519b), this.nodeName}) : String.format("nth-%schild(%dn%+d)", new Object[]{str, Integer.valueOf(this.f6518a), Integer.valueOf(this.f6519b)});
        }
    }

    public static class PseudoClassEmpty implements PseudoClass {
        private PseudoClassEmpty() {
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            return !(svgElementBase instanceof SVG.SvgContainer) || ((SVG.SvgContainer) svgElementBase).getChildren().size() == 0;
        }

        public String toString() {
            return "empty";
        }

        public /* synthetic */ PseudoClassEmpty(AnonymousClass1 r12) {
            this();
        }
    }

    public enum PseudoClassIdents {
        target,
        root,
        nth_child,
        nth_last_child,
        nth_of_type,
        nth_last_of_type,
        first_child,
        last_child,
        first_of_type,
        last_of_type,
        only_child,
        only_of_type,
        empty,
        not,
        lang,
        link,
        visited,
        hover,
        active,
        focus,
        enabled,
        disabled,
        checked,
        indeterminate,
        UNSUPPORTED;
        
        private static final Map<String, PseudoClassIdents> cache = null;

        static {
            cache = new HashMap();
            for (PseudoClassIdents pseudoClassIdents : values()) {
                if (pseudoClassIdents != UNSUPPORTED) {
                    cache.put(pseudoClassIdents.name().replace('_', SignatureVisitor.SUPER), pseudoClassIdents);
                }
            }
        }

        public static PseudoClassIdents fromString(String str) {
            PseudoClassIdents pseudoClassIdents = cache.get(str);
            return pseudoClassIdents != null ? pseudoClassIdents : UNSUPPORTED;
        }
    }

    public static class PseudoClassNot implements PseudoClass {
        private List<Selector> selectorGroup;

        public PseudoClassNot(List<Selector> list) {
            this.selectorGroup = list;
        }

        public int getSpecificity() {
            int i3 = Integer.MIN_VALUE;
            for (Selector selector : this.selectorGroup) {
                int i4 = selector.specificity;
                if (i4 > i3) {
                    i3 = i4;
                }
            }
            return i3;
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            for (Selector ruleMatch : this.selectorGroup) {
                if (CSSParser.ruleMatch(ruleMatchContext, ruleMatch, svgElementBase)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return C0118y.h(")", this.selectorGroup, new StringBuilder("not("));
        }
    }

    public static class PseudoClassNotSupported implements PseudoClass {
        private String clazz;

        public PseudoClassNotSupported(String str) {
            this.clazz = str;
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            return false;
        }

        public String toString() {
            return this.clazz;
        }
    }

    public static class PseudoClassOnlyChild implements PseudoClass {
        private boolean isOfType;
        private String nodeName;

        public PseudoClassOnlyChild(boolean z2, String str) {
            this.isOfType = z2;
            this.nodeName = str;
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            int i3;
            String nodeName2 = (!this.isOfType || this.nodeName != null) ? this.nodeName : svgElementBase.getNodeName();
            SVG.SvgContainer svgContainer = svgElementBase.parent;
            if (svgContainer != null) {
                Iterator<SVG.SvgObject> it = svgContainer.getChildren().iterator();
                i3 = 0;
                while (it.hasNext()) {
                    SVG.SvgElementBase svgElementBase2 = (SVG.SvgElementBase) it.next();
                    if (nodeName2 == null || svgElementBase2.getNodeName().equals(nodeName2)) {
                        i3++;
                    }
                }
            } else {
                i3 = 1;
            }
            return i3 == 1;
        }

        public String toString() {
            return this.isOfType ? a.l("only-of-type <", this.nodeName, ">") : "only-child";
        }
    }

    public static class PseudoClassRoot implements PseudoClass {
        private PseudoClassRoot() {
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            return svgElementBase.parent == null;
        }

        public String toString() {
            return "root";
        }

        public /* synthetic */ PseudoClassRoot(AnonymousClass1 r12) {
            this();
        }
    }

    public static class PseudoClassTarget implements PseudoClass {
        private PseudoClassTarget() {
        }

        public boolean matches(RuleMatchContext ruleMatchContext, SVG.SvgElementBase svgElementBase) {
            return ruleMatchContext != null && svgElementBase == ruleMatchContext.targetElement;
        }

        public String toString() {
            return TypedValues.AttributesType.S_TARGET;
        }

        public /* synthetic */ PseudoClassTarget(AnonymousClass1 r12) {
            this();
        }
    }

    public static class Rule {
        Selector selector;
        Source source;
        SVG.Style style;

        public Rule(Selector selector2, SVG.Style style2, Source source2) {
            this.selector = selector2;
            this.style = style2;
            this.source = source2;
        }

        public String toString() {
            return String.valueOf(this.selector) + " {...} (src=" + this.source + ")";
        }
    }

    public static class RuleMatchContext {
        SVG.SvgElementBase targetElement;

        public String toString() {
            SVG.SvgElementBase svgElementBase = this.targetElement;
            return svgElementBase != null ? C0118y.g("<", svgElementBase.getNodeName(), " id=\"", this.targetElement.id, "\">") : "";
        }
    }

    public static class Ruleset {
        private List<Rule> rules = null;

        public void add(Rule rule) {
            if (this.rules == null) {
                this.rules = new ArrayList();
            }
            for (int i3 = 0; i3 < this.rules.size(); i3++) {
                if (this.rules.get(i3).selector.specificity > rule.selector.specificity) {
                    this.rules.add(i3, rule);
                    return;
                }
            }
            this.rules.add(rule);
        }

        public void addAll(Ruleset ruleset) {
            if (ruleset.rules != null) {
                if (this.rules == null) {
                    this.rules = new ArrayList(ruleset.rules.size());
                }
                for (Rule add : ruleset.rules) {
                    add(add);
                }
            }
        }

        public List<Rule> getRules() {
            return this.rules;
        }

        public boolean isEmpty() {
            List<Rule> list = this.rules;
            return list == null || list.isEmpty();
        }

        public void removeFromSource(Source source) {
            List<Rule> list = this.rules;
            if (list != null) {
                Iterator<Rule> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().source == source) {
                        it.remove();
                    }
                }
            }
        }

        public int ruleCount() {
            List<Rule> list = this.rules;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public String toString() {
            if (this.rules == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (Rule rule : this.rules) {
                sb.append(rule.toString());
                sb.append(10);
            }
            return sb.toString();
        }
    }

    public static class SimpleSelector {
        List<Attrib> attribs = null;
        Combinator combinator = null;
        List<PseudoClass> pseudos = null;
        String tag = null;

        public SimpleSelector(Combinator combinator2, String str) {
            this.combinator = combinator2 == null ? Combinator.DESCENDANT : combinator2;
            this.tag = str;
        }

        public void addAttrib(String str, AttribOp attribOp, String str2) {
            if (this.attribs == null) {
                this.attribs = new ArrayList();
            }
            this.attribs.add(new Attrib(str, attribOp, str2));
        }

        public void addPseudo(PseudoClass pseudoClass) {
            if (this.pseudos == null) {
                this.pseudos = new ArrayList();
            }
            this.pseudos.add(pseudoClass);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Combinator combinator2 = this.combinator;
            if (combinator2 == Combinator.CHILD) {
                sb.append("> ");
            } else if (combinator2 == Combinator.FOLLOWS) {
                sb.append("+ ");
            }
            String str = this.tag;
            if (str == null) {
                str = Marker.ANY_MARKER;
            }
            sb.append(str);
            List<Attrib> list = this.attribs;
            if (list != null) {
                for (Attrib next : list) {
                    sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                    sb.append(next.name);
                    int i3 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$CSSParser$AttribOp[next.operation.ordinal()];
                    if (i3 == 1) {
                        sb.append(SignatureVisitor.INSTANCEOF);
                        sb.append(next.value);
                    } else if (i3 == 2) {
                        sb.append("~=");
                        sb.append(next.value);
                    } else if (i3 == 3) {
                        sb.append("|=");
                        sb.append(next.value);
                    }
                    sb.append(AbstractJsonLexerKt.END_LIST);
                }
            }
            List<PseudoClass> list2 = this.pseudos;
            if (list2 != null) {
                for (PseudoClass append : list2) {
                    sb.append(AbstractJsonLexerKt.COLON);
                    sb.append(append);
                }
            }
            return sb.toString();
        }
    }

    public enum Source {
        Document,
        RenderOptions
    }

    public CSSParser() {
        this(MediaType.screen, Source.Document);
    }

    private static int getChildPosition(List<SVG.SvgContainer> list, int i3, SVG.SvgElementBase svgElementBase) {
        int i4 = 0;
        if (i3 < 0) {
            return 0;
        }
        SVG.SvgContainer svgContainer = list.get(i3);
        SVG.SvgContainer svgContainer2 = svgElementBase.parent;
        if (svgContainer != svgContainer2) {
            return -1;
        }
        for (SVG.SvgObject svgObject : svgContainer2.getChildren()) {
            if (svgObject == svgElementBase) {
                return i4;
            }
            i4++;
        }
        return -1;
    }

    public static boolean mediaMatches(String str, MediaType mediaType) {
        CSSTextScanner cSSTextScanner = new CSSTextScanner(str);
        cSSTextScanner.skipWhitespace();
        return mediaMatches(parseMediaList(cSSTextScanner), mediaType);
    }

    private void parseAtRule(Ruleset ruleset, CSSTextScanner cSSTextScanner) throws CSSParseException {
        String nextIdentifier = cSSTextScanner.nextIdentifier();
        cSSTextScanner.skipWhitespace();
        if (nextIdentifier != null) {
            if (!this.inMediaRule && nextIdentifier.equals(SVGParser.XML_STYLESHEET_ATTR_MEDIA)) {
                List<MediaType> parseMediaList = parseMediaList(cSSTextScanner);
                if (cSSTextScanner.consume((char) AbstractJsonLexerKt.BEGIN_OBJ)) {
                    cSSTextScanner.skipWhitespace();
                    if (mediaMatches(parseMediaList, this.deviceMediaType)) {
                        this.inMediaRule = true;
                        ruleset.addAll(parseRuleset(cSSTextScanner));
                        this.inMediaRule = false;
                    } else {
                        parseRuleset(cSSTextScanner);
                    }
                    if (!cSSTextScanner.empty() && !cSSTextScanner.consume((char) AbstractJsonLexerKt.END_OBJ)) {
                        throw new CSSParseException("Invalid @media rule: expected '}' at end of rule set");
                    }
                } else {
                    throw new CSSParseException("Invalid @media rule: missing rule set");
                }
            } else if (this.inMediaRule || !nextIdentifier.equals("import")) {
                warn("Ignoring @%s rule", nextIdentifier);
                skipAtRule(cSSTextScanner);
            } else {
                String nextURL = cSSTextScanner.nextURL();
                if (nextURL == null) {
                    nextURL = cSSTextScanner.nextCSSString();
                }
                if (nextURL != null) {
                    cSSTextScanner.skipWhitespace();
                    List<MediaType> parseMediaList2 = parseMediaList(cSSTextScanner);
                    if (!cSSTextScanner.empty() && !cSSTextScanner.consume(';')) {
                        throw new CSSParseException("Invalid @media rule: expected '}' at end of rule set");
                    } else if (SVG.getFileResolver() != null && mediaMatches(parseMediaList2, this.deviceMediaType)) {
                        String resolveCSSStyleSheet = SVG.getFileResolver().resolveCSSStyleSheet(nextURL);
                        if (resolveCSSStyleSheet != null) {
                            ruleset.addAll(parse(resolveCSSStyleSheet));
                        } else {
                            return;
                        }
                    }
                } else {
                    throw new CSSParseException("Invalid @import rule: expected string or url()");
                }
            }
            cSSTextScanner.skipWhitespace();
            return;
        }
        throw new CSSParseException("Invalid '@' rule");
    }

    public static List<String> parseClassAttribute(String str) {
        CSSTextScanner cSSTextScanner = new CSSTextScanner(str);
        ArrayList arrayList = null;
        while (!cSSTextScanner.empty()) {
            String nextToken = cSSTextScanner.nextToken();
            if (nextToken != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(nextToken);
                cSSTextScanner.skipWhitespace();
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.caverock.androidsvg.SVG.Style parseDeclarations(com.caverock.androidsvg.CSSParser.CSSTextScanner r4) throws com.caverock.androidsvg.CSSParseException {
        /*
            r3 = this;
            com.caverock.androidsvg.SVG$Style r3 = new com.caverock.androidsvg.SVG$Style
            r3.<init>()
        L_0x0005:
            java.lang.String r0 = r4.nextIdentifier()
            r4.skipWhitespace()
            r1 = 58
            boolean r1 = r4.consume((char) r1)
            if (r1 == 0) goto L_0x0061
            r4.skipWhitespace()
            java.lang.String r1 = r4.nextPropertyValue()
            if (r1 == 0) goto L_0x0059
            r4.skipWhitespace()
            r2 = 33
            boolean r2 = r4.consume((char) r2)
            if (r2 == 0) goto L_0x003f
            r4.skipWhitespace()
            java.lang.String r2 = "important"
            boolean r2 = r4.consume((java.lang.String) r2)
            if (r2 == 0) goto L_0x0037
            r4.skipWhitespace()
            goto L_0x003f
        L_0x0037:
            com.caverock.androidsvg.CSSParseException r3 = new com.caverock.androidsvg.CSSParseException
            java.lang.String r4 = "Malformed rule set: found unexpected '!'"
            r3.<init>(r4)
            throw r3
        L_0x003f:
            r2 = 59
            r4.consume((char) r2)
            com.caverock.androidsvg.SVGParser.processStyleProperty(r3, r0, r1)
            r4.skipWhitespace()
            boolean r0 = r4.empty()
            if (r0 != 0) goto L_0x0058
            r0 = 125(0x7d, float:1.75E-43)
            boolean r0 = r4.consume((char) r0)
            if (r0 == 0) goto L_0x0005
        L_0x0058:
            return r3
        L_0x0059:
            com.caverock.androidsvg.CSSParseException r3 = new com.caverock.androidsvg.CSSParseException
            java.lang.String r4 = "Expected property value"
            r3.<init>(r4)
            throw r3
        L_0x0061:
            com.caverock.androidsvg.CSSParseException r3 = new com.caverock.androidsvg.CSSParseException
            java.lang.String r4 = "Expected ':'"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.CSSParser.parseDeclarations(com.caverock.androidsvg.CSSParser$CSSTextScanner):com.caverock.androidsvg.SVG$Style");
    }

    private static List<MediaType> parseMediaList(CSSTextScanner cSSTextScanner) {
        String nextWord;
        ArrayList arrayList = new ArrayList();
        while (!cSSTextScanner.empty() && (nextWord = cSSTextScanner.nextWord()) != null) {
            try {
                arrayList.add(MediaType.valueOf(nextWord));
            } catch (IllegalArgumentException unused) {
            }
            if (!cSSTextScanner.skipCommaWhitespace()) {
                break;
            }
        }
        return arrayList;
    }

    private boolean parseRule(Ruleset ruleset, CSSTextScanner cSSTextScanner) throws CSSParseException {
        List<Selector> access$400 = cSSTextScanner.nextSelectorGroup();
        if (access$400 == null || access$400.isEmpty()) {
            return false;
        }
        if (cSSTextScanner.consume((char) AbstractJsonLexerKt.BEGIN_OBJ)) {
            cSSTextScanner.skipWhitespace();
            SVG.Style parseDeclarations = parseDeclarations(cSSTextScanner);
            cSSTextScanner.skipWhitespace();
            for (Selector rule : access$400) {
                ruleset.add(new Rule(rule, parseDeclarations, this.source));
            }
            return true;
        }
        throw new CSSParseException("Malformed rule block: expected '{'");
    }

    private Ruleset parseRuleset(CSSTextScanner cSSTextScanner) {
        Ruleset ruleset = new Ruleset();
        while (!cSSTextScanner.empty()) {
            try {
                if (!cSSTextScanner.consume("<!--")) {
                    if (!cSSTextScanner.consume("-->")) {
                        if (!cSSTextScanner.consume('@')) {
                            if (!parseRule(ruleset, cSSTextScanner)) {
                                break;
                            }
                        } else {
                            parseAtRule(ruleset, cSSTextScanner);
                        }
                    }
                }
            } catch (CSSParseException e3) {
                Log.e(TAG, "CSS parser terminated early due to error: " + e3.getMessage());
            }
        }
        return ruleset;
    }

    public static boolean ruleMatch(RuleMatchContext ruleMatchContext, Selector selector, SVG.SvgElementBase svgElementBase) {
        ArrayList arrayList = new ArrayList();
        for (SVG.SvgContainer svgContainer = svgElementBase.parent; svgContainer != null; svgContainer = ((SVG.SvgObject) svgContainer).parent) {
            arrayList.add(0, svgContainer);
        }
        int size = arrayList.size() - 1;
        if (selector.size() == 1) {
            return selectorMatch(ruleMatchContext, selector.get(0), arrayList, size, svgElementBase);
        }
        return ruleMatch(ruleMatchContext, selector, selector.size() - 1, arrayList, size, svgElementBase);
    }

    private static boolean ruleMatchOnAncestors(RuleMatchContext ruleMatchContext, Selector selector, int i3, List<SVG.SvgContainer> list, int i4) {
        SimpleSelector simpleSelector = selector.get(i3);
        SVG.SvgElementBase svgElementBase = (SVG.SvgElementBase) list.get(i4);
        if (!selectorMatch(ruleMatchContext, simpleSelector, list, i4, svgElementBase)) {
            return false;
        }
        Combinator combinator = simpleSelector.combinator;
        if (combinator == Combinator.DESCENDANT) {
            if (i3 == 0) {
                return true;
            }
            while (i4 > 0) {
                i4--;
                if (ruleMatchOnAncestors(ruleMatchContext, selector, i3 - 1, list, i4)) {
                    return true;
                }
            }
            return false;
        } else if (combinator == Combinator.CHILD) {
            return ruleMatchOnAncestors(ruleMatchContext, selector, i3 - 1, list, i4 - 1);
        } else {
            int childPosition = getChildPosition(list, i4, svgElementBase);
            if (childPosition <= 0) {
                return false;
            }
            return ruleMatch(ruleMatchContext, selector, i3 - 1, list, i4, (SVG.SvgElementBase) svgElementBase.parent.getChildren().get(childPosition - 1));
        }
    }

    private static boolean selectorMatch(RuleMatchContext ruleMatchContext, SimpleSelector simpleSelector, List<SVG.SvgContainer> list, int i3, SVG.SvgElementBase svgElementBase) {
        List<String> list2;
        String str = simpleSelector.tag;
        if (str != null && !str.equals(svgElementBase.getNodeName().toLowerCase(Locale.US))) {
            return false;
        }
        List<Attrib> list3 = simpleSelector.attribs;
        if (list3 != null) {
            for (Attrib next : list3) {
                String str2 = next.name;
                str2.getClass();
                if (!str2.equals("id")) {
                    if (!str2.equals(CLASS) || (list2 = svgElementBase.classNames) == null || !list2.contains(next.value)) {
                        return false;
                    }
                } else if (!next.value.equals(svgElementBase.id)) {
                    return false;
                }
            }
        }
        List<PseudoClass> list4 = simpleSelector.pseudos;
        if (list4 == null) {
            return true;
        }
        for (PseudoClass matches : list4) {
            if (!matches.matches(ruleMatchContext, svgElementBase)) {
                return false;
            }
        }
        return true;
    }

    private void skipAtRule(CSSTextScanner cSSTextScanner) {
        int i3 = 0;
        while (!cSSTextScanner.empty()) {
            int intValue = cSSTextScanner.nextChar().intValue();
            if (intValue != 59 || i3 != 0) {
                if (intValue == 123) {
                    i3++;
                } else if (intValue == 125 && i3 > 0 && i3 - 1 == 0) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private static void warn(String str, Object... objArr) {
        Log.w(TAG, String.format(str, objArr));
    }

    public Ruleset parse(String str) {
        CSSTextScanner cSSTextScanner = new CSSTextScanner(str);
        cSSTextScanner.skipWhitespace();
        return parseRuleset(cSSTextScanner);
    }

    public CSSParser(Source source2) {
        this(MediaType.screen, source2);
    }

    public static class Selector {
        List<SimpleSelector> simpleSelectors;
        int specificity;

        private Selector() {
            this.simpleSelectors = null;
            this.specificity = 0;
        }

        public void add(SimpleSelector simpleSelector) {
            if (this.simpleSelectors == null) {
                this.simpleSelectors = new ArrayList();
            }
            this.simpleSelectors.add(simpleSelector);
        }

        public void addedAttributeOrPseudo() {
            this.specificity += 1000;
        }

        public void addedElement() {
            this.specificity++;
        }

        public void addedIdAttribute() {
            this.specificity += 1000000;
        }

        public SimpleSelector get(int i3) {
            return this.simpleSelectors.get(i3);
        }

        public boolean isEmpty() {
            List<SimpleSelector> list = this.simpleSelectors;
            return list == null || list.isEmpty();
        }

        public int size() {
            List<SimpleSelector> list = this.simpleSelectors;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (SimpleSelector append : this.simpleSelectors) {
                sb.append(append);
                sb.append(' ');
            }
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            return android.support.v4.media.session.a.p(sb, this.specificity, AbstractJsonLexerKt.END_LIST);
        }

        public /* synthetic */ Selector(AnonymousClass1 r12) {
            this();
        }
    }

    public CSSParser(MediaType mediaType, Source source2) {
        this.inMediaRule = false;
        this.deviceMediaType = mediaType;
        this.source = source2;
    }

    private static boolean mediaMatches(List<MediaType> list, MediaType mediaType) {
        for (MediaType next : list) {
            if (next == MediaType.all) {
                return true;
            }
            if (next == mediaType) {
                return true;
            }
        }
        return false;
    }

    private static boolean ruleMatch(RuleMatchContext ruleMatchContext, Selector selector, int i3, List<SVG.SvgContainer> list, int i4, SVG.SvgElementBase svgElementBase) {
        SimpleSelector simpleSelector = selector.get(i3);
        if (!selectorMatch(ruleMatchContext, simpleSelector, list, i4, svgElementBase)) {
            return false;
        }
        Combinator combinator = simpleSelector.combinator;
        if (combinator == Combinator.DESCENDANT) {
            if (i3 == 0) {
                return true;
            }
            while (i4 >= 0) {
                if (ruleMatchOnAncestors(ruleMatchContext, selector, i3 - 1, list, i4)) {
                    return true;
                }
                i4--;
            }
            return false;
        } else if (combinator == Combinator.CHILD) {
            return ruleMatchOnAncestors(ruleMatchContext, selector, i3 - 1, list, i4);
        } else {
            int childPosition = getChildPosition(list, i4, svgElementBase);
            if (childPosition <= 0) {
                return false;
            }
            return ruleMatch(ruleMatchContext, selector, i3 - 1, list, i4, (SVG.SvgElementBase) svgElementBase.parent.getChildren().get(childPosition - 1));
        }
    }
}
