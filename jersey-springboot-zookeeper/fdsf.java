package org.soachina.rest.component.resource;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.List;  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.restlet.ext.fileupload.RestletFileUpload;  
import org.restlet.representation.Representation;  
import org.restlet.resource.Post;  
import org.restlet.resource.ServerResource;  
  
public class PostDemoResource extends ServerResource {  
    @Post  
    public Representation post(Representation entity) {  
        //        getResponse().setStatus(Status.SUCCESS_OK);    
        //        return new StringRepresentation("11111111111" + parameters);   
        Representation rep = null;  
        // 1/ Create a factory for disk-based file items  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //System.out.println("大头");  
        // 2/ Create a new file upload handler based on the Restlet  
        // FileUpload extension that will parse Restlet requests and  
        // generates FileItems.  
        RestletFileUpload upload = new RestletFileUpload(factory);  
          
        // 3/ Request is parsed by the handler which generates a  
        // list of FileItems  
        List<FileItem> items = null;  
        try {  
            items = upload.parseRepresentation(entity);  
        } catch (FileUploadException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
          
        String filename =  "";  
          
        for (FileItem fi : items) {  
            filename = fi.getName();  
            System.out.println("Save image ... " + filename);  
            /*新建一个图片文件*/  
            String extName=filename.substring(filename.lastIndexOf("."));  
            String newName=new SimpleDateFormat("yyyyMMDDHHmmssms").format(new Date());  
            File file=new File(newName+extName);    
            if(!file.exists()){//判断文件是否存在    
                try {    
                    file.createNewFile();  //创建文件    
                        
                } catch (IOException e) {    
                    // TODO Auto-generated catch block    
                    e.printStackTrace();    
                }    
            }    
            /*获取文件路径*/    
            String path_=file.getPath();    
            /*获取绝对路径名*/    
            String absPath=file.getAbsolutePath();    
            /*获取父亲文件路径*/    
            String parent=file.getParent();    
            try {  
                fi.write(file);  
            } catch (Exception e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
  
        }  
        return null;  
    }  
}  