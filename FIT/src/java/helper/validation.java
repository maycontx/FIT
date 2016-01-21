/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago
 */
public class validation {

    //Variáveis 
    private String name;
    private String aftername;
    private String email;
    private String password;
    private String repassword;
    private String birthdate;
    private String sex;
    //

    public validation() {
    }

    public boolean validationRegister() {

        // Variável de controle de erros
        boolean error = false;
        //

        //Validação dos dados
        if (name.equals("") || name.trim().length() < 3 || name.trim().length() > 20) {
            error = true;
        }

        if (aftername.equals("") || aftername.trim().length() < 3 || aftername.trim().length() > 20) {
            error = true;
        }

        if (email.equals("")) {
            error = true;
        } else {
            String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            boolean ok = email.matches(regex);
            if (!ok) {
                error = true;
            }
        }

        if (password.trim().length() < 6) {
            error = true;
        }

        if (!password.equals(repassword)) {
            error = true;
        }

        //Validação data de nascimento
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(birthdate);
            if (df.parse(birthdate).after(new Date())) {
                error = true;
            }
        } catch (ParseException ex) {
            error = true;
        }
        
        if ( sex != null ){
            if ( !sex.equals("M") && !sex.equals("F") ){
                error = true;
            }            
        }else
            error = true;
        
        
        return error;
    }

    public Date convertDate(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
       return new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAftername() {
        return aftername;
    }

    public void setAftername(String aftername) {
        this.aftername = aftername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    

}
