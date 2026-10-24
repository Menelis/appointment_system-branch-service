package co.appointment.service;

import co.appointment.grpc.BranchServiceGrpc;
import co.appointment.grpc.GetBranchByIdRequest;
import co.appointment.grpc.GetBranchResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class BranchGrcpService extends BranchServiceGrpc.BranchServiceImplBase {

    // private final BranchRepository branchRepository;

    @Override
    public void processBranchById(
            final GetBranchByIdRequest request, final StreamObserver<GetBranchResponse> responseObserver) {
        /*
        Branch branch = branchRepository
                .findById(request.getId())
                .orElseThrow(() -> new RuntimeException("No Branch exists with id: " + request.getId()));
        GetBranchResponse.Builder builder = GetBranchResponse.newBuilder()
                .setId(branch.getId())
                .setName(branch.getName())
                .setStreetNo(branch.getStreetNo())
                .setPostalCode(branch.getPostalCode())
                .setEmailAddress(branch.getEmail())
                .setLandLine(branch.getLandLine());
        if (branch.getProvince() != null) {
            builder.setProvince(branch.getProvince().getName());
        }
        if (branch.getCity() != null) {
            builder.setCity(branch.getCity().getName());
        }
        if (StringUtils.hasText(branch.getAddressLine1())) {
            builder.setAddressLine1(branch.getAddressLine1());
        }
        if (StringUtils.hasText(branch.getAddressLine2())) {
            builder.setAddressLine2(branch.getAddressLine2());
        }
        if (StringUtils.hasText(branch.getFaxNo())) {
            builder.setFaxNumber(branch.getFaxNo());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();

         */
    }
}
