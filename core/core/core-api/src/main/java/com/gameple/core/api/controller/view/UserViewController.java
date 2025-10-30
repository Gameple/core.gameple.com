package com.gameple.core.api.controller.view;

import com.gameple.core.domain.ClientInfoService;
import com.gameple.core.enums.ClientType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/widget/v1")
public class UserViewController {

    private final ClientInfoService clientInfoService;

    @GetMapping("/login")
    public String loginWidget(
            @RequestParam String redirectUrl,
            @RequestParam ClientType clientType
            ) {
        clientInfoService.validateRedirectUrl(redirectUrl, clientType);
        return "loginWidget";
    }

    @GetMapping("/sign-up")
    public String signupWidget() {
        return "signupWidget";
    }
}
