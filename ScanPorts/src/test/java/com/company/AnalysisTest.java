package com.company;

import org.apache.commons.cli.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AnalysisTest {

    Analysis analysis = new Analysis();

    @Test
    public void testAnalysisArguments(){

        String[] source = { "scan",  "-h", "127.0.0.1-2,87.240.139.194", "-p", "80-82,631,443", "-t", "3"} ;

        assertEquals(3, analysis.analysisArguments(source));

    }

    @Test
    public void testAnalysisPort(){

        String sourceOne = "80-82,631,443";

        analysis.analysisPort(sourceOne);

        List<Integer> testPorts = new ArrayList<>();
        testPorts.add(631);
        testPorts.add(443);
        testPorts.add(80);
        testPorts.add(81);
        testPorts.add(82);

        assertEquals(testPorts, Scanner.getPorts());

    }

    @Test
    public void testAnalysisHost(){

        String source = "127.0.0.1-2,87.240.139.194-198,160.153.136.3";

        analysis.analysisHost(source);

        List<String> expected = new ArrayList<>();
        expected.add("160.153.136.3");
        expected.add("127.0.0.1");
        expected.add("127.0.0.2");
        expected.add("87.240.139.194");
        expected.add("87.240.139.195");
        expected.add("87.240.139.196");
        expected.add("87.240.139.197");
        expected.add("87.240.139.198");

        assertEquals(expected, Scanner.getHosts());

    }

}
