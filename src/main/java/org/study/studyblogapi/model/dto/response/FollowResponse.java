package org.study.studyblogapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowResponse {
    Integer followerId;
    String firstname;
    String lastname;
    String email;
    UserIconResponse userIcon;
}
