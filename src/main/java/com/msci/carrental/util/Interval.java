package com.msci.carrental.util;

import java.time.Instant;

public class Interval {

    private Instant pickUp;

    private Instant dropOff;

    public Interval(Instant pickUp, Instant dropOff) {
        this.pickUp = pickUp;
        this.dropOff = dropOff;
    }

    public Instant getPickUp() {
        return pickUp;
    }

    public void setPickUp(Instant pickUp) {
        this.pickUp = pickUp;
    }

    public Instant getDropOff() {
        return dropOff;
    }

    public void setDropOff(Instant dropOff) {
        this.dropOff = dropOff;
    }

}
