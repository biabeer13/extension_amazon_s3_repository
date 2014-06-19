package org.eobjects.datacleaner.repository.s3;

import com.amazonaws.services.s3.AmazonS3Client;

public class AmazonS3Repository extends AmazonS3RepositoryFolder {

    private final AmazonS3Client _client;

    public AmazonS3Repository(AmazonS3Client client) {
        _client = client;
    }

    public AmazonS3Client getClient() {
        return _client;
    }
    
}
