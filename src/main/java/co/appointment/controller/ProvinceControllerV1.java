package co.appointment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/province")
public class ProvinceControllerV1 {
    /*
    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProvinceDTO>>> getAllProvinces() {
        return ResponseEntity.ok(ApiResponse.success(provinceService.getAllProvinces()));
    }
    */
}
