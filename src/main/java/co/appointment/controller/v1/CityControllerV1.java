package co.appointment.controller.v1;

import static co.appointment.shared.constant.SharedConstants.X_CORRELATION_ID;

import co.appointment.dto.CityDTO;
import co.appointment.service.CityService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Tag(name = "Cities", description = "Endpoints for managing and viewing cities")
public class CityControllerV1 {

    private final CityService cityService;

    @Operation(
            summary = "Get cities by province ID",
            description = "Retrieves a list of all or cities associated with a specific province UUID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved list of cities",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDTO.class)))),
                @ApiResponse(responseCode = "204", description = "No cities found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @GetMapping
    public ResponseEntity<List<CityDTO>> getCityByProvince(
            @RequestParam(required = false) @Parameter(description = "Province ID(UUID)") final UUID provinceId,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {

        return ResponseEntity.ok(cityService.getCities(provinceId, xCorrelationId));
    }
}
