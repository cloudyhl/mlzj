package com.mlzj.storm.bolt.demo.drpc;

import org.apache.storm.utils.DRPCClient;
import org.apache.storm.utils.Utils;

import java.util.Map;

public class DrpcExclam {

	public static void main(String[] args) throws Exception {
		Map<String, Object> stringObjectMap = Utils.readDefaultConfig();
		DRPCClient client = new DRPCClient(stringObjectMap,"47.94.162.134", 3772);
	      for (String word : new String[]{ "hello", "goodbye" }) {
	    	  System.out.println(client.execute("exclamation", word));
	      }
	}
}
