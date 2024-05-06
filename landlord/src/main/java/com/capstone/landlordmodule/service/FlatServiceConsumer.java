package com.capstone.landlordmodule.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.landlordmodule.model.Flat;


@FeignClient(name = "FLAT")
public interface FlatServiceConsumer {
    
    @PostMapping("/flat/save")
    Flat saveFlat(@RequestBody Flat flat);


}
