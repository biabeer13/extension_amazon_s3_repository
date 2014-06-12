package org.eobjects.datacleaner.repository.s3;

import java.util.List;

import junit.framework.TestCase;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class AmazonS3RepositoryTest extends TestCase {

    private static final String ACCESS_KEY = "NE7YIAXSWBO8UEINUSYF";
    private static final String SECRET_KEY = "PICc90ariL1I640_UcMu3kuIZkNs0zwlZxFjlA==";
    
    private AmazonS3Repository _repo;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        final AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        final ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setPreemptiveBasicProxyAuth(true);
        
        final AmazonS3Client client = new AmazonS3Client(credentials, clientConfiguration);
        client.setEndpoint("http://localhost:8080");
        
        _repo = new AmazonS3Repository(client);
    }

    public void testConnect() throws Exception {
        AmazonS3Client client =  _repo.getClient();
        List<Bucket> buckets = client.listBuckets();
        assertNotNull(buckets);
    }
    
}
