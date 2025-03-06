package com.example.codehive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset")
public class AssetController {

    @GetMapping("my_asset.do")
    public String myAsset() {
        return "asset/my_asset";
    }

}
