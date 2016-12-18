package com.gk.aws;

import java.io.File;
import java.util.UUID;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Author:- Gaurav Karale
 * Date :- 17-12-2016
 * */
public class AWSS3Demo {
	String bucketName = "first-s3-bucket-" + UUID.randomUUID();
    String keyName = "MyObject";
	private static String uploadFileName="upload/index.html";
	String deleteFileName;
	
	public static void main(String[] args){
		
		AWSCredentials credentials = null;
            credentials = new ProfileCredentialsProvider().getCredentials();
		AWSS3Demo awsDemo= new AWSS3Demo(); 
		awsDemo.uploadFile(credentials);
	        
	}
	
	void uploadFile(AWSCredentials credentials){
		try {
			AmazonS3 s3client = new AmazonS3Client(credentials);
			File file = new File(uploadFileName);

		       
		    System.out.println("Creating bucket " + bucketName + "\n");
		    s3client.createBucket(bucketName);
		    System.out.println("file:"+file.getAbsolutePath());
			s3client.putObject(new PutObjectRequest(
					                 bucketName, keyName, file));
		} catch (AmazonServiceException e) {
			System.out.println("Excpetion : "+e.getMessage());
			System.out.println("Service name : "+e.getServiceName());
			System.out.println("Error type: "+e.getErrorType());
		}
	}

	void deleteFile(AWSCredentials credentials){
		try {
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			
			s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
		} catch (AmazonServiceException e) {
			System.out.println("Excpetion : "+e.getMessage());
			System.out.println("Service name : "+e.getServiceName());
			System.out.println("Error type: "+e.getErrorType());
		}
	}
}
