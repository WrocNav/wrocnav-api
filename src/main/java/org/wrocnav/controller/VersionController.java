package org.wrocnav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    private static long VERSION = 1L;

    @RequestMapping("/version")
    public long getVersion() {
        return VERSION;
    }
}
