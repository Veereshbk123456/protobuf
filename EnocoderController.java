package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.CodedOutputStream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class EnocoderController
{
    @RequestMapping(value = "/encoder/{className}",method = RequestMethod.POST)
    public ResponseEntity  encode(@RequestBody JSONObject object, @PathVariable String className) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, IOException, ParseException {
        compileJsonFileToBinaryFormUsingProtoCompiler();
        //File file = new File("D:\\ProtoBufToolEncoder\\src\\main\\java\\app");

        //convert the file to URL format
      //  URL url = file.toURI().toURL();
      //  URL[] urls = new URL[]{url};

        //load this folder into Class loader
    //    ClassLoader cl = new URLClassLoader(urls);

        //load the Address class in 'c:\\other_classes\\'
      //  Class  cls = cl.loadClass("app."+className);
        // Class c = Class.forName(args[1]);
       // ClassLoader classLoader = ProtoBufToolEncoder.class.getClassLoader();
        //Class c = classLoader.loadClass("app."+className);
        Class c = Class.forName("app."+className);
        Method method = c.getDeclaredMethod("newBuilder");
        Object employeeBuilder = method.invoke(method);
        method.setAccessible(true);
        Method method2 = employeeBuilder.getClass().getDeclaredMethod("build");
        method2.invoke(employeeBuilder);
        Object message =  getObject(object, employeeBuilder);
      //  JSONParser parser = new JSONParser();
       // JSONObject json = (JSONObject) parser.parse(message.toString());
        writeByteCodeToFile(message, className);
        return new ResponseEntity<Object>("Json is encoded in protobuf format", HttpStatus.OK);


    }
    private static void compileJsonFileToBinaryFormUsingProtoCompiler() {
        try
        {
            String[] command = {"cmd.exe", "/c", "Start","MyScript.bat"};
            Runtime.getRuntime().exec(command);
            Thread.sleep(2000);

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getObject(JSONObject jo, Object employeeBuilder) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, IOException
    {
        Set<Map.Entry<String, String>> map = jo.entrySet();

        for (Map.Entry<String, String> m : map)
        {
            Method method1 = employeeBuilder.getClass().getDeclaredMethod("set" + m.getKey(), new Class[]{String.class});
            method1.invoke(employeeBuilder, m.getValue());
        }
        Method buildMethod = employeeBuilder.getClass().getDeclaredMethod("build");
        return buildMethod.invoke(employeeBuilder);

    }
    private void writeByteCodeToFile(Object message, String className) throws IOException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException
    {
        Method writeToMethod = message.getClass().getDeclaredMethod("writeTo", new Class[]{CodedOutputStream.class});
        // FileOutputStream outputStream = new FileOutputStream(args[1]+".obj");
        FileOutputStream outputStream = new FileOutputStream(className+".obj");
        CodedOutputStream codedOutputStream = CodedOutputStream.newInstance(outputStream);
        System.out.println("--------------------------------------------------");
        System.out.println("Go and check protobuf file");
        System.out.println("--------------------------------------------------");
        writeToMethod.invoke(message,codedOutputStream);
        codedOutputStream.flush();
    }

}
