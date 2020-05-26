package com.company;

import java.net.InetAddress;
import lombok.Setter;
import lombok.Getter;

@Setter @Getter
public class Result {

    private InetAddress host;
    private Integer openPort;

}