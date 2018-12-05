package com.stefkova.pantheonui.backend.controller;

import com.stefkova.pantheonui.backend.model.Chat;
import com.stefkova.pantheonui.backend.service.ChatService;
import com.stefkova.pantheonui.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class ChatController {

    private ChatService chatService;
    private UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    /**
     * Get all chat messages between 2 users.
     *
     * @param fromUser user in conversation with toUser
     *
     * @param toUser user in conversation with fromUser
     *
     * @return collection of chat messages between 2 users
     */
    @GetMapping("/chats/{fromUser}/{toUser}")
    public List<Chat> getChats(@PathVariable String fromUser, @PathVariable String toUser) {
        this.userService.updateUser(fromUser); // update last action time of user
        return this.chatService.getChats(fromUser, toUser);
    }

    /**
     * Add a chat message between 2 users.
     *
     * @param chat chat message between 2 users
     *
     * @return ok when action succeeded
     */
    @PostMapping("/chat")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response addChat(@RequestBody Chat chat) {
        this.userService.updateUser(chat.getFromUser()); // update last action time of user
        this.chatService.addChat(chat);
        return Response.ok().build();
    }
}
