/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.check.eligibility.service.resource;

import com.check.eligibility.service.dao.queryDao;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crazydude
 */
@RestController
@RequestMapping("/rest/member")
@Api(value = "check member eligibility", description = "validates if member can make claim")
public class CheckEligibility {
    
     @GetMapping("/{memberID}")
    public Boolean getEligibility(@PathVariable("memberID") final String memberID) {
        
         queryDao dao = new queryDao();
         boolean isEligible = dao.isEligible(memberID);
         
         if(isEligible){
             System.out.println("ELIGIBLE");
             return true;
         }
         System.out.println("NOT ELIGIBLE");
         return false;
    }
    
}
