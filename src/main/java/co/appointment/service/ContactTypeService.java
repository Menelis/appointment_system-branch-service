package co.appointment.service;

import co.appointment.dto.ContactTypeDTO;
import co.appointment.entity.ContactType;
import co.appointment.mapper.ContactTypeToDTOMapper;
import co.appointment.payload.request.NewContactTypeRequest;
import co.appointment.payload.request.UpdateContactTypeRequest;
import co.appointment.repository.ContactTypeRepository;
import co.appointment.shared.payload.response.ApiResponse;
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
public class ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;
    private final ContactTypeToDTOMapper contactTypeToDTOMapper;

    public Page<ContactTypeDTO> getAllContactTypes(final Integer pageNo, final Integer pageSize) {
        Page<ContactType> contactTypes = contactTypeRepository.findAll(ObjectUtils.getPageable(pageNo, pageSize));

        List<ContactTypeDTO> content = contactTypes
                .map(contactTypeToDTOMapper::toDTO)
                .toList();
        return new PageImpl<>(content, contactTypes.getPageable(), contactTypes.getTotalElements());
    }

    public ResponseEntity<ApiResponse<ContactTypeDTO>> getContactTypeById(final int id) {
        Optional<ContactType> optionalContactType = contactTypeRepository.findById(id);
        return optionalContactType
                .map(contactType -> ResponseEntity.ok(new ApiResponse<>(contactTypeToDTOMapper.toDTO(contactType))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ApiResponse<ContactTypeDTO>> createContactType(final NewContactTypeRequest contactTypeRequest) {
        Optional<ContactType> optionalContactType = contactTypeRepository.findByName(contactTypeRequest.getName());
        if (optionalContactType.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Contact type already exists"));
        }
        contactTypeRepository.save(contactTypeToDTOMapper.toEntity(contactTypeRequest));
        return ResponseEntity.ok(new ApiResponse<>(true, "Contact type has been created successfully"));
    }
    public ResponseEntity<ApiResponse<ContactTypeDTO>> updateContactType(final int id, final UpdateContactTypeRequest contactTypeRequest) {
        if(id != contactTypeRequest.getId()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("id mismatch"));
        }
        Optional<ContactType> optionalContactType = contactTypeRepository.findById(id);
        if(optionalContactType.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contactTypeRepository.save(contactTypeToDTOMapper.toEntity(contactTypeRequest));
        return ResponseEntity.ok(new ApiResponse<>(true, "Contact Type has been updated successfully."));
    }
    public ResponseEntity<ApiResponse<Void>> deleteContactType(final int id) {
        Optional<ContactType> optionalContactType = contactTypeRepository.findById(id);
        if(optionalContactType.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contactTypeRepository.delete(optionalContactType.get());
        return ResponseEntity.noContent().build();
    }
}
