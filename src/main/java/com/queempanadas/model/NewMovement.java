package com.queempanadas.model;

import java.util.HashMap;
import java.util.Map;

public abstract class NewMovement {
    private Map<Long, Integer> empanadaQty = new HashMap<>();
    public Map<Long, Integer> getEmpanadaQty() {
        return empanadaQty;
    }
}
