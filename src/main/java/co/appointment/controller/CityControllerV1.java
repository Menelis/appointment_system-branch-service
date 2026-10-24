package co.appointment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityControllerV1 {

    /*
    private final CityService cityService;

    @GetMapping("/get-cities-by-province-id/{provinceId}")
    public ResponseEntity<ApiResponse<List<CityDTO>>> getAllCities(
            @PathVariable(name = "provinceId") final int provinceId) {
        return ResponseEntity.ok(ApiResponse.success(cityService.getCitiesByProvinceId(provinceId)));
    }
    */
}
