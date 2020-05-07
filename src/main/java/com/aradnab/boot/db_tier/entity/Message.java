package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Message {
    private int id;
    private int messageCategoryId;
    private byte messageStatusId;
    private int userIdFrom;
    private int userIdTo;
    private String message;
    private Byte isRead;
    private Date sendTime;
    private Date viewedTime;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private MessageCategory messageCategoryByMessageCategoryId;
    private MessageStatus messageStatusByMessageStatusId;
    private User userByUserIdFrom;
    private User userByUserIdTo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message_category_id", nullable = false)
    public int getMessageCategoryId() {
        return messageCategoryId;
    }

    public void setMessageCategoryId(int messageCategoryId) {
        this.messageCategoryId = messageCategoryId;
    }

    @Basic
    @Column(name = "message_status_id", nullable = false)
    public byte getMessageStatusId() {
        return messageStatusId;
    }

    public void setMessageStatusId(byte messageStatusId) {
        this.messageStatusId = messageStatusId;
    }

    @Basic
    @Column(name = "user_id_from", nullable = false)
    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    @Basic
    @Column(name = "user_id_to", nullable = false)
    public int getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "is_read", nullable = true)
    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    @Basic
    @Column(name = "send_time", nullable = true)
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "viewed_time", nullable = true)
    public Date getViewedTime() {
        return viewedTime;
    }

    public void setViewedTime(Date viewedTime) {
        this.viewedTime = viewedTime;
    }

    @Basic
    @Column(name = "saved_at", nullable = true)
    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    @Basic
    @Column(name = "last_updated_at", nullable = true)
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id &&
                messageCategoryId == message1.messageCategoryId &&
                messageStatusId == message1.messageStatusId &&
                userIdFrom == message1.userIdFrom &&
                userIdTo == message1.userIdTo &&
                Objects.equals(message, message1.message) &&
                Objects.equals(isRead, message1.isRead) &&
                Objects.equals(sendTime, message1.sendTime) &&
                Objects.equals(viewedTime, message1.viewedTime) &&
                Objects.equals(savedAt, message1.savedAt) &&
                Objects.equals(lastUpdatedAt, message1.lastUpdatedAt) &&
                Objects.equals(deletedAt, message1.deletedAt) &&
                Objects.equals(status, message1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageCategoryId, messageStatusId, userIdFrom, userIdTo, message, isRead, sendTime, viewedTime, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "message_category_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public MessageCategory getMessageCategoryByMessageCategoryId() {
        return messageCategoryByMessageCategoryId;
    }

    public void setMessageCategoryByMessageCategoryId(MessageCategory messageCategoryByMessageCategoryId) {
        this.messageCategoryByMessageCategoryId = messageCategoryByMessageCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "message_status_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public MessageStatus getMessageStatusByMessageStatusId() {
        return messageStatusByMessageStatusId;
    }

    public void setMessageStatusByMessageStatusId(MessageStatus messageStatusByMessageStatusId) {
        this.messageStatusByMessageStatusId = messageStatusByMessageStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_from", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUserByUserIdFrom() {
        return userByUserIdFrom;
    }

    public void setUserByUserIdFrom(User userByUserIdFrom) {
        this.userByUserIdFrom = userByUserIdFrom;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_to", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUserByUserIdTo() {
        return userByUserIdTo;
    }

    public void setUserByUserIdTo(User userByUserIdTo) {
        this.userByUserIdTo = userByUserIdTo;
    }
}
