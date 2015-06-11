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
public class ElasticDemo {
	
	public static void store(String msg,int num,BulkRequestBuilder bulkRequest,TransportClient transportClient,List<String> key)
	{
		
		String number= String.valueOf(num);
	
		//Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		
		 Map<String,Object> jsonDocument = new HashMap<String,Object>();
		 Map<String,Object> jsonDocument1 = new HashMap<String,Object>();
		 Map<String,Object> place = new HashMap<String,Object>();
		 Map<String,Object> place1 = new HashMap<String,Object>();
		
		 
		 
		//final Node node = nodeBuilder().clusterName("prophesee").node();
		
	//	final Client client   = node.client();
		 
		 try {
				JSONObject jobject = new JSONObject(msg);
				//ResponseList<Status> mentions = twitter.getMentionsTimeline(paging);
				
				  
				 
				String created_at=(String) jobject.get("created_at");
				jsonDocument.put("created_at", created_at);
				
				String id_str =  (String) jobject.get("id_str");
				jsonDocument.put("id_str",id_str);
				
				String text=(String) jobject.get("text");
				jsonDocument.put("text", text);
				
				String sourcee=(String) jobject.get("source");
				jsonDocument.put("source", sourcee);
				
				Boolean truncated =(Boolean) jobject.get("truncated");
				jsonDocument.put("truncated", truncated);
				
	/*			if(jobject.isNull("in_reply_to_status_id"))
			    {
			       jobject.put("in_reply_to_status_id", 0L);
			       
			    }
				long in_reply_to_status_id=(Long) jobject.get("in_reply_to_status_id");
				jsonDocument.put("in_reply_to_status_id", in_reply_to_status_id);
				
	*/			
				if(jobject.isNull("in_reply_to_status_id_str"))
			    {
			       jobject.put("in_reply_to_status_id_str", "null");
			       
			    }
				String in_reply_to_status_id_str=(String) jobject.get("in_reply_to_status_id_str");
				jsonDocument.put("in_reply_to_status_id_str", in_reply_to_status_id_str);
				
				
				
	/*			if(jobject.isNull("in_reply_to_user_id"))
			    {
			       jobject.put("in_reply_to_user_id",0);
			       
			    }
				int in_reply_to_user_id=(Integer) jobject.get("in_reply_to_user_id");
				jsonDocument.put("in_reply_to_user_id", in_reply_to_user_id);
	*/			
				
				if(jobject.isNull("in_reply_to_user_id_str"))
			    {
			       jobject.put("in_reply_to_user_id_str","null");
			       
			    }
				String in_reply_to_user_id_str=(String) jobject.get("in_reply_to_user_id_str");
				jsonDocument.put("in_reply_to_user_id_str", in_reply_to_user_id_str);
				
				
				if(jobject.isNull("in_reply_to_screen_name"))
			    {
			       jobject.put("in_reply_to_screen_name", "null");
			       
			    }
				String in_reply_to_screen_name=(String) jobject.get("in_reply_to_screen_name");
				jsonDocument.put("in_reply_to_screen_name", in_reply_to_screen_name);
				
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
				jsonDocument.put("user_id",u_id_str);
				
				String u_name=(String) user.get("name");
				jsonDocument.put("user_name", u_name);
				String u_screen_name=(String) user.get("screen_name");
				jsonDocument.put("user_screen_name",u_screen_name);
				String u_location=(String) user.get("location");
				jsonDocument.put("user_location",u_location);
				
				if(user.isNull("url"))
			    {
			       user.put("url", "null");
			       
			    }
				String u_url=(String) user.get("url");
				jsonDocument.put("user_url",u_url);
				if(user.isNull("description"))
			    {
			       user.put("description", "null");
			       
			    }
				String u_description=(String) user.get("description");
				jsonDocument.put("user_description",u_description);
				Boolean u_protected=(Boolean) user.get("protected");
				jsonDocument.put("user_protected",u_protected);
				Boolean u_verified=(Boolean) user.get("verified");
				jsonDocument.put("user_verified",u_verified);
				int u_followers_count=(Integer) user.get("followers_count");
				jsonDocument.put("user_follwers_count",u_followers_count);
				int u_friends_count=(Integer) user.get("friends_count");
				jsonDocument.put("user_friends_count",u_friends_count);
				
				int u_listed_count=(Integer) user.get("listed_count");
				jsonDocument.put("user_listed_count",u_listed_count);
				int u_favourites_count=(Integer) user.get("favourites_count");
				jsonDocument.put("user_favourites_count",u_favourites_count);
				int u_statuses_count=(Integer) user.get("statuses_count");
				jsonDocument.put("user_status_count",u_statuses_count);
				String u_created_at=(String) user.get("created_at");
				jsonDocument.put("user_created_at",u_created_at);
				if(user.isNull("utc_offset"))
			    {
			       user.put("utc_offset", 0);
			       
			    }
				int u_utc_offset=(Integer) user.get("utc_offset");
				jsonDocument.put("utc_offset",u_utc_offset);
				if(user.isNull("time_zone"))
			    {
			       user.put("time_zone","null");
			       
			    }
				String u_time_zone=(String) user.get("time_zone");
				jsonDocument.put("time_zone",u_time_zone);
				Boolean u_geo_enabled=(Boolean) user.get("geo_enabled");
				jsonDocument.put("geo_enabled",u_geo_enabled);
				String u_lang=(String) user.get("lang");
				jsonDocument.put("user_language",u_lang);
				Boolean u_contributors_enabled=(Boolean) user.get("contributors_enabled");
				jsonDocument.put("contributors_enabled",u_contributors_enabled);
				Boolean u_is_translator=(Boolean) user.get("is_translator");
				jsonDocument.put("is_translator",u_is_translator);
				String u_p_b_color=(String) user.get("profile_background_color");
				jsonDocument.put("profile_background_color",u_p_b_color);
				String u_p_b_image_url=(String) user.get("profile_background_image_url");
				jsonDocument.put("profile_background_image_url",u_p_b_image_url);
				String u_p_b_image_url_https=(String) user.get("profile_background_image_url_https");
				jsonDocument.put("profile_background_image_url_https",u_p_b_image_url_https);
				Boolean u_p_b_tile=(Boolean) user.get("profile_background_tile");
				jsonDocument.put("profile_background_tile",u_p_b_tile);
				String u_p_link_color=(String) user.get("profile_link_color");
				jsonDocument.put("profile_link_color",u_p_link_color);
				String u_s_border_color=(String) user.get("profile_sidebar_border_color");
				jsonDocument.put("profile-_idebar_border_color",u_s_border_color);
				String u_p_s_fill_color=(String) user.get("profile_sidebar_fill_color");
				jsonDocument.put("profile_sidebar_fill_color",u_p_s_fill_color);
				String u_p_text_color=(String) user.get("profile_text_color");
				jsonDocument.put("profile_text_color",u_p_text_color);
				Boolean u_p_u_b_image=(Boolean) user.get("profile_use_background_image");
				jsonDocument.put("profile_use_background_image",u_p_u_b_image);
				String u_p_image_url=(String) user.get("profile_image_url");
				jsonDocument.put("profile_image_url",u_p_image_url);
				String u_p_image_url_https=(String) user.get("profile_image_url_https");
				jsonDocument.put("profile_image_url_https",u_p_image_url_https);
				Boolean u_d_profile=(Boolean) user.get("default_profile");
				jsonDocument.put("default_image",u_d_profile);
				Boolean u_d_profile_image=(Boolean) user.get("default_profile_image");
				jsonDocument.put("default_profile_image",u_d_profile_image);
				if(user.isNull("following"))
			    {
			       user.put("following", "null");
			       
			    }
				String u_following=(String) user.get("following");
				jsonDocument.put("user_followers",u_following);
				if(user.isNull("follow_request_sent"))
			    {
			       user.put("follow_request_sent", "null");
			       
			    }
				String u_follow_request_sent=(String) user.get("follow_request_sent");
				jsonDocument.put("user_follow_request_sent",u_follow_request_sent);
				
				
				if(user.isNull("notifications"))
			    {
			       user.put("notifications", "null");
			       
			    }
				String u_notifications=(String) user.get("notifications");
				jsonDocument.put("user_notifications",u_notifications);
				
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
			//	String geo=(String) jobject.get("geo");
				//jsonDocument.put("user geo",geo);
				
				
				
				
				String cord="";
				
				if(jobject.isNull("coordinates"))
			    {
					
			       jobject.put("coordinates","null");
			        cord=(String) jobject.get("coordinates");
			      // System.out.println("coordinates**********"+cord);
			       
			    }
	/*			else
				{
					JSONArray cor=(JSONArray) jobject.get("coordinates");
					//System.out.print("coordinates*****");
					 for(int i=0; i<((Collection<String>) cor).size(); i++)
					 {
						 long j=(Long) cor.get(i);
						 System.out.print(j+"   ");
						 
					 }

				}
				
		*/		
				jsonDocument.put("user_coordinates",cord);
				
				String pid="",purl="",ptype="",pname="",pfullname="",pcode="",pcountry="";
				if(jobject.isNull("place"))
			    {
			       jobject.put("place","null");
			       String p=(String) jobject.get("place");
			       jsonDocument.put("place",p);
			     //  System.out.println("---place is Nul-----");
			       
			    }
				else
				{
					//System.out.println("-----has place----");
					JSONObject placee= (JSONObject) jobject.get("place");
					 pid=(String) placee.get("id");
					 purl=(String) placee.get("url");
					 ptype=(String) placee.get("place_type");
					 pname=(String) placee.get("name");
					 pfullname=(String) placee.get("full_name");
					pcode=(String) placee.get("country_code");
					 pcountry=(String) placee.get("country");
					 jsonDocument.put("place_id",pid);
					 jsonDocument.put("place_url",purl);
					 jsonDocument.put("place_type",ptype);
					 jsonDocument.put("place_name",pname);
					 jsonDocument.put("place_full_name",pfullname);
					jsonDocument.put("place_code",pcode);
					 jsonDocument.put("country",pcountry);
					
					//jsonDocument.put("place",place);
					
				}

				if(jobject.isNull("contributors"))
			    {
			       jobject.put("contributors","null");
			       
			    }
				String contri=(String) jobject.get("contributors");
				jsonDocument.put("user_contributors",contri);
				int retweet =(Integer) jobject.get("retweet_count");
				jsonDocument.put("user_retweet_count",retweet);
				int fav_count =(Integer) jobject.get("favorite_count");
				jsonDocument.put("user_favorite_count",fav_count);
				
				List<String> htext=new ArrayList<String>();
				List<String> url=new ArrayList<String>();
				List<String> expandedurl=new ArrayList<String>();
				List<String> displayurl=new ArrayList<String>();
				List<String> screen_name=new ArrayList<String>();
				List<String> name=new ArrayList<String>();
				List<String> uid_str=new ArrayList<String>();
				List<String> mid_str=new ArrayList<String>();
				List<String> media_url=new ArrayList<String>();
				List<String> media_url_https=new ArrayList<String>();
				List<String> murl=new ArrayList<String>();
				List<String> mexpandedurl=new ArrayList<String>();
				List<String> mdisplayurl=new ArrayList<String>();
							
				
				JSONObject entity =(JSONObject) jobject.get("entities");
				
				
				JSONArray hash=(JSONArray) entity.get("hashtags");
						int length = hash.length();
				  // take each value from the json array separately
				String k="";  
				for(int i=0;i<length;i++)
				{
					JSONObject innerObj = hash.getJSONObject(i);
				  
				  		String text1=(String) innerObj.get("text");
				  		htext.add(text1);
				  		System.out.println("hashtags-------"+text1);
				  		String t="#"+text1;
				  		
				  		for(int j=0;j<key.size();j++)
				  		{
				  			k=key.get(j);
				  			
				  			if(k.equals(t))
				  			{
				  				jsonDocument.put("source_keyword",k);
				  				System.out.println("-----source keyword-------"+k);
				  			}
				  		}
				          
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
				
				if(entity.has("media"))
				{
					//System.out.println("-----has Media-----");
				
				JSONArray med=(JSONArray) entity.get("media");
				int length4 = med.length();
		  // take each value from the json array separately
		  
				for(int i=0;i<length4;i++)
				{
				JSONObject innerObj = med.getJSONObject(i);
					
				String text1=(String) innerObj.get("id_str");
			  		mid_str.add(text1);
			  		
			  		String text2=(String) innerObj.get("media_url");
			  		media_url.add(text2);
			  		
			  		String text3=(String) innerObj.get("media_url_https");
			  		media_url_https.add(text3);
		  		
					
				String text4=(String) innerObj.get("url");
		  		murl.add(text4);
		  	//	System.out.println("url***********"+text1);
		  		
		  		String text5=(String) innerObj.get("expanded_url");
		  		mexpandedurl.add(text5);
		  		//System.out.println("expandedurl***********"+text2);
		  		
		  		String text6=(String) innerObj.get("display_url");
		  		mdisplayurl.add(text6);
		  		//System.out.println("displayurl***********"+text3);
		  		
				}
		  		
				jsonDocument.put("media_id_str",mid_str);
				jsonDocument.put("media_url",media_url);
				jsonDocument.put("media_url_https ",media_url_https);
				jsonDocument.put("m_url",murl);
				jsonDocument.put("m_display_url",mdisplayurl);
				jsonDocument.put("m_expanded_url",mexpandedurl);
				
				}	
				
				jsonDocument.put("hashtags",htext);
				jsonDocument.put("tweet_url",url);
				jsonDocument.put("tweet_expanded_url",expandedurl);
				jsonDocument.put("tweet_display_url",displayurl);
				jsonDocument.put("user_mention_screen_name",screen_name);
				jsonDocument.put("user_mention_name",name);
				jsonDocument.put("user_mention_id",uid_str);
				
									
				Boolean favorited=(Boolean) jobject.get("favorited");
				Boolean retweeted=(Boolean) jobject.get("retweeted");
				Boolean p_sensitive=(Boolean) jobject.get("possibly_sensitive");
				String f_level=(String) jobject.get("filter_level");
				String lang=(String) jobject.get("lang");
				String timestamp=(String) jobject.get("timestamp_ms");
				
				jsonDocument.put("is_favorited",favorited);
				jsonDocument.put("is_retweeted",retweeted);
				jsonDocument.put("is_possibly_sensitive",p_sensitive);
				jsonDocument.put("filter_level",f_level);
				jsonDocument.put("language",lang);
				jsonDocument.put("timestamp",timestamp);
	
			
				
				if(jobject.has("retweeted_status"))
			{
					
					System.out.println("-----is a retweeted tweet-------");
				JSONObject rjobject =(JSONObject) jobject.get("retweeted_status");
					
				
				String rcreated_at=(String) rjobject.get("created_at");
				jsonDocument1.put("r_created_at", rcreated_at);
				
				String rid_str =  (String) rjobject.get("id_str");
				jsonDocument1.put("r_id_str",rid_str);
				
				String rtext=(String) rjobject.get("text");
				jsonDocument1.put("r_text", rtext);
				
				String rsourcee=(String) rjobject.get("source");
				jsonDocument1.put("r_source", rsourcee);
				
				Boolean rtruncated =(Boolean) rjobject.get("truncated");
				jsonDocument1.put("r_truncated", rtruncated);
				
		/*		if(rjobject.isNull("in_reply_to_status_id"))
			    {
			       rjobject.put("in_reply_to_status_id", 0L);
			       
			    }
				long rin_reply_to_status_id=(Long) rjobject.get("in_reply_to_status_id");
				jsonDocument1.put("r_in_reply_to_status_id", rin_reply_to_status_id);
			*/	
				
				if(rjobject.isNull("in_reply_to_status_id_str"))
			    {
			       rjobject.put("in_reply_to_status_id_str", "null");
			       
			    }
				String rin_reply_to_status_id_str=(String) rjobject.get("in_reply_to_status_id_str");
				jsonDocument1.put("r_in_reply_to_status_id_str", rin_reply_to_status_id_str);
				
				
				
		/*		if(rjobject.isNull("in_reply_to_user_id"))
			    {
			       rjobject.put("in_reply_to_user_id",0L);
			       
			    }
				long rin_reply_to_user_id=(Long) rjobject.get("in_reply_to_user_id");
				jsonDocument1.put("r_in_reply_to_user_id", rin_reply_to_user_id);
				
			*/	
				if(rjobject.isNull("in_reply_to_user_id_str"))
			    {
			       rjobject.put("in_reply_to_user_id_str","null");
			       
			    }
				String rin_reply_to_user_id_str=(String) rjobject.get("in_reply_to_user_id_str");
				jsonDocument1.put("r_in_reply_to_user_id_str", rin_reply_to_user_id_str);
				
				
				if(rjobject.isNull("in_reply_to_screen_name"))
			    {
			       rjobject.put("in_reply_to_screen_name", "null");
			       
			    }
				String rin_reply_to_screen_name=(String) rjobject.get("in_reply_to_screen_name");
				jsonDocument1.put("r_in_reply_to_screen_name", rin_reply_to_screen_name);
				
			
				
				JSONObject ruser =(JSONObject) rjobject.get("user");
				
				//long u_id=(Long) user.get("id");
				String ru_id_str=(String) ruser.get("id_str");
				jsonDocument1.put("r_user_id",ru_id_str);
				
				String ru_name=(String) ruser.get("name");
				jsonDocument1.put("r_user_name", ru_name);
				String ru_screen_name=(String) ruser.get("screen_name");
				jsonDocument1.put("r_user_screen_name",ru_screen_name);
				String ru_location=(String) ruser.get("location");
				jsonDocument1.put("r_user_location",ru_location);
				
				if(ruser.isNull("url"))
			    {
			       ruser.put("url", "null");
			       
			    }
				String ru_url=(String) ruser.get("url");
				jsonDocument1.put("r_user_url",ru_url);
				if(ruser.isNull("description"))
			    {
			       ruser.put("description", "null");
			       
			    }
				String ru_description=(String) ruser.get("description");
				jsonDocument1.put("r_user_description",ru_description);
				Boolean ru_protected=(Boolean) ruser.get("protected");
				jsonDocument1.put("r_user_protected",ru_protected);
				Boolean ru_verified=(Boolean) ruser.get("verified");
				jsonDocument1.put("r_user_verified",ru_verified);
				int ru_followers_count=(Integer) ruser.get("followers_count");
				jsonDocument1.put("r_user_follwers_count",ru_followers_count);
				int ru_friends_count=(Integer) ruser.get("friends_count");
				jsonDocument1.put("r_user_friends_count",ru_friends_count);
				
				int ru_listed_count=(Integer) ruser.get("listed_count");
				jsonDocument1.put("r_user_listed_count",ru_listed_count);
				int ru_favourites_count=(Integer) ruser.get("favourites_count");
				jsonDocument1.put("r_user_favourites_count",ru_favourites_count);
				int ru_statuses_count=(Integer) ruser.get("statuses_count");
				jsonDocument1.put("r_user_status_count",ru_statuses_count);
				String ru_created_at=(String) ruser.get("created_at");
				jsonDocument1.put("r_user_created_at",ru_created_at);
				if(ruser.isNull("utc_offset"))
			    {
			       ruser.put("utc_offset", 0);
			       
			    }
				int ru_utc_offset=(Integer) ruser.get("utc_offset");
				jsonDocument1.put("r_utc_offset",ru_utc_offset);
				if(ruser.isNull("time_zone"))
			    {
			       ruser.put("time_zone","null");
			       
			    }
				String ru_time_zone=(String) ruser.get("time_zone");
				jsonDocument1.put("r_time_zone",ru_time_zone);
				Boolean ru_geo_enabled=(Boolean) ruser.get("geo_enabled");
				jsonDocument1.put("r_geo_enabled",ru_geo_enabled);
				String ru_lang=(String) ruser.get("lang");
				jsonDocument1.put("r_user_language",ru_lang);
				Boolean ru_contributors_enabled=(Boolean) ruser.get("contributors_enabled");
				jsonDocument1.put("r_contributors_enabled",ru_contributors_enabled);
				Boolean ru_is_translator=(Boolean) ruser.get("is_translator");
				jsonDocument1.put("r_is_translator",ru_is_translator);
				String ru_p_b_color=(String) ruser.get("profile_background_color");
				jsonDocument1.put("r_profile_background_color",ru_p_b_color);
				String ru_p_b_image_url=(String) ruser.get("profile_background_image_url");
				jsonDocument1.put("r_profile_background_image_url",ru_p_b_image_url);
				String ru_p_b_image_url_https=(String) ruser.get("profile_background_image_url_https");
				jsonDocument1.put("r_profile_background_image_url_https",ru_p_b_image_url_https);
				Boolean ru_p_b_tile=(Boolean) ruser.get("profile_background_tile");
				jsonDocument1.put("r_profile_background_tile",ru_p_b_tile);
				String ru_p_link_color=(String) ruser.get("profile_link_color");
				jsonDocument1.put("r_profile_link_color",ru_p_link_color);
				String ru_s_border_color=(String) ruser.get("profile_sidebar_border_color");
				jsonDocument1.put("r_profile_sidebar_border_color",ru_s_border_color);
				String ru_p_s_fill_color=(String) ruser.get("profile_sidebar_fill_color");
				jsonDocument1.put("r_profile_sidebar_fill_color",ru_p_s_fill_color);
				String ru_p_text_color=(String) ruser.get("profile_text_color");
				jsonDocument1.put("r_profile_text_color",ru_p_text_color);
				Boolean ru_p_u_b_image=(Boolean) ruser.get("profile_use_background_image");
				jsonDocument1.put("r_profile_use_background_image",ru_p_u_b_image);
				String ru_p_image_url=(String) ruser.get("profile_image_url");
				jsonDocument1.put("r_profile_image_url",ru_p_image_url);
				String ru_p_image_url_https=(String) ruser.get("profile_image_url_https");
				jsonDocument1.put("r_profile_image_url_https",ru_p_image_url_https);
				Boolean ru_d_profile=(Boolean) ruser.get("default_profile");
				jsonDocument1.put("r_default_image",ru_d_profile);
				Boolean ru_d_profile_image=(Boolean) ruser.get("default_profile_image");
				jsonDocument1.put("r_default_profile_image",ru_d_profile_image);
				if(ruser.isNull("following"))
			    {
			       ruser.put("following", "null");
			       
			    }
				String ru_following=(String) ruser.get("following");
				jsonDocument1.put("r_user_followers",ru_following);
				if(ruser.isNull("follow_request_sent"))
			    {
			       ruser.put("follow_request_sent", "null");
			       
			    }
				String ru_follow_request_sent=(String) ruser.get("follow_request_sent");
				jsonDocument1.put("r_user_follow_request_sent",ru_follow_request_sent);
				
				
				if(ruser.isNull("notifications"))
			    {
			       ruser.put("notifications", "null");
			       
			    }
				String ru_notifications=(String) ruser.get("notifications");
				jsonDocument1.put("r_user_notifications",ru_notifications);
						
				
				if(rjobject.isNull("geo"))
			    {
			       rjobject.put("geo","null");
			       
			    }
			//	String geo=(String) jobject.get("geo");
				//jsonDocument.put("user geo",geo);
				
				
				
				
				String rcord="";
				
				if(rjobject.isNull("coordinates"))
			    {
			       rjobject.put("coordinates","null");
			        rcord=(String) rjobject.get("coordinates");
			      // System.out.println("coordinates**********"+cord);
			       
			    }
/*				else
				{
					JSONArray rcor=(JSONArray) rjobject.get("coordinates");
					//System.out.print("coordinates*****");
					 for(int i=0; i<((Collection<String>) rcor).size(); i++)
					 {
						 long j=(Long) rcor.get(i);
						 System.out.print(j+"   ");
						 
					 }

				}
				
	*/			
				jsonDocument1.put("r_user_coordinates",rcord);
				
				String rpid="",rpurl="",rptype="",rpname="",rpfullname="",rpcode="",rpcountry="";
				if(rjobject.isNull("place"))
			    {
			       rjobject.put("place","null");
			       String p1=(String) rjobject.get("place");
			       jsonDocument1.put("r_place",p1);
			    }
				else
				{
					System.out.println("-----retweet has place----");
					JSONObject pla= (JSONObject) rjobject.get("place");
					rpid=(String) pla.get("id");
					 rpurl=(String) pla.get("url");
					ptype=(String) pla.get("place_type");
					rpname=(String) pla.get("name");
					rpfullname=(String) pla.get("full_name");
					// rpcode=(String) pla.get("country_code");
					rpcountry=(String) pla.get("country");
					jsonDocument1.put("r_place_id",rpid);
					jsonDocument1.put("r_place_url",rpurl);
					jsonDocument1.put("r_place_type",rptype);
					jsonDocument1.put("r_place_name",rpname);
					jsonDocument1.put("r_place_full_name",rpfullname);
					//place1.put("r_place_code",rpcode);
					jsonDocument1.put("r_country",rpcountry);
					
			//		jsonDocument1.put("retweeted_place",place1);
					
				}
		

				if(rjobject.isNull("contributors"))
			    {
			       rjobject.put("contributors","null");
			       
			    }
				String rcontri=(String) rjobject.get("contributors");
				jsonDocument1.put("r_user_contributors",rcontri);
				int rretweet =(Integer) rjobject.get("retweet_count");
				jsonDocument1.put("r_user_retweet_count",rretweet);
				int rfav_count =(Integer) rjobject.get("favorite_count");
				jsonDocument1.put("r_user_favorite_count",rfav_count);
				
				List<String> rhtext=new ArrayList<String>();
				List<String> rurl=new ArrayList<String>();
				List<String> rexpandedurl=new ArrayList<String>();
				List<String> rdisplayurl=new ArrayList<String>();
				List<String> rscreen_name=new ArrayList<String>();
				List<String> rname=new ArrayList<String>();
				List<String> ruid_str=new ArrayList<String>();
				List<String> rmid_str=new ArrayList<String>();
				List<String> rmedia_url=new ArrayList<String>();
				List<String> rmedia_url_https=new ArrayList<String>();
				List<String> rmurl=new ArrayList<String>();
				List<String> rmexpandedurl=new ArrayList<String>();
				List<String> rmdisplayurl=new ArrayList<String>();
				
				JSONObject rentity =(JSONObject) rjobject.get("entities");
				
				
				JSONArray rhash=(JSONArray) rentity.get("hashtags");
						int rlength = rhash.length();
				  // take each value from the json array separately
				  
				for(int i=0;i<rlength;i++)
				{
					JSONObject innerObj = rhash.getJSONObject(i);
				  
				  		String text1=(String) innerObj.get("text");
				  		rhtext.add(text1);
				  		//System.out.println("hashtags***********"+text1);
				          
				 }
				
				JSONArray rur=(JSONArray) rentity.get("urls");
				int rlength1 = rur.length();
		  // take each value from the json array separately
		  
				for(int i=0;i<rlength1;i++)
				{
					JSONObject innerObj = rur.getJSONObject(i);
		  
		  		String text1=(String) innerObj.get("url");
		  		rurl.add(text1);
		  	//	System.out.println("url***********"+text1);
		  		
		  		String text2=(String) innerObj.get("expanded_url");
		  		rexpandedurl.add(text2);
		  		//System.out.println("expandedurl***********"+text2);
		  		
		  		String text3=(String) innerObj.get("display_url");
		  		rdisplayurl.add(text3);
		  		//System.out.println("displayurl***********"+text3);
		  		
		          
				}
				
				JSONArray rum=(JSONArray) rentity.get("user_mentions");
				int rlength2 = rum.length();
		  // take each value from the json array separately
		  
				for(int i=0;i<rlength2;i++)
				{
					JSONObject innerObj = rum.getJSONObject(i);
		  
		  		String text1=(String) innerObj.get("screen_name");
		  		rscreen_name.add(text1);
		  		//System.out.println("screenname***********"+text1);
		  		
		  		String text2=(String) innerObj.get("name");
		  		rname.add(text2);
		  	//	System.out.println("name***********"+text2);
		  		
		  		String text3=(String) innerObj.get("id_str");
		  		ruid_str.add(text3);
		  		//System.out.println("id_str***********"+text3);
		  		
		          
				}
				
				if(rentity.has("media"))
				{
					//System.out.println("-----retweeted has Media-----");
				
				JSONArray rmed=(JSONArray) rentity.get("media");
				int length4 = rmed.length();
		  // take each value from the json array separately
		  
				for(int i=0;i<length4;i++)
				{
				JSONObject innerObj = rmed.getJSONObject(i);
					
				String text1=(String) innerObj.get("id_str");
			  		rmid_str.add(text1);
			  		
			  		String text2=(String) innerObj.get("media_url");
			  		rmedia_url.add(text2);
			  		
			  		String text3=(String) innerObj.get("media_url_https");
			  		rmedia_url_https.add(text3);
		  		
					
				String text4=(String) innerObj.get("url");
		  		rmurl.add(text4);
		  	//	System.out.println("url***********"+text1);
		  		
		  		String text5=(String) innerObj.get("expanded_url");
		  		rmexpandedurl.add(text5);
		  		//System.out.println("expandedurl***********"+text2);
		  		
		  		String text6=(String) innerObj.get("display_url");
		  		rmdisplayurl.add(text6);
		  		//System.out.println("displayurl***********"+text3);
		  		
				}
		  		
				jsonDocument1.put("r_media_id_str",rmid_str);
				jsonDocument1.put("r_media_url",rmedia_url);
				jsonDocument1.put("r_media_url_https",rmedia_url_https);
				jsonDocument1.put("r_m_url",rmurl);
				jsonDocument1.put("r_m_display_url",rmdisplayurl);
				jsonDocument1.put("r_m_expanded_url",rmexpandedurl);
				
				}	
				
				jsonDocument1.put("r_hashtags",rhtext);
				jsonDocument1.put("r_tweet_url",rurl);
				jsonDocument1.put("r_tweet_expanded_url",rexpandedurl);
				jsonDocument1.put("r_tweet_display_url",rdisplayurl);
				jsonDocument1.put("r_user_mention_screen_name",rscreen_name);
				jsonDocument1.put("r_user_mention_name",rname);
				jsonDocument1.put("r_user_mention_id",ruid_str);
				
				Boolean rfavorited=(Boolean) rjobject.get("favorited");
				Boolean rretweeted=(Boolean) rjobject.get("retweeted");
				Boolean rp_sensitive=(Boolean) rjobject.get("possibly_sensitive");
				String rf_level=(String) rjobject.get("filter_level");
				String rlang=(String) rjobject.get("lang");
				
				
				jsonDocument1.put("r_is_favorited",rfavorited);
				jsonDocument1.put("r_is_retweeted",rretweeted);
				jsonDocument1.put("r_is_possibly_sensitive",rp_sensitive);
				jsonDocument1.put("r_filter_level",rf_level);
				jsonDocument1.put("r_language",rlang);
				
				
				
			}
				
			jsonDocument.put("retweeted_user",jsonDocument1);	
		
	
		// System.out.println("---------------------source-------------"+jsonDocument+"-------"+number);
		
		bulkRequest.add(
				transportClient
				.prepareIndex("sex", "twee", id_str)
			    .setSource(jsonDocument));
		 	
		
		
		
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


}
