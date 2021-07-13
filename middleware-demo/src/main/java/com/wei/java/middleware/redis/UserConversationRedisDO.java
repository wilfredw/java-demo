package com.wei.java.middleware.redis;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/6/21
 */
public class UserConversationRedisDO {
    private Long version;
    private String role;
    private String status;
    private Integer unreadNum;
    private Long lastReadMsgId;
    private Long lastMsgId;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(Integer unreadNum) {
        this.unreadNum = unreadNum;
    }

    public Long getLastReadMsgId() {
        return lastReadMsgId;
    }

    public void setLastReadMsgId(Long lastReadMsgId) {
        this.lastReadMsgId = lastReadMsgId;
    }

    public Long getLastMsgId() {
        return lastMsgId;
    }

    public void setLastMsgId(Long lastMsgId) {
        this.lastMsgId = lastMsgId;
    }
}
