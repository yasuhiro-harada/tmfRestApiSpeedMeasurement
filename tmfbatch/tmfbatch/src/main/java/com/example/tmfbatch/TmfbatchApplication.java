package com.example.tmfbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.io.File;

import com.excel.ReadGridExcel;

@SpringBootApplication
public class TmfbatchApplication {

	public static void main(String[] args) {

		testCalender();

		try{
			//File file = new File("C:\\Users\\yasu1\\workspace\\java\\tmfbatch\\tmfbatch\\src\\main\\aaa.txt");
			File file = new File(".\\src\\main\\aaa.txt");
			if(file.exists()){
				System.out.println("Exist!");
			}

			//ReadGridExcel readGridExcel = new ReadGridExcel("path", "sheetName", 1, 1);
		}catch(Exception ex){
			
		}

		SpringApplication.run(TmfbatchApplication.class, args);
	}

	private static void testCalender()
	{
		Calendar calender = Calendar.getInstance();
		Calendar calender01 = Calendar.getInstance();
		Calendar calender02 = Calendar.getInstance();
		Calendar calender03 = (Calendar)calender.clone();
		calender01.add(Calendar.MINUTE, 1);
		calender02.add(Calendar.MINUTE, -1);
		if(calender.compareTo(calender01) == 0){
			System.out.println("calender == calendar01");
		}
		else if(calender.compareTo(calender01) < 0){
			System.out.println("calender > calendar01");
		}
		else if(calender.compareTo(calender01) > 0){
			System.out.println("calender < calendar01");
		}
		if(calender.compareTo(calender02) == 0){
			System.out.println("calender == calender02");
		}
		else if(calender.compareTo(calender02) < 0){
			System.out.println("calender > calender02");
		}
		else if(calender.compareTo(calender02) > 0){
			System.out.println("calender < calender02");
		}
		if(calender.compareTo(calender03) == 0){
			System.out.println("calender == calender03");
		}
		else if(calender.compareTo(calender03) < 0){
			System.out.println("calender > calender03");
		}
		else if(calender.compareTo(calender03) > 0){
			System.out.println("calender < calender03");
		}
	}
}
