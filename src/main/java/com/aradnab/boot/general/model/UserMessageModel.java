package com.aradnab.boot.general.model;

import com.aradnab.boot.db_tier.entity.Message;
import com.aradnab.boot.db_tier.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageModel {
    UserModel receivedBy;
    UserModel sentBy;
    List<MessageModel> messageList = new ArrayList<>();

    public static UserMessageModel entityToModel(List<Message> messages, User sentBy, User receivedBy) {
        return new UserMessageModel(UserModel.entityToModel(receivedBy), UserModel.entityToModel(sentBy), MessageModel.entityToModel(messages));
    }

}
