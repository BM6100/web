package kr.co.wikibook.account.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.account.dto.AccountJoinRequestDTO;
import kr.co.wikibook.account.dto.AccountLoginRequestDTO;

public interface AccountHelper {

    void join(AccountJoinRequestDTO joinReq);

    String login(AccountLoginRequestDTO loginReq, HttpServletRequest req, HttpServletResponse res);

    Integer getMemberId(HttpServletRequest req);

    boolean isLoggedIn(HttpServletRequest req);

    void logout(HttpServletRequest req, HttpServletResponse res);
}
