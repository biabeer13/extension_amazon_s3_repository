package org.eobjects.datacleaner.repository.s3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AmazonS3RepositoryTest extends TestCase {

	private static final String ACCESS_KEY = "NE7YIAXSWBO8UEINUSYF";
	private static final String SECRET_KEY = "PICc90ariL1I640_UcMu3kuIZkNs0zwlZxFjlA==";

	private AmazonS3Repository _repo;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		final AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY,
				SECRET_KEY);
		final ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setPreemptiveBasicProxyAuth(true);

		final S3ClientOptions options = new S3ClientOptions();
		options.setPathStyleAccess(true);

		final AmazonS3Client client = new AmazonS3Client(credentials,
				clientConfiguration);
		client.setS3ClientOptions(options);

		client.setEndpoint("http://localhost:8080");

		_repo = new AmazonS3Repository(client);
	}

	public void testConnect() throws Exception {
		AmazonS3Client client = _repo.getClient();
		List<Bucket> buckets = client.listBuckets();
		assertNotNull(buckets);

		Bucket bucket = buckets.get(0);
		assertEquals("testbucket", bucket.getName());

		boolean createStuff = true;
		if (createStuff) {
			// create file
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(1);
			client.putObject(bucket.getName(), "foodir/bardir/foo",
					new ByteArrayInputStream(new byte[1]), metadata);
			
			client.putObject(bucket.getName(), "foodir/barfile",
					new ByteArrayInputStream(new byte[1]), metadata);
			
			client.putObject(bucket.getName(), "foodir/bazfile",
					new ByteArrayInputStream(new byte[1]), metadata);
			
			client.putObject(bucket.getName(), "foodir/foofile",
					new ByteArrayInputStream(new byte[1]), metadata);
			
			client.putObject(bucket.getName(), "foodir/foodir/bar",
					new ByteArrayInputStream(new byte[1]), metadata);
		}
		
		System.out.println("List stuff under root dir:");
		
		ListObjectsRequest req = new ListObjectsRequest();
		req.setDelimiter("/");
		req.setPrefix("");
		req.setBucketName(bucket.getName());

		runListing(client, req);
		
System.out.println("List stuff under /foodir:");
		
		req = new ListObjectsRequest();
		req.setDelimiter("/");
		req.setPrefix("foodir/");
		req.setBucketName(bucket.getName());

		runListing(client, req);
	}

	private void runListing(AmazonS3Client client, ListObjectsRequest req) {
		ObjectListing listObjects = client.listObjects(req);
		while (listObjects != null) {
			List<S3ObjectSummary> objectSummaries = listObjects
					.getObjectSummaries();
			for (S3ObjectSummary objectSummary : objectSummaries) {
				String key = objectSummary.getKey();
				System.out.println("key: " + key + " - "
						+ objectSummary.getStorageClass());
			}
			if (listObjects.isTruncated()) {
				listObjects = client.listNextBatchOfObjects(listObjects);
			} else {
				listObjects = null;
			}
		}
	}

}
