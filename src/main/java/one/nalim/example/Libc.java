package one.nalim.example;

import one.nalim.Link;
import one.nalim.Linker;

public class Libc {

    @Link
    public static native int getuid();

    @Link
    public static native int getgid();

    static {
        Linker.linkClass(Libc.class);
    }

    public static void main(String[] args) {
        System.out.println(Libc.getgid());
        System.out.println(Libc.getuid());
    }
}
