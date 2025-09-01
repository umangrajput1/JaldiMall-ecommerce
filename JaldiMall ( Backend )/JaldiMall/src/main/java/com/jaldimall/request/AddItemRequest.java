package com.jaldimall.request;

import lombok.Data;

@Data
public class AddItemRequest {
    private Long productId;
    private String size;
    private int quantity;
}
