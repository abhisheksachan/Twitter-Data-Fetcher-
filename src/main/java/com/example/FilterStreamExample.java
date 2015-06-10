/**
 * Copyright 2013 Twitter, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.example;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import twitter4j.HashtagEntity;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserMentionEntity;


public class FilterStreamExample {

  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
    // add some track terms
    endpoint.trackTerms(Lists.newArrayList("#PM","#Obama","#India"));

    
    
    Settings settings = ImmutableSettings
			.settingsBuilder()
			.put("cluster.name","prophesee2")
			//.put("client.transport.sniff", true)
			.build();
    TransportClient transportClient = new TransportClient(settings);
    transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost",9300));
    BulkRequestBuilder bulkRequest = transportClient.prepareBulk(); 
   ElasticDemo ed=new ElasticDemo();
   // test ed = new test();
    
    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
    // Authentication auth = new BasicAuth(username, password);

   
   
       
    File file = new File("/usr/local/elastic.txt");
    
    if (!file.exists()) {
        try {
			file.createNewFile();
			 FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		   	   BufferedWriter bw = new BufferedWriter(fw);
				bw.write("[");
				bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // Create a new BasicClient. By default gzip is enabled.
    Client client = new ClientBuilder()
            .hosts(Constants.STREAM_HOST)
            .endpoint(endpoint)
            .authentication(auth)
            .processor(new StringDelimitedProcessor(queue))
            .build();

    // Establish a connection
    client.connect();

    // Do whatever needs to be done with messages
    for (int msgRead = 1; msgRead <=30000;msgRead++ ) {
      String msg = queue.take();
      char s= msg.charAt(2);
    		  if(s!='d')
    			  {System.out.println(msg);
    			  System.out.println(msgRead);}
    		  else
    		  {continue;}
    		  
   ed.store(msg,msgRead,bulkRequest,transportClient);	  
      
      //es.input(msg);

      
   try {
   	   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
   	   BufferedWriter bw = new BufferedWriter(fw);
		bw.write(msg+",");
		bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
    }
    
    FileWriter fw;
	try {
		fw = new FileWriter(file.getAbsoluteFile(),true);
		 BufferedWriter bw = new BufferedWriter(fw);
			bw.write("]");
			bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
    
    client.stop();
    
  }

//  public static void main(String[] args) {
//    try {
//      FilterStreamExample.run(args[0], args[1], args[2], args[3]);
//    } catch (InterruptedException e) {
//      System.out.println(e);
//    }
//  }
  
  public static void main(String[] args) {
	    try {
	      FilterStreamExample.run("TqCazYQVea4v0DZb8m00U4xGP","Sc6yLJJBGXxE131CScIc9e2HpVck6CDuvhm7dLBO6Gegt8IQoU", "626474493-WzUsI7QOfHQsxWjMS3CDUauehrZTmnDpoHYflcQm","rDtL32g8qaFuwpPUcEmYed4f9WDYEhqerw7uchPkGGSe3");
	    } catch (InterruptedException e) {
	      System.out.println(e);
	    }
	  }
	
}
