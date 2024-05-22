package org.cr.enums;

public enum CarRentSts {

    AVAILABLE("1"), NON_AVAILABLE("0");

    private final String sts;

    CarRentSts(final String sts) {
        this.sts = sts;
    }

    @Override
    public String toString() {
        return sts;
    }

}
