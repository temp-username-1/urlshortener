package me.giannists.rest;

import me.giannists.rest.dto.UrlDto;
import me.giannists.service.UrlRedirectionService;
import me.giannists.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    private UrlShorteningService urlShorteningService;

    @Autowired
    private UrlRedirectionService urlRedirectionService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity shortenUrl(@RequestBody @Valid UrlDto urlDto) {
        return ResponseEntity.ok(UrlDto.format(urlShorteningService.shorten(urlDto.getUrl())));
    }

    @RequestMapping(path = "{retrievalKey}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable String retrievalKey) {
        return ResponseEntity.ok(urlRedirectionService.retrieve(retrievalKey));
    }

    @RequestMapping(path = "{retrievalKey}", method = RequestMethod.GET)
    public ResponseEntity redirect(@PathVariable String retrievalKey) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(urlRedirectionService.retrieve(retrievalKey))
                .build();
    }
}
