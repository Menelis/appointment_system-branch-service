package co.appointment.controller.v1;

import static co.appointment.shared.constant.SharedConstants.X_CORRELATION_ID;

import co.appointment.dto.ProvinceDTO;
import co.appointment.service.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provinces")
@Tag(name = "Provinces", description = "Endpoints for managing and viewing provinces")
public class ProvinceControllerV1 {

    private final ProvinceService provinceService;

    @Operation(summary = "Get provinces", description = "Retrieves a list of provinces")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved list of provinces",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProvinceDTO.class)))),
                @ApiResponse(responseCode = "204", description = "No provinces found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @GetMapping
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces(
            @RequestParam(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {
        return ResponseEntity.ok(provinceService.getAllProvinces(xCorrelationId));
    }
}
