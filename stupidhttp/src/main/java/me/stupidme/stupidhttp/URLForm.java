package me.stupidme.stupidhttp;

import java.util.Map;

/**
 * Created by allen on 18-3-27.
 */

public interface URLForm extends EncodingForm{

    Map<String,String> pairs();
}
