
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
        
  /**
        // Check RestaurantService.java file for the interface contract.

   * Get all the restaurants that are open now within a specific service radius.
   * - For peak hours: 8AM - 10AM, 1PM-2PM, 7PM-9PM
   * - service radius is 3KMs.
   * - All other times, serving radius is 5KMs.
   * - If there are no restaurants, return empty list of restaurants.
   * @param getRestaurantsRequest valid lat/long
   * @param currentTime current time.
   * @return GetRestaurantsResponse object containing a list of open restaurants or an
   *     empty list if none fits the criteria.
   * 
   */   List<Restaurant> listOfRestaurants=new ArrayList<>();
        double latitude=getRestaurantsRequest.getLatitude();
        double longitude=getRestaurantsRequest.getLongitude();
        LocalTime time = currentTime;
        Double servingRadiusInKms;
        int h = currentTime.getHour();
        int m = currentTime.getMinute();
        if ((h >= 8 && h < 10) ||           // Between 8:00 AM and 9:59 AM
            (h == 10 && m == 0) ||           // 10:00 AM exactly
            (h >= 13 && h < 14) ||           // Between 1:00 PM and 1:59 PM
            (h == 14 && m == 0) ||           // 2:00 PM exactly
            (h >= 19 && h < 21) ||           // Between 7:00 PM and 8:59 PM
            (h == 21 && m == 0)) {           // 9:00 PM exactly
            servingRadiusInKms = peakHoursServingRadiusInKms;        // Peak hours serving radius is 3.0 KMs
        } else {
            servingRadiusInKms = normalHoursServingRadiusInKms;        // Normal hours serving radius is 5.0 KMs
        }
              listOfRestaurants.addAll(restaurantRepositoryService.findAllRestaurantsCloseBy(latitude, longitude, currentTime, servingRadiusInKms));
       if (listOfRestaurants.isEmpty()) {
        return new GetRestaurantsResponse(Collections.emptyList());
    } 
       GetRestaurantsResponse getRestaurantsResponse = new GetRestaurantsResponse(listOfRestaurants);

       log.info(getRestaurantsResponse);
       return getRestaurantsResponse;
  }



}

