package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.support.AbstractClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.json.simple.JSONArray;

public class DayWiseData {
	
	
	public static void main(String[] args)
	{
	Settings settings = ImmutableSettings
			.settingsBuilder()
			.put("cluster.name","prophesee2")
			.build();


	TransportClient transportClient=new TransportClient(settings);
	transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost",9300));
    BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
    
    Map<String,Object> Document1 = new HashMap<String,Object>();
    Map<String,Object> Document2 = new HashMap<String,Object>();
    Map<String,Object> Document3 = new HashMap<String,Object>();
 
    Map<String,Object> like = new HashMap<String,Object>();
    Map<String,Object> share = new HashMap<String,Object>();
    Map<String,Object> comment = new HashMap<String,Object>();
    
    ArrayList<HashMap<String,Object>> group = new ArrayList<HashMap<String,Object>>();
 /*   
  Document1.put("id","1");
    
   
    
    String c="",date="";
    //System.out.println("group size---"+group.size());
    for(int i = 0;i<4;i++)
    {
    	System.out.println("enter the count-----");
    	Scanner input = new Scanner(System.in);
    	c=input.nextLine();
    	System.out.println("enter the date-----");
    	Scanner input1 = new Scanner(System.in);
    	date=input1.nextLine();
    	
    	HashMap<String, Object> map = new HashMap<String,Object>();
    	map.put("count", c);
    	map.put("date", date);
    	group.add(map);
    	
    }
    System.out.println("----document 1-----"+group);
    Document1.put("like",group);
    
    share.put("date","10 Jun 2015");
    share.put("count","5");
    Document1.put("share",share);
    
    comment.put("date","10 Jun 2015");
    comment.put("count","20");
    Document1.put("comment",comment);
    bulkRequest.add(
			transportClient
			.prepareIndex("demo", "fb","1")
		    .setSource(Document1));
    System.out.println("document 1 created");
    
    
Document2.put("id","2");
    
    like.put("date","10 Jun 2015");
    like.put("count","5");
    Document2.put("like",like);
    
    share.put("date","10 Jun 2015");
    share.put("count","8");
    Document2.put("share",share);
    
    comment.put("date","10 Jun 2015");
    comment.put("count","2");
    Document2.put("comment",comment);
    
    bulkRequest.add(
			transportClient
			.prepareIndex("demo", "fb","2")
		    .setSource(Document2));
    System.out.println("document 2 created");
    
Document3.put("id","3");
    
    like.put("date","10 Jun 2015");
    like.put("count","3");
    Document3.put("like",like);
    
    share.put("date","10 Jun 2015");
    share.put("count","4");
    Document3.put("share",share);
    
    comment.put("date","10 Jun 2015");
    comment.put("count","5");
    Document3.put("comment",comment);
    
    
    
   
    bulkRequest.add(
			transportClient
			.prepareIndex("demo", "fb","3")
		    .setSource(Document3));
    System.out.println("document 3 created");
	
	
	
if(bulkRequest.request().requests().size() == 0){
		
		System.out.println("\n\n No request Added!");
	
	} else{
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		
		if (bulkResponse.hasFailures()) {
		    System.out.println("ElasticSearch Failures: \n"+bulkResponse.buildFailureMessage());
		}
		
	}

*/
String id="";
System.out.println("enter the id-----");
Scanner input = new Scanner(System.in);
id = input.nextLine();

GetResponse getResponse = transportClient.prepareGet("demo","fb", id)
 .execute()
.actionGet();
//   Map<String, Object> source = getResponse.getSource();
ArrayList<HashMap<String, Object>> source = (ArrayList<HashMap<String, Object>>) getResponse.getSourceAsMap().get("like");
// String title = (String)getResponse.getField("title").getValue();
System.out.println("------------------------------");
System.out.println("Index: " + getResponse.getIndex());
System.out.println("Type: " + getResponse.getType());
System.out.println("Id: " + getResponse.getId());
System.out.println("Version: " + getResponse.getVersion());
System.out.println(source);



for(int j=0;j<source.size();j++)
{
	HashMap<String,Object> m = (HashMap<String,Object>)source.get(j);
	HashMap<String,Object> g=new HashMap<String,Object>();
	Set<String> key = m.keySet();
 Iterator it = key.iterator();
	 while (it.hasNext()) 
	 {
		 String ke = (String)it.next();
		 String value = (String) m.get(ke);

		 //  System.out.println("key "+ date +" & value "+count);
		 if(ke.equals("date"))
			 g.put("date",value);
		 else
		 {
			 g.put("count",value);
			 System.out.println("g-----------"+g);
			 group.add(g);
		 }

		 it.remove(); // avoids a ConcurrentModificationException
	 }
}
	String c="",date="";
	System.out.println("enter the count-----");
	Scanner input2 = new Scanner(System.in);
	c=input2.nextLine();
	System.out.println("enter the date-----");
	Scanner input3 = new Scanner(System.in);
	date=input3.nextLine();
	HashMap<String,Object> g1=new HashMap<String,Object>();
	g1.put("date",date);
	g1.put("count",c);
	System.out.println("g1-----------"+g1);
	group.add(g1);
	
	System.out.println("group-----------"+group);

 Document1.put("like",group);
 
 share.put("date","10 Jun 2015");
 share.put("count","5");
 Document1.put("share",share);
 
 comment.put("date","10 Jun 2015");
 comment.put("count","20");
 Document1.put("comment",comment);
 
 bulkRequest.add(
			transportClient
			.prepareIndex("demo", "fb","1")
		    .setSource(Document1));
 if(bulkRequest.request().requests().size() == 0){
		
		System.out.println("\n\n No request Added!");
	
	} else{
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		
		if (bulkResponse.hasFailures()) {
		    System.out.println("ElasticSearch Failures: \n"+bulkResponse.buildFailureMessage());
		}
		
	}
 System.out.println("document 1 created-----"+Document1);
 
 
 
 GetResponse getResponse1 = transportClient.prepareGet("demo","fb", id)
		 .execute()
		.actionGet();
		//   Map<String, Object> source = getResponse.getSource();
		ArrayList<HashMap<String, Object>> source1 = (ArrayList<HashMap<String, Object>>) getResponse1.getSourceAsMap().get("like");
		// String title = (String)getResponse.getField("title").getValue();
		System.out.println("------------------------------");
		System.out.println("Index: " + getResponse.getIndex());
		System.out.println("Type: " + getResponse.getType());
		System.out.println("Id: " + getResponse.getId());
		System.out.println("Version: " + getResponse.getVersion());
		System.out.println(source1);


	}        
	}

