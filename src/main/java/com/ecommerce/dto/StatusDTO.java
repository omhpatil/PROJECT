package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDTO {
    private String status;

    public StatusDTO() { }

    public StatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
