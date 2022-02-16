package com.ECA.Service;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

@Service
public class UserServiceImp implements UserService {
    @Override
    public StringBuffer sendVerificationCode(String user, Long mobileNumber)throws Exception {
        int OTP;
            Random random=new Random();
            OTP=random.nextInt(999999);
            String apiKey = "TU6eHOMrSz7Riln5PdypYVxbIsfZD9hoWwmaGJ4A3Bv0g8CK12suqbmP5M1aZxeIFRNYpJcw7ESh3UC8";
            String Message = URLEncoder.encode("Dear "+user+" your OTP is "+OTP, "UTF-8");
            String route="otp";
            String myURL="https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&variables_values="+Message+"&route="+route+"&numbers="+mobileNumber;
            URL url=new URL(myURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            con.setRequestProperty("cache-control","no-cache");
            con.getResponseCode();

            StringBuffer buffer=new StringBuffer();
            BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
            while (true){
                String line=reader.readLine();
                if(line==null){
                    break;
                }
                buffer.append(line);
            }
            reader.close();
            return buffer;
        }
    }

