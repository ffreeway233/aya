/**
 * 
 */
package util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * Title: newyangguang<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-3-17 下午3:17:27 <br>
 * @author freeway
 */
public class DeletePic {
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";  
	public static void deleteFile(List<String> picPaths,String url) {
		System.out.println();
	    boolean flag = false;  
	    	for (String path : picPaths) {
	    		path=url+path;
		    	if(path.matches(matches)){
		    	 File file = new File(path);  
		    	 if (file.isFile() && file.exists()) {  
		 	        file.delete();  
		 	    } 
	    	}
		}
	/*   for (int i = 0; i < picPaths.size(); i++) {
		   if(picPaths.get(i).matches(matches)){
		    	 File file = new File(picPaths.get(i));  
		    	 if (file.isFile() && file.exists()) {  
		 	        file.delete();  
		 	    }
		    	if(i==picPaths.size()){
		    		flag=true;
		    	}
	    	}
	}*/
	    // 路径为文件且不为空则进行删除  
	   // return flag;  
	}  

}
