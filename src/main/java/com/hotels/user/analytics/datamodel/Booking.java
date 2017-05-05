package com.hotels.user.analytics.datamodel;


import java.math.BigDecimal;

public class Booking {

    private final String userId;
    private final String hotelId;
    private final Integer numberOfNights;
    private final BigDecimal totalPriceInUSD;

    public Booking(String userId, String hotelId, Integer numberOfNights, BigDecimal totalPriceInUSD) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.numberOfNights = numberOfNights;
        this.totalPriceInUSD = totalPriceInUSD;
    }

    public String getUserId() {
        return userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public BigDecimal getTotalPriceInUSD() {
        return totalPriceInUSD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (userId != null ? !userId.equals(booking.userId) : booking.userId != null) return false;
        if (hotelId != null ? !hotelId.equals(booking.hotelId) : booking.hotelId != null) return false;
        if (numberOfNights != null ? !numberOfNights.equals(booking.numberOfNights) : booking.numberOfNights != null)
            return false;
        return totalPriceInUSD != null ? totalPriceInUSD.equals(booking.totalPriceInUSD) : booking.totalPriceInUSD == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (hotelId != null ? hotelId.hashCode() : 0);
        result = 31 * result + (numberOfNights != null ? numberOfNights.hashCode() : 0);
        result = 31 * result + (totalPriceInUSD != null ? totalPriceInUSD.hashCode() : 0);
        return result;
    }
}
