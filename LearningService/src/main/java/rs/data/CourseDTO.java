package rs.data;

import java.math.BigDecimal;

public record CourseDTO(
        String title,
        String description,
        BigDecimal studyTime
) {}
