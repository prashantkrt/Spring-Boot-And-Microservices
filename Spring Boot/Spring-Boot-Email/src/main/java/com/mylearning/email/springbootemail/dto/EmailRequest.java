package com.mylearning.email.springbootemail.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmailRequest {
    private String toEMail;
    private String subject;
    private String messageBody;
    private String attachment;
    private String[] toEmails;
}
