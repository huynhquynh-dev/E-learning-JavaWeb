package com.myclass.controller;

import com.myclass.model.response.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("")
    public Object testPublic() {
        RestResult<String> result = new RestResult<>();
        result.ok();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
