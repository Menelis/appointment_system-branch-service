package co.appointment.service;

import co.appointment.entity.Branch;
import co.appointment.grpc.BranchServiceGrpc;
import co.appointment.grpc.GetBranchByIdRequest;
import co.appointment.grpc.GetBranchResponse;
import co.appointment.repository.BranchRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class BranchGrcpService extends BranchServiceGrpc.BranchServiceImplBase {

    private final BranchRepository branchRepository;

    @Override
    public void processBranchById(GetBranchByIdRequest request, StreamObserver<GetBranchResponse> responseObserver) {
        Branch branch = branchRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("No Branch exists with id: "+ request.getId()));
        GetBranchResponse.Builder builder = GetBranchResponse.newBuilder()
                .setId(branch.getId())
                .setName(branch.getName())
                .setStreetNo(branch.getStreetNo())
                .setAddressLine1(branch.getAddressLine1())
                .setAddressLine2(branch.getAddressLine2())
                .setPostalCode(branch.getPostalCode())
                .setEmailAddress(branch.getEmail())
                .setFaxNumber(branch.getFaxNo())
                .setLandLine(branch.getLandLine());
        if(branch.getProvince() != null) {
            builder.setProvince(branch.getProvince().getName());
        }
        if(branch.getCity() != null) {
            builder.setCity(branch.getCity().getName());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
