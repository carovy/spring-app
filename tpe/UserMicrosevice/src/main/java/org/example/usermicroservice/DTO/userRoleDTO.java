package org.example.usermicroservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class userRoleDTO {
    private Long id;
    private String name;
    private String lastname;
    private String role;

    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name + ", lastname=" + lastname + ", role=" + role + "]";
    }

}
