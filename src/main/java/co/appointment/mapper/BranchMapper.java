package co.appointment.mapper;

import co.appointment.entity.Branch;
import co.appointment.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchMapper {

    private final BranchRepository branchRepository;

    public Branch fromId(final Integer id) {
        if(id == null) {
            return null;
        }
        return branchRepository.findById(id).orElse(null);
    }
}
