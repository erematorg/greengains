package com.appsamurai.storyly.exoplayer2.common.text.span;

public final class RubySpan implements LanguageFeatureSpan {
    public final int position;
    public final String rubyText;

    public RubySpan(String str, int i3) {
        this.rubyText = str;
        this.position = i3;
    }
}
