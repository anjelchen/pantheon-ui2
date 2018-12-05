package com.stefkova.pantheonui.backend.service;

import com.stefkova.pantheonui.backend.model.Chat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final List<Chat> chats;

    public ChatService() {
        this.chats = new ArrayList<>();
    }

    public List<Chat> getChats(final String fromUser, final String toUser) {
        return this.chats.stream().filter(chat -> (chat.getFromUser().equals(fromUser) && chat.getToUser().equals(toUser))
                || (chat.getToUser().equals(fromUser) && chat.getFromUser().equals(toUser))).collect(Collectors.toList());
    }

    public void addChat(Chat chat) {
        chat.setDate(new Date());
        this.chats.add(chat);
    }
}
