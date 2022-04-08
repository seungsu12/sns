package code.sns.config.aws;

import code.sns.config.yml.YmlPropertySourceFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:cloud.yml", factory = YmlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class AwsConfig {

//    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey ="AKIAZ7DMM4CVE6CNRXHA";

//    @Value("{cloud.aws.credentials.secretKey}")
    private String secretKey ="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";

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client () {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,secretKey);
        return (AmazonS3Client)AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
