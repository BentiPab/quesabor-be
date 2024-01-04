package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public abstract class NewMovement {
    @JsonProperty("productQty")
    private Map<Long, Integer> productQty = new HashMap<>();
    public Map<Long, Integer> getEmpanadaQty() {
        return productQty;
    }
}
