package app;

import com.google.protobuf.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class ProtoBufToolEncoder
{
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException,
            ParseException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IOException
    {
        SpringApplication.run(ProtoBufToolEncoder.class);
        System.out.println("Serializing your json file using proto buf technique.. ");
       // compileJsonFileToBinaryFormUsingProtoCompiler();
       // new ProtoBufToolEncoder().encode(args);

    }

    private static void compileJsonFileToBinaryFormUsingProtoCompiler() {
        try
        {
            String[] command = {"cmd.exe", "/c", "Start","D:\\ProtoBufToolEncoder\\src\\main\\resources\\MyScript.bat"};
            Runtime.getRuntime().exec(command);

        }
       catch (IOException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void encode(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException,
            ParseException, ClassNotFoundException
    {
        try {
              // Object obj = new JSONParser().parse(new FileReader(args[0]));
            Object obj = new JSONParser().parse(new FileReader("D:\\ProtoBufToolEncoder\\src\\main\\resources\\employee.json"));
            JSONObject jo = (JSONObject) obj;
           // Class c = Class.forName(args[1]);
            ClassLoader classLoader = ProtoBufToolEncoder.class.getClassLoader();


            Class c = classLoader.loadClass("D\\ProtoBufToolEncoder\\src\\main\\java\\app\\Employee.java");
            // Class c = Class.forName("app.Employee");
              Method method = c.getDeclaredMethod("newBuilder");
            Object employeeBuilder = method.invoke(method);
            method.setAccessible(true);
            Method method2 = employeeBuilder.getClass().getDeclaredMethod("build");
            method2.invoke(employeeBuilder);
            Object message = getObject(jo, employeeBuilder);
            writeByteCodeToFile(message, args);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private Object getObject(JSONObject jo, Object employeeBuilder) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, IOException
    {
        Set<Map.Entry<String, String>> map = jo.entrySet();

        for (Map.Entry<String, String> m : map) {
            Iterator<Map.Entry<String, String>> iterator = map.iterator();
            Method method1 = employeeBuilder.getClass().getDeclaredMethod("set" + m.getKey(), new Class[]{String.class});
            method1.invoke(employeeBuilder, m.getValue());
        }
        Method buildMethod = employeeBuilder.getClass().getDeclaredMethod("build");
        return buildMethod.invoke(employeeBuilder);

    }

    private void writeByteCodeToFile(Object message, String[] args) throws IOException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException
    {
        Method writeToMethod = message.getClass().getDeclaredMethod("writeTo", new Class[]{CodedOutputStream.class});
      // FileOutputStream outputStream = new FileOutputStream(args[1]+".obj");
          FileOutputStream outputStream = new FileOutputStream("D:\\ProtoBufToolEncoder\\src\\main\\resources\\Employee.obj");
        CodedOutputStream codedOutputStream = CodedOutputStream.newInstance(outputStream);
        System.out.println("--------------------------------------------------");
        System.out.println("Go and check protobuf file");
        System.out.println("--------------------------------------------------");
        writeToMethod.invoke(message,codedOutputStream);
        codedOutputStream.flush();
    }
}
