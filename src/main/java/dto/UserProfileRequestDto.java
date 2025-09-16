package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserProfileRequestDto {

    private String name;
    private String profilePictureUrl;

}
