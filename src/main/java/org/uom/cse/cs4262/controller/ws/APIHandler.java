package org.uom.cse.cs4262.controller.ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Chanaka Lakmal
 * @date 5/11/2017
 * @since 1.0
 */

@RestController
public class APIHandler {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String root() throws IOException {
        return "SUCCESS";
    }
}
