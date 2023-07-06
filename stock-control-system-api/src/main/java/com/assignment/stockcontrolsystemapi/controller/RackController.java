package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.RackRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.RackResponse;
import com.assignment.stockcontrolsystemapi.service.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rack")
public class RackController {

    @Autowired
    private RackService rackService;

    @GetMapping
    public List<RackResponse> getAllRacks() {
        return rackService.getAllRacks();
    }

    @GetMapping("/{id}")
    public RackResponse getRackById(@PathVariable String id) {
        return rackService.getRackById(id);
    }

    @PostMapping
    public GenericResponse addRack(@RequestBody RackRequest rackRequest) {
        return rackService.addRack(rackRequest);
    }

    @PutMapping
    public GenericResponse updateRack(@RequestBody RackRequest rackRequest) {
        return rackService.updateRack(rackRequest);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteRackById(@PathVariable String id) {
        return rackService.deleteRackById(id);
    }

}
