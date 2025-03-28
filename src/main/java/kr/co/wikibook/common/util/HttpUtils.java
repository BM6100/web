package kr.co.wikibook.common.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {

    //http 세션 데이터를 입력하는 메서드
    public static void setSession(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    public static Object getSessionValue(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
    }

    public static void removeSession(HttpServletRequest req, String key) {
        req.getSession().removeAttribute(key);
    }
}
