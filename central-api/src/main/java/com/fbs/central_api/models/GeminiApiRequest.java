package com.fbs.central_api.models;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeminiApiRequest {
    private List<RequestContent> contents;
}
