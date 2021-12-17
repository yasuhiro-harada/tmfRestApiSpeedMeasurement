package com.example.tmfbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.Calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.lang.Runtime;

@SpringBootApplication
public class TmfbatchApplication {

	public static void main(String[] args) {

		testCalender();

		try{
			initLogs();

			int iMax = 100000000;
			//iMax = 10;
			int i = 8673500;
			Runtime r = Runtime.getRuntime();
			OkHttpClient client = new OkHttpClient().newBuilder().build();

			for(; i < iMax; i++){

				long startTime = 0;
				long endTime = 0;
				int elapsedTime = 0;
				Request request = null;

				// HTTP Clientの作成
				request = createPostRequest(i);
				//開始タイムスタンプ（処理前の時刻）を取得
				startTime = System.currentTimeMillis();
				// Request送信処理
				sendRequest(client, request);
				//終了タイムスタンプ（処理後の時刻）を取得
				endTime = System.currentTimeMillis();
				elapsedTime = (int)(endTime - startTime);

				// ログファイル出力
				outLogs(i, "POST", elapsedTime);

				if(i % 5 == 0){
					// HTTP Clientの作成
					request = createGetRequest(i);
					//開始タイムスタンプ（処理前の時刻）を取得
					startTime = System.currentTimeMillis();
					// Request送信処理
					sendRequest(client, request);
					//終了タイムスタンプ（処理後の時刻）を取得
					endTime = System.currentTimeMillis();
					elapsedTime = (int)(endTime - startTime);

					// ログファイル出力
					outLogs(i, "GET", elapsedTime);

					if(i % 1000 == 0){
						r.gc();
						System.console().printf("%d\r\n", i);
					}
				}
			}
		
			SpringApplication.run(TmfbatchApplication.class, args);

			}catch(Exception ex){
				System.console().printf(ex.getMessage());
			}
	}

	private static Request createGetRequest(int i) throws Exception
	{

	  	// Requestの作成
	  	return new Request.Builder()
		.url("http://localhost:80/v2/trn_riskpoint/" + String.format("%d", i))
		.method("GET", null)
		.build();
	}

	private static Request createPostRequest(int i) throws Exception
	{

	  	// Bodyの作成
		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
		.addFormDataPart("caseManagementId", String.format("%d", i))
		.addFormDataPart("title","Title")
		.addFormDataPart("bodyText","bodyText")
		.addFormDataPart("explainArgA", String.format("%d", i))
		.addFormDataPart("riskpoint", String.format("%d", i))
		.addFormDataPart("CreateUser","")
		.addFormDataPart("CreateDate","")
		.addFormDataPart("UpdateUser","")
		.addFormDataPart("UpdateDate","")
		.build();
		
	  	// Requestの作成
	  	return new Request.Builder()
		.url("http://localhost/v2/trn_riskpoint")
		.method("POST", body)
		.build();
	}

	private static void sendRequest(OkHttpClient client, Request request) throws Exception
	{
	  	// Request送信
		Response response = client.newCall(request).execute();	
		// client.newCall(request).execute();
		response.close();
	}

	private static void initLogs() throws Exception
	{
		initLog(5, "POST");
		initLog(1000, "POST");
		initLog(5, "GET");
		initLog(1000, "GET");
	}

	private static void initLog(int j, String method) throws Exception
	{
		// ヘッダの設定
		String header = "time,method,number of record,elapsed time";
		
		// ログファイルオープン
		FileWriter f = new FileWriter(".\\" + method + "_" + String.format("%06d", j) + ".csv", false);
		PrintWriter p = new PrintWriter(new BufferedWriter(f));
		// ヘッダ出力
		p.print(header);
		p.println();
		p.close();
		f.close();
	}

	private static void outLogs(int i, String method, int elapsedTime) throws Exception
	{
		if(i % 5 == 0){
			outLog(i, 5, method, elapsedTime);
		}
		if(i % 1000 == 0){
			outLog(i, 1000, method, elapsedTime);
		}
	}

	private static void outLog(int i, int j, String method, int elapsedTime) throws Exception
	{
		
		// ログファイルオープン
		FileWriter f = new FileWriter(".\\" + method + "_" + String.format("%06d", j) + ".csv", true);
		PrintWriter p = new PrintWriter(new BufferedWriter(f));
		// 現在時刻出力
		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);
		p.print(fdate1);
		p.print(",");
		// method出力
		p.print(method);
		p.print(",");
		// レコード件数出力
		p.print(String.format("%d", i));
		p.print(",");
		// 経過時間
		p.print(String.format("%d", elapsedTime));
		p.println();
		// ログファイルクローズ
		p.close();
		f.close();
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
