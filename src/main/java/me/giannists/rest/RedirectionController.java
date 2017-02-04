package me.giannists.rest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.giannists.service.UrlRedirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rdr")
public class RedirectionController {

    @Autowired
    private UrlRedirectionService urlRedirectionService;

    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Redirecting to url"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(path = "{retrievalKey}", method = RequestMethod.GET)
    public ResponseEntity redirect(@PathVariable String retrievalKey) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(urlRedirectionService.retrieve(retrievalKey))
                .build();
    }

}
