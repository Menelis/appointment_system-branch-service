package co.appointment.controller.v1;

import static co.appointment.shared.constant.SharedConstants.X_CORRELATION_ID;

import co.appointment.payload.request.CreateBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.service.BranchService;
import co.appointment.shared.dto.ServiceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/branches")
@RequiredArgsConstructor
@Tag(name = "Admin - Branches", description = "Administrative endpoints for managing branches")
public class AdminBranchControllerV1 {
    private final BranchService branchService;

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
    @PostMapping
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponseDto> deleteBranch(
            @PathVariable @Parameter(description = "Branch ID(UUID)") final UUID id,
            @RequestHeader(name = X_CORRELATION_ID)
                    @Parameter(description = "Correlation ID for request tracing", required = true)
                    final UUID xCorrelationId) {

        ServiceResponseDto responseDto = branchService.deleteBranch(id, xCorrelationId);

        return ResponseEntity.ok(responseDto);
    }
}
