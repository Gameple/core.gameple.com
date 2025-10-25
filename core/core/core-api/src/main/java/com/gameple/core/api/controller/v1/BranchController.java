package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.response.CountryInfoResponse;
import com.gameple.core.domain.BranchService;
import com.gameple.core.helper.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branch")
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/countries")
    public ApiResponse<List<CountryInfoResponse>> findCountries() {
        return ApiResponse.success(branchService.findCountries());
    }
}
