package com.mylearning.actuator.endpoint;

import com.mylearning.actuator.dto.ProdRelease;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Endpoint(id="releases")
public class FeatureReleasesEndPoint {

    List<ProdRelease> prodReleases=new ArrayList<>();

    // selector is similar to PathVariable in RestController
    // Endpoint doesn't support the complex object that's why we are passing it as String param

    @WriteOperation //POST
    public void addNewReleaseInfo(@Selector String crq, @Selector String releaseDt, String features){
        ProdRelease release = ProdRelease.builder().crq(crq)
                .releaseDt(releaseDt)
                .features(Arrays.stream(features.split(",")).collect(Collectors.toList())).build();
        prodReleases.add(release);
    }

    @ReadOperation //GET
    public List<ProdRelease> getAllReleases(){
        return prodReleases;
    }

    @ReadOperation //GET ⇒ By change request number
    public ProdRelease getReleaseByCRQ(@Selector String crq){
        return prodReleases.stream().filter(prodRelease -> prodRelease.getCrq().equals(crq))
                .findAny().get();
    }

    @DeleteOperation //DELETE
    public void deleteRelease(@Selector String crq){
        prodReleases.remove(getReleaseByCRQ(crq));
    }
}
