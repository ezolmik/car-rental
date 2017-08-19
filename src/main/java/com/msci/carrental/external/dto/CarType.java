package com.msci.carrental.external.dto;

public enum CarType {
    OpelAstra {
        @Override
        public boolean isForeignType() {
            return true;
        }
    },
    VolvoXC60 {
        @Override
        public boolean isForeignType() {
            return false;
        }
    };

    public abstract boolean isForeignType();

    public static CarType random() {
        return Math.random() < 0.5 ? OpelAstra : VolvoXC60;
    }
}
