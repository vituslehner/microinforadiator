/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class Test {
    public static void main(String[] args) throws SocketException {
        for(NetworkInterface i : NetworkInterface.getNetworkInterfaces()){
            System.out.println(i.toString());
        }
    }
}