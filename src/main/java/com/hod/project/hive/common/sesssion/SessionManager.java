package com.hod.project.hive.common.sesssion;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SessionManager {

    @Value("${hive.session.dev-token}")
    private String devToken;

    private Map<String, Session> sessionList = new Hashtable<>();

    public synchronized void addSession(String id, String token) {
        sessionList.put(token, new Session(id, token));
    }

    public synchronized void removeSession(String token) {
        sessionList.remove(token);
    }

    public synchronized boolean isValidSession(String token) {
        if (devToken.equals(token)) {
            return true;
        }
        Session session = sessionList.get(token);
        if (session == null) {
            return false;
        }

        if (session.isValid()) { // 세션 시간 유효
            session.resetExpireTime();
            return true;
        } else {
            removeSession(token);
            return false;
        }
    }

    @Scheduled(initialDelayString = "${hive.session.timeout}", fixedDelayString = "${hive.session.check-interval}")
    public synchronized void checkExpireSession() {
        Set<String> keySet = sessionList.keySet();
        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            Session session = sessionList.get(key);
            if (!session.isValid()) {
                iterator.remove();
            }
        }
    }

    @Data
    public static class Session {
        private String id;
        private String token;
        private Date expireTime;

        @Value("${hive.session.timeout}")
        private int sessionTimeout;

        public Session(String id, String token) {
            this.id = id;
            this.token = token;
            setExpireTime();
        }

        private void setExpireTime() {
            Date date = new Date();
            date.setTime(date.getTime() + sessionTimeout);
            expireTime = date;
        }

        public void resetExpireTime() {
            setExpireTime();
        }

        public boolean isValid() {
            Date now = new Date();

            return now.before(expireTime); // 현재가 이전이면 true
        }
    }
}
