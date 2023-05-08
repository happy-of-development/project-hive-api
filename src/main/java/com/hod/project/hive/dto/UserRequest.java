package com.hod.project.hive.dto;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class UserRequest {
    @NotBlank(groups = {Update.class}, message = "id가 입력되지 않았습니다.")
    @Pattern(groups = {Update.class}, regexp = "^[a-zA-Z0-9]{8}$", message = "id 형식이 잘못 되었습니다.")
    private String id;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 1, max = 15, message = "이름은 1~15자리 입니다.")
    private String name;

    private String password;

    private String photo;

    @Pattern(regexp = "^\\d{11}$", message = "휴대전화 번호 자리(11자리)가 맞지 않거나, 형식이 틀립니다.")
    private String mobile;

    @Email(message = "email 형식이 잘못 되었습니다.")
    private String email;

    public interface Update {
    }
}
