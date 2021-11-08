package com.zsy.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @Title com.zsy.json
 * @date 2021/7/21
 * @autor Zsy
 */

public class SpecificClassExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        System.out.println("======== shouldSkipField ========"+fieldAttributes.getName());
        if (fieldAttributes.getName().equals("BEATS_POSITION")){
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        System.out.println("======== shouldSkipClass ======== "+aClass.getName());
        return false;
    }
}
