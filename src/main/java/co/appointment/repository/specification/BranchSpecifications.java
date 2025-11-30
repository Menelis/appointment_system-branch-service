package co.appointment.repository.specification;

import co.appointment.entity.Branch;
import org.springframework.data.jpa.domain.Specification;

public class BranchSpecifications {

    public static Specification<Branch> nameContains(final String searchText) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + searchText + "%");
    }
    public static Specification<Branch> streetNoContains(final String searchText) {
        return (root, query, builder) -> builder.like(root.get("streetNo"), "%" + searchText + "%");
    }
}
