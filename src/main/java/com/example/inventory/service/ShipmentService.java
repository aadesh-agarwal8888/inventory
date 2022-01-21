package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventory.model.Shipment;
import com.example.inventory.repository.ShipmentRepository;

/**
 * Responsible for managing Shipment Services
 * @author aadesh
 *
 */
@Service
public class ShipmentService {

    public ShipmentService() {
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
    ShipmentRepository shipmentRepository;
    
    @Autowired
    InventoryService inventoryService;
    
    /**
     * Retrieves all the Shipments
     * @return List of Shipments
     */
    public List<Shipment> getShipments() {
        List<Shipment> resposne = shipmentRepository.findAll();
        System.out.println(resposne);
        return resposne;
    }
    
    /**
     * Retrieve Shipment by Id
     * @param id - Id of the shipment
     * @return Shipment
     */
    public Shipment getShipment(String id) {
        Optional<Shipment> optional = shipmentRepository.findById(id);
        
        if(!optional.isPresent())
            return null;
        
        return optional.get();
    }
    
    /**
     * Creates the Shipment
     * @param shipment - Shipment ot be created
     * @return Shipment
     */
    public Shipment createShipment(Shipment shipment) {
        inventoryService.removeItemsFromInventory(shipment.getFrom().toString(), shipment.getItems());
        inventoryService.addItemsToInventory(shipment.getTo().toString(), shipment.getItems());
        Shipment resposne = shipmentRepository.save(shipment);
        return resposne;
    }
    
    

}
