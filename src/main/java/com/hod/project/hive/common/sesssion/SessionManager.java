package com.hod.project.hive.common.sesssion;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SessionManager {
    @Data
    public static class Session {
        String id;
        String token;
        Date expireTime;

        public Session(String id, String token) {
            this.id = id;
            this.token = token;
            setExpireTime();
        }

        private void setExpireTime() {
            Date date = new Date();
            date.setTime(date.getTime() + SESSION_TIMEOUT_MS);
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

    public static int SESSION_TIMEOUT_MS = 10 * 60 * 1000; // 10Min TODO : properties에 설정할지 검토

    private Hashtable<String, Session> sessionList = new Hashtable<>();

    public SessionManager() {
        // FIXME  : 테스트 코드 삭제 할것.
        addSession("111111", "AAAA");
    }

    public synchronized void  addSession(String id, String token) {
        sessionList.put(token, new Session(id, token));
    }

    public synchronized void removeSession(String token) {
        sessionList.remove(token);
    }

    public synchronized boolean isValidSession(String token) {
        Session session = sessionList.get(token);
        if(session == null) {
            return false;
        }

        if(session.isValid()) { // 세션 시간 유효
            session.resetExpireTime();
            return true;
        } else {
            removeSession(token);
            return false;
        }
    }

    // TODO : 5분마다 실행인데 properties에 넣을지 불필요한 작업인지 검토 필요
    @Scheduled(fixedDelay = 5 * 60 * 1000 )
    public synchronized void checkExpireSession() {
        Set<String> keySet = sessionList.keySet();
        Iterator<String> iterator = keySet.iterator();

        while(iterator.hasNext()) {
            String key = iterator.next();
            Session session = sessionList.get(key);
            if(!session.isValid()) {
                iterator.remove();
            }
        }
    }
}
