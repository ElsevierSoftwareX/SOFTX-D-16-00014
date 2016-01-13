package com.csit.packages.mobilesurvey;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SportsRegularActivity extends Activity {
	
	
	
	
	public String sportsRegular[];                     
	
	
	
	
	TextView  main_question;
	TextView questionsTextView[];
	RadioButton radioButtons[][];
	RadioGroup radioGroups[];
	Boolean Allanswered=false;
	Boolean selected[];
	
	Boolean other_selected=false;
	String number_of_times ="none";
	String times_for_other="none";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ever_main);
		
		
		Allanswered=false;
	
		
		Intent intent=getIntent();
		other_selected=intent.getBooleanExtra("other", true);
		
		
		
		
		sportsRegular=getResources().getStringArray(R.array.sports_array);
		
		main_question = new TextView(this);
		
		main_question.setText("How many times have you done in the last 7 days?");
		main_question.setTextSize(20);
		main_question.setId(100);
		main_question.setLayoutParams(new LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		
			
		 LinearLayout linearLayout = (LinearLayout) findViewById (R.id.linear_layout);
			
	     linearLayout.setBackgroundColor(Color.TRANSPARENT);
	     
	     linearLayout.addView(main_question);
	    
	
	     
	     SharedPreferences sports_results = getSharedPreferences("regularSportsResults", 0);
	    // everTobacco= results.getBoolean("anySports", true);
	     
	     
			
			questionsTextView = new TextView  [sportsRegular.length];
			radioButtons = new RadioButton [sportsRegular.length][4];
			radioGroups = new RadioGroup [sportsRegular.length];
			selected = new Boolean [sportsRegular.length];
			
			
			
			      for (int i=0; i<sportsRegular.length; i++){
				
			    	  
			    	  questionsTextView[i] = new TextView(this);
			    	
				          questionsTextView[i].setText(sportsRegular[i]) ;
				          questionsTextView[i].setTextSize(15);
				         
				          questionsTextView[i].setId(i+27);                    //check uniqueness of this ID
				          
				          questionsTextView[i].setLayoutParams(new LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				
			      }
			
			
						      
			      for (int i=0; i<sportsRegular.length; i++){
						
			    	  
			    	  radioButtons[i][0] = new RadioButton(this);
			    	  radioButtons[i][1]= new RadioButton(this);
			    	  radioButtons[i][2]= new RadioButton(this);
			    	  radioButtons[i][3]= new RadioButton(this);
			    	  radioButtons[i][0].setText("1-2");
			    	  radioButtons[i][1].setText("3-4"); 
			    	  radioButtons[i][2].setText("5-6");
			    	  radioButtons[i][3].setText("7 or more");
			    	 // radioButtons[i][1].setChecked(true);
			  
		       }
			      
			      
			      
			
			      for (int i=0; i<sportsRegular.length; i++){
						
			    	  radioGroups[i]=new RadioGroup(this);
			    	  radioGroups[i].setOrientation(RadioGroup.HORIZONTAL) ;
			          radioGroups[i].addView(radioButtons[i][0]);
			    	  radioGroups[i].addView(radioButtons[i][1]);
			    	  radioGroups[i].addView(radioButtons[i][2]);
			    	  radioGroups[i].addView(radioButtons[i][3]);
			    	  
			    	  
		      }
		
		   
			      for (int i=0; i<sportsRegular.length; i++){
			    	  
			    	
			    	  if(sports_results.getBoolean("sports"+i, true)){
			    		  selected[i]=true;
			    	//  if  (selected[i]){
						
			    	  linearLayout.addView(questionsTextView[i]);
			    	  linearLayout.addView(radioGroups[i]);
			    	
			            }
			    	  
			    	  else {selected[i]=false;}
			    
		           } // end for
			
	
	}
	  




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ever, menu);
		return true;
	}

	@Override
	 public void onBackPressed () {
		//showToast();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode==KeyEvent.KEYCODE_HOME)
	    {
	    	//showToast();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
public void onClickSubmit (View view){
		
	
	  //check
	   Allanswered=true;
	
	  for (int i=0; i<sportsRegular.length; i++){
		  
			  if (selected[i]){
			         
				  if (!(radioButtons[i][0].isChecked() || radioButtons[i][1].isChecked() || radioButtons[i][2].isChecked() || radioButtons[i][3].isChecked())) {       // no selection?
	    	                Allanswered=false;
	    		            }
			  }  
		 	 
     
          }// end for
        
        
        
	//==================================================================  
	  
	 

	//======================================================================  
	  
	  
	  
	  
	       if (Allanswered==false){
	  	    	   
	    	      Toast.makeText(getApplicationContext(), "Please answer all the questions", Toast.LENGTH_LONG).show();
	  	  
	      }
	  
	        
	       
	       
	     else if (Allanswered==true){
	            	
	           	
	            	
	         
	    	 
	    	 
	    	 for (int i=0; i < sportsRegular.length; i++ )   {	
	            	
	            	
	        	 if(selected[i]){ 
	        	  if (radioButtons[i][0].isChecked()){
	            		
	            		number_of_times="1-2";
	            	}
	            	else if((radioButtons[i][1].isChecked())) {
	            		number_of_times="3-4";
	            	}
	            	else if((radioButtons[i][2].isChecked())) {
	            		number_of_times="5-6";
	            	}
	            	else if((radioButtons[i][3].isChecked())) {
	            		number_of_times="7 or more";
	            	}
	        	      
	        	  SharedPreferences.Editor num_times_results = getSharedPreferences("numberOfTimesSport", 0).edit();
	        		 num_times_results.putString("sports"+i, number_of_times);
		    		 num_times_results.commit();
		    		 
		    		    
		    		 
		    		           if (sportsRegular[i].contains("Other")){
            	                    times_for_other=number_of_times;
            	   
                              }
	        	  
	        	 }
	        	 
	        	 else {    // if sports i is not selected
	        		 
	        		 SharedPreferences.Editor num_times_results = getSharedPreferences("numberOfTimesSport", 0).edit();
	        		 num_times_results.putString("sports"+i, "none");
		    		 num_times_results.commit();
	        	 }
	            	
	        	 
	        	 
	        	 
	        	 
	          } // end for
	            	
	            	
	            	

	    			
	            	if(other_selected){
	            		
	            		 Intent intent = new Intent(this, OtherSportActivity.class);   
	            		 intent.putExtra("times_for_other", times_for_other);
	            	     startActivity(intent);
	            		
	            		
	            		
	            		   finish();
	            	} else{
	            	
	            	
	            	
	            	
	            	SharedPreferences getResults=getSharedPreferences("sportsClubResults", 0);
	            	
	            	if(getResults.getBoolean("anyClubs", true)){
	            	
	            	     Intent intent = new Intent(this, ClubsListActivity.class);   
	            	     startActivity(intent);
	            	     finish();
	            	}
	            	
	            	else if(getResults.getBoolean("anyNonClubs", true)){
	            		 Intent intent = new Intent(this, NonClubsListActivity.class);   
	            	     startActivity(intent);
	            	     finish();
	            	} 
	            	
	            	  else{
	            		
	            		 Intent intent2 = new Intent(this, ThursdayQHttpService.class);
	         				
            		     startService(intent2);
            		     
            		    
            		     
            		 //checking user selection mode shared preferences
   				         Boolean adminMode=false;
   				   		
   				   		SharedPreferences getUserMode = getSharedPreferences("userMode", 0);
   				   		
   				   		adminMode=getUserMode.getBoolean("Admin", false);
   				   		
   				   		if(adminMode){
   				   			//send intent to weekly sports and club activities
   				   			
   				   		   //  ResetResults rr = new ResetResults();
   						  //  rr.resetWeeklySurveys(getApplicationContext());
   						    
   					 
   				   		Toast.makeText(getApplicationContext(), "Thank you for taking part. Remember you can upload a photo as part of the mobiQ study.", Toast.LENGTH_LONG).show();
   					    
   					            // Intent intent3 = new Intent(this, MainActivity.class);
   						
   						        // startActivity(intent3);
   				   			
   				   		//Start camera activity================================================
  	    				   		
	   	    				   //	Intent camintent = new Intent(this, CameraActivity.class);
	   	    			   		
	   	    			      //  startActivity(camintent); 
   				   			
   				   			
   				   		          } //end if admin mode
   				   		
   				   		else {
   				   			
   				   		Toast.makeText(getApplicationContext(), "Thank you for taking part. Remember you can upload a photo as part of the mobiQ study.", Toast.LENGTH_LONG).show();
   				   		   //====================================================================================================
	    				   		
   	    				   	//Start camera activity=========================================
   	    				   		
   	    				    	//Intent camintent = new Intent(this, CameraActivity.class);
   	    			   		
   	    			           // startActivity(camintent); 
   	    			       
   	    			        //===========================================================================
   				   			 
   				   		        }// end else if not admin mode
   				   		
            		     
            		     
            		     
            		     
            		     
            		     
            		     
            		           	
	            	}          //upload results
	            	
	            	
	                       	
	            	
	            	
	            	   //Todo
	   	 		    
	   	 		    // store results
	            	
	            	
	    		      finish();
	    		      
	    		      
	            	} // end else if other_selected
	            
	     } // end if allAnswered = true
	      
	    	
	       
	          
		
	  }
    
    
  
	
	
	
}
