package com.aradnab.boot.general.model;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {
    int id;
    int categoryId;
    String category;
    String message;
    Date sentAt;
    Date viewedAt;
    int messageStatusId;
    String messageStatus;
    int read;
    int status;

    public static MessageModel entityToModel(Message message) {
        return new MessageModel(message.getId(), message.getMessageCategoryId(),
                message.getMessageCategoryByMessageCategoryId().getCategory(),
                message.getMessage(), message.getSendTime(), message.getViewedTime(), message.getMessageStatusId(),
                message.getMessageStatusByMessageStatusId().getStatus(), message.getIsRead(), message.getStatus());
    }

    public static List<MessageModel> entityToModel(List<Message> messages) {
        List<MessageModel> l = new ArrayList<>();
        messages.forEach(m -> {
            if (m.getStatus()== Status.LIVE_ACTIVE_STATUS){
                l.add(entityToModel(m));
            }
        });
        return l;
    }

    public static List<MessageModel> entityToModel(Collection<Message> messages) {
        List<MessageModel> l = new ArrayList<>();
        messages.forEach(m -> {
            if (m.getStatus()== Status.LIVE_ACTIVE_STATUS){
                l.add(entityToModel(m));
            }
        });
        return l;
    }

}
