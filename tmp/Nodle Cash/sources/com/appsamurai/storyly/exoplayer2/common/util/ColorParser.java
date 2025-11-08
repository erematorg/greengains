package com.appsamurai.storyly.exoplayer2.common.util;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.google.common.base.Ascii;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class ColorParser {
    private static final Map<String, Integer> COLOR_MAP;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    private static final Pattern RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");
    private static final Pattern RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");

    static {
        HashMap hashMap = new HashMap();
        COLOR_MAP = hashMap;
        a.c(-984833, hashMap, "aliceblue", -332841, "antiquewhite");
        a.d(hashMap, "aqua", -16711681, -8388652, "aquamarine");
        a.c(-983041, hashMap, "azure", -657956, "beige");
        a.c(-6972, hashMap, "bisque", ViewCompat.MEASURED_STATE_MASK, "black");
        a.c(-5171, hashMap, "blanchedalmond", -16776961, "blue");
        a.c(-7722014, hashMap, "blueviolet", -5952982, "brown");
        a.c(-2180985, hashMap, "burlywood", -10510688, "cadetblue");
        a.c(-8388864, hashMap, "chartreuse", -2987746, "chocolate");
        a.c(-32944, hashMap, "coral", -10185235, "cornflowerblue");
        a.c(-1828, hashMap, "cornsilk", -2354116, "crimson");
        a.d(hashMap, "cyan", -16711681, -16777077, "darkblue");
        a.c(-16741493, hashMap, "darkcyan", -4684277, "darkgoldenrod");
        a.d(hashMap, "darkgray", -5658199, -16751616, "darkgreen");
        a.d(hashMap, "darkgrey", -5658199, -4343957, "darkkhaki");
        a.c(-7667573, hashMap, "darkmagenta", -11179217, "darkolivegreen");
        a.c(-29696, hashMap, "darkorange", -6737204, "darkorchid");
        a.c(-7667712, hashMap, "darkred", -1468806, "darksalmon");
        a.c(-7357297, hashMap, "darkseagreen", -12042869, "darkslateblue");
        hashMap.put("darkslategray", -13676721);
        hashMap.put("darkslategrey", -13676721);
        a.d(hashMap, "darkturquoise", -16724271, -7077677, "darkviolet");
        a.c(-60269, hashMap, "deeppink", -16728065, "deepskyblue");
        hashMap.put("dimgray", -9868951);
        hashMap.put("dimgrey", -9868951);
        a.d(hashMap, "dodgerblue", -14774017, -5103070, "firebrick");
        a.c(-1296, hashMap, "floralwhite", -14513374, "forestgreen");
        a.d(hashMap, "fuchsia", -65281, -2302756, "gainsboro");
        a.c(-460545, hashMap, "ghostwhite", -10496, "gold");
        hashMap.put("goldenrod", -2448096);
        hashMap.put("gray", -8355712);
        a.c(-16744448, hashMap, "green", -5374161, "greenyellow");
        a.d(hashMap, "grey", -8355712, -983056, "honeydew");
        a.c(-38476, hashMap, "hotpink", -3318692, "indianred");
        a.c(-11861886, hashMap, "indigo", -16, "ivory");
        a.c(-989556, hashMap, "khaki", -1644806, "lavender");
        a.c(-3851, hashMap, "lavenderblush", -8586240, "lawngreen");
        a.c(-1331, hashMap, "lemonchiffon", -5383962, "lightblue");
        a.c(-1015680, hashMap, "lightcoral", -2031617, "lightcyan");
        hashMap.put("lightgoldenrodyellow", -329006);
        hashMap.put("lightgray", -2894893);
        hashMap.put("lightgreen", -7278960);
        hashMap.put("lightgrey", -2894893);
        a.c(-18751, hashMap, "lightpink", -24454, "lightsalmon");
        a.c(-14634326, hashMap, "lightseagreen", -7876870, "lightskyblue");
        hashMap.put("lightslategray", -8943463);
        hashMap.put("lightslategrey", -8943463);
        a.d(hashMap, "lightsteelblue", -5192482, -32, "lightyellow");
        a.c(-16711936, hashMap, "lime", -13447886, "limegreen");
        hashMap.put("linen", -331546);
        hashMap.put("magenta", -65281);
        a.c(-8388608, hashMap, "maroon", -10039894, "mediumaquamarine");
        a.c(-16777011, hashMap, "mediumblue", -4565549, "mediumorchid");
        a.c(-7114533, hashMap, "mediumpurple", -12799119, "mediumseagreen");
        a.c(-8689426, hashMap, "mediumslateblue", -16713062, "mediumspringgreen");
        a.c(-12004916, hashMap, "mediumturquoise", -3730043, "mediumvioletred");
        a.c(-15132304, hashMap, "midnightblue", -655366, "mintcream");
        a.c(-6943, hashMap, "mistyrose", -6987, "moccasin");
        a.c(-8531, hashMap, "navajowhite", -16777088, "navy");
        a.c(-133658, hashMap, "oldlace", -8355840, "olive");
        a.c(-9728477, hashMap, "olivedrab", -23296, "orange");
        a.c(-47872, hashMap, "orangered", -2461482, "orchid");
        a.c(-1120086, hashMap, "palegoldenrod", -6751336, "palegreen");
        a.c(-5247250, hashMap, "paleturquoise", -2396013, "palevioletred");
        a.c(-4139, hashMap, "papayawhip", -9543, "peachpuff");
        a.c(-3308225, hashMap, "peru", -16181, "pink");
        a.c(-2252579, hashMap, "plum", -5185306, "powderblue");
        a.c(-8388480, hashMap, "purple", -10079335, "rebeccapurple");
        a.c(-65536, hashMap, "red", -4419697, "rosybrown");
        a.c(-12490271, hashMap, "royalblue", -7650029, "saddlebrown");
        a.c(-360334, hashMap, "salmon", -744352, "sandybrown");
        a.c(-13726889, hashMap, "seagreen", -2578, "seashell");
        a.c(-6270419, hashMap, "sienna", -4144960, "silver");
        a.c(-7876885, hashMap, "skyblue", -9807155, "slateblue");
        hashMap.put("slategray", -9404272);
        hashMap.put("slategrey", -9404272);
        a.d(hashMap, "snow", -1286, -16711809, "springgreen");
        a.c(-12156236, hashMap, "steelblue", -2968436, "tan");
        a.c(-16744320, hashMap, "teal", -2572328, "thistle");
        a.c(-40121, hashMap, "tomato", 0, "transparent");
        a.c(-12525360, hashMap, "turquoise", -1146130, "violet");
        a.c(-663885, hashMap, "wheat", -1, "white");
        a.c(-657931, hashMap, "whitesmoke", -256, "yellow");
        hashMap.put("yellowgreen", -6632142);
    }

    private ColorParser() {
    }

    @ColorInt
    private static int parseColorInternal(String str, boolean z2) {
        Assertions.checkArgument(!TextUtils.isEmpty(str));
        String replace = str.replace(StringUtils.SPACE, "");
        if (replace.charAt(0) == '#') {
            int parseLong = (int) Long.parseLong(replace.substring(1), 16);
            if (replace.length() == 7) {
                return -16777216 | parseLong;
            }
            if (replace.length() == 9) {
                return ((parseLong & 255) << 24) | (parseLong >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (replace.startsWith(RGBA)) {
            Matcher matcher = (z2 ? RGBA_PATTERN_FLOAT_ALPHA : RGBA_PATTERN_INT_ALPHA).matcher(replace);
            if (matcher.matches()) {
                return Color.argb(z2 ? (int) (Float.parseFloat((String) Assertions.checkNotNull(matcher.group(4))) * 255.0f) : Integer.parseInt((String) Assertions.checkNotNull(matcher.group(4)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(3)), 10));
            }
        } else if (replace.startsWith(RGB)) {
            Matcher matcher2 = RGB_PATTERN.matcher(replace);
            if (matcher2.matches()) {
                return Color.rgb(Integer.parseInt((String) Assertions.checkNotNull(matcher2.group(1)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher2.group(2)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher2.group(3)), 10));
            }
        } else {
            Integer num = COLOR_MAP.get(Ascii.toLowerCase(replace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    @ColorInt
    public static int parseCssColor(String str) {
        return parseColorInternal(str, true);
    }

    @ColorInt
    public static int parseTtmlColor(String str) {
        return parseColorInternal(str, false);
    }
}
