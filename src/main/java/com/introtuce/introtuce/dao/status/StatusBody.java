package com.introtuce.introtuce.dao.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusBody implements Serializable {


    private int userId;

    private String description;

    private String mediaUrl;

}
