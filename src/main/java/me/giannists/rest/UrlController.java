package me.giannists.rest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.giannists.rest.dto.UrlDto;
import me.giannists.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    private UrlShorteningService urlShorteningService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful url insertion", response = UrlDto.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity shortenUrl(@RequestBody UrlDto urlDto) {
        return ResponseEntity.ok(UrlDto.format(urlShorteningService.shorten(urlDto.getUrl())));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found existing shortened url", response = UrlDto.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @RequestMapping(path = "{retrievalKey}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable String retrievalKey) {
        return ResponseEntity.ok(UrlDto.format(urlShorteningService.find(retrievalKey)));
    }
}
