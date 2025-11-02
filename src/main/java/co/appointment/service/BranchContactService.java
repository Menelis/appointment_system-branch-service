package co.appointment.service;

import co.appointment.dto.BranchContactDTO;
import co.appointment.entity.BranchContact;
import co.appointment.mapper.BranchContactToDTOMapper;
import co.appointment.payload.request.NewBranchContactRequest;
import co.appointment.payload.request.UpdateBranchContactRequest;
import co.appointment.repository.BranchContactRepository;
import co.appointment.shared.payload.response.ApiResponse;
import co.appointment.shared.util.SharedObjectUtils;
import co.appointment.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchContactService {

    private final BranchContactRepository branchContactRepository;
    private final BranchContactToDTOMapper branchContactToDTOMapper;

    public Page<BranchContactDTO> getBranchContacts(final int pageNo, final int pageSize) {
        Page<BranchContact> pagedBranchContacts = branchContactRepository.findAll(ObjectUtils.getPageable(pageNo, pageSize));

        List<BranchContactDTO> content = pagedBranchContacts
                .stream()
                .map(branchContactToDTOMapper::toDTO)
                .toList();
        return new PageImpl<>(content, pagedBranchContacts.getPageable(), pagedBranchContacts.getTotalElements());
    }

    public ResponseEntity<ApiResponse<BranchContactDTO>> getBranchContactById(final  int id) {
        Optional<BranchContact> optionalBranchContact = branchContactRepository.findById(id);
        return optionalBranchContact
                .map(branchContact -> ResponseEntity.ok(new ApiResponse<>(branchContactToDTOMapper.toDTO(branchContact))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<ApiResponse<BranchContactDTO>> createBranchContact(final NewBranchContactRequest request) {
        BranchContact branchContact = branchContactToDTOMapper.toEntity(request);
        Optional<BranchContact> optionalBranchContact = branchContactRepository.findByBranchAndContactTypeAndContact(branchContact.getBranch(), branchContact.getContactType(), branchContact.getContact());
        if(optionalBranchContact.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Branch Contact already exists"));
        }
        branchContactRepository.save(branchContact);
        return ResponseEntity.ok(new ApiResponse<>(true, "Branch Contact has been created successfully."));
    }

    public ResponseEntity<ApiResponse<BranchContactDTO>> updateBranchContact(final int id, final UpdateBranchContactRequest request) {
        if(id != request.getId()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("id mismatch"));
        }
        Optional<BranchContact> optionalBranchContact = branchContactRepository.findById(id);
        if(optionalBranchContact.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BranchContact branchContact = branchContactToDTOMapper.toEntity(request);
        SharedObjectUtils.mapAuditFields(branchContact, optionalBranchContact.get());

        branchContactRepository.save(branchContact);

        return ResponseEntity.ok(new ApiResponse<>(true, "Branch Contact has been updated successfully."));
    }

    public ResponseEntity<ApiResponse<Void>> deleteBranchContact(final int id) {
        Optional<BranchContact> optionalBranchContact = branchContactRepository.findById(id);
        if(optionalBranchContact.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        branchContactRepository.delete(optionalBranchContact.get());

        return ResponseEntity.noContent().build();
    }
}
