package com.abekiroglu.vehicle.api.model;

import java.util.HashMap;
import java.util.Map;

public enum VehicleColor {
    RED(0, "red"),
    GREEN(1, "green"),
    BLUE(2, "blue"),
    BLACK(3, "black"),
    GRAY(4, "gray"),
    WHITE(5, "white"),
    ;
    public final Integer index;
    public final String mask;
    VehicleColor(Integer index, String mask) {
        this.index = index;
        this.mask = mask;
    }

    private static final Map<Integer, VehicleColor> _map = new HashMap<Integer, VehicleColor>();
    static
    {
        for (VehicleColor color : VehicleColor.values())
            _map.put(color.index, color);
    }
    private static final Map<String, VehicleColor> __map = new HashMap<String, VehicleColor>();
    static
    {
        for (VehicleColor color : VehicleColor.values())
            __map.put(color.mask, color);
    }
    public static VehicleColor from(Integer index){
        return _map.get(index);
    }
    public static VehicleColor from(String mask){
        return __map.get(mask);
    }

    public static String stringifyEnum() {
        return _map.values().toString();
    }
}
