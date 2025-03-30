package org.study.studyblogapi.model.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.studyblogapi.model.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    Integer id;
    String firstname;
    String lastname;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;
}
