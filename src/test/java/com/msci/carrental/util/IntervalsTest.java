package com.msci.carrental.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IntervalsTest {

    @Test
    public void testFindPositionReturnsNegativeNumberWhenIntervalsIsNull() {
        //When
        int actual = Intervals.findPosition(null, Instant.now(), Instant.now());
        //Then
        assertThat(actual).isLessThan(0);
    }

    @Test
    public void testFindPositionReturnsZeroWhenIntervalsIsEmpty() {
        //Given
        List<Interval> sortedAndDisjointInts = new ArrayList<>();
        //When
        int actual = Intervals.findPosition(sortedAndDisjointInts, Instant.now(), Instant.now());
        //Then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void testFindPositionReturnsZeroWhenIntervalsStartsLater() {
        //Given
        List<Interval> sortedAndDisjointInts = new ArrayList<>();
        Instant now = Instant.now();
        Interval i1 = new Interval(now.plus(Duration.ofDays(4)), now.plus(Duration.ofDays(5)));
        sortedAndDisjointInts.add(i1);
        //When
        int actual = Intervals.findPosition(sortedAndDisjointInts, now.plus(Duration.ofDays(2)), now.plus(Duration.ofDays(3)));
        //Then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void testFindPositionReturnsOneWhenIntervalsEndsEarlier() {
        //Given
        List<Interval> sortedAndDisjointInts = new ArrayList<>();
        Instant now = Instant.now();
        Interval i1 = new Interval(now.plus(Duration.ofDays(1)), now.plus(Duration.ofDays(2)));
        sortedAndDisjointInts.add(i1);
        //When
        int actual = Intervals.findPosition(sortedAndDisjointInts, now.plus(Duration.ofDays(3)), now.plus(Duration.ofDays(4)));
        //Then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void testFindPositionReturnsNegativeNumberWhenIntervalsDoesNotIntersectInput() {
        //Given
        List<Interval> sortedAndDisjointInts = new ArrayList<>();
        Instant now = Instant.now();
        Interval i1 = new Interval(now, now.plus(Duration.ofDays(1)));
        Interval i2 = new Interval(now.plus(Duration.ofDays(4)), now.plus(Duration.ofDays(5)));
        sortedAndDisjointInts.add(i1);
        sortedAndDisjointInts.add(i2);
        //When
        int actual = Intervals.findPosition(sortedAndDisjointInts, now.plus(Duration.ofDays(2)), now.plus(Duration.ofDays(3)));
        //Then
        assertThat(actual).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void testFindPositionReturnsNegativeNumberWhenIntervalsIntersectsInput() {
        //Given
        List<Interval> sortedAndDisjointInts = new ArrayList<>();
        Instant now = Instant.now();
        Interval i1 = new Interval(now, now.plus(Duration.ofDays(1)));
        Interval i2 = new Interval(now.plus(Duration.ofDays(2)), now.plus(Duration.ofDays(4)));
        sortedAndDisjointInts.add(i1);
        sortedAndDisjointInts.add(i2);
        //When
        int actual = Intervals.findPosition(sortedAndDisjointInts, now.plus(Duration.ofDays(3)), now.plus(Duration.ofDays(4)));
        //Then
        assertThat(actual).isLessThan(0);
    }
}
