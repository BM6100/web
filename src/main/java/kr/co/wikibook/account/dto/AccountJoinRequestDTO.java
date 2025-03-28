package kr.co.wikibook.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountJoinRequestDTO {

    private String name;
    private String loginId;
    private String loginPw;
}
