package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Route;
import com.example.BusManagementSystem.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(long route_id) {
        Optional<Route> routeOptional = routeRepository.findById(route_id);
        return routeOptional.get();
    }

    @Override
    public Route updateRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void deleteRouteById(long route_id) {
        routeRepository.deleteById(route_id);
    }
}
