package com.example.inventory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.inventory.model.Shipment;
import com.example.inventory.service.ShipmentService;

/**
 * Handles Shipment end points
 * @author aadesh
 *
 */
@RestController
@RequestMapping("/shipment")
public class ShipmentRestController {

    public ShipmentRestController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
    ShipmentService shipmentService;
    
    /**
     * Retrieves All Shipments
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAllShipment() {
        List<Shipment> response = shipmentService.getShipments();
        if(response.size() == 0)
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Retrieves the given Shipment 
     * @param id - Id of the shipment to be retrieved
     * @return
     */
    @GetMapping("/{id}")
    public Shipment getShipment(@PathVariable String id) {
        return shipmentService.getShipment(id);
    }
    
    /**
     * Creates a Shipment
     * @param shipment - Shipment to be created
     * @return ResponseEntity
     */
    @PostMapping
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment);
    }
    
}
