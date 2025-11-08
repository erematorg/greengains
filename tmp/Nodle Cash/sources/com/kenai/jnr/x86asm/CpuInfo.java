package com.kenai.jnr.x86asm;

@Deprecated
public class CpuInfo {
    public static final CpuInfo GENERIC = new CpuInfo(Vendor.GENERIC, 0);
    final int family;
    final Vendor vendor;

    public enum Vendor {
        INTEL,
        AMD,
        GENERIC
    }

    public CpuInfo(Vendor vendor2, int i3) {
        this.vendor = vendor2;
        this.family = i3;
    }
}
