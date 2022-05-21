package com.waracle.cakemgr.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repo.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
public class CakeUtils{

    @Autowired
    CakeRepository cakeRepository;

    public void readAllCakes() throws Exception {
        System.out.println("init started");

        System.out.println("downloading cake json");
        try (InputStream inputStream = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json").openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                line = reader.readLine();
            }

            System.out.println("parsing cake json");
            JsonParser parser = new JsonFactory().createParser(buffer.toString());
            if (JsonToken.START_ARRAY != parser.nextToken()) {
                throw new Exception("bad token");
            }

            JsonToken nextToken = parser.nextToken();
            while(nextToken == JsonToken.START_OBJECT) {
                System.out.println("creating cake entity");

                CakeEntity cakeEntity = new CakeEntity();
                System.out.println(parser.nextFieldName());
                cakeEntity.setTitle(parser.nextTextValue());

                System.out.println(parser.nextFieldName());
                cakeEntity.setDescription(parser.nextTextValue());

                System.out.println(parser.nextFieldName());
                cakeEntity.setImage(parser.nextTextValue());

                try {
                    System.out.println("adding cake entity");
                    cakeRepository.save(cakeEntity);
                }
                catch (DataIntegrityViolationException  dve) {
                    System.out.println("DataIntegrityViolationException exception" +  dve);
                }

                nextToken = parser.nextToken();
                System.out.println(nextToken);

                nextToken = parser.nextToken();
                System.out.println(nextToken);
            }

        } catch (Exception ex) {
            throw ex;
        }
        System.out.println("init finished");
    }

   public StringBuffer showAllCakesList() {

        List<CakeEntity> list = cakeRepository.findAll();
        StringBuffer sb = new StringBuffer();
       int count = 1;

       sb.append("<html>" +
               "<body>" +
               "<p>Cake List:</p>"+
               "<table border ='1'>" +
               "<tr>" +
               "<td>Cake No</td>" +
               "<td>Image</td>" +
               "<td>Name</td>" +
               "<td>Description</td>" +
               "</tr>");

       for (CakeEntity entity : list) {
           sb.append("<tr><td>");
           sb.append(count);
           sb.append("</td><td>");
           sb.append("<img src="+ entity.getImage() +" width=\"50\" height=\"50\">");
           sb.append("</td><td>");
           sb.append(entity.getTitle());
           sb.append("</td><td>");
           sb.append(entity.getDescription());
           sb.append("</td></tr>");
           count++;
       }
       sb.append("</table>" +
               "</body>" +
               "</html>");

        return sb;
    }

    public StringBuffer showSearchedCake(CakeEntity cakeEntity) {

        StringBuffer sb = new StringBuffer();
        int count = 1;

        sb.append("<html>" +
                "<body>" +
                "<p>Cake List:</p>"+
                "<table border ='1'>" +
                "<tr>" +
                "<td>Cake No</td>" +
                "<td>Image</td>" +
                "<td>Name</td>" +
                "<td>Description</td>" +
                "</tr>");

            sb.append("<tr><td>");
            sb.append(count);
            sb.append("</td><td>");
            sb.append("<img src="+ cakeEntity.getImage() +" width=\"50\" height=\"50\">");
            sb.append("</td><td>");
            sb.append(cakeEntity.getTitle());
            sb.append("</td><td>");
            sb.append(cakeEntity.getDescription());
            sb.append("</td></tr>");

        sb.append("</table>" +
                "</body>" +
                "</html>");

        return sb;
    }

}
