/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import static java.lang.Math.pow;


/**
 *
 * @author huhuh
 */


public class test{
    public static double tinhS(int n, int x) {
        if(n==1) return 1;
        return n*pow(x,n-1)+tinhS(n-1,x);
    }
    
    public static void main(String[] args) {
		System.out.println(tinhS(3, 2));
	}
}
