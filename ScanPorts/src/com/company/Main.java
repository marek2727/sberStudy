package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private int minPort = 1;
    private int maxPort = 0x10000;
    private String host = "192.168.1.104";
    private String minHost = "192.168.1.104";
    private String maxHost;
    private int timeout = 100;

    public void setMinHost(String minHost){
        this.minHost = minHost;
    }

    public void setMaxHost(String maxHost){
        this.maxHost = maxHost;
    }

    public String getMinHost() {
        return minHost;
    }

    public String getMaxHost(){
        return maxHost;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMinPort() {
        return minPort;
    }

    public void setMinPort(int minPort) {
        this.minPort = minPort;
    }

    public int getMaxPort() {
        return maxPort;
    }

    public void setMaxPort(int maxPort) {
        this.maxPort = maxPort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void k(String minHost, String maxHost){
        String[] mn = minHost.split("\\.");
        String[] mx = maxHost.split("\\.");
        int a = Integer.parseInt(mn[mn.length-1]);
        int b = Integer.parseInt(mx[mx.length-1]);
        mx[mx.length-1] = Integer.toString(a+1);
        System.out.print(a + "-");
        System.out.println(b);
        System.out.println(mx[mx.length-1]);

        for (int i = a; i <= b; i++) {
            mx[mx.length-1] = Integer.toString(i);
        }
    }

    public List<Integer> scan() {
        try {
            List<String> openHostsList = new ArrayList<String>(0xFF);
            openHostsList.add(getMinHost());
            System.out.println("Работает");
            InetAddress ia = InetAddress.getByName(getMinHost());
            System.out.println("Работает");
            return scan(ia);
        } catch (IOException ioe) {
            return null;
        }
    }

    List<Integer> scan(InetAddress inetAddress) {
        System.out.println("Host: " + minHost);
        List<Integer> openPortsList = new ArrayList<Integer>(0xFF);
        System.out.println("scanning ports: ");
        for (int port = minPort; port <= maxPort; port++) {
            System.out.print(port);
            try {
                InetSocketAddress isa = new InetSocketAddress(inetAddress,port);
                Socket socket = new Socket();
                socket.connect(isa,timeout);
                System.out.println(" opened");
                openPortsList.add(port);
                socket.close();
            } catch (IOException ioe) {
                System.out.println("");
            }
        }
        return openPortsList;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
            return;
        }

        String host = args[0];
        System.out.println("Scanning host "+ host);

        Main scanner = new Main();

        if (args.length==2) {
            if (args[1].indexOf("-")>-1) {
                // range of ports pointed out
                String[] ports = args[1].split("-");
                try {
                    int minPort = Integer.parseInt(ports[0]);
                    int maxPort = Integer.parseInt(ports[1]);
                    scanner.setMinPort(minPort);
                    scanner.setMaxPort(maxPort);
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong ports!");
                    return;
                }
            } else {
                // one port pointed out
                try {
                    int port = Integer.parseInt(args[1]);
                    scanner.setMinPort(port);
                    scanner.setMaxPort(port);
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong port!");
                    return;
                }
            }
        }

        if (args[0].indexOf("-") > -1){
            String[] hosts = args[0].split("-");
            String minHost = hosts[0];
            String maxHost = hosts[1];
            scanner.k(minHost,maxHost);
            scanner.setMinHost(minHost);
            scanner.setMaxHost(maxHost);
        } else {
            // one port pointed out
            String host1 = args[1];
            scanner.setMinHost(host1);
            scanner.setMaxHost(host1);
        }



        scanner.setHost(host);
        List<Integer> openPortsList = scanner.scan();
        if (openPortsList != null) {
            if (openPortsList.size() > 0) {
                System.out.println("List of opened ports:");
                for (Integer openedPort : openPortsList) {
                    System.out.println(openedPort);
                }
            } else {
                System.out.println("No opened ports!");
            }
        } else {
            System.out.println("Error happened!");
        }
    }

    static void usage() {
        System.out.println("Java Port Scanner usage: ");
        System.out.println("java PortScanner host port");
        System.out.println("Examples:");
        System.out.println("java PortScanner 192.168.1.100 1-1024");
        System.out.println("java PortScanner 192.168.1.100 1099");
        System.out.println("java PortScanner 192.168.1.100 (this scans all ports from 1 to 0x10000)");
    }
}
