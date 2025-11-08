package com.sun.jna;

public interface SymbolProvider {
    long getSymbolAddress(long j2, String str, SymbolProvider symbolProvider);
}
