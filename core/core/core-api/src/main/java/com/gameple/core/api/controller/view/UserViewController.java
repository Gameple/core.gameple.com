package com.gameple.core.api.controller.view;

import com.gameple.core.domain.ClientInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final ClientInfoService clientInfoService;

    @GetMapping("/widget/v1/login")
    public String loginWidget(@RequestParam String redirectUrl) {
        clientInfoService.validateRedirectUrl(redirectUrl);
        return "loginWidget";
    }
}
