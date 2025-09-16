package dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

    private UUID id;
    private String name;
    private String profilePictureUrl;

}
