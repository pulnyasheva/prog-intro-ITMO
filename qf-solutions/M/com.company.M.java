package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class M {

    public static void main(String[] args) {
        int t = 0;
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        for (int p=0; p<t; p++){
            int n=0;
            n= sc.nextInt();
            int[] nInt = new int[n];
            for(int j=0; j<n; j++){
                nInt[j]= sc.nextInt();
            }
            int cnt = 0;
            Map<Integer, Integer> dict = new HashMap<>();
            for(int j=n-1; j>-1; j--){
                for (int i=0; i<j; i++){
                    cnt += dict.getOrDefault(2*nInt[j]-nInt[i],0);
                }
                if (!dict.containsKey(nInt[j])) {
                    dict.put(nInt[j], 1);
                } else {
                    int count = dict.get(nInt[j]);
                    dict.put(nInt[j], count + 1);
                }
            }
            System.out.println(cnt);
        }
    }
}
