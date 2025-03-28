package kr.co.wikibook.account.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.common.util.HttpUtils;
import kr.co.wikibook.account.dto.AccountJoinRequestDTO;
import kr.co.wikibook.account.dto.AccountLoginRequestDTO;
import kr.co.wikibook.account.etc.AccountConstants;
import kr.co.wikibook.member.entity.Member;
import kr.co.wikibook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionAccounHelper implements AccountHelper{

    private final MemberService memberService;

    @Override
    public void join(AccountJoinRequestDTO joinReq) {
        memberService.save(joinReq.getName(), joinReq.getLoginId(), joinReq.getLoginPw());
    }

    @Override
    public String login(AccountLoginRequestDTO loginReq, HttpServletRequest req, HttpServletResponse res) {
        Member member = memberService.find(loginReq.getLoginId(), loginReq.getLoginPw());

        if (member == null) {
            return null;
        }

        HttpUtils.setSession(req, AccountConstants.MEMBER_ID_NAME, member.getId());
        return member.getLoginId();
    }

    @Override
    public Integer getMemberId(HttpServletRequest req) {
        Object memberId = HttpUtils.getSessionValue(req, AccountConstants.MEMBER_ID_NAME);

        if (memberId != null) {
            return (int) memberId;
        }
        return null;
    }

    @Override
    public boolean isLoggedIn(HttpServletRequest req) {
        return getMemberId(req) != null;
    }

    @Override
    public void logout(HttpServletRequest req, HttpServletResponse res) {
        HttpUtils.removeSession(req, AccountConstants.MEMBER_ID_NAME);
    }
}
