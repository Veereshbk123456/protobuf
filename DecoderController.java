package app;

import com.google.protobuf.ExtensionRegistryLite;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
public class DecoderController
{
    @RequestMapping(value = "/decoder/{className}/{encodedFile}",method = RequestMethod.GET)
    public ResponseEntity decode( @PathVariable String className,@PathVariable String encodedFile) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, IOException, ParseException {
        {
                System.out.println("Serializing your json file using protobuf technique.. ");
                try {
                    String[] command = {"cmd.exe", "/C", "Start", "MyScript.bat"};
                    Runtime.getRuntime().exec(command);
                } catch (IOException ex) {
                }

            try {
                // InputStream fileInputStream = new FileInputStream(args[0]);
                InputStream fileInputStream = new FileInputStream(encodedFile+".obj");
                //Class c = Class.forName(args[1]);
                Class c = Class.forName("app."+className);
                Method method1 = c.getMethod("parseFrom", InputStream.class, ExtensionRegistryLite.class);
                ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.newInstance();
                //  FileWriter fileWriter = new FileWriter(args[1]) ;
                FileWriter fileWriter = new FileWriter(className+".json");
                Object object = method1.invoke(c, fileInputStream, extensionRegistryLite).toString();
                System.out.println(object);
                System.out.println("--------------------------------------------------");
                System.out.println("Go and check json file");
                System.out.println("--------------------------------------------------");
                Object o = object.toString().replaceAll("/n", "/n,");
                System.out.println(o);
                fileWriter.write(o.toString());
                fileWriter.flush();
                return new ResponseEntity<String>(method1.invoke(c, fileInputStream, extensionRegistryLite).toString(), HttpStatus.OK);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            }
        }
    }


