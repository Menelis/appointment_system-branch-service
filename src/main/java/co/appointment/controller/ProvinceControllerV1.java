package co.appointment.controller;

import co.appointment.dto.ProvinceDTO;
import co.appointment.service.ProvinceService;

import co.appointment.shared.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/province")
public class ProvinceControllerV1 {
    private final ProvinceService provinceService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<ProvinceDTO>>> getAllProvinces() {
        return ResponseEntity.ok(new ApiResponse<>(provinceService.getAllProvinces()));
    }
}
