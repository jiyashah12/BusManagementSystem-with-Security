package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Route;
import java.util.List;

public interface RouteService {

    public void addRoute(Route route);

    public List<Route> getAllRoutes();

    public Route getRouteById(long route_id);

    public Route updateRoute(Route route);

    public void deleteRouteById(long route_id);

}
