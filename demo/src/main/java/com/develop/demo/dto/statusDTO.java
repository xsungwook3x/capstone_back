package com.develop.demo.dto;

import com.develop.demo.StatusEnum;
import lombok.Data;

@Data
public class statusDTO {

    private StatusEnum status;
    private String message;
    private Object data;

    public statusDTO() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
