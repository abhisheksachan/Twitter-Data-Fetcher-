package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import static org.elasticsearch.node.NodeBuilder.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
public class test {
	
	public static void store(String msg,int num)
	{
		
		String number= String.valueOf(num);
		Settings settings = ImmutableSettings
				.settingsBuilder()
				.put("cluster.name","prophesee2")
				.build();
		
		TransportClient transportClient = new TransportClient(settings);
		
		//Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		
		
	 transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost",9300));		
		
		 BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
		 Map<String,Object> source = new HashMap<String,Object>();
		
		//final Node node = nodeBuilder().clusterName("prophesee").node();
		
	//	final Client client   = node.client();
		 
		 try {
				JSONObject jobject = new JSONObject(msg);
				//ResponseList<Status> mentions = twitter.getMentionsTimeline(paging);
				String created_at=(String) jobject.get("created_at");
				String id_str =  (String) jobject.get("id_str");
				String text=(String) jobject.get("text");
				String sourcee=(String) jobject.get("source");
				Boolean truncated =(Boolean) jobject.get("truncated");
				
				if(jobject.isNull("in_reply_to_status_id"))
			    {
			       jobject.put("in_reply_to_status_id", 0L);
			       
			    }
				long in_reply_to_status_id=(Long) jobject.get("in_reply_to_status_id");
				
				
				
				if(jobject.isNull("in_reply_to_status_id_str"))
			    {
			       jobject.put("in_reply_to_status_id_str", "null");
			       
			    }
				String in_reply_to_status_id_str=(String) jobject.get("in_reply_to_status_id_str");
				
				
				
				
				if(jobject.isNull("in_reply_to_user_id"))
			    {
			       jobject.put("in_reply_to_user_id",0L);
			       
			    }
				long in_reply_to_user_id=(Long) jobject.get("in_reply_to_user_id");
				
				
				
				if(jobject.isNull("in_reply_to_user_id_str"))
			    {
			       jobject.put("in_reply_to_user_id_str","null");
			       
			    }
				String in_reply_to_user_id_str=(String) jobject.get("in_reply_to_user_id_str");
				
				
				
				if(jobject.isNull("in_reply_to_screen_name"))
			    {
			       jobject.put("in_reply_to_screen_name", "null");
			       
			    }
				String in_reply_to_screen_name=(String) jobject.get("in_reply_to_screen_name");
				
				
	/*			System.out.println("created at*****"+created_at);	
				System.out.println("id_str*****"+id_str);
				System.out.println("text*****"+text);
				System.out.println("source*****"+sourcee);
				System.out.println("truncated*****"+truncated);
				System.out.println("in_reply_to_status_id*****"+in_reply_to_status_id);
				System.out.println("in_reply_to_status_id_str*****"+in_reply_to_status_id_str);
				System.out.println("in_reply_to_user_id*****"+in_reply_to_user_id);
				System.out.println("in_reply_to_user_id_str*****"+in_reply_to_user_id_str);
				System.out.println("in_reply_screen_name*****"+in_reply_to_screen_name);
	*/			
				
				JSONObject user =(JSONObject) jobject.get("user");
				
				//long u_id=(Long) user.get("id");
				String u_id_str=(String) user.get("id_str");
				String u_name=(String) user.get("name");
				String u_screen_name=(String) user.get("screen_name");
				String u_location=(String) user.get("location");
				
				if(user.isNull("url"))
			    {
			       user.put("url", "null");
			       
			    }
				String u_url=(String) user.get("url");
				
				if(user.isNull("description"))
			    {
			       user.put("description", "null");
			       
			    }
				String u_description=(String) user.get("description");
				Boolean u_protected=(Boolean) user.get("protected");
				Boolean u_verified=(Boolean) user.get("verified");
				int u_followers_count=(Integer) user.get("followers_count");
				int u_friends_count=(Integer) user.get("friends_count");
				int u_listed_count=(Integer) user.get("listed_count");
				int u_favourites_count=(Integer) user.get("favourites_count");
				int u_statuses_count=(Integer) user.get("statuses_count");
				String u_created_at=(String) user.get("created_at");
				
				if(user.isNull("utc_offset"))
			    {
			       user.put("utc_offset", 0);
			       
			    }
				int u_utc_offset=(Integer) user.get("utc_offset");
				
				if(user.isNull("time_zone"))
			    {
			       user.put("time_zone","null");
			       
			    }
				String u_time_zone=(String) user.get("time_zone");
				Boolean u_geo_enabled=(Boolean) user.get("geo_enabled");
				String u_lang=(String) user.get("lang");
				Boolean u_contributors_enabled=(Boolean) user.get("contributors_enabled");
				Boolean u_is_translator=(Boolean) user.get("is_translator");
				String u_p_b_color=(String) user.get("profile_background_color");
				String u_p_b_image_url=(String) user.get("profile_background_image_url");
				String u_p_b_image_url_https=(String) user.get("profile_background_image_url_https");
				Boolean u_p_b_tile=(Boolean) user.get("profile_background_tile");
				String u_p_link_color=(String) user.get("profile_link_color");
				String u_s_border_color=(String) user.get("profile_sidebar_border_color");
				String u_p_s_fill_color=(String) user.get("profile_sidebar_fill_color");
				String u_p_text_color=(String) user.get("profile_text_color");
				Boolean u_p_u_b_image=(Boolean) user.get("profile_use_background_image");
				String u_p_image_url=(String) user.get("profile_image_url");
				String u_p_image_url_https=(String) user.get("profile_image_url_https");
				//String u_p_banner_url=(String) user.get("profile_banner_url");
				Boolean u_d_profile=(Boolean) user.get("default_profile");
				Boolean u_d_profile_image=(Boolean) user.get("default_profile_image");
				
				if(user.isNull("following"))
			    {
			       user.put("following", "null");
			       
			    }
				String u_following=(String) user.get("following");
				
				if(user.isNull("follow_request_sent"))
			    {
			       user.put("follow_request_sent", "null");
			       
			    }
				String u_follow_request_sent=(String) user.get("follow_request_sent");
				
				
				if(user.isNull("notifications"))
			    {
			       user.put("notifications", "null");
			       
			    }
				String u_notifications=(String) user.get("notifications");
				
				
				//System.out.println("id*****"+u_id);	
/*				System.out.println("id_str*****"+u_id_str);
				System.out.println("name*****"+u_name);
				System.out.println("screenname*****"+u_screen_name);
				System.out.println("location*****"+u_location);
				System.out.println("url*****"+u_url);
				System.out.println("description*****"+u_description);
				System.out.println("protected*****"+u_protected);
				System.out.println("verified*****"+u_verified);
				System.out.println("followerscount*****"+u_followers_count);
				System.out.println("friendscount*****"+u_friends_count);
				System.out.println("listed_count*****"+u_listed_count);	
				System.out.println("favoritecount*****"+u_favourites_count);
				System.out.println("statuscount*****"+u_statuses_count);
				System.out.println("createdat*****"+u_created_at);
				System.out.println("utcoffset*****"+u_utc_offset);
				System.out.println("timezone*****"+u_time_zone);
				System.out.println("geoenabled*****"+u_geo_enabled);
				System.out.println("lang*****"+u_lang);
				System.out.println("contributorsenabled******"+u_contributors_enabled);
				System.out.println("translator*****"+u_is_translator);
				System.out.println("color*****"+u_p_b_color);
				System.out.println("url*****"+u_p_b_image_url);	
				System.out.println("https*****"+u_p_b_image_url_https);
				System.out.println("tile*****"+u_p_b_tile);
				System.out.println("color*****"+u_p_link_color);
				System.out.println("bordercolor*****"+u_s_border_color);
				System.out.println("fillcolor*****"+u_p_s_fill_color);
				System.out.println("textcolot****"+u_p_text_color);
				System.out.println("image*****"+u_p_u_b_image);
				System.out.println("url*****"+u_p_image_url);
				System.out.println("url*****"+u_p_image_url_https);
				System.out.println("profile*****"+u_d_profile);
				System.out.println("image*****"+u_d_profile_image);
				System.out.println("following*****"+u_following);
				System.out.println("request*****"+u_follow_request_sent);
				System.out.println("noti*****"+u_notifications);
	*/		
				
				if(jobject.isNull("geo"))
			    {
			       jobject.put("geo","null");
			       
			    }
				String geo=(String) jobject.get("geo");
				String cord="";
				
				if(jobject.isNull("coordinates"))
			    {
			       jobject.put("coordinates","null");
			        cord=(String) jobject.get("coordinates");
			      // System.out.println("coordinates**********"+cord);
			       
			    }
				else
				{
					JSONArray cor=(JSONArray) jobject.get("coordinates");
					//System.out.print("coordinates*****");
					 for(int i=0; i<((Collection<String>) cor).size(); i++)
					 {
						 long j=(Long) cor.get(i);
						 System.out.print(j+"   ");
						 
					 }

				}
				
				
/*
				if(jobject.isNull("place"))
			    {
			       jobject.put("place","null");
			       
			    }
				String place=(String) jobject.get("place");
	*/			

				if(jobject.isNull("contributors"))
			    {
			       jobject.put("contributors","null");
			       
			    }
				String contri=(String) jobject.get("contributors");
				
				int retweet =(Integer) jobject.get("retweet_count");
				int fav_count =(Integer) jobject.get("favorite_count");
				
				
				List<String> htext=new ArrayList<String>();
				List<String> url=new ArrayList<String>();
				List<String> expandedurl=new ArrayList<String>();
				List<String> displayurl=new ArrayList<String>();
				List<String> screen_name=new ArrayList<String>();
				List<String> name=new ArrayList<String>();
				List<String> uid_str=new ArrayList<String>();
				
				
				
				JSONObject entity =(JSONObject) jobject.get("entities");
				
				
				JSONArray hash=(JSONArray) entity.get("hashtags");
						int length = hash.length();
				  // take each value from the json array separately
				  
				for(int i=0;i<length;i++)
				{
					JSONObject innerObj = hash.getJSONObject(i);
				  
				  		String text1=(String) innerObj.get("text");
				  		htext.add(text1);
				  		//System.out.println("hashtags***********"+text1);
				          
				 }
				
				JSONArray ur=(JSONArray) entity.get("urls");
				int length1 = ur.length();
		  // take each value from the json array separately
		  
				for(int i=0;i<length1;i++)
				{
					JSONObject innerObj = ur.getJSONObject(i);
		  
		  		String text1=(String) innerObj.get("url");
		  		url.add(text1);
		  	//	System.out.println("url***********"+text1);
		  		
		  		String text2=(String) innerObj.get("expanded_url");
		  		expandedurl.add(text2);
		  		//System.out.println("expandedurl***********"+text2);
		  		
		  		String text3=(String) innerObj.get("display_url");
		  		displayurl.add(text3);
		  		//System.out.println("displayurl***********"+text3);
		  		
		          
				}
				
				JSONArray um=(JSONArray) entity.get("user_mentions");
				int length2 = um.length();
		  // take each value from the json array separately
		  
				for(int i=0;i<length2;i++)
				{
					JSONObject innerObj = um.getJSONObject(i);
		  
		  		String text1=(String) innerObj.get("screen_name");
		  		screen_name.add(text1);
		  		//System.out.println("screenname***********"+text1);
		  		
		  		String text2=(String) innerObj.get("name");
		  		name.add(text2);
		  	//	System.out.println("name***********"+text2);
		  		
		  		String text3=(String) innerObj.get("id_str");
		  		uid_str.add(text3);
		  		//System.out.println("id_str***********"+text3);
		  		
		          
				}
				
				Boolean favorited=(Boolean) jobject.get("favorited");
				Boolean retweeted=(Boolean) jobject.get("retweeted");
				Boolean p_sensitive=(Boolean) jobject.get("possibly_sensitive");
				String f_level=(String) jobject.get("filter_level");
				String lang=(String) jobject.get("lang");
				String timestamp=(String) jobject.get("timestamp_ms");
				

				
			/*		System.out.println("geo*****"+geo);	
			    	System.out.println("place*****"+place);
					System.out.println("contributions*****"+contri);
					System.out.println("retweet*****"+retweet);
					System.out.println("favorite count********"+fav_count);
					System.out.println("favorited*****"+favorited);	
			    	System.out.println("retweeeted*****"+retweeted);
					System.out.println("possiblysenstive*****"+p_sensitive);
					System.out.println("lang*****"+lang);
					System.out.println("timestamp********"+timestamp);
					
				*/
					
				
			
		
		 source=putJsonDocument(created_at,id_str,text,sourcee,truncated,in_reply_to_status_id,
				 in_reply_to_status_id_str, in_reply_to_user_id, in_reply_to_user_id_str,
				 in_reply_to_screen_name,u_id_str,u_name,u_screen_name,u_location,u_url
				 ,u_description,u_protected,u_verified,u_followers_count,u_friends_count,
				 u_listed_count,u_favourites_count,u_statuses_count,u_created_at,u_utc_offset,
				 u_time_zone,u_geo_enabled,u_lang,u_contributors_enabled,u_is_translator,
				 u_p_b_color,u_p_b_image_url,u_p_b_image_url_https,u_p_b_tile,
				 u_p_link_color,u_s_border_color,u_p_s_fill_color,u_p_text_color,
				 u_p_u_b_image,u_p_image_url,u_p_image_url_https,u_d_profile,u_d_profile_image,
				 u_following,u_follow_request_sent,u_notifications,
				 geo,cord,contri,retweet,fav_count,
				 htext,url,expandedurl,displayurl,screen_name,name,uid_str,
				 favorited,retweeted,p_sensitive,f_level,lang,timestamp
				 
				);
		 
		 System.out.println("---------------------source-------------"+source+"-------"+
				 		number);
		
		bulkRequest.add(
				transportClient
				.prepareIndex("stream", "tweet", number)
			    .setSource(source)
		);
		
		System.out.println("hello");
		
		
	if(bulkRequest.request().requests().size() == 0){
			
			System.out.println("\n\n No request Added!");
		
		} else{
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			
			if (bulkResponse.hasFailures()) {
			    System.out.println("ElasticSearch Failures: \n"+bulkResponse.buildFailureMessage());
			}
			
		}
		
	
		 } catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	
private static Map<String, Object> putJsonDocument(String created_at,
		String id_str, String text, String source, Boolean truncated,
		long in_reply_to_status_id, String in_reply_to_status_id_str
		,long  in_reply_to_user_id , String  in_reply_to_user_id_str,
		 String in_reply_to_screen_name,
		 String u_id_str,String u_name,String u_screen_name,String u_location,String u_url
		 ,String u_description,Boolean u_protected,Boolean u_verified,int u_followers_count,
		 int u_friends_count,
		 int u_listed_count,int u_favourites_count,int u_statuses_count,String u_created_at,int u_utc_offset,
		 String u_time_zone,Boolean u_geo_enabled,String u_lang,Boolean u_contributors_enabled,Boolean u_is_translator,
		 String u_p_b_color,String u_p_b_image_url,String u_p_b_image_url_https,Boolean u_p_b_tile,
		 String u_p_link_color,String u_s_border_color,String u_p_s_fill_color,String u_p_text_color,
		 Boolean u_p_u_b_image,String u_p_image_url,String u_p_image_url_https,Boolean u_d_profile,Boolean u_d_profile_image,
		 String u_following,String u_follow_request_sent,String u_notifications,
		 String geo,String cord,String contri,int retweet,int fav_count,
		 List<String> htext,List<String> url,List<String> expandedurl,List<String> displayurl,
		 List<String> screen_name,List<String> name,List<String> uid_str,
		 Boolean favorited,Boolean retweeted,Boolean p_sensitive,String f_level,String lang,String timestamp) 
		 {
	// TODO Auto-generated method stub
	
	Map<String, Object> jsonDocument = new HashMap<String, Object>();
	
	 
	jsonDocument.put("created_at", created_at);
	jsonDocument.put("id_str",id_str);
	jsonDocument.put("text", text);
	jsonDocument.put("source", source);
	jsonDocument.put("truncated", truncated);
	jsonDocument.put("in_reply_to_status_id", in_reply_to_status_id);
	jsonDocument.put("in_reply_to_status_id_str", in_reply_to_status_id_str);
	jsonDocument.put("in_reply_to_user_id", in_reply_to_user_id);
	jsonDocument.put("in_reply_to_user_id_str", in_reply_to_user_id_str);
	jsonDocument.put("in_reply_to_screen_name", in_reply_to_screen_name);
	jsonDocument.put("user id",u_id_str);
	jsonDocument.put("user name", u_name);
	jsonDocument.put("user screen name", text);
	jsonDocument.put("user location",u_location);
	jsonDocument.put("user url",u_url);
	jsonDocument.put("user description",u_description);
	jsonDocument.put("user protected",u_protected);
	jsonDocument.put("user verified",u_verified);
	jsonDocument.put("user follwers count",u_followers_count);
	jsonDocument.put("user friends count",u_friends_count);
	jsonDocument.put("user_listed_count",u_listed_count);
	jsonDocument.put("user favourites count",u_favourites_count);
	jsonDocument.put("user status count",u_statuses_count);
	jsonDocument.put("user created_at",u_created_at);
	jsonDocument.put("utc_offset",u_utc_offset);
	jsonDocument.put("time_zone",u_time_zone);
	jsonDocument.put("geo_enabled",u_geo_enabled);
	jsonDocument.put("user language",u_lang);
	jsonDocument.put("contributors enabled",u_contributors_enabled);
	jsonDocument.put("is translator",u_is_translator);
	jsonDocument.put("profile background color",u_p_b_color);
	jsonDocument.put("profile background image url",u_p_b_image_url);
	jsonDocument.put("profile background image url https",u_p_b_image_url_https);
	jsonDocument.put("profile background tile",u_p_b_tile);
	jsonDocument.put("profile link color",u_p_link_color);
	jsonDocument.put("profile sidebar border color",u_s_border_color);
	jsonDocument.put("profile sidebar fill color",u_p_s_fill_color);
	jsonDocument.put("profile text color",u_p_text_color);
	jsonDocument.put("profile use background image",u_p_u_b_image);
	jsonDocument.put("profile image url",u_p_image_url);
	jsonDocument.put("profile image url https",u_p_image_url_https);
	jsonDocument.put("default_image",u_d_profile);
	jsonDocument.put("default profile image",u_d_profile_image);
	jsonDocument.put("user followers",u_following);
	jsonDocument.put("user follow request sent",u_follow_request_sent);
	jsonDocument.put("user notifications",u_notifications);
	jsonDocument.put("user geo",geo);
	jsonDocument.put("user coordinates",cord);
	jsonDocument.put("user contributors",contri);
	jsonDocument.put("user retweet count",retweet);
	jsonDocument.put("user favorite count",fav_count);
	
	jsonDocument.put("hashtags",htext);
	jsonDocument.put("tweet url",url);
	jsonDocument.put("tweet expanded url",expandedurl);
	jsonDocument.put("tweet display url",displayurl);
	jsonDocument.put("user mention screen name",screen_name);
	jsonDocument.put("user mention name",name);
	jsonDocument.put("user mention id",uid_str);
	jsonDocument.put("is favorited",favorited);
	jsonDocument.put("is retweeted",retweeted);
	jsonDocument.put("is possibly sensitive",p_sensitive);
	jsonDocument.put("filter level",f_level);
	jsonDocument.put("language",lang);
	jsonDocument.put("timestamp",timestamp);
	
	return jsonDocument;

}

}
