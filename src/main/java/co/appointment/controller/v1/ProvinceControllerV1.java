package co.appointment.controller.v1;

import co.appointment.dto.ProvinceDTO;
import co.appointment.service.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<ProvinceDTO>> getProvinces() {
        return ResponseEntity.ok(provinceService.getProvinces());
    }

    //    @Operation(summary = "Get provinces", description = "Retrieves a list of provinces")
    //    @ApiResponses(
    //            value = {
    //                @ApiResponse(
    //                        responseCode = "200",
    //                        description = "Successfully retrieved list of provinces",
    //                        content = @Content(array = @ArraySchema(schema = @Schema(implementation =
    // ProvinceDTO.class)))),
    //                @ApiResponse(responseCode = "204", description = "No provinces found", content = @Content),
    //                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    //                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
    //                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    //            })
    //    @GetMapping
    //    public ResponseEntity<Page<ProvinceDTO>> getAllProvinces(
    //            @RequestParam(name = PAGE_NUMBER_PARAMETER_NAME, defaultValue = PAGE_NUMBER_DEFAULT_VALUE)
    //                    @Parameter(description = "Page Number", required = true, example = PAGE_NUMBER_DEFAULT_VALUE)
    //                    final int pageNo,
    //            @RequestParam(name = PAGE_SIZE_PARAMETER_NAME, defaultValue = PAGE_SIZE_DEFAULT_VALUE)
    //                    @Parameter(
    //                            description = "The number of items to return per page",
    //                            required = true,
    //                            example = PAGE_SIZE_DEFAULT_VALUE)
    //                    final int pageSize,
    //            @RequestParam(name = SEARCH_TERM_PARAMETER_NAME, required = false)
    //                    @Parameter(description = "Optional search query to filter results by name", example =
    // "Gauteng")
    //                    final String searchTerm,
    //            @RequestHeader(name = X_CORRELATION_ID)
    //                    @Parameter(
    //                            description = "Correlation ID for request tracing",
    //                            required = true,
    //                            example = "550e8400-e29b-41d4-a716-446655440000")
    //                    final UUID xCorrelationId) {
    //        Page<ProvinceDTO> paginatedProvinces =
    //                provinceService.getProvinces(pageNo, pageSize, searchTerm, xCorrelationId);
    //
    //        return ResponseEntity.ok(paginatedProvinces);
    //    }
}
