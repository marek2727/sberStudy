package com.company;

import java.net.InetAddress;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Setter;
import lombok.Getter;

@JsonAutoDetect
@Setter @Getter
public class Result {

    private InetAddress host;
    private Integer openPort;

}