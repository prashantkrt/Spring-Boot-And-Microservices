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
    private String[] toEmails; // this value will be given through the postman api
}

//{
//        "toEMail":"yourMail@gmail.com",
//        "subject":"Testing my application",
//        "messageBody": "<b>Hello</b> This is just a sample message which is sent to you as a test result!!  \n\n Please ignore the same ",
//        "attachment":"No",
//        "toEmails": [
//        "yourMail1@gmail.com",
//        "yourMail2@gmail.com"
//        ]
//}


// with attachment
//{
//        "toEMail":"youremail@gmail.com",
//        "subject":"Testing my application",
//        "messageBody": "Hello This is just sample message which is been sent to you as a test result Please ignore the same ",
//        "attachment":"/path/fileName.pdf",
//        "toEmails":[
//        "youremail1@gmail.com",
//        "youremail2@gmail.com"
//        ]
//        }
