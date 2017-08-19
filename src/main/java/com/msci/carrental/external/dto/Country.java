package com.msci.carrental.external.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Country {
    CZE, AUT, DEU, CHE, MNE, SRB;

    public static final Country[] EUCOUNTRIES = {CZE, AUT, DEU};

    public boolean isEuMember() {
        return Arrays.stream(EUCOUNTRIES).map(Country::toString).anyMatch(this::equals);
    }

    public static List<Country> randomList() {
        if (Math.random() < 0.5) {
            return Arrays.stream(EUCOUNTRIES)
                    .collect(Collectors.toList());
        }
        return Arrays.stream(values())
                .filter(c -> Math.random() < 0.5)
                .collect(Collectors.toList());
    }
}
