
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @JsonProperty("restaurantId")
    private String restaurantId;

    private String name;

    private String city;

    private String imageUrl;

    private double latitude;

    private double longitude;

    private String opensAt;

    private String closesAt;

    private List<String> attributes;

}

