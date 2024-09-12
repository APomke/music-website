package com.lidong.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseVO {
    private String status;
    private Integer code;
    private String info;
    private Object data;
}
