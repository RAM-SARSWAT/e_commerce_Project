package com.ECA.Service;

import com.ECA.Entity.UserDetails;
import com.ECA.Enums.Status;
import com.ECA.Exception.BadRequestException;
import com.ECA.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import static com.ECA.Constant.Constant.*;
@Service
public class UserSendOtpServiceImp implements UserSendOtpService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StringBuffer sendVerificationCode(String userName, Long mobileNumber) throws Exception {
        int OTP;
        UserDetails userDetails = userDetailsRepository.findAllByMobileNumber(mobileNumber);
        if (userDetails.getUserName() != null) {
            throw new BadRequestException("This Mobile Number Is Already Register", HttpStatus.BAD_REQUEST);
        }
        Random random = new Random();
        OTP = random.nextInt(NUMBER);
        UserDetails userDetail=new UserDetails();
        userDetail.setMobileNumber(mobileNumber);
        userDetail.setOtp((long) OTP);
        userDetailsRepository.save(userDetail);
        String Message = URLEncoder.encode("Dear " + userName + " your OTP is " + OTP, "UTF-8");
        String route = "otp";
        String myURL = API_URL + API_KEY + "&variables_values=" + Message + "&route=" + route + "&numbers=" + mobileNumber;
        URL url = new URL(myURL);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("cache-control", "no-cache");
        con.getResponseCode();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            buffer.append(line);
        }
        reader.close();
        return buffer;
    }

    @Override
    public String checkVerificationCode(Long otp, Long mobileNumber) throws BadRequestException {
        UserDetails userDetails = userDetailsRepository.findAllByMobileNumber(mobileNumber);
        if(userDetails.getOtp()!=otp){
            throw new BadRequestException("Wrong OTP.",HttpStatus.BAD_REQUEST);
        }
        return "Success";
    }
}

