package com.msci.carrental.util;

import java.time.Instant;
import java.util.List;

public class Intervals {

    public static int findPosition(List<Interval> sortedAndDisjointInts, Instant pickUp, Instant dropOff) {
        if (sortedAndDisjointInts != null) {
            if (sortedAndDisjointInts.isEmpty() || dropOff.isBefore(sortedAndDisjointInts.get(0).getPickUp())) {
                return 0;
            }
            if (pickUp.isAfter(sortedAndDisjointInts.get(sortedAndDisjointInts.size() - 1).getDropOff())) {
                return sortedAndDisjointInts.size();
            }
            for (int i = 1; i < sortedAndDisjointInts.size(); i++) {
                if (pickUp.isAfter(sortedAndDisjointInts.get(i - 1).getDropOff())
                        && dropOff.isBefore(sortedAndDisjointInts.get(i).getPickUp())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
