package co.appointment.service.grp;

import co.appointment.grpc.Address;
import co.appointment.grpc.BranchServiceGrpc;
import co.appointment.grpc.GetBranchByIdRequest;
import co.appointment.grpc.GetBranchResponse;
import co.appointment.persistence.BranchRepository;
import co.appointment.shared.record.BranchSummary;
import io.grpc.stub.StreamObserver;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class BranchGrcpService extends BranchServiceGrpc.BranchServiceImplBase {

    private final BranchRepository branchRepository;

    @Override
    public void processBranchById(
            final GetBranchByIdRequest request, final StreamObserver<GetBranchResponse> responseObserver) {
        BranchSummary branchSummary = branchRepository
                .getBranchSummary(UUID.fromString(request.getId()))
                .orElseThrow(
                        () -> new IllegalArgumentException("No branch exist with id=[%s]".formatted(request.getId())));
        GetBranchResponse.Builder builder = GetBranchResponse.newBuilder()
                .setName(branchSummary.name())
                .setPhysicalAddress(Address.newBuilder()
                        .setAddressLine1(branchSummary.addressLine1())
                        .setAddressLine2(branchSummary.addressLine2())
                        .setSuburb(branchSummary.suburb())
                        .setProvince(branchSummary.province())
                        .setCity(branchSummary.city())
                        .build())
                .setEmailAddress(branchSummary.email())
                .setFaxNumber(branchSummary.faxNo())
                .setLandLine(branchSummary.landLine());

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
