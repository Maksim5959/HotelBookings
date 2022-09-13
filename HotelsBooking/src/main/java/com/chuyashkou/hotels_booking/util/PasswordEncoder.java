package com.chuyashkou.hotels_booking.util;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.net.BCodec;

public final class PasswordEncoder {

    private static final StringEncoder ENCODER = new BCodec();

    /**
     * Encode password
     *
     * @param password password value before encoding
     * @return encoded password value
     */
    public static String encodePassword(String password) {
        String encodedPassword = password;
        try {
            encodedPassword = ENCODER.encode(password);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return encodedPassword;
    }
}
