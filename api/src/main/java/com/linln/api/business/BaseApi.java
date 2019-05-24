package com.linln.api.business;

import com.linln.api.business.frombean.base.Response;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BaseApi {

    protected Response verification(BindingResult result)
    {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                return new Response().failure(error.getDefaultMessage());
            }
        }
        return new Response();
    }
}
