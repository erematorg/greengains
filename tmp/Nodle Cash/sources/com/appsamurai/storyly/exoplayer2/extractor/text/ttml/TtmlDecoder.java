package com.appsamurai.storyly.exoplayer2.extractor.text.ttml;

import A.a;
import android.text.Layout;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.util.XmlPullParserUtil;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.h;
import com.appsamurai.storyly.exoplayer2.extractor.text.SimpleSubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException;
import com.google.common.base.Ascii;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    static final Pattern SIGNED_PERCENTAGE = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    public static final class CellResolution {
        final int columns;
        final int rows;

        public CellResolution(int i3, int i4) {
            this.columns = i3;
            this.rows = i4;
        }
    }

    public static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        public FrameAndTickRate(float f2, int i3, int i4) {
            this.effectiveFrameRate = f2;
            this.subFrameRate = i3;
            this.tickRate = i4;
        }
    }

    public static final class TtsExtent {
        final int height;
        final int width;

        public TtsExtent(int i3, int i4) {
            this.width = i3;
            this.height = i4;
        }
    }

    public TtmlDecoder() {
        super(TAG);
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e3) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e3);
        }
    }

    private static TtmlStyle createIfNull(@Nullable TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals("body") || str.equals(TtmlNode.TAG_DIV) || str.equals(TtmlNode.TAG_P) || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals("image") || str.equals("data") || str.equals(TtmlNode.TAG_INFORMATION);
    }

    @Nullable
    private static Layout.Alignment parseAlignment(String str) {
        String lowerCase = Ascii.toLowerCase(str);
        lowerCase.getClass();
        char c3 = 65535;
        switch (lowerCase.hashCode()) {
            case -1364013995:
                if (lowerCase.equals(TtmlNode.CENTER)) {
                    c3 = 0;
                    break;
                }
                break;
            case 100571:
                if (lowerCase.equals("end")) {
                    c3 = 1;
                    break;
                }
                break;
            case 3317767:
                if (lowerCase.equals(TtmlNode.LEFT)) {
                    c3 = 2;
                    break;
                }
                break;
            case 108511772:
                if (lowerCase.equals(TtmlNode.RIGHT)) {
                    c3 = 3;
                    break;
                }
                break;
            case 109757538:
                if (lowerCase.equals(TtmlNode.START)) {
                    c3 = 4;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return Layout.Alignment.ALIGN_CENTER;
            case 1:
            case 3:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case 2:
            case 4:
                return Layout.Alignment.ALIGN_NORMAL;
            default:
                return null;
        }
    }

    private static CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed cell resolution: ".concat(attributeValue));
            return cellResolution;
        }
        try {
            int parseInt = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)));
            int parseInt2 = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2)));
            if (parseInt != 0 && parseInt2 != 0) {
                return new CellResolution(parseInt, parseInt2);
            }
            throw new SubtitleDecoderException("Invalid cell resolution " + parseInt + StringUtils.SPACE + parseInt2);
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed cell resolution: ".concat(attributeValue));
            return cellResolution;
        }
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException(a.m(new StringBuilder("Invalid number of entries for fontSize: "), JwtUtilsKt.JWT_DELIMITER, split.length));
        }
        if (matcher.matches()) {
            String str2 = (String) Assertions.checkNotNull(matcher.group(3));
            str2.getClass();
            char c3 = 65535;
            switch (str2.hashCode()) {
                case 37:
                    if (str2.equals("%")) {
                        c3 = 0;
                        break;
                    }
                    break;
                case 3240:
                    if (str2.equals("em")) {
                        c3 = 1;
                        break;
                    }
                    break;
                case 3592:
                    if (str2.equals("px")) {
                        c3 = 2;
                        break;
                    }
                    break;
            }
            switch (c3) {
                case 0:
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                case 1:
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case 2:
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                default:
                    throw new SubtitleDecoderException(a.l("Invalid unit for fontSize: '", str2, "'."));
            }
            ttmlStyle.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1))));
            return;
        }
        throw new SubtitleDecoderException(a.l("Invalid expression for fontSize: '", str, "'."));
    }

    private static FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        float f2;
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, StringUtils.SPACE);
            if (split.length == 2) {
                f2 = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        } else {
            f2 = 1.0f;
        }
        FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
        int i3 = frameAndTickRate.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i3 = Integer.parseInt(attributeValue3);
        }
        int i4 = frameAndTickRate.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i4 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) parseInt) * f2, i3, i4);
    }

    private static Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, CellResolution cellResolution, @Nullable TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                String id = parseStyleAttributes.getId();
                if (id != null) {
                    map.put(id, parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, cellResolution, ttsExtent);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_METADATA)) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private static void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "image") && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_ID)) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_METADATA));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode parseNode(org.xmlpull.v1.XmlPullParser r20, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode r21, java.util.Map<java.lang.String, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlRegion> r22, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.FrameAndTickRate r23) throws com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException {
        /*
            r0 = r20
            r9 = r21
            r1 = r23
            r2 = 1
            int r3 = r20.getAttributeCount()
            r4 = 0
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r5 = parseStyleAttributes(r0, r4)
            java.lang.String r8 = ""
            r12 = r4
            r11 = r8
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r8 = r12
            r4 = 0
        L_0x0025:
            if (r4 >= r3) goto L_0x00b6
            java.lang.String r10 = r0.getAttributeName(r4)
            java.lang.String r6 = r0.getAttributeValue(r4)
            r10.getClass()
            int r19 = r10.hashCode()
            switch(r19) {
                case -934795532: goto L_0x0073;
                case 99841: goto L_0x0068;
                case 100571: goto L_0x005d;
                case 93616297: goto L_0x0052;
                case 109780401: goto L_0x0046;
                case 1292595405: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            r7 = -1
            goto L_0x007d
        L_0x003b:
            java.lang.String r7 = "backgroundImage"
            boolean r7 = r10.equals(r7)
            if (r7 != 0) goto L_0x0044
            goto L_0x0039
        L_0x0044:
            r7 = 5
            goto L_0x007d
        L_0x0046:
            java.lang.String r7 = "style"
            boolean r7 = r10.equals(r7)
            if (r7 != 0) goto L_0x0050
            goto L_0x0039
        L_0x0050:
            r7 = 4
            goto L_0x007d
        L_0x0052:
            java.lang.String r7 = "begin"
            boolean r7 = r10.equals(r7)
            if (r7 != 0) goto L_0x005b
            goto L_0x0039
        L_0x005b:
            r7 = 3
            goto L_0x007d
        L_0x005d:
            java.lang.String r7 = "end"
            boolean r7 = r10.equals(r7)
            if (r7 != 0) goto L_0x0066
            goto L_0x0039
        L_0x0066:
            r7 = 2
            goto L_0x007d
        L_0x0068:
            java.lang.String r7 = "dur"
            boolean r7 = r10.equals(r7)
            if (r7 != 0) goto L_0x0071
            goto L_0x0039
        L_0x0071:
            r7 = r2
            goto L_0x007d
        L_0x0073:
            java.lang.String r7 = "region"
            boolean r7 = r10.equals(r7)
            if (r7 != 0) goto L_0x007c
            goto L_0x0039
        L_0x007c:
            r7 = 0
        L_0x007d:
            switch(r7) {
                case 0: goto L_0x00aa;
                case 1: goto L_0x00a5;
                case 2: goto L_0x00a0;
                case 3: goto L_0x009b;
                case 4: goto L_0x0090;
                case 5: goto L_0x0081;
                default: goto L_0x0080;
            }
        L_0x0080:
            goto L_0x008d
        L_0x0081:
            java.lang.String r7 = "#"
            boolean r7 = r6.startsWith(r7)
            if (r7 == 0) goto L_0x008d
            java.lang.String r12 = r6.substring(r2)
        L_0x008d:
            r7 = r22
            goto L_0x00b3
        L_0x0090:
            java.lang.String[] r6 = parseStyleIds(r6)
            int r7 = r6.length
            if (r7 <= 0) goto L_0x008d
            r7 = r22
            r8 = r6
            goto L_0x00b3
        L_0x009b:
            long r13 = parseTimeExpression(r6, r1)
            goto L_0x008d
        L_0x00a0:
            long r15 = parseTimeExpression(r6, r1)
            goto L_0x008d
        L_0x00a5:
            long r17 = parseTimeExpression(r6, r1)
            goto L_0x008d
        L_0x00aa:
            r7 = r22
            boolean r10 = r7.containsKey(r6)
            if (r10 == 0) goto L_0x00b3
            r11 = r6
        L_0x00b3:
            int r4 = r4 + r2
            goto L_0x0025
        L_0x00b6:
            if (r9 == 0) goto L_0x00cf
            long r1 = r9.startTimeUs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00cd
            int r6 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00c8
            long r13 = r13 + r1
        L_0x00c8:
            int r6 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00cd
            long r15 = r15 + r1
        L_0x00cd:
            r1 = r13
            goto L_0x00d5
        L_0x00cf:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x00cd
        L_0x00d5:
            int r6 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r6 != 0) goto L_0x00ec
            int r6 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00e2
            long r17 = r1 + r17
            r3 = r17
            goto L_0x00ed
        L_0x00e2:
            if (r9 == 0) goto L_0x00ec
            long r6 = r9.endTimeUs
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00ec
            r3 = r6
            goto L_0x00ed
        L_0x00ec:
            r3 = r15
        L_0x00ed:
            java.lang.String r0 = r20.getName()
            r6 = r8
            r7 = r11
            r8 = r12
            r9 = r21
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode r0 = com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode.buildNode(r0, r1, r3, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.parseNode(org.xmlpull.v1.XmlPullParser, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode, java.util.Map, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder$FrameAndTickRate):com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0185, code lost:
        if (r0.equals(com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlNode.VERTICAL) == false) goto L_0x0164;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0155  */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlRegion parseRegionAttributes(org.xmlpull.v1.XmlPullParser r18, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.CellResolution r19, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.TtsExtent r20) {
        /*
            r0 = r18
            r1 = r20
            r2 = 0
            r3 = 1
            r4 = 2
            java.lang.String r5 = "id"
            java.lang.String r7 = com.appsamurai.storyly.exoplayer2.common.util.XmlPullParserUtil.getAttributeValue(r0, r5)
            r5 = 0
            if (r7 != 0) goto L_0x0011
            return r5
        L_0x0011:
            java.lang.String r6 = "origin"
            java.lang.String r6 = com.appsamurai.storyly.exoplayer2.common.util.XmlPullParserUtil.getAttributeValue(r0, r6)
            java.lang.String r8 = "TtmlDecoder"
            if (r6 == 0) goto L_0x01cb
            java.util.regex.Pattern r9 = PERCENTAGE_COORDINATES
            java.util.regex.Matcher r10 = r9.matcher(r6)
            java.util.regex.Pattern r11 = PIXEL_COORDINATES
            java.util.regex.Matcher r12 = r11.matcher(r6)
            boolean r13 = r10.matches()
            java.lang.String r14 = "Ignoring region with missing tts:extent: "
            java.lang.String r15 = "Ignoring region with malformed origin: "
            r16 = 1120403456(0x42c80000, float:100.0)
            if (r13 == 0) goto L_0x0061
            java.lang.String r12 = r10.group(r3)     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.Object r12 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r12)     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ NumberFormatException -> 0x0059 }
            float r12 = java.lang.Float.parseFloat(r12)     // Catch:{ NumberFormatException -> 0x0059 }
            float r12 = r12 / r16
            java.lang.String r10 = r10.group(r4)     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.Object r10 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r10)     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ NumberFormatException -> 0x0059 }
            float r10 = java.lang.Float.parseFloat(r10)     // Catch:{ NumberFormatException -> 0x0059 }
            float r10 = r10 / r16
            r17 = r12
            r12 = r10
            r10 = r17
            goto L_0x0097
        L_0x0059:
            java.lang.String r0 = r15.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x0061:
            boolean r10 = r12.matches()
            if (r10 == 0) goto L_0x01c1
            if (r1 != 0) goto L_0x0071
            java.lang.String r0 = r14.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x0071:
            java.lang.String r10 = r12.group(r3)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.Object r10 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r10)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ NumberFormatException -> 0x01b9 }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.String r12 = r12.group(r4)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.Object r12 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r12)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ NumberFormatException -> 0x01b9 }
            int r12 = java.lang.Integer.parseInt(r12)     // Catch:{ NumberFormatException -> 0x01b9 }
            float r10 = (float) r10     // Catch:{ NumberFormatException -> 0x01b9 }
            int r13 = r1.width     // Catch:{ NumberFormatException -> 0x01b9 }
            float r13 = (float) r13     // Catch:{ NumberFormatException -> 0x01b9 }
            float r10 = r10 / r13
            float r12 = (float) r12     // Catch:{ NumberFormatException -> 0x01b9 }
            int r13 = r1.height     // Catch:{ NumberFormatException -> 0x01b9 }
            float r13 = (float) r13
            float r12 = r12 / r13
        L_0x0097:
            java.lang.String r13 = "extent"
            java.lang.String r13 = com.appsamurai.storyly.exoplayer2.common.util.XmlPullParserUtil.getAttributeValue(r0, r13)
            if (r13 == 0) goto L_0x01b3
            java.util.regex.Matcher r9 = r9.matcher(r13)
            java.util.regex.Matcher r11 = r11.matcher(r13)
            boolean r13 = r9.matches()
            java.lang.String r15 = "Ignoring region with malformed extent: "
            if (r13 == 0) goto L_0x00d9
            java.lang.String r1 = r9.group(r3)     // Catch:{ NumberFormatException -> 0x00d1 }
            java.lang.Object r1 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r1)     // Catch:{ NumberFormatException -> 0x00d1 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x00d1 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x00d1 }
            float r1 = r1 / r16
            java.lang.String r9 = r9.group(r4)     // Catch:{ NumberFormatException -> 0x00d1 }
            java.lang.Object r9 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r9)     // Catch:{ NumberFormatException -> 0x00d1 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x00d1 }
            float r5 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x00d1 }
            float r5 = r5 / r16
            r13 = r5
            goto L_0x0111
        L_0x00d1:
            java.lang.String r0 = r15.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x00d9:
            boolean r9 = r11.matches()
            if (r9 == 0) goto L_0x01a9
            if (r1 != 0) goto L_0x00e9
            java.lang.String r0 = r14.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x00e9:
            java.lang.String r9 = r11.group(r3)     // Catch:{ NumberFormatException -> 0x01a1 }
            java.lang.Object r9 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r9)     // Catch:{ NumberFormatException -> 0x01a1 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x01a1 }
            int r9 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x01a1 }
            java.lang.String r11 = r11.group(r4)     // Catch:{ NumberFormatException -> 0x01a1 }
            java.lang.Object r11 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r11)     // Catch:{ NumberFormatException -> 0x01a1 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ NumberFormatException -> 0x01a1 }
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ NumberFormatException -> 0x01a1 }
            float r9 = (float) r9     // Catch:{ NumberFormatException -> 0x01a1 }
            int r13 = r1.width     // Catch:{ NumberFormatException -> 0x01a1 }
            float r13 = (float) r13     // Catch:{ NumberFormatException -> 0x01a1 }
            float r9 = r9 / r13
            float r11 = (float) r11     // Catch:{ NumberFormatException -> 0x01a1 }
            int r1 = r1.height     // Catch:{ NumberFormatException -> 0x01a1 }
            float r1 = (float) r1
            float r11 = r11 / r1
            r1 = r9
            r13 = r11
        L_0x0111:
            java.lang.String r5 = "displayAlign"
            java.lang.String r5 = com.appsamurai.storyly.exoplayer2.common.util.XmlPullParserUtil.getAttributeValue(r0, r5)
            if (r5 == 0) goto L_0x0141
            java.lang.String r5 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r5)
            r5.getClass()
            java.lang.String r6 = "center"
            boolean r6 = r5.equals(r6)
            if (r6 != 0) goto L_0x0137
            java.lang.String r6 = "after"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0131
            goto L_0x0141
        L_0x0131:
            float r12 = r12 + r13
            r5 = r19
            r11 = r4
        L_0x0135:
            r9 = r12
            goto L_0x0145
        L_0x0137:
            r5 = 1073741824(0x40000000, float:2.0)
            float r5 = r13 / r5
            float r5 = r5 + r12
            r11 = r3
            r9 = r5
            r5 = r19
            goto L_0x0145
        L_0x0141:
            r5 = r19
            r11 = r2
            goto L_0x0135
        L_0x0145:
            int r5 = r5.rows
            float r5 = (float) r5
            r6 = 1065353216(0x3f800000, float:1.0)
            float r15 = r6 / r5
            java.lang.String r5 = "writingMode"
            java.lang.String r0 = com.appsamurai.storyly.exoplayer2.common.util.XmlPullParserUtil.getAttributeValue(r0, r5)
            if (r0 == 0) goto L_0x0192
            java.lang.String r0 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r0)
            r0.getClass()
            r5 = -1
            int r6 = r0.hashCode()
            switch(r6) {
                case 3694: goto L_0x017e;
                case 3553396: goto L_0x0172;
                case 3553576: goto L_0x0166;
                default: goto L_0x0164;
            }
        L_0x0164:
            r2 = r5
            goto L_0x0188
        L_0x0166:
            java.lang.String r2 = "tbrl"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0170
            goto L_0x0164
        L_0x0170:
            r2 = r4
            goto L_0x0188
        L_0x0172:
            java.lang.String r2 = "tblr"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x017c
            goto L_0x0164
        L_0x017c:
            r2 = r3
            goto L_0x0188
        L_0x017e:
            java.lang.String r6 = "tb"
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x0188
            goto L_0x0164
        L_0x0188:
            switch(r2) {
                case 0: goto L_0x018f;
                case 1: goto L_0x018f;
                case 2: goto L_0x018c;
                default: goto L_0x018b;
            }
        L_0x018b:
            goto L_0x0192
        L_0x018c:
            r16 = r3
            goto L_0x0195
        L_0x018f:
            r16 = r4
            goto L_0x0195
        L_0x0192:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x018c
        L_0x0195:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlRegion r0 = new com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlRegion
            r2 = 0
            r14 = 1
            r6 = r0
            r8 = r10
            r10 = r2
            r12 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r0
        L_0x01a1:
            java.lang.String r0 = r15.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x01a9:
            java.lang.String r0 = "Ignoring region with unsupported extent: "
            java.lang.String r0 = r0.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x01b3:
            java.lang.String r0 = "Ignoring region without an extent"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x01b9:
            java.lang.String r0 = r15.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x01c1:
            java.lang.String r0 = "Ignoring region with unsupported origin: "
            java.lang.String r0 = r0.concat(r6)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        L_0x01cb:
            java.lang.String r0 = "Ignoring region without an origin"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r8, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.parseRegionAttributes(org.xmlpull.v1.XmlPullParser, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder$CellResolution, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder$TtsExtent):com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlRegion");
    }

    private static float parseShear(String str) {
        Matcher matcher = SIGNED_PERCENTAGE.matcher(str);
        if (!matcher.matches()) {
            h.a("Invalid value for shear: ", str, TAG);
            return Float.MAX_VALUE;
        }
        try {
            return Math.min(100.0f, Math.max(-100.0f, Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1)))));
        } catch (NumberFormatException e3) {
            Log.w(TAG, "Failed to parse shear: " + str, e3);
            return Float.MAX_VALUE;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r13, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14) {
        /*
            r0 = 5
            r1 = 4
            r2 = -1
            r3 = 3
            r4 = 2
            r5 = 1
            int r6 = r13.getAttributeCount()
            r7 = 0
            r8 = r7
        L_0x000c:
            if (r8 >= r6) goto L_0x02d3
            java.lang.String r9 = r13.getAttributeValue(r8)
            java.lang.String r10 = r13.getAttributeName(r8)
            r10.getClass()
            java.lang.String r11 = "TtmlDecoder"
            int r12 = r10.hashCode()
            switch(r12) {
                case -1550943582: goto L_0x00d5;
                case -1224696685: goto L_0x00c9;
                case -1065511464: goto L_0x00bc;
                case -879295043: goto L_0x00af;
                case -734428249: goto L_0x00a3;
                case 3355: goto L_0x0098;
                case 3511770: goto L_0x008d;
                case 94842723: goto L_0x0082;
                case 109403361: goto L_0x0075;
                case 110138194: goto L_0x0067;
                case 365601008: goto L_0x005a;
                case 921125321: goto L_0x004c;
                case 1115953443: goto L_0x003f;
                case 1287124693: goto L_0x0032;
                case 1754920356: goto L_0x0025;
                default: goto L_0x0022;
            }
        L_0x0022:
            r10 = r2
            goto L_0x00e0
        L_0x0025:
            java.lang.String r12 = "multiRowAlign"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x002e
            goto L_0x0022
        L_0x002e:
            r10 = 14
            goto L_0x00e0
        L_0x0032:
            java.lang.String r12 = "backgroundColor"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x003b
            goto L_0x0022
        L_0x003b:
            r10 = 13
            goto L_0x00e0
        L_0x003f:
            java.lang.String r12 = "rubyPosition"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x0048
            goto L_0x0022
        L_0x0048:
            r10 = 12
            goto L_0x00e0
        L_0x004c:
            java.lang.String r12 = "textEmphasis"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x0056
            goto L_0x0022
        L_0x0056:
            r10 = 11
            goto L_0x00e0
        L_0x005a:
            java.lang.String r12 = "fontSize"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x0063
            goto L_0x0022
        L_0x0063:
            r10 = 10
            goto L_0x00e0
        L_0x0067:
            java.lang.String r12 = "textCombine"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x0071
            goto L_0x0022
        L_0x0071:
            r10 = 9
            goto L_0x00e0
        L_0x0075:
            java.lang.String r12 = "shear"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x007e
            goto L_0x0022
        L_0x007e:
            r10 = 8
            goto L_0x00e0
        L_0x0082:
            java.lang.String r12 = "color"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x008b
            goto L_0x0022
        L_0x008b:
            r10 = 7
            goto L_0x00e0
        L_0x008d:
            java.lang.String r12 = "ruby"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x0096
            goto L_0x0022
        L_0x0096:
            r10 = 6
            goto L_0x00e0
        L_0x0098:
            java.lang.String r12 = "id"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x00a1
            goto L_0x0022
        L_0x00a1:
            r10 = r0
            goto L_0x00e0
        L_0x00a3:
            java.lang.String r12 = "fontWeight"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x00ad
            goto L_0x0022
        L_0x00ad:
            r10 = r1
            goto L_0x00e0
        L_0x00af:
            java.lang.String r12 = "textDecoration"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x00ba
            goto L_0x0022
        L_0x00ba:
            r10 = r3
            goto L_0x00e0
        L_0x00bc:
            java.lang.String r12 = "textAlign"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x00c7
            goto L_0x0022
        L_0x00c7:
            r10 = r4
            goto L_0x00e0
        L_0x00c9:
            java.lang.String r12 = "fontFamily"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x00d3
            goto L_0x0022
        L_0x00d3:
            r10 = r5
            goto L_0x00e0
        L_0x00d5:
            java.lang.String r12 = "fontStyle"
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x00df
            goto L_0x0022
        L_0x00df:
            r10 = r7
        L_0x00e0:
            switch(r10) {
                case 0: goto L_0x02c2;
                case 1: goto L_0x02b9;
                case 2: goto L_0x02ac;
                case 3: goto L_0x0248;
                case 4: goto L_0x0238;
                case 5: goto L_0x0221;
                case 6: goto L_0x01a1;
                case 7: goto L_0x018d;
                case 8: goto L_0x017f;
                case 9: goto L_0x0152;
                case 10: goto L_0x0142;
                case 11: goto L_0x0134;
                case 12: goto L_0x0107;
                case 13: goto L_0x00f3;
                case 14: goto L_0x00e5;
                default: goto L_0x00e3;
            }
        L_0x00e3:
            goto L_0x02d0
        L_0x00e5:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            android.text.Layout$Alignment r9 = parseAlignment(r9)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setMultiRowAlign(r9)
            goto L_0x02d0
        L_0x00f3:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            int r10 = com.appsamurai.storyly.exoplayer2.common.util.ColorParser.parseTtmlColor(r9)     // Catch:{ IllegalArgumentException -> 0x0100 }
            r14.setBackgroundColor(r10)     // Catch:{ IllegalArgumentException -> 0x0100 }
            goto L_0x02d0
        L_0x0100:
            java.lang.String r10 = "Failed parsing background value: "
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r10, r9, r11)
            goto L_0x02d0
        L_0x0107:
            java.lang.String r9 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r9)
            r9.getClass()
            java.lang.String r10 = "before"
            boolean r10 = r9.equals(r10)
            if (r10 != 0) goto L_0x012a
            java.lang.String r10 = "after"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0120
            goto L_0x02d0
        L_0x0120:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setRubyPosition(r4)
            goto L_0x02d0
        L_0x012a:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setRubyPosition(r5)
            goto L_0x02d0
        L_0x0134:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TextEmphasis r9 = com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TextEmphasis.parse(r9)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setTextEmphasis(r9)
            goto L_0x02d0
        L_0x0142:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)     // Catch:{ SubtitleDecoderException -> 0x014b }
            parseFontSize(r9, r14)     // Catch:{ SubtitleDecoderException -> 0x014b }
            goto L_0x02d0
        L_0x014b:
            java.lang.String r10 = "Failed parsing fontSize value: "
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r10, r9, r11)
            goto L_0x02d0
        L_0x0152:
            java.lang.String r9 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r9)
            r9.getClass()
            java.lang.String r10 = "all"
            boolean r10 = r9.equals(r10)
            if (r10 != 0) goto L_0x0175
            java.lang.String r10 = "none"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x016b
            goto L_0x02d0
        L_0x016b:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setTextCombine(r7)
            goto L_0x02d0
        L_0x0175:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setTextCombine(r5)
            goto L_0x02d0
        L_0x017f:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            float r9 = parseShear(r9)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setShearPercentage(r9)
            goto L_0x02d0
        L_0x018d:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            int r10 = com.appsamurai.storyly.exoplayer2.common.util.ColorParser.parseTtmlColor(r9)     // Catch:{ IllegalArgumentException -> 0x019a }
            r14.setFontColor(r10)     // Catch:{ IllegalArgumentException -> 0x019a }
            goto L_0x02d0
        L_0x019a:
            java.lang.String r10 = "Failed parsing color value: "
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r10, r9, r11)
            goto L_0x02d0
        L_0x01a1:
            java.lang.String r9 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r9)
            r9.getClass()
            int r10 = r9.hashCode()
            switch(r10) {
                case -618561360: goto L_0x01ea;
                case -410956671: goto L_0x01df;
                case -250518009: goto L_0x01d4;
                case -136074796: goto L_0x01c8;
                case 3016401: goto L_0x01bd;
                case 3556653: goto L_0x01b1;
                default: goto L_0x01af;
            }
        L_0x01af:
            r9 = r2
            goto L_0x01f4
        L_0x01b1:
            java.lang.String r10 = "text"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x01bb
            goto L_0x01af
        L_0x01bb:
            r9 = r0
            goto L_0x01f4
        L_0x01bd:
            java.lang.String r10 = "base"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x01c6
            goto L_0x01af
        L_0x01c6:
            r9 = r1
            goto L_0x01f4
        L_0x01c8:
            java.lang.String r10 = "textContainer"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x01d2
            goto L_0x01af
        L_0x01d2:
            r9 = r3
            goto L_0x01f4
        L_0x01d4:
            java.lang.String r10 = "delimiter"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x01dd
            goto L_0x01af
        L_0x01dd:
            r9 = r4
            goto L_0x01f4
        L_0x01df:
            java.lang.String r10 = "container"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x01e8
            goto L_0x01af
        L_0x01e8:
            r9 = r5
            goto L_0x01f4
        L_0x01ea:
            java.lang.String r10 = "baseContainer"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x01f3
            goto L_0x01af
        L_0x01f3:
            r9 = r7
        L_0x01f4:
            switch(r9) {
                case 0: goto L_0x0217;
                case 1: goto L_0x020d;
                case 2: goto L_0x0203;
                case 3: goto L_0x01f9;
                case 4: goto L_0x0217;
                case 5: goto L_0x01f9;
                default: goto L_0x01f7;
            }
        L_0x01f7:
            goto L_0x02d0
        L_0x01f9:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setRubyType(r3)
            goto L_0x02d0
        L_0x0203:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setRubyType(r1)
            goto L_0x02d0
        L_0x020d:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setRubyType(r5)
            goto L_0x02d0
        L_0x0217:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setRubyType(r4)
            goto L_0x02d0
        L_0x0221:
            java.lang.String r10 = "style"
            java.lang.String r11 = r13.getName()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x02d0
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setId(r9)
            goto L_0x02d0
        L_0x0238:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            java.lang.String r10 = "bold"
            boolean r9 = r10.equalsIgnoreCase(r9)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setBold(r9)
            goto L_0x02d0
        L_0x0248:
            java.lang.String r9 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r9)
            r9.getClass()
            int r10 = r9.hashCode()
            switch(r10) {
                case -1461280213: goto L_0x027a;
                case -1026963764: goto L_0x026e;
                case 913457136: goto L_0x0263;
                case 1679736913: goto L_0x0258;
                default: goto L_0x0256;
            }
        L_0x0256:
            r9 = r2
            goto L_0x0284
        L_0x0258:
            java.lang.String r10 = "linethrough"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0261
            goto L_0x0256
        L_0x0261:
            r9 = r3
            goto L_0x0284
        L_0x0263:
            java.lang.String r10 = "nolinethrough"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x026c
            goto L_0x0256
        L_0x026c:
            r9 = r4
            goto L_0x0284
        L_0x026e:
            java.lang.String r10 = "underline"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0278
            goto L_0x0256
        L_0x0278:
            r9 = r5
            goto L_0x0284
        L_0x027a:
            java.lang.String r10 = "nounderline"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0283
            goto L_0x0256
        L_0x0283:
            r9 = r7
        L_0x0284:
            switch(r9) {
                case 0: goto L_0x02a3;
                case 1: goto L_0x029a;
                case 2: goto L_0x0291;
                case 3: goto L_0x0288;
                default: goto L_0x0287;
            }
        L_0x0287:
            goto L_0x02d0
        L_0x0288:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setLinethrough(r5)
            goto L_0x02d0
        L_0x0291:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setLinethrough(r7)
            goto L_0x02d0
        L_0x029a:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setUnderline(r5)
            goto L_0x02d0
        L_0x02a3:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setUnderline(r7)
            goto L_0x02d0
        L_0x02ac:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            android.text.Layout$Alignment r9 = parseAlignment(r9)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setTextAlign(r9)
            goto L_0x02d0
        L_0x02b9:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setFontFamily(r9)
            goto L_0x02d0
        L_0x02c2:
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = createIfNull(r14)
            java.lang.String r10 = "italic"
            boolean r9 = r10.equalsIgnoreCase(r9)
            com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle r14 = r14.setItalic(r9)
        L_0x02d0:
            int r8 = r8 + r5
            goto L_0x000c
        L_0x02d3:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle):com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlStyle");
    }

    private static String[] parseStyleIds(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : Util.split(trim, "\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00bb, code lost:
        if (r13.equals("ms") == false) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f3, code lost:
        r8 = r8 / r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fb, code lost:
        r8 = r8 * r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long parseTimeExpression(java.lang.String r13, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.FrameAndTickRate r14) throws com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException {
        /*
            r0 = 4
            r1 = 3
            java.util.regex.Pattern r2 = CLOCK_TIME
            java.util.regex.Matcher r2 = r2.matcher(r13)
            boolean r3 = r2.matches()
            r4 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r6 = 2
            r7 = 1
            if (r3 == 0) goto L_0x0084
            java.lang.String r13 = r2.group(r7)
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r7 = java.lang.Long.parseLong(r13)
            r9 = 3600(0xe10, double:1.7786E-320)
            long r7 = r7 * r9
            double r7 = (double) r7
            java.lang.String r13 = r2.group(r6)
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r9 = java.lang.Long.parseLong(r13)
            r11 = 60
            long r9 = r9 * r11
            double r9 = (double) r9
            double r7 = r7 + r9
            java.lang.String r13 = r2.group(r1)
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r9 = java.lang.Long.parseLong(r13)
            double r9 = (double) r9
            double r7 = r7 + r9
            java.lang.String r13 = r2.group(r0)
            r0 = 0
            if (r13 == 0) goto L_0x0057
            double r9 = java.lang.Double.parseDouble(r13)
            goto L_0x0058
        L_0x0057:
            r9 = r0
        L_0x0058:
            double r7 = r7 + r9
            r13 = 5
            java.lang.String r13 = r2.group(r13)
            if (r13 == 0) goto L_0x006a
            long r9 = java.lang.Long.parseLong(r13)
            float r13 = (float) r9
            float r3 = r14.effectiveFrameRate
            float r13 = r13 / r3
            double r9 = (double) r13
            goto L_0x006b
        L_0x006a:
            r9 = r0
        L_0x006b:
            double r7 = r7 + r9
            r13 = 6
            java.lang.String r13 = r2.group(r13)
            if (r13 == 0) goto L_0x0080
            long r0 = java.lang.Long.parseLong(r13)
            double r0 = (double) r0
            int r13 = r14.subFrameRate
            double r2 = (double) r13
            double r0 = r0 / r2
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            double r0 = r0 / r13
        L_0x0080:
            double r7 = r7 + r0
            double r7 = r7 * r4
            long r13 = (long) r7
            return r13
        L_0x0084:
            java.util.regex.Pattern r2 = OFFSET_TIME
            java.util.regex.Matcher r2 = r2.matcher(r13)
            boolean r3 = r2.matches()
            if (r3 == 0) goto L_0x010a
            java.lang.String r13 = r2.group(r7)
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            double r8 = java.lang.Double.parseDouble(r13)
            java.lang.String r13 = r2.group(r6)
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            r13.getClass()
            r2 = -1
            int r3 = r13.hashCode()
            switch(r3) {
                case 102: goto L_0x00e0;
                case 104: goto L_0x00d5;
                case 109: goto L_0x00ca;
                case 116: goto L_0x00be;
                case 3494: goto L_0x00b5;
                default: goto L_0x00b3;
            }
        L_0x00b3:
            r0 = r2
            goto L_0x00ea
        L_0x00b5:
            java.lang.String r1 = "ms"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00ea
            goto L_0x00b3
        L_0x00be:
            java.lang.String r0 = "t"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00c8
            goto L_0x00b3
        L_0x00c8:
            r0 = r1
            goto L_0x00ea
        L_0x00ca:
            java.lang.String r0 = "m"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00d3
            goto L_0x00b3
        L_0x00d3:
            r0 = r6
            goto L_0x00ea
        L_0x00d5:
            java.lang.String r0 = "h"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00de
            goto L_0x00b3
        L_0x00de:
            r0 = r7
            goto L_0x00ea
        L_0x00e0:
            java.lang.String r0 = "f"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00e9
            goto L_0x00b3
        L_0x00e9:
            r0 = 0
        L_0x00ea:
            switch(r0) {
                case 0: goto L_0x0103;
                case 1: goto L_0x00fd;
                case 2: goto L_0x00f9;
                case 3: goto L_0x00f5;
                case 4: goto L_0x00ee;
                default: goto L_0x00ed;
            }
        L_0x00ed:
            goto L_0x0107
        L_0x00ee:
            r13 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x00f3:
            double r8 = r8 / r13
            goto L_0x0107
        L_0x00f5:
            int r13 = r14.tickRate
            double r13 = (double) r13
            goto L_0x00f3
        L_0x00f9:
            r13 = 4633641066610819072(0x404e000000000000, double:60.0)
        L_0x00fb:
            double r8 = r8 * r13
            goto L_0x0107
        L_0x00fd:
            r13 = 4660134898793709568(0x40ac200000000000, double:3600.0)
            goto L_0x00fb
        L_0x0103:
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            goto L_0x00f3
        L_0x0107:
            double r8 = r8 * r4
            long r13 = (long) r8
            return r13
        L_0x010a:
            com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException r14 = new com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException
            java.lang.String r0 = "Malformed time expression: "
            java.lang.String r13 = androidx.browser.trusted.c.a(r0, r13)
            r14.<init>((java.lang.String) r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder.parseTimeExpression(java.lang.String, com.appsamurai.storyly.exoplayer2.extractor.text.ttml.TtmlDecoder$FrameAndTickRate):long");
    }

    @Nullable
    private static TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring non-pixel tts extent: ".concat(attributeValue));
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1))), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2))));
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed tts extent: ".concat(attributeValue));
            return null;
        }
    }

    public Subtitle decode(byte[] bArr, int i3, boolean z2) throws SubtitleDecoderException {
        FrameAndTickRate frameAndTickRate;
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            hashMap2.put("", new TtmlRegion(""));
            TtsExtent ttsExtent = null;
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i3), (String) null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRate2 = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution = DEFAULT_CELL_RESOLUTION;
            int i4 = 0;
            TtmlSubtitle ttmlSubtitle = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i4 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRate2 = parseFrameAndTickRates(newPullParser);
                            cellResolution = parseCellResolution(newPullParser, DEFAULT_CELL_RESOLUTION);
                            ttsExtent = parseTtsExtent(newPullParser);
                        }
                        TtsExtent ttsExtent2 = ttsExtent;
                        FrameAndTickRate frameAndTickRate3 = frameAndTickRate2;
                        CellResolution cellResolution2 = cellResolution;
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + newPullParser.getName());
                            i4++;
                            frameAndTickRate2 = frameAndTickRate3;
                        } else {
                            if (TtmlNode.TAG_HEAD.equals(name)) {
                                frameAndTickRate = frameAndTickRate3;
                                parseHeader(newPullParser, hashMap, cellResolution2, ttsExtent2, hashMap2, hashMap3);
                            } else {
                                frameAndTickRate = frameAndTickRate3;
                                try {
                                    TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate);
                                    arrayDeque.push(parseNode);
                                    if (ttmlNode != null) {
                                        ttmlNode.addChild(parseNode);
                                    }
                                } catch (SubtitleDecoderException e3) {
                                    Log.w(TAG, "Suppressing parser error", e3);
                                    i4++;
                                }
                            }
                            frameAndTickRate2 = frameAndTickRate;
                        }
                        ttsExtent = ttsExtent2;
                        cellResolution = cellResolution2;
                    } else if (eventType == 4) {
                        ((TtmlNode) Assertions.checkNotNull(ttmlNode)).addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                    } else if (eventType == 3) {
                        if (newPullParser.getName().equals(TtmlNode.TAG_TT)) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) Assertions.checkNotNull((TtmlNode) arrayDeque.peek()), hashMap, hashMap2, hashMap3);
                        }
                        arrayDeque.pop();
                    }
                } else if (eventType == 2) {
                    i4++;
                } else if (eventType == 3) {
                    i4--;
                }
                newPullParser.next();
            }
            if (ttmlSubtitle != null) {
                return ttmlSubtitle;
            }
            throw new SubtitleDecoderException("No TTML subtitles found");
        } catch (XmlPullParserException e4) {
            throw new SubtitleDecoderException("Unable to decode source", e4);
        } catch (IOException e5) {
            throw new IllegalStateException("Unexpected error when reading input.", e5);
        }
    }
}
