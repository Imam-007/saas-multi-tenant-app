package com.saas.responses;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private String id;
    private String name;
    private String description;
}
