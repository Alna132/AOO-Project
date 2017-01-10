package ie.gmit.sw;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.jar.*;

/**
 * InputJARFile handles the reading of the JAR file. 
 * Code from the "Dynamic Class Introspection" is used here. 
 * The output details of the class, interfaces, methods and packages are also handled here.
 */

public class InputJARFile {
	
	public InputJARFile(String jarName) {
		initialise(jarName);
    }
	
	public void initialise(String pathName) {
		try{
			File file  = new File(pathName);

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);
			
			//- Code from Dynamic Class Introspection.
			JarInputStream in = new JarInputStream(new FileInputStream(new File(pathName)));
			JarEntry next = in.getNextJarEntry();
			
			while(next != null) {
				if(next.getName().endsWith(".class")){
					String name = next.getName().replaceAll("/", "\\.");
					name = name.replaceAll(".class", "");
					if(!name.contains("$")) name.substring(0, name.length()-".class".length());
					//- System.out.println(name);
					Class cls = Class.forName(name, false, cl);
                    System.out.println("Class Name: " + cls.getName());
                    printDetails(cls);
				}//- End of if
				next = in.getNextJarEntry();
			}//- End of while	
		} catch (FileNotFoundException e){
            System.out.println("JAR file was not found!");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }//- End of try/catch
		
	}//- End of initalise
	
    public void printDetails(Class cls){

    	//- Get the package in which the class is contained.
        Package pack = cls.getPackage();
        System.out.println("Package Name: " + pack.getName());

        boolean interfaced = cls.isInterface();
        System.out.println("Is Class an Interface?: " + interfaced);

        //- Get the interfaces that the class implements.
        Class[] interfaces = cls.getInterfaces();
        for(Class i : interfaces){
            System.out.println("Implements Interface: " + i.getName());
        }//- End of for

        //- Get the constructors of the class.
        Constructor[] cons = cls.getConstructors();
        Class[] constructorParams;
        for(Constructor c : cons){
            System.out.println("Contructor: " + c.getName());
            constructorParams = c.getParameterTypes();
            for(Class param : constructorParams){
                System.out.println("Constructor Param: " + param.getName());
            }//- End of inner for
        }//- End of outer for

        Field[] fields = cls.getFields();
        for(Field f : fields){
            System.out.println("Field: " + f.getName());
        }//- End of for

        //- Get the methods of the class
        Method[] methods = cls.getMethods();
        Class[] methodParams;
        for(Method m : methods){
            System.out.println("Method: " + m.getName());

            Class methodReturnType = m.getReturnType();
            System.out.println("Method Return Type: " + methodReturnType.getName());

            methodParams = m.getParameterTypes();
            for(Class mp : methodParams){
                System.out.println("Method Param: " + mp.getName());
            }//- End of inner for
        }//- End of outer for
    }//- End of printClassDetails()	
}//- End of InputJARFile
