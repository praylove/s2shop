package com.x_s.s2shop;

import org.junit.Test;

import java.util.Scanner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class S2shopApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int floor = (int) Math.floor(Math.sqrt(n));
		System.out.println(floor*floor);
	}

}
