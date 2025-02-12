package com.example.BusManagementSystem.controllers;

import com.example.BusManagementSystem.entities.Route;
import com.example.BusManagementSystem.services.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/addroute")
    public ResponseEntity<String> addRoute(@Valid @RequestBody Route route) {
            routeService.addRoute(route);
            return ResponseEntity.ok("Route added successfully.");
    }

    @GetMapping("/getallroutes")
    public List<Route> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return routes;
    }

    @GetMapping("/getroutebyid/{route_id}")
    public ResponseEntity<Route> getRouteById(@PathVariable long route_id) {
        Route route = routeService.getRouteById(route_id);
        return ResponseEntity.ok(route);
    }

    @PutMapping("/updateroute/{route_id}")
    public ResponseEntity<String> updateRoute(@PathVariable long route_id, @RequestBody Route route) {
        try {
            Route existingRoute = routeService.getRouteById(route_id);

            if (existingRoute != null) {
                route.setId(existingRoute.getId());
                routeService.updateRoute(route);
                return ResponseEntity.ok("Route updated successfully.");
            }

            return ResponseEntity.status(404).body("Route not found.");
        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Route not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error updating route.");
        }
    }

    @DeleteMapping("/deleteroutebyid/{route_id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable long route_id) {
        try {
            Route route = routeService.getRouteById(route_id);
            if (route != null) {
                routeService.deleteRouteById(route_id);
                return ResponseEntity.ok("Route deleted successfully.");
            }
            return ResponseEntity.status(404).body("Route not found.");
        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Route not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error deleting route.");
        }
    }

}
