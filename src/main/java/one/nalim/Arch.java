package one.nalim;

import java.util.Locale;

public enum Arch {

    UNSPECIFIED,
    AMD64,
    AARCH64,
    RSICV64,
    UNKNOWN;

    static final Arch CURRENT;

    static {
        String arch = System.getProperty("os.arch").toLowerCase(Locale.US);

        if(!arch.contains("64")) {
            CURRENT = UNKNOWN;
        }

        else if (arch.contains("x86") || arch.contains("amd64")) {
            CURRENT = AMD64;
        } else if (arch.contains("aarch") || arch.contains("arm")) {
            CURRENT = AARCH64;
        } else if (arch.contains("riscv")) {
            CURRENT = RSICV64;
        } else {
            CURRENT = UNKNOWN;
        }
    }
}
