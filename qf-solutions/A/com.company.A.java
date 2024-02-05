package com.company;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int n = 0;
        int ans;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        n = sc.nextInt();
        if ((n-b)%(b-a)>0){
            ans = ((n-b)/(b-a)+1)*2+1;
        } else {
            ans = ((n-b)/(b-a))*2+1;
        }
        System.out.println(ans);
    }
}
