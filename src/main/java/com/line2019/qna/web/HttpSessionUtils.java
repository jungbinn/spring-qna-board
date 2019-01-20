package com.line2019.qna.web;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String SESSION_KEY = "session-user";

    public static User getSessionUser(HttpSession session) {
        return (User) session.getAttribute(HttpSessionUtils.SESSION_KEY);
    }

    public static boolean isUserLogin(HttpSession session) {
        return getSessionUser(session) != null;
    }

    public static boolean isUserAdmin(HttpSession session) {
        return isUserLogin(session) && getSessionUser(session).isAdmin();
    }
}
