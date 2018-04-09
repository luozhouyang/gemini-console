package me.stupidme.stupidhttp;

/**
 * Created by allen on 18-4-9.
 */
public interface EncodingForm {

    default String encoding() {
        return "application/x-www-form-urlencoded";
    }
}
