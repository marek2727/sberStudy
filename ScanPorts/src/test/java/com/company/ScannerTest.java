package com.company;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScannerTest {

    private Scanner scanner = new Scanner();

    @Test
    public void testMainPorts(){

        String[] test = { "80-82", "631", "888-889", "5667" };

        scanner.mainPorts(test);

        List<Integer> expected = new ArrayList<>();
        expected.add(631);
        expected.add(5667);
        expected.add(80);
        expected.add(81);
        expected.add(82);
        expected.add(888);
        expected.add(889);

        assertEquals(expected, Scanner.getPorts());

    }

    @Test
    public void testScanPortsOne() {

        int a = 10;
        int b = 15;

        scanner.scanPorts(a, b);

        List<Integer> test = new ArrayList<>();
        test.add(10);
        test.add(11);
        test.add(12);
        test.add(13);
        test.add(14);
        test.add(15);

        assertEquals(test, Scanner.getPorts());

    }

    @Test
    public void testScanPortsTwo(){

        String[] test = { "562", "433", "8080" };

        scanner.scanPorts(test);

        List<Integer> expected = new ArrayList<>();
        expected.add(562);
        expected.add(433);
        expected.add(8080);

        assertEquals(expected, Scanner.getPorts());

    }

    @Test
    public void testScanPortsThree(){

        int test = 15;

        scanner.scanPorts(test);

        List<Integer> expected = new ArrayList<>();
        expected.add(15);

        assertEquals(expected, Scanner.getPorts());

    }

    @Test
    public void testScanHostsOne(){

        String test = "127.0.0.1";

        scanner.scanHosts(test);

        List<String> expected = new ArrayList<>();
        expected.add("127.0.0.1");

        assertEquals(expected, Scanner.getHosts());

    }

    @Test
    public void testScanHostsTwo(){

        String[] source = { "127.0.0.1", "127.0.0.2" };

        scanner.scanHosts(source);

        List<String> expected = new ArrayList<>();
        expected.add("127.0.0.1");
        expected.add("127.0.0.2");

        assertEquals(expected, Scanner.getHosts());

    }

    @Test
    public void testScanHostsThree(){

        String sourceMinHost = "127.0.0.1";
        String sourceMaxHost = "4";

        scanner.scanHosts(sourceMinHost, sourceMaxHost);

        List<String> expected = new ArrayList<>();
        expected.add("127.0.0.1");
        expected.add("127.0.0.2");
        expected.add("127.0.0.3");
        expected.add("127.0.0.4");

        assertEquals(expected, Scanner.getHosts());

    }

    @Test
    public void mainHosts(){

        String[] source = { "127.0.0.1", "127.0.0.3-5", "127.0.0.7" };

        scanner.mainHosts(source);

        List<String> expected = new ArrayList<>();
        expected.add("127.0.0.1");
        expected.add("127.0.0.7");
        expected.add("127.0.0.3");
        expected.add("127.0.0.4");
        expected.add("127.0.0.5");

        assertEquals(expected, Scanner.getHosts());

    }

    @Test
    public void startScan(){

        scanner.scanHosts("160.153.136.3");
        scanner.scanPorts(80);
        scanner.startScan(0);

        Pair<InetAddress, Integer> pair = null;

        try {
            pair = new Pair<>(InetAddress.getByName("160.153.136.3") , 80);
        } catch (UnknownHostException e) { }

        List<Pair<InetAddress, Integer>> test = new ArrayList<>();
        test.add(pair);

        assertEquals(test, Scanner.getResultOfScan());

    }

}
