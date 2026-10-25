package co.appointment.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SuppressWarnings({"HideUtilityClassConstructor"})
public class ObjectUtils {
    public static Pageable getPageable(final int page, final int size) {
        return PageRequest.of(page - 1, size);
    }
}
