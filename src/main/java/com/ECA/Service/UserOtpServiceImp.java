package com.ECA.Service;

import com.ECA.Constant.Constant;
import com.ECA.Entity.UserDetails;
import com.ECA.Exception.BadRequestException;
import com.ECA.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.Random;

import static com.ECA.Constant.Constant.*;

@Service
public class UserOtpServiceImp implements UserOtpService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StringBuffer sendVerificationCode(String userName, Long mobileNumber) throws Exception {
        Long OTP;
        if (mobileNumber == null) {
            throw new BadRequestException(ENTER_MOBILE_NUMBER, HttpStatus.BAD_REQUEST);
        }
        if (userName == null) {
            throw new BadRequestException(ENTER_YOUR_NAME, HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = userDetailsRepository.findAllByMobileNumber(mobileNumber);
        if (userDetails != null && userDetails.getUserName() != null) {
            throw new BadRequestException(MOBILE_NUMBER_ALREADY_REGISTER, HttpStatus.BAD_REQUEST);
        }
        if (userDetails == null) {
            userDetails = new UserDetails();
        }
        Random random = new Random();
        OTP = (long) random.nextInt(NUMBER);
        StringBuffer stringBuffer = generateOtp(userName, mobileNumber, OTP);
        userDetails.setMobileNumber(mobileNumber);
        userDetails.setOtp(OTP);
        userDetailsRepository.save(userDetails);
        return stringBuffer;
    }

    private StringBuffer generateOtp(String userName, Long mobileNumber, Long OTP) throws IOException {
        String Message = URLEncoder.encode("Dear " + userName + " your OTP is " + OTP, "UTF-8");
        String route = Constant.OTP;
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
        if (!Objects.equals(userDetails.getOtp(), otp)) {
            throw new BadRequestException(WRONG_OTP, HttpStatus.BAD_REQUEST);
        }
        return SUCCESS;
    }

    @Override
    public StringBuffer forgetPassword(Long mobileNumber) throws BadRequestException, IOException {
        UserDetails userDetails = userDetailsRepository.findAllByMobileNumber(mobileNumber);
        if (userDetails == null) {
            throw new BadRequestException(NO_RECORD_FOUND, HttpStatus.BAD_REQUEST);
        } else if (userDetails.getUserName() == null) {
            throw new BadRequestException(REGISTER_DETAILS, HttpStatus.BAD_REQUEST);
        }
        Random random = new Random();
        Long OTP = (long) random.nextInt(NUMBER);
        userDetails.setOtp(OTP);
        userDetailsRepository.save(userDetails);
        return generateOtp(userDetails.getUserName(), mobileNumber, OTP);
    }

    @Override
    public String editPassword(Long mobileNumber, String oldPassword, String newPassword, String confirmPassword) throws BadRequestException {
        if(oldPassword == null){
            throw new BadRequestException(ENTER_OLD_PASSWORD,HttpStatus.BAD_REQUEST);
        }
        if(newPassword == null){
            throw new BadRequestException(ENTER_NEW_PASSWORD,HttpStatus.BAD_REQUEST);
        }
        if(confirmPassword == null){
            throw new BadRequestException(ENTER_CONFIRM_PASSWORD,HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails=userDetailsRepository.findAllByMobileNumber(mobileNumber);
        if(userDetails.getPassword().equals(oldPassword)){
            if(newPassword.equals(confirmPassword)){
                userDetails.setPassword(newPassword);
                userDetailsRepository.save(userDetails);
                return PASSWORD_SUCCESS;
            }else {
                throw new BadRequestException(OLD_PASSWORD_AND_NEW_PASSWORD_NOT_MATCH,HttpStatus.BAD_REQUEST);
            }
        }else {
            throw new BadRequestException(WRONG_OLD_PASSWORD,HttpStatus.BAD_REQUEST);
        }
    }
}

