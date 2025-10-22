package com.gameple.core.helper;

public class IpAddressHolder {

    private static final ThreadLocal<String> ipHolder = new ThreadLocal<>();

    public static void set(String ip) { ipHolder.set(ip); }

    public static String get() { return ipHolder.get(); }

    public static void clear() { ipHolder.remove(); }
}
