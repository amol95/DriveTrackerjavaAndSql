package com.create;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjMap {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static Object getObjectFromJSON(String jsonString, Class<Createdrive> reg) {
			
			Object rd = null;
			

			try 
			{
				rd = mapper.readValue(jsonString, reg);
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
			return rd;

		}

		public static String getJSONFromObject(Object object) {
			String jsondata = null;

			try
			{
				jsondata = mapper.writeValueAsString(object);
			} 
			catch (Exception e)
			{
				System.out.println(e);
			}

			return jsondata;

		}

	}

