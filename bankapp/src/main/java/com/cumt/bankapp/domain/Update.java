package com.cumt.bankapp.domain;

import lombok.Data;

@Data
public class Update {

    private String  oldpsw;
    private String  newpsw;
    private String  oldPaypsw;
    private String  newPaypsw;
    private String  userName;

}
