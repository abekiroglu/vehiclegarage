package com.abekiroglu.vehicle.api.model;

import java.util.HashMap;
import java.util.Map;

public enum VehicleType {
    CABRIO(1, "car", "cabrio", 0),
    SEDAN(1, "car", "sedan", 1),
    HATCHBACK(1, "car", "hatchback", 2),
    JEEP(2, "jeep", "jeep", 3),
    TRUCK(4, "truck", "truck", 4);

    public final int size;
    public final String type;
    public final String mask;
    public final int index;
    private static final Map<String, VehicleType> _map = new HashMap<String, VehicleType>();
    static
    {
        for (VehicleType type : VehicleType.values())
            _map.put(type.mask, type);
    }
    private static final Map<Integer, VehicleType> __map = new HashMap<Integer, VehicleType>();
    static
    {
        for (VehicleType type : VehicleType.values())
            __map.put(type.index, type);
    }
    VehicleType(int size, String type, String mask, int index){
        this.size = size;
        this.type = type;
        this.mask = mask;
        this.index = index;
    }
    public static VehicleType from(int index){
        return __map.get(index);
    }
    public static VehicleType from(String type){
        return _map.get(type);
    }

    public static String stringifyEnum(){
        return _map.values().toString();
    }
}
