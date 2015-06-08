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

//import com.google.common.collect.Multiset.Entry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.language.bm.Lang;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SampleStreamExample {

  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
    // Create an appropriately sized blocking queue
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

    // Define our endpoint: By default, delimited=length is set (we need this for our processor)
    // and stall warnings are on.
    StatusesSampleEndpoint endpoint = new StatusesSampleEndpoint();
    endpoint.stallWarnings(false);

    
   File file = new File("/usr/local/Output11.txt");
   
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
 
    
    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
    //Authentication auth = new com.twitter.hbc.httpclient.auth.BasicAuth(username, password);

    // Create a new BasicClient. By default gzip is enabled.
    BasicClient client = new ClientBuilder()
            .name("sampleExampleClient")
            .hosts(Constants.STREAM_HOST)
            .endpoint(endpoint)
            .authentication(auth)
            .processor(new StringDelimitedProcessor(queue))
            .build();

    // Establish a connection
    client.connect();

    // Do whatever needs to be done with messages
    for (int msgRead = 0; msgRead < 1000; msgRead++) {
      if (client.isDone()) {
        System.out.println("Client connection closed unexpectedly: " + client.getExitEvent().getMessage());
        break;
      }
      
      String msg = queue.poll(5, TimeUnit.SECONDS);
    //  String Time="time",Text="Text";
      //Lang id;
      if (msg == null) {
        System.out.println("Did not receive a message in 5 seconds");
      } else {
    	  
    	  System.out.println(msg); 
    	  //System.out.println("**************hahahahahahahah********************");
    	  
    	 

         
         try {
        	   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        	   BufferedWriter bw = new BufferedWriter(fw);
        	   
        	   if(msgRead==999)
			bw.write(msg);
        	   else
        		   bw.write(msg+",");
        		   
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          

    	  
    /*	  JSONParser jsonParser = new JSONParser();
    	 	//JsonElement jsonElement = null;
    	 	
    	 	String key="";
    	 try {
    		 //jsonElement= (JsonElement) jsonParser.parse(msg); 
			JSONObject jsonObject = (JSONObject) jsonParser.parse(msg);
			//JsonObject jsonObjec = jsonElement.getAsJsonObject();
			//for(Entry<String, JsonElement> entry : jsonObjec.entrySet())
		//	{  key = entry.getKey();
			//	if(key=="delete")
				//		System.out.println("this comment is deleted");
		//	}	
        	//JsonElement value = entry.getValue();
			
			//***** printing date
		//	Time = (String) jsonObject.get("created_at");
            System.out.println("Date of creation====: " + jsonObject.get("created_at"));
            //******printing id
          //   id = (Lang) jsonObject.get("id");
         //   System.out.println("id=========: " + jsonObject.get("id"));
            //*******text
             //Text = (String) jsonObject.get("text");
           //System.out.println("Text==========: " + jsonObject.get("text"));
            
            //************inside user************
            JSONObject structure = (JSONObject) jsonObject.get("user");
            System.out.println("Into user structure ,  id====: " + structure.get("id"));
            System.out.println("Into user structure ,  name====: " + structure.get("name"));
            System.out.println("Into user structure ,  screen_name====: " + structure.get("screen_name"));
            System.out.println("Into user structure ,  location====: " + structure.get("location"));
            System.out.println("Into user structure ,  description====: " + structure.get("description"));
            System.out.println("Into user structure ,  followers====: " + structure.get("followers_count"));
            System.out.println("Into user structure ,  friends====: " + structure.get("friends_count"));
            System.out.println("Into user structure ,  listed====: " + structure.get("listed_count"));
            System.out.println("Into user structure ,  favorite====: " + structure.get("favorites_count"));
            System.out.println("Into user structure ,  status_count====: " + structure.get("status_count"));
            System.out.println("Into user structure ,  created at====: " + structure.get("created at"));
            
            
            
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
       	*/
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

    // Print some stats
  System.out.printf("The client read %d messages!\n", client.getStatsTracker().getNumMessages());
  
    }

  public static void main(String[] args) {
    try {
      SampleStreamExample.run("TqCazYQVea4v0DZb8m00U4xGP","Sc6yLJJBGXxE131CScIc9e2HpVck6CDuvhm7dLBO6Gegt8IQoU", "626474493-WzUsI7QOfHQsxWjMS3CDUauehrZTmnDpoHYflcQm","rDtL32g8qaFuwpPUcEmYed4f9WDYEhqerw7uchPkGGSe3");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
