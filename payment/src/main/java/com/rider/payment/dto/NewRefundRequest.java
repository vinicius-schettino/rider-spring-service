package com.rider.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class NewRefundRequest {
    @NotNull
    private UUID paymentId;

    @NotNull
    private String riderName;

    @NotNull
    private String userName;
}
