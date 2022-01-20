package com.thiagosol.ci.controller;

import com.thiagosol.ci.service.DeployService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/deploy")
public class DeployController {

    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping("/{project}")
    public ResponseEntity<String> deploy(@PathVariable String project){
        return ResponseEntity.ok(deployService.deploy(project));
    }


}
