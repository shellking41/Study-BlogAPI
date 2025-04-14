package org.study.studyblogapi.model.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.study.studyblogapi.model.enums.UsageType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserIconResponse {

    private Long id;
    private Integer userId;
    private String path;
    @Enumerated(EnumType.STRING)
    private UsageType usageType;

}
