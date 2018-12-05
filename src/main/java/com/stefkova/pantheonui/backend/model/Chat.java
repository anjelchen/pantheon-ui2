package com.stefkova.pantheonui.backend.model;

import lombok.Data;

import java.util.Date;

@Data
public class Chat {

    private String fromUser;
    private String toUser;
    private Date date;
    private String message;

}
