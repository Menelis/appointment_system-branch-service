package co.appointment.controller;

import co.appointment.dto.CityDTO;
import co.appointment.service.CityService;
import co.appointment.shared.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityControllerV1 {
    private final CityService cityService;

    @GetMapping("/admin/get-cities-by-province-id/{provinceId}")
    public ResponseEntity<ApiResponse<List<CityDTO>>> getAllCities(@PathVariable(name = "provinceId")final int provinceId) {
        return ResponseEntity.ok(new ApiResponse<>(cityService.getCitiesByProvinceId(provinceId)));
    }
}
