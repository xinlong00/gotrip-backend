package com.ytzl.gotrip;
import com.ytzl.gotrip.bean.Hotel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SolrAutoConfiguration.class})
public class HotelSearchApp {

    @Value("${solr.url}")
    private String solrUrl;

    @Bean
    public BaseQuery<Hotel> hotelQuery(){
        return new BaseQuery<>(solrUrl);
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelSearchApp.class,args);
    }
}
