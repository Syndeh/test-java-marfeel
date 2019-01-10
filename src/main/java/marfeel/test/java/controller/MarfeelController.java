package marfeel.test.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import marfeel.test.java.resources.SiteRequest;
import marfeel.test.java.resources.SiteResponse;
import marfeel.test.java.service.MarfeelService;

@RestController
public class MarfeelController {

    @Autowired
    MarfeelService userService;
 
    @RequestMapping(method = RequestMethod.POST, value = "/marfeelizable")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SiteResponse> analyze(@RequestBody List<SiteRequest> siteResourceList) {
        SiteResponse response = userService.analyze(siteResourceList);

        return new ResponseEntity<SiteResponse>(response, HttpStatus.CREATED);
    }

}