package com.mylearning.actuator.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ProdRelease {
    private String crq;// change request number
    private String releaseDt;
    private List<String> features;
}
