package com.aca.obiee;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
public class DecryptBaseFile {

    public DecryptBaseFile() {

        super();

    }

    public static void main(String args[]) {

        BufferedReader br = null;

        FileReader fr = null;


        try {
            if(args.length < 2){
                System.out.println("Please enter input and output files in command line arguments to execute this jar");
                System.exit(1);
            }

            if(args[0] == null ){
                System.out.println("Please enter input file location");
                System.exit(1);
            }
            if(args[1] == null){
                System.out.println("Please enter output file location");
                System.exit(1);
            }

            File input =  new File(args[0]);
            File output = new File(args[1]);
            fr = new FileReader(input);

            br = new BufferedReader(fr);



            StringBuffer fileData = new StringBuffer();

            String sCurrentLine;



            while ((sCurrentLine = br.readLine()) != null) {

                fileData.append(sCurrentLine);

            }

            System.out.println("Reading data done from file " + input.getAbsolutePath());
            Pattern p = Pattern.compile("<reportBytes>(\\S+)</reportBytes>");
            Matcher m = p.matcher(fileData);
            FileOutputStream os = new FileOutputStream(output);

            if(m.find()){
               byte[] rbytes = Base64.decodeBase64(m.group(1).toString().getBytes());
                os.write(rbytes);
            }



            os.close();


            System.out.println("Output file created successfully at "+output.getAbsolutePath());

        } catch (IOException ie){

            System.out.println(ie.getMessage());

        }
        catch (Exception e) {

            e.printStackTrace();

            System.out.println("Error:Kindly contact admin");

            System.out.println(e.getMessage());

        }

    }

}