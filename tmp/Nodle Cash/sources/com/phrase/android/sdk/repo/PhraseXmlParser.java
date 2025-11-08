package com.phrase.android.sdk.repo;

import androidx.browser.trusted.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.phrase.android.sdk.repo.PhraseData;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseXmlParser;", "", "", "xml", "Lcom/phrase/android/sdk/repo/PhraseData;", "parse", "Ljava/io/InputStream;", "inputStream", "<init>", "()V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseXmlParser {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String[] f7297a = {"zero", "one", "two", "few", "many", "other"};

    public final void a(XmlPullParser xmlPullParser, String str, PhraseData.Builder builder) {
        xmlPullParser.require(2, (String) null, "plurals");
        while (xmlPullParser.nextTag() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                xmlPullParser.require(2, (String) null, "item");
                String attributeValue = xmlPullParser.getAttributeValue((String) null, FirebaseAnalytics.Param.QUANTITY);
                if (ArraysKt.contains((T[]) this.f7297a, attributeValue)) {
                    String a2 = a(xmlPullParser);
                    Intrinsics.checkNotNullExpressionValue(attributeValue, FirebaseAnalytics.Param.QUANTITY);
                    builder.putPluralItem(str, attributeValue, a2);
                    xmlPullParser.require(3, (String) null, "item");
                } else {
                    throw new XmlPullParserException(c.a("Unknown quantity qualifier: ", attributeValue));
                }
            }
        }
        xmlPullParser.require(3, (String) null, "plurals");
    }

    @NotNull
    public final PhraseData parse(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "xml");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return parse((InputStream) new ByteArrayInputStream(bytes));
    }

    @NotNull
    public final PhraseData parse(@NotNull InputStream inputStream) {
        Throwable th;
        InputStream inputStream2 = inputStream;
        Intrinsics.checkNotNullParameter(inputStream2, "inputStream");
        PhraseData.Builder builder = new PhraseData.Builder();
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(inputStream2, (String) null);
            newPullParser.next();
            if (newPullParser.getEventType() != 1) {
                newPullParser.require(2, (String) null, "resources");
                while (newPullParser.nextTag() != 3) {
                    if (newPullParser.getEventType() == 2) {
                        String attributeValue = newPullParser.getAttributeValue((String) null, "name");
                        if (attributeValue == null || attributeValue.length() == 0) {
                            throw new XmlPullParserException("Missing name attribute in <" + newPullParser.getName() + "> declaration");
                        }
                        String name = newPullParser.getName();
                        if (name != null) {
                            int hashCode = name.hashCode();
                            if (hashCode == -1024600675) {
                                if (name.equals("string-array")) {
                                    Intrinsics.checkNotNullExpressionValue(newPullParser, "this");
                                    Intrinsics.checkNotNullExpressionValue(attributeValue, "attrName");
                                    newPullParser.require(2, (String) null, "string-array");
                                    while (newPullParser.nextTag() != 3) {
                                        if (newPullParser.getEventType() == 2) {
                                            newPullParser.require(2, (String) null, "item");
                                            builder.putArrayItem(attributeValue, a(newPullParser));
                                            newPullParser.require(3, (String) null, "item");
                                        }
                                    }
                                    newPullParser.require(3, (String) null, "string-array");
                                }
                            } else if (hashCode == -891985903) {
                                if (name.equals("string")) {
                                    Intrinsics.checkNotNullExpressionValue(newPullParser, "this");
                                    Intrinsics.checkNotNullExpressionValue(attributeValue, "attrName");
                                    newPullParser.require(2, (String) null, "string");
                                    builder.putString(attributeValue, a(newPullParser));
                                    newPullParser.require(3, (String) null, "string");
                                }
                            } else if (hashCode == -475309713) {
                                if (name.equals("plurals")) {
                                    Intrinsics.checkNotNullExpressionValue(newPullParser, "this");
                                    Intrinsics.checkNotNullExpressionValue(attributeValue, "attrName");
                                    a(newPullParser, attributeValue, builder);
                                }
                            }
                        }
                        throw new XmlPullParserException("Unknown tag: " + newPullParser.getName());
                    }
                    throw new XmlPullParserException("Unexpected tag: <" + newPullParser.getName() + Typography.greater);
                }
                newPullParser.require(3, (String) null, "resources");
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(inputStream2, (Throwable) null);
            return builder.build();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            CloseableKt.closeFinally(inputStream2, th);
            throw th3;
        }
    }

    public static String a(XmlPullParser xmlPullParser) {
        StringBuilder sb = new StringBuilder();
        int i3 = 1;
        while (i3 != 0) {
            int nextToken = xmlPullParser.nextToken();
            if (nextToken == 2) {
                i3++;
                sb.append("<");
                sb.append(xmlPullParser.getName());
                sb.append(">");
            } else if (nextToken == 3) {
                i3--;
                if (i3 > 0) {
                    sb.append("</");
                    sb.append(xmlPullParser.getName());
                    sb.append(">");
                }
            } else if (nextToken == 4) {
                String text = xmlPullParser.getText();
                Intrinsics.checkNotNullExpressionValue(text, "parser.text");
                if (StringsKt__StringsKt.contains$default((CharSequence) text, '\"', false, 2, (Object) null)) {
                    StringBuilder sb2 = new StringBuilder();
                    int length = text.length();
                    for (int i4 = 0; i4 < length; i4++) {
                        if (text.charAt(i4) != '\"' || (i4 != 0 && text.charAt(i4 - 1) == '\\')) {
                            sb2.append(text.charAt(i4));
                        }
                    }
                    text = sb2.toString();
                    Intrinsics.checkNotNullExpressionValue(text, "sb.toString()");
                }
                if (StringsKt__StringsKt.contains$default((CharSequence) text, (char) AbstractJsonLexerKt.STRING_ESC, false, 2, (Object) null)) {
                    text = StringEscapeUtils.unescapeJava(text);
                    Intrinsics.checkNotNullExpressionValue(text, "unescapeJava(s)");
                }
                sb.append(StringsKt__StringsJVMKt.replace$default(text, "\n", "<br/>", false, 4, (Object) null));
            } else if (nextToken == 5) {
                sb.append("<![CDATA[");
                sb.append(xmlPullParser.getText());
                sb.append("]]>");
            } else if (nextToken == 6) {
                sb.append(xmlPullParser.getText());
            }
        }
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "sb.toString()");
        return sb3;
    }
}
