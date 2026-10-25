package co.appointment.controller.v1;

import static co.appointment.shared.constant.SharedConstants.X_CORRELATION_ID;

import co.appointment.dto.BranchDto;
import co.appointment.payload.request.CreateBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.service.BranchService;
import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.dto.ServiceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
@Tag(name = "Branches", description = "Endpoints for managing and viewing branches")
public class BranchControllerV1 {

    private final BranchService branchService;

    @Operation(summary = "Get branches", description = "Retrieves a list of branches")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved list of branches",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchDto.class)))),
                @ApiResponse(responseCode = "204", description = "No branches found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranches(
            @RequestHeader(name = X_CORRELATION_ID) final UUID xCorrelationId) {

        List<BranchDto> branches = branchService.getAllBranches(xCorrelationId);

        return ResponseEntity.ok(branches);
    }

    @Operation(
            summary = "Get branches by province ID(UUID) and city ID(UUID)",
            description = "Retrieves a list of branches")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved list of branches",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchDto.class)))),
                @ApiResponse(responseCode = "204", description = "No branches found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @GetMapping("/provinces/{provinceId}/cities/{cityId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByProvinceId(
            @PathVariable(name = "provinceId") @Parameter(description = "Province ID", required = true)
                    final UUID provinceId,
            @PathVariable(name = "cityId") @Parameter(description = "City ID", required = true) final UUID cityId,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {

        List<BranchDto> branches = branchService.getBranches(provinceId, cityId, xCorrelationId);

        return ResponseEntity.ok(branches);
    }

    @Operation(
            summary = "Get paginated branches",
            description = "Retrieves a paginated list of branches with optional search term filtering.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved list of branches",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchDto.class)))),
                @ApiResponse(responseCode = "204", description = "No branches found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @GetMapping("/search")
    public ResponseEntity<Page<BranchDto>> getAllBranches(
            @RequestParam(
                            name = SharedConstants.PAGE_NUMBER_PARAMETER_NAME,
                            defaultValue = SharedConstants.PAGE_NUMBER_DEFAULT_VALUE)
                    final int pageNumber,
            @RequestParam(
                            name = SharedConstants.PAGE_SIZE_PARAMETER_NAME,
                            defaultValue = SharedConstants.PAGE_SIZE_DEFAULT_VALUE)
                    final int pageSize,
            @RequestParam(name = SharedConstants.SEARCH_TERM_PARAMETER_NAME, required = false) final String searchTerm,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {

        Page<BranchDto> pagedBranches =
                branchService.getPagedBranches(pageNumber, pageSize, searchTerm, xCorrelationId);

        return ResponseEntity.ok(pagedBranches);
    }

    @Operation(
            summary = "Get Branch by ID",
            description = "Retrieves branch details that matches the provided ID(UUID).")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved branches details",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = BranchDto.class)))),
                @ApiResponse(responseCode = "204", description = "No branch found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(
            @PathVariable @Parameter(description = "Branch ID(UUID)") final UUID id,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {

        Optional<BranchDto> branchDTO = branchService.getBranchById(id, xCorrelationId);

        return ResponseEntity.ok(branchDTO.get());
    }

    @Operation(summary = "Create new branch", description = "Create the new branch.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully created the branch",
                        content = @Content(contentSchema = @Schema(implementation = CreateBranchRequest.class))),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @PostMapping("/admin")
    public ResponseEntity<ServiceResponseDto> createBranch(
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId,
            @RequestBody @Valid @Parameter(description = "Create Branch Request")
                    final CreateBranchRequest branchRequest) {

        ServiceResponseDto responseDto = branchService.createBranch(branchRequest, xCorrelationId);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Modifies the existing branch", description = "Modifies the existing branch.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully updated the branch",
                        content = @Content(contentSchema = @Schema(implementation = UpdateBranchRequest.class))),
                @ApiResponse(responseCode = "204", description = "No branch found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @PutMapping("/admin/{id}")
    public ResponseEntity<ServiceResponseDto> updateBranch(
            @PathVariable("id") @Parameter(description = "Branch ID(UUID)") final UUID id,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId,
            @RequestBody @Parameter(description = "Update Branch Request") final UpdateBranchRequest branchRequest) {

        ServiceResponseDto responseDto = branchService.updateBranch(id, branchRequest, xCorrelationId);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Delete the existing branch", description = "Delete the existing branch by branch ID(UUID)")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully deleted the branch",
                        content = @Content(contentSchema = @Schema(implementation = UpdateBranchRequest.class))),
                @ApiResponse(responseCode = "204", description = "No branch found", content = @Content),
                @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                @ApiResponse(responseCode = "403", description = "Insufficient permissions", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            })
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ServiceResponseDto> deleteBranch(
            @PathVariable @Parameter(description = "Branch ID(UUID)") final UUID id,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {

        ServiceResponseDto responseDto = branchService.deleteBranch(id, xCorrelationId);

        return ResponseEntity.ok(responseDto);
    }
}
