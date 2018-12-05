package com.stefkova.pantheonui.backend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final int CLEANUP_OFFSET = 30; // in minutes

    private final Map<String, LocalDateTime> users;

    public UserService() {
        this.users = new ConcurrentHashMap<>();
    }

    public Set<String> getUsers(String user) {
        return this.users.keySet().stream().filter(u -> !u.equals(user)).collect(Collectors.toSet());
    }

    public void addUser(String user) {
        this.users.put(user, LocalDateTime.now());
    }

    public void removeUser(String user) {
        this.users.remove(user);
    }

    public void updateUser(String user) {
        this.addUser(user);
    }

    @Scheduled(fixedDelay = CLEANUP_OFFSET * 60 * 1000)
    private void cleanUp() {
        this.users.entrySet().removeIf(stringDateEntry -> stringDateEntry.getValue().plusMinutes(CLEANUP_OFFSET).isBefore(LocalDateTime.now()));
    }
}
