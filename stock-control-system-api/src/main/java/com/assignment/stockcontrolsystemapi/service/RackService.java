package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.RackRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.RackResponse;
import com.assignment.stockcontrolsystemapi.model.Rack;
import com.assignment.stockcontrolsystemapi.repository.RackRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RackService {

    @Autowired
    private RackRepository rackRepository;
    @Autowired
    private InventoryService inventoryService;

    public List<RackResponse> getAllRacks() {
        return rackRepository.findAll()
            .stream()
            .map(rack ->
                RackResponse.builder()
                    .id(rack.getId())
                    .name(rack.getName())
                    .damagedRack(rack.isDamagedRack())
                    .inventories(
                        inventoryService.getAllInventories()
                            .stream()
                            .filter(inventory -> rack.getId().equals(inventory.getRackId()))
                                .filter(inventory -> inventory.getStatus().equals("IN_STOCK") || inventory.getStatus().equals("RETURN"))
                            .collect(Collectors.toList())
                    )
                    .build())
            .collect(Collectors.toList());
    }

    public RackResponse getRackById(String id) {
        return rackRepository.findById(id).map(rack ->
            RackResponse.builder()
                .id(rack.getId())
                .name(rack.getName())
                .damagedRack(rack.isDamagedRack())
                .inventories(
                    inventoryService.getAllInventories()
                        .stream()
                        .filter(inventory -> rack.getId().equals(inventory.getRackId()))
                        .collect(Collectors.toList())
                )
                .build()).get();
    }

    public GenericResponse addRack(RackRequest rackRequest) {
        Rack rack = Rack.builder()
            .id(rackRequest.getId())
            .name(rackRequest.getName())
            .damagedRack(rackRequest.isDamagedRack())
            .build();

        Rack response = rackRepository.save(rack);

        return new GenericResponse(Constants.SUCCESS, "rack added successfully");
    }

    public GenericResponse updateRack(RackRequest rackRequest) {
        Optional<Rack> rack = rackRepository.findById(rackRequest.getId());
        if (rack.isPresent()) {
            Rack updatedRack = Rack.builder()
                .id(rackRequest.getId())
                .name(rackRequest.getName())
                .damagedRack(rackRequest.isDamagedRack())
                .build();
            rackRepository.save(updatedRack);
            return new GenericResponse(Constants.SUCCESS, "rack updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "rack not found");
        }
    }

    public GenericResponse deleteRackById(String id) {
        rackRepository.deleteById(id);
        return new GenericResponse(Constants.SUCCESS, "rack deleted successfully");
    }
}
